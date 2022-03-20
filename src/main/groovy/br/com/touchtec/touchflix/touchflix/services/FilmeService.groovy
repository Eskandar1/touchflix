package br.com.touchtec.touchflix.touchflix.services

import br.com.touchtec.touchflix.touchflix.dto.ComentarioDTO
import br.com.touchtec.touchflix.touchflix.dto.FilmeDTO
import br.com.touchtec.touchflix.touchflix.models.Comentario
import br.com.touchtec.touchflix.touchflix.models.Filme

import br.com.touchtec.touchflix.touchflix.repositories.FilmeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class FilmeService {
    @Autowired
    FilmeRepository filmeRepository

    List<Filme> buscarFilmes() {
        return filmeRepository.findAll()
    }

    Filme buscarFilme(long idFilme) {
        Optional<Filme> filme = filmeRepository.findById(idFilme)
        if (!filme.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Não foi encontrado filme com id informado")
        } else {
            return filme.get()
        }
    }

    Filme adicionarFilme(Filme filme) {
        return filmeRepository.save(filme)
    }

    void deletarFilme(long idFilme) {
        Filme filme = buscarFilme(idFilme)
        filmeRepository.delete(filme)
    }

    Filme modificarFilme(long idFilme, FilmeDTO filmeDTO) {
        Filme filme = buscarFilme(idFilme)
        boolean houveMudanca = false
        if (filmeDTO.nome != null && filme.nome != filmeDTO.nome) {
            filme.setNome(filmeDTO.nome)
            houveMudanca = true
        }
        if (filmeDTO.anoLancamento != null && filme.anoLancamento != filmeDTO.anoLancamento) {
            filme.setAnoLancamento(filmeDTO.anoLancamento)
            houveMudanca = true
        }
        if (filmeDTO.categoria != null && filme.categoria != filmeDTO.categoria) {
            filme.setCategoria(filmeDTO.categoria)
            houveMudanca = true
        }
        if (filmeDTO.sinopse != null && filme.sinopse != filmeDTO.sinopse) {
            filme.setSinopse(filmeDTO.sinopse)
            houveMudanca = true
        }
        if (!houveMudanca) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Não houve mudança")
        }

        return filmeRepository.save(filme)
    }

    List<Comentario> adicionarComentarioFilme(long idFilme, Comentario comentario) {
        Filme filme = buscarFilme(idFilme);
        if (filme.comentarios == null || filme.comentarios.isEmpty()) {
            filme.setComentarios([comentario]);
        } else {
            List<Comentario> comentarios = filme.comentarios
            comentarios.add(comentario)
        }
        filmeRepository.save(filme)
        Filme filmeAlterado = filmeRepository.findById(idFilme).get()
        return filmeAlterado.comentarios
    }

    Comentario buscarComentarioDeFilme(long idFilme, long idComentario) {
        Filme filme = buscarFilme(idFilme)
        Comentario comentarioEncontrado = filme.comentarios.find { comentario ->
            comentario.id = idComentario
        }
        if (comentarioEncontrado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontado o comentário")
        }
        return comentarioEncontrado
    }

    Comentario modificarComentarioDeFilme(
            long idFilme,
            long idComentario,
            ComentarioDTO comentarioForm
    ) {
        boolean houveMudanca = false
        Comentario comentarioAlterado = buscarComentarioDeFilme(idFilme, idComentario)
        Filme filme = buscarFilme(idFilme)
        if (comentarioForm.nota != null && comentarioAlterado.nota != comentarioForm.nota) {
            comentarioAlterado.setNota(comentarioForm.nota)
            houveMudanca = true
        }
        if (comentarioForm.texto != null && comentarioAlterado.texto != comentarioForm.texto) {
            comentarioAlterado.setTexto(comentarioForm.texto)
            houveMudanca = true
        }
        if (!houveMudanca) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Não houve mudança")
        }
        filme.comentarios.each { comentario ->
            if (comentario.id == comentarioAlterado.id) {
                comentario = comentarioAlterado
            }
        }
        filmeRepository.save(filme)
        return buscarComentarioDeFilme(idFilme, idComentario)

    }

    void deletarComentario(long idFilme, long idComentario) {
        Comentario comentario = buscarComentarioDeFilme(idFilme, idComentario)
        Filme filme = buscarFilme(idFilme)
        List<Comentario> comentariosAlterados = filme.comentarios.findAll { comentarios ->
            comentarios != comentario
        }
        filme.setComentarios(comentariosAlterados)
        filmeRepository.save(filme)
    }
}
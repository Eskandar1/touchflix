package br.com.touchtec.touchflix.touchflix.services

import br.com.touchtec.touchflix.touchflix.dto.ComentarioDTO
import br.com.touchtec.touchflix.touchflix.dto.SerieDTO
import br.com.touchtec.touchflix.touchflix.models.Comentario
import br.com.touchtec.touchflix.touchflix.models.Serie
import br.com.touchtec.touchflix.touchflix.repositories.SerieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SerieService {
    @Autowired
    SerieRepository serieRepository

    List<Serie> buscarSeries() {
        return serieRepository.findAll()
    }

    Serie buscarSerie(long idSerie) {
        Optional<Serie> serie = serieRepository.findById(idSerie)
        if (!serie.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Não foi encontrado sésrie com id informado")
        } else {
            return serie.get()
        }
    }

    Serie adicionarSerie(Serie serie) {
        return serieRepository.save(serie)
    }

    void deletarSerie(long idSerie) {
        Serie serie = buscarSerie(idSerie)
        serieRepository.delete(serie)
    }

    Serie modificarSerie(long idSerie, SerieDTO serieForm) {
        Serie serie = buscarSerie(idSerie)
        boolean houveMudanca = false
        if (serieForm.nome != null && serie.nome != serieForm.nome) {
            serie.setNome(serieForm.nome)
            houveMudanca = true
        }
        if (serieForm.anoLancamento != null && serie.anoLancamento != serieForm.anoLancamento) {
            serie.setAnoLancamento(serieForm.anoLancamento)
            houveMudanca = true
        }
        if (serieForm.anoTermino != null && serie.anoTermino != serieForm.anoTermino) {
            serie.setAnoTermino(serieForm.anoTermino)
            houveMudanca = true
        }
        if (serieForm.numeroTemporadas != null && serie.numeroTemporadas != serieForm.numeroTemporadas) {
            serie.setNumeroTemporadas(serieForm.numeroTemporadas)
            houveMudanca = true
        }
        if (serieForm.categoria != null && serie.categoria != serieForm.categoria) {
            serie.setCategoria(serieForm.categoria)
            houveMudanca = true
        }
        if (serieForm.sinopse != null && serie.sinopse != serieForm.sinopse) {
            serie.setSinopse(serieForm.sinopse)
            houveMudanca = true
        }
        if (!houveMudanca) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Não houve mudança")
        }

        return serieRepository.save(serie)
    }

    List<Comentario> adicionarComentarioSerie(long idSerie, Comentario comentario) {
        Serie serie = buscarSerie(idSerie);
        if (serie.comentarios == null || serie.comentarios.isEmpty()) {
            serie.setComentarios([comentario]);
        } else {
            List<Comentario> comentarios = serie.comentarios
            comentarios.add(comentario)
        }
        serieRepository.save(serie)
        Serie serieAlterada = serieRepository.findById(idSerie).get()
        return serieAlterada.comentarios
    }

    Comentario buscarComentarioDeSerie(long idSerie, long idComentario) {
        Serie serie = buscarSerie(idSerie)
        Comentario comentarioEncontrado = serie.comentarios.find { comentario ->
            comentario.id = idComentario
        }
        if (comentarioEncontrado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontado o comentário")
        }
        return comentarioEncontrado
    }

    Comentario modificarComentarioDeSerie(
            long idSerie,
            long idComentario,
            ComentarioDTO comentarioForm
    ) {
        boolean houveMudanca = false
        Comentario comentarioAlterado = buscarComentarioDeSerie(idSerie, idComentario)
        Serie serie = buscarSerie(idSerie)
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
        serie.comentarios.each { comentario ->
            if (comentario.id == comentarioAlterado.id) {
                comentario = comentarioAlterado
            }
        }
        serieRepository.save(serie)
        return buscarComentarioDeSerie(idSerie, idComentario)
    }

    void deletarComentario(long idSerie, long idComentario) {
        Comentario comentario = buscarComentarioDeSerie(idSerie, idComentario)
        Serie serie = buscarSerie(idSerie)
        List<Comentario> comentariosAlterados = serie.comentarios.findAll { comentarios ->
            comentarios != comentario
        }
        serie.setComentarios(comentariosAlterados)
        serieRepository.save(serie)
    }
}

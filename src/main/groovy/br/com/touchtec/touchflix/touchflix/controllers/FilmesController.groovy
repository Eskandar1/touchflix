package br.com.touchtec.touchflix.touchflix.controllers

import br.com.touchtec.touchflix.touchflix.dto.ComentarioDTO
import br.com.touchtec.touchflix.touchflix.dto.FilmeDTO
import br.com.touchtec.touchflix.touchflix.models.Comentario
import br.com.touchtec.touchflix.touchflix.models.Filme
import br.com.touchtec.touchflix.touchflix.services.FilmeService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Api(value = "API de Filmes")
@CrossOrigin(origins = "*")
@RequestMapping("/filmes")
class FilmesController {

    @Autowired
    FilmeService filmeService;

    @GetMapping
    @ApiOperation(value = "Retorna todos os filmes")
    List<Filme> buscarTodosFilmes() {
        return filmeService.buscarFilmes()
    }

    @PostMapping
    @ApiOperation(value = "Insere um filme")
    ResponseEntity<Filme> adicionarFilme(@RequestBody FilmeDTO filmeDTO) {
        FilmeDTO.validaPost(filmeDTO)
        Filme filme = new Filme()
        BeanUtils.copyProperties(filmeDTO, filme)
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeService.adicionarFilme(filme))
    }

    @GetMapping(value = "/{idFilme}")
    @ApiOperation(value = "Busca um filme pelo id")
    Filme buscarFilmeEspecifico(@PathVariable Long idFilme) {
        return filmeService.buscarFilme(idFilme)
    }

    @PatchMapping(value = "/{idFilme}")
    @ApiOperation(value = "Modifica um filme")
    Filme modificarFilme(@PathVariable Long idFilme, @RequestBody FilmeDTO filmeDTO){
        FilmeDTO.validaPatch(filmeDTO)
        return filmeService.modificarFilme(idFilme, filmeDTO)
    }

    @DeleteMapping(value = "/{idFilme}")
    @ApiOperation(value = "Deleta um filme")
    Filme deletarFilme(@PathVariable Long idFilme) {
        return filmeService.deletarFilme(idFilme)
    }

    @GetMapping(value = "/{idFilme}/comentarios")
    @ApiOperation(value = "Busca os comentários de um filme")
    List<Comentario> verComentariosDoFilme(@PathVariable Long idFilme) {
        return filmeService.buscarFilme(idFilme).comentarios
    }

    @PostMapping(value = "/{idFilme}/comentarios")
    @ApiOperation(value = "Adiciona um comentário ao filme")
    List<Comentario> adicionarComentarioFilme(
            @PathVariable Long idFilme,
            @RequestBody ComentarioDTO comentarioDTO
    ) {
        ComentarioDTO.validaComentarioPost(comentarioDTO)
        Comentario comentario = new Comentario()
        BeanUtils.copyProperties(comentarioDTO, comentario)
        return filmeService.adicionarComentarioFilme(idFilme, comentario)
    }

    @GetMapping(value = "/{idFilme}/comentarios/{idComentario}")
    @ApiOperation(value = "Visualiza um comentário de um filme")
    Comentario buscarComentarioEspecificoDoFilme(
            @PathVariable Long idFilme,
            @PathVariable Long idComentario
    ){
        return filmeService.buscarComentarioDeFilme(idFilme, idComentario)
    }

    @PatchMapping(value = "/{idFilme}/comentarios/{idComentario}")
    @ApiOperation(value = "Modifica um comentário de um filme")
    Comentario modificarComentarioDeFilme(
            @PathVariable long idFilme,
            @PathVariable long idComentario,
            @RequestBody ComentarioDTO comentarioDTO
    ){
        return filmeService.modificarComentarioDeFilme(idFilme, idComentario, comentarioDTO)
    }

    @DeleteMapping(value = "/{idFilme}/comentarios/{idComentario}")
    @ApiOperation(value = "Deleta um comentário de um filme")
    void deletarComentarioDefilme(
            @PathVariable long idFilme,
            @PathVariable long idComentario
    ){
        filmeService.deletarComentario(idFilme, idComentario)
    }
}

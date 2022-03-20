package br.com.touchtec.touchflix.touchflix.controllers

import br.com.touchtec.touchflix.touchflix.dto.ComentarioDTO
import br.com.touchtec.touchflix.touchflix.dto.SerieDTO
import br.com.touchtec.touchflix.touchflix.models.Comentario
import br.com.touchtec.touchflix.touchflix.models.Serie
import br.com.touchtec.touchflix.touchflix.services.SerieService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Api(value = "API de Séries")
@CrossOrigin(origins = "*")
@RequestMapping("/series")
class SeriesController {

    @Autowired
    SerieService serieService

    @GetMapping
    @ApiOperation(value = "Retorna todas as séries")
    List<Serie> buscarTodosFilmes() {
        return serieService.buscarSeries()
    }

    @PostMapping
    @ApiOperation(value = "Insere uma série")
    ResponseEntity<Serie> adicionarFilme(@RequestBody SerieDTO serieDTO) {
        SerieDTO.validaPost(serieDTO)
        Serie serie = new Serie()
        BeanUtils.copyProperties(serieDTO, serie)
        return ResponseEntity.status(HttpStatus.CREATED).body(serieService.adicionarSerie(serie))
    }

    @GetMapping(value = "/{idSerie}")
    @ApiOperation(value = "Busca uma série pelo id")
    Serie buscarFilmeEspecifico(@PathVariable Long idSerie) {
        return serieService.buscarSerie(idSerie)
    }

    @PatchMapping(value = "/{idSerie}")
    @ApiOperation(value = "Modifica uma série")
    Serie modificarFilme(@PathVariable Long idSerie, @RequestBody SerieDTO serieDTO){
        SerieDTO.validaPatch(serieDTO)
        return serieService.modificarSerie(idSerie, serieDTO)
    }

    @DeleteMapping(value = "/{idSerie}")
    @ApiOperation(value = "Deleta uma série")
    Serie deletarFilme(@PathVariable Long idSerie) {
        return serieService.deletarSerie(idSerie)
    }

    @GetMapping(value = "/{idSerie}/comentarios")
    @ApiOperation(value = "Busca os comentários de uma série")
    List<Comentario> verComentariosDoFilme(@PathVariable Long idSerie) {
        return serieService.buscarSerie(idSerie).comentarios
    }

    @PostMapping(value = "/{idSerie}/comentarios")
    @ApiOperation(value = "Adiciona um comentário à série")
    List<Comentario> adicionarComentarioFilme(
            @PathVariable Long idSerie,
            @RequestBody ComentarioDTO comentarioDTO
    ) {
        ComentarioDTO.validaComentarioPost(comentarioDTO)
        Comentario comentario = new Comentario()
        BeanUtils.copyProperties(comentarioDTO, comentario)
        return serieService.adicionarComentarioSerie(idSerie, comentario)
    }

    @GetMapping(value = "/{idSerie}/comentarios/{idComentario}")
    @ApiOperation(value = "Visualiza um comentário de uma série")
    Comentario buscarComentarioEspecificoDoFilme(
            @PathVariable Long idSerie,
            @PathVariable Long idComentario
    ){
        return serieService.buscarComentarioDeSerie(idSerie, idComentario)
    }

    @PatchMapping(value = "/{idSerie}/comentarios/{idComentario}")
    @ApiOperation(value = "Modifica um comentário de uma série")
    Comentario modificarComentarioDeFilme(
            @PathVariable long idSerie,
            @PathVariable long idComentario,
            @RequestBody ComentarioDTO comentarioDTO
    ){
        return serieService.modificarComentarioDeSerie(idSerie, idComentario, comentarioDTO)
    }

    @DeleteMapping(value = "/{idSerie}/comentarios/{idComentario}")
    @ApiOperation(value = "Deleta um comentário de uma série")
    void deletarComentarioDefilme(
            @PathVariable long idSerie,
            @PathVariable long idComentario
    ){
        serieService.deletarComentario(idSerie, idComentario)
    }
}

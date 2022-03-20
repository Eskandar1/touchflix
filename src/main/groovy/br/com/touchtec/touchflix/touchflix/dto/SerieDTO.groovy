package br.com.touchtec.touchflix.touchflix.dto

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class SerieDTO {
    String nome
    String categoria
    String anoLancamento
    String anoTermino
    Integer numeroTemporadas
    String sinopse

    public static void validaPost(SerieDTO serieForm) {
        if (serieForm == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Body não pode ser nulo")
        }
        if (serieForm.nome == null || serieForm.nome.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nome é um campo obrigatório, não pode ser nulo nem vazio"
            )
        }
        if (serieForm.categoria == null || serieForm.categoria.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Categoria é um campo obrigatório, não pode ser nulo nem vazio"
            )
        }
        if (serieForm.anoLancamento != null) {
            if (serieForm.anoLancamento.matches("\\d{4}")) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "AnoLancamento fora do formato aaaa"
                )
            }
        }
        if (serieForm.anoTermino != null) {
            if (serieForm.anoLancamento.matches("\\d{4}")) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "AnoTermino fora do formato aaaa"
                )
            }
        }
        if (serieForm.numeroTemporadas != null) {
            if (serieForm.numeroTemporadas < 0) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "numeorTemporadas menor que 0"
                )
            }
        }
        if (serieForm.sinopse != null && serieForm.sinopse.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Sinopse não pode ser vazia"
            )
        }
    }

    public static void validaPatch(SerieDTO serieForm) {
        if (serieForm == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Body não pode ser nulo")
        }
        if (serieForm.nome != null && serieForm.nome.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nome é um campo obrigatório, não pode ser nulo nem vazio"
            )
        }
        if (serieForm.categoria == null || serieForm.categoria.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Categoria é um campo obrigatório, não pode ser nulo nem vazio"
            )
        }
        if (serieForm.anoLancamento != null) {
            if (serieForm.anoLancamento.matches("\\d{4}")) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "AnoLancamento fora do formato aaaa"
                )
            }
        }
        if (serieForm.anoTermino != null) {
            if (serieForm.anoLancamento.matches("\\d{4}")) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "AnoTermino fora do formato aaaa"
                )
            }
        }
        if (serieForm.numeroTemporadas != null) {
            if (serieForm.numeroTemporadas < 0) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "numeorTemporadas menor que 0"
                )
            }
        }
        if (serieForm.sinopse != null && serieForm.sinopse.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Sinopse não pode ser vazia"
            )
        }
    }
}

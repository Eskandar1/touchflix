package br.com.touchtec.touchflix.touchflix.dto


import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class FilmeDTO {
    String nome
    String categoria
    String anoLancamento
    String sinopse

    public static void validaPost(FilmeDTO filmeForm) {
        if (filmeForm == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Body não pode ser nulo")
        }
        if (filmeForm.nome == null || filmeForm.nome.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nome é um campo obrigatório, não pode ser nulo nem vazio"
            )
        }
        if (filmeForm.categoria == null || filmeForm.categoria.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Categoria é um campo obrigatório, não pode ser nulo nem vazio"
            )
        }
        if (filmeForm.anoLancamento != null) {
            if (filmeForm.anoLancamento.matches("\\d{4}")) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "AnoLancamento fora do formato aaaa"
                )
            }
        }
        if (filmeForm.sinopse != null && filmeForm.sinopse.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Sinopse não pode ser vazia"
            )
        }
    }

    public static void validaPatch(FilmeDTO filmeForm) {
        if (filmeForm == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Body não pode ser nulo")
        }
        if (filmeForm.nome != null && filmeForm.nome.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nome é um campo obrigatório, não pode ser nulo nem vazio"
            )
        }
        if (filmeForm.categoria == null || filmeForm.categoria.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Categoria é um campo obrigatório, não pode ser nulo nem vazio"
            )
        }
        if (filmeForm.anoLancamento != null) {
            if (filmeForm.anoLancamento.matches("\\d{4}")) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "AnoLancamento fora do formato aaaa"
                )
            }
        }
        if (filmeForm.sinopse != null && filmeForm.sinopse.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Sinopse não pode ser vazia"
            )
        }
    }
}

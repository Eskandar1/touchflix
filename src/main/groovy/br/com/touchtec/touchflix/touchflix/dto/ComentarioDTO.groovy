package br.com.touchtec.touchflix.touchflix.dto

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class ComentarioDTO {
    Integer nota
    String texto

    public static void validaComentarioPost(ComentarioDTO comentarioForm){
        if(comentarioForm == null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Request Body não pode ser nulo");
        }
        if (comentarioForm.nota == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nota é um campo obrigatório");
        }else if(comentarioForm.nota < 0 || comentarioForm.nota > 10){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O valor mínimo de nota é 0 e máximo é 10");
        }
        if(comentarioForm.texto != null && comentarioForm.texto.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Texto não pode ser vazio");
        }
    }
    static void validaComentarioPatch(ComentarioDTO comentarioForm){
        if(comentarioForm == null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Request Body não pode ser nulo");
        }
        if(comentarioForm.nota != null && (comentarioForm.nota < 0 || comentarioForm.nota > 10)){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O valor mínimo de nota é 0 e máximo é 10");
        }
        if(comentarioForm.texto != null && comentarioForm.texto.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Texto não pode ser vazio");
        }
    }
}

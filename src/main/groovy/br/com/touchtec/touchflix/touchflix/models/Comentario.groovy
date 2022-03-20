package br.com.touchtec.touchflix.touchflix.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name= "comentarios")
class Comentario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    @Column(nullable = false, length = 2)
    Integer nota

    @Column(nullable = true)
    String texto

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    int getNota() {
        return nota
    }

    void setNota(Integer nota) {
        this.nota = nota
    }

    String getTexto() {
        return texto
    }

    void setTexto(String texto) {
        this.texto = texto
    }
}

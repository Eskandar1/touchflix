package br.com.touchtec.touchflix.touchflix.models

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Serie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id

    @Column(nullable = false)
    private String nome

    @Column(nullable = false)
    private String categoria

    @Column(nullable = true, length = 4)
    private String anoLancamento

    @Column(nullable = true, length = 4)
    private String anoTermino

    @Column(nullable = true, length = 2)
    private Integer numeroTemporadas

    @Column(nullable = true)
    private String sinopse

    @OneToMany(targetEntity = Comentario, cascade = CascadeType.ALL)
    private List<Comentario> comentarios

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getNome() {
        return nome
    }

    void setNome(String nome) {
        this.nome = nome
    }

    String getCategoria() {
        return categoria
    }

    void setCategoria(String categoria) {
        this.categoria = categoria
    }

    String getAnoLancamento() {
        return anoLancamento
    }

    void setAnoLancamento(String anoLancamento) {
        this.anoLancamento = anoLancamento
    }

    String getAnoTermino() {
        return anoTermino
    }

    void setAnoTermino(String anoTermino) {
        this.anoTermino = anoTermino
    }

    Integer getNumeroTemporadas() {
        return numeroTemporadas
    }

    void setNumeroTemporadas(Integer numeroTemporadas) {
        this.numeroTemporadas = numeroTemporadas
    }

    String getSinopse() {
        return sinopse
    }

    void setSinopse(String sinopse) {
        this.sinopse = sinopse
    }

    List<Comentario> getComentarios() {
        return comentarios
    }

    void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios
    }
}

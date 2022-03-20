package br.com.touchtec.touchflix.touchflix.json

import br.com.touchtec.touchflix.touchflix.models.Comentario
import br.com.touchtec.touchflix.touchflix.models.Serie

import java.text.DecimalFormat

class SerieJson {
    String nome
    String categoria
    String anoLancamento
    String anoTermino
    Integer numeroTemporadas
    String sinopse
    String nota
    List<Comentario> comentarios

    public SerieJson(Serie serie) {
        this.nome = serie.nome
        this.categoria = serie.categoria
        this.anoLancamento = serie.anoLancamento
        this.anoTermino = serie.anoTermino
        this.numeroTemporadas = serie.numeroTemporadas
        this.sinopse = serie.sinopse
        this.comentarios = serie.comentarios
        if (serie.comentarios != null && !serie.comentarios.isEmpty()) {
            List<Integer> notas = serie.comentarios.collect { it.nota }
            float media = notas.sum() / notas.size()
            DecimalFormat formatter = new DecimalFormat("0.0")
            this.nota = formatter.format(media).toString()
        } else {
            this.nota = null
        }
    }
}

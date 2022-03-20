package br.com.touchtec.touchflix.touchflix.json

import br.com.touchtec.touchflix.touchflix.models.Comentario
import br.com.touchtec.touchflix.touchflix.models.Filme
import br.com.touchtec.touchflix.touchflix.models.Serie

import java.text.DecimalFormat

class FilmeJson {
    String nome
    String categoria
    String anoLancamento
    String sinopse
    String nota
    List<Comentario> comentarios

    public FilmeJson(Filme filme) {
        this.nome = filme.nome
        this.categoria = filme.categoria
        this.anoLancamento = filme.anoLancamento
        this.sinopse = filme.sinopse
        this.comentarios = filme.comentarios
        if (filme.comentarios != null && !filme.comentarios.isEmpty()) {
            List<Integer> notas = filme.comentarios.collect { it.nota }
            float media = notas.sum() / notas.size()
            DecimalFormat formatter = new DecimalFormat("0.0")
            this.nota = formatter.format(media).toString()
        } else {
            this.nota = null
        }

    }
}

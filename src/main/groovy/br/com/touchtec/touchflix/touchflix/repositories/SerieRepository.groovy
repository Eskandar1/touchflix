package br.com.touchtec.touchflix.touchflix.repositories

import br.com.touchtec.touchflix.touchflix.models.Serie
import org.springframework.data.jpa.repository.JpaRepository

interface SerieRepository extends JpaRepository<Serie, Long> {

}
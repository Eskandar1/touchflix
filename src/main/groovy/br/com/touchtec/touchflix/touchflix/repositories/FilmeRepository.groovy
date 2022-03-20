package br.com.touchtec.touchflix.touchflix.repositories

import br.com.touchtec.touchflix.touchflix.models.Filme
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FilmeRepository extends JpaRepository<Filme, Long> {
}
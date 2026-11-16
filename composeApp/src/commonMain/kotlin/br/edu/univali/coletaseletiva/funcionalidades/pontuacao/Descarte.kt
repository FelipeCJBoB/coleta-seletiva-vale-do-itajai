package br.edu.univali.coletaseletiva.funcionalidades.pontuacao

import br.edu.univali.coletaseletiva.funcionalidades.pontos.Material
import kotlinx.datetime.LocalDate

/** Um descarte registrado pelo morador (autodeclarado, RF09). `id` 0 = ainda não salvo. */
data class Descarte(
    val id: Long = 0L,
    val material: Material,
    val pontos: Int,
    val data: LocalDate,
)

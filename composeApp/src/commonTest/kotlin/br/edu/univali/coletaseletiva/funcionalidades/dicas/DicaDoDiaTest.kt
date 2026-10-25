package br.edu.univali.coletaseletiva.funcionalidades.dicas

import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class DicaDoDiaTest {

    private val dicasExemplo = (1..8).map { i ->
        Dica(id = i.toLong(), titulo = "Dica $i", texto = "Texto da dica $i")
    }

    @Test
    fun escolhe_a_dica_pela_posicao_do_dia_do_ano() {
        val dia1 = LocalDate(2025, 1, 1) // dayOfYear = 1
        val dica = dicaDoDia(dicasExemplo, dia1)

        // 1 mod 8 = 1 -> Dica de posição 1 (Dica 2)
        assertEquals(dicasExemplo[1 % 8], dica)
    }

    @Test
    fun dias_seguidos_dao_dicas_diferentes() {
        val dia10 = LocalDate(2025, 1, 10)
        val dia11 = LocalDate(2025, 1, 11)

        val dica10 = dicaDoDia(dicasExemplo, dia10)
        val dica11 = dicaDoDia(dicasExemplo, dia11)

        assertNotEquals(dica10, dica11)
    }
}

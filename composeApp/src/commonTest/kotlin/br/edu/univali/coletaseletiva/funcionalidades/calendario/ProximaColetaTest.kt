
package br.edu.univali.coletaseletiva.funcionalidades.calendario

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ProximaColetaTest {

    @Test
    fun retorna_hoje_quando_hoje_e_dia_de_coleta() {
        val resultado = proximaColeta(
            hoje = LocalDate(2026, 10, 26),
            diasDaSemana = setOf(DayOfWeek.MONDAY)
        )

        assertEquals(LocalDate(2026, 10, 26), resultado)
    }

    @Test
    fun procura_o_proximo_dia_programado() {
        val resultado = proximaColeta(
            hoje = LocalDate(2026, 10, 24),
            diasDaSemana = setOf(DayOfWeek.MONDAY)
        )

        assertEquals(LocalDate(2026, 10, 26), resultado)
    }

    @Test
    fun feriado_em_dia_de_coleta_passa_para_o_proximo_dia_util() {
        val resultado = proximaColeta(
            hoje = LocalDate(2026, 11, 20),
            diasDaSemana = setOf(DayOfWeek.FRIDAY)
        )

        assertEquals(LocalDate(2026, 11, 23), resultado)
    }

    @Test
    fun exige_pelo_menos_um_dia_de_coleta() {
        assertFailsWith<IllegalArgumentException> {
            proximaColeta(
                hoje = LocalDate(2026, 10, 24),
                diasDaSemana = emptySet()
            )
        }
    }
}

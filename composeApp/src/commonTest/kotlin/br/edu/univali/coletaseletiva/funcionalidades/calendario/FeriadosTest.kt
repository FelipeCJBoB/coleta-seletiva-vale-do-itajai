
package br.edu.univali.coletaseletiva.funcionalidades.calendario

import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FeriadosTest {

    @Test
    fun datas_fixas_sao_feriado() {
        val feriados = listOf(
            LocalDate(2026, 1, 1),
            LocalDate(2026, 4, 21),
            LocalDate(2026, 5, 1),
            LocalDate(2026, 9, 7),
            LocalDate(2026, 10, 12),
            LocalDate(2026, 11, 2),
            LocalDate(2026, 11, 15),
            LocalDate(2026, 11, 20),
            LocalDate(2026, 12, 25)
        )

        feriados.forEach { data ->
            assertTrue(ehFeriadoNacional(data), "$data deveria ser feriado")
        }
    }

    @Test
    fun sexta_feira_santa_de_2026_e_2027_e_feriado() {
        assertTrue(ehFeriadoNacional(LocalDate(2026, 4, 3)))
        assertTrue(ehFeriadoNacional(LocalDate(2027, 3, 26)))
    }

    @Test
    fun dia_comum_nao_e_feriado() {
        assertFalse(ehFeriadoNacional(LocalDate(2026, 10, 6)))
    }
}

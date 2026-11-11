
package br.edu.univali.coletaseletiva.funcionalidades.calendario

import kotlinx.datetime.LocalDate

private val feriadosFixos = setOf(
    1 to 1,
    4 to 21,
    5 to 1,
    9 to 7,
    10 to 12,
    11 to 2,
    11 to 15,
    11 to 20,
    12 to 25
)

private val sextasFeirasSantas = setOf(
    LocalDate(2026, 4, 3),
    LocalDate(2027, 3, 26)
)

/**
 * Identifica os feriados nacionais previstos para 2026 e 2027.
 */
fun ehFeriadoNacional(data: LocalDate): Boolean {
    val dataFixa = data.month.ordinal + 1 to data.day

    return dataFixa in feriadosFixos ||
            data in sextasFeirasSantas
}

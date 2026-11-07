
package br.edu.univali.coletaseletiva.funcionalidades.calendario

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.plus

/**
 * Calcula a próxima data de coleta programada.
 *
 * Se a data programada for feriado nacional, a coleta passa
 * para o próximo dia útil, excluindo sábados, domingos e feriados.
 */
fun proximaColeta(
    hoje: LocalDate,
    diasDaSemana: Set<DayOfWeek>
): LocalDate {
    require(diasDaSemana.isNotEmpty()) {
        "Informe pelo menos um dia da semana para a coleta."
    }

    var data = hoje

    // Procura a próxima data programada, incluindo hoje.
    while (data.dayOfWeek !in diasDaSemana) {
        data = data.plus(DatePeriod(days = 1))
    }

    // Se a data programada for feriado, procura o próximo dia útil.
    if (ehFeriadoNacional(data)) {
        data = data.plus(DatePeriod(days = 1))

        while (
            data.dayOfWeek == DayOfWeek.SATURDAY ||
            data.dayOfWeek == DayOfWeek.SUNDAY ||
            ehFeriadoNacional(data)
        ) {
            data = data.plus(DatePeriod(days = 1))
        }
    }

    return data
}

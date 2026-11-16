package br.edu.univali.coletaseletiva.funcionalidades.lembrete

import br.edu.univali.coletaseletiva.funcionalidades.calendario.proximaColeta
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlin.test.Test
import kotlin.test.assertEquals

// PB12 · critérios 1 e 2: o lembrete é na véspera da próxima coleta, no horário escolhido.
class DataDoLembreteTest {

    @Test
    fun vespera_no_horario_escolhido() {
        // Coleta na segunda 26/10/2026, horário 19:00: o aviso é no domingo 25/10 às 19:00.
        val quando = dataDoLembrete(LocalDate(2026, 10, 26), LocalTime(19, 0))

        assertEquals(LocalDateTime(2026, 10, 25, 19, 0), quando)
    }

    @Test
    fun feriado_ja_ajustado_pela_proximaColeta() {
        // Sexta 04/09/2026: a segunda 07/09 é feriado, então a coleta vai para terça 08/09 (PB08).
        val coleta = proximaColeta(LocalDate(2026, 9, 4), setOf(DayOfWeek.MONDAY))
        assertEquals(LocalDate(2026, 9, 8), coleta)

        // A véspera é a da data já ajustada: segunda 07/09.
        assertEquals(LocalDateTime(2026, 9, 7, 19, 0), dataDoLembrete(coleta, LocalTime(19, 0)))
    }
}

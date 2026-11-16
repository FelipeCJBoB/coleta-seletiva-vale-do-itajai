package br.edu.univali.coletaseletiva.funcionalidades.lembrete

import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.atTime
import kotlinx.datetime.minus

/**
 * RF02: o lembrete é na VÉSPERA da coleta, no horário que o morador escolheu.
 * `proximaColeta` já deve vir ajustada por feriado (PB08): a véspera é a da data ajustada.
 */
fun dataDoLembrete(proximaColeta: LocalDate, horario: LocalTime): LocalDateTime =
    proximaColeta.minus(DatePeriod(days = 1)).atTime(horario)

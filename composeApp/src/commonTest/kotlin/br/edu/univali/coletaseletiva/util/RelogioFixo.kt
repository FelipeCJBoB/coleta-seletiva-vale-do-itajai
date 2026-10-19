package br.edu.univali.coletaseletiva.util

import kotlinx.datetime.LocalDate

/** Relógio dos testes: sempre devolve a data que você escolher. Ex.: RelogioFixo(LocalDate(2026, 10, 23)). */
class RelogioFixo(var data: LocalDate) : Relogio {
    override fun hoje(): LocalDate = data
}

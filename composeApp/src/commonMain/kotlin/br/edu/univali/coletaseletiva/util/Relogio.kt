package br.edu.univali.coletaseletiva.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlin.time.Clock

/** Quem diz "que dia é hoje". Nos testes usamos o RelogioFixo, assim o resultado nunca depende do dia em que o teste roda. */
interface Relogio {
    fun hoje(): LocalDate
}

object RelogioDoSistema : Relogio {
    override fun hoje(): LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
}

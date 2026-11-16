package br.edu.univali.coletaseletiva.funcionalidades.lembrete

import kotlinx.datetime.LocalDateTime

/**
 * Contrato de quem agenda a notificação de verdade. O lembrete é local, sem servidor (RF02).
 * Cada plataforma implementa o seu (AgendadorAndroid; no iOS fica como evolução).
 */
interface AgendadorDeLembrete {
    /** Pede uma notificação para essa data e hora (horário local do aparelho), trocando a anterior. */
    fun agendar(quando: LocalDateTime)

    /** Cancela a notificação agendada, se houver. */
    fun cancelar()
}

/** Agendador que não faz nada: usado onde ainda não há notificação (iOS) e como padrão do AppContainer. */
object AgendadorNulo : AgendadorDeLembrete {
    override fun agendar(quando: LocalDateTime) = Unit
    override fun cancelar() = Unit
}

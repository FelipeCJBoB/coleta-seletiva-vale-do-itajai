package br.edu.univali.coletaseletiva.navegacao

/** Todas as telas do app. Infraestrutura (Integrante 1). */
sealed interface Rota {
    data object Inicio : Rota
    data object EscolherLocal : Rota
    data object Calendario : Rota
    data object Pontos : Rota
    data class PontoDetalhe(val pontoId: Long) : Rota
    data object Guia : Rota
    data class GuiaDetalhe(val itemId: Long) : Rota
    data object MeusPontos : Rota
    data object RegistrarDescarte : Rota
    data object Dicas : Rota
    data object Lembrete : Rota
}

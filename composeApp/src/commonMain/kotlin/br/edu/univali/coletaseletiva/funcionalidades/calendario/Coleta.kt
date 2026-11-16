package br.edu.univali.coletaseletiva.funcionalidades.calendario

import kotlinx.datetime.DayOfWeek

/** Os dois tipos de coleta de um setor (RN01 e RN02). */
enum class TipoColeta(val nomeExibicao: String) {
    SELETIVA("Coleta seletiva"),
    COMUM("Coleta comum"),
}

/** Dias da semana em que o caminhão passa no setor, para um tipo de coleta. Não há horário fixo de passagem (RN01). */
data class ColetaDoSetor(
    val tipo: TipoColeta,
    val diasDaSemana: Set<DayOfWeek>,
)

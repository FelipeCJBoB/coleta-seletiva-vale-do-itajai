package br.edu.univali.coletaseletiva.funcionalidades.dicas

import kotlinx.datetime.LocalDate

/**
 * Calcula qual dica deve ser exibida no dia informado.
 * Utiliza o dia do ano mod a quantidade de dicas disponíveis.
 */
fun dicaDoDia(dicas: List<Dica>, hoje: LocalDate): Dica {
    require(dicas.isNotEmpty()) { "A lista de dicas não pode ser vazia" }
    val indice = hoje.dayOfYear % dicas.size
    return dicas[indice]
}

package br.edu.univali.coletaseletiva.funcionalidades.dicas

/**
 * Modelo de uma dica de reciclagem ou sustentabilidade.
 */
data class Dica(
    val id: Long,
    val titulo: String,
    val texto: String,
)

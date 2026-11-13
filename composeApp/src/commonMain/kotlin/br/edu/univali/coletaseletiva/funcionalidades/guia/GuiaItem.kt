package br.edu.univali.coletaseletiva.funcionalidades.guia

/** Para onde vai cada item do guia (PB11). RN04: pilhas, baterias e óleo vão para PONTO_DE_DESCARTE. */
enum class Destino(val nomeExibicao: String) {
    COLETA_SELETIVA("Coleta seletiva"),
    COLETA_COMUM("Coleta comum"),
    PONTO_DE_DESCARTE("Ponto de descarte"),
}

/** Um item do guia de separação: conteúdo estático, funciona sem internet (RNF03). */
data class GuiaItem(
    val id: Long,
    val nome: String,
    val destino: Destino,
    val orientacao: String,
    val sinonimos: List<String>,
)

package br.edu.univali.coletaseletiva.funcionalidades.pontos

/**
 * Tipos de materiais aceitos nos pontos de descarte.
 */
enum class Material(val nomeExibicao: String) {
    PAPEL("Papel"),
    PLASTICO("Plástico"),
    METAL("Metal"),
    VIDRO("Vidro"),
    PILHAS_BATERIAS("Pilhas e baterias"),
    OLEO("Óleo de cozinha"),
    ELETRONICOS("Eletrônicos"),
    ROUPAS("Roupas e tecidos")
}

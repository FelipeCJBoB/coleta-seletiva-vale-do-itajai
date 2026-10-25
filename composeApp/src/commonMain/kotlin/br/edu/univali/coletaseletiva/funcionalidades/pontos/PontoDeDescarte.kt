package br.edu.univali.coletaseletiva.funcionalidades.pontos

/**
 * Material aceito em um ponto de descarte com limite opcional de quantidade/peso.
 */
data class MaterialAceito(
    val material: Material,
    val limite: String? = null,
)

/**
 * Ponto de descarte no município.
 */
data class PontoDeDescarte(
    val id: Long,
    val municipioId: Long,
    val nome: String,
    val endereco: String,
    val horario: String,
    val latitude: Double,
    val longitude: Double,
    val materiais: List<MaterialAceito>,
)

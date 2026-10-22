package br.edu.univali.coletaseletiva.funcionalidades.local

/**
 * Dados de DEMONSTRAÇÃO (fictícios) de municípios e setores do Vale do Itajaí.
 * Os ids são fixos: os cartões de calendário (PB08) e pontos de descarte (PB09) usam estes mesmos números.
 */
object DadosLocalDemo {
    const val ID_ITAJAI = 1L
    const val ID_CAMBORIU = 2L

    val municipios = listOf(
        Municipio(ID_ITAJAI, "Itajaí", "SC"),
        Municipio(ID_CAMBORIU, "Camboriú", "SC"),
    )

    val setores = listOf(
        Setor(1, ID_ITAJAI, "Centro"),
        Setor(2, ID_ITAJAI, "São Vicente"),
        Setor(3, ID_ITAJAI, "Fazenda"),
        Setor(4, ID_CAMBORIU, "Centro"),
        Setor(5, ID_CAMBORIU, "Monte Alegre"),
        Setor(6, ID_CAMBORIU, "Tabuleiro"),
    )
}

package br.edu.univali.coletaseletiva.funcionalidades.pontos

import kotlin.test.Test
import kotlin.test.assertEquals

class MaterialTest {

    @Test
    fun todos_os_oito_materiais_existem_com_nome_para_exibir() {
        assertEquals("Papel", Material.PAPEL.nomeExibicao)
        assertEquals("Plástico", Material.PLASTICO.nomeExibicao)
        assertEquals("Metal", Material.METAL.nomeExibicao)
        assertEquals("Vidro", Material.VIDRO.nomeExibicao)
        assertEquals("Pilhas e baterias", Material.PILHAS_BATERIAS.nomeExibicao)
        assertEquals("Óleo de cozinha", Material.OLEO.nomeExibicao)
        assertEquals("Eletrônicos", Material.ELETRONICOS.nomeExibicao)
        assertEquals("Roupas e tecidos", Material.ROUPAS.nomeExibicao)
    }
}

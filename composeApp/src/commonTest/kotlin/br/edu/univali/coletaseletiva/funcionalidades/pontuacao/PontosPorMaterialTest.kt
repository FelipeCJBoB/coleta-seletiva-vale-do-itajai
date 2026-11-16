package br.edu.univali.coletaseletiva.funcionalidades.pontuacao

import br.edu.univali.coletaseletiva.funcionalidades.pontos.Material
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

// PB16 · critério 1: pontos por material (RN08).
class PontosPorMaterialTest {

    @Test
    fun cinco_pontos_para_papel_plastico_metal_vidro() {
        for (material in listOf(Material.PAPEL, Material.PLASTICO, Material.METAL, Material.VIDRO)) {
            assertEquals(5, pontosPorMaterial(material), "$material deve valer 5 pontos.")
        }
    }

    @Test
    fun dez_pontos_para_pilhas_baterias_e_oleo() {
        for (material in listOf(Material.PILHAS_BATERIAS, Material.OLEO)) {
            assertEquals(10, pontosPorMaterial(material), "$material deve valer 10 pontos.")
        }
    }

    @Test
    fun quinze_pontos_para_eletronicos() {
        assertEquals(15, pontosPorMaterial(Material.ELETRONICOS))
    }

    @Test
    fun roupas_nao_pontuam() {
        // Decisão D-07: a regra RN08 não define valor para roupas.
        assertNull(pontosPorMaterial(Material.ROUPAS))
    }
}

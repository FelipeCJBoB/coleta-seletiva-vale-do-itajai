package br.edu.univali.coletaseletiva.funcionalidades.guia

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

// PB11 · critérios 2 a 5 (RF08): busca no guia de separação.
class BuscaNoGuiaTest {

    private val caixaDeLeite = GuiaItem(
        id = 1,
        nome = "Caixa de leite (longa vida)",
        destino = Destino.COLETA_SELETIVA,
        orientacao = "Enxágue e amasse antes de colocar na coleta seletiva.",
        sinonimos = listOf("tetra pak"),
    )
    private val garrafaPet = GuiaItem(
        id = 2,
        nome = "Garrafa PET",
        destino = Destino.COLETA_SELETIVA,
        orientacao = "Esvazie, tampe e amasse.",
        sinonimos = listOf("refrigerante", "garrafa plástica"),
    )
    private val oleo = GuiaItem(
        id = 3,
        nome = "Óleo de cozinha usado",
        destino = Destino.PONTO_DE_DESCARTE,
        orientacao = "Guarde em garrafa fechada e leve a um ponto de descarte.",
        sinonimos = listOf("óleo de fritura"),
    )
    private val papel = GuiaItem(
        id = 4,
        nome = "Papel",
        destino = Destino.COLETA_SELETIVA,
        orientacao = "Coloque seco e sem gordura.",
        sinonimos = emptyList(),
    )

    // Fora de ordem de propósito: a busca é quem ordena.
    private val itens = listOf(papel, oleo, garrafaPet, caixaDeLeite)

    @Test
    fun ignora_acento_e_caixa() {
        assertEquals(listOf(caixaDeLeite), buscarNoGuia(itens, "CAIXA LEITE"))
        assertEquals(listOf(caixaDeLeite), buscarNoGuia(itens, "caixa de leite"))
        assertEquals(listOf(oleo), buscarNoGuia(itens, "OLEO cozinha"))
    }

    @Test
    fun acha_por_sinonimo() {
        assertEquals(listOf(garrafaPet), buscarNoGuia(itens, "refrigerante"))
    }

    @Test
    fun termo_vazio_devolve_todos_em_ordem_alfabetica() {
        // "Óleo" fica entre "Garrafa" e "Papel": a ordem ignora o acento.
        val esperado = listOf(caixaDeLeite, garrafaPet, oleo, papel)
        assertEquals(esperado, buscarNoGuia(itens, ""))
        assertEquals(esperado, buscarNoGuia(itens, "   "))
    }

    @Test
    fun sem_resultado_devolve_lista_vazia() {
        assertTrue(buscarNoGuia(itens, "pneu de trator").isEmpty())
    }
}

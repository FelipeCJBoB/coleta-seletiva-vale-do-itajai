package br.edu.univali.coletaseletiva.funcionalidades.guia

import br.edu.univali.coletaseletiva.util.normalizarTexto
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

// PB11 · critério 6: protege o conteúdo do guia (DadosGuiaDemo).
class DadosGuiaTest {

    private val itens = DadosGuiaDemo.itens

    @Test
    fun tem_pelo_menos_vinte_itens() {
        assertTrue(itens.size >= 20, "O guia tem ${itens.size} itens; o mínimo é 20.")
        itens.forEach { item ->
            assertTrue(item.nome.isNotBlank(), "Item ${item.id} sem nome.")
            assertTrue(item.orientacao.isNotBlank(), "Item ${item.id} sem orientação.")
        }
    }

    @Test
    fun ids_sao_unicos() {
        val repetidos = itens.groupBy { it.id }.filterValues { it.size > 1 }.keys
        assertEquals(emptySet(), repetidos, "Ids repetidos no guia.")
    }

    @Test
    fun pilhas_baterias_e_oleo_vao_para_ponto_de_descarte() {
        // RN04: nunca vão para a coleta porta a porta.
        for (palavra in listOf("pilha", "bateria", "oleo")) {
            val doTipo = itens.filter { palavra in normalizarTexto(it.nome) }
            assertTrue(doTipo.isNotEmpty(), "O guia precisa ter um item de '$palavra'.")
            doTipo.forEach { item ->
                assertEquals(Destino.PONTO_DE_DESCARTE, item.destino, "'${item.nome}' deve ir para ponto de descarte.")
            }
        }
    }
}

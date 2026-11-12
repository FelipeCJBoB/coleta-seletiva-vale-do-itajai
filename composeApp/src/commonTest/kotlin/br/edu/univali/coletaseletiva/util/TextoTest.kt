package br.edu.univali.coletaseletiva.util

import kotlin.test.Test
import kotlin.test.assertEquals

// PB11 · critério 1: a busca do guia compara textos normalizados
// (sem espaços nas pontas, minúsculas e sem acento).
class TextoTest {

    @Test
    fun tira_acento_caixa_alta_e_espacos_nas_pontas() {
        assertEquals("caixa de leite", normalizarTexto(" CAIXÁ de Leite "))
    }

    @Test
    fun texto_ja_normalizado_nao_muda() {
        assertEquals("garrafa pet", normalizarTexto("garrafa pet"))
    }
}

package br.edu.univali.coletaseletiva.funcionalidades.guia

import br.edu.univali.coletaseletiva.util.normalizarTexto

/**
 * RF08: busca no guia de separação.
 * Termo vazio devolve todos; senão, fica o item em que TODAS as palavras do termo
 * aparecem no nome ou nos sinônimos. Sem diferença de maiúsculas e acentos.
 * O resultado sai em ordem alfabética (também sem considerar acentos).
 */
fun buscarNoGuia(itens: List<GuiaItem>, termo: String): List<GuiaItem> {
    val palavras = normalizarTexto(termo).split(" ").filter { it.isNotEmpty() }
    val encontrados = itens.filter { item ->
        val textoDoItem = normalizarTexto((listOf(item.nome) + item.sinonimos).joinToString(" "))
        palavras.all { palavra -> palavra in textoDoItem }
    }
    return encontrados.sortedBy { normalizarTexto(it.nome) }
}

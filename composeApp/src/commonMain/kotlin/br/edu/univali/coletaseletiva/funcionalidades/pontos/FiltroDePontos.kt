package br.edu.univali.coletaseletiva.funcionalidades.pontos

/**
 * Filtra e ordena a lista de pontos de descarte.
 * Se material é nulo, devolve todos; senão, só os que aceitam aquele material.
 * Sempre ordenados alfabeticamente pelo nome do ponto.
 */
fun filtrarPontos(
    pontos: List<PontoDeDescarte>,
    material: Material?,
): List<PontoDeDescarte> {
    val filtrados = if (material == null) {
        pontos
    } else {
        pontos.filter { ponto ->
            ponto.materiais.any { it.material == material }
        }
    }
    return filtrados.sortedBy { it.nome }
}

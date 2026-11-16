package br.edu.univali.coletaseletiva.funcionalidades.pontuacao

import br.edu.univali.coletaseletiva.funcionalidades.pontos.Material

/**
 * RN08: papel, plástico, metal e vidro = 5; pilhas, baterias e óleo = 10; eletrônicos = 15.
 * Roupas não pontuam (a regra não define valor, decisão D-07): devolve nulo.
 */
fun pontosPorMaterial(material: Material): Int? = when (material) {
    Material.PAPEL, Material.PLASTICO, Material.METAL, Material.VIDRO -> 5
    Material.PILHAS_BATERIAS, Material.OLEO -> 10
    Material.ELETRONICOS -> 15
    Material.ROUPAS -> null
}

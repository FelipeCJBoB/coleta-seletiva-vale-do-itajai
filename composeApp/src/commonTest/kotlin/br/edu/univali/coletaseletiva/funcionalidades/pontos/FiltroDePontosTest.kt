package br.edu.univali.coletaseletiva.funcionalidades.pontos

import kotlin.test.Test
import kotlin.test.assertEquals

class FiltroDePontosTest {

    private val pontoA = PontoDeDescarte(
        id = 1L,
        municipioId = 1L,
        nome = "Ponto Alpha",
        endereco = "Rua A",
        horario = "8h às 18h",
        latitude = -26.9,
        longitude = -48.6,
        materiais = listOf(MaterialAceito(Material.PILHAS_BATERIAS)),
    )

    private val pontoB = PontoDeDescarte(
        id = 2L,
        municipioId = 1L,
        nome = "Ponto Beta",
        endereco = "Rua B",
        horario = "8h às 18h",
        latitude = -26.9,
        longitude = -48.6,
        materiais = listOf(MaterialAceito(Material.OLEO)),
    )

    private val pontoC = PontoDeDescarte(
        id = 3L,
        municipioId = 1L,
        nome = "Ponto Charlie",
        endereco = "Rua C",
        horario = "8h às 18h",
        latitude = -26.9,
        longitude = -48.6,
        materiais = listOf(
            MaterialAceito(Material.PILHAS_BATERIAS),
            MaterialAceito(Material.ROUPAS),
        ),
    )

    private val pontosExemplo = listOf(pontoB, pontoC, pontoA)

    @Test
    fun filtra_por_pilhas_e_ordena_por_nome() {
        val resultado = filtrarPontos(pontosExemplo, Material.PILHAS_BATERIAS)
        assertEquals(listOf(pontoA, pontoC), resultado)
    }

    @Test
    fun sem_filtro_devolve_todos() {
        val resultado = filtrarPontos(pontosExemplo, null)
        assertEquals(listOf(pontoA, pontoB, pontoC), resultado)
    }
}

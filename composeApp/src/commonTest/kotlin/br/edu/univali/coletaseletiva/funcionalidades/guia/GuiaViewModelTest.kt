package br.edu.univali.coletaseletiva.funcionalidades.guia

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

// PB11 · critérios 5 e 7: estado da busca e do detalhe.
@OptIn(ExperimentalCoroutinesApi::class)
class GuiaViewModelTest {

    private val caixaDeLeite = GuiaItem(
        id = 1,
        nome = "Caixa de leite (longa vida)",
        destino = Destino.COLETA_SELETIVA,
        orientacao = "Enxágue e amasse.",
        sinonimos = emptyList(),
    )
    private val garrafaPet = GuiaItem(
        id = 2,
        nome = "Garrafa PET",
        destino = Destino.COLETA_SELETIVA,
        orientacao = "Esvazie e amasse.",
        sinonimos = listOf("refrigerante"),
    )
    private val oleo = GuiaItem(
        id = 3,
        nome = "Óleo de cozinha usado",
        destino = Destino.PONTO_DE_DESCARTE,
        orientacao = "Leve a um ponto de descarte.",
        sinonimos = emptyList(),
    )

    private val repo = GuiaRepositorioEmMemoria(listOf(oleo, garrafaPet, caixaDeLeite))

    @BeforeTest
    fun antes() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @AfterTest
    fun depois() {
        Dispatchers.resetMain()
    }

    @Test
    fun digitar_filtra_a_lista() = runTest {
        val vm = GuiaViewModel(repo)
        assertFalse(vm.estado.value.carregando)
        assertEquals(listOf(caixaDeLeite, garrafaPet, oleo), vm.estado.value.itens)

        vm.digitar("LEITE")

        assertEquals("LEITE", vm.estado.value.termo)
        assertEquals(listOf(caixaDeLeite), vm.estado.value.itens)
        assertFalse(vm.estado.value.nadaEncontrado)
    }

    @Test
    fun sem_resultado_marca_nadaEncontrado() = runTest {
        val vm = GuiaViewModel(repo)

        vm.digitar("pneu de trator")
        assertTrue(vm.estado.value.itens.isEmpty())
        assertTrue(vm.estado.value.nadaEncontrado)

        // Apagar o termo volta a mostrar tudo.
        vm.digitar("")
        assertEquals(3, vm.estado.value.itens.size)
        assertFalse(vm.estado.value.nadaEncontrado)
    }

    @Test
    fun abrir_item_carrega_o_detalhe() = runTest {
        val vm = GuiaDetalheViewModel(itemId = 3, repo = repo)
        assertFalse(vm.estado.value.carregando)
        assertEquals(oleo, vm.estado.value.item)
        assertFalse(vm.estado.value.naoEncontrado)

        val inexistente = GuiaDetalheViewModel(itemId = 999, repo = repo)
        assertNull(inexistente.estado.value.item)
        assertTrue(inexistente.estado.value.naoEncontrado)
    }
}

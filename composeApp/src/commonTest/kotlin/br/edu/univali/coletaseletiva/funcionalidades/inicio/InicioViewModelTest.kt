package br.edu.univali.coletaseletiva.funcionalidades.inicio

import br.edu.univali.coletaseletiva.funcionalidades.local.LocalRepositorioFake
import br.edu.univali.coletaseletiva.funcionalidades.local.Municipio
import br.edu.univali.coletaseletiva.funcionalidades.local.Setor
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

@OptIn(ExperimentalCoroutinesApi::class)
class InicioViewModelTest {
    private fun repositorio() = LocalRepositorioFake(
        municipiosIniciais = listOf(Municipio(1, "Itajaí", "SC")),
        setoresIniciais = listOf(Setor(1, 1, "Centro")),
    )

    @BeforeTest
    fun antes() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @AfterTest
    fun depois() {
        Dispatchers.resetMain()
    }

    @Test
    fun com_setor_escolhido_mostra_municipio_e_setor() = runTest {
        val repo = repositorio()
        repo.escolherSetor(1)
        val vm = InicioViewModel(repo)
        assertFalse(vm.estado.value.carregando)
        assertEquals("Itajaí · Centro", vm.estado.value.local)
        assertFalse(vm.estado.value.precisaEscolherLocal)
    }

    @Test
    fun sem_setor_escolhido_marca_precisaEscolherLocal() = runTest {
        val vm = InicioViewModel(repositorio())
        assertFalse(vm.estado.value.carregando)
        assertNull(vm.estado.value.local)
        assertTrue(vm.estado.value.precisaEscolherLocal)
    }
}

package br.edu.univali.coletaseletiva.funcionalidades.local

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
class EscolherLocalViewModelTest {
    private val itajai = Municipio(1, "Itajaí", "SC")
    private val camboriu = Municipio(2, "Camboriú", "SC")
    private val setores = listOf(
        Setor(1, 1, "Centro"),
        Setor(4, 2, "Centro"),
        Setor(5, 2, "Monte Alegre"),
    )

    @BeforeTest
    fun antes() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @AfterTest
    fun depois() {
        Dispatchers.resetMain()
    }

    private fun repositorio() = LocalRepositorioFake(listOf(itajai, camboriu), setores)

    @Test
    fun lista_os_municipios_em_ordem_alfabetica() = runTest {
        val vm = EscolherLocalViewModel(repositorio())
        assertFalse(vm.estado.value.carregando)
        assertEquals(listOf("Camboriú", "Itajaí"), vm.estado.value.municipios.map { it.nome })
    }

    @Test
    fun ao_escolher_municipio_mostra_so_os_setores_dele() = runTest {
        val vm = EscolherLocalViewModel(repositorio())
        vm.escolherMunicipio(2)
        assertEquals(camboriu, vm.estado.value.municipioSelecionado)
        assertEquals(listOf("Centro", "Monte Alegre"), vm.estado.value.setores.map { it.nome })
    }

    @Test
    fun ao_confirmar_salva_a_escolha_e_marca_concluido() = runTest {
        val repo = repositorio()
        val vm = EscolherLocalViewModel(repo)
        vm.escolherMunicipio(2)
        vm.escolherSetor(5)
        vm.confirmar()
        assertTrue(vm.estado.value.concluido)
        assertEquals(SetorEscolhido(5, "Camboriú", "Monte Alegre"), repo.setorEscolhido())
    }

    @Test
    fun sem_municipios_mostra_mensagem_de_lista_vazia() = runTest {
        val vm = EscolherLocalViewModel(LocalRepositorioFake())
        assertFalse(vm.estado.value.carregando)
        assertTrue(vm.estado.value.semMunicipios)
        assertTrue(vm.estado.value.municipios.isEmpty())
    }

    @Test
    fun confirmar_sem_escolher_setor_nao_conclui_nem_salva() = runTest {
        val repo = repositorio()
        val vm = EscolherLocalViewModel(repo)
        vm.escolherMunicipio(2)
        vm.confirmar()
        assertFalse(vm.estado.value.concluido)
        assertNull(repo.setorEscolhido())
    }

    @Test
    fun trocar_de_municipio_limpa_o_setor_escolhido() = runTest {
        val vm = EscolherLocalViewModel(repositorio())
        vm.escolherMunicipio(2)
        vm.escolherSetor(5)
        vm.escolherMunicipio(1)
        assertNull(vm.estado.value.setorSelecionado)
        assertEquals(listOf("Centro"), vm.estado.value.setores.map { it.nome })
    }
}

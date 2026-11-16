package br.edu.univali.coletaseletiva.funcionalidades.pontuacao

import br.edu.univali.coletaseletiva.funcionalidades.pontos.Material
import br.edu.univali.coletaseletiva.util.RelogioFixo
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import kotlin.test.Test

// PB16 · critério 8: o repositório é chamado exatamente uma vez num registro válido (R10).
class RegistrarDescarteMockitoTest {

    private val relogio = RelogioFixo(LocalDate(2026, 11, 16))

    @Test
    fun registro_valido_chama_inserir_uma_vez() = runTest {
        val repo = mock<DescarteRepositorio> {
            onBlocking { contarNaData(any()) } doReturn 0
        }

        RegistrarDescarte(repo, relogio)(Material.PLASTICO)

        verify(repo, times(1)).inserir(any())
    }

    @Test
    fun limite_atingido_nunca_chama_inserir() = runTest {
        val repo = mock<DescarteRepositorio> {
            onBlocking { contarNaData(any()) } doReturn 3
        }

        RegistrarDescarte(repo, relogio)(Material.PLASTICO)

        verify(repo, never()).inserir(any())
    }
}

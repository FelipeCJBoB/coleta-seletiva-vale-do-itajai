package br.edu.univali.coletaseletiva.funcionalidades.calendario

import br.edu.univali.coletaseletiva.dados.bancoEmMemoria
import br.edu.univali.coletaseletiva.funcionalidades.local.DadosLocalDemo
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.DayOfWeek
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

// PB08 · critério 9: o que está no banco volta exatamente igual (sem horário).
class CalendarioRepositorioSqlTest {

    @Test
    fun devolve_exatamente_os_dias_cadastrados_do_setor() = runTest {
        val banco = bancoEmMemoria()
        banco.calendarioQueries.inserirColeta(5, "SELETIVA", 1) // segunda
        banco.calendarioQueries.inserirColeta(5, "COMUM", 2) // terça
        banco.calendarioQueries.inserirColeta(5, "COMUM", 5) // sexta
        banco.calendarioQueries.inserirColeta(4, "SELETIVA", 3) // outro setor, não pode aparecer

        val coletas = CalendarioRepositorioSql(banco).coletasDoSetor(5)

        assertEquals(
            listOf(
                ColetaDoSetor(TipoColeta.SELETIVA, setOf(DayOfWeek.MONDAY)),
                ColetaDoSetor(TipoColeta.COMUM, setOf(DayOfWeek.TUESDAY, DayOfWeek.FRIDAY)),
            ),
            coletas.sortedBy { it.tipo.ordinal },
        )
    }

    @Test
    fun semear_cadastra_os_dois_tipos_de_todos_os_setores_so_uma_vez() = runTest {
        val repo = CalendarioRepositorioSql(bancoEmMemoria())

        repo.semearSeVazio()
        repo.semearSeVazio() // chamar de novo não duplica nem quebra

        for (setor in DadosLocalDemo.setores) {
            val coletas = repo.coletasDoSetor(setor.id)
            assertEquals(
                setOf(TipoColeta.SELETIVA, TipoColeta.COMUM),
                coletas.map { it.tipo }.toSet(),
                "O setor ${setor.nome} (${setor.id}) precisa de coleta seletiva e comum.",
            )
            // Entre segunda e sexta, como combinado para os dados de demonstração.
            coletas.flatMap { it.diasDaSemana }.forEach {
                assertTrue(it != DayOfWeek.SATURDAY && it != DayOfWeek.SUNDAY, "Dia de coleta fora de segunda a sexta.")
            }
        }
    }
}

# Receitas (consulta)

Todo código abaixo foi **compilado e testado no CI** com um exemplo de outro assunto (Tarefa). Copie o padrão e troque os nomes pelos do seu cartão. Não faz parte do app.

## R1 · Modelo e função pura com teste
*Quando usar: Toda regra de negócio nasce assim.*

Uma `data class` guarda dados. Uma função pura recebe valores e devolve um valor (sem banco, sem tela). É a parte mais fácil de testar. O teste usa `kotlin.test` (`@Test`, `assertEquals`).

`commonMain/.../exemplo/Tarefa.kt`

```kotlin
package br.edu.univali.coletaseletiva.exemplo

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import kotlin.time.Clock

data class Tarefa(val id: Long, val titulo: String, val feita: Boolean)

fun contarPendentes(tarefas: List<Tarefa>): Int = tarefas.count { !it.feita }

fun proximoDiaUtil(data: LocalDate): LocalDate {
    var d = data.plus(1, DateTimeUnit.DAY)
    while (d.dayOfWeek == DayOfWeek.SATURDAY || d.dayOfWeek == DayOfWeek.SUNDAY) {
        d = d.plus(1, DateTimeUnit.DAY)
    }
    return d
}

fun hoje(): LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
```

`commonTest/.../exemplo/TarefaTest.kt`

```kotlin
package br.edu.univali.coletaseletiva.exemplo

import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class TarefaTest {
    @Test
    fun conta_somente_pendentes() {
        val lista = listOf(Tarefa(1, "a", false), Tarefa(2, "b", true))
        assertEquals(1, contarPendentes(lista))
    }

    @Test
    fun sexta_vai_para_segunda() {
        assertEquals(LocalDate(2026, 10, 26), proximoDiaUtil(LocalDate(2026, 10, 23)))
    }

    @Test
    fun hoje_nao_quebra() {
        hoje()
    }
}
```

> Dica: Nome de teste: letras minúsculas e underscore, sem acento (ex.: dia_normal_proxima_e_a_segunda).

## R2 · Interface do repositório e Fake
*Quando usar: Antes de escrever ViewModel ou SQL.*

A interface diz O QUE o repositório faz. O Fake (só para testes) implementa a interface com uma lista em memória. Assim o ViewModel é testado sem banco. `suspend` marca funções que rodam em segundo plano.

`commonMain/.../exemplo/TarefaRepositorio.kt`

```kotlin
package br.edu.univali.coletaseletiva.exemplo

interface TarefaRepositorio {
    suspend fun listar(): List<Tarefa>
    suspend fun adicionar(titulo: String)
}
```

`commonTest/.../exemplo/TarefaRepositorioFake.kt`

```kotlin
package br.edu.univali.coletaseletiva.exemplo

class TarefaRepositorioFake(inicial: List<Tarefa> = emptyList()) : TarefaRepositorio {
    private val itens = inicial.toMutableList()
    override suspend fun listar(): List<Tarefa> = itens.toList()
    override suspend fun adicionar(titulo: String) {
        itens.add(Tarefa(itens.size + 1L, titulo, false))
    }
}
```

## R3 · ViewModel com StateFlow e o teste dele
*Quando usar: Entre o repositório e a tela.*

O ViewModel guarda o ESTADO da tela (`data class ...Estado`) num `StateFlow` e expõe ações (funções). A tela só observa o estado. No teste, `Dispatchers.setMain(UnconfinedTestDispatcher())` faz o `viewModelScope` rodar na hora; não esqueça do `resetMain` no `@AfterTest`.

`commonMain/.../exemplo/TarefasViewModel.kt`

```kotlin
package br.edu.univali.coletaseletiva.exemplo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class TarefasEstado(
    val carregando: Boolean = true,
    val tarefas: List<Tarefa> = emptyList(),
)

class TarefasViewModel(private val repositorio: TarefaRepositorio) : ViewModel() {
    private val _estado = MutableStateFlow(TarefasEstado())
    val estado: StateFlow<TarefasEstado> = _estado.asStateFlow()

    init {
        carregar()
    }

    private fun carregar() {
        viewModelScope.launch {
            _estado.value = TarefasEstado(carregando = false, tarefas = repositorio.listar())
        }
    }

    fun adicionar(titulo: String) {
        viewModelScope.launch {
            repositorio.adicionar(titulo)
            carregar()
        }
    }
}
```

`commonTest/.../exemplo/TarefasViewModelTest.kt`

```kotlin
package br.edu.univali.coletaseletiva.exemplo

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

@OptIn(ExperimentalCoroutinesApi::class)
class TarefasViewModelTest {
    @BeforeTest
    fun antes() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @AfterTest
    fun depois() {
        Dispatchers.resetMain()
    }

    @Test
    fun carrega_tarefas_ao_iniciar() = runTest {
        val vm = TarefasViewModel(TarefaRepositorioFake(listOf(Tarefa(1, "a", false))))
        assertFalse(vm.estado.value.carregando)
        assertEquals(1, vm.estado.value.tarefas.size)
    }

    @Test
    fun adicionar_atualiza_a_lista() = runTest {
        val vm = TarefasViewModel(TarefaRepositorioFake())
        vm.adicionar("nova")
        assertEquals("nova", vm.estado.value.tarefas.single().titulo)
    }
}
```

## R4 · Tela (Compose): Rota + tela sem ViewModel
*Quando usar: Depois do ViewModel.*

Duas funções: a `...Rota` pega o container, cria o ViewModel (`viewModel { ... }`) e coleta o estado; a `...Lista`/`...Tela` só recebe o estado e funções (fica fácil de testar e de visualizar). Para o botão Voltar, use `navegador.voltar()`; para abrir outra tela, `navegador.ir(Rota.X)`.

`commonMain/.../exemplo/TarefasTela.kt`

```kotlin
package br.edu.univali.coletaseletiva.exemplo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.univali.coletaseletiva.di.LocalContainer
import br.edu.univali.coletaseletiva.navegacao.Navegador

@Composable
fun TarefasRota(navegador: Navegador) {
    val container = LocalContainer.current
    val vm = viewModel { TarefasViewModel(container.tarefaRepositorio) }
    val estado by vm.estado.collectAsState()
    TarefasLista(estado.tarefas, onAdicionar = vm::adicionar, onVoltar = { navegador.voltar() })
}

@Composable
fun TarefasLista(tarefas: List<Tarefa>, onAdicionar: (String) -> Unit, onVoltar: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Tarefas", style = MaterialTheme.typography.headlineMedium)
        LazyColumn {
            items(tarefas, key = { it.id }) { Text(it.titulo) }
        }
        Button(onClick = { onAdicionar("Nova") }) { Text("Adicionar") }
        Button(onClick = onVoltar) { Text("Voltar") }
    }
}
```

> Dica: Textos da tela em português. Para espaçamento use `padding(16.dp)`; para cores use `MaterialTheme.colorScheme`.

## R5 · Teste de interface (Compose)
*Quando usar: 1 teste por tela, na Sprint 3.*

Esse teste monta a tela com dados de exemplo e confere o que aparece. Fica na pasta androidInstrumentedTest e roda em emulador (Android Studio ▶ ao lado do teste) ou no CI (Actions > CI > Run workflow > job 'Testes de interface').

`androidInstrumentedTest/.../exemplo/TarefasListaTest.kt`

```kotlin
package br.edu.univali.coletaseletiva.exemplo

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class TarefasListaTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun mostra_o_titulo_da_tarefa() {
        rule.setContent { TarefasLista(listOf(Tarefa(1, "Separar o lixo", false)), {}, {}) }
        rule.onNodeWithText("Separar o lixo").assertIsDisplayed()
    }
}
```

> Dica: Outros comandos úteis: onNodeWithText("...").performClick() e performTextInput("...").

## R6 · Tabela SQL (arquivo .sq)
*Quando usar: Só depois do PB06b mergeado.*

Cada arquivo `.sq` descreve tabelas e consultas. Linha terminada em `nome:` vira uma função Kotlin `banco.xxxQueries.nome(...)`. O nome do arquivo (`tarefa.sq`) vira `tarefaQueries`.

`commonMain/sqldelight/.../dados/tarefa.sq`

```sql
CREATE TABLE tarefa (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    titulo TEXT NOT NULL,
    feita INTEGER NOT NULL DEFAULT 0
);

listarTodas:
SELECT id, titulo, feita FROM tarefa ORDER BY id;

inserir:
INSERT INTO tarefa(titulo) VALUES (?);

contar:
SELECT COUNT(*) FROM tarefa;
```

> Dica: Depois de salvar o .sq rode `.\gradlew.bat testDebugUnitTest` para o Kotlin ser gerado. REGRA D-13: escreva sempre CREATE TABLE IF NOT EXISTS (o app abre bancos antigos do emulador e, sem o IF NOT EXISTS, a tabela nova não é criada). Se mudar colunas de uma tabela que já existe, desinstale o app do emulador (Wipe Data).

## R7 · Repositório SQL e teste com banco em memória
*Quando usar: Depois da tabela.*

O repositório SQL implementa a interface da R2. O teste cria um SQLite em memória (some quando o teste acaba), então não precisa de celular. Fica na pasta androidUnitTest (roda na JVM).

`commonMain/.../exemplo/TarefaRepositorioSql.kt`

```kotlin
package br.edu.univali.coletaseletiva.exemplo

import br.edu.univali.coletaseletiva.dados.Banco
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TarefaRepositorioSql(private val banco: Banco) : TarefaRepositorio {

    override suspend fun listar(): List<Tarefa> = withContext(Dispatchers.Default) {
        banco.tarefaQueries.listarTodas { id, titulo, feita -> Tarefa(id, titulo, feita == 1L) }
            .executeAsList()
    }

    override suspend fun adicionar(titulo: String) = withContext(Dispatchers.Default) {
        banco.tarefaQueries.inserir(titulo)
        Unit
    }

    suspend fun semearSeVazio() = withContext(Dispatchers.Default) {
        if (banco.tarefaQueries.contar().executeAsOne() == 0L) {
            listOf("Separar o lixo", "Levar as pilhas ao ponto").forEach { banco.tarefaQueries.inserir(it) }
        }
    }
}
```

`androidUnitTest/.../exemplo/TarefaRepositorioSqlTest.kt`

```kotlin
package br.edu.univali.coletaseletiva.exemplo

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import br.edu.univali.coletaseletiva.dados.Banco
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class TarefaRepositorioSqlTest {
    private fun bancoEmMemoria(): Banco {
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        Banco.Schema.create(driver)
        return Banco(driver)
    }

    @Test
    fun adicionar_e_listar() = runTest {
        val repo = TarefaRepositorioSql(bancoEmMemoria())
        repo.adicionar("x")
        assertEquals(listOf("x"), repo.listar().map { it.titulo })
    }

    @Test
    fun semear_so_quando_vazio() = runTest {
        val repo = TarefaRepositorioSql(bancoEmMemoria())
        repo.semearSeVazio()
        repo.semearSeVazio()
        assertEquals(2, repo.listar().size)
    }
}
```

> Dica: Quando o PB06b estiver pronto, use `bancoEmMemoria()` do projeto no lugar do `bancoEmMemoria()` privado do exemplo.

## R8 · Dados de demonstração (semear)
*Quando usar: No fim do cartão.*

`semearSeVazio()` (no repositório SQL da R7) só insere os dados se a tabela estiver vazia. Quem chama é `AppContainer.iniciar()`, que roda antes da primeira tela.

`commonMain/.../di/AppContainer.kt (no SEU bloco de iniciar())`

```kotlin
// ===== INTEGRANTE N (PBxx): dados de demonstração =====
// início
tarefaRepositorio.semearSeVazio()
// fim
```

## R9 · Ligar a funcionalidade no app
*Quando usar: Último passo antes do PR.*

Dois lugares, sempre dentro do SEU bloco: (1) `AppContainer.kt` cria o repositório (by lazy); (2) `AppNavegacao.kt` troca `TelaEmConstrucao` pela sua `...Rota`.

`commonMain/.../di/AppContainer.kt (seu bloco)`

```kotlin
val tarefaRepositorio: TarefaRepositorioSql by lazy { TarefaRepositorioSql(banco) }
```

`commonMain/.../navegacao/AppNavegacao.kt (seu bloco)`

```kotlin
Rota.Dicas -> {
    TarefasRota(navegador)
}
```

## R10 · Mockito: verificar se algo foi chamado
*Quando usar: Só quando o cartão pedir.*

Mockito cria um repositório de mentira que registra as chamadas. `verify(repo).adicionar("x")` confere que foi chamado uma vez. Fica em androidUnitTest (roda na JVM).

`androidUnitTest/.../exemplo/TarefasViewModelMockitoTest.kt`

```kotlin
package br.edu.univali.coletaseletiva.exemplo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TarefasViewModelMockitoTest {
    @BeforeTest
    fun antes() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @AfterTest
    fun depois() {
        Dispatchers.resetMain()
    }

    @Test
    fun adicionar_chama_o_repositorio_uma_vez() = runTest {
        val repo = mock<TarefaRepositorio> {
            onBlocking { listar() } doReturn emptyList()
        }
        val vm = TarefasViewModel(repo)
        vm.adicionar("x")
        verify(repo).adicionar("x")
    }
}
```

## R11 · Datas com kotlinx-datetime
*Quando usar: Calendário, lembrete, pontos por dia, dica do dia.*

`LocalDate(2026, 10, 23)` é uma data; `.dayOfWeek` é o dia da semana; `.plus(1, DateTimeUnit.DAY)` soma um dia; `.dayOfYear` é o dia do ano. A função `proximoDiaUtil` do exemplo (R1) mostra um laço com datas. Para 'hoje' use sempre o `Relogio` (nos testes, `RelogioFixo`).

`commonTest/.../util/RelogioFixo.kt (já existe)`

```kotlin
class RelogioFixo(var data: LocalDate) : Relogio {
    override fun hoje(): LocalDate = data
}
```

## R12 · Git do dia a dia
*Quando usar: Em todo cartão.*

Um cartão = uma branch = um Pull Request. Mensagem de commit começa com `test:`, `feat:`, `refactor:`, `fix:` ou `docs:`.

`terminal`

```bash
git checkout main
git pull
git checkout -b feat/pb08-calendario
# ... escreva o teste ...
git add .
git commit -m "test: cobre coleta em feriado"
git push -u origin feat/pb08-calendario
# ... escreva o código ...
git add .
git commit -m "feat: empurra a coleta para o proximo dia util"
git push
```

> Dica: Errou algo? Não use 'git push --force' nem 'git reset --hard' sem perguntar no grupo.

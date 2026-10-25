# App final da Etapa 2 (definição)

Este arquivo define o app completo. O que está aqui é o combinado do grupo; mudanças viram PR neste arquivo.

## Arquitetura (MVVM)

```
Tela (Compose)  →  ViewModel (estado + ações)  →  Repositório (interface)  →  SQLite (SQLDelight) ou lista em memória
```

- Pasta de cada funcionalidade: `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/<nome>/`.
- Testes: `commonTest` (regras, ViewModels com Fake; rodam no Android e no simulador iOS), `androidUnitTest` (SQL em memória e Mockito), `androidInstrumentedTest` (interface, emulador/CI).
- Peças criadas e entregues às telas pelo `AppContainer` (injeção manual). Navegação por `Navegador` + `Rota`.
- Tecnologias: Kotlin Multiplatform, Compose Multiplatform, SQLDelight, kotlinx-datetime, kotlinx-coroutines, GitHub Actions. Versões em `gradle/libs.versions.toml`.

## Telas

| Tela | O que mostra | Dono | Cartão |
|---|---|---|---|
| Início | Mostra 'Município · Setor' e 5 atalhos (Calendário, Pontos de descarte, Guia de separação, Meus pontos, Dicas). Aviso 'Dados de demonstração'. | Integrante 1 com o Claude (pronta) | PB07b |
| Escolher local | Escolhe município e depois setor; guarda no aparelho. | Integrante 1 com o Claude (pronta) | PB07 |
| Calendário | Dias da coleta seletiva e comum + próxima data (com feriados). Sem horário fixo. | Integrante 2 | PB08 |
| Pontos de descarte | Lista do município com chips de filtro por material e aviso para pilhas/óleo. | Integrante 3 | PB09 |
| Detalhe do ponto | Endereço, horário, materiais com limite, coordenadas e botão 'Abrir rota' (app de mapas). | Integrante 3 | PB10 |
| Guia de separação | Busca ('caixa de leite') e detalhe com orientação. Funciona sem internet. | Integrante 4 | PB11 |
| Meus pontos | Saldo, histórico e botão 'Registrar descarte'. | Integrante 5 | PB16 |
| Registrar descarte | Escolhe o material e ganha pontos (máx. 3 por dia). | Integrante 5 | PB16 |
| Dicas de reciclagem | Dica do dia e lista de dicas. | Integrante 3 | PB17 |
| Lembrete (opcional) | Avisa na véspera da coleta, no horário escolhido. | Integrante 2 | PB12 |

## Dados (SQLite)

| Tabela | Colunas | Cartão |
|---|---|---|
| municipio | id, nome, uf | PB07 |
| setor | id, municipio_id, nome | PB07 |
| escolha_local | id (1), setor_id | PB07 |
| coleta_dia | setor_id, tipo, dia_semana | PB08 |
| ponto_descarte | id, municipio_id, nome, endereco, horario, latitude, longitude | PB09 |
| ponto_material | ponto_id, material, limite | PB09 |
| descarte | id, material, pontos, data | PB06b/PB16 |

Conteúdo estático (sem SQL): itens do guia (PB11) e dicas (PB17).

**Ids fixos dos dados de demonstração:** municípios 1 Itajaí, 2 Camboriú · setores 1 Centro (Itajaí), 2 São Vicente, 3 Fazenda, 4 Centro (Camboriú), 5 Monte Alegre, 6 Tabuleiro. Todos os dados são fictícios e marcados como demonstração.

## Regras de negócio que viram testes

RN01 a RN03 (calendário e feriados), RN04 (pilhas/óleo só em ponto de descarte), RN05 (limites por material), RN06 (só locais cadastrados), RN07 (escolha só no aparelho), RN08 (pontos por material), RN09 (máx. 3 por dia). Texto completo em `docs/ESCOPO.md`.

## Decisões novas da Etapa 2

| ID | Decisão | Detalhe |
|---|---|---|
| D-07 | Roupas não pontuam | A RN08 só define pontos para papel, plástico, metal, vidro, pilhas/baterias, óleo e eletrônicos. Roupas aparecem nos pontos de descarte (filtro) mas não geram pontos. Se o grupo quiser pontuar, define um valor na Planning. |
| D-08 | Guia e dicas são conteúdo estático | Itens do guia e dicas ficam em listas dentro do app (funcionam offline, sem SQL). Município, setor, calendário, pontos de descarte e descartes ficam no SQLite. |
| D-09 | Feriados: só 2026 e 2027 | Feriados fixos + Sexta-feira Santa tabelada para 2026 e 2027 (a Páscoa muda a cada ano). Registrado no relatório como limitação. |
| D-10 | Voltar do celular | Navegação por botão 'Voltar' na tela. O botão voltar do sistema Android ainda fecha o app (melhoria futura, só se sobrar tempo). |
| D-13 | Tabelas novas com IF NOT EXISTS | Toda tabela nova em arquivo .sq usa CREATE TABLE IF NOT EXISTS. O DriverFactory do Android cria as tabelas que faltam ao abrir o app (assim um emulador com banco antigo ganha as tabelas novas). Mudou colunas de uma tabela existente? Desinstale o app do emulador (Wipe Data). Quem introduziu isso: Integrante 3, no PR #6. |
| D-12 | PB07 e PB07b feitos pelo Integrante 1 com o Claude | Por combinado entre o Integrante 1 e o Integrante 2 (divisão de carga), o Integrante 1 fez o PB07 e o PB07b com o Claude, em TDD, com PRs #4 e #5. O Integrante 2 segue com PB08 e PB12. Também entrou infraestrutura: EscopoDeTela (ViewModel novo a cada vez que a tela abre) e `configChanges` no Manifest. |
| D-11 | iOS | Os testes comuns rodam no simulador iOS pelo CI (macOS). O app iOS completo (projeto Xcode) é item do Integrante 1 com o Claude na Sprint 3/4; se não der, o relatório diz exatamente o que foi feito. |

## Quem espera quem

- **PB06b (FEITO, PR #2) → todos:** O `banco` e o `bancoEmMemoria()` já estão na main (rode `git pull`). Até lá cada um faz o que não precisa de banco (modelos, regras, testes, ViewModel, tela). O passo SQL de cada cartão só depois.
- **Material (Integrante 3, PB09) → PB16:** O enum `Material` sai num PR pequeno até qua 28/10. O Integrante 5 usa nos pontos.
- **PB07 (FEITO, PR #4) → PB08, PB09, PB12:** O `LocalRepositorio` e o `LocalRepositorioFake` já estão na main. Todos que precisam saber 'qual município/setor' usam o Fake nos testes (rode `git pull`).
- **PB08 → PB12:** O lembrete usa a regra `proximaColeta`.

## Mapa: cartão → arquivos → testes

### PB06b · Banco local ligado e ajudante de teste (Integrante 5)
- `composeApp/src/commonMain/sqldelight/br/edu/univali/coletaseletiva/dados/descarte.sq`
- `composeApp/src/androidUnitTest/kotlin/br/edu/univali/coletaseletiva/dados/BancoDeTeste.kt`
- `composeApp/src/androidUnitTest/kotlin/br/edu/univali/coletaseletiva/dados/DescarteTabelaTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/di/AppContainer.kt` (já existe)

### PB07 · Escolher município e setor (Integrante 2)
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/local/Local.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/local/LocalRepositorio.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/local/LocalRepositorioFake.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/local/EscolherLocalViewModelTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/local/EscolherLocalViewModel.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/local/EscolherLocalTela.kt`
- `composeApp/src/androidInstrumentedTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/local/EscolherLocalTelaTest.kt`
- `composeApp/src/commonMain/sqldelight/br/edu/univali/coletaseletiva/dados/local.sq`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/local/LocalRepositorioSql.kt`
- `composeApp/src/androidUnitTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/local/LocalRepositorioSqlTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/local/DadosLocalDemo.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/di/AppContainer.kt` (já existe)

### PB07b · Identidade do app e tela Início (Integrante 2)
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/inicio/Tema.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/inicio/InicioViewModelTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/inicio/InicioViewModel.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/inicio/InicioTela.kt`
- `composeApp/src/androidInstrumentedTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/inicio/InicioTelaTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/navegacao/AppNavegacao.kt` (já existe)

### PB08 · Calendário de coleta com feriados (Integrante 2)
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/Coleta.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/FeriadosTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/Feriados.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/ProximaColetaTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/ProximaColeta.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/CalendarioRepositorio.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/CalendarioRepositorioFake.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/CalendarioViewModelTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/CalendarioViewModel.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/CalendarioTela.kt`
- `composeApp/src/androidInstrumentedTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/CalendarioTelaTest.kt`
- `composeApp/src/commonMain/sqldelight/br/edu/univali/coletaseletiva/dados/calendario.sq`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/CalendarioRepositorioSql.kt`
- `composeApp/src/androidUnitTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/CalendarioRepositorioSqlTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/DadosCalendarioDemo.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/di/AppContainer.kt` (já existe)

### PB12 · Lembrete na véspera da coleta (opcional) (Integrante 2)
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/lembrete/DataDoLembrete.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/lembrete/DataDoLembreteTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/lembrete/AgendadorDeLembrete.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/lembrete/LembreteViewModel.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/lembrete/LembreteViewModelTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/lembrete/LembreteTela.kt`
- `composeApp/src/androidMain/kotlin/br/edu/univali/coletaseletiva/lembrete/AgendadorAndroid.kt`
- `composeApp/src/iosMain/kotlin/br/edu/univali/coletaseletiva/lembrete/AgendadorIos.kt`

### PB09 · Pontos de descarte com filtro por material (Integrante 3)
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/Material.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/MaterialTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontoDeDescarte.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/FiltroDePontosTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/FiltroDePontos.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontosRepositorio.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontosRepositorioFake.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontosViewModelTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontosViewModel.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontosTela.kt`
- `composeApp/src/androidInstrumentedTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontosTelaTest.kt`
- `composeApp/src/commonMain/sqldelight/br/edu/univali/coletaseletiva/dados/pontos.sq`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontosRepositorioSql.kt`
- `composeApp/src/androidUnitTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontosRepositorioSqlTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/DadosPontosDemo.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/di/AppContainer.kt` (já existe)

### PB10 · Detalhe do ponto e rota no app de mapas (Integrante 3)
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/RotaNoMapaTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/RotaNoMapa.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontoDetalheViewModelTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontoDetalheViewModel.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontoDetalheTela.kt`
- `composeApp/src/androidInstrumentedTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontoDetalheTelaTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/navegacao/AppNavegacao.kt` (já existe)

### PB17 · Dicas de reciclagem (Integrante 3)
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/dicas/Dica.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/dicas/DicaDoDiaTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/dicas/DicaDoDia.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/dicas/DicasRepositorio.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/dicas/DadosDicasDemo.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/dicas/DicasViewModelTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/dicas/DicasViewModel.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/dicas/DicasTela.kt`
- `composeApp/src/androidInstrumentedTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/dicas/DicasTelaTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/di/AppContainer.kt` (já existe)

### PB11 · Guia de separação pesquisável (offline) (Integrante 4)
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/util/Texto.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/util/TextoTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/guia/GuiaItem.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/guia/BuscaNoGuiaTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/guia/BuscaNoGuia.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/guia/GuiaRepositorio.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/guia/DadosGuiaDemo.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/guia/DadosGuiaTest.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/guia/GuiaViewModelTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/guia/GuiaViewModel.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/guia/GuiaTela.kt`
- `composeApp/src/androidInstrumentedTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/guia/GuiaTelaTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/di/AppContainer.kt` (já existe)

### PB16 · Registrar descarte, saldo e histórico de pontos (Integrante 5)
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/Descarte.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/PontosPorMaterialTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/PontosPorMaterial.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/DescarteRepositorio.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/DescarteRepositorioFake.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/RegistrarDescarteTest.kt`
- `composeApp/src/androidUnitTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/RegistrarDescarteMockitoTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/RegistrarDescarte.kt`
- `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/PontuacaoViewModelsTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/MeusPontosViewModel.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/RegistrarDescarteViewModel.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/PontuacaoTelas.kt`
- `composeApp/src/androidInstrumentedTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/PontuacaoTelasTest.kt`
- `composeApp/src/commonMain/sqldelight/br/edu/univali/coletaseletiva/dados/descarte.sq`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/DescarteRepositorioSql.kt`
- `composeApp/src/androidUnitTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/DescarteRepositorioSqlTest.kt`
- `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/di/AppContainer.kt` (já existe)


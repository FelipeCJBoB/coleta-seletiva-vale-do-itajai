# Integrante 5 · Desenvolvimento: banco, pontuação e documentação

> Sua bíblia da Etapa 2. Tudo que você precisa está aqui (e igual no Trello). Primeiro leia o Plano geral no HTML.

## Seu papel em 1 minuto

Liga o banco para todo mundo, faz a pontuação (registrar descarte, saldo, histórico) e escreve/entrega o relatório da Etapa 2.

**Você faz:**
- PB06b Banco ligado (primeira tarefa, todos dependem)
- PB16 Registrar descarte, saldo e histórico
- Relatório 5.1.1, 5.1.2, 5.1.3, 5.1.4
- Montar o PDF e enviar

**Você não faz:**
- Mexer nas pastas dos outros sem avisar

## Preparação (até domingo 25/10) — vale para os 5

**1. Instalar os programas**
- Git — git-scm.com (instalação padrão).
- JDK 17 (Temurin) — adoptium.net, versão 17 (LTS). Depois de instalar, abra um terminal novo e rode: `java -version` (tem que aparecer 17).
- Android Studio (versão estável) — developer.android.com/studio. Na primeira abertura aceite baixar o Android SDK que ele sugerir.
- VS Code (opcional) — code.visualstudio.com. Extensões úteis: 'GitHub Pull Requests' e 'Kotlin' (a do Kotlin ainda está em alpha: se falhar, use o Android Studio para editar; os comandos são os mesmos).

**2. Conta e identidade no GitHub**
- Aceite o convite para o repositório (o Integrante 1 envia por e-mail/GitHub).
- No GitHub: Settings > Emails > marque 'Keep my email addresses private' e use o e-mail 'noreply' que aparece lá.
- No terminal: `git config --global user.name "seu-usuario-do-github"` e `git config --global user.email "SEU-ID+usuario@users.noreply.github.com"` (o e-mail noreply do passo anterior).

**3. Baixar o projeto**
- No terminal, numa pasta sua: `git clone https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai.git`
- Entre na pasta: `cd coleta-seletiva-vale-do-itajai`

**4. Abrir e testar**
- Android Studio: File > Open > escolha a pasta clonada. Aguarde 'Gradle sync' (1ª vez demora ~10 min; aceite instalar o que ele pedir, como a plataforma do Android SDK).
- No terminal (Windows PowerShell): `.\gradlew.bat testDebugUnitTest` · (Git Bash/Mac/Linux): `./gradlew testDebugUnitTest`. Esperado: 'BUILD SUCCESSFUL'.
- Gerar o APK: `.\gradlew.bat assembleDebug` (o arquivo fica em composeApp/build/outputs/apk/debug/).

**5. Ver o app rodando**
- Android Studio: Tools > Device Manager > Create Device > Pixel 7 > imagem Android 14 (API 34, baixe se pedir) > Finish.
- Clique no ▶ (Run 'composeApp'). Vai aparecer uma tela escrita 'Início: em construção' com um botão Voltar. É isso mesmo: o app será construído nas próximas semanas.
- Alternativa com celular Android: ative 'Opções do desenvolvedor' + 'Depuração USB' e escolha o aparelho no lugar do emulador. Ou baixe o APK gerado pelo CI (aba Actions > a última execução > 'apk-debug').

**6. Seu primeiro Pull Request**
- `git checkout -b docs/ambiente-integrante-N` (troque N pelo seu número).
- Abra docs/CHECKLIST-AMBIENTE.md e troque `[ ]` por `[x]` SÓ na linha do seu Integrante.
- `git add .` · `git commit -m "docs: ambiente do Integrante N pronto"` · `git push -u origin docs/ambiente-integrante-N`
- No GitHub abra o Pull Request, marque o Integrante 1 como revisor. Quando ele fizer o merge, você está pronto.
- Avise no grupo: 'Integrante N: ambiente ok'.

## Sua agenda

| Sprint | Datas | Meta da sprint |
|---|---|---|
| Sprint 1 (fecha o PB05) | até dom 25/10 | Todos com o ambiente funcionando e o primeiro PR feito (checklist de ambiente). |
| Sprint 2 | seg 26/10 a dom 08/11 | Cada um entrega o 1º ciclo TDD da sua funcionalidade: regra testada + ViewModel testado + tela. Banco ligado (PB06b), Material (PB09), LocalRepositorio (PB07) mergeados até qua 28/10. |
| Sprint 3 | seg 09/11 a dom 22/11 | Funcionalidades completas com SQL e dados de demonstração. Testes de interface. Roteiro de validação escrito. |
| Sprint 4 | seg 23/11 a dom 06/12 | App integrado e validado pelo grupo, relatório 5.1.1–5.1.4, vídeo, PDF e entrega. Lembrete (PB12) só se sobrar tempo. |

*Data oficial da Etapa 2 ainda não confirmada com o professor. As datas abaixo são provisórias (6 semanas, 26/10 a 06/12) e o Integrante 1 confirma.*

## O ciclo de cada cartão (vale para todos os cartões de código)

1. **Pegar o cartão.** No Trello, mova o cartão para 'Em andamento' (ou marque o primeiro item do checklist) e avise no grupo se for começar algo que outro espera.
2. **Atualizar e criar a branch.** `git checkout main` · `git pull` · `git checkout -b feat/pbXX-resumo` (ex.: feat/pb08-calendario).
3. **Ler os arquivos-esqueleto.** No VS Code/Android Studio abra a pasta do seu cartão. Cada arquivo já existe, só com comentários: eles dizem o que escrever, passo a passo. Leia todos antes de começar.
4. **Trabalhar na ordem da lista.** A ordem dos arquivos no cartão é a ordem certa: modelos → testes da regra → regra → interface do repositório → Fake → teste do ViewModel → ViewModel → tela → teste de interface → SQL (só depois do PB06b) → repositório SQL → dados → ligar no container.
5. **Ciclo TDD em cada regra (vermelho → verde → refatorar).** (1) Escreva o teste do arquivo de teste e rode: tem que FALHAR. `git commit -m "test: ..."`. (2) Escreva o código mínimo para passar. `git commit -m "feat: ..."`. (3) Melhore o código sem quebrar o teste. `git commit -m "refactor: ..."`. Dê push depois de cada commit: o CI mostra vermelho e depois verde (é a nossa evidência).
6. **Rodar tudo local.** `.\gradlew.bat testDebugUnitTest` (PowerShell) ou `./gradlew testDebugUnitTest`. Para só um teste: `.\gradlew.bat testDebugUnitTest --tests "*NomeDoTeste"`.
7. **Abrir o Pull Request.** `git push -u origin feat/pbXX-resumo` e abra o PR no GitHub. Preencha o modelo: o que mudou, qual cartão, como conferir, checklist.
8. **Revisão e merge.** O Integrante 1 revisa (até 24 h). Corrija o que pedir. Com o CI verde e a aprovação, ele faz o merge.
9. **Fechar o cartão.** Marque o checklist do Trello, mova para 'Concluído' e escreva 1 linha no grupo: 'PBxx mergeado'.

## Seus cartões

### PB06b · Banco local ligado e ajudante de teste
*Integrante 5 · Sprint 2 · 3 pontos · Requisitos: RNF05 (parte do PB06)*

**História:** Como time, quero o banco SQLite ligado ao app e uma forma de testá-lo em memória, para todas as funcionalidades guardarem dados.

**Objetivo:** Deixar o banco funcionando para todos: a primeira tabela (descarte), o `banco` dentro do AppContainer e o ajudante `bancoEmMemoria()` que os outros 3 desenvolvedores vão usar nos testes. É a primeira tarefa da Sprint 2: os outros dependem dela para o passo SQL. Meta: PR mergeado até qua 28/10.

**Regras (docs/ESCOPO.md):**
- Dados ficam só no aparelho (RN07).

**Critérios de aceite = os testes que você vai escrever:**

| # | Critério | Dado | Quando | Então |
|---|---|---|---|---|
| 1 | Tabela criada | o banco em memória recém-criado | eu pergunto quantos descartes existem | a resposta é 0 (a tabela `descarte` existe e está vazia) |
| 2 | Bancos independentes | o ajudante bancoEmMemoria() | eu chamo duas vezes e gravo um descarte só no primeiro | o segundo banco continua vazio |
| 3 | App compila com o banco | o AppContainer com `banco` | o CI roda assembleDebug | o APK é gerado sem erro |

**Arquivos deste cartão, na ordem certa de trabalho:**

1. `composeApp/src/commonMain/sqldelight/br/edu/univali/coletaseletiva/dados/descarte.sq` — Primeira tabela do app: o registro de descartes (usada no PB16).
   - Descreva a tabela `descarte` com 4 colunas: id (número inteiro, chave primária, automático), material (texto, obrigatório; guarda o nome do Material), pontos (número inteiro, obrigatório) e data (texto obrigatório no formato AAAA-MM-DD).
   - Escreva UMA consulta com nome `contarDescartes` que devolve a quantidade de linhas da tabela (é o que o teste usa agora).
   - As outras consultas (inserir, listar, somar, contar por data) ficam para o cartão PB16 — não escreva agora.
   - Receita: R6. Tipo de arquivo: .sq (SQL do SQLDelight).
2. `composeApp/src/androidUnitTest/kotlin/br/edu/univali/coletaseletiva/dados/BancoDeTeste.kt` — Ajudante usado pelos testes de SQL de todo mundo.
   - Crie a função `bancoEmMemoria(): Banco` (pacote ...coletaseletiva.dados).
   - Passo 1: crie um driver SQLite em memória (JdbcSqliteDriver com IN_MEMORY).
   - Passo 2: peça ao esquema do Banco para criar as tabelas nesse driver (Banco.Schema.create).
   - Passo 3: devolva um Banco novo usando esse driver.
   - Receita: R7 (copie o padrão do teste de exemplo).
3. `composeApp/src/androidUnitTest/kotlin/br/edu/univali/coletaseletiva/dados/DescarteTabelaTest.kt` — Testes do banco em memória (casos 1 e 2).
   - Escreva os testes ANTES de mexer no BancoDeTeste (TDD): rode, veja falhar (vermelho), depois implemente.
   - Use kotlin.test (@Test, assertEquals). Cada teste cria seu próprio banco com bancoEmMemoria().
   - ☐ teste: `tabela_descarte_comeca_vazia: banco novo → contarDescartes = 0`
   - ☐ teste: `bancos_em_memoria_sao_independentes: grave algo no banco A (por SQL direto ou, depois do PB16, pelo repositório) → o banco B continua com 0`
4. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/di/AppContainer.kt` *(já existe — edite dentro do seu bloco)* — (arquivo já existe) Dentro do SEU bloco 'INTEGRANTE 5 (PB06b)'.
   - Adicione a propriedade `banco`, criada só quando for usada (by lazy): Banco(driverFactory.criar(Banco.Schema)).
   - Importe Banco (pacote ...coletaseletiva.dados). Não mexa nos blocos dos outros.
   - Rode assembleDebug para garantir que compila.

**Pronto quando:**
- Todos os critérios de aceite têm teste verde (CI verde) e Pull Request aprovado.
- Os outros 3 sabem (aviso no grupo) que `banco` e `bancoEmMemoria()` existem.

### PB16 · Registrar descarte, saldo e histórico de pontos
*Integrante 5 · Sprint 3 · 8 pontos · Requisitos: RF09, RF10, RN08, RN09, CU06, CU07*

**História:** Como morador, quero registrar meus descartes e acumular pontos, com saldo e histórico.

**Objetivo:** O usuário escolhe o material, registra o descarte e ganha pontos (RN08), com limite de 3 registros por dia (RN09). Outra tela mostra saldo e histórico. O registro é autodeclarado (limitação assumida: sem QR/GPS/foto).

**Regras (docs/ESCOPO.md):**
- RN08: papel, plástico, metal, vidro = 5; pilhas, baterias e óleo = 10; eletrônicos = 15. Roupas NÃO pontuam (a regra não define valor) — decisão D-07.
- RN09: no máximo 3 descartes por dia; acima disso avisa e não credita.
- Limitação: o registro é autodeclarado; o limite diário é a contenção.

**Critérios de aceite = os testes que você vai escrever:**

| # | Critério | Dado | Quando | Então |
|---|---|---|---|---|
| 1 | Pontos por material | cada material | pergunto os pontos | PAPEL, PLASTICO, METAL e VIDRO = 5; PILHAS_BATERIAS e OLEO = 10; ELETRONICOS = 15; ROUPAS = não pontua (nulo) |
| 2 | Registrar credita | nenhum descarte hoje | registro PAPEL | sucesso com 5 pontos e 1 descarte salvo com a data de hoje |
| 3 | Limite diário | 3 descartes já registrados hoje | registro o quarto | resultado LimiteAtingido e nada é salvo |
| 4 | Novo dia zera | 3 descartes ontem | registro hoje | sucesso (o limite é por dia) |
| 5 | Roupas não pontuam | qualquer dia | registro ROUPAS | resultado MaterialNaoPontua e nada é salvo |
| 6 | Saldo | descartes de 5, 10 e 15 pontos | consulto o saldo | é 30 |
| 7 | Histórico | 3 descartes em dias diferentes | listo o histórico | aparecem do mais recente para o mais antigo, com material, pontos e data |
| 8 | Repositório chamado uma vez | um registro válido | o caso de uso roda (Mockito) | repositorio.inserir é chamado exatamente 1 vez |
| 9 | Persistência | descartes gravados no banco | leio de novo | saldo e histórico iguais |
| 10 | Telas | estado com saldo 30 e 2 itens | as telas abrem | mostram 'Saldo: 30 pontos', o histórico e o botão 'Registrar descarte'; ao registrar com limite, mostram o aviso |

**Arquivos deste cartão, na ordem certa de trabalho:**

1. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/Descarte.kt` — Modelo.
   - `Descarte`: id (Long), material (Material), pontos (Int), data (LocalDate). Material vem do PB09 (pacote pontos).
2. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/PontosPorMaterialTest.kt` — Teste do caso 1. ESCREVA ANTES.
   - Um teste por faixa de pontos.
   - ☐ teste: `cinco_pontos_para_papel_plastico_metal_vidro`
   - ☐ teste: `dez_pontos_para_pilhas_baterias_e_oleo`
   - ☐ teste: `quinze_pontos_para_eletronicos`
   - ☐ teste: `roupas_nao_pontuam`
3. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/PontosPorMaterial.kt` — Regra RN08.
   - `pontosPorMaterial(material: Material): Int?` — nulo quando o material não pontua (ROUPAS). Use um 'when'.
4. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/DescarteRepositorio.kt` — Contrato.
   - Interface `DescarteRepositorio` (suspend): `inserir(descarte)`, `listarMaisRecentesPrimeiro()`, `contarNaData(data: LocalDate): Int`, `somarPontos(): Int`.
5. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/DescarteRepositorioFake.kt` — Fake em memória.
   - Guarda uma lista; implementa as 4 funções (R2).
6. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/RegistrarDescarteTest.kt` — Testes do caso de uso (casos 2 a 5 e 8). ESCREVA ANTES.
   - Use o RelogioFixo (util) e o DescarteRepositorioFake. Este arquivo cobre os casos 2 a 5; o caso 8 (Mockito) fica no arquivo seguinte, na pasta androidUnitTest.
   - ☐ teste: `registrar_credita_os_pontos_do_material`
   - ☐ teste: `quarto_registro_no_mesmo_dia_atinge_o_limite`
   - ☐ teste: `novo_dia_zera_o_limite`
   - ☐ teste: `roupas_nao_pontuam_e_nada_e_salvo`
7. `composeApp/src/androidUnitTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/RegistrarDescarteMockitoTest.kt` — Teste com Mockito (caso 8).
   - Com mock<DescarteRepositorio>() e verify(...).inserir(...): registro válido chama inserir exatamente uma vez; LimiteAtingido nunca chama inserir (verify never). Receita R10.
   - ☐ teste: `registro_valido_chama_inserir_uma_vez`
   - ☐ teste: `limite_atingido_nunca_chama_inserir`
8. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/RegistrarDescarte.kt` — Caso de uso: regras RN08 e RN09 juntas.
   - Crie o resultado `ResultadoRegistro` (sealed interface): Sucesso(pontos: Int), LimiteAtingido, MaterialNaoPontua.
   - Crie `RegistrarDescarte(repositorio: DescarteRepositorio, relogio: Relogio)` com `suspend operator fun invoke(material: Material): ResultadoRegistro`.
   - Passos: (1) pontos = pontosPorMaterial(material) — se nulo, MaterialNaoPontua; (2) hoje = relogio.hoje(); se repositorio.contarNaData(hoje) já for 3 ou mais, LimiteAtingido; (3) senão insere Descarte(material, pontos, hoje) e devolve Sucesso(pontos).
   - Constante LIMITE_DIARIO = 3 (RN09) no topo do arquivo.
9. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/PontuacaoViewModelsTest.kt` — Testes dos ViewModels (casos 6, 7 e 10). ESCREVA ANTES.
   - Fake + RelogioFixo.
   - ☐ teste: `saldo_soma_os_pontos`
   - ☐ teste: `historico_do_mais_recente_para_o_mais_antigo`
   - ☐ teste: `registrar_com_limite_mostra_aviso`
   - ☐ teste: `registrar_com_sucesso_atualiza_o_saldo`
10. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/MeusPontosViewModel.kt` — Saldo e histórico.
   - `MeusPontosEstado`: carregando, saldo, historico. `MeusPontosViewModel(repo)` (R3).
11. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/RegistrarDescarteViewModel.kt` — Registro.
   - `RegistrarEstado`: materiaisDisponiveis (todos os Material que pontuam), mensagem (texto do último resultado), registrando. `RegistrarDescarteViewModel(registrar: RegistrarDescarte)`: `registrar(material)` chama o caso de uso e transforma o ResultadoRegistro em mensagem: 'Você ganhou N pontos!' / 'Limite de 3 descartes por hoje atingido' / 'Esse material não gera pontos'.
12. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/PontuacaoTelas.kt` — Telas: Meus pontos e Registrar descarte.
   - `MeusPontosRota`/`MeusPontosTela`: 'Saldo: N pontos', botão 'Registrar descarte' (→ Rota.RegistrarDescarte), lista do histórico (material, pontos, data dd/MM/aaaa), texto 'Registro autodeclarado: no máximo 3 por dia.'.
   - `RegistrarDescarteRota`/`RegistrarDescarteTela`: lista de materiais (botões) e a mensagem do resultado.
13. `composeApp/src/androidInstrumentedTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/PontuacaoTelasTest.kt` — Teste de interface.
   - 1 teste por tela (R5).
   - ☐ teste: `meus_pontos_mostra_saldo_e_botao`
   - ☐ teste: `registrar_mostra_os_materiais`
14. `composeApp/src/commonMain/sqldelight/br/edu/univali/coletaseletiva/dados/descarte.sq` — (já existe, criado no PB06b) Complete as consultas.
   - Adicione consultas com nome: inserirDescarte(material, pontos, data), listarDescartesRecentesPrimeiro, contarDescartesNaData(data), somarPontos (soma da coluna pontos; devolva 0 quando vazio — dica: COALESCE).
   - Receita R6.
15. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/DescarteRepositorioSql.kt` — Repositório SQL.
   - Converta material (texto) em Material com Material.valueOf e a data (texto AAAA-MM-DD) com LocalDate.parse (R7).
16. `composeApp/src/androidUnitTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontuacao/DescarteRepositorioSqlTest.kt` — Integração (caso 9).
   - Use bancoEmMemoria().
   - ☐ teste: `saldo_e_historico_persistem`
   - ☐ teste: `contarNaData_so_conta_a_data_pedida`
17. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/di/AppContainer.kt` *(já existe — edite dentro do seu bloco)* — (já existe) Seu bloco 'INTEGRANTE 5 (PB16)'.
   - Adicione `descarteRepositorio` e `registrarDescarte` (usa relogio). Em AppNavegacao.kt, troque 'Meus pontos' e 'Registrar descarte' pelas Rotas.

**Pronto quando:**
- Todos os critérios de aceite têm teste verde (CI verde) e Pull Request aprovado.
- Os valores de pontos conferem com a tabela da RN08 (leia docs/ESCOPO.md).

### DOC-511 · Relatório 5.1.1 Elaboração da Aplicação Mobile
*Integrante 5 · Sprint 4 · 3 pontos*

**Objetivo:** Descrever o app que o grupo construiu: arquitetura, tecnologias, telas e funcionalidades, com prints.

**Passo a passo:**
1. Abra relatorio/etapa2/5.1.1-elaboracao-da-aplicacao.md (esqueleto com os tópicos).
2. Escreva a visão geral: o que o app faz, para quem e como o escopo (RF, RN) virou telas. Use docs/ETAPA2.md.
3. Explique a arquitetura em 1 parágrafo + 1 figura simples (telas → ViewModel → repositório → SQLite). Camadas: interface (Compose), ViewModel (MVVM), repositório, banco.
4. Liste as tecnologias com a versão e para que servem (Kotlin, Compose Multiplatform, SQLDelight, kotlinx-datetime, Gradle, GitHub Actions) — versões em gradle/libs.versions.toml.
5. Faça uma tabela funcionalidade × requisito × tela × responsável (Integrante N).
6. Insira os prints das telas (o Integrante 1 coleta no emulador/celular).
7. Fale das limitações assumidas: registro autodeclarado, dados de demonstração, lembrete (se não entrar), iOS só no simulador/CI.
8. Revisão: outro integrante lê. Sem inventar número.

### DOC-512 · Relatório 5.1.2 Elaboração dos Testes
*Integrante 5 · Sprint 4 · 3 pontos*

**Objetivo:** Mostrar que os testes existem, o que cada tipo cobre e o resultado.

**Passo a passo:**
1. Abra relatorio/etapa2/5.1.2-elaboracao-dos-testes.md.
2. Explique os tipos de teste usados: unitários (kotlin.test sobre JUnit), integração com banco em memória, comportamento com Mockito, interface (Compose/Espresso) e testes comuns executados no simulador iOS pelo CI.
3. Monte a tabela: funcionalidade × testes escritos × tipo (peça os números ao Integrante 1; contar com './gradlew testDebugUnitTest' e o relatório HTML).
4. Cole 1 trecho curto de um teste (não mais de 15 linhas) como exemplo e explique em palavras.
5. Inclua o print do CI verde (aba Actions) e do relatório de testes.
6. Cobertura: SOMENTE se o grupo medir (não invente porcentagem).
7. Cite a literatura do template (TDD, pirâmide de testes) com referência ABNT na seção 9.

### DOC-513 · Relatório 5.1.3 Validação do Projeto (com o Integrante 1)
*Integrante 5 · Sprint 4 · 2 pontos*

**Objetivo:** Registrar a validação feita pelo grupo (sem usuários externos) e justificar isso.

**Passo a passo:**
1. Abra relatorio/etapa2/5.1.3-validacao-do-projeto.md.
2. Integrante 1 entrega o roteiro executado (docs/VALIDACAO.md) com resultados: caso, resultado, data, quem executou (Integrante N).
3. Transcreva a tabela de resultados e os defeitos encontrados e corrigidos.
4. Escreva a justificativa: a validação foi feita pelo próprio grupo, em emulador e celular, sem usuários externos (decisão do grupo); o feedback de moradores fica como evolução. Não afirme que 'usuários validaram'.
5. Cite o que o template pede (testes funcionais, desempenho, feedback) e diga quais foram feitos: funcionais e desempenho simples (RNF04); feedback de usuários não.

### DOC-514 · Relatório 5.1.4 Ciclo de Desenvolvimento e Validação com TDD
*Integrante 5 · Sprint 4 · 3 pontos*

**Objetivo:** Provar o TDD com evidências do GitHub.

**Passo a passo:**
1. Abra relatorio/etapa2/5.1.4-ciclo-tdd.md.
2. Explique o ciclo vermelho → verde → refatorar com o exemplo de 1 funcionalidade (escolha com o Integrante 1, ex.: próxima coleta).
3. Cole a sequência de commits (hash curto, mensagem 'test:', 'feat:', 'refactor:') e os prints do CI vermelho e depois verde do mesmo PR.
4. Monte a tabela de evidências do PB13 (uma linha por PR): PR, commit do teste, commit do código, CI vermelho, CI verde.
5. Reflexão curta: o que o TDD mudou no trabalho do grupo (só o que realmente aconteceu).

### DOC-PDF · Montar o PDF da Etapa 2 e enviar
*Integrante 5 · Sprint 4 · 3 pontos*

**Objetivo:** Entregar tudo no prazo, no template.

**Passo a passo:**
1. Reúna as seções: 1 Introdução (atualizar), 5 completa, 6 Scrum (Integrante 1), 7 (arquivo do vídeo na aba de evidências; o professor pede o ARQUIVO, não o link), 8 Conclusão, 9 Referências.
2. Peça ao Claude para gerar o .docx a partir de relatorio/etapa2/ (python relatorio/build.py) e confira no Word: numeração, figuras, tabelas, ABNT.
3. Converter para PDF único, 1 arquivo, sem zip.
4. Integrante 1 confere o PDF contra a rubrica (docs/RUBRICA.md) e o template.
5. Enviar até a data oficial (confirmar com o professor); meta interna 2 dias antes.
6. Guardar uma cópia na pasta do grupo.

## Quando travar

1. Releia o comentário do arquivo e a receita citada (docs/RECEITAS.md).
2. Copie o erro inteiro e peça ajuda no grupo dizendo o que você já tentou.
3. O Integrante 1 leva o problema ao Claude se for técnico e mais de 1 dia parado.

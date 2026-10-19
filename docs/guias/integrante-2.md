# Integrante 2 · Desenvolvimento: local, calendário e a cara do app

> Sua bíblia da Etapa 2. Tudo que você precisa está aqui (e igual no Trello). Primeiro leia o Plano geral no HTML.

## Seu papel em 1 minuto

Escolher local, tela Início e identidade visual (nome, ícone, cores), calendário de coleta com feriados e, se sobrar tempo, o lembrete.

**Você faz:**
- PB07 Escolher município e setor
- PB07b Tema, ícone e tela Início
- PB08 Calendário com feriados
- PB12 Lembrete (opcional)

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

1. **Pegar o cartão.** No Trello, abra o seu cartão (coluna do seu Integrante, Etapa 2), leia o checklist e marque os itens conforme avança. Avise no grupo quando começar.
2. **Atualizar e criar a branch.** `git checkout main` · `git pull` · `git checkout -b feat/pbXX-resumo` (ex.: feat/pb08-calendario).
3. **Ler os arquivos-esqueleto.** No VS Code/Android Studio abra a pasta do seu cartão. Cada arquivo já existe, só com comentários: eles dizem o que escrever, passo a passo. Leia todos antes de começar.
4. **Trabalhar na ordem da lista.** A ordem dos arquivos no cartão é a ordem certa: modelos → testes da regra → regra → interface do repositório → Fake → teste do ViewModel → ViewModel → tela → teste de interface → SQL (só depois do PB06b) → repositório SQL → dados → ligar no container.
5. **Ciclo TDD em cada regra (vermelho → verde → refatorar).** (1) Escreva o teste do arquivo de teste e rode: tem que FALHAR. `git commit -m "test: ..."`. (2) Escreva o código mínimo para passar. `git commit -m "feat: ..."`. (3) Melhore o código sem quebrar o teste. `git commit -m "refactor: ..."`. Dê push depois de cada commit: o CI mostra vermelho e depois verde (é a nossa evidência).
6. **Rodar tudo local.** `.\gradlew.bat testDebugUnitTest` (PowerShell) ou `./gradlew testDebugUnitTest`. Para só um teste: `.\gradlew.bat testDebugUnitTest --tests "*NomeDoTeste"`.
7. **Abrir o Pull Request.** `git push -u origin feat/pbXX-resumo` e abra o PR no GitHub. Preencha o modelo: o que mudou, qual cartão, como conferir, checklist. Mova o cartão para a coluna 'Revisão' do Trello.
8. **Revisão e merge.** O Integrante 1 revisa (até 24 h). Corrija o que pedir. Com o CI verde e a aprovação, ele faz o merge.
9. **Fechar o cartão.** Depois do merge, mova o cartão para 'Concluído' no Trello e escreva 1 linha no grupo: 'PBxx mergeado'.

## Seus cartões

### PB07 · Escolher município e setor
*Integrante 2 · Sprint 2 · 3 pontos · Requisitos: RF01, RN06, RN07, RNF05, CU01*

**História:** Como morador, quero escolher meu município e setor de coleta entre os cadastrados, para o app mostrar as informações do meu local.

**Objetivo:** O usuário escolhe um município, depois um setor (bairro) daquele município, e o app guarda a escolha no aparelho. Os municípios e setores vêm do banco (dados de demonstração), nunca do código da tela. PRIMEIRO PR pequeno (até qua 28/10): Local.kt, LocalRepositorio.kt e LocalRepositorioFake.kt, porque os Integrantes 3 e o seu próprio Início/Calendário dependem deles nos testes.

**Regras (docs/ESCOPO.md):**
- RN06: só existem municípios e setores cadastrados.
- RN07: a escolha fica só no aparelho (a tela avisa isso).
- RNF05: cadastrar um município novo = só inserir dados.

**Critérios de aceite = os testes que você vai escrever:**

| # | Critério | Dado | Quando | Então |
|---|---|---|---|---|
| 1 | Lista de municípios | 2 municípios cadastrados (Itajaí e Camboriú) | a tela abre | os 2 aparecem em ordem alfabética |
| 2 | Setores do município | o município Camboriú escolhido | a lista de setores é pedida | aparecem só os setores de Camboriú |
| 3 | Confirmar escolha | município e setor escolhidos | o usuário confirma | a escolha é salva e a tela fecha (concluído) |
| 4 | Escolha persiste | um setor escolhido e salvo | o repositório é consultado de novo (app reaberto) | devolve o mesmo setor e município |
| 5 | Nada cadastrado | nenhum município cadastrado | a tela abre | mostra 'Nenhum município cadastrado' (RN06) |
| 6 | Município novo sem mudar código | um município novo inserido pelo repositório | a lista é pedida | ele aparece (RNF05) |

**Arquivos deste cartão, na ordem certa de trabalho:**

1. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/local/Local.kt` — Modelos Municipio e Setor.
   - Crie a data class `Municipio` com id (Long), nome (String) e uf (String).
   - Crie a data class `Setor` com id (Long), municipioId (Long) e nome (String).
   - Crie `SetorEscolhido` com o setor, o nome do município e o nome do setor (para a tela inicial mostrar 'Município · Setor').
   - Receita: R1.
2. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/local/LocalRepositorio.kt` — Contrato (interface) do repositório de local.
   - Crie a interface `LocalRepositorio` com funções suspend: listarMunicipios(), listarSetores(municipioId), escolherSetor(setorId), setorEscolhido() (devolve SetorEscolhido ou nulo) e cadastrarMunicipio(nome, uf) (usada no teste de RNF05).
   - Só a interface: nada de SQL aqui. Receita: R2.
3. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/local/LocalRepositorioFake.kt` — Repositório falso, em memória, para testar o ViewModel sem banco.
   - Implemente `LocalRepositorioFake` guardando listas na memória (receita R2). Aceite uma lista inicial de municípios e setores no construtor.
   - Este arquivo também é usado pelo teste do Início (PB07b) e do Calendário (PB08).
4. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/local/EscolherLocalViewModelTest.kt` — Testes do ViewModel (casos 1, 2, 3 e 5). ESCREVA ANTES do ViewModel.
   - Passo 1: escreva os 4 testes abaixo com o Fake. Rode: todos falham (vermelho). Commit 'test: ...'.
   - Passo 2: só depois escreva o ViewModel (verde). Commit 'feat: ...'.
   - Lembre de Dispatchers.setMain / resetMain (receita R3).
   - ☐ teste: `lista_os_municipios_em_ordem_alfabetica`
   - ☐ teste: `ao_escolher_municipio_mostra_so_os_setores_dele`
   - ☐ teste: `ao_confirmar_salva_a_escolha_e_marca_concluido`
   - ☐ teste: `sem_municipios_mostra_mensagem_de_lista_vazia`
5. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/local/EscolherLocalViewModel.kt` — Estado e ações da tela de escolha.
   - Crie `EscolherLocalEstado` com: carregando, municipios, municipioSelecionado, setores, setorSelecionado, concluido, semMunicipios.
   - Crie `EscolherLocalViewModel(repositorio: LocalRepositorio)` com StateFlow `estado` (receita R3).
   - Ações: escolherMunicipio(id) (carrega os setores dele), escolherSetor(id), confirmar() (chama repositorio.escolherSetor e marca concluido).
   - Ao iniciar, carregue os municípios; se vierem vazios, marque semMunicipios.
6. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/local/EscolherLocalTela.kt` — Tela (Compose) de escolha de local.
   - Crie `EscolherLocalRota(navegador)`: pega o container (LocalContainer.current), cria o ViewModel com viewModel { ... } e coleta o estado (receita R4).
   - Crie `EscolherLocalTela(estado, ...)` SEM ViewModel dentro (só recebe estado e funções): assim dá para testar.
   - Conteúdo: título 'Escolha seu local', lista de municípios (clicável), depois lista de setores, botão 'Confirmar' (ativo só com setor escolhido) e o texto 'Sua escolha fica só no aparelho.' (RN07). Se semMunicipios: 'Nenhum município cadastrado'.
   - Quando estado.concluido ficar verdadeiro, volte ao Início: navegador.trocarPara(Rota.Inicio).
7. `composeApp/src/androidInstrumentedTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/local/EscolherLocalTelaTest.kt` — Teste de interface (Compose/Espresso) da tela.
   - Escreva 1 teste: renderiza EscolherLocalTela com estado de exemplo e confere que o texto 'Escolha seu local' e o nome de um município aparecem (receita R5).
   - Roda no CI (job 'Testes de interface') ou no emulador do Android Studio.
   - ☐ teste: `mostra_titulo_e_municipios`
8. `composeApp/src/commonMain/sqldelight/br/edu/univali/coletaseletiva/dados/local.sq` — Tabelas de municípios, setores e escolha do usuário.
   - Tabela `municipio`: id (inteiro, chave primária), nome (texto), uf (texto).
   - Tabela `setor`: id (inteiro, chave primária), municipio_id (inteiro, referencia municipio), nome (texto).
   - Tabela `escolha_local`: guarda UMA linha só (id fixo 1) com setor_id. Dica: use 'INSERT OR REPLACE' para trocar a escolha.
   - Consultas (com nome): listarMunicipios (ordem alfabética), listarSetoresDoMunicipio(municipio_id), buscarSetorEscolhido (junta escolha_local + setor + municipio), salvarEscolha, inserirMunicipio, inserirSetor, contarMunicipios.
   - Receita: R6. Só escreva este arquivo DEPOIS do PB06b mergeado (precisa do Banco).
9. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/local/LocalRepositorioSql.kt` — Implementação do repositório com SQLite.
   - Crie `LocalRepositorioSql(banco: Banco) : LocalRepositorio`. Use withContext(Dispatchers.Default) em cada função (receita R7).
   - Converta as linhas do banco para Municipio/Setor com a lambda do SQLDelight (os campos viram parâmetros).
   - Crie também `semearSeVazio()`: se contarMunicipios = 0, insere os dados de DadosLocalDemo.
10. `composeApp/src/androidUnitTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/local/LocalRepositorioSqlTest.kt` — Testes de integração com banco em memória (casos 4 e 6).
   - Use bancoEmMemoria() (do PB06b). Escreva o teste ANTES do repositório SQL (vermelho → verde).
   - ☐ teste: `escolha_salva_e_lida_de_novo_devolve_o_mesmo_setor`
   - ☐ teste: `municipio_novo_cadastrado_aparece_na_lista_sem_mudar_codigo`
   - ☐ teste: `semear_so_insere_quando_o_banco_esta_vazio`
11. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/local/DadosLocalDemo.kt` — Dados de demonstração (fictícios) de municípios e setores.
   - Ids FIXOS (os outros cartões dependem deles): Municípios: 1 = Itajaí/SC, 2 = Camboriú/SC. Setores: 1 Centro (Itajaí), 2 São Vicente (Itajaí), 3 Fazenda (Itajaí), 4 Centro (Camboriú), 5 Monte Alegre (Camboriú), 6 Tabuleiro (Camboriú).
   - Crie as listas e constantes com esses ids. Escreva um comentário curto: 'dados de demonstração'.
   - NÃO invente horários. Só nomes e ids (os dias de coleta ficam no PB08).
12. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/di/AppContainer.kt` *(já existe — edite dentro do seu bloco)* — (já existe) Dentro do bloco 'INTEGRANTE 2 (PB07, PB08)'.
   - Em AppContainer.kt, no seu bloco 'INTEGRANTE 2 (PB07, PB08)': adicione `localRepositorio` (by lazy: LocalRepositorioSql(banco)) — receita R9.
   - Em `iniciar()`, no seu bloco de dados de demonstração, chame localRepositorio.semearSeVazio() — receita R8.
   - Em AppNavegacao.kt, troque no seu bloco 'Escolher local' a TelaEmConstrucao por EscolherLocalRota(navegador).

**Pronto quando:**
- Todos os critérios de aceite têm teste verde (CI verde) e Pull Request aprovado.
- Município e setor escolhidos continuam salvos depois de fechar e abrir o app (teste manual no celular/emulador).

### PB07b · Identidade do app e tela Início
*Integrante 2 · Sprint 2 · 2 pontos · Requisitos: RNF01, D-04 (dados de demonstração identificados)*

**História:** Como morador, quero uma tela inicial com o meu local e atalhos, para chegar a calendário, pontos e guia em até 2 toques.

**Objetivo:** A parte mais visual do app: nome, ícone, cores (Tema) e a tela Início com os atalhos. É a 'bobeira' do Integrante 2: aqui o grupo decide a cara do app.

**Regras (docs/ESCOPO.md):**
- RNF01: calendário, pontos e guia em no máximo 2 toques a partir do Início.
- D-04: a tela deve dizer que os dados são de demonstração.

**Critérios de aceite = os testes que você vai escrever:**

| # | Critério | Dado | Quando | Então |
|---|---|---|---|---|
| 1 | Local no topo | um setor escolhido (Centro de Itajaí) | o Início abre | mostra 'Itajaí · Centro' |
| 2 | Sem local | nenhum setor escolhido | o Início abre | mostra o botão 'Escolher local' em destaque |
| 3 | Atalhos | o Início aberto | olho os botões | existem: Calendário, Pontos de descarte, Guia de separação, Meus pontos e Dicas (1 toque cada) |
| 4 | Aviso de demonstração | o Início aberto | olho o rodapé | o texto 'Dados de demonstração' aparece |

**Arquivos deste cartão, na ordem certa de trabalho:**

1. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/inicio/Tema.kt` — Cores e tipografia do app.
   - Escolham no grupo: nome do app (hoje 'Coleta Seletiva Vale') e uma cor principal (verde é o natural para coleta seletiva).
   - Crie `Tema(content)`: um Composable que envolve MaterialTheme com colorScheme (lightColorScheme com primary, secondary, background) — receita R4.
   - Em App.kt, troque MaterialTheme por Tema.
   - Nome do app: edite 'android:label' no AndroidManifest.xml. Ícone: Android Studio > clique direito em 'res' > New > Image Asset (gera tudo sozinho).
2. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/inicio/InicioViewModelTest.kt` — Testes do ViewModel do Início (casos 1 e 2). ESCREVA ANTES.
   - Use o LocalRepositorioFake do PB07 (já existe).
   - ☐ teste: `com_setor_escolhido_mostra_municipio_e_setor`
   - ☐ teste: `sem_setor_escolhido_marca_precisaEscolherLocal`
3. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/inicio/InicioViewModel.kt` — Estado do Início.
   - Crie `InicioEstado` com: carregando, local (texto 'Município · Setor' ou nulo), precisaEscolherLocal.
   - Crie `InicioViewModel(local: LocalRepositorio)` (receita R3): ao iniciar, lê repositorio.setorEscolhido() e monta o estado.
4. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/inicio/InicioTela.kt` — Tela Início.
   - Crie `InicioRota(navegador)` e `InicioTela(estado, onIr: (Rota) -> Unit)` (R4).
   - Mostra o local no topo (ou o botão 'Escolher local' → Rota.EscolherLocal), os 5 atalhos (cada um chama navegador.ir com a Rota certa) e o texto 'Dados de demonstração' no rodapé.
5. `composeApp/src/androidInstrumentedTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/inicio/InicioTelaTest.kt` — Teste de interface do Início.
   - 1 teste: renderiza InicioTela com um estado e confere que os 5 atalhos e o aviso de demonstração aparecem (R5).
   - ☐ teste: `mostra_os_cinco_atalhos_e_o_aviso_de_demonstracao`
6. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/navegacao/AppNavegacao.kt` *(já existe — edite dentro do seu bloco)* — (já existe) Seu bloco 'Início'.
   - Troque TelaEmConstrucao por InicioRota(navegador) no bloco do Início.

**Pronto quando:**
- Todos os critérios de aceite têm teste verde (CI verde) e Pull Request aprovado.
- O grupo viu o app com a cara nova (print do Início para o relatório 5.1.1).

### PB08 · Calendário de coleta com feriados
*Integrante 2 · Sprint 3 · 5 pontos · Requisitos: RF03, RN01, RN02, RN03, CU02*

**História:** Como morador, quero ver os dias da coleta seletiva e da coleta comum do meu setor e quando será a próxima, para colocar o lixo no dia certo.

**Objetivo:** Mostrar, para o setor escolhido, os dias da semana de cada tipo de coleta e a data da PRÓXIMA coleta de cada tipo, considerando feriados nacionais (a coleta passa para o próximo dia útil). Não existe horário fixo de passagem (RN01/RN02).

**Regras (docs/ESCOPO.md):**
- RN01/RN02: dias vêm do cadastro do setor; sem horário.
- RN03: feriado nacional = sem coleta; passa para o próximo dia útil (segunda a sexta que não seja feriado).
- Feriados nacionais usados: 1/1, 21/4, 1/5, 7/9, 12/10, 2/11, 15/11, 20/11, 25/12 e Sexta-feira Santa (3/4/2026 e 26/3/2027).

**Critérios de aceite = os testes que você vai escrever:**

| # | Critério | Dado | Quando | Então |
|---|---|---|---|---|
| 1 | Dia normal | setor com coleta às segundas; hoje é sexta 23/10/2026 | calculo a próxima coleta | é segunda 26/10/2026 |
| 2 | Hoje é dia de coleta | setor com coleta às segundas; hoje é segunda 26/10/2026 | calculo a próxima coleta | é hoje, 26/10/2026 |
| 3 | Feriado empurra | setor com coleta às segundas; hoje é sexta 04/09/2026 (segunda 07/09 é feriado) | calculo a próxima coleta | é terça 08/09/2026 |
| 4 | Sexta-feira Santa | setor com coleta às sextas; hoje é segunda 30/03/2026 | calculo a próxima coleta | é segunda 06/04/2026 (a sexta 03/04 é feriado) |
| 5 | Dois dias na semana | setor com coleta segunda e quinta; hoje é terça 27/10/2026 | calculo a próxima coleta | é quinta 29/10/2026 |
| 6 | Feriados reconhecidos | as 9 datas fixas de 2026 e a Sexta-feira Santa | pergunto se são feriado | todas respondem sim; 06/10/2026 responde não |
| 7 | Tela mostra os dois tipos | setor escolhido com coleta seletiva e comum cadastradas | a tela abre | mostra, para cada tipo, os dias da semana e a próxima data |
| 8 | Sem local | nenhum setor escolhido | a tela abre | pede para escolher o local primeiro |
| 9 | Dados do setor | dias cadastrados no banco para o setor 5 | o repositório é consultado | devolve exatamente os dias cadastrados (sem horário) |

**Arquivos deste cartão, na ordem certa de trabalho:**

1. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/Coleta.kt` — Modelos da coleta.
   - Crie o enum `TipoColeta` com SELETIVA e COMUM.
   - Crie a data class `ColetaDoSetor` com tipo (TipoColeta) e diasDaSemana (Set de DayOfWeek do kotlinx-datetime). Receitas R1 e R11.
2. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/FeriadosTest.kt` — Testes dos feriados nacionais (caso 6). ESCREVA ANTES.
   - Primeiro teste: passe uma data (LocalDate) e confira o booleano. Use as datas de 2026 e 2027.
   - ☐ teste: `datas_fixas_de_2026_sao_feriado: 1/1, 21/4, 1/5, 7/9, 12/10, 2/11, 15/11, 20/11, 25/12`
   - ☐ teste: `sexta_feira_santa_2026_e_2027_sao_feriado: 3/4/2026 e 26/3/2027`
   - ☐ teste: `dia_comum_nao_e_feriado: 6/10/2026`
3. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/Feriados.kt` — Regra RN03: quais datas são feriado nacional.
   - Crie a função `ehFeriadoNacional(data: LocalDate): Boolean`.
   - Guarde as datas fixas (mês e dia) em um conjunto e a Sexta-feira Santa de 2026 e 2027 em outro conjunto de LocalDate (a Páscoa muda todo ano; só precisamos de 2026 e 2027 e vamos escrever isso num comentário).
   - Receita: R1 e R11.
4. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/ProximaColetaTest.kt` — Testes da regra principal (casos 1 a 5). ESCREVA ANTES.
   - Um teste por linha abaixo. Use LocalDate(2026, 10, 23) etc. e assertEquals.
   - ☐ teste: `dia_normal_proxima_e_a_segunda`
   - ☐ teste: `hoje_e_dia_de_coleta_vale_hoje`
   - ☐ teste: `feriado_empurra_para_o_proximo_dia_util`
   - ☐ teste: `sexta_feira_santa_empurra_para_segunda`
   - ☐ teste: `dois_dias_na_semana_escolhe_o_mais_proximo`
5. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/ProximaColeta.kt` — Regra RN01 a RN03: a próxima coleta.
   - Crie `proximaColeta(hoje: LocalDate, diasDaSemana: Set<DayOfWeek>): LocalDate`.
   - Ideia em passos: (1) comece em 'hoje'; (2) ande dia a dia até achar um dia cujo dayOfWeek esteja no conjunto; (3) esse é o dia programado; (4) se o dia programado for feriado nacional, ande até o próximo dia útil (não sábado, não domingo, não feriado); (5) devolva essa data.
   - Atenção ao caso 'hoje é feriado e é dia de coleta': a coleta passa para o próximo dia útil (receita R11).
6. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/CalendarioRepositorio.kt` — Contrato do repositório do calendário.
   - Interface `CalendarioRepositorio` com suspend `coletasDoSetor(setorId: Long): List<ColetaDoSetor>`. Receita R2.
7. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/CalendarioRepositorioFake.kt` — Fake do calendário.
   - Guarda um mapa setorId → lista de ColetaDoSetor (R2).
8. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/CalendarioViewModelTest.kt` — Testes do ViewModel (casos 7 e 8). ESCREVA ANTES.
   - Use o RelogioFixo (já existe em util) para fixar 'hoje', o LocalRepositorioFake e o CalendarioRepositorioFake.
   - ☐ teste: `mostra_dias_e_proxima_data_dos_dois_tipos`
   - ☐ teste: `sem_local_escolhido_pede_para_escolher`
9. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/CalendarioViewModel.kt` — Estado da tela de calendário.
   - `CalendarioEstado`: carregando, semLocal, local (texto), itens (lista com tipo, diasDaSemana, proximaData).
   - `CalendarioViewModel(local: LocalRepositorio, calendario: CalendarioRepositorio, relogio: Relogio)`: lê o setor escolhido; se não houver, semLocal = true; senão pede as coletas e usa proximaColeta(relogio.hoje(), dias) para cada tipo (R3).
10. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/CalendarioTela.kt` — Tela de calendário.
   - `CalendarioRota(navegador)` e `CalendarioTela(estado, onVoltar, onEscolherLocal)` (R4).
   - Mostra 'Coleta seletiva' e 'Coleta comum': dias da semana por extenso em português (Segunda-feira...), 'Próxima coleta: dd/MM/aaaa' e o texto 'Não há horário fixo de passagem do caminhão.' (RN01).
   - Se semLocal: texto 'Escolha seu local' e botão para a tela de escolha.
11. `composeApp/src/androidInstrumentedTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/CalendarioTelaTest.kt` — Teste de interface do calendário.
   - 1 teste: renderiza CalendarioTela com estado de exemplo e confere 'Coleta seletiva', 'Coleta comum' e a data da próxima coleta (R5).
   - ☐ teste: `mostra_os_dois_tipos_e_a_proxima_data`
12. `composeApp/src/commonMain/sqldelight/br/edu/univali/coletaseletiva/dados/calendario.sq` — Dias de coleta por setor.
   - Tabela `coleta_dia`: setor_id (inteiro), tipo (texto: 'SELETIVA' ou 'COMUM'), dia_semana (inteiro 1 a 7, com 1 = segunda). Chave primária composta pelas 3 colunas.
   - Consultas: listarColetasDoSetor(setor_id), inserirColeta(setor_id, tipo, dia_semana), contarColetas.
   - Receita R6. Faça só depois do PB07 (setores existem).
13. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/CalendarioRepositorioSql.kt` — Repositório SQL do calendário.
   - Converta dia_semana (1 a 7) em DayOfWeek e agrupe por tipo para montar a lista de ColetaDoSetor (R7).
   - Crie `semearSeVazio()` com os dados de DadosCalendarioDemo.
14. `composeApp/src/androidUnitTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/CalendarioRepositorioSqlTest.kt` — Teste de integração (caso 9).
   - Insira dias para o setor 5 e confira que a leitura devolve os mesmos dias.
   - ☐ teste: `devolve_exatamente_os_dias_cadastrados_do_setor`
15. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/calendario/DadosCalendarioDemo.kt` — Dias de coleta de demonstração (fictícios).
   - Para cada um dos 6 setores (ids no DadosLocalDemo), defina 1 dia de coleta seletiva e 1 ou 2 dias de coleta comum, entre segunda e sexta. São dados de demonstração: escreva isso no comentário. NÃO coloque horário.
16. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/di/AppContainer.kt` *(já existe — edite dentro do seu bloco)* — (já existe) Seu bloco 'INTEGRANTE 2'.
   - Em AppContainer.kt, no seu bloco, adicione `calendarioRepositorio` (by lazy) e, em `iniciar()`, chame calendarioRepositorio.semearSeVazio() DEPOIS do semear do local (R8, R9).
   - Em AppNavegacao.kt, troque o bloco do Calendário por CalendarioRota(navegador).

**Pronto quando:**
- Todos os critérios de aceite têm teste verde (CI verde) e Pull Request aprovado.
- Calendário confere à mão com 3 datas escolhidas pelo grupo (inclua uma perto de feriado).

### PB12 · Lembrete na véspera da coleta (opcional)
*Integrante 2 · Sprint 4 · 5 pontos · Requisitos: RF02, CU05*

**História:** Como morador, quero ser lembrado na véspera da coleta, em um horário que eu escolho.

**Objetivo:** Primeiro item a SAIR se faltar tempo (risco R-03). Parte A (obrigatória se o card for feito): a regra de quando avisar, testada. Parte B (só com ajuda): agendar a notificação de verdade no Android/iOS.

**Regras (docs/ESCOPO.md):**
- O lembrete é local (sem servidor).
- Véspera = o dia anterior à PRÓXIMA coleta (já ajustada por feriado, PB08).

**Critérios de aceite = os testes que você vai escrever:**

| # | Critério | Dado | Quando | Então |
|---|---|---|---|---|
| 1 | Véspera no horário | próxima coleta na segunda 26/10 e horário 19:00 | calculo o lembrete | é domingo 25/10 às 19:00 |
| 2 | Feriado respeitado | próxima coleta ajustada para terça 08/09 | calculo o lembrete | é segunda 07/09 (véspera da data ajustada) |
| 3 | Ativar e desativar | lembrete ativado com 19:00 | o usuário desativa | o agendador recebe 'cancelar' e a config fica desativada |
| 4 | Agendador chamado | lembrete ativado | o usuário salva | o agendador recebe 1 pedido de agendamento com a data certa (teste com Mockito) |

**Arquivos deste cartão, na ordem certa de trabalho:**

1. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/lembrete/DataDoLembrete.kt` — Regra: quando avisar.
   - Crie `dataDoLembrete(proximaColeta: LocalDate, horario: LocalTime): LocalDateTime` = véspera da coleta no horário escolhido (R11).
2. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/lembrete/DataDoLembreteTest.kt` — Testes dos casos 1 e 2. ESCREVA ANTES.
   - Um teste por caso.
   - ☐ teste: `vespera_no_horario_escolhido`
   - ☐ teste: `feriado_ja_ajustado_pela_proximaColeta`
3. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/lembrete/AgendadorDeLembrete.kt` — Contrato do agendador de notificações.
   - Interface `AgendadorDeLembrete` com `agendar(quando: LocalDateTime)` e `cancelar()`. Só a interface (cada plataforma implementa depois).
4. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/lembrete/LembreteViewModel.kt` — Estado e ações da tela de lembrete.
   - `LembreteEstado`: ativo, horario. `LembreteViewModel(agendador, calendario, local, relogio)` com ativar(horario), desativar() (R3).
5. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/lembrete/LembreteViewModelTest.kt` — Testes dos casos 3 e 4. ESCREVA ANTES.
   - Caso 3 com um AgendadorFalso escrito por você; caso 4 com Mockito (receita R10).
   - ☐ teste: `desativar_cancela_o_agendamento`
   - ☐ teste: `salvar_chama_o_agendador_uma_vez_com_a_data_certa`
6. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/lembrete/LembreteTela.kt` — Tela do lembrete.
   - `LembreteRota` e `LembreteTela(estado, ...)`: interruptor 'Lembrar na véspera' e seletor de horário (pode ser uma lista simples de horários).
7. `composeApp/src/androidMain/kotlin/br/edu/univali/coletaseletiva/lembrete/AgendadorAndroid.kt` — PARTE B (Android): notificação de verdade.
   - Só depois da Parte A pronta e com ajuda do Integrante 1 e do Claude.
   - Passos de alto nível: criar canal de notificação; pedir a permissão POST_NOTIFICATIONS (Android 13+); agendar com AlarmManager para a data/hora; receber com um BroadcastReceiver que mostra a notificação; declarar tudo no AndroidManifest.
   - Não aprofundamos aqui: se não der tempo, o relatório diz 'lembrete implementado como regra testada; agendamento no aparelho fica como evolução'.
8. `composeApp/src/iosMain/kotlin/br/edu/univali/coletaseletiva/lembrete/AgendadorIos.kt` — PARTE B (iOS): fica por último.
   - Só se o Android estiver pronto. Usa o UNUserNotificationCenter via Kotlin/Native. Pode ficar como evolução futura (registrar no relatório).

**Pronto quando:**
- Todos os critérios de aceite têm teste verde (CI verde) e Pull Request aprovado.
- Parte A com testes verdes. Parte B só se o grupo decidir na Review da Sprint 3.

## Quando travar

1. Releia o comentário do arquivo e a receita citada (docs/RECEITAS.md).
2. Copie o erro inteiro e peça ajuda no grupo dizendo o que você já tentou.
3. O Integrante 1 leva o problema ao Claude se for técnico e mais de 1 dia parado.

# Integrante 3 · Desenvolvimento: pontos de descarte e dicas

> Sua bíblia da Etapa 2. Tudo que você precisa está aqui (e igual no Trello). Primeiro leia o Plano geral no HTML.

## Seu papel em 1 minuto

Pontos de descarte com filtro por material, detalhe com rota no app de mapas e as dicas de reciclagem (conteúdo).

**Você faz:**
- PB09 Pontos de descarte e filtro (inclui o enum Material)
- PB10 Detalhe do ponto e rota
- PB17 Dicas de reciclagem

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

### PB09 · Pontos de descarte com filtro por material
*Integrante 3 · Sprint 2 · 8 pontos · Requisitos: RF04, RF06, RF07, RN04, RN05, RNF04, CU03*

**História:** Como morador, quero ver os pontos de descarte do meu município e filtrar por tipo de material, para saber onde levar pilhas, óleo, eletrônicos e roupas.

**Objetivo:** Lista de pontos do município escolhido (nome, endereço, horário, materiais aceitos com limites) e filtro por material. Mostrar aviso quando o filtro for pilhas/baterias ou óleo (RN04).

**Regras (docs/ESCOPO.md):**
- RN04: pilhas, baterias e óleo de cozinha só vão a pontos de descarte ou comércio, nunca na coleta porta a porta (aviso na tela).
- RN05: cada ponto mostra horário e materiais aceitos, com limites quando houver (ex.: eletrônicos até 5 kg).
- RNF04: a lista aparece em até 3 s com dados locais.

**Critérios de aceite = os testes que você vai escrever:**

| # | Critério | Dado | Quando | Então |
|---|---|---|---|---|
| 1 | Material existe | o enum Material | listo os valores | existem PAPEL, PLASTICO, METAL, VIDRO, PILHAS_BATERIAS, OLEO, ELETRONICOS e ROUPAS, cada um com nome para exibir em português |
| 2 | Filtro | 3 pontos (A aceita pilhas, B aceita óleo, C aceita pilhas e roupas) | filtro por PILHAS_BATERIAS | retorna A e C, em ordem alfabética |
| 3 | Sem filtro | 3 pontos | filtro nulo (Todos) | retorna os 3 |
| 4 | Do município | pontos de 2 municípios, local escolhido no município 2 | a lista é carregada | só aparecem os pontos do município 2 |
| 5 | Aviso RN04 | filtro em PILHAS_BATERIAS ou OLEO | o estado é montado | avisoRN04 é verdadeiro; para outros materiais é falso |
| 6 | Dados do ponto | um ponto com horário e materiais com limite | a tela mostra | aparecem endereço, horário e 'Eletrônicos (até 5 kg)' |
| 7 | Município novo | ponto novo inserido pelo repositório | a lista é pedida | ele aparece sem mudar código (RNF05) |
| 8 | Desempenho | 200 pontos cadastrados | carrego a lista | leva menos de 3 segundos (RNF04) |

**Arquivos deste cartão, na ordem certa de trabalho:**

1. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/Material.kt` — O enum Material (usado por 3 funcionalidades). FAÇA PRIMEIRO, num PR pequeno separado.
   - Crie o enum `Material` com: PAPEL, PLASTICO, METAL, VIDRO, PILHAS_BATERIAS, OLEO, ELETRONICOS, ROUPAS e uma propriedade `nomeExibicao` em português (ex.: 'Pilhas e baterias').
   - Abra um PR SÓ com este arquivo e o teste dele e peça revisão no mesmo dia: o Integrante 5 precisa dele no PB16. Meta: mergeado até qua 28/10.
   - Escreva o teste (casos 'Material existe') antes. Receita R1.
2. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/MaterialTest.kt` — Teste do enum.
   - Escreva antes do enum.
   - ☐ teste: `todos_os_oito_materiais_existem_com_nome_para_exibir`
3. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontoDeDescarte.kt` — Modelos do ponto de descarte.
   - `MaterialAceito`: material (Material) e limite (String? — texto como 'até 5 kg', pode ser nulo).
   - `PontoDeDescarte`: id, municipioId, nome, endereco, horario (texto livre, ex.: 'Seg a sex, 8h às 17h'), latitude (Double), longitude (Double), materiais (lista de MaterialAceito).
4. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/FiltroDePontosTest.kt` — Testes do filtro (casos 2 e 3). ESCREVA ANTES.
   - Monte 3 pontos de exemplo dentro do teste.
   - ☐ teste: `filtra_por_pilhas_e_ordena_por_nome`
   - ☐ teste: `sem_filtro_devolve_todos`
5. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/FiltroDePontos.kt` — Regra RF07: filtrar e ordenar.
   - Crie `filtrarPontos(pontos: List<PontoDeDescarte>, material: Material?): List<PontoDeDescarte>` — se material é nulo, devolve todos; senão, só os que aceitam aquele material; sempre em ordem alfabética pelo nome.
6. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontosRepositorio.kt` — Contrato do repositório de pontos.
   - Interface `PontosRepositorio` com suspend: `pontosDoMunicipio(municipioId)`, `ponto(id)` (usada no PB10) e `cadastrar(ponto)` (usada no teste de RNF05).
7. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontosRepositorioFake.kt` — Fake de pontos.
   - Lista em memória (R2).
8. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontosViewModelTest.kt` — Testes do ViewModel (casos 4 e 5). ESCREVA ANTES.
   - Use LocalRepositorioFake (PB07) para dizer qual município está escolhido.
   - ☐ teste: `mostra_so_os_pontos_do_municipio_escolhido`
   - ☐ teste: `aviso_rn04_ligado_so_para_pilhas_baterias_e_oleo`
   - ☐ teste: `trocar_filtro_atualiza_a_lista`
9. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontosViewModel.kt` — Estado da lista de pontos.
   - `PontosEstado`: carregando, semLocal, pontosFiltrados, filtro (Material?), avisoRN04 (Boolean).
   - `PontosViewModel(local, pontos)`: carrega os pontos do município do setor escolhido; `filtrarPor(material: Material?)` atualiza a lista com filtrarPontos (R3).
10. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontosTela.kt` — Tela da lista de pontos.
   - `PontosRota` e `PontosTela(estado, onFiltrar, onAbrirPonto, onVoltar)` (R4).
   - Linha de 'chips' (FilterChip) com Todos + um por Material; lista (LazyColumn) com nome, endereço, horário e materiais (com limite). Aviso RN04 em destaque quando avisoRN04: 'Pilhas, baterias e óleo de cozinha: leve apenas a pontos de descarte, nunca na coleta comum.'
   - Toque no ponto → navegador.ir(Rota.PontoDetalhe(id)) (PB10).
11. `composeApp/src/androidInstrumentedTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontosTelaTest.kt` — Teste de interface.
   - 1 teste: renderiza com estado de exemplo e confere nome do ponto e chip 'Todos' (R5).
   - ☐ teste: `mostra_chips_e_um_ponto`
12. `composeApp/src/commonMain/sqldelight/br/edu/univali/coletaseletiva/dados/pontos.sq` — Tabelas de pontos e materiais aceitos.
   - `ponto_descarte`: id (chave), municipio_id, nome, endereco, horario, latitude (real), longitude (real).
   - `ponto_material`: ponto_id, material (texto = nome do enum), limite (texto, pode ser nulo). Chave: ponto_id + material.
   - Consultas: listarPontosDoMunicipio(municipio_id), buscarPonto(id), listarMateriaisDoPonto(ponto_id), inserirPonto, inserirMaterialDoPonto, contarPontos.
   - Receita R6.
13. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontosRepositorioSql.kt` — Repositório SQL de pontos.
   - Junte pontos e materiais (duas consultas) em PontoDeDescarte (R7). `semearSeVazio()` com DadosPontosDemo.
14. `composeApp/src/androidUnitTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontosRepositorioSqlTest.kt` — Integração (casos 6, 7 e 8).
   - Para o caso 8, insira 200 pontos e use measureTime (kotlin.time) para conferir < 3 s.
   - ☐ teste: `ponto_salvo_volta_com_horario_e_materiais_com_limite`
   - ☐ teste: `ponto_novo_cadastrado_aparece_sem_mudar_codigo`
   - ☐ teste: `duzentos_pontos_carregam_em_menos_de_3_segundos`
15. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/DadosPontosDemo.kt` — Pontos de demonstração (fictícios).
   - Pelo menos 6 pontos (3 em Itajaí = município 1, 3 em Camboriú = município 2), com coordenadas aproximadas da região (pode usar o Google Maps para pegar), horários e materiais variados: cada um dos 4 tipos (pilhas, óleo, eletrônicos, roupas) deve aparecer em pelo menos 2 pontos.
   - Ao menos um ponto com limite (ex.: eletrônicos até 5 kg, RN05). Comentário: 'dados de demonstração, fictícios'.
16. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/di/AppContainer.kt` *(já existe — edite dentro do seu bloco)* — (já existe) Seu bloco 'INTEGRANTE 3'.
   - Em AppContainer.kt, no seu bloco, adicione `pontosRepositorio` (by lazy) e, em `iniciar()`, chame pontosRepositorio.semearSeVazio() (R8, R9).
   - Em AppNavegacao.kt, troque o bloco 'Pontos de descarte' por PontosRota(navegador).

**Pronto quando:**
- Todos os critérios de aceite têm teste verde (CI verde) e Pull Request aprovado.
- Filtros testados à mão com os 4 tipos principais no celular.

### PB10 · Detalhe do ponto e rota no app de mapas
*Integrante 3 · Sprint 3 · 3 pontos · Requisitos: RF05, CU03*

**História:** Como morador, quero ver onde fica o ponto de descarte e abrir a rota no app de mapas.

**Objetivo:** Tela de detalhe do ponto com endereço, horário, materiais, coordenadas e o botão 'Abrir rota', que abre o Google Maps (ou o navegador) já no local.

**Regras (docs/ESCOPO.md):**
- Sem mapa embutido (decisão D-02): só coordenadas e o botão para o app de mapas.
- O link usa o formato oficial de URL do Google Maps: https://www.google.com/maps/search/?api=1&query=LATITUDE,LONGITUDE.

**Critérios de aceite = os testes que você vai escrever:**

| # | Critério | Dado | Quando | Então |
|---|---|---|---|---|
| 1 | URL da rota | latitude -26.9 e longitude -48.7 | gero a URL | é https://www.google.com/maps/search/?api=1&query=-26.9,-48.7 |
| 2 | Detalhe carregado | um ponto cadastrado | a tela de detalhe abre | mostra nome, endereço, horário, materiais e coordenadas |
| 3 | Ponto inexistente | um id que não existe | a tela abre | mostra 'Ponto não encontrado' sem travar |
| 4 | Botão abre rota | detalhe aberto | toco em 'Abrir rota' | o app pede ao sistema para abrir a URL do caso 1 |

**Arquivos deste cartão, na ordem certa de trabalho:**

1. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/RotaNoMapaTest.kt` — Teste da URL (caso 1). ESCREVA ANTES.
   - Um teste com assertEquals na String inteira.
   - ☐ teste: `gera_url_do_google_maps_com_latitude_e_longitude`
2. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/RotaNoMapa.kt` — Regra: montar o link do mapa.
   - Crie `urlDaRota(latitude: Double, longitude: Double): String` com o formato da regra acima (Double.toString usa ponto decimal, serve).
   - Receita R1.
3. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontoDetalheViewModelTest.kt` — Testes (casos 2 e 3). ESCREVA ANTES.
   - Use o PontosRepositorioFake.
   - ☐ teste: `carrega_o_ponto_pelo_id`
   - ☐ teste: `id_inexistente_marca_naoEncontrado`
4. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontoDetalheViewModel.kt` — Estado do detalhe.
   - `PontoDetalheEstado`: carregando, ponto (ou nulo), naoEncontrado, urlDaRota (calculada a partir do ponto).
   - `PontoDetalheViewModel(pontoId, pontos)` (R3).
5. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontoDetalheTela.kt` — Tela de detalhe.
   - `PontoDetalheRota(pontoId, navegador)` cria o ViewModel passando o id (R4). `PontoDetalheTela(estado, onAbrirRota, onVoltar)`.
   - No botão 'Abrir rota', use `LocalUriHandler.current.openUri(url)` dentro da Rota e passe a ação para a tela.
   - Também mostre o aviso RN04 quando o ponto aceita pilhas/baterias/óleo (reuse o texto do PB09).
6. `composeApp/src/androidInstrumentedTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/pontos/PontoDetalheTelaTest.kt` — Teste de interface.
   - Confere nome do ponto e botão 'Abrir rota' (R5).
   - ☐ teste: `mostra_dados_e_botao_abrir_rota`
7. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/navegacao/AppNavegacao.kt` *(já existe — edite dentro do seu bloco)* — (já existe) Bloco 'Detalhe do ponto'.
   - Troque por PontoDetalheRota(rota.pontoId, navegador).

**Pronto quando:**
- Todos os critérios de aceite têm teste verde (CI verde) e Pull Request aprovado.
- No celular real, o botão abre o app de mapas no local certo.

### PB17 · Dicas de reciclagem
*Integrante 3 · Sprint 3 · 2 pontos · Requisitos: RF11, CU08*

**História:** Como morador, quero ver dicas de reciclagem e informações ambientais.

**Objetivo:** Tela com uma lista de dicas curtas e a 'dica do dia' (muda a cada dia). É a 'bobeira' do Integrante 3: escrever o conteúdo. Conteúdo estático: não precisa de SQL.

**Regras (docs/ESCOPO.md):**
- Texto curto, correto e sem promessa que o app não cumpre.
- Conteúdo estático dentro do app (funciona sem internet).

**Critérios de aceite = os testes que você vai escrever:**

| # | Critério | Dado | Quando | Então |
|---|---|---|---|---|
| 1 | Dica do dia | 8 dicas e o dia 1 do ano | calculo a dica do dia | devolve a dica de posição (dia do ano mod 8) |
| 2 | Muda por dia | o mesmo conjunto de dicas em 2 dias seguidos | calculo a dica do dia | as duas dicas são diferentes |
| 3 | Lista completa | o repositório de dicas | listo | devolve pelo menos 8 dicas, cada uma com título e texto não vazios |
| 4 | Tela | dicas carregadas | a tela abre | mostra a dica do dia em destaque e a lista abaixo |

**Arquivos deste cartão, na ordem certa de trabalho:**

1. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/dicas/Dica.kt` — Modelo.
   - `Dica`: id (Long), titulo (String), texto (String).
2. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/dicas/DicaDoDiaTest.kt` — Testes dos casos 1 e 2. ESCREVA ANTES.
   - Use LocalDate fixo.
   - ☐ teste: `escolhe_a_dica_pela_posicao_do_dia_do_ano`
   - ☐ teste: `dias_seguidos_dao_dicas_diferentes`
3. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/dicas/DicaDoDia.kt` — Regra: qual dica mostrar hoje.
   - `dicaDoDia(dicas: List<Dica>, hoje: LocalDate): Dica` — posição = (dia do ano) mod (quantidade de dicas). LocalDate tem a propriedade dayOfYear (R11).
4. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/dicas/DicasRepositorio.kt` — Contrato e implementação em memória.
   - Interface `DicasRepositorio` (suspend listar()) e `DicasRepositorioEmMemoria` que devolve DadosDicasDemo. Sem SQL.
5. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/dicas/DadosDicasDemo.kt` — O conteúdo: no mínimo 8 dicas.
   - Escreva 8 ou mais dicas curtas (título + 1 ou 2 frases), em português simples. Sugestões de tema: enxaguar embalagens; óleo de cozinha usado em garrafa PET fechada para ponto de descarte; pilhas e baterias só em ponto de descarte; papel molhado ou engordurado não é reciclável; amassar latas e caixas; vidro quebrado embrulhado; eletrônicos em pontos específicos; separar orgânico do reciclável.
   - Confira cada dica nas fontes de docs/PESQUISA.md ou no guia da prefeitura. Não invente número ou lei.
6. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/dicas/DicasViewModelTest.kt` — Testes (caso 3 e 4). ESCREVA ANTES.
   - Use RelogioFixo e o repositório em memória.
   - ☐ teste: `lista_tem_pelo_menos_oito_dicas_completas`
   - ☐ teste: `destaca_a_dica_do_dia`
7. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/dicas/DicasViewModel.kt` — Estado.
   - `DicasEstado`: carregando, dicaDoDia, dicas. `DicasViewModel(repo, relogio)` (R3).
8. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/dicas/DicasTela.kt` — Tela.
   - `DicasRota` e `DicasTela(estado, onVoltar)`: card em destaque com a dica do dia e lista das outras (R4).
9. `composeApp/src/androidInstrumentedTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/dicas/DicasTelaTest.kt` — Teste de interface.
   - Confere título da tela e a dica do dia (R5).
   - ☐ teste: `mostra_a_dica_do_dia`
10. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/di/AppContainer.kt` *(já existe — edite dentro do seu bloco)* — (já existe) Seu bloco 'INTEGRANTE 3'.
   - Adicione `dicasRepositorio`. Em AppNavegacao.kt, troque o bloco 'Dicas' por DicasRota(navegador).

**Pronto quando:**
- Todos os critérios de aceite têm teste verde (CI verde) e Pull Request aprovado.
- Todas as dicas foram lidas por outro integrante (revisão de conteúdo).

## Quando travar

1. Releia o comentário do arquivo e a receita citada (docs/RECEITAS.md).
2. Copie o erro inteiro e peça ajuda no grupo dizendo o que você já tentou.
3. O Integrante 1 leva o problema ao Claude se for técnico e mais de 1 dia parado.

# Integrante 4 · Desenvolvimento: guia de separação e vídeo

> Sua bíblia da Etapa 2. Tudo que você precisa está aqui (e igual no Trello). Primeiro leia o Plano geral no HTML.

## Seu papel em 1 minuto

Guia de separação pesquisável (offline) e o vídeo da Etapa 2.

**Você faz:**
- PB11 Guia de separação (busca + conteúdo de 20 itens)
- Vídeo da Etapa 2 (até 3 min)

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

### PB11 · Guia de separação pesquisável (offline)
*Integrante 4 · Sprint 2 · 8 pontos · Requisitos: RF08, RN04, RNF03, CU04*

**História:** Como morador, quero buscar no guia de separação (ex.: 'caixa de leite') e saber onde descartar, mesmo sem internet.

**Objetivo:** Um guia com pelo menos 20 itens do dia a dia. O usuário digita e a lista filtra (sem diferenciar maiúsculas e acentos). Ao tocar, vê a orientação de descarte. Tudo local: o app nem pede permissão de internet.

**Regras (docs/ESCOPO.md):**
- RN04: itens de pilhas, baterias e óleo têm destino 'Ponto de descarte' (nunca coleta porta a porta).
- Conteúdo do HOW IV: responsabilidade pela separação e horário de colocar o lixo na calçada viram itens/orientações do guia, não regra de sistema.
- RNF03: funciona sem internet.

**Critérios de aceite = os testes que você vai escrever:**

| # | Critério | Dado | Quando | Então |
|---|---|---|---|---|
| 1 | Normalizar texto | o texto ' CAIXÁ de Leite ' | normalizo | o resultado é 'caixa de leite' |
| 2 | Busca ignora acento e caixa | item 'Caixa de leite (longa vida)' | busco 'CAIXA LEITE' e depois 'caixa de leite' | o item aparece nas duas buscas (ambas as palavras estão no nome) |
| 3 | Busca por sinônimo | item 'Garrafa PET' com sinônimo 'refrigerante' | busco 'refrigerante' | o item aparece |
| 4 | Busca vazia | termo vazio | busco | volta todos os itens em ordem alfabética |
| 5 | Nada encontrado | termo que não existe | busco | lista vazia e a tela mostra 'Nada encontrado' |
| 6 | Dados do guia | o conjunto de itens de demonstração | valido | são pelo menos 20, ids únicos, e todo item de pilha/bateria/óleo tem destino PONTO_DE_DESCARTE (RN04) |
| 7 | Detalhe | item cadastrado | abro o detalhe | mostra nome, destino (Coleta seletiva / Coleta comum / Ponto de descarte) e orientação |
| 8 | Sem internet | o AndroidManifest do app | confiro as permissões | não existe INTERNET |

**Arquivos deste cartão, na ordem certa de trabalho:**

1. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/util/Texto.kt` — Função de apoio: normalizar texto para busca.
   - Crie `normalizarTexto(texto: String): String`: tira espaços do começo/fim, passa para minúsculas e troca letras acentuadas por sem acento (á→a, ã→a, ç→c, é→e, ê→e, í→i, ó→o, õ→o, ô→o, ú→u...).
   - Dica: um mapa de letras ou um 'when' simples resolve; não precisa de biblioteca. Receita R1.
2. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/util/TextoTest.kt` — Teste (caso 1). ESCREVA ANTES.
   - Ver caso 1; adicione um teste de texto já normalizado (não muda).
   - ☐ teste: `tira_acento_caixa_alta_e_espacos_nas_pontas`
   - ☐ teste: `texto_ja_normalizado_nao_muda`
3. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/guia/GuiaItem.kt` — Modelos do guia.
   - Enum `Destino`: COLETA_SELETIVA, COLETA_COMUM, PONTO_DE_DESCARTE (com nomeExibicao em português).
   - `GuiaItem`: id (Long), nome (String), destino (Destino), orientacao (String), sinonimos (List<String>).
4. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/guia/BuscaNoGuiaTest.kt` — Testes da busca (casos 2 a 5). ESCREVA ANTES.
   - Monte itens de exemplo dentro do teste.
   - ☐ teste: `ignora_acento_e_caixa`
   - ☐ teste: `acha_por_sinonimo`
   - ☐ teste: `termo_vazio_devolve_todos_em_ordem_alfabetica`
   - ☐ teste: `sem_resultado_devolve_lista_vazia`
5. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/guia/BuscaNoGuia.kt` — Regra RF08: buscar no guia.
   - `buscarNoGuia(itens: List<GuiaItem>, termo: String): List<GuiaItem>`.
   - Ideia: normalize o termo; se vazio, devolva todos em ordem alfabética; senão separe o termo em palavras e mantenha os itens em que TODAS as palavras estão no nome ou em algum sinônimo (também normalizados).
   - Ordene o resultado por nome.
6. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/guia/GuiaRepositorio.kt` — Contrato e implementação em memória.
   - Interface `GuiaRepositorio` (suspend listar(), buscar(id)) e `GuiaRepositorioEmMemoria` que usa DadosGuiaDemo. Sem SQL (conteúdo estático).
7. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/guia/DadosGuiaDemo.kt` — Conteúdo do guia: no mínimo 20 itens.
   - Escreva 20 ou mais itens com id único. Cubra: papel, papelão, caixa de leite, jornal; garrafa PET, embalagem de margarina, sacolas plásticas, isopor; lata de alumínio, lata de aço, tampinha; vidro (garrafa, pote), espelho/vidro plano (não recicla); pilhas, baterias, óleo de cozinha usado, lâmpada, eletrônicos, roupas; orgânicos, papel higiênico, fralda, guardanapo sujo.
   - Para cada um: nome, destino correto, orientação em 1 ou 2 frases (ex.: 'Enxágue e amasse antes de colocar na coleta seletiva') e sinônimos.
   - Pilhas, baterias e óleo: destino PONTO_DE_DESCARTE (RN04). Inclua 2 itens de 'orientação' vindos do HOW IV: 'Quem separa o lixo' e 'Quando colocar na calçada' (sem inventar horário).
8. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/guia/DadosGuiaTest.kt` — Teste de integridade dos dados (caso 6). ESCREVA ANTES do conteúdo.
   - Este teste protege o conteúdo: roda sobre DadosGuiaDemo.
   - ☐ teste: `tem_pelo_menos_vinte_itens`
   - ☐ teste: `ids_sao_unicos`
   - ☐ teste: `pilhas_baterias_e_oleo_vao_para_ponto_de_descarte`
9. `composeApp/src/commonTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/guia/GuiaViewModelTest.kt` — Testes do ViewModel (casos 5 e 7). ESCREVA ANTES.
   - Use o repositório em memória com itens de exemplo.
   - ☐ teste: `digitar_filtra_a_lista`
   - ☐ teste: `sem_resultado_marca_nadaEncontrado`
   - ☐ teste: `abrir_item_carrega_o_detalhe`
10. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/guia/GuiaViewModel.kt` — Estado da busca.
   - `GuiaEstado`: carregando, termo, itens (já filtrados), nadaEncontrado. `GuiaViewModel(repo)`: ao iniciar carrega todos; `digitar(termo)` recalcula com buscarNoGuia (R3).
   - Crie também `GuiaDetalheViewModel(itemId, repo)` (estado: carregando, item, naoEncontrado) — pode ficar no mesmo arquivo.
11. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/funcionalidades/guia/GuiaTela.kt` — Telas do guia (lista com busca e detalhe).
   - `GuiaRota`/`GuiaTela(estado, onDigitar, onAbrir, onVoltar)`: campo de texto (OutlinedTextField) 'Buscar no guia', lista com nome + destino, mensagem 'Nada encontrado'.
   - `GuiaDetalheRota(itemId, navegador)`/`GuiaDetalheTela`: nome, destino em destaque, orientação. Se o destino for ponto de descarte, botão 'Ver pontos de descarte' → Rota.Pontos.
12. `composeApp/src/androidInstrumentedTest/kotlin/br/edu/univali/coletaseletiva/funcionalidades/guia/GuiaTelaTest.kt` — Teste de interface.
   - Digitar no campo e conferir que a lista muda (R5, use performTextInput).
   - ☐ teste: `digitar_filtra_os_itens_na_tela`
13. `composeApp/src/commonMain/kotlin/br/edu/univali/coletaseletiva/di/AppContainer.kt` *(já existe — edite dentro do seu bloco)* — (já existe) Seu bloco 'INTEGRANTE 4'.
   - Adicione `guiaRepositorio`. Em AppNavegacao.kt, troque 'Guia de separação' por GuiaRota(navegador) e 'Detalhe do item do guia' por GuiaDetalheRota(rota.itemId, navegador).

**Pronto quando:**
- Todos os critérios de aceite têm teste verde (CI verde) e Pull Request aprovado.
- Outro integrante revisou o conteúdo dos 20 itens (orientações corretas).

### VIDEO · Vídeo da Etapa 2 (até 3 min)
*Integrante 4 · Sprint 4 · 5 pontos*

**Objetivo:** Mostrar o app funcionando, os testes e o Scrum em até 3 minutos.

**Passo a passo:**
1. Combine com o Integrante 1 a data de gravação (Review da Sprint 4).
2. Roteiro: peça ao Claude (ele escreve a partir de docs/ETAPA2.md e docs/PLANO-VIDEO-ETAPA1.md). Estrutura sugerida: problema (15 s), o app em uso (60 s), testes e TDD (40 s), Scrum e equipe (30 s), fechamento (15 s).
3. Gravar a tela do app: no Android Studio use o emulador e 'Record screen' (ícone de câmera na barra do emulador) ou grave o celular. Telas: Início → Escolher local → Calendário → Pontos com filtro → Detalhe/Rota → Guia (buscando) → Registrar descarte → Meus pontos.
4. Mostrar também o GitHub Actions verde e o quadro do Trello (prints ou gravação curta).
5. Narração e legenda: mesmo método da Etapa 1 (docs/PLANO-VIDEO-ETAPA1.md): narração clara, legenda em português, sem música alta.
6. Editar no editor que preferir (CapCut, DaVinci, Clipchamp). Duração máxima 3:00.
7. Exportar o arquivo (.mp4) e enviar o ARQUIVO na aba de evidências (o template manda não enviar link). Também subir no YouTube como não listado para o link da seção 7.1/7.2 se o professor permitir.
8. Quem aparece/narra: decisão do grupo; sem avisos de IA se não houver imagem gerada por IA.

## Quando travar

1. Releia o comentário do arquivo e a receita citada (docs/RECEITAS.md).
2. Copie o erro inteiro e peça ajuda no grupo dizendo o que você já tentou.
3. O Integrante 1 leva o problema ao Claude se for técnico e mais de 1 dia parado.

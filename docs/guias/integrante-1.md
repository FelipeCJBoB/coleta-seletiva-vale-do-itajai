# Integrante 1 · Scrum Master e Validação

> Sua bíblia da Etapa 2. HTML, Trello e Markdown têm o MESMO conteúdo (todos saem da mesma fonte). Escolha UM para seguir do começo ao fim. Regra prática: leia e siga o passo a passo no formato que preferir, e marque o andamento do cartão no Trello (mover para 'Revisão' e 'Concluído'), porque é o Trello que o professor vê como evidência do Scrum.

## Seu papel em 1 minuto

Cuida do processo, da qualidade e da entrega. Garante que o app, os testes e o relatório atendam o que o professor pede. Não escreve código de funcionalidade.

**Você faz:**
- Trello, eventos do Scrum, impedimentos
- Revisar e fazer o merge de TODOS os Pull Requests
- Evidências do TDD (PB13) e roteiro de validação (PB14)
- Seção 6 do relatório (Scrum) e conferência final contra a rubrica
- Falar com o professor (prazo, formato)
- CI (GitHub Actions) e iOS, junto com o Claude

**Você não faz:**
- Código das funcionalidades (dos Integrantes 2 a 5)

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

## Seus cartões

### SM-01 · Convidar o grupo e proteger a main
*Integrante 1 · Sprint 1*

**Objetivo:** Deixar o GitHub pronto para 5 pessoas trabalharem sem quebrar a main.

**Passo a passo:**
1. Peça aos 4 integrantes o usuário do GitHub (no grupo).
2. GitHub do repositório > Settings > Collaborators > Add people: adicione os 4 com permissão Write.
3. Settings > Branches > Add branch ruleset (ou 'Add classic branch protection rule') para a branch `main`: marque 'Require a pull request before merging' (1 aprovação) e 'Require status checks to pass' escolhendo 'Testes (Android/JVM)' e 'APK de teste'.
4. Settings > General > Pull Requests: deixe marcado SÓ 'Allow merge commits' (desmarque squash e rebase). Assim o histórico mantém os commits `test:` e `feat:`, que são a evidência do TDD.
5. Teste: peça a um integrante para abrir um PR de teste (o PR de ambiente do checklist serve).
6. Peça ao Claude para conferir as configurações (ele pode ler com o GitHub CLI).

### SM-02 · Conferir o ambiente dos 4 (PB05)
*Integrante 1 · Sprint 1*

**Objetivo:** Todo mundo com ambiente funcionando até 23/10.

**Passo a passo:**
1. Acompanhe docs/CHECKLIST-AMBIENTE.md: cada integrante faz um PR marcando a sua linha.
2. Quem travar: peça o texto do erro e resolva por chamada rápida ou com o Claude.
3. 23/10: se algum ainda não rodou `testDebugUnitTest` com BUILD SUCCESSFUL, é impedimento número 1 da Planning.

### SM-03 · Planning da Sprint 2 (seg 26/10, até 1 h)
*Integrante 1 · Sprint 2*

**Objetivo:** Começar a Sprint 2 com todos sabendo o que fazer.

**Passo a passo:**
1. Leia os cartões de cada Integrante no Trello. Cada um confirma: 'entendi meu cartão e sei por onde começar'.
2. Confirme a meta da Sprint 2 (veja o plano) e as dependências: PB06b, Material e LocalRepositorio até qua 28/10.
3. Registre no Trello: meta da sprint na coluna 'Sprints', data, quem participou.
4. Marque Review + Retro para sáb 07/11 e combine o horário da daily (assíncrona até 20h).

### SM-04 · Daily assíncrona (todo dia útil)
*Integrante 1 · Sprint 2*

**Objetivo:** Ver o que trava antes de virar atraso.

**Passo a passo:**
1. Fixe no grupo o modelo de 3 linhas: 1) o que fiz; 2) o que vou fazer; 3) o que me trava.
2. Até 20h cada integrante responde. Quem não respondeu, cobre no dia seguinte.
3. Impedimento: anote no Trello (cartão 'Impedimentos') e resolva ou escale em 24 h.

### SM-05 · Revisar Pull Requests (todo PR, até 24 h)
*Integrante 1 · Sprint 2*

**Objetivo:** Só entra na main o que está testado e dentro do escopo.

**Passo a passo:**
1. Abra o PR no GitHub e confira, nesta ordem:
2. ☐ O CI está verde (aba Checks).
3. ☐ O PR tem commit `test:` ANTES do commit `feat:` (aba Commits, ordem cronológica).
4. ☐ Todos os critérios de aceite do cartão têm teste (compare com a lista do Trello).
5. ☐ Só arquivos da pasta do dono (e do bloco dele no AppContainer/AppNavegacao).
6. ☐ Os comentários de passo dos arquivos-esqueleto foram trocados por comentários úteis (ou apagados).
7. ☐ Sem chave, senha, dado pessoal ou horário/número inventado.
8. ☐ Textos em português correto; dados marcados como demonstração.
9. Se tudo ok: Approve e Merge pull request (merge commit). Se não: comente o que ajustar de forma objetiva. Dúvida técnica: cole o trecho no Claude.
10. Depois do merge: atualize docs/EVIDENCIAS-TDD.md (SM-06).

### SM-06 · PB13 Evidências do TDD
*Integrante 1 · Sprint 2*

**Objetivo:** Provar o ciclo vermelho → verde → refatorar com dados reais.

**Passo a passo:**
1. Abra docs/EVIDENCIAS-TDD.md (tabela já criada).
2. Para cada PR mergeado, adicione uma linha: PR, cartão, commit do teste, commit do código, print do CI vermelho, print do CI verde.
3. Os hashes: `git log --oneline --reverse` na branch do PR ou aba Commits do PR.
4. Os prints: Actions > execução do commit `test:` (vermelha) e do commit `feat:` (verde). Salve em docs/evidencias/ com nome pbXX-vermelho.png e pbXX-verde.png.
5. Use no mínimo 1 PR por Integrante; escolha o mais claro para a seção 5.1.4.

### SM-07 · Review e Retrospectiva da Sprint 2 (sáb 07/11)
*Integrante 1 · Sprint 2*

**Objetivo:** Mostrar o que foi feito e melhorar.

**Passo a passo:**
1. Review (30 min): cada dev mostra a sua parte funcionando (emulador ou APK do CI), 5 min cada.
2. Retrospectiva (30 min): o que foi bem, o que atrapalhou, 1 ação concreta para a próxima sprint.
3. Registre no Trello (coluna Sprints) e tire print do quadro: vai para a seção 6 do relatório.
4. Atualize o Product Backlog: o que foi concluído e o que entra na Sprint 3.

### SM-08 · PB14 Roteiro de validação (escrever)
*Integrante 1 · Sprint 3*

**Objetivo:** Um roteiro que o grupo executa no app para validar as funcionalidades.

**Passo a passo:**
1. Abra docs/VALIDACAO.md: já tem uma lista de casos esperados (VAL-01...).
2. Complete cada caso com passos curtos e objetivos (ex.: 'abra o app, toque em Escolher local, selecione Itajaí e Centro').
3. Acrescente casos de desempenho simples (RNF04: lista abre em até 3 s) e de uso sem internet (modo avião, RNF03).
4. Combine com o grupo quem executa cada caso (Integrante N) na Sprint 4. A validação é só do grupo, sem usuários externos.

### SM-09 · PB14 Executar a validação
*Integrante 1 · Sprint 4*

**Objetivo:** Rodar o roteiro e registrar o resultado.

**Passo a passo:**
1. Em dupla (ou sozinho), execute o roteiro em um emulador e em pelo menos um celular Android (APK do CI).
2. Preencha na tabela: resultado obtido, data, quem executou (Integrante N), aprovado ou não.
3. Defeito encontrado: crie um cartão 'Bug' no Trello, o dono corrige com teste novo (TDD) e repete o caso.
4. Entregue a tabela ao Integrante 5 para o relatório 5.1.3.

### SM-10 · Seção 6 do relatório (Scrum, Etapa 2)
*Integrante 1 · Sprint 3*

**Objetivo:** Mostrar o Scrum funcionando nas 3 sprints.

**Passo a passo:**
1. Abra relatorio/etapa2/6-scrum-configurado.md (esqueleto).
2. Atualize papéis (Integrantes 2 a 5 desenvolvem, Integrante 1 é o Scrum Master, o Product Owner define prioridades com o grupo), eventos com as datas reais, artefatos (Product Backlog, Sprint Backlog, Incremento).
3. Insira os prints do Trello de cada Sprint (Product Backlog, Releases, Sprints, colunas por Integrante).
4. Descreva cada sprint: meta, o que foi entregue, o que melhorou na retrospectiva (só fatos).

### SM-11 · Confirmar com o professor (antes de 25/10)
*Integrante 1 · Sprint 1*

**Objetivo:** Tirar as 5 dúvidas que mudam o plano.

**Passo a passo:**
1. Envie uma mensagem curta (AVA ou e-mail) com as perguntas:
2. 1) Qual é a data de entrega da Etapa 2?
3. 2) O vídeo da Etapa 2 deve ser enviado como ARQUIVO na aba de evidências (como o template diz), sem link? Pode subir também no YouTube como não listado?
4. 3) A validação feita pelo próprio grupo (sem usuários externos) é aceita na seção 5.1.3?
5. 4) iOS: executar os testes no simulador pelo CI (sem Mac) atende?
6. 5) O PDF único segue o mesmo template da Etapa 1, com a seção 5 completa?
7. Registre as respostas em docs/PLANO.md (tabela de decisões).

### SM-12 · iOS: CI e app no simulador (com o Claude)
*Integrante 1 · Sprint 3*

**Objetivo:** Cumprir o 'Android e iOS' do template sem Mac.

**Passo a passo:**
1. Hoje o CI roda os testes comuns no simulador iOS (job 'Testes no iOS'). Confira que fica verde em cada PR.
2. Peça ao Claude para adicionar o projeto Xcode (iosApp) e um job que abre o app no simulador e salva uma captura de tela (artefato do CI).
3. Guarde a captura para o relatório 5.1.1. Se não der tempo, o relatório descreve exatamente o que foi feito (só os testes no iOS).

### SM-13 · Conferência final e envio
*Integrante 1 · Sprint 4*

**Objetivo:** Entregar tudo certo e no prazo.

**Passo a passo:**
1. Abra docs/RUBRICA.md e marque cada critério com a evidência no PDF.
2. Confira o PDF contra o template (todas as seções, numeração, ABNT, título de seção 5.1.1 a 5.1.4 preenchidos).
3. Confira o vídeo: até 3:00, arquivo na aba de evidências, link na seção 7 se pedido.
4. Peça a outro integrante para ler o PDF inteiro.
5. Envie (meta: 2 dias antes). Guarde o comprovante.

## Quando travar

1. Releia o comentário do arquivo e a receita citada (docs/RECEITAS.md).
2. Copie o erro inteiro e peça ajuda no grupo dizendo o que você já tentou.
3. O Integrante 1 leva o problema ao Claude se for técnico e mais de 1 dia parado.

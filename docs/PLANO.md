# Plano do time (5 integrantes)

Escrito por nós, para nós. Quem discordar de algo, abre um PR neste arquivo ou fala no grupo; o que o grupo decidir vira texto aqui.

## 1. O que precisamos entregar

| Quando | O quê | Quem envia |
|---|---|---|
| **sex 23/10** (meta interna) | PDF único da Etapa 1 + link do vídeo (até 3 min, YouTube não listado) | Integrante 1 |
| **dom 25/10, 23h59** (prazo) | Mesmo PDF, só se algo atrasar | Integrante 1 |
| Etapa 2 | App mobile com testes (TDD), validação, seções 5.1.1 a 5.1.4, vídeo e PDF único (data a confirmar com o professor) | Integrante 1 |

Regra de ouro: **o PDF da Etapa 1 é a prioridade até 23/10.** Código do app só entra na frente se o relatório estiver em dia.

## 2. Papéis

Os mesmos papéis que usamos no HOW IV e que aparecem na seção 6 do relatório.

| Integrante | Papel Scrum | Responsabilidade principal |
|---|---|---|
| 1 | Scrum Master | Seção 6 (Scrum), reuniões, Trello e prints, montar e enviar o PDF |
| 2 | Product Owner | Seção 4.1 (escopo), prioridades do backlog e critérios de aceite (base dos testes da Etapa 2) |
| 3 | Redator | Seções 1, 2, 3, 8 e 9, formatação ABNT, revisão final de texto |
| 4 | Editor | Vídeo (roteiro, gravação, edição, YouTube), figuras e tabelas do relatório |
| 5 | Time de Desenvolvimento (técnico) | Repositório, ambiente, arquitetura do app, primeiros testes, CI |

Todos somos Time de Desenvolvimento quando o app começar: cada um assume uma funcionalidade (seção 5).

## 3. Sprint 1: 12/10 a 25/10 (Release 1: Etapa 1)

| Quando | Marco |
|---|---|
| seg 12/10 | Repositório, plano e escopo do MVP aprovados (feito) |
| ter 13 a qui 15/10 | Cada um escreve ou ajusta as suas seções. Todos instalam o ambiente (Android Studio) |
| sex 16/10 | **Checkpoint 1:** relatório completo em rascunho; roteiro do vídeo pronto |
| sáb 17 a qui 22/10 | Revisão cruzada, vídeo gravado e editado, prints do Trello, referências com data de acesso |
| sex 23/10 | **Entrega interna:** PDF final e link do vídeo no PDF; Integrante 1 envia |
| sáb 24 e dom 25/10 | Reserva. Não planejamos nada para esses dias |

### Tarefas por integrante (Sprint 1)

**Integrante 1 (Scrum Master)**
- [ ] Marcar o checkpoint de 16/10 e a revisão final de 22/10
- [ ] Escrever a seção 6 (papéis, artefatos, eventos, backlog, Sprint 1) alinhada a este plano
- [ ] Montar o Trello (Product Backlog, Release 1, Release 2, Sprint 1) e tirar 2 prints para a seção 6
- [ ] Confirmar com o professor: prazo e formato do vídeo da Etapa 2, iOS em simulador, uso de material do HOW IV
- [ ] Montar o PDF final, conferir links e enviar

**Integrante 2 (Product Owner)**
- [ ] Revisar a seção 4.1 (4.1.1 a 4.1.8) com o escopo de [ESCOPO.md](ESCOPO.md)
- [ ] Fechar o Product Backlog priorizado e os critérios de aceite de cada item
- [ ] Escrever os critérios de aceite de PB06 a PB15 (viram os testes da Etapa 2)

**Integrante 3 (Redator)**
- [ ] Revisar as seções 1, 2 e 3 (texto do HOW IV adaptado ao HOW VIII, sem copiar sem revisar)
- [ ] Escrever 8 (Conclusão da Etapa 1) e 9 (Referências em ABNT, com data de acesso)
- [ ] Resumo e abstract coerentes com o escopo final
- [ ] Revisão de formatação do template em todo o documento

**Integrante 4 (Editor)**
- [ ] Roteiro do vídeo até 16/10 (problema, solução, escopo, Scrum, resultados)
- [ ] Gravação e edição até 21/10; publicar como não listado e passar o link ao Integrante 3
- [ ] Atualizar figuras: stakeholders, cronograma e tabelas do relatório

**Integrante 5 (Técnico)**
- [ ] Criar o projeto Kotlin Multiplatform neste repositório e rodar "hello world" no emulador Android
- [ ] Guia curto de instalação em `docs/AMBIENTE.md` e ajudar os outros 4 a instalar
- [ ] Seção 4.1.6 (ferramentas e tecnologias) revisada
- [ ] Primeiro teste unitário passando (ciclo vermelho, verde, refatorar), mesmo que trivial

## 4. Etapa 2 / app (Release 2: sprints de 2 semanas)

Tudo da Etapa 2 (app final, telas, dados, arquivos e testes) está em [ETAPA2.md](ETAPA2.md). A bíblia de cada Integrante, com o passo a passo ponta a ponta, está em [guias/](guias/).

| Sprint | Datas (provisórias) | Objetivo |
|---|---|---|
| 1 (fecha) | até 25/10 | PB05: todos com o ambiente funcionando e o primeiro PR ([CHECKLIST-AMBIENTE.md](CHECKLIST-AMBIENTE.md)) |
| 2 | 26/10 a 08/11 | 1º ciclo TDD de cada funcionalidade: banco (PB06b), local (PB07), início (PB07b), pontos (PB09), guia (PB11), regras de pontuação |
| 3 | 09/11 a 22/11 | Funcionalidades completas com SQL e dados, testes de interface, calendário (PB08), detalhe do ponto (PB10), dicas (PB17), pontuação (PB16), roteiro de validação |
| 4 | 23/11 a 06/12 | Integração, validação pelo grupo, relatório 5.1.1 a 5.1.4, vídeo, PDF e entrega; PB12 só se sobrar tempo |

Papéis e donos na Etapa 2:

| Integrante | Papel | Cartões |
|---|---|---|
| 1 | Scrum Master e Validação. Não programa funcionalidades, exceto o PB07 e o PB07b, assumidos com o Claude por combinado com o Integrante 2 (D-12) | Trello, eventos, revisão e merge de todos os PRs, PB13, PB14, seção 6, iOS/CI, contato com o professor; PB07 e PB07b (feitos) |
| 2 | Dev: calendário e lembrete | PB08, PB12 (opcional). PB07 e PB07b já feitos pelo Integrante 1 com o Claude |
| 3 | Dev: pontos de descarte e dicas | PB09 (inclui o enum Material), PB10, PB17 |
| 4 | Dev: guia e vídeo | PB11, vídeo 7.2 |
| 5 | Dev: banco, pontuação e documentação | PB06b, PB16, relatório 5.1.1 a 5.1.4, PDF |

Decisões novas (D-07 a D-12) em [ETAPA2.md](ETAPA2.md). A data oficial da Etapa 2 ainda precisa ser confirmada com o professor (Integrante 1).

## 5. Como trabalhamos

- **Daily assíncrona** no grupo, de segunda a sexta, 3 linhas: o que fiz, o que vou fazer, o que me trava.
- **Planning** no primeiro dia da sprint (até 1 h), **Review + Retrospectiva** no último dia (até 1 h no total). Na seção 6 do relatório usamos exatamente esses valores.
- Tarefas ficam no Trello, quadro "HOW VIII — Coleta Seletiva Vale do Itajaí" (evidência para o professor): Product Backlog, Releases, Sprints e colunas por Integrante, divididas em Etapa 1 e Etapa 2. Não há atribuição por nome; cada um trabalha na coluna do seu número. Cópia do backlog em [BACKLOG.md](BACKLOG.md).
- Código, documentos de planejamento e o **relatório mestre** ficam neste repositório (decisão D-03): pasta `relatorio/`, uma seção por arquivo, um dono por arquivo, PDF gerado a partir dela.
- Ninguém fica bloqueado por mais de um dia sem avisar.
- Revisão cruzada: o que um escreve, outro lê antes de entrar no PDF.

## 6. Definição de pronto

**Relatório (Etapa 1)**
- Segue o template do professor (seções, numeração, ABNT).
- Seções 1 a 4, 6 e 8 preenchidas; 5 e 7.2 só com título; 7.1 com o link do vídeo.
- Referências com data de acesso; 2 prints do Trello na seção 6.
- Lido por pelo menos 2 integrantes além do autor da seção.

**Funcionalidade do app (Etapa 2)**
- Teste escrito antes do código (TDD) e passando.
- Revisada em PR por outro integrante.
- Roda no emulador Android; o CI compila (e testa, quando houver) o iOS.

## 7. Riscos e decisões em aberto

| ID | Item | Situação |
|---|---|---|
| D-01 | **Horário da coleta:** o texto de campo do HOW IV diz que a coleta "não tem horário fixo" e a RN1 falava em 08h às 12h | **decidido:** sem horário fixo; o app mostra só os dias |
| D-02 | **Mapa (RF05):** começamos com lista + coordenadas; mapa interativo só depois, porque pede chave de API e configuração extra | **aprovado** |
| D-03 | **Onde fica o relatório mestre** | **decidido:** no Git, pasta `relatorio/` |
| D-04 | **Dados de demonstração** de ecopontos | **decidido:** fictícios, marcados como tal (confirmar com o professor, Integrante 1) |
| D-05 | **Alcance geográfico** | **decidido:** Vale do Itajaí, por dados cadastrados (município e setor). O HOW IV é a origem da ideia e da pesquisa de campo. Ver `docs/ESCOPO.md` |
| D-06 | **O que trazer de volta do HOW IV** | **decidido (grupo):** pontuação simples com histórico (PB16), dicas de reciclagem (PB17) e rota no app de mapas (RF05). O resto segue fora |
| R-01 | Ninguém tem Mac: iOS só roda via CI (macOS) e simulador. **autorizado pelo professor** | aberto, Integrante 1 |
| R-02 | Todos novos em mobile: o ambiente leva mais tempo que o previsto. Mitigação: instalar já nesta semana | em curso |
| R-03 | Lembrete (RF02) exige permissão de notificação e agendamento no aparelho; é o item mais arriscado do MVP | aceito, é o primeiro a sair se faltar tempo |
| R-04 | Pontuação **autodeclarada** (sem QR, GPS ou foto): o usuário pode registrar descartes que não fez. Mitigação: limite de 3 por dia (RN09) e dizer isso no relatório | aceito |

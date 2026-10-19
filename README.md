# Coleta Seletiva Vale do Itajaí

Aplicativo móvel (Android e iOS) para ajudar moradores do **Vale do Itajaí/SC** a saber quando passa a coleta, onde descartar materiais específicos (pilhas, óleo de cozinha, eletrônicos) e como separar o lixo.

Projeto da disciplina **HOW VIII** (Análise e Desenvolvimento de Sistemas, UNIVALI). Nasce de uma ideia do HOW IV (pesquisa de campo e proposta de app gamificado) e a transforma em um projeto novo, voltado ao Vale do Itajaí, com desenvolvimento orientado a testes (TDD) e Scrum.

## Status

| Etapa | Entrega | Situação |
|---|---|---|
| Etapa 1 | Relatório (PDF), vídeo de até 3 min e Scrum configurado | em andamento, prazo 25/10/2026 |
| Etapa 2 | Aplicativo mobile com testes (TDD), validação, relatório e vídeo | a iniciar após a Etapa 1 |

## O que o app faz (MVP)

1. **Calendário de coleta** do município e setor do morador, com lembrete.
2. **Pontos de descarte** do município por tipo de material, com filtro.
3. **Guia de separação** pesquisável e disponível offline.
4. **Pontuação simples** por descarte registrado, com saldo e histórico.
5. **Dicas de reciclagem**.

Detalhes: [docs/ESCOPO.md](docs/ESCOPO.md). O que o app não vai conter, para reavaliação: [docs/FORA-DO-ESCOPO.md](docs/FORA-DO-ESCOPO.md).

## Como o time trabalha

- Plano completo, divisão entre os 5 integrantes e calendário: [docs/PLANO.md](docs/PLANO.md)
- Relatório (fonte em Markdown, uma seção por arquivo): [relatorio/README.md](relatorio/README.md)
- Fontes e dados levantados (secundários): [docs/PESQUISA.md](docs/PESQUISA.md)
- Product Backlog (cópia em Git do que está no Trello): [docs/BACKLOG.md](docs/BACKLOG.md)
- Como usar o Git no projeto: [CONTRIBUINDO.md](CONTRIBUINDO.md)
- Scrum no Trello (quadro privado do grupo): Product Backlog, Releases, Sprints e colunas por Integrante, divididas em Etapa 1 e Etapa 2.

## Tecnologia (planejada)

Kotlin Multiplatform + Compose Multiplatform, com lógica compartilhada entre Android e iOS, banco local SQLite, testes com JUnit/kotlin.test, Mockito (ou fakes) e testes de interface. O iOS é compilado e testado no CI em macOS, pois o grupo não tem Mac.

## Equipe

Cinco integrantes (Integrante 1 a 5). Os papéis estão em [docs/PLANO.md](docs/PLANO.md).

## Etapa 2 (desenvolvimento do app)

- App final definido: [docs/ETAPA2.md](docs/ETAPA2.md)
- Ambiente: [docs/AMBIENTE.md](docs/AMBIENTE.md) · Receitas de código: [docs/RECEITAS.md](docs/RECEITAS.md)
- Bíblia de cada Integrante: [docs/guias/](docs/guias/)
- Checklist de ambiente: [docs/CHECKLIST-AMBIENTE.md](docs/CHECKLIST-AMBIENTE.md)

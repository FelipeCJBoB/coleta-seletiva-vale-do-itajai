# Evidências do TDD (PB13)

Uma linha por Pull Request mergeado. Mantida pelo Integrante 1. Os prints ficam em `docs/evidencias/` (pbXX-vermelho.png e pbXX-verde.png). Usada na seção 5.1.4 do relatório.

| PR | Cartão | Integrante | Commit `test:` (hash + mensagem) | Commit `feat:` (hash + mensagem) | CI vermelho (arquivo) | CI verde (arquivo) |
|---|---|---|---|---|---|---|
| #2 | PB06b | 5 | `1b67061` | `67b6c62` | [vermelho](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/39866859072) | [verde](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/39703777213) |
| #4 | PB07 | 1 (com Claude) | `ce9f572`, `e407d0b`, `03711b1` | `5c4d798`, `e217ad9`, `51038ab` | ver tabela abaixo | ver tabela abaixo |
| #5 | PB07b | 1 (com Claude) | `e721f7c`, `11dfcea` | `2ac8828`, `656d0d4` | ver tabela abaixo | ver tabela abaixo |
| #8 (aberto) | PB08 | 2 | sem commit `test:` separado (testes no mesmo commit) | `dc48dd5` | — | [verde](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/42398077579) |
| | PB12 | 2 | | | | |
| #6 | PB09 | 3 | sem commit `test:` separado (testes no mesmo commit) | `c407aa7` | — | [verde](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/40202198457) |
| #6 | PB10 | 3 | sem commit `test:` separado (testes no mesmo commit) | `c407aa7` | — | [verde](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/40202198457) |
| #6 | PB17 | 3 | sem commit `test:` separado (testes no mesmo commit) | `c407aa7` | — | [verde](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/40202198457) |
| #9 (aberto) | PB11 | 4 (João Gabriel) | `1e74cc4`, `a4f28cb`, `80ae458`, `233e1fe`, `41d985a`, `3636234` | `e2330b8`, `c2b625a`, `e5b10d9`, `0f40ac8`, `9125462`, `4e92ad3` | ver tabela abaixo | ver tabela abaixo |
| | PB16 | 5 | | | | |

## Registradas (CI = job de testes JVM; vermelho = não compilou/falhou, verde = passou)

| PR | Cartão | Integrante | Commit `test:` | CI vermelho | Commit `feat:` | CI verde |
|---|---|---|---|---|---|---|
| #2 | PB06b | 5 | `1b67061` test: tabela descarte e bancos independentes | [vermelho](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/39866859072) | `67b6c62` feat: banco ligado e bancoEmMemoria | [verde](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/39703777213) |
| #4 | PB07 | 1 (com Claude) | `ce9f572` test: ViewModel de escolher local + Fake | [vermelho](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/39864137620) | `5c4d798` feat: modelos, contrato e ViewModel | [verde](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/39864272040) |
| #4 | PB07 | 1 (com Claude) | `e407d0b` test: repositório SQL de local | [vermelho](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/39864466763) | `e217ad9` feat: tabelas, repositório SQL e dados de demonstração | [verde](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/39864598909) |
| #4 | PB07 | 1 (com Claude) | `03711b1` test: tela de escolher local | [vermelho](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/39864773574) | `51038ab` feat: tela, escopo de ViewModel e ligação no app | [verde](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/39865630038) |
| #5 | PB07b | 1 (com Claude) | `e721f7c` test: ViewModel da tela Início | [vermelho](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/39865809390) | `2ac8828` feat: ViewModel e tema | [verde](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/39865944144) |
| #5 | PB07b | 1 (com Claude) | `11dfcea` test: tela Início | [vermelho](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/39866066636) | `656d0d4` feat: tela, tema aplicado e ícone | [verde](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/39866716580) |
| #9 (aberto) | PB11 | 4 (João Gabriel) | `1e74cc4` test: normalizar texto para busca | [vermelho](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/43316572182) | `e2330b8` feat: normalizar texto | [verde](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/43317027710) |
| #9 (aberto) | PB11 | 4 (João Gabriel) | `a4f28cb` test: busca no guia | [vermelho](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/43326143518) | `c2b625a` feat: modelos do guia e busca | [verde](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/43326939893) |
| #9 (aberto) | PB11 | 4 (João Gabriel) | `80ae458` test: integridade dos dados do guia | [vermelho](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/43329529364) | `e5b10d9` feat: conteúdo do guia (27 itens) | [verde](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/43330231884) |
| #9 (aberto) | PB11 | 4 (João Gabriel) | `233e1fe` test: ViewModel do guia | [vermelho](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/43336215008) | `0f40ac8` feat: repositório e ViewModels | [verde](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/43337194073) |
| #9 (aberto) | PB11 | 4 (João Gabriel) | `41d985a` test: tela do guia | [vermelho](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/43338227926) | `9125462` feat: telas do guia ligadas na navegação | [verde](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/43339664885) |
| #9 (aberto, revisão) | PB11 | 4 (João Gabriel) | `3636234` test: orientações gerais sem destino de coleta | [vermelho](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/43727495339) | `4e92ad3` feat: destino "Orientação geral" | [verde](https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai/actions/runs/43727513087) |

Os links levam à execução no GitHub Actions (aba Actions). Para o relatório (5.1.4), tire print das duas execuções do mesmo cartão e salve em `docs/evidencias/`.

## Modelo para os próximos cartões


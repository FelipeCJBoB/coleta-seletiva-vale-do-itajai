# Evidências do TDD (PB13)

Uma linha por Pull Request mergeado. Mantida pelo Integrante 1. Os prints ficam em `docs/evidencias/` (pbXX-vermelho.png e pbXX-verde.png). Usada na seção 5.1.4 do relatório.

| PR | Cartão | Integrante | Commit `test:` (hash + mensagem) | Commit `feat:` (hash + mensagem) | CI vermelho (arquivo) | CI verde (arquivo) |
|---|---|---|---|---|---|---|
| | PB06b | 5 | | | | |
| | PB07 | 2 | | | | |
| | PB07b | 2 | | | | |
| | PB08 | 2 | | | | |
| | PB12 | 2 | | | | |
| | PB09 | 3 | | | | |
| | PB10 | 3 | | | | |
| | PB17 | 3 | | | | |
| | PB11 | 4 | | | | |
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

Os links levam à execução no GitHub Actions (aba Actions). Para o relatório (5.1.4), tire print das duas execuções do mesmo cartão e salve em `docs/evidencias/`.

## Modelo para os próximos cartões


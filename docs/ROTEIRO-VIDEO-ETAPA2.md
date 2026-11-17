# Roteiro do vídeo da Etapa 2 (até 3 minutos)

Formato: **motion graphics em HTML (HyperFrames) + prints do app dentro do Android Studio + narração do integrante**. Foco **só nos resultados da Etapa 2** (exigência do enunciado, seção 7.2). Meta: **2:40 a 2:50**. Narração: ~400 palavras, ~145 palavras/min.

Entrega: o **arquivo .mp4** na aba "Curricularização da Extensão: Evidências" **e** o **link do YouTube "Não listado"** na seção 7.2 do relatório e no campo de texto do envio.

## Cenas

| # | Tempo | Narração (falada) | Imagem / motion graphics |
|---|---|---|---|
| 1. Abertura | 0:00–0:12 | "Na Etapa 2 saímos do planejamento e entregamos o aplicativo Coleta Seletiva Vale, com testes escritos antes do código." | Título animado: logo ♻️ + nome do app + "Etapa 2 · resultados". Contador sobe até "99 testes". |
| 2. O problema em 1 frase | 0:12–0:25 | "O morador não sabe o dia da coleta nem onde descartar pilhas e óleo. O app responde isso em poucos toques." | Duas perguntas em tipografia grande ("Quando passa a coleta?" / "Onde descarto pilhas?") que viram os ícones do app. |
| 3. O app: calendário | 0:25–0:50 | "O morador escolhe o município e o setor. O calendário mostra os dias da coleta seletiva e da comum e a próxima data, já considerando feriados nacionais." | **Android Studio** com o emulador mostrando o Calendário; ao lado, o código `proximaColeta` (zoom). Destaque animado na data "Próxima coleta". |
| 4. O app: pontos e guia | 0:50–1:15 | "Os pontos de descarte têm filtro por material e abrem a rota no mapa. O guia de separação funciona sem internet: buscamos 'caixa de leite' e achamos a orientação." | Emulador: Pontos com filtro "Pilhas" e aviso âmbar; Guia com busca; selo "modo avião" ligado. |
| 5. O app: pontuação e lembrete | 1:15–1:40 | "Cada descarte registrado vale pontos, com limite de três por dia. O saldo e o histórico ficam no aparelho, e um lembrete avisa na véspera da coleta." | Emulador: Registrar descarte → "Você ganhou 5 pontos!" → Meus pontos (saldo). Notificação do lembrete. |
| 6. Testes e TDD | 1:40–2:15 | "O desenvolvimento seguiu TDD: primeiro o teste, que falha; depois o código, que faz passar. O GitHub Actions executa os 99 testes: unitários, de integração com banco e Mockito, e de interface no emulador, também no simulador iOS." | Tela dividida: commit `test:` com CI vermelho → commit `feat:` com CI verde (prints reais de `docs/EVIDENCIAS-TDD.md`). Barras animadas: 61 / 18 / 20 por tipo. |
| 7. Validação e Scrum | 2:15–2:35 | "Validamos o app em emulador e celular com um roteiro de dezessete casos, e organizamos o trabalho em sprints no Trello." [[confirmar: só afirmar o que foi executado]] | Tabela de validação (aprovados/total) + print do quadro do Trello da Etapa 2. Rostos ou nomes dos 5 integrantes (participação). |
| 8. Fechamento | 2:35–2:50 | "Entregamos um app que ajuda o morador a descartar certo, com dados de demonstração e validação feita pelo grupo. O próximo passo é validar com moradores. Obrigado." | Logo + resumo de 3 números (funcionalidades, testes, casos validados). Nomes do grupo e contato da turma. |

## Como produzir

1. **Prints com Android Studio** (depois da instalação): com o emulador aberto em cada tela, capturar a **janela do Android Studio** mostrando o emulador ao lado do código da tela correspondente. Em cada cena 3 a 5, usar a captura da IDE como fundo e a tela do app em destaque. Para as telas estáticas, os 12 PNGs de `docs/evidencias/telas/`.
2. **HyperFrames:** uma composição HTML por cena (HTML + CSS + GSAP, saída MP4 em 1920×1080 a 30 fps), com os textos e números acima como camadas animadas e os prints como imagens. Narração e legenda entram na edição (a ferramenta não grava voz).
3. **Narração:** você grava a voz em um local silencioso, falando as frases da coluna "Narração" (já escritas em primeira pessoa do plural). Sem música alta.
4. **Legenda:** em português, até 42 caracteres por linha e 2 linhas.
5. **Subir** no YouTube como "Não listado", colocar o link na seção 7.2 e enviar também o arquivo .mp4.

## Para o critério "participação dos membros"

Citar os cinco integrantes na cena 7 (nome e o que cada um fez) ou mostrar os cinco na cena 8. [[definir quem aparece]]

## O que NÃO afirmar sem ter acontecido

- Resultado da validação (quantos casos foram aprovados): só depois de executar `docs/VALIDACAO.md`.
- "Usuários validaram": o app foi validado só pelo grupo.
- Cobertura de testes em porcentagem: não foi medida.

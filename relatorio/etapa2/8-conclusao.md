## Conclusão

O projeto entregou, na Etapa 2, um aplicativo Android (com código compartilhado para iOS) que reúne o calendário de coleta com feriados, os pontos de descarte com filtro e rota, o guia de separação offline, as dicas, o registro de descartes com pontos e o lembrete na véspera da coleta. O desenvolvimento seguiu o Scrum, em sprints de duas semanas, e a prática de TDD, com 99 testes automatizados (61 compartilhados, 18 de integração e Mockito na JVM e 20 de interface) executados pelo GitHub Actions a cada envio de código.

Os resultados estão registrados no repositório: a tabela de evidências do TDD (`docs/EVIDENCIAS-TDD.md`), os critérios de aceite de cada cartão nos Pull Requests e o roteiro de validação (`docs/VALIDACAO.md`).

**Limitações assumidas.** O registro de descartes é autodeclarado; todos os dados são de demonstração; os feriados cobrem apenas 2026 e 2027; a notificação do lembrete existe só no Android; o iOS foi verificado apenas pelos testes no simulador do CI; e a validação foi feita somente pelo grupo, sem moradores. [[ajustar esta frase ao que realmente ficou pronto e validado]]

**Trabalhos futuros.** Validar o app com moradores e coletar feedback; incluir mais municípios (o cadastro de município e setor já é só dado, sem mudar o código das telas); trocar os dados de demonstração pelos dados oficiais das prefeituras; validar o descarte (QR, GPS ou foto); mapa interativo; calcular a Páscoa em vez de tabelar os feriados; persistir a configuração do lembrete e reagendá-lo automaticamente; e implementar a notificação no iOS.

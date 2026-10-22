# Como usar o Git neste projeto

Somos 5 e ninguém tinha rotina de Git em equipe, então o fluxo é simples de propósito.

## Regras

1. **Ninguém commita direto na `main`.** Todo trabalho vai em uma branch e entra por Pull Request (PR).
2. **Um PR é revisado por outra pessoa** antes de entrar. Pode ser rápido (5 minutos).
3. **Branch pequena e curta:** uma tarefa, uma branch, de preferência fechada em 1 ou 2 dias.
4. **Atualize antes de começar:** `git pull` na `main` antes de criar a branch.

## Nome da branch

`tipo/pbXX-resumo` (o número do cartão no Trello), por exemplo:

- `docs/escopo-ajuste-rn`
- `feat/pb08-calendario`
- `test/pb08-proxima-coleta`
- `chore/ambiente-gradle`

## Mensagens de commit

Em português, no imperativo, começando pelo tipo:

```
docs: ajusta regras de negócio do escopo
test: cobre coleta em feriado
feat: calcula próxima coleta do setor
fix: corrige filtro de pontos por material
```

No TDD, o commit `test:` (teste falhando) vem antes do `feat:` (código que faz passar).

## Etapa 2: TDD e revisão

- Cada cartão = 1 branch = 1 Pull Request. Siga o ciclo completo em [docs/guias](docs/guias/) (bíblia do seu Integrante) e as receitas em [docs/RECEITAS.md](docs/RECEITAS.md).
- Commits na ordem: `test:` (falha) → `feat:` (passa) → `refactor:`. Esse histórico é a evidência do TDD; por isso o merge é **merge commit** (nada de squash).
- O CI roda **em todo push** (aba Actions do GitHub): dê push depois de cada commit para ver o vermelho do `test:` e o verde do `feat:`.
- O Integrante 1 revisa e faz o merge de todos os PRs; o CI precisa estar verde.
- Cada um edita só a própria pasta em `funcionalidades/` e o próprio bloco em `AppContainer.kt` e `AppNavegacao.kt`.

## Passo a passo

```bash
git checkout main
git pull
git checkout -b docs/escopo-ajuste-rn
# faça as alterações
git add .
git commit -m "docs: ajusta regras de negócio do escopo"
git push -u origin docs/escopo-ajuste-rn
```

Depois abra o PR no GitHub, marque outra pessoa para revisar e avise no grupo.

## O que não vai para o repositório

- Senhas, chaves de API e arquivos `local.properties` (o `.gitignore` já cobre os mais comuns).
- Dados pessoais de qualquer morador entrevistado.
- Arquivos pesados de vídeo.

## Dúvidas

Pergunte no grupo antes de usar `git push --force` ou `git reset --hard`.

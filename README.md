# Board Game Engine

Engine de jogos de tabuleiro em Java, executada pelo terminal.

**Checkpoint 1:** implementação de um jogo concreto — o jogo da velha (3x3) para dois jogadores — com modelagem orientada a objetos.

## Integrantes

- Filipe Godoy
- Sofia Ruas

## Requisitos

- Java JDK 17 ou superior (confirme com `java -version`)

## Como executar

Clone o repositório e, na raiz do projeto, rode os comandos abaixo.

**Compilar**

Windows (PowerShell):
```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java src).FullName
```

Linux / macOS:
```bash
javac -d out $(find src -name "*.java")
```

**Rodar**
```
java -cp out Main
```

## Como jogar

- O jogo é para dois jogadores, `X` e `O`, que jogam alternadamente.
- A cada rodada, o tabuleiro é exibido com os números das linhas e colunas (1 a 3).
- Digite o número da linha e, em seguida, o número da coluna desejada.
- Jogadas em posições fora do tabuleiro ou já ocupadas são recusadas, com uma mensagem explicando o motivo; a vez não passa para o outro jogador nesse caso.
- Se for digitado um valor que não seja um número, o jogo pede a entrada novamente, sem travar.
- A partida termina automaticamente quando um jogador completa uma linha, coluna ou diagonal (vitória) ou quando o tabuleiro enche sem vencedor (empate).

## Estrutura do projeto

```
src/
├── Main.java                              ponto de entrada do programa
├── board/
│   ├── Board.java                         tabuleiro: estado, impressão, validação, vitória/empate
│   └── Position.java                      posição (linha, coluna) no tabuleiro
├── engine/
│   └── Game.java                          controla a partida: turnos, leitura de jogadas, fim de jogo
├── player/
│   └── Player.java                        nome e símbolo de um jogador
└── exceptions/
    ├── InvalidMoveException.java          jogada em posição já ocupada
    └── InvalidPositionException.java      jogada em posição fora do tabuleiro
```

## Diagrama de classes

Ver [`docs/diagrama-de-classes.md`](docs/diagrama-de-classes.md).
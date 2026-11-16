# Diagrama de classes

Diagrama do Checkpoint 2. O GitHub desenha o bloco abaixo sozinho; a mesma figura também está em `diagrama-de-classes.png`, para quem abrir o arquivo fora do GitHub.

```mermaid
classDiagram
    direction TB

    class Main {
        +main(args) void
    }

    class GameLoader {
        +load(filePath) Game
        -readSymbol(symbols, index) char
    }

    class Game {
        -board : Board
        -players : Player[]
        -currentPlayerIndex : int
        -scanner : Scanner
        -winCondition : WinCondition
        -piecesPerPlayer : int
        -allowsMovement : boolean
        +play() void
        -getCurrentPlayer() Player
        -switchTurn() void
        -readPosition() Position
    }

    class Board {
        -grid : char[][]
        -rows : int
        -columns : int
        +getRows() int
        +getColumns() int
        +isValidPosition(pos) boolean
        +isEmpty(pos) boolean
        +placePiece(pos, symbol) void
        +movePiece(from, to, symbol) void
        +getPiece(pos) char
        +countPieces(symbol) int
        +isFull() boolean
        +print() void
    }

    class Position {
        -row : int
        -column : int
        +getRow() int
        +getColumn() int
        +toString() String
    }

    class Player {
        -name : String
        -symbol : char
        +getName() String
        +getSymbol() char
    }

    class WinCondition {
        <<interface>>
        +isMet(board, symbol) boolean
    }

    class AlignmentWinCondition {
        +isMet(board, symbol) boolean
    }

    class InvalidMoveException
    class InvalidPositionException
    class InvalidConfigurationException

    Exception <|-- InvalidMoveException
    Exception <|-- InvalidPositionException
    Exception <|-- InvalidConfigurationException

    WinCondition <|.. AlignmentWinCondition

    Game *-- Board
    Game *-- "2" Player
    Game --> WinCondition
    Board ..> Position
    AlignmentWinCondition ..> Board

    Main ..> GameLoader : usa
    GameLoader ..> Game : cria
    GameLoader ..> InvalidConfigurationException : lança
    Board ..> InvalidMoveException : lança
    Board ..> InvalidPositionException : lança
```

## Como ler

Setas tracejadas indicam dependência (uma classe usa a outra, sem guardá-la como atributo). Losango preenchido indica composição: o `Game` guarda o `Board` e os dois `Player`. A seta cheia do `Game` para `WinCondition` é uma associação: o jogo guarda uma regra de vitória, mas não sabe qual implementação é.

## O que mudou desde o Checkpoint 1

- `WinCondition` é uma interface, e `AlignmentWinCondition` a implementa. O `Game` só conhece a interface, então trocar a regra de vitória não exige mexer nele.
- `GameLoader` é novo. Ele lê o JSON, valida e monta o `Game` pronto.
- `InvalidConfigurationException` é nova e cobre os erros de arquivo de configuração.
- `Game` recebe `piecesPerPlayer` e `allowsMovement`. Com `allowsMovement` verdadeiro, ele passa por uma fase de colocar peças e depois por uma fase de mover (Picaria). Com falso, só coloca (jogo da velha).
- `Board` ganhou `movePiece`, `countPieces`, `getRows` e `getColumns`.

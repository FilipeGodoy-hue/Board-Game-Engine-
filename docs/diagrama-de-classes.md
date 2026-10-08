# Diagrama de Classes — Board Game Engine (Checkpoint 1)

```mermaid
classDiagram
    class Main {
        +main(String[] args) void
    }

    class Game {
        -Board board
        -Player[] players
        -int currentPlayerIndex
        -Scanner scanner
        +Game(Player player1, Player player2)
        +play() void
        -getCurrentPlayer() Player
        -switchTurn() void
        -readPosition() Position
    }

    class Board {
        -char[][] grid
        -int rows
        -int columns
        -char EMPTY$
        +Board(int rows, int columns)
        +isValidPosition(Position pos) boolean
        +isEmpty(Position pos) boolean
        +placePiece(Position pos, char symbol) void
        +getPiece(Position pos) char
        +print() void
        +checkWinner(char symbol) boolean
        +isFull() boolean
    }

    class Position {
        -int row
        -int column
        +Position(int row, int column)
        +getRow() int
        +getColumn() int
        +toString() String
    }

    class Player {
        -String name
        -char symbol
        +Player(String name, char symbol)
        +getName() String
        +getSymbol() char
    }

    class InvalidMoveException {
        +InvalidMoveException(String message)
    }

    class InvalidPositionException {
        +InvalidPositionException(String message)
    }

    Main ..> Game : cria
    Main ..> Player : cria
    Game "1" o-- "1" Board : possui
    Game "1" o-- "2" Player : possui
    Game ..> Position : usa
    Board ..> Position : usa
    Board ..> InvalidMoveException : lança
    Board ..> InvalidPositionException : lança
    InvalidMoveException --|> Exception
    InvalidPositionException --|> Exception
```

## Leitura do diagrama

- **Main** cria dois `Player` e um `Game`, e inicia a partida chamando `play()`.
- **Game** é o controlador da partida: possui um `Board` e dois `Player` (composição — não existem fora de um `Game`), controla de quem é a vez e lê as jogadas digitadas pelo usuário.
- **Board** representa o tabuleiro e concentra as regras de posição, marcação, impressão, vitória e empate. Ele usa `Position` para localizar casas e lança `InvalidMoveException` / `InvalidPositionException` quando uma jogada é inválida.
- **Position** é um objeto simples de valor (linha e coluna).
- **Player** guarda o nome e o símbolo (`X` ou `O`) de cada jogador.
- **InvalidMoveException** e **InvalidPositionException** estendem `Exception`, criando tipos de erro próprios do jogo em vez de usar exceções genéricas.

Legenda: `o--` = composição (Game é dono de Board/Player), `..>` = dependência (uma classe usa outra), `--|>` = herança.# Diagrama de Classes — Board Game Engine (Checkpoint 1)

```mermaid
classDiagram
    class Main {
        +main(String[] args) void
    }

    class Game {
        -Board board
        -Player[] players
        -int currentPlayerIndex
        -Scanner scanner
        +Game(Player player1, Player player2)
        +play() void
        -getCurrentPlayer() Player
        -switchTurn() void
        -readPosition() Position
    }

    class Board {
        -char[][] grid
        -int rows
        -int columns
        -char EMPTY$
        +Board(int rows, int columns)
        +isValidPosition(Position pos) boolean
        +isEmpty(Position pos) boolean
        +placePiece(Position pos, char symbol) void
        +getPiece(Position pos) char
        +print() void
        +checkWinner(char symbol) boolean
        +isFull() boolean
    }

    class Position {
        -int row
        -int column
        +Position(int row, int column)
        +getRow() int
        +getColumn() int
        +toString() String
    }

    class Player {
        -String name
        -char symbol
        +Player(String name, char symbol)
        +getName() String
        +getSymbol() char
    }

    class InvalidMoveException {
        +InvalidMoveException(String message)
    }

    class InvalidPositionException {
        +InvalidPositionException(String message)
    }

    Main ..> Game : cria
    Main ..> Player : cria
    Game "1" o-- "1" Board : possui
    Game "1" o-- "2" Player : possui
    Game ..> Position : usa
    Board ..> Position : usa
    Board ..> InvalidMoveException : lança
    Board ..> InvalidPositionException : lança
    InvalidMoveException --|> Exception
    InvalidPositionException --|> Exception
```

## Leitura do diagrama

- **Main** cria dois `Player` e um `Game`, e inicia a partida chamando `play()`.
- **Game** é o controlador da partida: possui um `Board` e dois `Player` (composição — não existem fora de um `Game`), controla de quem é a vez e lê as jogadas digitadas pelo usuário.
- **Board** representa o tabuleiro e concentra as regras de posição, marcação, impressão, vitória e empate. Ele usa `Position` para localizar casas e lança `InvalidMoveException` / `InvalidPositionException` quando uma jogada é inválida.
- **Position** é um objeto simples de valor (linha e coluna).
- **Player** guarda o nome e o símbolo (`X` ou `O`) de cada jogador.
- **InvalidMoveException** e **InvalidPositionException** estendem `Exception`, criando tipos de erro próprios do jogo em vez de usar exceções genéricas.

Legenda: `o--` = composição (Game é dono de Board/Player), `..>` = dependência (uma classe usa outra), `--|>` = herança.

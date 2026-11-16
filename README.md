# Board Game Engine

Motor de jogos de tabuleiro em Java, feito para a disciplina de LPOO (UPE). A ideia é que o mesmo código rode jogos diferentes, e que o jogo seja definido por um arquivo JSON em vez de estar escrito direto no programa. Até agora o motor roda dois jogos: Tic-Tac-Toe (jogo da velha) e Picaria.

Autores: Sofia Ruas Machado Marques e Filipe Godoy.

## Como compilar e rodar

É preciso ter o JDK instalado. A única biblioteca externa é o `org.json`, e o jar já está na pasta `lib/`. Rode os comandos a partir da raiz do projeto, porque os caminhos dos arquivos JSON são relativos a ela.

No Windows (PowerShell):

```powershell
javac -cp "lib/json-20240303.jar" -d out (Get-ChildItem -Recurse -Filter *.java src).FullName
java -cp "out;lib/json-20240303.jar" Main
```

No Linux ou macOS:

```bash
javac -cp lib/json-20240303.jar -d out $(find src -name "*.java")
java -cp "out:lib/json-20240303.jar" Main
```

O programa mostra um menu para escolher o jogo:

```
=== Board Game Engine ===
1) Tic-Tac-Toe
2) Picaria
Choose a Game:
```

Nas partidas, a linha e a coluna são digitadas de 1 em diante, uma por vez.

## Os jogos

Tic-Tac-Toe. Tabuleiro 3x3, dois jogadores colocam uma peça por turno em uma casa vazia. Ganha quem completar uma linha, uma coluna ou uma diagonal. Se o tabuleiro encher sem vencedor, dá empate.

Picaria. Usamos a versão de 9 pontos, que ocupa o mesmo tabuleiro 3x3. Cada jogador tem 3 peças. Primeiro os dois colocam suas peças, uma por turno. Depois que todas estão no tabuleiro, cada turno consiste em mover uma peça própria para uma casa vazia adjacente (vertical, horizontal ou diagonal). A condição de vitória é a mesma do jogo da velha: alinhar as três peças. Como as peças só se movem, o tabuleiro nunca enche, então a Picaria não tem empate no nosso código.

## Arquivos de configuração

Cada jogo é descrito por um JSON na pasta `games/`. Exemplo, o da Picaria:

```json
{
  "game": "Picaria",
  "board": { "rows": 3, "columns": 3 },
  "players": { "count": 2, "symbols": ["X", "O"] },
  "rules": {
    "placement": "EMPTY_POSITION",
    "winCondition": { "type": "ALIGNMENT", "quantity": 3 },
    "piecesPerPlayer": 3,
    "allowsMovement": true
  }
}
```

O do Tic-Tac-Toe tem o mesmo formato, com `"allowsMovement": false` e sem `piecesPerPlayer`.

| Campo | O que faz | Usado pelo motor |
|---|---|---|
| `game` | nome do jogo | lido, mas ainda não exibido |
| `board.rows`, `board.columns` | tamanho do tabuleiro | sim |
| `players.symbols` | símbolo de cada jogador (um caractere cada) | sim |
| `players.count` | número de jogadores | não, o motor sempre usa 2 |
| `rules.placement` | onde se pode colocar peças | não, qualquer casa vazia vale |
| `rules.winCondition.type` | tipo de regra de vitória; hoje só existe `ALIGNMENT` | sim |
| `rules.winCondition.quantity` | quantas peças alinhar | não, a regra confere a linha, coluna ou diagonal inteira |
| `rules.piecesPerPlayer` | peças de cada jogador, para jogos com fase de colocação | sim, quando há movimento |
| `rules.allowsMovement` | se, depois de colocar, as peças se movem | sim |

Para ver a configuração mudar o jogo, basta editar o arquivo e rodar de novo, sem recompilar. Por exemplo, trocar `["X", "O"]` por `["A", "B"]` faz a partida usar esses símbolos.

## Validação da configuração

O `GameLoader` confere o arquivo antes de montar o jogo. Se algo estiver errado, o programa mostra uma mensagem e termina, sem stack trace. Os casos cobertos:

- tabuleiro com linhas ou colunas menores ou iguais a zero;
- campo obrigatório ausente ou com tipo errado (por exemplo, falta `rows`);
- símbolo que não tenha exatamente um caractere;
- os dois jogadores com o mesmo símbolo;
- tipo de regra de vitória desconhecido;
- com movimento, `piecesPerPlayer` ausente, menor ou igual a zero, ou grande demais para caber no tabuleiro.

Exemplos de saída:

```
Error loading game: Unknown win condition type: SUPERWIN
Error loading game: Invalid configuration file: JSONObject["rows"] not found.
Error loading game: Invalid configuration file: All symbols need to have 1 character
```

## Estrutura do projeto

```
games/        arquivos JSON de cada jogo
lib/          biblioteca org.json
docs/         diagrama de classes
src/
  Main.java           menu e ponto de entrada
  config/             GameLoader
  engine/             Game (loop do jogo)
  board/              Board e Position
  player/             Player
  rules/              WinCondition e AlignmentWinCondition
  exceptions/         exceções do projeto
```

O diagrama completo está em [docs/diagrama-de-classes.md](docs/diagrama-de-classes.md).

## Como as peças se encaixam

O `Main` pergunta o jogo e entrega o caminho do JSON ao `GameLoader`. O loader lê e valida o arquivo, cria o `Board` e os dois `Player`, escolhe a regra de vitória e devolve um `Game` pronto. O `Game` só cuida do fluxo de turnos: lê a posição, pede ao `Board` para colocar ou mover, pergunta à regra de vitória se alguém ganhou e passa a vez.

Alguns conceitos de orientação a objetos que aparecem no código:

- Encapsulamento: os atributos de `Board`, `Game`, `Player` e `Position` são privados, e o acesso é por métodos.
- Interface e polimorfismo: `WinCondition` é uma interface e o `Game` usa só ela. O `GameLoader` escolhe a implementação (`AlignmentWinCondition`) a partir do campo `type` do JSON. Uma regra nova seria uma nova classe e um novo `case` no loader.
- Composição: o `Game` é composto por um `Board`, dois `Player` e uma `WinCondition`, que o loader entrega pelo construtor.
- Herança: as exceções do projeto estendem `Exception`.
- Coleções e arrays: o tabuleiro é uma matriz de caracteres e os jogadores ficam em um array; o loader percorre o `JSONArray` dos símbolos.
- Tratamento de exceções: `InvalidPositionException` e `InvalidMoveException` são tratadas dentro do `Game`, que mostra a mensagem e repete a jogada. `InvalidConfigurationException` é tratada no `Main`.

## Limitações conhecidas

- A verificação de diagonais só vale para tabuleiros quadrados.
- O campo `quantity` ainda não é usado, então a vitória é sempre a linha, coluna ou diagonal inteira.
- A validação de `piecesPerPlayer` está ligada ao `allowsMovement`. Jogos com movimento mas peças fixas desde o início (como o xadrez) precisariam de outra forma de configurar isso.
- Na Picaria, o movimento aceita qualquer casa adjacente, incluindo diagonais a partir de qualquer ponto. No tabuleiro tradicional, as diagonais só existem a partir de alguns pontos.
- As mensagens de erro de posição ainda falam em "1-3", o que só faz sentido para tabuleiros 3x3.
- O motor sempre trabalha com dois jogadores.

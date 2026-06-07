## **BVA Analysis for GameManager()**

**Method under test: start()**

|             | System under test                           | Expected output | Implemented? |
|:------------|:--------------------------------------------| :---- |:-------------|
| Test Case 1 | Starting with 2 players                     | Game initializes successfully, players are assigned black/white, no exception | yes          |
| Test Case 2 | Starting with 1 players                     | Game fails to initialize due to insufficient number of players and returns an exception. | yes          |
| Test Case 3 | Starting with 0 players                     | Game fails to initialize due to insufficient number of players and returns an exception. | yes          |
| Test Case 4 | Starting with \>2 players                   | Game fails to start and returns an exception. | yes          |
| Test Case 5 | Starting with 2 players with the same color | Game fails to start and returns an exception. | yes          |

### STEPS FOR BVA: `start()`

1. Data Types
* Input: cases
  * Input number of players \== 0
  * Input number of players \== 1
  * Input number of players \== 2
  * Input number of players \== 3
* Output: void/Exception
2. Test Cases: 
* All combinations
  * 0 → ❌
  * 1 → ❌
  * 2 → ✅
  * 3 → ❌

**Method under test: changeTurns()**

|             | System under test                  | Expected output                                                                          | Implemented? |
|:------------|:-----------------------------------|:-----------------------------------------------------------------------------------------|:-------------|
| Test Case 1 | Transitioning from white to black. | UI change to show that it is the black player’s move. Only that player can move a piece. | yes          |
| Test Case 2 | Transitioning from black to white. | UI change to show that it is the white player’s move. Only that player can move a piece. | yes          |
| Test Case 3 | A tie/draw.                        | Neither player can move a piece, notification that the game ended in a draw.             | yes          |
| Test Case 4 | Game initially starts with White.  | The current player is White.                                                             | yes          |


### STEPS FOR BVA: `changeTurns()`
1. Data Types
* Input: cases
  * Input player color \== “B”
  * Input player color \== “W”
* Output: void/Exception
  * Function does not return anything, but currentPlayer should be changed.
2. Test Cases
* All combinations
  * “B” → “W”
  * “W” → “B”

**Method under test: isGameOver()**

|             | System under test                                          | Expected output | Implemented? |
|:------------|:-----------------------------------------------------------| :---- |:-------------|
| Test Case 1 | King is in check and has no other valid moves.             | Returns true, game notification that the game is over. | yes          |
| Test Case 2 | King is in check and has >=1 valid move.                   | Returns false, the game continues. | yes          |
| Test Case 3 | Stalemate \- player is not in check but has 0 valid moves. | Returns true, game is a draw.  | yes          |
 | Test Case 4 | King is not in check and has >=1 valid move.               | Returns false, the game continues. | yes          |

### STEPS FOR BVA: `isGameOver()`

1. Data Types
* Input: cases 
  * No more valid moves and King isInCheck() is true
  * \>=1 valid move
* Output: boolean
  * True, False
2. Test Cases
* All combinations
  * King isInCheck() == true && there are 0 valid moves → ✅ 
  * King isInCheck() == true && there are >= 1 valid moves → ❌
  * King isInCheck() == false && there are 0 valid moves → Stalemate
  * King isInCheck() == false && there are >= 1 valid moves → ❌

## Method under test: `getMessage()` for GameManager

| Test Number | Locale                         | Key               | Key exists ? | Expected Output          | Implemented? |
|-------------|--------------------------------|-------------------|--------------|--------------------------|--------------|
| 1           | none set                       | `start.game`      | yes          | `"Start Game"` (default) | yes          |
| 2           | english                        | `start.game`      | yes          | `"Start Game"`           | yes          |
| 3           | spanish                        | `start.game`      | yes          | `"Iniciar Juego"`        | yes          |
| 4           | english then switch to spanish | `start.game`      | yes          | `"Iniciar Juego"`        | no           |
| 5           | english                        | `nonexistent.key` | no           | `"nonexistent.key"`      | no           |
| 6           | english                        | `checkmate`       | yes          | `"Checkmate!"`           | no           |
| 7           | spanish                        | `checkmate`       | yes          | `"¡Jaque mate!"`         | no           |
| 8           | french (new locale)            | `start.game`      | yes          | `"Démarrer le jeu"`      | no           |

### STEPS FOR BVA: `getMessage()`

1) input equivalence classes and output equivalence classes
* input:
  * locale 
  * key (the string)
  * whether the key exists 
* output:
  * the translated string, or the key itself 

2) BVA catalog classes
* input:
  * locale: cases
  * key: cases
  * key exists in resources: boolean
* output:
  * a string: value

3) BVA catalog classes -- values
* input:
  * locale: cases
    * none set (should default to English)
    * ENGLISH
    * SPANISH
    * ENGLISH then switch to SPANISH (supports changing locales)
    * new locale being added (FRENCH)
  * key: cases
    * valid key that exists in the resources
    * invalid key that does not exist in the resources
  * key exists in resources: boolean
    * True 
    * False 
* output:
  * a string: value
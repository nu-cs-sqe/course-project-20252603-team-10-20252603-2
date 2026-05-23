## **BVA Analysis for GameManager()**

**Method under test: start()**

|             | System under test                           | Expected output | Implemented? |
|:------------|:--------------------------------------------| :---- |:-------------|
| Test Case 1 | Starting with 2 players                     | Game initializes successfully, players are assigned black/white, no exception | yes          |
| Test Case 2 | Starting with 1 players                     | Game fails to initialize due to insufficient number of players and returns an exception. | yes          |
| Test Case 3 | Starting with \>2 players                   | Game fails to start and returns an exception. | yes          |
| Test Case 4 | Starting with 2 players with the same color | Game fails to start and returns an exception. | yes          |

### STEPS FOR BVA: `start()`

1. Data Types
* Input: cases
  * Input number of players \== 0
  * Input number of players \== 1
  * Input number of players \== 2
* Output: void/Exception
2. Test Cases: 
* All combinations
  * 0 → ❌
  * 1 → ❌
  * 2 → ✅
  * 3 → ❌

**Method under test: changeTurns()**

|             | System under test                  | Expected output                                                                          | Implemented? |
|:------------|:-----------------------------------|:-----------------------------------------------------------------------------------------| :---- |
| Test Case 1 | Transitioning from white to black. | UI change to show that it is the black player’s move. Only that player can move a piece. | no |
| Test Case 2 | Transitioning from black to white. | UI change to show that it is the white player’s move. Only that player can move a piece. | no |
| Test Case 3 | A tie/draw.                        | Neither player can move a piece, notification that the game ended in a draw.             | no |
| Test Case 4 | Game initially starts with White.  | The current player is White.                                                             | no |


### STEPS FOR BVA: `changeTurns()`
1. Data Types
* Input: cases
  * Input player color \== “B”
  * Input player color \== “W”
* Output: void/Exception
2. Test Cases
* All combinations
  * “B” → “W”
  * “W” → “B”

**Method under test: isGameOver()**

|             | System under test                                          | Expected output | Implemented? |
|:------------|:-----------------------------------------------------------| :---- | :---- |
| Test Case 1 | King is in check and has no other valid moves.             | Returns true, game notification that the game is over. | no |
| Test Case 2 | King is in check and has >=1 valid move.                   | Returns false, the game continues. | no |
| Test Case 3 | Stalemate \- player is not in check but has 0 valid moves. | Returns true, game is a draw.  | no |
 | Test Case 4 | King is not in check and has >=1 valid move.               | Returns false, the game continues. | no |

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
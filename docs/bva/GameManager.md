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


**Method under test: movePiece()**

| Test Number | Current Turn | Location 1           | Location 2 | Piece being moved | Location 2 contents | Expected output                                                               | Implemented? |
|:------------|:-------------|:---------------------|:-----------|:------------------|:--------------------|:------------------------------------------------------------------------------|:-------------|
| 1           | WHITE        | (4,0)                | (5,0)      | null              | empty               | MoveResult.NO_PIECE_SELECTED (board/turn unchanged)                           | yes          |
| 2           | BLACK        | (0,1)                | (2,0)      | BLACK KNIGHT      | empty               | MoveResult.SUCCESS  (board/turn updated)                                      | yes          |                                         
| 3           | WHITE        | (7,6)                | (5,5)      | WHITE KNIGHT      | empty               | MoveResult.SUCCESS  (board/turn updated)                                      | yes          |                                          
| 4           | BLACK        | (0,0)                | (1,0)      | BLACK ROOK        | friendly            | MoveResult.INVALID_MOVE (board/turn unchanged)                                | yes          |                                         
| 5           | BLACK        | (2,0) (custom board) | (6,0)      | BLACK ROOK        | enemy               | MoveResult.SUCCESS  (board/turn updated, point count updated)                 | yes          |                                         
| 6           | WHITE        | (5,7) (custom board) | (1,1)      | WHITE QUEEN       | empty               | MoveResult.INVALID_MOVE (board/turn unchanged, point NOT count updated)       | yes          |                                         
| 7           | BLACK        | (7,6)                | (5,5)      | WHITE KNIGHT      | empty               | MoveResult.WRONG_PLAYER_PIECE (board/turn unchanged)                          | yes          |                                         
| 8           | BLACK        | (6,0) (custom board) | (7,0)      | BLACK PAWN        | empty               | MoveResult.PROMOTION_REQUIRED (board updated, turn unchanged until promotion) | yes          |                                         


### STEPS FOR BVA: `movePiece()`

1. Data Types
* Input: 
  * color of current turn
  * piece being moved
  * location 1
  * location 2
  * State of location 2
* Output:
  * A varying output that tells me if the piece move was successful or, if not, what the problem was
    * e.g. no piece selected, wrong player's piece selected, invalid move, success, pawn must be promoted
  * Side effects:
    * board state updated/unchanged
    * turn changed/unchanged
    * points updated if captured
2. BVA catalog types
* Input:
  * color of current turn: cases
  * Piece: pointer
  * Location 1: array indices
  * Location 2: array indices
  * State of location 2: cases
* Output
  * move result: cases:
  * Side effects: cases
3. Values
* Input:
  * Color of current turn: cases
    * BLACK
    * WHITE
  * Piece: pointer
    * Real piece object
    * Null pointer
  * Location 1: array indices
    * all indices are 0 at same time: [0][0] already tested in Location and in PieceType Tests
    * all largest valid value: [7][7] already tested in Location and in PieceType Tests
    * first index -1: [-1][3], CANT SET
    * second index -1: [3][-1] , CANT SET
    * first element too big: [8][3], CANT SET
    * second element too big: [3][8], CANT SET
  * Location 2: array indices
    * all indices are 0 at same time: [0][0] already tested in Location and in PieceType Tests
    * all largest valid value: [7][7] already tested in Location and in PieceType Tests
    * first index -1: [-1][3], CANT SET
    * second index -1: [3][-1] , CANT SET
    * first element too big: [8][3], CANT SET
    * second element too big: [3][8], CANT SET
  * State of location 2:
    * empty square
    * friendly square
    * enemy square
* Output
  * move result: cases:
    * NO_PIECE_SELECTED
    * WRONG_PLAYER_PIECE
    * INVALID_MOVE
    * SUCCESS
    * PROMOTION_REQUIRED
  * Side effects: cases
    * board state updated/unchanged
    * turn changed/unchanged
    * points updated if captured


**Method under test: promotePawn()**

| Test Number | PIECE        | Location | Replace with | Expected output                                                  | Implemented? |
|:------------|:-------------|:---------|:-------------|:-----------------------------------------------------------------|:-------------|
| 1           | WHITE PAWN   | (0,0)    | QUEEN        | white pawn replaced with white queen                             | yes          |
| 2           | BLACK PAWN   | (7,7)    | QUEEN        | black pawn replaced with black queen                             | yes          |
| 3           | WHITE PAWN   | (0,0)    | KNIGHT       | white pawn replaced with white knight                            | yes          |
| 4           | WHITE PAWN   | (0,0)    | BISHOP       | white pawn replaced with white bishop                            | yes          |
| 5           | WHITE PAWN   | (0,0)    | ROOK         | white pawn replaced with white rook                              | yes          |
| 6           | null         | (0,0)    | ROOK         | IllegalArgumentException ("Piece is not eligible for promotion") | yes          |
| 7           | BLACK KNIGHT | (7,7)    | QUEEN        | IllegalArgumentException ("Piece is not eligible for promotion") | yes          |
| 8           | WHITE PAWN   | (0,0)    | KING         | IllegalArgumentException ("Invalid promotion piece")             | yes          |


### STEPS FOR BVA: `promotePawn()`

1. Data Types
* Input:
  * pawn object
  * color of pawn
  * location to promote pawn
  * type of new piece (e.g. queen, rook, bishop, knight)
* Output:
  * void but the side effect of changing the pawn at the given location to the specified piece
2. BVA catalog types
* Input:
  * pawn object: pointer
  * color of pawn: cases
  * location to promote pawn: array indices (2d)
  * type of new piece: cases(e.g. queen, rook, bishop, knight)
* Output
  * void with side effects: cases
3. Values
* Input:
  * pawn object: pointer
    * pawn object
    * different piece
    * null
  * color of pawn: cases
    * BLACK
    * WHITE
  * location to promote pawn: array indices (2d)
    * all indices are 0 at same time: [0][0] 
    * all largest valid value: [7][7] 
    * first index -1: [-1][3], CANT SET
    * second index -1: [3][-1] , CANT SET
    * first element too big: [8][3], CANT SET
    * second element too big: [3][8], CANT SET
  * type of new piece: cases
    * QUEEN
    * KNIGHT
    * ROOK
    * BISHOP
* Output
  * void with side effects: cases
    * pawn -> queen
    * pawn -> rook
    * pawn -> bishop
    * pawn -> knight
    * non pawn rejected
    * empty location rejected
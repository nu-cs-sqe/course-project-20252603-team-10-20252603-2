# BVA Analysis for Player

**Method under test: getPoints()**

|  | System under test | Expected output                                                                              | Implemented? |
| :---- | :---- |:---------------------------------------------------------------------------------------------|:-------------|
| Test Case 1 | There are 0 captured pieces. | getCapturedPieces(): \[\] getPoints(): 0                                                     | yes          |
| Test Case 2 | All pieces were captured, assuming no pawns have been promoted. | getCapturedPieces(): \[all pieces\] getPoints() returns 39                              | yes          |
| Test Case 3 | One piece has been captured (ex. pawn) | getCapturedPieces(): \[pawn\] getPoints() returns 1                                          | yes          |
| Test Case 4 | All pieces were captured, with \>1 pawn being promoted. | getCapturedPieces(): \[all pieces, including promoted pieces\] getPoints() returns \>39 | yes          |

### STEPS FOR BVA: `getCapturedPieces() and getTotalPoints()`

1. input equivalence classes and output equivalence classes
* Input: Board and getCapturedPieces()
* Output: Number
2. determine data types
* Input: cases
    * Input getCapturedPieces() for all pieces
        * 0 pieces captured
        * 1 piece captured
        * \>1 piece captured
* Output: Number
    * 0
    * \<= 39
    * \> 39

**Method under test: isInCheck()**

|  | System under test | Expected output | Implemented? |
| :---- | :---- | :---- | :---- |
| Test Case 1 | King is on a safe square | isInCheck() returns false  | no |
| Test Case 2 | King is on a threatened square | isInCheck() returns true  | no |

### STEPS FOR BVA: `isInCheck()`

1. input equivalence classes and output equivalence classes
* Input: Board, King’s position
* Output: boolean
2. determine data types
* Input: Board object and King’s current position
* Output: boolean
    * True
    * False

**Method under test: isCurrentTurn()**

|  | System under test | Expected output | Implemented? |
| :---- | :---- | :---- | :---- |
| Test Case 1 | Current player’s turn | isCurrentTurn() returns true  | no |
| Test Case 2 | Opposing player’s turn | isCurrentTurn() returns false  | no |

### STEPS FOR BVA: `isCurrentTurn()`

1. input equivalence classes and output equivalence classes
* Input: GameManager’s currentTurn()
* Output: boolean
2. determine data types
* Input: GameManager’s currentTurn()
* Output: boolean
    * True
    * False
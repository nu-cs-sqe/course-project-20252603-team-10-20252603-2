# BVA Analysis for Player

**Method under test: getPoints()**

|  | System under test | Expected output | Implemented? |
| :---- | :---- | :---- | :---- |
| Test Case 1 | There are 0 captured pieces. | getPoints(): 0 | yes |
| Test Case 2 | All pieces were captured, assuming no pawns have been promoted. | getPoints() returns 39 | yes |
| Test Case 3 | One piece has been captured (ex. pawn) | getPoints() returns 1 | yes |
| Test Case 4 | All pieces were captured, with \>=1 pawn being promoted. | getPoints() returns \>39 | yes |
| Test Case 5 | One Queen was captured. | getPoints() returns 9 | no |

### STEPS FOR BVA: `getPoints()`

1. input equivalence classes and output equivalence classes
* Input
    * Cases
        * Player’s color: WHITE
        * Player’s color: BLACK
    * Board (2D array)
* Output: Number
2. determine data types
* Input
    * Cases
        * Player’s color: WHITE
        * Player’s color: BLACK
    * Board (2D array)
* Output: Number
    * 0
    * \<= 39
    * \> 39


**Method under test: isInCheck()**

| Test | Color | King Position | Threat Type | Threat Position | Blocking Piece?          | Expected Output | Implemented? |
| ----- | :---- | :---- | :---- | :---- |:-------------------------| ----- | :---- |
| 1 | WHITE | \[4\]\[4\] | NONE | N/A | No                       | FALSE | no |
| 2 | WHITE | \[0\]\[0\] | ROOK | \[0\]\[7\] | No                       | TRUE | no |
| 3 | WHITE | \[0\]\[0\] | ROOK | \[0\]\[7\] | YES (Pawn at \[0\]\[1\]) | FALSE | no |
| 4 | WHITE | \[7\]\[7\] | KNIGHT | \[5\]\[6\] | No                       | TRUE | no |
| 5 | WHITE | \[4\]\[4\] | BISHOP | \[5\]\[5\] | No                       | TRUE | no |
| 6 | BLACK | \[0\]\[4\] | PAWN | \[1\]\[3\] | No                       | TRUE | no |
| 7 | BLACK | \[0\]\[4\] | PAWN | \[1\]\[4\] | No                       | FALSE | no |
| 8 | WHITE | \[7\]\[0\] | QUEEN | \[0\]\[7\] | No                       | TRUE | no |

### 

### STEPS FOR BVA: `isInCheck()`

1. input equivalence classes and output equivalence classes
* Input:
    * King Color: cases (BLACK, WHITE)
    * King Position: array indices
    * Attacking Piece Position: array indices
    * Blocking Piece: boolean
    * Movement pattern: cases
* Output: boolean
2. determine data types
* Input:
    * King Color: cases (BLACK, WHITE)
    * King Position: array indices
    * Attacking Piece Position: array indices
    * Blocking Piece: boolean
    * Movement pattern: cases
* Output: boolean
    * True
    * False

| Input | Boundary / Case Values                                                                                                                                                                |
| :---- |:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| King Color | BLACK, WHITE                                                                                                                                                                          |
| King Position (row, col) | Min boundary (Corner): \[0\]\[0\] <br/>Max boundary (Corner): \[7\]\[7\] <br/>Edge boundary: \[0\]\[4\] or \[4\]\[7\] <br/>Out of bounds: \[-1\]\[0\] or \[8\]\[0\] (CANT SET)        |
| Attacking Piece Position (row, col) | Minimum distance: \[1\]\[1\] from King <br/>Maximum distance: \[0\]\[7\] if King is at \[0\]\[0\] <br/>Out of bounds: \[3\]\[-1\] or \[3\]\[8\] (CANT SET) |
| Blocking Piece | True: No pieces between Attacker and King. <br/>False: At least one piece (friendly or enemy) exists on the line of sight.                                                            |
| Movement pattern | Pawn: One square diagonally forward. <br/>Knight: Exactly "L-shape" away. <br/>Rook: Same row or column.<br/>Bishop: same diagonal. <br/>Queen: diagonal, same row or same column     |

**Method under test: isCurrentTurn()**

|  | System under test | Expected output | Implemented? |
| :---- | :---- | :---- | :---- |
| Test Case 1 | Current player’s turn | isCurrentTurn() returns true  | no |
| Test Case 2 | Opposing player’s turn | isCurrentTurn() returns false  | no |

### STEPS FOR BVA: `isCurrentTurn()`

1. input equivalence classes and output equivalence classes
* Input: 
  * Cases
    * Player’s color: WHITE
    * Player’s color: BLACK
* Output: boolean
2. determine data types
* Input:
    * Cases
        * Player’s color: WHITE
        * Player’s color: BLACK
* Output: boolean
    * True
    * False
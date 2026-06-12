# BVA Analysis for Bishop Piece

### Method under test: `isValidMove()` for Bishop


| Test Number | Color    | Start position | Chosen position | Chosen position contents | Is path clear? | Movement pattern                                      | Expected output                                                             | Implemented? |
|-------------|----------|----------------|-----------------|--------------------------|----------------|-------------------------------------------------------|-----------------------------------------------------------------------------|--------------|
| 1           | "WHITE"  | [0][0]         | [0][0]          | EMPTY                    | True           | same square                                           | False                                                                       | yes          |
| 2           | "WHITE"  | [0][0]         | [1][1]          | EMPTY                    | True           | 1 diagonal (row++, col++) min                         | True                                                                        | yes          |
| 3           | "WHITE"  | [0][7]         | [1][6]          | EMPTY                    | True           | 1 diagonal (row++, col--) min                         | True                                                                        | yes          |
| 4           | "WHITE"  | [7][0]         | [6][1]          | EMPTY                    | True           | 1 diagonal (row--, col++) min                         | True                                                                        | yes          |
| 5           | "WHITE"  | [7][7]         | [6][6]          | EMPTY                    | True           | 1 diagonal (row--, col--) min                         | True                                                                        | yes          |
| 6           | "WHITE"  | [0][0]         | [7][7]          | EMPTY                    | True           | 7 diagonal (row++, col++) max                         | True                                                                        | yes          |
| 7           | "WHITE"  | [0][7]         | [7][0]          | EMPTY                    | True           | 7 diagonal (row++, col--) max                         | True                                                                        | yes          |
| 8           | "WHITE"  | [7][0]         | [0][7]          | EMPTY                    | True           | 7 diagonal (row--, col++) max                         | True                                                                        | yes          |
| 9           | "WHITE"  | [7][7]         | [0][0]          | EMPTY                    | True           | 7 diagonal (row--, col--) max                         | True                                                                        | yes          |
| 10          | "WHITE"  | [0][0]         | [3][0]          | EMPTY                    | True           | vertical                                              | False                                                                       | yes          |
| 11          | "WHITE"  | [0][0]         | [0][3]          | EMPTY                    | True           | horizontal                                            | False                                                                       | yes          |
| 12          | "WHITE"  | [0][0]         | [7][7]          | enemy                    | True           | capture diagonal                                      | True                                                                        | yes          |
| 13          | "WHITE"  | [0][0]         | [3][3]          | friendly                 | True           | friendly diagonal                                     | False                                                                       | yes          |
| 14          | "BLACK"  | [0][0]         | [7][7]          | enemy                    | True           | capture diagonal                                      | True                                                                        | yes          |
| 15          | "BLACK"  | [0][0]         | [7][7]          | empty                    | False          | valid move + blocked path                             | False                                                                       | yes          |
| 16          | "BLACK"  | [0][0]         | [7][7]          | empty                    | True           | valid move                                            | True                                                                        | yes          |
| 17          | "WHITE"  | [2][0]         | [3][1]          | EMPTY                    | True           | valid diagonal + board restoration after simulation   | True, bishop remains at [2][0] and [3][1] is empty after call               | yes          |
| 18          | "WHITE"  | [0][0]         | [2][2]          | enemy                    | True           | capture diagonal + board restoration after simulation | True, board has bishop at [0][0] and enemy piece still at [2][2] after call | yes          |
| 19          | "WHITE"  | [2][0]         | [1][1]          | EMPTY                    | True           | valid diagonal that blocks check on allied king       | True                                                                        | yes          |

### STEPS FOR BVA: `isValidMove()` for Bishop

1) input equivalence classes and output equivalence classes
* input:
    * bishop color
    * piece start position (row, column)
    * chosen position (row, column)
    * chosen position contents (empty, enemy piece, friendly piece)
    * path is clear?
    * movement pattern (one of the chess movement types, or not)
* output:
    * a yes/no answer
    * board state after simulated king-safety check is restored

2) BVA catalog classes
* input:
    * bishop color (white or black): cases
    * piece start position (row, column): array indices
    * chosen position (row, column): array indices
    * chosen position contents (empty, enemy piece, friendly piece): cases
    * path is clear?: boolean
    * movement pattern (one of the chess movement types, or not): cases
* output:
    * a yes/no answer: boolean
    * side effect: cases
        * board state after simulated king-safety check is restored

3) BVA catalog classes -- values
* input:
    * bishop color: cases
        * BLACK, WHITE
    * piece start position (row, column): array indices
        * all indices are 0 at same time: [0][0]
        * all largest valid value: [7][7]
        * first index too small: [-1][3], CANT SET
        * second index to small: [3][-1] , CANT SET
        * first index too big: [8][3], CANT SET
        * second index too big: [3][8], CANT SET
    * chosen position (row, column): array indices
        * all indices are 0 at same time: [0][0]
        * all largest valid value: [7][7]
        * first index too small: [-1][3], CANT SET
        * second index to small: [3][-1] , CANT SET
        * first index too big: [8][3], CANT SET
        * second index too big: [3][8], CANT SET
    * chosen position contents (empty, enemy piece, friendly piece): cases
        * chosen position is EMPTY
        * chosen position has an ENEMY piece
        * chosen position has a FRIENDLY piece
    * path is clear?: boolean
        * True, False
    * movement pattern (one of the chess movement types, or not): cases
        * 1 diagonal (row++, col++) - min distance
        * 1 diagonal (row++, col--) - min distance
        * 1 diagonal (row--, col++) - min distance
        * 1 diagonal (row--, col--) - min distance 
        * 7 diagonal (row++, col++) - max distance 
        * 7 diagonal (row++, col--) - max distance 
        * 7 diagonal (row--, col++) - max distance 
        * diagonal (row--, col--) - max distance
        * any vertical (invalid)
        * any horizontal (invalid)
        * same square (invalid)
        * blocked path with valid direction
* output:
    * a yes/no answer: boolean
    * side effect: cases
        * board state after simulated king-safety check is restored


### Method under test: `makeCopy()` for Bishop, basic functionality testing

| Test Number | Color      | Expected output                                           | Implemented? |
|-------------|------------|-----------------------------------------------------------|--------------|
| 1           | "BLACK"    | new non null bishop, same color same type and diff object | yes          |
| 2           | "WHITE"    | new non null bishop, same color same type and diff object | yes          |


## Method under test: `hasValidMoves()` for Bishop

| Test Number | Color   | Start position | State                                                                                        | Expected Output | Implemented? |
|-------------|---------|----------------|----------------------------------------------------------------------------------------------|-----------------|--------------|
| 1           | WHITE   | [4,4]          | Clear board                                                                                  | TRUE            | yes          |
| 2           | WHITE   | [0,0]          | Clear board                                                                                  | TRUE            | yes          |
| 3           | WHITE   | [4,4]          | all 4 diagonal squares blocked by friendly pieces                                            | FALSE           | yes          |
| 4           | WHITE   | [0,0]          | bishop in corder, diagonal square [1,1] blocked by friendly piece                            | FALSE           | yes          |
| 5           | WHITE   | [4,4]          | bishop cannot move without exposing king                                                     | FALSE           | yes          |
| 6           | WHITE   | [4,4]          | one diagonal path open, all others blocked                                                   | TRUE            | yes          |
| 7           | WHITE   | [4,4]          | unprotected enemy piece on diagonal                                                          | TRUE            | yes          |
| 8           | BLACK   | [7,7]          | Clear board                                                                                  | TRUE            | yes          |
| 9           | WHITE   | [4,4]          | only up-left diagonal open; up-right, down-left, and down-right blocked by friendly pieces   | TRUE            | yes          |
| 10          | WHITE   | [4,4]          | only up-right diagonal open; up-left, down-left, and down-right blocked by friendly pieces   | TRUE            | yes          |

### STEPS FOR BVA: `hasValidMoves()` for Bishop

1) input equivalence classes and output equivalence classes
* input:
    * bishop color
    * bishop start position (row, column)
    * state of the board (Board)
    * are any diagonal squares reachable without putting the king in check?
* output:
    * a yes/no answer

2) BVA catalog classes
* input:
    * bishop color: cases
    * bishop start position (row, column): array indices
    * state of the board: cases
    * reachable diagonal square exists: boolean
* output:
    * a yes/no answer: boolean

3) BVA catalog classes -- values
* input:
    * bishop color: cases
        * WHITE, BLACK
    * bishop start position (row, column): array indices
        * all indices are 0 at same time: [0][0] 
        * all largest valid value: [7][7] 
        * center of board: [4][4]
    * state of the board: cases
        * clear board 
        * all immediate diagonal squares blocked by friendly pieces 
        * all diagonal paths attacked, moving exposes king 
        * one diagonal path open, all others blocked by friendlies 
        * enemy piece on diagonal, not protected 
    * reachable diagonal square exists: boolean
        * True 
        * False 
* output:
    * a yes/no answer: boolean

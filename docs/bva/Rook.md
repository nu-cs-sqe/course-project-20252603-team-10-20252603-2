# BVA Analysis for Rook Piece

### Method under test: `isValidMove()` for Rook 

| Test Number | Color   | Start position | Chosen position | Chosen position contents | Is path clear? | Movement pattern                 | Expected output | Implemented? |
|-------------|---------|----------------|-----------------|--------------------------|----------------|----------------------------------|-----------------|--------------|
| 1           | "WHITE" | [0][0]         | [0][0]          | EMPTY                    | True           | same square                      | False           | yes          |
| 2           | "WHITE" | [0][0]         | [0][1]          | EMPTY                    | True           | 1 right (min distance)           | True            | yes          |
| 3           | "WHITE" | [0][0]         | [0][7]          | EMPTY                    | True           | 7 right (max distance)           | True            | yes          |
| 4           | "WHITE" | [0][7]         | [0][6]          | EMPTY                    | True           | 1 left (min distance)            | True            | yes          |
| 5           | "WHITE" | [0][7]         | [0][0]          | EMPTY                    | True           | 7 left (max distance)            | True            | yes          |
| 6           | "WHITE" | [0][0]         | [1][0]          | EMPTY                    | True           | 1 down (min distance)            | True            | yes          |
| 7           | "WHITE" | [0][0]         | [7][0]          | EMPTY                    | True           | 7 down (max distance)            | True            | yes          |
| 8           | "WHITE" | [0][0]         | [3][3]          | EMPTY                    | True           | diag                             | False           | yes          |
| 9           | "WHITE" | [0][0]         | [0][3]          | EMPTY                    | False          | blocked horizontal               | False           | yes          |
| 10          | "WHITE" | [0][0]         | [3][0]          | EMPTY                    | False          | blocked vertical                 | False           | yes          |
| 11          | "WHITE" | [0][0]         | [0][7]          | enemy                    | True           | capture horizontal               | True            | yes          |
| 12          | "WHITE" | [0][0]         | [7][0]          | enemy                    | True           | capture vertical                 | True            | yes          |
| 13          | "WHITE" | [7][0]         | [6][0]          | EMPTY                    | True           | 1 up (min)                       | True            | yes          |
| 14          | "WHITE" | [7][0]         | [0][0]          | EMPTY                    | True           | 7 up (max)                       | True            | yes          |
| 15          | "BLACK" | [0][0]         | [0][3]          | enemy                    | True           | capture horizontal (black rook)  | True            | yes          |
| 16          | "BLACK" | [0][0]         | [0][1]          | friendly                 | True           | friendly horizontal (black rook) | False           | yes          |
| 17          | "WHITE" | [0][0]         | [0][3]          | friendly                 | True           | friendly horizontal              | False           | yes          |
| 18          | "WHITE" | [0][0]         | [3][0]          | friendly                 | True           | friendly vertical                | False           | yes          |
| 19          | "BLACK" | [0][0]         | [7][0]          | enemy                    | True           | capture vertical (black rook)    | True            | yes          |
| 20          | "BLACK" | [7][7]         | [7][6]          | empty                    | True           | 1 left from bottom corner        | True            | yes          |
| 21          | "WHITE" | [7][7]         | [6][7]          | empty                    | True           | 1 up from bottom corner          | True            | yes          |

### STEPS FOR BVA: `isValidMove()` for Rook

1) input equivalence classes and output equivalence classes
* input:
    * rook color
    * piece start position (row, column)
    * chosen position (row, column)
    * chosen position contents (empty, enemy piece, friendly piece)
    * path is clear?
    * movement pattern (one of the chess movement types, or not)
* output:
    * a yes/no answer

2) BVA catalog classes
* input:
    * rook color (white or black): cases
    * piece start position (row, column): array indices
    * chosen position (row, column): array indices
    * chosen position contents (empty, enemy piece, friendly piece): cases
    * path is clear?: boolean
    * movement pattern (one of the chess movement types, or not): cases
* output:
    * a yes/no answer: boolean

3) BVA catalog classes -- values
* input:
    * rook color: cases
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
        * one horizontal 
        * one vertical 
        * seven horizontal 
        * seven vertical 
        * diagonal left/right (invalid)
        * same square
        * too far (out of bounds)
        * blocked path with valid direction
* output:
    * a yes/no answer: boolean


### Method under test: `makeCopy()` for Rook, basic functionality testing

| Test Number | Color      | Expected output                                          | Implemented? |
|-------------|------------|----------------------------------------------------------|--------------|
| 1           | "BLACK"    | new non null rook, same color same type and diff object  | yes          |
| 2           | "WHITE"    | new non null rook, same color same type and diff object  | yes          |

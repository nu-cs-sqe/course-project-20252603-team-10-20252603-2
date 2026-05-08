# BVA Analysis for Rook Piece

### Method under test: `isValidMove()` for Rook 

| Test Number   | Color   | Start position | Chosen position | Chosen position contents | Is path clear? | Movement pattern                 | Expected output | Implemented? |
|---------------|---------|----------------|-----------------|--------------------------|----------------|----------------------------------|-----------------|--------------|
| 1             | "WHITE" | [0][0]         | [0][0]          | EMPTY                    | True           | same square                      | False           | no           |
| 2             | "WHITE" | [0][0]         | [0][1]          | EMPTY                    | True           | 1 right (min distance)           | True            | no           |
| 3             | "WHITE" | [0][0]         | [0][7]          | EMPTY                    | True           | 7 right (max distance)           | True            | no           |
| 4             | "WHITE" | [0][7]         | [0][6]          | EMPTY                    | True           | 1 left (min distance)            | True            | no           |
| 5             | "WHITE" | [0][7]         | [0][0]          | EMPTY                    | True           | 7 left (max distance)            | True            | no           |
| 6             | "WHITE" | [0][0]         | [1][0]          | EMPTY                    | True           | 1 down (min distance)            | True            | no           |
| 7             | "WHITE" | [0][0]         | [7][0]          | EMPTY                    | True           | 7 down (max distance)            | True            | no           |
| 8             | "WHITE" | [0][0]         | [3][3]          | EMPTY                    | True           | diag                             | False           | no           |
| 9             | "WHITE" | [0][0]         | [0][3]          | EMPTY                    | False          | blocked horizontal               | False           | no           |
| 10            | "WHITE" | [0][0]         | [3][0]          | EMPTY                    | False          | blocked vertical                 | False           | no           |
| 11            | "WHITE" | [0][0]         | [0][7]          | enemy                    | True           | capture horizontal               | True            | no           |
| 12            | "WHITE" | [0][0]         | [7][0]          | enemy                    | True           | capture vertical                 | True            | no           |
| 13            | "WHITE" | [7][0]         | [6][0]          | EMPTY                    | True           | 1 up (min)                       | True            | no           |
| 14            | "WHITE" | [7][0]         | [0][0]          | EMPTY                    | True           | 7 up (max)                       | True            | no           |
| 15            | "BLACK" | [0][0]         | [0][1]          | EMPTY                    | True           | 1 horizontal (black rook)        | True            | no           |
| 16            | "BLACK" | [0][0]         | [0][1]          | enemy                    | True           | capture horizontal (black rook)  | True            | no           |
| 17            | "BLACK" | [0][0]         | [0][1]          | friendly                 | True           | friendly horizontal (black rook) | False           | no           |
| 18            | "WHITE" | [0][0]         | [0][3]          | friendly                 | True           | friendly horizontal              | False           | no           |
| 19            | "WHITE" | [0][0]         | [3][0]          | friendly                 | True           | friendly vertical                | False           | no           |
| 20 (CANT SET) | "BLACK" | [8][0]         | [7][0]          | EMPTY                    | True           | invalid start row                | False           | no           |
| 21 (CANT SET) | "BLACK" | [0][8]         | [0][7]          | EMPTY                    | True           | invalid start col                | False           | no           |
| 22 (CANT SET) | "BLACK" | [0][0]         | [-1][0]         | EMPTY                    | True           | invalid dest row                 | False           | no           |
| 23 (CANT SET) | "BLACK" | [0][0]         | [0][-1]         | EMPTY                    | True           | invalid dest col                 | False           | no           |


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
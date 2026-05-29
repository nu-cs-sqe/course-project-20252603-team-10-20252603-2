# BVA Analysis for Bishop Piece

### Method under test: `isValidMove()` for Bishop


| Test Number | Color   | Start position | Chosen position | Chosen position contents | Is path clear? | Movement pattern              | Expected output | Implemented? |
|-------------|---------|----------------|-----------------|--------------------------|----------------|-------------------------------|-----------------|--------------|
| 1           | "WHITE" | [0][0]         | [0][0]          | EMPTY                    | True           | same square                   | False           | yes          |
| 2           | "WHITE" | [0][0]         | [1][1]          | EMPTY                    | True           | 1 diagonal (row++, col++) min | True            | yes          |
| 3           | "WHITE" | [0][7]         | [1][6]          | EMPTY                    | True           | 1 diagonal (row++, col--) min | True            | yes          |
| 4           | "WHITE" | [7][0]         | [6][1]          | EMPTY                    | True           | 1 diagonal (row--, col++) min | True            | yes          |
| 5           | "WHITE" | [7][7]         | [6][6]          | EMPTY                    | True           | 1 diagonal (row--, col--) min | True            | yes          |
| 6           | "WHITE" | [0][0]         | [7][7]          | EMPTY                    | True           | 7 diagonal (row++, col++) max | True            | yes          |
| 7           | "WHITE" | [0][7]         | [7][0]          | EMPTY                    | True           | 7 diagonal (row++, col--) max | True            | yes          |
| 8           | "WHITE" | [7][0]         | [0][7]          | EMPTY                    | True           | 7 diagonal (row--, col++) max | True            | yes          |
| 9           | "WHITE" | [7][7]         | [0][0]          | EMPTY                    | True           | 7 diagonal (row--, col--) max | True            | yes          |
| 10          | "WHITE" | [0][0]         | [3][0]          | EMPTY                    | True           | vertical                      | False           | yes          |
| 11          | "WHITE" | [0][0]         | [0][3]          | EMPTY                    | True           | horizontal                    | False           | yes          |
| 12          | "WHITE" | [0][0]         | [7][7]          | enemy                    | True           | capture diagonal              | True            | yes          |
| 13          | "WHITE" | [0][0]         | [3][3]          | friendly                 | True           | friendly diagonal             | False           | yes          |
| 14          | "BLACK" | [0][0]         | [7][7]          | enemy                    | True           | capture diagonal              | True            | yes          |
| 15          | "BLACK" | [0][0]         | [7][7]          | empty                    | False          | valid move + blocked path     | False           | yes          |
| 16          | "BLACK" | [0][0]         | [7][7]          | empty                    | True           | valid move                    | True            | yes          |

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
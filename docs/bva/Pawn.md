# BVA Analysis for Pawn

## UPDATED TEST ANALYSIS

### Method under test: `isValidMove()` for Pawn

| Test Number | Color   | Start position | Chosen position | Chosen position contents | Is path clear? | Movement pattern | Expected output        | Implemented? |
|-------------|---------|----------------|-----------------|--------------------------|----------------|------------------|------------------------|--------------|
| 1           | "WHITE" | x=0, y=0       | x=0, y=0        | EMPTY                    | True           | same square      | False                  | yes          |
| 2           | "BLACK" | x=7, y=7       | x=7, y=7        | EMPTY                    | True           | same square      | False                  | yes          |
| 3           | "WHITE" | x=0, y=0       | x=7, y=7        | EMPTY                    | True           | too far          | False                  | yes          |
| 4           | "WHITE" | x=7, y=7       | x=6, y=7        | EMPTY                    | True           | one forward      | True                   | yes          |
| 5           | "BLACK" | x=1, y=0       | x=3, y=0        | EMPTY                    | True           | two forward      | True                   | yes          |
| 6           | "BLACK" | x=1, y=0       | x=3, y=0        | enemy                    | False          | two forward      | False                  | yes          |
| 7           | "WHITE" | x=7 y=6        | x=6, y=7        | enemy                    | False          | one diag right   | True                   | yes          |
| 8           | "WHITE" | x=7 y=6        | x=6, y=5        | enemy                    | False          | one diag left    | True                   | yes          |
| 9           | "WHITE" | x=7 y=6        | x=6, y=5        | EMPTY                    | True           | one diag left    | False                  | yes          |
| 10          | "WHITE" | x=7 y=6        | x=6, y=5        | friendly                 | false          | one diag left    | False                  | yes          |
| 11          | "WHITE" | x=6 y=6        | x=7 y=6         | EMPTY                    | True           | backward         | False                  | yes          |
| 12          | "WHITE" | x=6 y=6        | x=6 y=5         | EMPTY                    | True           | sideways         | False                  | yes          |
| 13          | "BLACK" | x=0, y=0       | x=2, y=0        | EMPTY                    | False          | two forward      | False                  | yes          |
| 14          | "BLACK" | x=2, y=0       | x=4, y=0        | EMPTY                    | True           | two forward      | False (non first turn) | yes          |
| 15          | "WHITE" | x=5, y=7       | x=3, y=7        | EMPTY                    | True           | two forward      | False (non first turn) | no           |
| 16          | "WHITE" | x=5, y=0       | x=3, y=0        | EMPTY                    | True           | two forward      | False                  | no           |



### STEPS FOR BVA: `isValidMove()` for Pawn

1) input equivalence classes and output equivalence classes
* input:
    * pawn color
    * piece start position (row, column)
    * chosen position (row, column)
    * chosen position contents (empty, enemy piece, friendly piece)
    * path is clear?
    * movement pattern (one of the chess movement types, or not)
* output:
    * a yes/no answer

2) BVA catalog classes
* input:
    * pawn color: cases
    * piece start position (row, column): array indices
    * chosen position (row, column): array indices
    * chosen position contents (empty, enemy piece, friendly piece): cases
    * path is clear?: boolean
    * movement pattern (one of the chess movement types, or not): cases
* output:
    * a yes/no answer: boolean

3) BVA catalog classes -- values
* input:
    * pawn color: cases
        * BLACK, WHITE
    * piece start position (row, column): array indices
        * all indices are 0 at same time: [0][0]
        * all largest valid value: [7][7]
        * first index -1: [-1][3], CANT SET
        * second index -1: [3][-1] , CANT SET
        * first element too big: [8][3], CANT SET
        * second element too big: [3][8], CANT SET
    * chosen position (row, column): array indices
        * all indices are 0 at same time: [0][0]
        * all largest valid value: [7][7]
        * first index -1: [-1][3], CANT SET
        * second index -1: [3][-1] , CANT SET
        * first element too big: [8][3], CANT SET
        * second element too big: [3][8], CANT SET
    * chosen position contents (empty, enemy piece, friendly piece): cases
        * chosen position is EMPTY
        * chosen position has an ENEMY piece
        * chosen position has a FRIENDLY piece
    * path is clear?: boolean
        * True, False
    * movement pattern (one of the chess movement types, or not): cases
        * one forward (open path)
        * two forward (open path, first turn)
        * one diagonal left (enemy piece)
        * one diagonal right (enemy piece)
        * backward (INVALID)
        * sideways (INVALID)
        * same square (INVALID)
        * too far (INVALID)
* output:
    * a yes/no answer: boolean
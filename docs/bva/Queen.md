# BVA Analysis for Queen

### Method under test: `isValidMove()` for Queen

| Test Number | Color   | Start position | Chosen position | Chosen position contents | Is path clear?                   | Movement pattern         | Expected output | Implemented? |
|-------------|---------|----------------|-----------------|--------------------------|----------------------------------|--------------------------|-----------------|--------------|
| 1           | "WHITE" | x=0, y=0       | x=7, y=0        | empty                    | True                             | x movement (+ direction) | True            | yes          |
| 2           | "BLACK" | x=0, y=0       | x=7, y=0        | empty                    | True                             | x movement (+ direction) | True            | no           |
| 3           | "BLACK" | x=7, y=7       | x=0, y=7        | empty                    | True                             | x movement (- direction) | True            | no           |
| 4           | "BLACK" | x=7, y=7       | x=7, y=0        | empty                    | True                             | y movement (- direction) | True            | no           |
| 5           | "BLACK" | x=0, y=0       | x=0, y=7        | empty                    | True                             | y movement (+ direction) | True            | no           |
| 6           | "WHITE" | x=0, y=0       | x=7, y=7        | empty                    | True                             | diagonal (+ direction)   | True            | no           |
| 7           | "WHITE" | x=7, y=7       | x=0, y=0        | empty                    | True                             | diagonal (- direction)   | True            | no           |
| 8           | "BLACK" | x=0, y=0       | x=7, y=0        | empty                    | False (friendly piece @ x=3,y=0) | x movement (+ direction) | False           | no           |
| 9           | "BLACK" | x=0, y=0       | x=7, y=0        | empty                    | False (enemy piece @ x=3,y=0)    | x movement (+ direction) | False           | no           |
| 10          | "BLACK" | x=0, y=0       | x=0, y=7        | empty                    | False (enemy piece @ x=0,y=3)    | y movement (+ direction) | False           | no           |
| 11          | "WHITE" | x=0, y=0       | x=7, y=7        | empty                    | False (enemy piece @ x=3,y=3)    | diagonal (+ direction)   | False           | no           |
| 12          | "WHITE" | x=7, y=7       | x=0, y=7        | ENEMY                    | True                             | x movement (- direction) | True            | no           |
| 13          | "WHITE" | x=7, y=7       | x=0, y=7        | FRIENDLY                 | True                             | x movement (- direction) | False           | no           |
| 14          | "WHITE" | x=5, y=7       | x=5, y=7        | FRIENDLY (same square)   | True                             | same movement            | False           | no           |
| 15          | "WHITE" | x=3, y=3       | x=5, y=2        | EMPTY                    | True                             | L movement #1            | False           | no           |
| 16          | "BLACK" | x=3, y=3       | x=5, y=4        | EMPTY                    | True                             | L movement #2            | False           | no           |


### STEPS FOR BVA: `isValidMove()` for Queen

1) input equivalence classes and output equivalence classes
* input:
    * queen color
    * piece start position (row, column)
    * chosen position (row, column)
    * chosen position contents (empty, enemy piece, friendly piece)
    * path is clear?
    * movement pattern (one of the chess movement types, or not)
* output:
    * a yes/no answer

2) BVA catalog classes
* input:
    * queen color: cases
    * piece start position (row, column): array indices
    * chosen position (row, column): array indices
    * chosen position contents (empty, enemy piece, friendly piece): cases
    * path is clear?: boolean
    * movement pattern (one of the chess movement types, or not): cases
* output:
    * a yes/no answer: boolean

3) BVA catalog classes -- values
* input:
    * queen color: cases
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
        * True, False (NO IMPACT ON MOVEMENT)
    * movement pattern (one of the chess movement types, or not): cases
        * L shape (8 possible formations) (INVALID)
          * #1: move forward 2, and 1 left
          * #2: move forward 2 and 1 right
          * #3: move forward 1 and 2 left
          * #4: move forward 1 and 2 right
          * #5: move backwards 2 and 1 left
          * #6: move backwards 2 and 1 right
          * #7: move backwards 1 and 2 left
          * #8: move backwards 1 and 2 right
        * x movement (forwards/backwards) (VALID)
          * Interval: x between [-7, 7]
            * NOTE: cannot move beyond +/- 7 squares, can only test HIGH and LOW as dictated by BVA catalog 
        * y movement (left right) (VALID)
          * Interval: y between [-7, 7]
            * NOTE: cannot move beyond +/- 7 squares, can only test HIGH and LOW as dictated by BVA catalog
        * diagonal (VALID)
            * Interval: x == y and x between [-7, 7]
        * same square (INVALID)
* output:
    * a yes/no answer: boolean
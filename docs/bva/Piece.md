# BVA Analysis for Piece

## UPDATED TEST ANALYSIS

### Method under test: `isSameColor()`

|             | System under test | Expected output | Implemented? |
|-------------|-------------------|-----------------|--------------|
| Test Case 1 | "B", "B"          | True            | yes          |
| Test Case 2 | "B", "W"          | False           | yes          |
| Test Case 3 | "W", "B"          | False           | yes          |
| Test Case 4 | "W", "W"          | True            | yes          |


### STEPS FOR BVA: `isSameColor()`
1) input equivalence classes and output equivalence classes
* Input: string of current piece, either “black” or “white” 
* Output: a yes or no answer

2) determine data types
* Input: cases 
  * Input color = color of current piece 
  * Input color != color of current piece 
* Output: boolean

3)
* Input: cases 
  * Colors: “B” and “W” 
* Output: boolean 
  * True, False

4) 
* All combinations 
  * “B”, “B” --> T 
  * “B”, “W” --> F 
  * “W”, “W” --> T 
  * “W”, “B” --> F



### Method under test: `isValidMove()` for Pawn -- EXEMPLAR FOR OTHER PIECE TYPE BVA

| Test Number    | Color   | Start position | Chosen position | Chosen position contents | Is path clear? | Movement pattern | Expected output | Implemented? |
|----------------|---------|----------------|-----------------|--------------------------|----------------|------------------|-----------------|--------------|
| 1              | "WHITE" | [0][0]         | [0][0]          | EMPTY                    | True           | same square      | False           | yes          |
| 2              | "BLACK" | [7][7]         | [7][7]          | EMPTY                    | True           | same square      | False           | yes          |
| 3              | "WHITE" | [0][0]         | [7][7]          | EMPTY                    | True           | too far          | False           | yes          |
| 4              | "BLACK" | [7][7]         | [6][7]          | EMPTY                    | True           | one forward      | True            | no           |
| 5              | "WHITE" | [0][0]         | [0][2]          | EMPTY                    | True           | two forward      | True            | no           |
| 6              | "WHITE" | [0][0]         | [0][2]          | enemy                    | False          | two forward      | False           | no           |
| 7              | "BLACK" | [7][6]         | [6][7]          | enemy                    | False          | one diag right   | True            | no           |
| 8              | "BLACK" | [7][6]         | [6][5]          | enemy                    | False          | one diag left    | True            | no           |
| 9              | "BLACK" | [7][6]         | [6][5]          | EMPTY                    | True           | one diag left    | False           | no           |
| 10             | "BLACK" | [7][6]         | [6][5]          | friendly                 | false          | one diag left    | False           | no           |
| 11             | "BLACK" | [6][6]         | [7][6]          | EMPTY                    | True           | backward         | False           | no           |
| 12             | "BLACK" | [6][6]         | [6][7]          | EMPTY                    | True           | sideways         | False           | no           |
| 13 (CANT SET)  | "BLACK" | [-1][3]        | [6][7]          | EMPTY                    | True           | sideways         | False           | no           |
| 14 (CANT SET)  | "BLACK" | [3][-1]        | [6][7]          | EMPTY                    | True           | sideways         | False           | no           |
| 15 (CANT SET)  | "BLACK" | [8][3]         | [6][7]          | EMPTY                    | True           | sideways         | False           | no           |
| 16 (CANT SET)  | "BLACK" | [3][8]         | [6][7]          | EMPTY                    | True           | sideways         | False           | no           |
| 17 (CANT SET)  | "BLACK" | [6][6]         | [-1][3]         | EMPTY                    | True           | sideways         | False           | no           |
| 18 (CANT SET)  | "BLACK" | [6][6]         | [3][-1]         | EMPTY                    | True           | sideways         | False           | no           |
| 19 (CANT SET)  | "BLACK" | [6][6]         | [8][3]          | EMPTY                    | True           | sideways         | False           | no           |
| 20 (CANT SET)  | "BLACK" | [6][6]         | [3][8]          | EMPTY                    | True           | sideways         | False           | no           |


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
    * two forward (open path)
    * one diagonal left (enemy piece)
    * one diagonal right (enemy piece)
    * backward
    * sideways
    * same square
    * too far
* output:
  * a yes/no answer: boolean
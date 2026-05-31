# BVA Analysis for King Piece

## Method under test: `isInCheck()` for King

| Test Number | Color | King Location | Attacker Type | Attacker Color | Attacker Location | Pieces Blocking Path | Expected Output         | Implemented? |
|-------------|-------|---------------|---------------|----------------|-------------------|----------------------|-------------------------|--------------|
| 1           | WHITE | x=4, y=4      | none          | none           | none              | none                 | False                   | yes          |
| 2           | WHITE | x=4, y=4      | Rook          | BLACK          | x=4, y=7          | none                 | True                    | yes          |
| 3           | WHITE | x=4, y=4      | Rook          | BLACK          | x=4, y=7          | friendly at x=4, y=6 | False                   | yes          |
| 4           | WHITE | x=4, y=4      | Rook          | BLACK          | x=4, y=7          | enemy at x=4, y=6    | False                   | no           |
| 5           | WHITE | x=4, y=4      | Rook          | BLACK          | x=0, y=4          | none                 | True                    | no           |
| 6           | WHITE | x=4, y=4      | Bishop        | BLACK          | x=2, y=2          | none                 | True                    | no           |
| 7           | WHITE | x=4, y=4      | Bishop        | BLACK          | x=2, y=2          | friendly at x=3, y=3 | False                   | no           |
| 8           | WHITE | x=4, y=4      | Knight        | BLACK          | x=2, y=3          | n/a                  | True                    | no           |
| 9           | WHITE | x=4, y=4      | Pawn          | BLACK          | x=3, y=3          | none                 | True                    | no           |
| 10          | WHITE | x=4, y=4      | Pawn          | BLACK          | x=3, y=5          | none                 | True                    | no           |
| 11          | WHITE | x=4, y=4      | Pawn          | WHITE          | x=3, y=3          | none                 | False (friendly)        | no           |
| 12          | BLACK | x=4, y=4      | Pawn          | WHITE          | x=5, y=3          | none                 | True                    | no           |
| 13          | WHITE | x=4, y=4      | Pawn          | BLACK          | x=5, y=3          | none                 | False (wrong direction) | no           |
| 14          | WHITE | x=4, y=4      | Queen         | BLACK          | x=4, y=7          | none                 | True (rook-like)        | no           |
| 15          | WHITE | x=4, y=4      | Queen         | BLACK          | x=1, y=1          | none                 | True (bishop-like)      | no           |
| 16          | WHITE | x=4, y=4      | Kign          | BLACK          | x=3, y=4          | none                 | True                    | no           |
| 17          | WHITE | x=4, y=4      | King          | BLACK          | x=2, y=4          | none                 | False (too far)         | no           |
| 18          | WHITE | x=0, y=0      | Rook          | BLACK          | x=0, y=7          | none                 | True (corner)           | no           |
| 19          | WHITE | x=7, y=7      | Rook          | BLACK          | x=7, y=0          | none                 | True (corner)           | no           |

### STEPS FOR BVA: `isInCheck()` for King

1) input equivalence classes and output equivalence classes
* input:
    * king color
    * king location (row, col)
    * attacker piece type (rook, bishop, queen, knight, pawn, king, or none)
    * attacker location (row, col)
    * path is clear?
* output:
    * a yes/no answer

2) BVA catalog classes
* input:
    * king color (white or black): cases
    * king location (row, column): array indices
    * attacker piece type: cases
    * attacker location (row, col): array indices
    * pieces blocking the path: cases
* output:
    * a yes/no answer: boolean

3) BVA catalog classes -- values
* input:
    * king color: cases
        * BLACK, WHITE
    * king location (row, column): array indices
        * all indices are 0 at same time: [0][0]
        * all largest valid value: [7][7]
        * first index too small: [-1][3], CANT SET
        * second index to small: [3][-1] , CANT SET
        * first index too big: [8][3], CANT SET
        * second index too big: [3][8], CANT SET
    * attacker piece type: cases
        * rook
        * bishop
        * queen
        * knight (cannot be blocked)
        * pawn
        * king
        * none
    * attacker color: cases
      * same color as king (friendly, cannot check)
      * opposite color as king (enemy piece can check)
    * king location (row, column): array indices
      * same row as king
      * same column as king
      * same diagonal as king
      * L shape from king
      * one diagonal forward from king
      * adjacent
    * path is clear?: cases
        * no pieces (path clear)
        * friendly piece between
        * enemy piece
* output:
    * a yes/no answer: boolean

## Method under test: `isValidMove()` for King

| Test Number | Color | Start position | Chosen position | Chosen position contents | Expected output | Implemented? |
|-------------|-------|----------------|-----------------|--------------------------|-----------------|--------------|
| 1           | WHITE | x=4, y=4       | x=4, y=4        | EMPTY                    | False           | no           |
| 2           | WHITE | x=4, y=4       | x=4, y=6        | EMPTY                    | False (too far) | no           |
| 3           | WHITE | x=4, y=4       | x=2, y=4        | EMPTY                    | False (too far) | no           |
| 4           | WHITE | x=4, y=4       | x=5, y=4        | EMPTY                    | True            | no           |
| 5           | WHITE | x=4, y=4       | x=3, y=4        | EMPTY                    | True            | no           |
| 6           | WHITE | x=4, y=4       | x=4, y=5        | EMPTY                    | True            | no           |
| 7           | WHITE | x=4, y=4       | x=4, y=3        | EMPTY                    | True            | no           |
| 8           | WHITE | x=4, y=4       | x=3, y=3        | EMPTY                    | True (diagonal) | no           |
| 9           | WHITE | x=4, y=4       | x=3, y=5        | EMPTY                    | True (diagonal) | no           |
| 10          | WHITE | x=4, y=4       | x=5, y=3        | EMPTY                    | True (diagonal) | no           |
| 11          | WHITE | x=4, y=4       | x=5, y=5        | EMPTY                    | True (diagonal) | no           |
| 12          | WHITE | x=4, y=4       | x=3, y=3        | friendly                 | False           | no           |
| 13          | WHITE | x=4, y=4       | x=3, y=3        | enemy                    | True (capture)  | no           |
| 14          | WHITE | x=4, y=4       | x=3, y=3        | EMPTY                    | False           | no           |
| 15          | WHITE | x=0, y=0       | x=0, y=1        | EMPTY                    | True            | no           |
| 16          | WHITE | x=7, y=7       | x=6, y=7        | EMPTY                    | True            | no           |
| 17          | WHITE | x=4, y=4       | x=3, y=3        | enemy KING               | False           | no           |

### STEPS FOR BVA: `isValidMove()` for King

1) input equivalence classes and output equivalence classes
* input:
    * king color
    * piece start position (row, col)
    * chosen position (row, col)
    * chosen position contents (empty, enemy piece, friendly piece)
    * is the king now in check?
* output:
    * a yes/no answer
2) BVA catalog classes
* input:
    * king color: cases
    * piece start position (row, column): array indices
    * chosen position (row, column): array indices
    * chosen position contents (empty, enemy piece, friendly piece): cases
    * is the king now in check?: boolean
* output:
    * a yes/no answer: boolean
3) BVA catalog classes -- values
* input:
    * king color: cases
        * BLACK, WHITE
    * piece start position (row, column): array indices
        * all indices are 0 at same time: [0][0]
        * all largest valid value: [7][7]
        * first index -1: [-1][3], CANT SET
        * second index -1: [3][-1], CANT SET
        * first element too big: [8][3], CANT SET
        * second element too big: [3][8], CANT SET
    * chosen position (row, column): array indices
        * same starting square  
        * one move horizontally 
        * one move vertically 
        * one move diagonally 
        * two or more moves in any direction 
    * chosen position contents: cases
        * EMPTY 
        * enemy piece 
        * friendly piece 
    * move exposes king to check: boolean
        * True 
        * False 
* output:
    * a yes/no answer: boolean
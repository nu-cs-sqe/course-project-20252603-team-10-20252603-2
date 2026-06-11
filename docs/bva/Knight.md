# BVA Analysis for Knight

### Method under test: `isValidMove()` for Knight

| Test Number | Color   | Start position | Chosen position | Chosen position contents | Is path clear?                     | Movement pattern | Expected output | Implemented? |
|-------------|---------|----------------|-----------------|--------------------------|------------------------------------|------------------|-----------------|--------------|
| 1           | "WHITE" | x=3, y=3       | x=5, y=2        | EMPTY                    | True                               | L movement #1    | True            | yes          |
| 2           | "BLACK" | x=3, y=3       | x=5, y=4        | EMPTY                    | True                               | L movement #2    | True            | yes          |
| 3           | "WHITE" | x=3, y=3       | x=4, y=1        | EMPTY                    | True                               | L movement #3    | True            | yes          |
| 4           | "WHITE" | x=3, y=3       | x=4, y=5        | EMPTY                    | True                               | L movement #4    | True            | yes          |
| 5           | "WHITE" | x=3, y=3       | x=1, y=2        | EMPTY                    | True                               | L movement #5    | True            | yes          |
| 6           | "WHITE" | x=3, y=3       | x=1, y=4        | EMPTY                    | True                               | L movement #6    | True            | yes          |
| 7           | "WHITE" | x=3, y=3       | x=2, y=1        | EMPTY                    | True                               | L movement #7    | True            | yes          |
| 8           | "WHITE" | x=3, y=3       | x=2, y=5        | EMPTY                    | True                               | L movement #8    | True            | yes          |
| 9           | "WHITE" | x=0, y=0       | x=2, y=1        | EMPTY                    | True                               | L movement #2    | True            | yes          |
| 10          | "WHITE" | x=7, y=7       | x=6, y=5        | EMPTY                    | True                               | L movement #5    | True            | yes          |
| 11          | "WHITE" | x=2, y=1       | x=0, y=0        | EMPTY                    | True                               | L movement #5    | True            | yes          |
| 12          | "WHITE" | x=6, y=5       | x=7, y=7        | EMPTY                    | True                               | L movement #4    | True            | yes          |
| 13          | "BLACK" | x=3, y=3       | x=5, y=2        | EMPTY                    | false (block at x=4,y=3, enemy)    | L movement #1    | True            | yes          |
| 14          | "BLACK" | x=3, y=3       | x=5, y=2        | ENEMY                    | True                               | L movement #1    | True            | yes          |
| 15          | "BLACK" | x=3, y=3       | x=5, y=2        | FRIENDLY                 | True                               | L movement #1    | False           | yes          |
| 16          | "BLACK" | x=3, y=3       | x=5, y=3        | EMPTY                    | True                               | forward and back | False           | yes          |
| 17          | "BLACK" | x=3, y=3       | x=3, y=5        | EMPTY                    | True                               | sideways         | False           | yes          |
| 18          | "WHITE" | x=3, y=3       | x=5, y=5        | EMPTY                    | True                               | diagonal         | False           | yes          |
| 19          | "WHITE" | x=3, y=3       | x=3, y=3        | EMPTY                    | True                               | same square      | False           | yes          |
| 20          | "BLACK" | x=3, y=3       | x=5, y=2        | EMPTY                    | false (block at x=5,y=3, enemy)    | L movement #1    | True            | yes          |
| 21          | "WHITE" | x=3, y=3       | x=4, y=1        | EMPTY                    | false (block at x=4,y=3, enemy)    | L movement #3    | True            | yes          |
| 22          | "WHITE" | x=3, y=3       | x=4, y=1        | EMPTY                    | false (block at x=4,y=2, friendly) | L movement #3    | True            | yes          |


### STEPS FOR BVA: `isValidMove()` for Knight

1) input equivalence classes and output equivalence classes
* input:
    * knight color
    * piece start position (row, column)
    * chosen position (row, column)
    * chosen position contents (empty, enemy piece, friendly piece)
    * path is clear?
    * movement pattern (one of the chess movement types, or not)
* output:
    * a yes/no answer

2) BVA catalog classes
* input:
    * knight color: cases
    * piece start position (row, column): array indices
    * chosen position (row, column): array indices
    * chosen position contents (empty, enemy piece, friendly piece): cases
    * path is clear?: boolean
    * movement pattern (one of the chess movement types, or not): cases
* output:
    * a yes/no answer: boolean

3) BVA catalog classes -- values
* input:
    * knight color: cases
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
        * L shape (8 possible formations)
          * #1: move forward 2, and 1 left
          * #2: move forward 2 and 1 right
          * #3: move forward 1 and 2 left
          * #4: move forward 1 and 2 right
          * #5: move backwards 2 and 1 left
          * #6: move backwards 2 and 1 right
          * #7: move backwards 1 and 2 left
          * #8: move backwards 1 and 2 right
        * x movement (forwards/backwards) (INVALID)
        * y movement (left right) (INVALID)
        * diagonal (INVALID)
        * same square (INVALID)
* output:
    * a yes/no answer: boolean

    
### Method under test: `makeCopy()` for Knight, basic functionality testing

| Test Number | Color      | Expected output                                           | Implemented? |
|-------------|------------|-----------------------------------------------------------|--------------|
| 1           | "BLACK"    | new non null knight, same color same type and diff object | yes          |
| 2           | "WHITE"    | new non null knight, same color same type and diff object | yes          |


### Method under test: `hasValidMoves()` for Knight

| Test Number | Color | Start position | Board State                                                                    | Expected Output | Implemented? |
|-------------|-------|----------------|--------------------------------------------------------------------------------|-----------------|--------------|
| 1           | WHITE | [6,0]          | Clear board                                                                    | TRUE            | yes          |               
| 2           | WHITE | [6,0]          | Friendly piece at [4, 1], [5,2],[7,2], diagonal spaces are empty               | FALSE           | yes          |                
| 3           | BLACK | [5,4]          | Friendly piece blocking one step forward at [3, 5].                            | TRUE            | yes          |
| 4           | BLACK | [7,0]          | Stuck at the absolute edge, friendly pieces at [5, 1] and [6, 2]               | FALSE           | yes          |
| 5           | WHITE | [7,1]          | Pinned against its own White King [7, 0] by an enemy Black Rook [7, 7] along a row | FALSE           | yes          |
| 6           | WHITE | [7,1]          | Blocked by enemy piece [5, 2]                                                  | TRUE            | yes          |

### STEPS FOR BVA: `hasValidMoves()` for Knight

1) input equivalence classes and output equivalence classes
* input:
    * knight color
    * piece start position (row, column)
    * state of the board (Board)
    * path is clear?
    * status of the King (isInCheck())
* output:
    * a yes/no answer

2) BVA catalog classes
* input:
    * knight color: cases
    * piece start position (row, column): array indices
    * path is clear?: boolean
    * state of the board: cases
    * status of the King: boolean
* output:
    * a yes/no answer: boolean

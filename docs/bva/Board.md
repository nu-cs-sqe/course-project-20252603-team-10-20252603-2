# Test / BVA Analysis for Board

### Method under test: initializeBoard() (more functional testing than true BVA)

| Test # | Board setup? | Expected Output                                  | Implemented? |
|--------|--------------|--------------------------------------------------|--------------|
| 1      | false        | empty board                                      | yes          | 
| 2      | true         | init board (B/W pawns in correct place)          | yes          | 
| 3      | true         | init board (W major pieces in correct place)     | yes          | 
| 4      | true         | init board (B major pieces in correct place)     | yes          | 
| 5      | true         | init board (non starting rows (2-5) are empty)   | yes          | 

1) input equivalence classes and output equivalence classes
   * input equiv class:
     * whether you want to setup the board or not (yes/no)
   * output equiv class:
     * initialized chess board OR empty chess board
2) BVA catalog classes
   * input: boolean
   * output: cases
     1) initialized board
     2) empty board
3) BVA catalog classes -- values
   * input:
     * true
     * false
   * output
     * board with standard chess setup 
     * board with no pieces

### Method under test: isPieceHere()

| Test # | Board state                    | Location | Expected Output | Implemented? |
|--------|--------------------------------|----------|-----------------|--------------|
| 1      | empty                          | x=0 y=0  | false           | yes          | 
| 2      | empty w/ white pawn at x=0 y=0 | x=0 y=0  | true            | yes          | 
| 3      | empty w/ black pawn at x=7 y=7 | x=7 y=7  | true            | yes          | 
| 4      | initialized board              | x=1 y=0  | true            | yes          | 
| 5      | initialized board              | x=3 y=3  | false           | yes          | 


1) input equivalence classes and output equivalence classes
    * input equiv class:
        * a location on the board
        * the state of the board at that location
    * output equiv class:
        * yes/no answer
2) BVA catalog classes
    * input:
      * location: array indices
      * board state: cases
    * output: boolean
3) BVA catalog classes -- values
    * input:
        * location (2d array indices)
          * [0][0]
          * [7][7]
          * [-1][5] CANT SET
          * [5][-1] CANT SET
          * [8][5] CANT SET
          * [5][8] CANT SET
        * board state (cases)
          * case 1: piece present
          * case 2: no piece present
    * output
        * true
        * false


### Method under test: getPiece()

| Test # | Board state                     | Location | Expected Output | Implemented? |
|--------|---------------------------------|----------|-----------------|--------------|
| 1      | empty                           | x=0 y=0  | null            | yes          | 
| 2      | empty w/ white pawn at x=0 y=0  | x=0 y=0  | white pawn      | yes          | 
| 3      | empty w/ black queen at x=7 y=7 | x=7 y=7  | black queen     | yes          | 
| 4      | initialized board               | x=0 y=0  | black rook      | yes          |

1) input equivalence classes and output equivalence classes
    * input equiv class:
        * a location on the board
        * the state of the board at that location
    * output equiv class:
        * Piece obj
        * null (SHOULD BE AVOIDED BY USING IN TANDEM WITH isPieceHere())
2) BVA catalog classes
    * input:
        * location: array indices
        * board state: cases
    * output: pointer
3) BVA catalog classes -- values
    * input:
        * location (2d array indices)
            * [0][0]
            * [7][7]
            * [-1][5] CANT SET
            * [5][-1] CANT SET
            * [8][5] CANT SET
            * [5][8] CANT SET
        * board state (cases)
            * case 1: piece present
            * case 2: no piece present
    * output
        * return null pointer
        * return Piece obj

    
### Method under test: setPiece()

| Test # | Board state                             | Location | Piece Obj    | Expected Output                                 | Implemented? |
|--------|-----------------------------------------|----------|--------------|-------------------------------------------------|--------------|
| 1      | empty                                   | x=0 y=0  | WHITE PAWN   | isPieceHere == True, getPiece() == WHITE PAWN   | yes          | 
| 2      | empty                                   | x=7 y=7  | BLACK KNIGHT | isPieceHere == True, getPiece() == BLACK KNIGHT | yes          | 
| 3      | occupied square (WHITE PAWN) at x=0 y=0 | x=0 y=0  | BLACK QUEEN  | isPieceHere == True, getPiece() == BLACK QUEEN  | yes          | 
| 4      | empty                                   | x=7 y=7  | null         | illegalArgumentException                        | yes          | 


1) input equivalence classes and output equivalence classes
    * input equiv class:
        * a location on the board
        * piece obj
    * output equiv class:
        * updated board state
2) BVA catalog classes
    * input:
        * location: array indices
        * piece obj: cases
    * output: cases (diff board states)
3) BVA catalog classes -- values
    * input:
        * location (2d array indices)
            * [0][0]
            * [7][7]
            * [-1][5] CANT SET
            * [5][-1] CANT SET
            * [8][5] CANT SET
            * [5][8] CANT SET
        * piece obj
            * piece object
    * output
        * piece added to the board at specified location
        * current piece on board at specified location replaced with new piece


### Method under test: removePiece()

| Test # | Board state                              | Location | Expected Output                                  | Implemented? |
|--------|------------------------------------------|----------|--------------------------------------------------|--------------| 
| 1      | occupied square (WHITE PAWN) at x=0 y=0  | x=0 y=0  | isPieceHere == False, getPiece() == null         | yes          | 
| 2      | occupied square (BLACK QUEEN) at x=7 y=7 | x=7 y=7  | isPieceHere == False, getPiece() == null         | yes          | 
| 3      | empty square                             | x=0 y=7  | isPieceHere == False, getPiece() == null         | yes          | 


1) input equivalence classes and output equivalence classes
    * input equiv class:
        * a location on the board
    * output equiv class:
        * updated board state (specified location on the board is set to null)
2) BVA catalog classes
    * input:
        * location: array indices
    * output: cases (diff board states)
3) BVA catalog classes -- values
    * input:
        * location (2d array indices)
            * [0][0]
            * [7][7]
            * [-1][5] CANT SET
            * [5][-1] CANT SET
            * [8][5] CANT SET
            * [5][8] CANT SET
    * output
        * occupied square becomes empty
        * empty square remains empty

### Method under test: getSnapshot(), functional testing (NOT BVA)

| Test # | Board state                     | Expected Output                                         | Implemented? |
|--------|---------------------------------|---------------------------------------------------------|--------------|
| 1      | empty board                     | snapshot with only nulls                                | yes          | 
| 2      | board with one piece at x=0,y=0 | snapshot with piece at same location                    | yes          |


### Method under test: findKingLocation()

| Test # | Board state                                                | King color | Expected Output | Implemented? |
|--------|------------------------------------------------------------|------------|-----------------|--------------|
| 1      | empty board                                                | BLACK      | null            | yes          | 
| 2      | board with BLACK king at x=0,y=0 and WHITE king at x=7 y=7 | WHITE      | Location(7,7)   | yes          |
| 3      | board with BLACK king at x=0,y=0 and WHITE king at x=7 y=7 | BLACK      | Location(0,0)   | yes          |
| 4      | board with BLACK king at x=0,y=0 then moved to x=1 y=1     | BLACK      | Location(1,1)   | yes          |
| 5      | board with WHITE king at x=7,y=7 then moved to x=6 y=7     | BLACK      | Location(6,7)   | no           |


1) input equivalence classes and output equivalence classes
    * input equiv class:
        * king's color
        * king's location
    * output equiv class:
        * location where the king is or null if the king is nowhere
2) BVA catalog classes
    * input:
        * kings color: cases
          * BLACK
          * WHITE
        * king's location: array indices
    * output: pointer
3) BVA catalog classes -- values
    * input:
        * color: cases:
            * BLACK
            * WHITE
        * location (2d array indices)
            * [0][0] 
            * [7][7] 
            * [-1][5] CANT SET
            * [5][-1] CANT SET
            * [8][5] CANT SET
            * [5][8] CANT SET
    * output: pointer
        * location obj
        * null

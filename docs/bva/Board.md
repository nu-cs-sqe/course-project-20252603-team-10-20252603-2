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
| 3      | empty w/ black queen at x=7 y=7 | x=7 y=7  | black queen     | no           | 
| 4      | initialized board               | x=0 y=0  | black rook      | no           | 
| 5      | initialized board               | x=7 y=1  | white knight    | no           | 


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

| Test # | Board state                              | Location | Piece Obj    | Expected Output                                  | Implemented? |
|--------|------------------------------------------|----------|--------------|--------------------------------------------------|--------------|
| 1      | empty                                    | x=0 y=0  | WHITE PAWN   | isPieceHere == True, getPiece() == WHITE PAWN    | no           | 
| 2      | empty                                    | x=7 y=7  | BLACK KNIGHT | isPieceHere == True, getPiece() == BLACK KNIGHT  | no           | 
| 3      | occupied square (WHITE PAWN) at x=0 y=0  | x=0 y=0  | BLACK QUEEN  | isPieceHere == True, getPiece() == BLACK QUEEN   | no           | 
| 4      | occupied square (WHITE PAWN) at x=0 y=0  | x=0 y=0  | null         | isPieceHere == False, getPiece() == null         | no           | 

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
            * null
    * output
        * piece added
        * piece replaced
        * piece removed


### Method under test: getSnapshot(), functional testing (NOT BVA)

| Test # | Board state                     | Expected Output                                         | Implemented? |
|--------|---------------------------------|---------------------------------------------------------|--------------|
| 1      | empty board                     | snapshot with only nulls                                | yes          | 
| 2      | board with one piece at x=0,y=0 | snapshot with piece at same location                    | yes          |

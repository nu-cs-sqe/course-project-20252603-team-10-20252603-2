### CLASSES
- Gameboard 
- Game Manager 
- Piece 
  - Subclasses: Pawn, King, Queen, Rook, Knight, Bishop 
- Player
- Position

### METHODS
- Game Manager 
  - start()
  - changeTurns()
  - isGameOver()
- Game Board 
  - gameboardInit()
  - move()
  - removePiece()
  - isInsideBoard()
  - isPieceHere()
- Piece
  - isSameColor()
  - isValidMove() – changes based on piece type subclass 
- Player 
  - getPoints()
  - incrementPoints()
- Position

### RELATIONSHIPS
- Game Manager
    - start()
      - calls gameboardInit(), creates 2 players
    - changeTurns()
    - isGameOver()
      - calls getPoints() for each player
- Game Board
    - gameboardInit()
      - creates 32 piece objects (16 white, 16 black; 8 pawns, 1 king, 1 queen, 2 rook, 2 knight, 2 bishop)
    - move()
    - removePiece()
      - calls incrementPoints() for the player whose piece is NOT removed
    - isInsideBoard()
    - isPieceHere()
- Piece
    - isSameColor()
    - isValidMove() – changes based on piece type subclass
- Player
    - getPoints()
    - incrementPoints()
- Position


**User Story:**

As a player of 2D Chess, I want the game to be properly set up automatically when a new game starts, so that I can begin playing without manually setting up the board.

**Acceptance Criteria**

1.  ✅ The game must not start unless there are 2 players.
2.  ✅ Each player must be assigned as a white or black player.
3.  ✅ The game should be initialized with 16 black and 16 white pieces in their proper starting tiles.
4.  ✅ The turn order must be initialized once setup is complete and will begin with white.

### **Use Case #1: Start New Game**

-   **Actor:** Player
-   **Preconditions:**
    -   The game application is launched.
-   **Main Flow:**
    -   Player clicks “Start Game”.
    -   (Your team can also add "asking for player name" feature and etc..)
    -   System shows the board.
    -   System requests white player to make a move.
    -   The system checks if the move is valid
        -   If yes, the system moves the piece, and starts the other player’s move
        -   If no, the system presents an error and requests that the player select a correct move for its piece.
    -   If the move is valid and the destination square has an opponent’s piece, that piece is removed from the board and given to the player.
    -   Turn switches to the other player.
-   **Postconditions:**
    -   Each player has a side with 16 pieces (spread indicated above
    -   The game is ready for the first turn.

**User Story:**

As a player of 2D Chess, I want to be able to move my chess pieces accordingly and know if they are valid moves.

**Acceptance Criteria**

1.  ✅ The game must check each chess move.
2.  ✅ If the move is valid, the game shows the piece being moved to the new tile.
3.  ✅ If the move is not valid, the game provides feedback to the user in some way and requests them to make a different move.

### **Use Case #2: Make a Move**

-   **Actor:** Player
-   **Preconditions:**
    -   The game application is launched and the board is being displayed.
    -   Players have been assigned as either black or white.
-   **Main Flow:**
    -   System requests the player to make a move.
    -   The system checks if the move is valid
        -   If yes, the system moves the piece, and starts the other player’s move
        -   If no, the system presents an error and requests that the player select a correct move for its piece.
    -   If the move is valid and the destination square has an opponent’s piece, that piece is removed from the board and given to the player.
    -   Turn switches to the other player.
-   **Alternate Flows:**
    -   Player can capture other pieces. In this case, remove that piece from the opposing player’s board.
    -   Knights can jump over other pieces.
-   **Postconditions:**
    -   The game is ready for the next player’s turn.

 **User Story:**

As a player of 2D Chess, I want to be able to capture opponent pieces.

**Acceptance Criteria**

1.  ✅ The game must check each chess move.
2.  ✅ If the move is valid, the game shows the opponent piece being captured.
3.  ✅ If the move is not valid, the game provides feedback to the user in some way and requests them to make a different move.

### **Use Case #3: Capture a Piece**

-   **Actor:** Player
-   **Preconditions:**
    -   The game application is launched and the board is being displayed.
-   **Main Flow:**
    -   On their turn, the player selects the piece they want to move and the destination square for the piece
    -   System ensures the selected piece belongs to that user
    -   System checks if the chosen move is valid
        -   If it's valid and the destination square has an opponent’s piece, the opponent’s piece is removed from the board
    -   The player’s piece moves to the destination square
    -   System switches the turn to the other player
-   **Postconditions:**
    -   The game is ready for the next player’s turn.

**User Story:**

As a player of 2D Chess, I want to know the outcome of the game.

**Acceptance Criteria**

1.  ✅ The game must have concluded (there are no more moves to be made).

### **Use Case #4: Win a Game**

-   **Actor:** Player
-   **Preconditions:**
    -   The game application is launched and the board is being displayed.
-   **Main Flow:**
    -   One of the players makes a move that involves capturing the opponent’s king
    -   The king is removed from the board
    -   System checks if any king has been removed the board after every successful move
        -   If yes, the system ends the game and assigns the winner to whoever captured their opponent’s king
    -   System shows which player one
-   **Postconditions:**
    -   The game has displayed the end state of the game.

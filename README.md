![Gradle Build](https://github.com/nu-cs-sqe/course-project-20252603-team-10-20252603-2/actions/workflows/main.yml/badge.svg)

# 2D Chess Game

## Contributors
- Cate Rose
- Samreen Ibrahim
- Vivian Chen

## Dependencies
- JDK 11
- JUnit 5.10
- Gradle 8.10
- GUI - Java Swing

## Acknowledgements
REFERENCES, SOURCE OF HELP ETC.

## Design Documentation

### Functionalities
- Two players max (black and white)
- Board (64 tiles)
- Place all pieces in starting positions at the start of the game
- How to win:
    - Checkmate the opponent's king
    - Stalemate
- Valid moves:
    - All moves should be validated by the game
    - Ensure no piece is blocking the path
- Track turns for each player
- Allow capturing opponent pieces

### Pieces (16 total per side)
- Pawns (8)
- King (1)
- Queen (1)
- Rook (2)
- Knight (2)
- Bishop (2)

### Architecture / Setup
Classes:
- Pieces (Pawn, King, Queen, Rook, Knight, Bishop)
- Board
- Location (x, y)
- Move (startBlock, endBlock)
- Game
- GUI - Java Swing (Board GUI)

### User Stories & Use Cases

**User Story 1: Start New Game**
As a player of 2D Chess, I want the game to be properly set up automatically when a new game starts, so that I can begin playing without manually setting up the board.

Acceptance Criteria:
- The game must not start unless there are 2 players.
- Each player must be assigned as a white or black player.
- The game should be initialized with 16 black and 16 white pieces in their proper starting tiles.
- The turn order must be initialized once setup is complete and will begin with white.

Use Case: Start New Game
- Actor: Player
- Preconditions: The game application is launched.
- Main Flow:
    1. Player clicks "Start Game".
    2. System shows the board.
    3. System requests white player to make a move.
    4. The system checks if the move is valid.
        - If yes, the system moves the piece and starts the other player's move.
        - If no, the system presents an error and requests a correct move.
    5. If the move is valid and the destination square has an opponent's piece, that piece is removed from the board and given to the player.
    6. Turn switches to the other player.
- Postconditions: Each player has a side with 16 pieces in the spread indicated above; the game is ready for the first turn.

**User Story 2: Make a Move**
As a player of 2D Chess, I want to be able to move my chess pieces accordingly and know if they are valid moves.

Acceptance Criteria:
- The game must check each chess move.
- If the move is valid, the game shows the piece being moved to the new tile.
- If the move is not valid, the game provides feedback to the user and requests a different move.

Use Case: Make a Move
- Actor: Player
- Preconditions: The game application is launched, the board is being displayed, and players have been assigned as black or white.
- Main Flow:
    1. System requests the player to make a move.
    2. The system checks if the move is valid.
        - If yes, the system moves the piece and starts the other player's move.
        - If no, the system presents an error and requests a correct move.
    3. If the move is valid and the destination square has an opponent's piece, that piece is removed from the board and given to the player.
    4. Turn switches to the other player.
- Alternate Flows:
    - Player can capture other pieces; the captured piece is removed from the opposing player's board.
    - Knights can jump over other pieces.
- Postconditions: The game is ready for the next player's turn.

**User Story 3: Capture a Piece**
As a player of 2D Chess, I want to be able to capture opponent pieces.

Acceptance Criteria:
- The game must check each chess move.
- If the move is valid, the game shows the opponent piece being captured.
- If the move is not valid, the game provides feedback and requests a different move.

Use Case: Capture a Piece
- Actor: Player
- Preconditions: The game application is launched and the board is being displayed.
- Main Flow:
    1. On their turn, the player selects the piece they want to move and the destination square.
    2. System ensures the selected piece belongs to that player.
    3. System checks if the chosen move is valid.
    4. If valid and the destination square has an opponent's piece, the opponent's piece is removed from the board.
    5. The player's piece moves to the destination square.
    6. System switches the turn to the other player.
- Postconditions: The game is ready for the next player's turn.

**User Story 4: Win a Game**
As a player of 2D Chess, I want to know the outcome of the game.

Acceptance Criteria:
- The game must have concluded (there are no more moves to be made).

Use Case: Win a Game
- Actor: Player
- Preconditions: The game application is launched and the board is being displayed.
- Main Flow:
    1. One of the players makes a move that involves capturing the opponent's king.
    2. The king is removed from the board.
    3. System checks if any king has been removed from the board after every successful move.
    4. If yes, the system ends the game and assigns the winner to whoever captured their opponent's king.
    5. System shows which player won.
- Postconditions: The game has displayed the end state of the game.

### Class & Method Design

**GameManager**
- `start()`
- `changeTurns()`
- `isGameOver()`

**GameBoard**
- `init()`
- `move()`
- `removePiece()`
- `isInsideBoard()`
- `isPieceHere()`

**Piece**
- `isSameColor()`
- `isValidMove()` — implementation varies by subclass

**Player**
- `getColor()`
- `capturedPieces()` — tracks points via a piece-to-point mapping
- `isInCheck()`
- `isCurrentTurn()` — returns boolean
- `getTotalPoints()` — uses `capturedPieces()`

### Gameboard
- 8x8 grid of alternating black and white slots
- Movable pieces

### Piece Movement & Capture Rules

| Piece | Movement | Capture |
|---|---|---|
| Pawn (8) | 1 forward, or 2 forward (initial move) | 1 diagonal |
| King (1) | 1 square in any direction (NESW or diagonal) | Same as movement |
| Queen (1) | NESW or diagonal, any distance | Same as movement |
| Rook (2) | Any number of squares horizontally or vertically; cannot jump over pieces | Same as movement |
| Knight (2) | L-shape (2 squares in one direction + 1 perpendicular); can jump over pieces | Same as movement |
| Bishop (2) | Any number of squares diagonally; cannot jump over pieces | Same as movement |

- **Position**: Row and column of each square on the board
- **Player**: Assigned either white or black

### BVA: Piece

**isSameColor()**
- Input: colors "B" and "W"
- Output: boolean
- All combinations:
    - ("B", "B") → True
    - ("B", "W") → False
    - ("W", "W") → True
    - ("W", "B") → False

**isValidMove()** (varies by subclass)
- Input: new position/coordinate, old position/coordinate
- Output: boolean
- Depends on whether a piece is in the position and what type of piece this is

## Grading Explanations

### JaCoCo and Pitest Code Coverage
Fully implemented before the Sunday cutoff deadline in a branch, but not merged into main until after the deadline, as we were trying to fix a few key mutations in the same PR. We realized it would be too extensive for one PR, so we chose to merge and continue the rest separately so everyone could take on a subsection of the work.

### Queen Code

JaCoCo reports one missed branch in `Queen.isValidMove()` on the `xMovement` boolean expression. This corresponds to the same-square case, where `start.getY() == end.getY()` is true and `start.getX() != end.getX()` is false. That path is intentionally handled earlier by the `start.equals(end)` guard clause, so it is unreachable at the `xMovement` expression. The behavior is tested by `isValidMove_Queen_sameSquare_returnFalse()`.

| Line | Missing branch | Why it cannot be covered |
|---|---|---|
| `xMovement = (start.getY() == end.getY()) && (start.getX() != end.getX())` | `start.getY() == end.getY()` true while `start.getX() != end.getX()` false | That requires `start.getY() == end.getY()` and `start.getX() == end.getX()`, which means `start.equals(end)`. The method returns false earlier for same-square moves, so this branch is unreachable. |

### Rook Code

6 unkillable mutants according to the Pitest report:

| Line | Surviving mutant | Equivalent? | Reason |
|---|---|---|---|
| 29 | `end.getY() > start.getY()` changed boundary, likely to `>=` | Yes | If `end.getY() == start.getY()` inside the horizontal branch, then start and end are the same square, already caught at line 20. |
| 30 | `end.getX() > start.getX()` changed boundary, likely to `>=` | Yes | If `end.getX() == start.getX()` inside the vertical branch, then start and end are the same square, already caught at line 20. |
| 78 | Removed call to `Board::setPiece` | Yes | The tests that trigger a simulated path error move the Rook to an empty square (where `originalPiece` is already null). Since `originalPiece` is null, line 78 would have been a no-op anyway, so the mutation has an identical execution path. |
| 102-103 | Replaced integer addition with subtraction; replaced integer multiplication with division | Yes | Swapping `+` to `-` or `*` to `/` for `currX + (dX * step)` is unkillable. Multiplying by 1/-1 returns the same numeric result as dividing by them. Flipping the sign on the addition just flips which directions (e.g., Up vs Down) are checked first chronologically — the loop still sweeps the full axis eventually. |
| 105 | Changed conditional boundary (2, 7) | Yes | This changes boundary safeguards. Any step past index 7 targets index 8. If mutated to allow index 8 to pass, line 109 executes `new Location(targetX, targetY)`. The `Location` class constructor immediately throws an `IllegalArgumentException` for values outside 0-7, crashing the thread cleanly before any logic leaks out. |

### Queen Code (Mutation Testing)

7 surviving mutants appear equivalent:

| Line | Mutant | Verdict | Why |
|---|---|---|---|
| 51 | Replaced boolean return with `true` for `Queen::isValidShape` | Equivalent | This mutant forces `isValidShape` to blindly return true instead of validating `xMovement \|\| yMovement \|\| diagonalMovement`. It is equivalent because of downstream structural validation filters inside the public `isValidMove` method. |
| 97 | Removed call to `Board::setPiece` | Equivalent | This mutant removes the step where the Queen is temporarily placed on the destination square (`end`) during the simulation sandbox phase of `doesMoveKingIntoCheck`. It is equivalent because of how the King's check engine validates threats: when the King runs `alliedKing.isInCheck`, it checks if any enemy piece can legally move to `kingLocation`. |
| 145-146 | Replaced integer addition with subtraction | Equivalent | This occurs inside the initialization of the directional scan loop inside `hasValidMoves`: `int targetRow = currentRow + rowDirection;`. The loop processes all directions from -1 to 1. When `rowDirection` is -1, changing addition to subtraction turns it into `currentRow - (-1)`, which is addition. When `rowDirection` is 1, it becomes subtraction. This means the mutant simply swaps the chronological order in which opposing directional rays (e.g., Up vs Down) are searched. Because `hasValidMoves` scans all directions until it finds a valid escape square, changing whether it checks North before South has no impact on the final outcome. |
| 147 | Changed conditional boundary | Equivalent | This mutates one of the matrix boundary guards inside the array scanning loop, shifting a condition like `targetRow < NUM_ROWS` to `targetRow <= NUM_ROWS` or vice versa. In this loop context, any step past index 7 directly targets index 8. If the boundary shifts to allow checking index 8, the loop immediately attempts `new Location(8, targetCol)` on line 149. Because the `Location` class constructor contains an invariant guard clause that throws an `IllegalArgumentException` for any value outside 0-7, the runtime thread stops instantly. |
| 153-154 | Replaced integer addition with subtraction | Equivalent | This occurs during the step update: `targetRow += rowDirection;`. Just like lines 145 and 146, replacing this with subtraction reverses the stepping vector math along the row axis for that specific loop pass. The direction array handles symmetric matching steps in the opposite loop iterations, leading to full matrix coverage by the time the method finishes execution. |

### King Code

| Line | Surviving mutant | Why it is equivalent |
|---|---|---|
| 32 | Changed conditional boundary in `canKingAttack()` | `canKingAttack()` is only called when an enemy king is found on the board. Two kings can never occupy the same square, so the boundary change only affects an impossible situation. No valid board state can distinguish the mutant. |
| 53 | `return false` → `return true` in `isValidMovementPattern()` when `start.equals(end)` | Even if the helper incorrectly returns true, the overall public method `isValidMove()` still returns false for a same-square move. The mutated helper does not change the observable result of the method being tested. |
| 74 | Removed `board.removePiece(start)` during move simulation | The king remains on both the start and end squares during the temporary simulation. However, `isInCheck()` ignores friendly pieces using `piece.isSameColor(this)`, so the extra king at the start square does not affect the check calculation. The final result remains unchanged for all reachable states. |

The remaining King mutants appear to be equivalent or masked because changing them does not change the public behavior that the tests can observe. The surviving same-square mutant in `isValidMovementPattern()` changes the `start.equals(end)` case from returning false to returning true, but `isValidMove()` still checks the destination afterward. Since the same square already contains the king, `isValidDestination()` rejects it as a friendly-occupied destination, so the final result is still false — the bad return value is masked by the later destination check.

The remaining `hasValidMoves()` mutants are also masked by how the method searches every adjacent direction and only returns a boolean answer. The self-square skip mutant changes the condition that skips `(rowOffset == 0 && colOffset == 0)`, but checking the king's current square does not create a valid move because the king already occupies that square. The arithmetic mutants that change `currentRow + rowOffset` or `currentCol + colOffset` to subtraction are masked because the nested loops already scan both positive and negative offsets. Flipping the sign often just causes the method to check a square that is already checked by another loop iteration, and since the method only reports whether any legal move exists — not which square was found — the observable result stays the same.

### Bishop Tests

8 Bishop mutants remain and are equivalent/masked:
- **Lines 58–59 (`isPathClear`)**: PIT changes `>` to `>=` for row/column direction. These are equivalent because `isPathClear()` is only called after `isDiagonal()` confirms a nonzero diagonal move, so `end.getX() == start.getX()` and `end.getY() == start.getY()` can never occur.
- **Lines 153–154 and 162–163 (`hasValidMoves`)**: PIT changes `+` to `-` in target row/column setup and updates. These are masked because `hasValidMoves()` searches all four diagonal directions and only returns a boolean, not the specific move found. Direction-specific and far-target tests still produce the same observable result.
- **Line 156 boundary mutants (`hasValidMoves`)**: These change board-edge loop boundaries. They are masked because off-board squares are never valid moves, and the public result remains only whether any valid move exists. Existing edge, blocked, open, directional, and far-target tests do not distinguish the mutated behavior.

### Pawn Tests

The remaining Pawn mutants are equivalent and do not represent test gaps:
- **Line 120 (`hasValidMoves`)**: PIT changes `2 * direction` to `2 / direction`. This is equivalent because `direction` is always 1 or -1, and both operators produce identical results for every value that `direction` can take.
- **Line 120 (`hasValidMoves`)**: PIT changes `currX + 2 * direction` to `currX - 2 * direction` in the two-forward selected square. This is masked because `hasValidMoves` only returns whether any valid move exists, not which one. The path-clear check in `isValidMove` rejects two-forward if the square is blocked, so the mutated coordinate never affects the result.
- **Line 131 (`hasValidMoves`)**: PIT changes one of the boundary checks to another boundary. This is masked because the existing edge tests (`hasValidMoves_PawnAtEdge_returnsFalse`, `hasValidMoves_Pawn_whiteAtRowZero_forwardOffBoard_returnsFalse`, `hasValidMoves_Pawn_blackAtRowSeven_forwardOffBoard_returnsFalse`) already constrain the pawn to board edges.

### Knight Tests

The 3 remaining knight mutants are masked:
- **Line 105 (`hasValidMoves`)**: PIT changes one of 4 boundary conditions (`targetX >= MIN_COORD`, `targetX <= MAX_COORD`, `targetY >= MIN_COORD`, `targetY <= MAX_COORD`) to an adjacent boundary. These are masked because `hasValidMoves` evaluates 8 candidate squares and returns true if any one results in a valid move. Shifting the boundary by one unit changes whether a single candidate is considered, but the overall true/false result is still determined by the remaining 7 candidates, which are not affected by the mutation. The existing tests cover the center, corner, edge, and direction-isolating cases.

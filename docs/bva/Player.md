# BVA Analysis for Player

**Method under test: incrementPoints()**

|  | System under test | Expected output | Implemented? |
| :---- | :---- | :---- |:-------------|
| Test Case 1 | There are 0 captured pieces. | getPoints(): 0 | yes          |
| Test Case 2 | All pieces were captured, assuming no pawns have been promoted. | getPoints() returns 39 | yes          |
| Test Case 3 | One piece has been captured (ex. pawn) | getPoints() returns 1 | yes          |
| Test Case 4 | All pieces were captured, with \>=1 pawn being promoted. | getPoints() returns \>39 | yes          |
| Test Case 5 | One Queen was captured. | getPoints() returns 9 | yes          |

### STEPS FOR BVA: `incrementPoints()`

1. Data Types
* Input
    * Cases
        * Player’s color: WHITE
        * Player’s color: BLACK
    * Cases
      * Pieces (PAWN, ROOK, KNIGHT, QUEEN, BISHOP)
* Output: Number
2. Test Cases
* 0 pieces captured - 0 points
* All pieces captured, no promotions - 39 points
* One pawn captured - 1 point
* All pieces captured, with promotions - >39 points
* Queen was captured - 9 points

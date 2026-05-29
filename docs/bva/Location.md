
### Method under test: Location() Constructor

| Test Number | x coord | y coord | Expected output           | Implemented? |
|-------------|---------|---------|---------------------------|--------------|
| 1           | 0       | 0       | Location obj              | yes          |
| 2           | 7       | 7       | Location obj              | yes          |
| 3           | 0       | 7       | Location obj              | yes          |
| 4           | 7       | 0       | Location obj              | yes          |
| 5           | -1      | 0       | IllegalArgumentException  | no           |
| 6           | 8       | 0       | IllegalArgumentException  | no           |
| 7           | 0       | -1      | IllegalArgumentException  | no           |
| 8           | 0       | 8       | IllegalArgumentException  | no           |

### STEPS FOR BVA: `Location()`
1) input equivalence classes and output equivalence classes
* Input:
  * an x value on the board between 0 and 7
  * a y value on the board between 0 and 7
* Output: 
  * A Location object (valid coords)
  * Illegal argument exception

2) and 3) determine data types and values
* Input: 
  * x: cases
    * case 1: valid value in interval [0,7]
    * case 2: invalid value (x<0 or x>7)
  * y: cases
    * case 1: valid value in interval [0,7]
    * case 2: invalid value (y<0 or y>7)
* Output: cases
  * case 1: A Location object (valid coords)
  * case 2: Illegal argument exception

4) test cases (see above)


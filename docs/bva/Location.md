
### Method under test: Location() Constructor

| Test Number | x coord | y coord | Expected output           | Implemented? |
|-------------|---------|---------|---------------------------|--------------|
| 1           | 0       | 0       | Location obj              | yes          |
| 2           | 7       | 7       | Location obj              | yes          |
| 3           | 0       | 7       | Location obj              | yes          |
| 4           | 7       | 0       | Location obj              | yes          |
| 5           | -1      | 0       | IllegalArgumentException  | yes          |
| 6           | 8       | 0       | IllegalArgumentException  | yes          |
| 7           | 0       | -1      | IllegalArgumentException  | yes          |
| 8           | 0       | 8       | IllegalArgumentException  | yes          |

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



### Method under test: equals()

| Test Number | Location             | Comparison         | Expected output   | Implemented? |
|-------------|----------------------|--------------------|-------------------|--------------|
| 1           | Location(x=1, y=1)   | same object        | true              | yes          |
| 2           | Location(x=3, y=4)   | Location(x=3, y=4) | true              | yes          |
| 3           | Location(x=3, y=4)   | Location(x=4, y=4) | false             | yes          |
| 4           | Location(x=3, y=4)   | Location(x=3, y=3) | false             | yes          |
| 5           | Location(x=3, y=4)   | "hello"            | false             | yes          |
| 6           | Location(x=3, y=4)   | null               | false             | yes          |


### STEPS FOR BVA: `equals()`
1) input equivalence classes and output equivalence classes
* Input:
  * a location object
  * something to compare the location object to 
    * another location obj
    * the exact location obj
    * non location object
    * null
* Output:
  * a yes/no answer

2) and 3) determine data types and values
* Input:
  * location object: pointer
    * location object
  * something to compare: cases
    * another location obj
    * the exact location obj
    * non location object
    * null
* Output: boolean (t/f)

4) test cases (see above)
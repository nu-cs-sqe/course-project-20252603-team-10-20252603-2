# BVA Analysis for Piece

## UPDATED TEST ANALYSIS

### Method under test: `isSameColor()`

|             | System under test | Expected output | Implemented? |
|-------------|-------------------|-----------------|--------------|
| Test Case 1 | "B", "B"          | True            | yes          |
| Test Case 2 | "B", "W"          | False           | yes          |
| Test Case 3 | "W", "B"          | False           | yes          |
| Test Case 4 | "W", "W"          | True            | yes          |


### STEPS FOR BVA: `isSameColor()`
1) input equivalence classes and output equivalence classes
* Input: string of current piece, either “black” or “white” 
* Output: a yes or no answer

2) determine data types
* Input: cases 
  * Input color = color of current piece 
  * Input color != color of current piece 
* Output: boolean

3)
* Input: cases 
  * Colors: “B” and “W” 
* Output: boolean 
  * True, False

4) 
* All combinations 
  * “B”, “B” --> T 
  * “B”, “W” --> F 
  * “W”, “W” --> T 
  * “W”, “B” --> F

    
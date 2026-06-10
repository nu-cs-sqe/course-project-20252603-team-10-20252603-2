## **BVA Analysis for GameManager()**

**Method under test: start()**

|             | System under test                           | Expected output | Implemented? |
|:------------|:--------------------------------------------| :---- |:-------------|
| Test Case 1 | Starting with 2 players                     | Game initializes successfully, players are assigned black/white, no exception | yes          |
| Test Case 2 | Starting with 1 players                     | Game fails to initialize due to insufficient number of players and returns an exception. | yes          |
| Test Case 3 | Starting with 0 players                     | Game fails to initialize due to insufficient number of players and returns an exception. | yes          |
| Test Case 4 | Starting with \>2 players                   | Game fails to start and returns an exception. | yes          |
| Test Case 5 | Starting with 2 players with the same color | Game fails to start and returns an exception. | yes          |

### STEPS FOR BVA: `start()`

1. Data Types
* Input: cases
  * Input number of players \== 0
  * Input number of players \== 1
  * Input number of players \== 2
  * Input number of players \== 3
* Output: void/Exception
2. Test Cases: 
* All combinations
  * 0 → ❌
  * 1 → ❌
  * 2 → ✅
  * 3 → ❌

**Method under test: changeTurns()**

|             | System under test                  | Expected output                                                                          | Implemented? |
|:------------|:-----------------------------------|:-----------------------------------------------------------------------------------------|:-------------|
| Test Case 1 | Transitioning from white to black. | UI change to show that it is the black player’s move. Only that player can move a piece. | yes          |
| Test Case 2 | Transitioning from black to white. | UI change to show that it is the white player’s move. Only that player can move a piece. | yes          |
| Test Case 3 | A tie/draw.                        | Neither player can move a piece, notification that the game ended in a draw.             | yes          |
| Test Case 4 | Game initially starts with White.  | The current player is White.                                                             | yes          |


### STEPS FOR BVA: `changeTurns()`
1. Data Types
* Input: cases
  * Input player color \== “B”
  * Input player color \== “W”
* Output: void/Exception
  * Function does not return anything, but currentPlayer should be changed.
2. Test Cases
* All combinations
  * “B” → “W”
  * “W” → “B”

**Method under test: isCheckmate()**
|             | System under test                                          | Expected output | Implemented? |
|:------------|:-----------------------------------------------------------| :---- |:-------------|
| Test Case 1 | King is in check and has no other valid moves.             | Returns true, game notification that the game is over. | yes          |
| Test Case 2 | King is in check and has 1 valid move.                   | Returns false, the game continues. | yes          |
| Test Case 3 | King is not in check and has >=1 valid move.               | Returns false, the game continues. | yes          |
| Test Case 4 | King is in check and has >1 valid move.                | Returns false, the game continues. | no          |
| Test Case 5 | King is not in check and has 0 valid moves.               | Returns false, the game is a stalemate. | no          |

### STEPS FOR BVA: `isCheckmate()`
1. Data Types
* Input: cases
  * isInCheck() for the King -> boolean
  * playerHasValidMoves() -> boolean
* Output: boolean
2. Test Cases
* All combinations
  * isInCheck() && hasValidMoves() == 0 --> TRUE
  * isInCheck() && hasValidMoves() == 1 --> FALSE
  * !isInCheck() && hasValidMoves() > 1--> FALSE
  * isInCheck() && hasValidMoves() > 1 --> FALSE
  * !isInCheck() && hasValidMoves() == 0 --> FALSE

**Method under test: isStalemate()**

|             | System under test                                 | Expected output                          | Implemented? |
|:------------|:--------------------------------------------------|:-----------------------------------------|:-------------|
| Test Case 1 | King is not in check and player has 0 valid moves | Returns true, game is a draw.            | yes          |
| Test Case 2 | King is not in check and player has 1 valid move  | Returns false, the game continues.       | yes          |
| Test Case 3 | King is in check and has no other valid moves.    | Returns false, game ends on a checkmate. | yes          |
| Test Case 4 | King is not in check and has >1 valid moves.      | Returns false, the game continues.       | yes            |

### STEPS FOR BVA: `isStalemate()`
1. Data Types
* Input: cases
  * isInCheck() for the King -> boolean
  * playerHasValidMoves() -> boolean
* Output: boolean
2. Test Cases
* All combinations
  * isInCheck() && hasValidMoves() == 0 --> FALSE
  * !isInCheck() && hasValidMoves() == 1--> FALSE
  * !isInCheck() && hasValidMoves() > 1 --> FALSE
  * !isInCheck() && hasValidMoves() == 0 --> TRUE
**Method under test: isGameOver()**

|             | System under test                                          | Expected output | Implemented? |
|:------------|:-----------------------------------------------------------| :---- |:-------------|
| Test Case 1 | King is in check and has no other valid moves.             | Returns true, game notification that the game is over. | yes          |
| Test Case 2 | King is in check and has >=1 valid move.                   | Returns false, the game continues. | yes          |
| Test Case 3 | Stalemate \- player is not in check but has 0 valid moves. | Returns true, game is a draw.  | yes          |
 | Test Case 4 | King is not in check and has >=1 valid move.               | Returns false, the game continues. | yes          |

### STEPS FOR BVA: `isGameOver()`

1. Data Types
* Input: cases 
  * No more valid moves and King isInCheck() is true
  * \>=1 valid move
* Output: boolean
  * True, False
2. Test Cases
* All combinations
  * King isInCheck() == true && there are 0 valid moves → ✅ 
  * King isInCheck() == true && there are >= 1 valid moves → ❌
  * King isInCheck() == false && there are 0 valid moves → Stalemate
  * King isInCheck() == false && there are >= 1 valid moves → ❌

## Method under test: `getMessage()` for GameManager

| Test Number | Locale                         | Key               | Key exists ? | Expected Output          | Implemented? |
|-------------|--------------------------------|-------------------|--------------|--------------------------|--------------|
| 1           | none set                       | `start.game`      | yes          | `"Start Game"` (default) | yes          |
| 2           | english                        | `start.game`      | yes          | `"Start Game"`           | yes          |
| 3           | spanish                        | `start.game`      | yes          | `"Iniciar Juego"`        | yes          |
| 4           | english then switch to spanish | `start.game`      | yes          | `"Iniciar Juego"`        | yes          |
| 5           | english                        | `nonexistent.key` | no           | `"nonexistent.key"`      | yes          |
| 6           | english                        | `checkmate`       | yes          | `"Checkmate!"`           | yes          |
| 7           | spanish                        | `checkmate`       | yes          | `"¡mate!"`               | yes          |
| 8           | french (new locale)            | `start.game`      | yes          | `"Lancer la partie"`     | yes          |

### STEPS FOR BVA: `getMessage()`

1) input equivalence classes and output equivalence classes
* input:
  * locale 
  * key (the string)
  * whether the key exists 
* output:
  * the translated string, or the key itself 

2) BVA catalog classes
* input:
  * locale: cases
  * key: cases
  * key exists in resources: boolean
* output:
  * a string: value

3) BVA catalog classes -- values
* input:
  * locale: cases
    * none set (should default to English)
    * ENGLISH
    * SPANISH
    * ENGLISH then switch to SPANISH (supports changing locales)
    * new locale being added (FRENCH)
  * key: cases
    * valid key that exists in the resources
    * invalid key that does not exist in the resources
  * key exists in resources: boolean
    * True 
    * False 
* output:
  * a string: value


**Method under test: movePiece()**

| Test Number | Current Turn | Location 1           | Location 2 | Piece being moved | Location 2 contents | Expected output                                                               | Implemented? |
|:------------|:-------------|:---------------------|:-----------|:------------------|:--------------------|:------------------------------------------------------------------------------|:-------------|
| 1           | WHITE        | (4,0)                | (5,0)      | null              | empty               | MoveResult.NO_PIECE_SELECTED (board/turn unchanged)                           | yes          |
| 2           | BLACK        | (0,1)                | (2,0)      | BLACK KNIGHT      | empty               | MoveResult.SUCCESS  (board/turn updated)                                      | yes          |                                         
| 3           | WHITE        | (7,6)                | (5,5)      | WHITE KNIGHT      | empty               | MoveResult.SUCCESS  (board/turn updated)                                      | yes          |                                          
| 4           | BLACK        | (0,0)                | (1,0)      | BLACK ROOK        | friendly            | MoveResult.INVALID_MOVE (board/turn unchanged)                                | yes          |                                         
| 5           | BLACK        | (2,0) (custom board) | (6,0)      | BLACK ROOK        | enemy               | MoveResult.SUCCESS  (board/turn updated, point count updated)                 | yes          |                                         
| 6           | WHITE        | (5,7) (custom board) | (1,1)      | WHITE QUEEN       | empty               | MoveResult.INVALID_MOVE (board/turn unchanged, point NOT count updated)       | yes          |                                         
| 7           | BLACK        | (7,6)                | (5,5)      | WHITE KNIGHT      | empty               | MoveResult.WRONG_PLAYER_PIECE (board/turn unchanged)                          | yes          |                                         
| 8           | BLACK        | (6,0) (custom board) | (7,0)      | BLACK PAWN        | empty               | MoveResult.PROMOTION_REQUIRED (board updated, turn unchanged until promotion) | yes          |                                         


### STEPS FOR BVA: `movePiece()`

1. Data Types
* Input: 
  * color of current turn
  * piece being moved
  * location 1
  * location 2
  * State of location 2
* Output:
  * A varying output that tells me if the piece move was successful or, if not, what the problem was
    * e.g. no piece selected, wrong player's piece selected, invalid move, success, pawn must be promoted
  * Side effects:
    * board state updated/unchanged
    * turn changed/unchanged
    * points updated if captured
2. BVA catalog types
* Input:
  * color of current turn: cases
  * Piece: pointer
  * Location 1: array indices
  * Location 2: array indices
  * State of location 2: cases
* Output
  * move result: cases:
  * Side effects: cases
3. Values
* Input:
  * Color of current turn: cases
    * BLACK
    * WHITE
  * Piece: pointer
    * Real piece object
    * Null pointer
  * Location 1: array indices
    * all indices are 0 at same time: [0][0] already tested in Location and in PieceType Tests
    * all largest valid value: [7][7] already tested in Location and in PieceType Tests
    * first index -1: [-1][3], CANT SET
    * second index -1: [3][-1] , CANT SET
    * first element too big: [8][3], CANT SET
    * second element too big: [3][8], CANT SET
  * Location 2: array indices
    * all indices are 0 at same time: [0][0] already tested in Location and in PieceType Tests
    * all largest valid value: [7][7] already tested in Location and in PieceType Tests
    * first index -1: [-1][3], CANT SET
    * second index -1: [3][-1] , CANT SET
    * first element too big: [8][3], CANT SET
    * second element too big: [3][8], CANT SET
  * State of location 2:
    * empty square
    * friendly square
    * enemy square
* Output
  * move result: cases:
    * NO_PIECE_SELECTED
    * WRONG_PLAYER_PIECE
    * INVALID_MOVE
    * SUCCESS
    * PROMOTION_REQUIRED
  * Side effects: cases
    * board state updated/unchanged
    * turn changed/unchanged
    * points updated if captured


**Method under test: promotePawn()**

| Test Number | PIECE        | Location | Replace with | Expected output                                                  | Implemented? |
|:------------|:-------------|:---------|:-------------|:-----------------------------------------------------------------|:-------------|
| 1           | WHITE PAWN   | (0,0)    | QUEEN        | white pawn replaced with white queen                             | yes          |
| 2           | BLACK PAWN   | (7,7)    | QUEEN        | black pawn replaced with black queen                             | yes          |
| 3           | WHITE PAWN   | (0,0)    | KNIGHT       | white pawn replaced with white knight                            | yes          |
| 4           | WHITE PAWN   | (0,0)    | BISHOP       | white pawn replaced with white bishop                            | yes          |
| 5           | WHITE PAWN   | (0,0)    | ROOK         | white pawn replaced with white rook                              | yes          |
| 6           | null         | (0,0)    | ROOK         | IllegalArgumentException ("Piece is not eligible for promotion") | yes          |
| 7           | BLACK KNIGHT | (7,7)    | QUEEN        | IllegalArgumentException ("Piece is not eligible for promotion") | yes          |
| 8           | WHITE PAWN   | (0,0)    | KING         | IllegalArgumentException ("Invalid promotion piece")             | yes          |


### STEPS FOR BVA: `promotePawn()`

1. Data Types
* Input:
  * pawn object
  * color of pawn
  * location to promote pawn
  * type of new piece (e.g. queen, rook, bishop, knight)
* Output:
  * void but the side effect of changing the pawn at the given location to the specified piece
2. BVA catalog types
* Input:
  * pawn object: pointer
  * color of pawn: cases
  * location to promote pawn: array indices (2d)
  * type of new piece: cases(e.g. queen, rook, bishop, knight)
* Output
  * void with side effects: cases
3. Values
* Input:
  * pawn object: pointer
    * pawn object
    * different piece
    * null
  * color of pawn: cases
    * BLACK
    * WHITE
  * location to promote pawn: array indices (2d)
    * all indices are 0 at same time: [0][0] 
    * all largest valid value: [7][7] 
    * first index -1: [-1][3], CANT SET
    * second index -1: [3][-1] , CANT SET
    * first element too big: [8][3], CANT SET
    * second element too big: [3][8], CANT SET
  * type of new piece: cases
    * QUEEN
    * KNIGHT
    * ROOK
    * BISHOP
* Output
  * void with side effects: cases
    * pawn -> queen
    * pawn -> rook
    * pawn -> bishop
    * pawn -> knight
    * non pawn rejected
    * empty location rejected


## Method under test: `loadSupportedLanguages()` for GameManager

**Testing scope:** 
This method is must be treated as functional/integration test because it loads the fixed resource files that are added in
with the program (i.e. languages.properties, messages_en.properties, etc). The files are NOT normal user inputs. 
Missing files, missing keys, empty configs, and exactly-one-language configs are documented below as configuration boundaries, 
but they're marked as CAN'T SET for the real tests.

Note that the fixed config has three preset languages at the time of writing this test, but any automated test should 
compare against the locale codes listed in languages.properties instead of hardcoding 3.

Additionally because `loadSupportedLanguages()` is private, these tests observe its behavior indirectly by constructing 
a `GameManager` and calling `getSupportedLanguages()`.

| Test Number | Fixed resource/config state                                                                        | Expected Output                                                                                                    | Implemented?  |
|-------------|----------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------|---------------|
| 1           | languages.properties exists and defines a non-empty supported= property                            | GameManager initializes without throwing MissingResourceException                                                  | yes           |
| 2           | supported= value contains more than one locale code, e.g. en,es,fr                                 | returns the same number of LanguageOptions as locale codes listed in languages.properties                          | no            |
| 3           | current supported= value contains at least two locale codes                                        | returned list contains at least two language choices                                                               | no            |
| 4           | each locale code listed in the fixed supported= property has a matching messages_locale.properties | loading supported languages completes without a missing bundle exception                                           | no            |
| 5           | every fixed messages_locale.properties contains language.name                                      | each LanguageOption has non-empty display name                                                                     | no            |
| 6           | Fixed supported= value lists English                                                               | returned list contains a LanguageOption for english                                                                | no            |
| 7           | Fixed supported= value lists Spanish                                                               | returned list contains a LanguageOption for spanish                                                                | no            |
| 8           | Fixed supported= value lists French                                                                | returned list contains a LanguageOption for french                                                                 | no            |
| 9           | Fixed supported= value has an order, e.g. en,es,fr                                                 | returned LanguageOption s that preserve the same order as the config file                                          | no            |
| 10          | Fixed locale codes are converted with Locale.forLanguageTag(code.trim())                           | returned LanguageOption locales matching Locale.forLanguageTag(...) for each configured code                       | no            |
| 11          | supported key is missing                                                                           | CAN'T SET with the real fixed files; documented configuration error case that would throw MissingResourceException | not automated |
| 12          | supported is empty, e.g. supported=                                                                | CAN'T SET with the real fixed files; documented invalid configuration case                                         | not automated |
| 13          | supported contains exactly one locale code                                                         | CAN'T SET with the real fixed files unless test resources are swapped in; documented collection-size boundary      | not automated |
| 14          | supported contains duplicate locale codes, e.g. en,en                                              | CAN'T SET with the real fixed files; documented duplicate-contents boundary                                        | not automated |
| 15          | A listed locale has no matching message bundle                                                     | CAN'T SET with the real fixed files; documented configuration error case that would throw MissingResourceException | not automated |
| 16          | A listed locale's bundle is missing language.name                                                  | CAN'T SET with the real fixed files; documented configuration error case that would throw MissingResourceException | not automated |

### STEPS FOR BVA / Functional Testing: `loadSupportedLanguages()`

1) input equivalence classes and output equivalence classes

* input/configuration:
  * fixed languages.properties file shipped with the app
  * fixed supported= property string inside languages.properties
  * fixed locale code strings listed in supported
  * fixed messages_<locale>.properties bundles shipped with the app
  * fixed language.name keys inside each message bundle

* output:
  * a List<LanguageOption> created during GameManager construction
  * or a configuration exception if the fixed resource files are invalid

2) BVA / catalog classes

* input/configuration:
  * supported locale list: collection size
  * locale code: string
  * locale code list contents: collection contents
  * resource bundle availability: configuration precondition
  * language.name key availability: configuration precondition

* output:
  * collection of LanguageOption objects
    * no exception for valid fixed resources
    * documented configuration exception cases for invalid fixed resources

3) BVA / catalog classes -- values

* input/configuration:
  * supported locale list: collection size
    * empty list / missing supported value: CAN'T SET with real fixed files (documented as invalid config)
    * exactly one locale code: CAN'T SET with real fixed files unless test resources are swapped in
    * more than one locale code: tested with current fixed resources
  * locale code: string
    * valid locale code, e.g. en: tested with fixed resources
    * valid locale code with whitespace, e.g. " es": documented if not present in the real file
    * locale code with no matching resource bundle: CAN'T SET with real fixed files; documented as invalid config
  * locale code list contents:
    * no duplicate locale codes: tested with current fixed resources if current config has no duplicates
    * duplicate locale codes: CAN'T SET with real fixed files; documented boundary
  * resource bundle availability:
    * True: tested by loading every currently configured locale
    * False: CAN'T SET with real fixed files; documented invalid config
  * language.name key exists in bundle:
    * True: tested by checking display names for every currently configured locale
    * False: CAN'T SET with real fixed files; documented invalid config

* output:
  * collection:
    * list contains more than one LanguageOption for the current fixed config
    * list contains at least two options for the locale-selection feature
    * each LanguageOption has the expected display name and locale
    * list order matches languages.properties
  * exception:
    * no exception for valid fixed resources
    * MissingResourceException documented for missing config, missing bundle, or missing language.name, but not automated



## Method under test: `getSupportedLanguages()` for GameManager

**IMPORTANT, Testing scope:** 
This method is non-UI, but it actively supports the language-selection GUI. Its tested using the real fixed resource 
files loaded by GameManager. The main behavior being tested is that it returns the configured languages and 
protects the internal supportedLanguages list by returning a defensive copy.

Note that the fixed config has three preset languages at the time of writing this test, but any automated test should
compare against the locale codes listed in languages.properties instead of hardcoding 3.

| Test Number | Initial fixed-resource state                                            | Action                                                                                         | Expected Output                                                                           | Implemented?  |
|-------------|-------------------------------------------------------------------------|------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|---------------|
| 1           | Current fixed config has a non-empty supported language list            | Call getSupportedLanguages()                                                                   | Returns a non-empty list of LanguageOptions                                               | no            |
| 2           | Current fixed config has more than one supported language               | Call getSupportedLanguages()                                                                   | Returns more than 1 LanguageOption                                                        | no            |
| 3           | Current fixed config supports at least two languages                    | Call getSupportedLanguages()                                                                   | Returned list size is at least 2, supporting the A-level locale requirement               | no            |
| 4           | Current fixed config includes English                                   | Call getSupportedLanguages()                                                                   | Returned list contains English language option                                            | no            |
| 5           | Current fixed config includes Spanish                                   | Call getSupportedLanguages()                                                                   | Returned list contains Spanish language option                                            | no            |
| 6           | Current fixed config includes French                                    | Call getSupportedLanguages()`                                                                  | Returned list contains French language option                                             | no            |
| 7           | Current fixed config lists languages in a specific order                | Call getSupportedLanguages()`                                                                  | Returned list preserves the configured order                                              | no            |
| 8           | Current fixed config has three preset languages at the time of writing  | Call getSupportedLanguages()                                                                   | Returned list size equals the number of configured locale codes, not a hardcoded number   | no            |
| 9           | Current fixed config is valid                                           | Call getSupportedLanguages() twice                                                             | Returns two different list objects with equivalent contents                               | no            |
| 10          | Current fixed config is valid                                           | Call getSupportedLanguages(), clear the returned list, then call getSupportedLanguages() again | Internal supportedLanguages list is unchanged because the method returns a defensive copy | no            |
| 11          | Current fixed config is valid                                           | Call getSupportedLanguages(), add a fake LanguageOption, then call the getter again            | Internal supportedLanguages list does not include the fake language option                | no            |
| 12          | Internal supported language list has exactly one language               | CAN'T SET with the real fixed files unless test resources are swapped in                       | Documented collection-size boundary, not automated with real fixed files                  | not automated |
| 13          | Internal supported language list is empty                               | CAN'T SET with the real fixed files because app should ship with at least two languages        | Documented collection-size boundary, not automated with real fixed files                  | not automated |

### STEPS FOR BVA / Functional Testing: `getSupportedLanguages()`

1) input equivalence classes and output equivalence classes

* input/configuration:
  * current supportedLanguages field inside GameManager
  * number of supported languages loaded from the fixed resource files (should be three from config)
  * contents of the supported language list loaded from fixed resource files
  * caller's modification of the returned list

* output:
  * a copy of the supported languages list

2) BVA / catalog classes

* input/configuration:
  * supported languages list: collection size
  * supported languages list contents: collection contents
  * returned list modification: collection/reference behavior

* output:
  * collection of LanguageOption pointers/objects
    * collection size/count
  * side effect:
    * defensive copy behavior

3) BVA / catalog classes -- values

* input/configuration:
  * supported languages list: collection 
    * size
      * empty list: CAN'T SET with real fixed files because the app should support at least two languages
      * exactly one language: CAN'T SET with real fixed files unless test resources are swapped in
      * more than one language: tested with current fixed resources
    * contents:
      * contains English: testable 
      * contains Spanish: testable 
      * contains French: testable
      * contains all configured languages: testable by comparing list size and expected locales from the fixed config
  *  returned list modification:
    * caller does not modify returned list: tested by normal getter call
    * caller clears returned list: tested for defensive copy
    * caller adds a fake LanguageOption to returned list: tested for defensive copy

* output:
  * collection:
    * returned list not empty
    * returned list has >1 one language for the current fixed config
    * returned list contains expected LanguageOptions
    * returned list size matches configured supported languages
    * returned list preserves configured order
  * defensive copy:
    * modifying the returned list does not modify GameManager's internal list
    * repeated calls return equivalent contents but not the same list object
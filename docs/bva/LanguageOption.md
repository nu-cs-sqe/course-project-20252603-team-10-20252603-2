## Method under test: `LanguageOption(String displayName, Locale locale)`, basic functional testing

**NOTE:** This constructor stores a display name and locale for later retrieval. Functional testing is more appropriate than BVA.

| Test Number | Input                           | Expected Output               | Implemented? |
|-------------|---------------------------------|-------------------------------|--------------|
| 1           | `"English"`, `Locale.ENGLISH`   | Object created successfully   | yes          |
| 2           | `"Español"`, `new Locale("es")` | Object created successfully   | yes          |


## Method under test: `toString()`, basic functional testing

**NOTE:** This method returns the stored display name.

| Test Number | Object State                                  | Expected Output | Implemented? |
|-------------|-----------------------------------------------|-----------------|--------------|
| 1           | `LanguageOption("English", Locale.ENGLISH)`   | `"English"`     | yes          |
| 2           | `LanguageOption("Español", new Locale("es"))` | `"Español"`     | yes          |


## Method under test: `getLocale()`, basic functional testing

**NOTE:** This method returns the stored locale associated with the language option (getter).

| Test Number | Object State                                  | Expected Output       | Implemented? |
|-------------|-----------------------------------------------|-----------------------|--------------|
| 1           | `LanguageOption("English", Locale.ENGLISH)`   | `Locale.ENGLISH`      | no           |
| 2           | `LanguageOption("Español", new Locale("es"))` | Spanish locale object | no           |

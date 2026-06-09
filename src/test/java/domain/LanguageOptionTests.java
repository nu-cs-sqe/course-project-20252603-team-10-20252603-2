package domain;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LanguageOptionTests {

    @Test
    public void languageOption_englishLocale_objectCreatedSuccessfully() {
        LanguageOption option =
                new LanguageOption("English", Locale.ENGLISH);

        assertNotNull(option);
    }

}

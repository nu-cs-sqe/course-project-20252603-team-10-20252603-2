package domain;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LanguageOptionTests {

    @Test
    public void languageOption_englishLocale_objectCreatedSuccessfully() {
        LanguageOption option =
                new LanguageOption("English", Locale.ENGLISH);

        assertNotNull(option);
    }

    @Test
    public void languageOption_spanishLocale_objectCreatedSuccessfully() {
        LanguageOption option =
                new LanguageOption("Español", new Locale("es"));

        assertNotNull(option);
    }

    @Test
    public void toString_englishLanguageOption_returnsEnglish() {
        LanguageOption option =
                new LanguageOption("English", Locale.ENGLISH);

        assertEquals("English", option.toString());
    }

    @Test
    public void toString_spanishLanguageOption_returnsSpanish() {
        LanguageOption option =
                new LanguageOption("Español", new Locale("es"));

        assertEquals("Español", option.toString());
    }

}

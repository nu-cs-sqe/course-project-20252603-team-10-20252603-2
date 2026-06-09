package domain;

import java.util.Locale;

public class LanguageOption {

    private final String displayName;
    private final Locale locale;

    public LanguageOption(String displayName, Locale locale) {
        this.displayName = displayName;
        this.locale = locale;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
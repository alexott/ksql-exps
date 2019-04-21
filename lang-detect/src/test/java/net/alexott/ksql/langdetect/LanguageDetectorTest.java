package net.alexott.ksql.langdetect;

import junit.framework.TestCase;

public class LanguageDetectorTest extends TestCase {
    LanguageDetector languageDetector = new LanguageDetector();

    public void testLangRussian() {
        String lang = languageDetector.langdetect("Это русский текст");
        assertNotNull(lang);
        assertEquals("ru", lang);
    }

    public void testLangVietnamese() {
        String lang = languageDetector.langdetect("Xin chào");
        assertNotNull(lang);
        assertEquals("vi", lang);
    }

    public void testLangGerman() {
        String lang = languageDetector.langdetect("Das ist Deutsch");
        assertNotNull(lang);
        assertEquals("de", lang);
    }

    public void testLangEnglish() {
        String lang = languageDetector.langdetect("This is English");
        assertNotNull(lang);
        assertEquals("en", lang);
    }
}
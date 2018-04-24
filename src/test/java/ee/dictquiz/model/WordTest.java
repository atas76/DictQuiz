package ee.dictquiz.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class WordTest {

    Word wordGr = new Word("ee:sõda,gr:πόλεμος");
    Word wordEn = new Word("ee:kannama,en:to wear");
    Word wordAll = new Word("ee:kalda,en:shore,gr:ακτή");

    @Test
    public void testTranslation() {

        assertEquals("sõda", wordGr.getTranslation("ee"));
        assertEquals("πόλεμος", wordGr.getTranslation("gr"));

        assertEquals("to wear", wordEn.getTranslation("en"));

        assertEquals("kalda", wordAll.getTranslation("ee"));
        assertEquals("shore", wordAll.getTranslation("en"));
        assertEquals("ακτή", wordAll.getTranslation("gr"));
    }

    @Test
    public void testTargetTranslation() {

        assertNotEquals("sõda", wordGr.getTargetTranslation("ee"));
        assertNotEquals("πόλεμος", wordGr.getTargetTranslation("gr"));

        assertNotEquals("to wear", wordEn.getTargetTranslation("en"));

        assertNotEquals("kalda", wordAll.getTargetTranslation("ee"));
        assertNotEquals("shore", wordAll.getTargetTranslation("en"));
        assertNotEquals("ακτή", wordAll.getTargetTranslation("gr"));
    }

    @Test
    public void testTranslatesTo() {

        assertTrue(wordGr.translatesTo("πόλεμος", "ee2"));
        assertTrue(wordGr.translatesTo("sõda", "2ee"));

        assertTrue(wordEn.translatesTo("to wear", "ee2"));
        assertTrue(wordEn.translatesTo("kannama", "2ee"));

        assertTrue(wordAll.translatesTo("shore", "ee2"));
        assertTrue(wordAll.translatesTo("ακτή", "ee2"));
        assertTrue(wordAll.translatesTo("kalda", "2ee"));
    }
}

package com.codecool.filepartreader;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileWordAnalyzerTest {
    //  Get absolute path for filePath
    private String relPath = "src/test/resources/test.txt";
    private File testFile = new File(relPath);
    private String absPath = testFile.getAbsolutePath();

    @Test
    public void testConstructor_IsFilePartReaderNotNull() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup(absPath, 10, 12);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        assertNotNull(fileWordAnalyzer.filePartReader);
    }

    @Test
    public void testGetWordsOrderedAlphabetically_IsTypeCorrect() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup(absPath, 1, 1);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        try {
            assertEquals(fileWordAnalyzer.getWordsOrderedAlphabetically().getClass(), ArrayList.class);
        } catch (IOException error) {
            System.err.println("ERROR! " + error);
        }
    }

    @Test
    public void testGetWordsOrderedAlphabetically_IsContentCorrect() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup(absPath, 1, 1);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        try {
            String actual = fileWordAnalyzer.getWordsOrderedAlphabetically().toString();
            String expected = "[amet, dolor, ipsum, Lorem, sit]";
            assertEquals(expected, actual);
        } catch (IOException error) {
            System.err.println("ERROR! " + error);
        }
    }

    @Test
    public void testGetWordsContainingSubstring_IsContentCorrect() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup(absPath, 3, 8);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        try {
            String actual = fileWordAnalyzer.getWordsContainingSubstring("or").toString();
            String expected = "[tempor, labore, dolore, laboris, dolor]";
            assertEquals(expected, actual);
        } catch (IOException error) {
            System.err.println("ERROR! " + error);
        }
    }

    @Test
    public void testGetStringsWhichPalindromes_IsContentCorrect() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup(absPath, 4, 11);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        try {
            String actual = fileWordAnalyzer.getStringsWhichPalindromes().toString();
            String expected = "[minim, esse]";
            assertEquals(expected, actual);
        } catch (IOException error) {
            System.err.println("ERROR! " + error);
        }
    }
}

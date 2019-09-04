package com.codecool.filepartreader;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FilePartReaderTest {
    //  Get absolute path for filePath
    private String relPath = "src/test/resources/test.txt";
    private File testFile = new File(relPath);
    private String absPath = testFile.getAbsolutePath();
    private String testFileContent = "Lorem ipsum dolor sit amet, \n" +
                                     "consectetur adipisicing elit, sed do \n" +
                                     "eiusmod tempor incididunt ut labore \n" +
                                     "et dolore magna aliqua. Ut \n" +
                                     "enim ad minim veniam, quis \n" +
                                     "nostrud exercitation ullamco laboris nisi \n" +
                                     "ut aliquip ex ea commodo \n" +
                                     "consequat. Duis aute irure dolor \n" +
                                     "in reprehenderit in voluptate velit \n" +
                                     "esse cillum dolore eu fugiat \n" +
                                     "nulla pariatur. Excepteur sint occaecat \n" +
                                     "cupidatat non proident, sunt in \n" +
                                     "culpa qui officia deserunt mollit \n" +
                                     "anim id est laborum.";

    @Test
    public void testSetup_IsFromLineLowerThan1ThrowsException() {
        FilePartReader filePartReader = new FilePartReader();
        assertThrows(IllegalArgumentException.class, ()-> {
            filePartReader.setup(absPath, 0, 5);
        });
    }

    @Test
    public void testSetup_IsFromLineLowerThanToLine() {
        FilePartReader filePartReader = new FilePartReader();
        assertThrows(IllegalArgumentException.class, ()-> {
            filePartReader.setup(absPath, 5, 2);
        });
    }

    @Test
    public void testRead_IsExceptionThrownIfFileNotFound() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup(absPath + "/fakePath", 5, 6);
        assertThrows(FileNotFoundException.class, ()-> {
            filePartReader.read();
        });
    }

    @Test
    public void testRead_IsFileContentReadCorrectly() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup(absPath, 5, 6);
        try {
            assertEquals(this.testFileContent, filePartReader.read());
        } catch (IOException error) {
            System.err.println("ERROR! " + error);
        }
    }

    @Test
    public void testReadLines_IsReadContentCorrect_SomeLines() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup(absPath, 7, 8);
        try {
            assertEquals("ut aliquip ex ea commodo \n" +
                    "consequat. Duis aute irure dolor",
                    filePartReader.readLines());
        } catch (IOException error) {
            System.err.println("ERROR! " + error);
        }
    }

    @Test
    public void testReadLines_IsReadContentCorrect_FirstLineAlso() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup(absPath, 1, 3);
        try {
            assertEquals("Lorem ipsum dolor sit amet, \n" +
                    "consectetur adipisicing elit, sed do \n" +
                    "eiusmod tempor incididunt ut labore",
                    filePartReader.readLines());
        } catch (IOException error) {
            System.err.println("ERROR! " + error);
        }
    }

    @Test
    public void testReadLines_IsReadContentCorrect_FirstLineOnly() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup(absPath, 1, 1);
        try {
            assertEquals("Lorem ipsum dolor sit amet,",
                    filePartReader.readLines());
        } catch (IOException error) {
            System.err.println("ERROR! " + error);
        }
    }



}
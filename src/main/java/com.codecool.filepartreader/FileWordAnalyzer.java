package com.codecool.filepartreader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileWordAnalyzer {
    private FilePartReader filePartReader;

    public FileWordAnalyzer(FilePartReader filePartReader) {
        this.filePartReader = filePartReader;
    }

    public List getWordsOrderedAlphabetically() throws IOException {
        String content = this.filePartReader.readLines();
        List<String> words = Arrays.asList(content.split("\\s+"));
        words.sort(String::compareToIgnoreCase);

        for (int i = 0; i < words.size(); i++) {
            words.set(i, words.get(i).replaceAll("[.,?!:;\"\']", ""));
        }
        return words;
    }

    public List getWordsContainingSubstring(String subString) throws IOException {
        String content = this.filePartReader.readLines();
        List<String> words = Arrays.asList(content.split("\\s+"));
        List<String> wordsContainingSubstring = new ArrayList<>();

        for (String word : words) {
            if (word.contains(subString)) wordsContainingSubstring.add(word);
        }
        return wordsContainingSubstring;
    }

    public List getStringsWhichPalindromes() throws IOException {
        String content = this.filePartReader.readLines();
        List<String> words = Arrays.asList(content.split("\\s+"));
        List<String> palindromes = new ArrayList<>();

        for (String word : words) {
            StringBuilder stringBuilder = new StringBuilder(word);
            String reverseWord = stringBuilder.reverse().toString();
            if (word.equals(reverseWord)) {
                palindromes.add(word);
            }
        }
        return palindromes;
    }
}

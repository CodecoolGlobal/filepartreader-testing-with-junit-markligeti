package com.codecool.filepartreader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FilePartReader {
    private String filePath;
    private Integer fromLine;
    private Integer toLine;

    public FilePartReader() {
        this.filePath = "zzz";
        this.fromLine = 0;
        this.toLine = 0;
    }

    public void setup(String filePath, Integer fromLine, Integer toLine) {
        this.filePath = filePath;
        this.fromLine = fromLine;
        this.toLine = toLine;
    }

    public String read() throws IOException {
        File file = new File(this.filePath);
        Scanner scanner = new Scanner(file);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder
                    .append(scanner.nextLine())
                    .append("\n");
        }
        return stringBuilder.toString().trim();
    }

    public String readLines() throws IOException {
        String content = this.read();
        String[] allLines = content.split("\n");
        List<String> lines = new ArrayList<>(Arrays.asList(allLines)
                .subList(this.fromLine - 1, this.toLine));

        StringBuilder stringBuilder = new StringBuilder();
        for (String line : lines) {
            stringBuilder
                    .append(line)
                    .append("\n");
        }
        return stringBuilder.toString().trim();
    }

    public static void main(String[] args) {
        FilePartReader test1 = new FilePartReader();
        test1.setup("/home/ligetimark/codecool/oop/SI5/filepartreader-testing-with-junit-markligeti/test.txt", 1, 5);
        try {
            System.out.println(test1.readLines());
        } catch(IOException error) {
            System.err.println("ERROR! " + error);
        }
    }
}

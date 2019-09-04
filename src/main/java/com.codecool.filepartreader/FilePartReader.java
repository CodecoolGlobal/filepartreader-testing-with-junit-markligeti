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
        if (fromLine < 1) {
            throw new IllegalArgumentException("Parameter 'fromLine' cannot be lower than 1.");
        } else {
            this.fromLine = fromLine;
        }
        if (toLine < fromLine) {
            throw new IllegalArgumentException("Parameter 'toLine' cannot be lower than 'fromLine'!");
        } else {
            this.toLine = toLine;
        }
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
        //  Get absolute path for filePath
        String relPath = "src/test/resources/test.txt";
        File testFile = new File(relPath);
        String absPath = testFile.getAbsolutePath();

        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup(absPath, 1, 5);
        try {
            System.out.println(filePartReader.readLines());
        } catch (IOException error) {
            System.err.println("ERROR! " + error);
        }
    }
}

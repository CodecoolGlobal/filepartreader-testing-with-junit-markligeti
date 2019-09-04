package com.codecool.filepartreader;

import java.io.File;
import java.io.FileReader;
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
        this.filePath = "aaa";
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

        String fileContent = "";
        while (scanner.hasNext()) {
            fileContent += (scanner.next() + "\n");
        }
        return fileContent.trim();
    }

    public String readLines() {
        try {
            String fileContent = this.read();
            String[] fileContentLines = fileContent.split("\n");
            List<String> filePartLines = new ArrayList<>();

            for(int i = this.fromLine-1; i < this.toLine; i++) {
                filePartLines.add(fileContentLines[i]);
            }
            return filePartLines.toString();
        }
        catch (IOException error) {
            System.err.println("ERROR! " + error);
            return "";
        }
    }

    public static void main(String[] args) {
        FilePartReader test1 = new FilePartReader();
        test1.setup("/home/ligetimark/codecool/oop/SI5/filepartreader-testing-with-junit-markligeti/test.txt", 1, 5);
        System.out.println(test1.readLines());
    }

}

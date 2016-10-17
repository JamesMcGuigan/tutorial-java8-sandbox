package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO:
 * - Read text file (both whole and as file buffer)
 * - TreeMap data structure
 * - Threads and ThreadPool
 */
public class FileReader {
    protected String filepath = "src/main/resources/Frank Herbert - Dune.txt";

    public FileReader() {
    }

    public void run() {
        LongLoop.timer(() -> ioFileInputStream(filepath));
        LongLoop.timer(() -> nioBufferedReader(filepath));
        LongLoop.timer(() -> nioFilesReadAllBytes(filepath));

        LongLoop.timer(() -> ioFileInputStream(filepath));
        LongLoop.timer(() -> nioBufferedReader(filepath));
        LongLoop.timer(() -> nioFilesReadAllBytes(filepath));
    }

    /**
     * Reads file one byte at a time, using fileInputStream.read(), storing data in a char[] array
     * 51ms - slowest method
     * @param filepath
     * @return
     */
    public String ioFileInputStream(String filepath) {
        File file = new File(filepath);
        int fileLength = (int) file.length();
        int charCount  = 0;
        char[] charArray = new char[fileLength];

        try {
            // NOTE: new FileInputStream(file) alone fails testFileContentsSame() test due to UTF-8 char
            BufferedReader fileInputStream = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            int fileByte;
            while( (fileByte = fileInputStream.read()) != -1 ) {
                charArray[charCount] = (char) fileByte;
                charCount++;
            }
        } catch (FileNotFoundException exception) {
            System.out.println("ioFileInputStream() - FileNotFoundException: " + exception.toString());
        } catch (IOException exception) {
            System.out.println("ioFileInputStream() - IOException: " + exception.toString());
        }

        System.out.println("ioFileInputStream() - charCount: " + charCount);
        System.out.println("ioFileInputStream() - fileLength: " + fileLength);

        assert fileLength >= charCount;                 // fileLength == 1187237; charCount == 1187231; due to UTF8 chars
        return String.valueOf(charArray, 0, charCount); // testFileContentsSame() fails if null chars[] at end of array are not stripped
    }

    /**
     * Reads file one line at a time using Files.newBufferedReader() and StringBuilder()
     * 26ms - use of StringBuilder actually makes this method slower than nioFilesReadAllBytes()
     * @param filepath
     * @return
     */
    public String nioBufferedReader(String filepath) {
        StringBuilder lines = new StringBuilder();
        int lineCount = 0;
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(filepath), StandardCharsets.UTF_8);
            String line;
            while( (line = reader.readLine()) != null ) {
                //System.out.println(line);
                lines.append(line);
                lineCount++;
            }
        } catch (IOException exception) {
            System.out.println("nioBufferedReader() - IOException: " + exception.toString());
        }

        System.out.println("nioBufferedReader() - lineCount: " + lineCount);

        return lines.toString();
    }

    /**
     * Reads entire file in one go
     * 5ms - strangely enough, this is actually the fastest method for just extracting content
     * @param filepath
     * @return
     */
    public String nioFilesReadAllBytes(String filepath) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filepath)), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            System.out.println("nioFilesReadAllBytes() - IOException: " + exception.toString());
        }

        System.out.println("nioFilesReadAllBytes() - content.length(): " + content.length());
        return content;
    }

}

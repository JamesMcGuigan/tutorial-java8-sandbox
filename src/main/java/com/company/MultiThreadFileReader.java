package com.company;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MultiThreadFileReader {
    protected static volatile int lineNumber = 0;
    protected static final int maxThreads = 100;
    protected static final String filepath = "src/main/resources/Frank Herbert - Dune.txt";
    protected List<Thread> threads;
    protected BufferedReader reader;
    protected StringBuffer   content;
    protected ExecutorService executorService;

    public static void main(String[] args) {
        MultiThreadFileReader multiThreadFileReader = new MultiThreadFileReader();
        multiThreadFileReader.simpleThreadRead();
    }

    public MultiThreadFileReader() {
        try {
            reader  = Files.newBufferedReader(Paths.get(MultiThreadFileReader.filepath), StandardCharsets.UTF_8);
            content = new StringBuffer();
            threads = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String simpleThreadRead() {
        for( int i = 0; i < maxThreads; i++ ) {
            Thread thread = new ThreadReader(i);
            threads.add(thread);
        }
        for( Thread thread : threads ) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return content.toString();
    }

    private class ThreadReader extends Thread {
        private String line;
        public final int threadNumber;

        public ThreadReader(int threadNumber) {
            this.threadNumber = threadNumber;
        }

        @Override
        public void run() {
            try {
                while(true) {
                    synchronized(reader) {
                        lineNumber++;
                        String line = line = reader.readLine();
                        if( line == null ) {
                            break;
                        }
                        System.out.format("(thread %02d) -> %d: %s\n", threadNumber, lineNumber, line);

                        content.append(line+"\n");
                    }
                    Thread.currentThread().sleep(10);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

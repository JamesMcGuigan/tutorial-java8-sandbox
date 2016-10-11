package com.company;

import java.security.Provider;
import java.time.Instant;
import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LongLoop {
    public static void main(String[] args) {
        new LongLoop();
    }

    public LongLoop() {
        this.bigLong();
        this.littleLong();

        this.timer(() -> this.bigLong());
        this.timer(() -> this.littleLong());

        this.timer(this::bigLong);
        this.timer(this::littleLong);
    }

    // For: void littleLong()
    public void timer(Runnable func) {
        Instant start = Instant.now();

        func.run();

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis() + " ms");
    }

    // For: void bigLong()
    public void timer(Supplier<Long> func) {
        Instant start = Instant.now();

        func.get();

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis() + " ms");
    }


    public void littleLong() {
        Instant start = Instant.now();

        long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        System.out.println("littleLong: " + sum);

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis() + " ms");

        // return sum;
    }
    public Long bigLong() {
        Instant start = Instant.now();

        Long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        System.out.println("bigLong:    " + sum);

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis() + " ms");

        return sum;
    }
}

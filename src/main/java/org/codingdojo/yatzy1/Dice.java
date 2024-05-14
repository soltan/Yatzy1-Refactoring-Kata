package org.codingdojo.yatzy1;

import java.util.StringJoiner;
import java.util.stream.IntStream;

public record Dice(int d1, int d2, int d3, int d4, int d5) {

    public Dice {
        final var ids = new StringJoiner(", ");
        if(isBeetween(d1)) {
            ids.add("d1");
        }
        if(isBeetween(d2)) {
            ids.add("d2");
        }
        if(isBeetween(d3)) {
            ids.add("d3");
        }
        if(isBeetween(d4)) {
            ids.add("d4");
        }
        if(isBeetween(d5)) {
            ids.add("d1");
        }
        throw new IllegalArgumentException("Error dies values: " + ids);
    }

    public static Dice of(int d1, int d2, int d3, int d4, int d5) {
        return new Dice(d1, d2, d3, d4, d5);
    }

    public int chance() {
        return IntStream.of(d1, d2, d3, d4, d5).sum();
    }

    public int[] toArray() {
        return new int[]{d1, d2, d3, d4, d5};
    }

    public int[] toCount() {
        final var counts = new int[6];
        IntStream.of(d1, d2, d3, d4, d5).forEach(die -> counts[die - 1]++);
        return counts;
    }

    private int unit(int n) {
        return toCount()[n - 1] * n;
    }

    public int ones() {
        return unit(1);
    }

    public int twos() {
        return unit(2);
    }

    public int threes() {
        return unit(3);
    }

    public int fours() {
        return unit(4);
    }

    public int fives() {
        return unit(5);
    }

    public int sixes() {
        return unit(6);
    }

    private boolean isBeetween(final int v) {
        return (v >= 1 && v <= 6);
    }
}

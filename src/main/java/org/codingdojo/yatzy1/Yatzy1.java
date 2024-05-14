package org.codingdojo.yatzy1;

import java.util.Arrays;
import java.util.stream.IntStream;

public record Yatzy1(Dice dice) {

    public Yatzy1(int d1, int d2, int d3, int d4, int d5) {
        this(Dice.of(d1, d2, d3, d4, d5));
    }

    public static Yatzy1 of(int d1, int d2, int d3, int d4, int d5) {
        return new Yatzy1(d1, d2, d3, d4, d5);
    }

    public static Yatzy1 of(final Dice dice) {
        return new Yatzy1(dice);
    }

    public static int chance(int d1, int d2, int d3, int d4, int d5) {
        return Dice.of(d1, d2, d3, d4, d5).chance();
    }

    public static int score_pair(int d1, int d2, int d3, int d4, int d5) {
        return Yatzy1.of(d1, d2, d3, d4, d5).score_pair();
    }

    public static int two_pair(int d1, int d2, int d3, int d4, int d5) {
        return Yatzy1.of(d1, d2, d3, d4, d5).two_pair();
    }

    public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5) {
        return Yatzy1.of(d1, d2, d3, d4, d5).three_of_a_kind();
    }

    public static int four_of_a_kind(int d1, int d2, int d3, int d4, int d5) {
        return Yatzy1.of(d1, d2, d3, d4, d5).four_of_a_kind();
    }

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5) {
        return Yatzy1.of(d1, d2, d3, d4, d5).smallStraight();
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {
        return Yatzy1.of(d1, d2, d3, d4, d5).largeStraight();
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {
        return Yatzy1.of(d1, d2, d3, d4, d5).fullHouse();
    }

    public static int yatzy(int d1, int d2, int d3, int d4, int d5) {
        final var tallies = Yatzy1.of(d1, d2, d3, d4, d5).toCount();
        return Arrays.stream(tallies)
            .filter(i -> i == 5)
            .map(__ -> 50)
            .findFirst().orElse(0);
    }

    private int[] toCount() {
        return dice.toCount();
    }

    public int ones() {
        return this.dice.ones();
    }

    public int twos() {
        return this.dice.twos();
    }

    public int threes() {
        return this.dice.threes();
    }

    public int fours() {
        return this.dice.fours();
    }

    public int fives() {
        return this.dice.fives();
    }

    public int sixes() {
        return this.dice.sixes();
    }

    public static int ones(int d1, int d2, int d3, int d4, int d5) {
        return Yatzy1.of(d1, d2, d3, d4, d5).ones();
    }

    public static int twos(int d1, int d2, int d3, int d4, int d5) {
        return Yatzy1.of(d1, d2, d3, d4, d5).twos();
    }

    public static int threes(int d1, int d2, int d3, int d4, int d5) {
        return Yatzy1.of(d1, d2, d3, d4, d5).threes();
    }

    public static int fours(int d1, int d2, int d3, int d4, int d5) {
        return Yatzy1.of(d1, d2, d3, d4, d5).fours();
    }

    public static int fives(int d1, int d2, int d3, int d4, int d5) {
        return Yatzy1.of(d1, d2, d3, d4, d5).fives();
    }

    public static int sixes(int d1, int d2, int d3, int d4, int d5) {
        return Yatzy1.of(d1, d2, d3, d4, d5).sixes();
    }

    public int score_pair(final int n) {
        final var tallies = dice.toCount();
        for (var at = 5; at >= 0; at--) {
            if (tallies[at] >= 2) {
                return at * 2;
            }
        }
        return 0;
    }

    public int score_pair() {
        final var tallies = dice.toCount();
        for (var at = 5; at >= 0; at--) {
            if (tallies[at] >= 2) {
                return (at + 1) * 2;
            }
        }
        return 0;
    }

    public int two_pair() {
        final var tallies = dice.toCount();
        final var ret = IntStream.range(0, tallies.length)
            .filter(i -> tallies[5 - i] >= 2)
            .map(i -> (6 - i) * 2)
            .toArray();
        if (ret.length >= 2) {
            return Arrays.stream(ret).sum();
        }
        return 0;
    }

    public int four_of_a_kind() {
        final var tallies = dice.toCount();
        return IntStream.range(0, tallies.length)
            .filter(i -> tallies[i] >= 4)
            .map(i -> (i + 1) * 4)
            .findFirst()
            .orElse(0);
    }

    public int three_of_a_kind() {
        final var tallies = dice.toCount();
        return IntStream.range(0, tallies.length)
            .filter(i -> tallies[i] >= 3)
            .map(i -> (i + 1) * 3)
            .findFirst()
            .orElse(0);
    }


    public int smallStraight() {
        final var tallies = dice.toCount();
        if (IntStream.range(0, tallies.length - 1).allMatch(i -> tallies[i] == 1)) {
            return 15;
        }
        return 0;
    }

    public int largeStraight() {
        final var tallies = dice.toCount();
        if (IntStream.range(1, tallies.length).allMatch(i -> tallies[i] == 1)) {
            return 20;
        }
        return 0;
    }

    public int fullHouse() {
        final var tallies = dice.toCount();
        final var _2 = IntStream.range(0, tallies.length)
            .filter(i -> tallies[i] == 2)
            .map(i -> (i + 1) * 2)
            .findFirst();
        final var _3 = IntStream.range(0, tallies.length)
            .filter(i -> tallies[i] == 3)
            .map(i -> (i + 1) * 3)
            .findFirst();
        if (_2.isPresent() && _3.isPresent()) {
            return _2.getAsInt() + _3.getAsInt();
        }
        return 0;
    }
}





package aoc2024;

import aoc2024.tools.Parse;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Day11 {

    private static final BigInteger FACTOR = BigInteger.valueOf(2024);

    record Stone(BigInteger value, long count) {

        public Collection<Stone> blink() {
            if (value.equals(BigInteger.ZERO)) {
                return List.of(new Stone(BigInteger.ONE, count));
            }
            String string = value.toString();
            if (string.length() % 2 == 0) {
                return List.of(new Stone(new BigInteger(string.substring(0, string.length() / 2)), count),
                        new Stone(new BigInteger(string.substring(string.length() / 2)), count));
            }
            return List.of(new Stone(value.multiply(FACTOR), count));
        }
    }

    public static long getPart1(List<String> lines) {
        return algorithm(lines, 25);
    }

    private static long algorithm(List<String> lines, int duration) {
        if (lines.size() != 1) {
            throw new RuntimeException("Expected exactly one line");
        }
        List<Stone> stones = new ArrayList<>();
        for (Long value : Parse.getLongs(lines.getFirst())) {
            stones.add(new Stone(BigInteger.valueOf(value), 1));
        }
        for (int i = 0; i < duration; i++) {
            List<Stone> nextStones = new ArrayList<>();
            for (Stone stone : stones) {
                outer:
                for (Stone newStone : stone.blink()) {
                    for (int k = 0; k < nextStones.size(); k++) {
                        if (nextStones.get(k).value.equals(newStone.value)) {
                            nextStones.set(k, new Stone(newStone.value, nextStones.get(k).count + newStone.count));
                            continue outer;
                        }
                    }
                    nextStones.add(newStone);
                }
            }
            stones = nextStones;
        }
        return stones.stream().mapToLong(Stone::count).sum();
    }


    public static long getPart2(List<String> lines) {
        return algorithm(lines, 75);
    }

}

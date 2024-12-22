package aoc2024;

import aoc2024.tools.Parse;

import java.util.*;

public class Day22 {

    public static long getPart1(List<String> lines) {
        long sum = 0;
        for (String line : lines) {
            long secret = Parse.getLongs(line).getFirst();
            for (int i = 1; i <= 2000; i++) {
                secret = next(secret);
            }
            sum += secret;
        }
        return sum;
    }

    private static long next(long secret) {
        secret = (secret ^ (secret << 6)) & 16777215;
        secret = (secret ^ (secret >> 5)) & 16777215;
        secret = (secret ^ (secret << 11)) & 16777215;
        return secret;
    }

    public static long getPart2(List<String> lines) {
        Map<Pattern, Integer> bananasPerPattern = new HashMap<>();
        for (String line : lines) {
            long secret = Parse.getLongs(line).getFirst();
            final List<Integer> prices = new ArrayList<>();
            prices.add(price(secret));
            Set<Pattern> seenThisMonkey = new HashSet<>();
            for (int i = 1; i <= 2000; i++) {
                secret = next(secret);
                int price = price(secret);
                prices.add(price);
                Pattern pattern = getPattern(prices);
                if (pattern != null && seenThisMonkey.add(pattern)) {
                    bananasPerPattern.compute(pattern, (k, v) -> v == null ? price : v + price);
                }
            }
        }
        return bananasPerPattern.values().stream().max(Integer::compareTo).orElseThrow();
    }

    public static int price(long secret) {
        return (int) (secret % 10);
    }

    public record Pattern(short p1, short p2, short p3, short p4) {

    }

    public static Pattern getPattern(List<Integer> prices) {
        int size = prices.size();
        if (size < 5) {
            return null;
        }

        return new Pattern((short) (prices.get(size - 5) - prices.get(size - 4)),
                (short) (prices.get(size - 4) - prices.get(size - 3)),
                (short) (prices.get(size - 3) - prices.get(size - 2)),
                (short) (prices.get(size - 2) - prices.get(size - 1)));
    }

}

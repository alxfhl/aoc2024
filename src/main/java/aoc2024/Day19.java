package aoc2024;

import java.util.*;

import static java.util.Comparator.comparing;

public class Day19 {

    public static long getPart1(List<String> lines) {
        Set<String> patterns = new HashSet<>(List.of(lines.getFirst().split(", ")));
        long count = 0;
        for (String line : lines) {
            if (combinations(line, patterns) > 0) {
                count++;
            }
        }
        return count;
    }

    public static long getPart2(List<String> lines) {
        Set<String> patterns = new HashSet<>(List.of(lines.getFirst().split(", ")));
        long sum = 0;
        for (String line : lines) {
            sum += combinations(line, patterns);
        }
        return sum;
    }

    private static long combinations(String line, Set<String> patterns) {
        Map<String, Long> map = new HashMap<>();
        map.put(line, 1L);
        long sum = 0;
        while (!map.isEmpty()) {
            String longest = map.keySet().stream().max(comparing(String::length)).stream().findFirst().get();
            long ways = map.remove(longest);
            for (String pattern : patterns) {
                if (longest.equals(pattern)) {
                    sum += ways;
                } else if (longest.startsWith(pattern)) {
                    String rest = longest.substring(pattern.length());
                    map.compute(rest, (k, v) -> v == null ? ways : v + ways);
                }
            }
        }
        return sum;
    }


}

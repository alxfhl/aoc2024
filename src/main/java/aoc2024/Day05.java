package aoc2024;

import aoc2024.tools.Parse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Day05 {

    record Rule(int first, int second) {

    }


    public static long getPart1(List<String> lines) {
        List<Rule> rules = new ArrayList<>();
        long sum = 0;
        for (String line : lines) {
            if (line.indexOf('|') > 0) {
                List<Integer> integers = Parse.getIntegers(line);
                rules.add(new Rule(integers.get(0), integers.get(1)));
            }
            if (line.indexOf(',') > 0) {
                List<Integer> pages = Parse.getIntegers(line);
                if (new HashSet<>(pages).size() < pages.size()) {
                    throw new AssertionError("line with duplicate pages: " + line);
                }
                if (inOrder(pages, rules)) {
                    sum += pages.get(pages.size() / 2);
                }

            }
        }
        return sum;
    }

    private static void sort(List<Integer> pages, List<Rule> rules) {
        pages.sort((page1, page2) -> {
            if (Objects.equals(page1, page2)) {
                return 0;
            }
            for (Rule rule : rules) {
                if (rule.first == page1 && rule.second == page2) {
                    return -1;
                }
                if (rule.first == page2 && rule.second == page1) {
                    return 1;
                }
            }
            return 0;
        });
    }

    private static boolean inOrder(List<Integer> pages, List<Rule> rules) {
        for (int i = 0; i < pages.size() - 1; i++) {
            Integer page1 = pages.get(i);
            Integer page2 = pages.get(i + 1);
            if (rules.stream().anyMatch(r -> r.first == page1 && r.second == page2)) {
                continue;
            }
            if (rules.stream().anyMatch(r -> r.first == page2 && r.second == page1)) {
                return false;
            }
            throw new AssertionError("missing rule for pair " + page1 + "," + page2);
        }
        return true;
    }

    public static long getPart2(List<String> lines) {
        List<Rule> rules = new ArrayList<>();
        long sum = 0;
        for (String line : lines) {
            if (line.indexOf('|') > 0) {
                List<Integer> integers = Parse.getIntegers(line);
                rules.add(new Rule(integers.get(0), integers.get(1)));
            }
            if (line.indexOf(',') > 0) {
                List<Integer> pages = Parse.getIntegers(line);
                if (new HashSet<>(pages).size() < pages.size()) {
                    throw new AssertionError("line with duplicate pages: " + line);
                }
                if (!inOrder(pages, rules)) {
                    sort(pages, rules);
                    sum += pages.get(pages.size() / 2);
                }

            }
        }
        return sum;
    }

}

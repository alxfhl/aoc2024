package aoc2024;

import aoc2024.tools.Parse;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {

    public static long getPart1(List<String> lines) {
        Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
        long sum = 0;
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                List<Long> longs = Parse.getLongs(matcher.group());
                sum += longs.get(0) * longs.get(1);
            }
        }
        return sum;
    }

    public static long getPart2(List<String> lines) {
        Pattern pattern = Pattern.compile("do\\(\\)|don't\\(\\)|mul\\(\\d{1,3},\\d{1,3}\\)");
        boolean enabled = true;
        long sum = 0;
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String match = matcher.group();
                if (match.equals("do()")) {
                    enabled = true;
                } else if (match.equals("don't()")) {
                    enabled = false;
                } else if (enabled) {
                    List<Long> longs = Parse.getLongs(match);
                    sum += longs.get(0) * longs.get(1);
                }
            }
        }
        return sum;
    }

}

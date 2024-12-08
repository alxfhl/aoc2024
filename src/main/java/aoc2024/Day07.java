package aoc2024;

import aoc2024.tools.Parse;

import java.util.ArrayList;
import java.util.List;

public class Day07 {

    public static long getPart1(List<String> lines) {
        long sum = 0;
        for (String line : lines) {
            List<Long> values = Parse.getLongs(line);
            long result = values.removeFirst();
            List<List<Long>> stack = new ArrayList<>();
            stack.add(values);
            while (!stack.isEmpty()) {
                List<Long> numbers = stack.removeLast();
                if (numbers.size() == 1) {
                    if (numbers.getFirst() == result) {
                        sum += numbers.getFirst();
                        break;
                    }
                    continue;
                }

                List<Long> newNumbers = new ArrayList<>();
                newNumbers.add(numbers.getFirst() * numbers.get(1));
                newNumbers.addAll(numbers.subList(2, numbers.size()));
                stack.add(newNumbers);

                newNumbers = new ArrayList<>();
                newNumbers.add(numbers.getFirst() + numbers.get(1));
                newNumbers.addAll(numbers.subList(2, numbers.size()));
                stack.add(newNumbers);
            }
        }
        return sum;
    }


    public static long getPart2(List<String> lines) {
        long sum = 0;
        for (String line : lines) {
            List<Long> values = Parse.getLongs(line);
            long result = values.removeFirst();
            List<List<Long>> stack = new ArrayList<>();
            stack.add(values);
            while (!stack.isEmpty()) {
                List<Long> numbers = stack.removeLast();
                if (numbers.size() == 1) {
                    if (numbers.getFirst() == result) {
                        sum += numbers.getFirst();
                        break;
                    }
                    continue;
                }

                List<Long> newNumbers = new ArrayList<>();
                newNumbers.add(numbers.getFirst() * numbers.get(1));
                newNumbers.addAll(numbers.subList(2, numbers.size()));
                stack.add(newNumbers);

                newNumbers = new ArrayList<>();
                newNumbers.add(numbers.getFirst() + numbers.get(1));
                newNumbers.addAll(numbers.subList(2, numbers.size()));
                stack.add(newNumbers);

                newNumbers = new ArrayList<>();
                newNumbers.add(Long.parseLong(numbers.getFirst() + Long.toString(numbers.get(1))));
                newNumbers.addAll(numbers.subList(2, numbers.size()));
                stack.add(newNumbers);
            }
        }
        return sum;
    }

}

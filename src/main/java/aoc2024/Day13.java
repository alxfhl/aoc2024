package aoc2024;

import aoc2024.tools.Coord2D;
import aoc2024.tools.Parse;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

public class Day13 {

    public static long getPart1(List<String> lines) {
        ArrayList<String> remainingLines = lines.stream()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .collect(toCollection(ArrayList::new));
        long sum = 0;
        while (!remainingLines.isEmpty()) {
            List<Long> longs = Parse.getLongs(remainingLines.removeFirst());
            Coord2D buttonA = new Coord2D(longs.getFirst(), longs.get(1));
            longs = Parse.getLongs(remainingLines.removeFirst());
            Coord2D buttonB = new Coord2D(longs.getFirst(), longs.get(1));
            longs = Parse.getLongs(remainingLines.removeFirst());
            Coord2D price = new Coord2D(longs.getFirst(), longs.get(1));
            long cheapest = Long.MAX_VALUE;
            for (long a = 0; a <= 100; a++) {
                if (a * buttonA.x() > price.x() || a * buttonA.y() > price.y()) {
                    break;
                }
                long b = (price.x() - buttonA.x() * a) / buttonB.x();
                if (a * buttonA.x() + b * buttonB.x() == price.x() && a * buttonA.y() + b * buttonB.y() == price.y()) {
                    cheapest = Math.min(cheapest, a * 3 + b);
                }
            }
            if (cheapest < Long.MAX_VALUE) {
                sum += cheapest;
            }
        }
        return sum;
    }


    public static long getPart2(List<String> lines) {
        ArrayList<String> remainingLines = lines.stream()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .collect(toCollection(ArrayList::new));
        long sum = 0;
        while (!remainingLines.isEmpty()) {
            List<Long> longs = Parse.getLongs(remainingLines.removeFirst());
            Coord2D buttonA = new Coord2D(longs.getFirst(), longs.get(1));
            longs = Parse.getLongs(remainingLines.removeFirst());
            Coord2D buttonB = new Coord2D(longs.getFirst(), longs.get(1));
            longs = Parse.getLongs(remainingLines.removeFirst());
            Coord2D price = new Coord2D(longs.getFirst() + 10000000000000L, longs.get(1) + 10000000000000L);

            long a = (price.x() * buttonB.y() - price.y() * buttonB.x()) //
                    / (buttonA.x() * buttonB.y() - buttonA.y() * buttonB.x());
            long b = (price.x() - buttonA.x() * a) / buttonB.x();
            if (a * buttonA.x() + b * buttonB.x() == price.x() && a * buttonA.y() + b * buttonB.y() == price.y()) {
                sum += a * 3 + b;
            }
        }
        return sum;
    }

}

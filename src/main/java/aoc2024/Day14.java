package aoc2024;

import aoc2024.tools.CharMatrix;
import aoc2024.tools.Coord2D;
import aoc2024.tools.Direction;
import aoc2024.tools.Parse;

import java.util.*;

public class Day14 {

    public static long getPart1(List<String> lines, long width, long height, long steps) {
        List<Coord2D> positions = new ArrayList<>();
        for (String line : lines) {
            List<Long> longs = Parse.getLongs(line);
            Coord2D pos = new Coord2D(longs.get(0), longs.get(1));
            Coord2D v = new Coord2D(longs.get(2), longs.get(3));
            long endX = (pos.x() + v.x() * steps) % width;
            long endY = (pos.y() + v.y() * steps) % height;
            if (endX < 0) {
                endX += width;
            }
            if (endY < 0) {
                endY += height;
            }
            Coord2D end = new Coord2D(endX, endY);
            positions.add(end);
        }
        return count(positions, 0, 0, width / 2, height / 2)
                * count(positions, width / 2 + 1, height / 2 + 1, width, height)
                * count(positions, 0, height / 2 + 1, width / 2, height)
                * count(positions, width / 2 + 1, 0, width, height / 2);
    }

    private static long count(List<Coord2D> positions, long minX, long minY, long maxX, long maxY) {
        return positions.stream()
                .filter(pos -> pos.x() >= minX && pos.x() < maxX && pos.y() >= minY && pos.y() < maxY).count();
    }


    public static long getPart2(List<String> lines, long width, long height) {
        for (int steps = 0; steps < 100000; steps++) {
            Set<Coord2D> positions = new HashSet<>();
            CharMatrix matrix = new CharMatrix((int) width, (int) height, '.');
            for (String line : lines) {
                List<Long> longs = Parse.getLongs(line);
                Coord2D pos = new Coord2D(longs.get(0), longs.get(1));
                Coord2D v = new Coord2D(longs.get(2), longs.get(3));
                long endX = (pos.x() + v.x() * steps) % width;
                long endY = (pos.y() + v.y() * steps) % height;
                if (endX < 0) {
                    endX += width;
                }
                if (endY < 0) {
                    endY += height;
                }
                positions.add(new Coord2D(endX, endY));
                matrix.set((int) endX, (int) endY, '#');
            }
            long sum = 0;
            for (Coord2D position : positions) {
                if (Arrays.stream(Direction.values()).anyMatch(dir -> positions.contains(position.go(dir)))) {
                    sum++;
                }
            }
            if (sum > 300) {
                System.out.println("after steps: " + steps);
                System.out.println(matrix);
                return steps;
            }
        }
        return 0;
    }

}

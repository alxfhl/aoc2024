package aoc2024;

import aoc2024.tools.CharMatrix;
import aoc2024.tools.Coord2D;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day08 {

    public static long getPart1(List<String> lines) {
        CharMatrix matrix = CharMatrix.valueOf(lines);
        CharMatrix antinodes = new CharMatrix(matrix.getWidth(), matrix.getHeight(), ' ');
        Set<Character> done = new HashSet<>();
        for (int x = 0; x < matrix.getWidth(); x++) {
            for (int y = 0; y < matrix.getHeight(); y++) {
                char frequency = matrix.get(x, y);
                if (!done.add(frequency) || (!Character.isDigit(frequency) && !Character.isAlphabetic(frequency))) {
                    continue;
                }
                List<Coord2D> coords = matrix.findAll(frequency);
                for (int first = 0; first < coords.size(); first++) {
                    for (int second = first + 1; second < coords.size(); second++) {
                        Coord2D c1 = coords.get(first);
                        Coord2D c2 = coords.get(second);
                        Coord2D diff = c2.minus(c1);
                        Coord2D antinode = c1.minus(diff);
                        if (antinodes.isInside(antinode)) {
                            antinodes.set(antinode, '#');
                        }
                        antinode = c2.plus(diff);
                        if (antinodes.isInside(antinode)) {
                            antinodes.set(antinode, '#');
                        }
                    }
                }
            }
        }
        return antinodes.count('#');
    }


    public static long getPart2(List<String> lines) {
        CharMatrix matrix = CharMatrix.valueOf(lines);
        CharMatrix antinodes = new CharMatrix(matrix.getWidth(), matrix.getHeight(), ' ');
        Set<Character> done = new HashSet<>();
        for (int x = 0; x < matrix.getWidth(); x++) {
            for (int y = 0; y < matrix.getHeight(); y++) {
                char frequency = matrix.get(x, y);
                if (!done.add(frequency) || (!Character.isDigit(frequency) && !Character.isAlphabetic(frequency))) {
                    continue;
                }
                List<Coord2D> coords = matrix.findAll(frequency);
                if (coords.size() < 2) {
                    continue;
                }
                for (int first = 0; first < coords.size(); first++) {
                    for (int second = first + 1; second < coords.size(); second++) {
                        Coord2D c1 = coords.get(first);
                        Coord2D c2 = coords.get(second);
                        Coord2D pos = c1;
                        Coord2D diff = c1.minus(c2);
                        while (antinodes.isInside(pos)) {
                            antinodes.set(pos, '#');
                            pos = pos.plus(diff);
                        }
                        pos = c2;
                        diff = c2.minus(c1);
                        while (antinodes.isInside(pos)) {
                            antinodes.set(pos, '#');
                            pos = pos.plus(diff);
                        }
                    }
                }
            }
        }
        return antinodes.count('#');
    }

}

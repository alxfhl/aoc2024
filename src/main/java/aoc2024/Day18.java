package aoc2024;

import aoc2024.tools.CharMatrix;
import aoc2024.tools.Coord;
import aoc2024.tools.Parse;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class Day18 {

    public static long getPart1(List<String> lines, int blocks, int width, int height) {
        CharMatrix matrix = new CharMatrix(width, height, '.');
        for (int i = 0; i < blocks; i++) {
            List<Integer> integers = Parse.getIntegers(lines.get(i));
            matrix.set(integers.getFirst(), integers.getLast(), '#');
        }
        return shortestPath(matrix, new Coord(0, 0), new Coord(width - 1, height - 1));
    }

    private static Integer shortestPath(CharMatrix matrix, Coord start, Coord goal) {
        Map<Coord, Integer> shortestPaths = new HashMap<>();
        var todo = new LinkedHashSet<Coord>();
        todo.add(start);
        shortestPaths.put(start, 0);
        while (!todo.isEmpty()) {
            Coord pos = todo.removeFirst();
            int length = shortestPaths.get(pos) + 1;
            for (Coord neighbor : pos.getNeighbors()) {
                if (matrix.isInside(neighbor) && matrix.get(neighbor) != '#') {
                    if (!shortestPaths.containsKey(neighbor) || shortestPaths.get(neighbor) > length) {
                        shortestPaths.put(neighbor, length);
                        todo.add(neighbor);
                    }
                }
            }
        }
        return shortestPaths.get(goal);
    }


    public static String getPart2(List<String> lines, int blocks, int width, int height) {
        CharMatrix matrix = new CharMatrix(width, height, '.');
        for (int i = 0; i < blocks; i++) {
            List<Integer> integers = Parse.getIntegers(lines.get(i));
            matrix.set(integers.getFirst(), integers.getLast(), '#');
        }
        for (int i = blocks; i < lines.size(); i++) {
            List<Integer> integers = Parse.getIntegers(lines.get(i));
            matrix.set(integers.getFirst(), integers.getLast(), '#');
            if (shortestPath(matrix, new Coord(0, 0), new Coord(width - 1, height - 1)) == null) {
                return integers.getFirst() + "," + integers.getLast();
            }
        }
        return null;
    }

}

package aoc2024;

import aoc2024.tools.CharMatrix;
import aoc2024.tools.Coord2D;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.*;

import static java.util.Comparator.comparing;

public class Day20 {

    @Data
    @ToString(exclude = {"neighbors"})
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    static class Location {
        @EqualsAndHashCode.Include
        Coord2D pos;
        int toStart;
        Integer toEnd = null;
        List<Location> neighbors = new ArrayList<>(4);

        public Location(Coord2D pos, int toStart) {
            this.pos = pos;
            this.toStart = toStart;
        }

        public void addNeighbor(Location location) {
            if (!neighbors.contains(location)) {
                neighbors.add(location);
            }
        }
    }

    public static long getPart1(List<String> lines, int minSaving) {
        return getPart2(lines, 2, minSaving);
    }

    public static long getPart2(List<String> lines, int cheatTimeLimit, int minSaving) {
        CharMatrix matrix = CharMatrix.valueOf(lines);
        Coord2D start = matrix.indexOf('S');
        Coord2D end = matrix.indexOf('E');
        matrix.set(start, '.');
        matrix.set(end, '.');
        Map<Coord2D, Location> solution = solve(matrix, start, end);
        return countCheats(solution, cheatTimeLimit, minSaving);
    }

    private static Map<Coord2D, Location> solve(CharMatrix matrix, Coord2D start, Coord2D end) {
        var todo = new PriorityQueue<>(comparing(Location::getToStart));
        Map<Coord2D, Location> solution = new HashMap<>();
        Location startLocation = new Location(start, 0);
        solution.put(start, startLocation);
        todo.add(startLocation);
        while (!todo.isEmpty()) {
            Location location = todo.poll();
            if (location.getPos().equals(end)) {
                continue;
            }
            int toStart = location.getToStart() + 1;
            for (Coord2D neighbor : location.getPos().getNeighbors()) {
                if (matrix.get(neighbor) != '.') {
                    continue;
                }
                Location neighborSolution = solution.get(neighbor);
                if (neighborSolution == null) {
                    neighborSolution = new Location(neighbor, toStart);
                    solution.put(neighbor, neighborSolution);
                    todo.add(neighborSolution);
                } else if (neighborSolution.getToStart() > toStart) {
                    neighborSolution.setToStart(toStart);
                    todo.add(neighborSolution);
                }
                neighborSolution.addNeighbor(location);
                location.addNeighbor(neighborSolution);
            }
        }
        // fill out toEnd
        todo = new PriorityQueue<>(comparing(Location::getToEnd));
        solution.get(end).setToEnd(0);
        todo.add(solution.get(end));
        while (!todo.isEmpty()) {
            Location location = todo.poll();
            int toEnd = location.getToEnd() + 1;
            for (Location neighbor : location.neighbors) {
                if (neighbor.getToEnd() == null || neighbor.getToEnd() > toEnd) {
                    neighbor.setToEnd(toEnd);
                    todo.add(neighbor);
                }
            }
        }
        return solution;
    }

    private static long countCheats(Map<Coord2D, Location> solution, int cheatTimeLimit, int minSaving) {
        long count = 0;
        for (Location from : solution.values()) {
            int fromX = (int) from.getPos().x();
            int fromY = (int) from.getPos().y();
            for (int y = fromY - cheatTimeLimit; y <= fromY + cheatTimeLimit; y++) {
                int distanceY = Math.abs(fromY - y);
                int remaining = cheatTimeLimit - distanceY;
                for (int x = fromX - remaining; x <= fromX + remaining; x++) {
                    Location to = solution.get(new Coord2D(x, y));
                    if (to == null || to.getToStart() <= from.getToStart()) {
                        continue;
                    }
                    int saving = from.getToEnd() - to.getToEnd() - Math.abs(fromX - x) - distanceY;
                    if (saving >= minSaving) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

}

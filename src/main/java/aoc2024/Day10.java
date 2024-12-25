package aoc2024;

import aoc2024.tools.CharMatrix;
import aoc2024.tools.Coord;
import aoc2024.tools.Direction;

import java.util.*;

public class Day10 {

    public static long getPart1(List<String> lines) {
        CharMatrix matrix = CharMatrix.valueOf(lines);
        List<Coord> candidates = matrix.findAll('0');
        Map<Coord, Integer> trailheads = new HashMap<>();
        for (Coord candidate : candidates) {
            Set<Coord> summits = new HashSet<>();

            Set<Coord> positions = new HashSet<>();
            positions.add(candidate);
            Set<Coord> nextPositions = new HashSet<>();
            while (!positions.isEmpty()) {
                for (Coord pos : positions) {
                    char height = matrix.get(pos);
                    if (height == '9') {
                        summits.add(pos);
                        continue;
                    }
                    for (Direction dir : Direction.values()) {
                        Coord neighbor = pos.go(dir);
                        if (matrix.isInside(neighbor) && matrix.get(neighbor) == (height + 1)) {
                            nextPositions.add(neighbor);
                        }
                    }
                }
                positions = nextPositions;
                nextPositions = new HashSet<>();
            }

            if (!summits.isEmpty()) {
                trailheads.put(candidate, summits.size());
            }
        }
        return trailheads.values().stream().mapToLong(i -> i).sum();
    }


    public static long getPart2(List<String> lines) {
        CharMatrix matrix = CharMatrix.valueOf(lines);
        List<Coord> candidates = matrix.findAll('0');
        Map<Coord, Long> trailheads = new HashMap<>();
        for (Coord candidate : candidates) {
            Map<Coord, Long> summits = new HashMap<>();

            Map<Coord, Long> positions = new HashMap<>();
            positions.put(candidate, 1L);
            Map<Coord, Long> nextPositions = new HashMap<>();
            while (!positions.isEmpty()) {
                for (var entry : positions.entrySet()) {
                    Coord pos = entry.getKey();
                    long trails = entry.getValue();
                    char height = matrix.get(pos);

                    if (height == '9') {
                        summits.compute(pos, (p, value) -> value == null ? trails : value + trails);
                        continue;
                    }

                    for (Direction dir : Direction.values()) {
                        Coord neighbor = pos.go(dir);
                        if (matrix.isInside(neighbor) && matrix.get(neighbor) == (height + 1)) {
                            nextPositions.compute(neighbor, (p, value) -> value == null
                                    ? trails
                                    : value + trails);
                        }
                    }
                }
                positions = nextPositions;
                nextPositions = new HashMap<>();
            }

            if (!summits.isEmpty()) {
                trailheads.put(candidate, summits.values().stream().mapToLong(i -> i).sum());
            }
        }
        return trailheads.values().stream().mapToLong(i -> i).sum();
    }

}

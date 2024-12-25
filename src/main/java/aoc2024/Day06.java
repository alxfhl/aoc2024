package aoc2024;

import aoc2024.tools.CharMatrix;
import aoc2024.tools.Coord;
import aoc2024.tools.Direction;

import java.util.EnumMap;
import java.util.List;

public class Day06 {

    public static long getPart1(List<String> lines) {
        CharMatrix matrix = CharMatrix.valueOf(lines);
        Direction dir = null;
        Coord pos = null;
        for (Direction direction : Direction.values()) {
            pos = matrix.indexOf(direction.symbol());
            if (pos != null) {
                dir = direction;
                break;
            }
        }
        if (pos == null) {
            throw new AssertionError("starting position not found");
        }
        while (true) {
            matrix.set(pos, 'X');
            Coord next = pos.go(dir);
            if (matrix.isOutside(next)) {
                break;
            }
            if (matrix.get(next) == '#') {
                dir = dir.turnRight();
                continue;
            }
            pos = next;
        }
        return matrix.count('X');
    }


    public static long getPart2(List<String> lines) {
        CharMatrix matrix = CharMatrix.valueOf(lines);
        Direction startDir = null;
        Coord start = null;
        for (Direction direction : Direction.values()) {
            start = matrix.indexOf(direction.symbol());
            if (start != null) {
                startDir = direction;
                break;
            }
        }
        if (start == null) {
            throw new AssertionError("starting position not found");
        }
        long count = 0;
        for (int x = 0; x < matrix.getWidth(); x++) {
            for (int y = 0; y < matrix.getHeight(); y++) {
                if (x == start.x() && y == start.y() || matrix.get(x, y) == '#') {
                    continue;
                }
                matrix.set(x, y, 'O');
                EnumMap<Direction, CharMatrix> history = new EnumMap<>(Direction.class);
                for (Direction direction : Direction.values()) {
                    history.put(direction, new CharMatrix(matrix.getWidth(), matrix.getHeight(), ' '));
                }
                Direction dir = startDir;
                Coord pos = start;
                while (true) {
                    if (history.get(dir).get(pos) == 'X') {
                        // loop detected
                        count++;
                        break;
                    }
                    history.get(dir).set(pos, 'X');
                    Coord next = pos.go(dir);
                    if (matrix.isOutside(next)) {
                        break;
                    }
                    char ahead = matrix.get(next);
                    if (ahead == '#' || ahead == 'O') {
                        dir = dir.turnRight();
                        continue;
                    }
                    pos = next;
                }
                matrix = CharMatrix.valueOf(lines);
            }
        }
        return count;
    }

}

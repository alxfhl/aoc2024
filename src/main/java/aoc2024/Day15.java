package aoc2024;

import aoc2024.tools.CharMatrix;
import aoc2024.tools.Coord;
import aoc2024.tools.Direction;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static java.util.Comparator.comparing;


public class Day15 {

    public static long getPart1(List<String> lines) {
        CharMatrix matrix = getMatrix(lines);
        List<Direction> commands = getCommands(lines);
        Coord pos = matrix.indexOf('@');
        matrix.set(pos, '.');
        for (Direction command : commands) {
            int move = 0;
            Coord neighbor = pos;
            while (true) {
                neighbor = neighbor.go(command);
                char ch = matrix.get(neighbor);
                if (ch == '.') {
                    pos = pos.go(command);
                    break;
                }
                if (ch == '#') {
                    move = 0;
                    break;
                }
                move++;
            }
            if (move > 0) {
                matrix.set(neighbor, 'O');
                matrix.set(pos, '.');
            }
        }
        return matrix.findAll('O').stream().mapToLong(coord2D -> coord2D.y() * 100 + coord2D.x()).sum();
    }

    public static long getPart2(List<String> lines) {
        CharMatrix matrix = widenMatrix(getMatrix(lines));
        List<Direction> commands = getCommands(lines);
        for (Direction command : commands) {
            List<Coord> moving = new ArrayList<>();
            boolean canMove = true;
            var unclear = new LinkedHashSet<Coord>();
            unclear.add(matrix.indexOf('@'));
            while (!unclear.isEmpty()) {
                Coord candidate = unclear.removeFirst();
                Coord target = candidate.go(command);
                char neighbor = matrix.get(target);
                if (neighbor == '.') {
                    moving.add(candidate);
                    continue;
                }
                if (neighbor == '#') {
                    canMove = false;
                    break;
                }
                if (neighbor == '[') {
                    unclear.add(target);
                    unclear.add(new Coord(target.x() + 1, target.y()));
                    moving.add(candidate);
                }
                if (neighbor == ']') {
                    unclear.add(new Coord(target.x() - 1, target.y()));
                    unclear.add(target);
                    moving.add(candidate);
                }
                moving.forEach(unclear::remove);
            }
            if (canMove) {
                moving.sort(switch (command) {
                    case LEFT -> comparing(Coord::x);
                    case RIGHT -> comparing(Coord::x).reversed();
                    case UP -> comparing(Coord::y);
                    case DOWN -> comparing(Coord::y).reversed();
                });
                for (Coord block : moving) {
                    matrix.set(block.go(command), matrix.get(block));
                    matrix.set(block, '.');
                }
            }
        }
        return matrix.findAll('[').stream().mapToLong(coord2D -> coord2D.y() * 100 + coord2D.x()).sum();
    }

    private static CharMatrix widenMatrix(CharMatrix matrix) {
        CharMatrix matrix2 = new CharMatrix(matrix.getWidth() * 2, matrix.getHeight(), ' ');
        for (int x = 0; x < matrix.getWidth(); x++) {
            for (int y = 0; y < matrix.getHeight(); y++) {
                char ch = matrix.get(x, y);
                if (ch == 'O') {
                    matrix2.set(x * 2, y, '[');
                    matrix2.set(x * 2 + 1, y, ']');
                } else if (ch == '@') {
                    matrix2.set(x * 2, y, '@');
                    matrix2.set(x * 2 + 1, y, '.');
                } else {
                    matrix2.set(x * 2, y, ch);
                    matrix2.set(x * 2 + 1, y, ch);
                }
            }
        }
        return matrix2;
    }

    private static CharMatrix getMatrix(List<String> lines) {
        List<String> rows = new ArrayList<>();
        for (String line : lines) {
            if (line.isBlank()) {
                break;
            }
            rows.add(line);
        }
        return CharMatrix.valueOf(rows);
    }

    private static List<Direction> getCommands(List<String> lines) {
        List<Direction> commands = new ArrayList<>();
        for (String line : lines) {
            if (line.trim().matches("^[<>^v]+$")) {
                for (char ch : line.trim().toCharArray()) {
                    commands.add(Direction.valueOf(ch));
                }
            }
        }
        return commands;
    }


}

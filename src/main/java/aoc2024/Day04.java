package aoc2024;

import aoc2024.tools.CharMatrix;

import java.util.List;

public class Day04 {

    public static long getPart1(List<String> lines) {
        CharMatrix matrix = CharMatrix.valueOf(lines);
        long count = 0;
        for (int x = 0; x < matrix.getWidth(); x++) {
            for (int y = 0; y < matrix.getHeight(); y++) {
                if (contains(matrix, "XMAS", x, y, 1, 0)) {
                    count++;
                }
                if (contains(matrix, "XMAS", x, y, 1, 1)) {
                    count++;
                }
                if (contains(matrix, "XMAS", x, y, 0, 1)) {
                    count++;
                }
                if (contains(matrix, "XMAS", x, y, -1, 1)) {
                    count++;
                }
                if (contains(matrix, "XMAS", x, y, -1, 0)) {
                    count++;
                }
                if (contains(matrix, "XMAS", x, y, -1, -1)) {
                    count++;
                }
                if (contains(matrix, "XMAS", x, y, 0, -1)) {
                    count++;
                }
                if (contains(matrix, "XMAS", x, y, 1, -1)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean contains(CharMatrix matrix, String xmas, int x, int y, int dx, int dy) {
        for (int i = 0; i < xmas.length(); i++) {
            int newX = x + i * dx;
            int newY = y + i * dy;
            if (matrix.isOutside(newX, newY)) {
                return false;
            }
            if (matrix.get(newX, newY) != xmas.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static long getPart2(List<String> lines) {
        CharMatrix matrix = CharMatrix.valueOf(lines);
        long count = 0;
        for (int x = 1; x < matrix.getWidth() - 1; x++) {
            for (int y = 1; y < matrix.getHeight() - 1; y++) {
                if (matrix.get(x, y) != 'A') {
                    continue;
                }
                if ((contains(matrix, "MAS", x - 1, y - 1, 1, 1)
                        || contains(matrix, "SAM", x - 1, y - 1, 1, 1))
                        && (contains(matrix, "MAS", x - 1, y + 1, 1, -1)
                        || contains(matrix, "SAM", x - 1, y + 1, 1, -1))) {
                    count++;
                }
            }
        }
        return count;
    }

}

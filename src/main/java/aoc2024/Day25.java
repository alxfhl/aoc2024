package aoc2024;

import aoc2024.tools.CharMatrix;
import aoc2024.tools.Parse;

import java.util.ArrayList;
import java.util.List;

public class Day25 {

    record Key(List<Integer> pins) {

        public boolean fitsInto(Lock lock) {
            for (int i = 0; i < pins.size(); i++) {
                if (pins.get(i) + lock.pins().get(i) > 5) {
                    return false;
                }
            }
            return true;
        }
    }

    record Lock(List<Integer> pins) {

    }

    public static long getPart1(List<String> lines) {
        List<Key> keys = new ArrayList<>();
        List<Lock> locks = new ArrayList<>();
        for (List<String> block : Parse.splitIntoBlocks(lines)) {
            CharMatrix matrix = CharMatrix.valueOf(block);
            if (isKey(matrix)) {
                keys.add(parseKey(matrix));
            } else if (isLock(matrix)) {
                locks.add(parseLock(matrix));
            } else {
                throw new RuntimeException("Unparsed block: " + block);
            }
        }
        long count = 0;
        for (Key key : keys) {
            for (Lock lock : locks) {
                if (key.fitsInto(lock)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static Lock parseLock(CharMatrix matrix) {
        List<Integer> pins = new ArrayList<>();
        for (int x = 0; x < matrix.getWidth(); x++) {
            for (int y = 1; y < matrix.getHeight(); y++) {
                if (matrix.get(x, y) == '.') {
                    pins.add(y - 1);
                    break;
                }
            }
        }
        return new Lock(pins);
    }

    private static Key parseKey(CharMatrix matrix) {
        List<Integer> pins = new ArrayList<>();
        for (int x = 0; x < matrix.getWidth(); x++) {
            for (int y = matrix.getHeight() - 2; y >= 0; y--) {
                if (matrix.get(x, y) == '.') {
                    pins.add(matrix.getHeight() - y - 2);
                    break;
                }
            }
        }
        return new Key(pins);
    }

    private static boolean isKey(CharMatrix matrix) {
        return hasFullRowOf(matrix, 0, '.') && hasFullRowOf(matrix, matrix.getHeight() - 1, '#');
    }

    private static boolean isLock(CharMatrix matrix) {
        return hasFullRowOf(matrix, 0, '#') && hasFullRowOf(matrix, matrix.getHeight() - 1, '.');
    }


    private static boolean hasFullRowOf(CharMatrix matrix, int y, char c) {
        for (char ch : matrix.getRow(y)) {
            if (ch != c) {
                return false;
            }
        }
        return true;
    }
}

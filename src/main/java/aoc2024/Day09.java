package aoc2024;

import java.util.ArrayList;
import java.util.List;

public class Day09 {

    public static long getPart1(List<String> lines) {
        if (lines.size() != 1) {
            throw new AssertionError("only one line expected");
        }
        List<Integer> blocks = parseBlocks(lines.getFirst());
        defrag1(blocks);
        return getChecksum(blocks);
    }

    public static long getPart2(List<String> lines) {
        if (lines.size() != 1) {
            throw new AssertionError("only one line expected");
        }
        List<Integer> blocks = parseBlocks(lines.getFirst());
        defrag2(blocks);
        return getChecksum(blocks);
    }

    private static void defrag1(List<Integer> blocks) {
        int last = blocks.size() - 1;
        for (int i = 0; i < last; i++) {
            if (blocks.get(i) == null) {
                blocks.set(i, blocks.get(last));
                blocks.set(last, null);
                while (blocks.get(last) == null) {
                    last--;
                }
            }
        }
    }

    private static void defrag2(List<Integer> blocks) {
        int fileId = blocks.stream().mapToInt(i -> i == null ? 0 : i).max().getAsInt();
        while (fileId >= 0) {
            int start = 0;
            int end = 0;
            for (int i = 0; i < blocks.size(); i++) {
                if (blocks.get(i) != null && blocks.get(i) == fileId) {
                    if (start == 0) {
                        start = i;
                    }
                    end = i;
                }
            }
            int emptyStart = 0;
            int emptyEnd = 0;
            for (int i = 0; i < blocks.size(); i++) {
                if (i > start) {
                    break;
                }
                if (blocks.get(i) == null) {
                    if (emptyStart == 0) {
                        emptyStart = i;
                    }
                    emptyEnd = i;
                    if (emptyEnd - emptyStart == end - start) {
                        for (int b = 0; b <= end - start; b++) {
                            blocks.set(emptyStart + b, fileId);
                            blocks.set(start + b, null);
                        }
                        break;
                    }
                } else {
                    emptyStart = 0;
                }
            }
            fileId--;
        }
    }

    private static long getChecksum(List<Integer> blocks) {
        long sum = 0;
        for (int i = 0; i < blocks.size(); i++) {
            Integer fileId = blocks.get(i);
            if (fileId != null) {
                sum += (long) fileId * (long) i;
            }
        }
        return sum;
    }

    private static List<Integer> parseBlocks(String line) {
        List<Integer> blocks = new ArrayList<>();
        boolean fileNext = true;
        int fileId = 0;
        for (char ch : line.toCharArray()) {
            int amount = ch - '0';
            if (fileNext) {
                for (int i = 0; i < amount; i++) {
                    blocks.add(fileId);
                }
                fileId++;
            } else {
                for (int i = 0; i < amount; i++) {
                    blocks.add(null);
                }
            }
            fileNext = !fileNext;
        }
        return blocks;
    }

}
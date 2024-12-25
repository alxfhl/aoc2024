package aoc2024;

import aoc2024.tools.CharMatrix;
import aoc2024.tools.Coord;
import aoc2024.tools.Parse;

import java.util.*;

public class Day21 {

    final static char ACTIVATE = 'A';
    final static CharMatrix numPad = CharMatrix.valueOf(List.of("789", "456", "123", " 0A"));
    final static CharMatrix arrowPad = CharMatrix.valueOf(List.of(" ^A", "<v>"));

    static class Robot {
        final CharMatrix pad;
        Coord position;
        Coord forbidden;
        Robot nextRobot;
        Map<String, Long> cache = new HashMap<>();

        Robot(CharMatrix pad) {
            this.pad = pad;
            this.position = pad.indexOf(ACTIVATE);
            this.forbidden = pad.indexOf(' ');
        }

        public Set<String> getSequences(char ch) {
            Coord target = pad.indexOf(ch);
            if (target.equals(position)) {
                return Set.of("A");
            }
            try {
                int deltaY = (int) Math.abs(target.y() - position.y());
                int deltaX = (int) Math.abs(target.x() - position.x());
                if (deltaX == 0) {
                    return Set.of((target.y() < position.y() ? "^" : "v").repeat(deltaY)
                            + "A");
                }
                if (deltaY == 0) {
                    return Set.of((target.x() < position.x() ? "<" : ">").repeat(deltaX)
                            + "A");
                }
                // adjust x first
                String xFirst = forbidden.equals(new Coord(target.x(), position.y())) ? null :
                        (target.x() < position.x() ? "<" : ">").repeat(deltaX)
                                + (target.y() < position.y() ? "^" : "v").repeat(deltaY) + "A";
                // adjust y first
                String yFirst = forbidden.equals(new Coord(position.x(), target.y())) ? null :
                        (target.y() < position.y() ? "^" : "v").repeat(deltaY)
                                + (target.x() < position.x() ? "<" : ">").repeat(deltaX) + "A";
                if (xFirst == null) {
                    return Set.of(Objects.requireNonNull(yFirst));
                }
                if (yFirst == null) {
                    return Set.of(xFirst);
                }
                return Set.of(xFirst, yFirst);
            } finally {
                position = target;
            }
        }

        public Set<String> getVariations(String line) {
            Set<String> possibilities = new HashSet<>();
            possibilities.add("");
            for (char ch : line.toCharArray()) {
                Set<String> next = new HashSet<>();
                if (nextRobot != null) {
                    for (String sequence : getSequences(ch)) {
                        for (String possibility : possibilities) {
                            for (String nextPoss : nextRobot.getVariations(sequence)) {
                                next.add(possibility + nextPoss);
                            }
                        }
                    }
                } else {
                    for (String possibility : possibilities) {
                        next.add(possibility + ch);
                    }
                }
                possibilities = next;
                position = pad.indexOf(ch);
            }
            return possibilities;
        }

        public long getMinLength(String line) {
            if (line.isEmpty()) {
                return 0;
            }
            if (nextRobot == null) {
                return line.length();
            }
            if (cache.containsKey(line)) {
                return cache.get(line);
            }
            long minLength = 0;
            for (char ch : line.toCharArray()) {
                minLength += getSequences(ch).stream()
                        .map(sequence -> nextRobot.getMinLength(sequence))
                        .mapToLong(l -> l).min().orElseThrow();
                position = pad.indexOf(ch);
            }
            cache.put(line, minLength);
            return minLength;
        }
    }

    public static long getPart1(List<String> lines) {
        Robot robot = configureRobots(3);
        long sum = 0;
        for (String line : lines) {
            sum += Parse.getLongs(line).getFirst() * robot.getMinLength(line);
        }
        return sum;
    }

    private static Robot configureRobots(int otherRobots) {
        Robot robot = new Robot(numPad);
        Robot lastRobot = robot;
        for (int i = 0; i < otherRobots; i++) {
            Robot nextRobot = new Robot(arrowPad);
            lastRobot.nextRobot = nextRobot;
            lastRobot = nextRobot;
        }
        return robot;
    }


    public static long getPart2(List<String> lines) {
        Robot robot = configureRobots(26);
        long sum = 0;
        for (String line : lines) {
            sum += Parse.getLongs(line).getFirst() * robot.getMinLength(line);
        }
        return sum;
    }

}

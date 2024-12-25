package aoc2024;

import aoc2024.tools.CharMatrix;
import aoc2024.tools.Coord;
import aoc2024.tools.Parse;

import java.util.List;

public class Day21 {

    final static char ACTIVATE = 'A';
    final static CharMatrix numPad = CharMatrix.valueOf(List.of("789", "456", "123", " 0A"));
    final static CharMatrix arrowPad = CharMatrix.valueOf(List.of(" ^A", "<v>"));

    static class Robot {
        final CharMatrix pad;
        Coord position;
        Coord forbidden;

        Robot(CharMatrix pad) {
            this.pad = pad;
            this.position = pad.indexOf(ACTIVATE);
            this.forbidden = pad.indexOf(' ');
        }

        public String getSequence(char ch) {
            Coord target = pad.indexOf(ch);
            if (target.equals(position)) {
                return "A";
            }
            try {
                int deltaY = (int) Math.abs(target.y() - position.y());
                int deltaX = (int) Math.abs(target.x() - position.x());
                if (deltaX == 0) {
                    return (target.y() < position.y() ? "^" : "v").repeat(deltaY)
                            + "A";
                }
                if (deltaY == 0) {
                    return (target.x() < position.x() ? "<" : ">").repeat(deltaX)
                            + "A";
                }
                if (!forbidden.equals(new Coord(target.x(), position.y()))) {
                    // adjust x first
                    return (target.x() < position.x() ? "<" : ">").repeat(deltaX)
                            + (target.y() < position.y() ? "^" : "v").repeat(deltaY)
                            + "A";
                } else {
                    // adjust y first
                    return (target.y() < position.y() ? "^" : "v").repeat(deltaY)
                            + (target.x() < position.x() ? "<" : ">").repeat(deltaX)
                            + "A";
                }
            } finally {
                position = target;
            }
        }

        public String getSequence(String line) {
            StringBuilder sb = new StringBuilder();
            for (char ch : line.toCharArray()) {
                sb.append(getSequence(ch));
            }
            return sb.toString();
        }
    }

    public static long getPart1(List<String> lines) {
        Robot robot = new Robot(numPad);
        Robot robot2 = new Robot(arrowPad);
        long sum = 0;
        Robot robot3 = new Robot(arrowPad);
        for (String line : lines) {
            String sequence1 = robot.getSequence(line);
            String sequence2 = robot2.getSequence(sequence1);
            String sequence = robot3.getSequence(sequence2);
            System.out.println(sequence1);
            System.out.println(sequence2);
            System.out.println(sequence);
            int length = sequence.length();
            long number = Parse.getLongs(line).getFirst();
            System.out.println(length + " * " + line + " = " + (number * length));
            sum += number * length;
        }
        return sum;
    }


    public static long getPart2(List<String> lines) {
        return 0;
    }

}

package aoc2024;

import aoc2024.tools.Parse;
import lombok.RequiredArgsConstructor;

import java.util.*;

import static java.util.stream.Collectors.joining;

public class Day17 {

    @RequiredArgsConstructor
    enum Op {
        ADV(0, true),
        BLX(1, false),
        BST(2, true),
        JNZ(3, false),
        BXC(4, false),
        OUT(5, true),
        BDV(6, true),
        CDV(7, true);

        final int opcode;
        final boolean comboOperand;

        static Op fromOpcode(int opcode) {
            for (Op op : Op.values()) {
                if (op.opcode == opcode) {
                    return op;
                }
            }
            throw new IllegalArgumentException("Unknown opcode " + opcode);
        }
    }

    public static String getPart1(List<String> lines) {
        long regA = Parse.getIntegers(lines.getFirst()).getFirst();
        long regB = Parse.getIntegers(lines.get(1)).getFirst();
        long regC = Parse.getIntegers(lines.get(2)).getFirst();
        List<Integer> code = Parse.getIntegers(lines.get(4));
        return eval(code, regA, regB, regC).stream()
                .map(Object::toString)
                .collect(joining(","));
    }

    private static List<Integer> eval(List<Integer> code, long regA, long regB, long regC) {
        final List<Integer> output = new ArrayList<>();
        int ip = 0;
        while (ip < code.size()) {
            Op op = Op.fromOpcode(code.get(ip++));
            long operand;
            if (op.comboOperand) {
                int value = code.get(ip++);
                operand = switch (value) {
                    case 0, 1, 2, 3 -> value;
                    case 4 -> regA;
                    case 5 -> regB;
                    case 6 -> regC;
                    default -> throw new IllegalArgumentException("Unknown value " + value);
                };
            } else {
                operand = code.get(ip++);
            }
            switch (op) {
                case ADV:
                    regA = regA >> operand;
                    break;
                case BLX:
                    regB = regB ^ operand;
                    break;
                case BST:
                    regB = operand % 8;
                    break;
                case JNZ:
                    if (regA != 0) {
                        ip = (int) operand;
                    }
                    break;
                case BXC:
                    regB = regB ^ regC;
                    break;
                case OUT:
                    output.add((int) operand % 8);
                    break;
                case BDV:
                    regB = regA >> operand;
                    break;
                case CDV:
                    regC = regA >> operand;
                    break;
            }
        }
        return output;
    }


    public static long getPart2(List<String> lines) {
        List<Integer> code = Parse.getIntegers(lines.get(4));
        long regB = Parse.getIntegers(lines.get(1)).getFirst();
        long regC = Parse.getIntegers(lines.get(2)).getFirst();
        // for each number 0..7 all numbers < 1024 that lead to this output
        Map<Integer, Set<Long>> digitPatterns = new HashMap<>();
        for (long regA = 0; regA < 1 << 10; regA++) {
            int digit = getFirstOutput(code, regA, regB, regC);
            digitPatterns.computeIfAbsent(digit, d -> new HashSet<>()).add(regA);
        }
        Set<Long> solutions = new HashSet<>();
        for (int i = 0; i < code.size(); i++) {
            if (i == 0) {
                solutions = new HashSet<>(digitPatterns.get(code.getFirst()));
            } else {
                int shift = i * 3;
                Set<Long> nextPatterns = digitPatterns.get(code.get(i));
                Set<Long> newSolutions = new HashSet<>();
                for (long solution : solutions) {
                    long suffix = solution >> shift;
                    for (long nextPattern : nextPatterns) {
                        if ((nextPattern & 127) == suffix) {
                            newSolutions.add(solution | (nextPattern >> 7) << (shift + 7));
                        }
                    }
                }
                solutions = newSolutions;
            }
            System.out.println(code.subList(0, i + 1) + ": " + solutions.stream().min(Long::compareTo)
                    .map(l -> Long.toString(l, 8)).get());
        }
        return solutions.stream().min(Comparator.naturalOrder()).get();
    }

    private static int getFirstOutput(List<Integer> code, long regA, long regB, long regC) {
        int ip = 0;
        while (true) {
            Op op = Op.fromOpcode(code.get(ip++));
            long operand;
            if (op.comboOperand) {
                int value = code.get(ip++);
                operand = switch (value) {
                    case 0, 1, 2, 3 -> value;
                    case 4 -> regA;
                    case 5 -> regB;
                    case 6 -> regC;
                    default -> throw new IllegalArgumentException("Unknown value " + value);
                };
            } else {
                operand = code.get(ip++);
            }
            switch (op) {
                case ADV:
                    regA = regA >> operand;
                    break;
                case BLX:
                    regB = regB ^ operand;
                    break;
                case BST:
                    regB = operand % 8;
                    break;
                case JNZ:
                    if (regA != 0) {
                        ip = (int) operand;
                    }
                    break;
                case BXC:
                    regB = regB ^ regC;
                    break;
                case OUT:
                    return (int) operand % 8;
                case BDV:
                    regB = regA >> operand;
                    break;
                case CDV:
                    regC = regA >> operand;
                    break;
            }
        }
    }
}

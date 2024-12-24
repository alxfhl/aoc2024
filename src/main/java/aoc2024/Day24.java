package aoc2024;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day24 {

    private static List<Wire> xWires;
    private static List<Wire> yWires;
    private static List<Wire> zWires;
    private static List<Gate> gates;
    private static Map<String, Wire> wires;

    @Data
    @EqualsAndHashCode(of = "name")
    static class Wire implements Comparable<Wire> {
        final String name;
        final Boolean startingValue;
        Gate input;
        final List<Gate> output = new ArrayList<>();
        Boolean value = null;

        public void setValue(int i) {
            setValue(i != 0);
        }

        public void setValue(boolean value) {
            if (this.value != null && this.value == value) {
                return;
            }
            this.value = value;
            for (Gate gate : output) {
                gate.update();
            }
        }

        public void reset() {
            this.value = null;
        }

        public Set<Wire> getAllWires() {
            if (input == null) {
                return Set.of(this);
            }
            Set<Wire> wires = new HashSet<>(input.getAllWires());
            wires.add(this);
            return wires;
        }

        @Override
        public int compareTo(Wire o) {
            return name.compareTo(o.name);
        }
    }

    @Data
    @AllArgsConstructor
    static class Gate {
        final Wire input1;
        final Wire input2;
        Wire output;
        final String type;

        public void update() {
            if (input1.value != null && input2.value != null) {
                output.setValue(switch (type) {
                    case "AND" -> input1.value && input2.value;
                    case "OR" -> input1.value || input2.value;
                    case "XOR" -> input1.value ^ input2.value;
                    default -> throw new IllegalStateException("Unexpected value: " + type);
                });
            }
        }

        public Set<Wire> getAllWires() {
            Set<Wire> wires = new HashSet<>();
            wires.addAll(input1.getAllWires());
            wires.addAll(input2.getAllWires());
            return wires;
        }
    }

    public static long getPart1(List<String> lines) {
        wires = parseWires(lines);
        gates = parseGates(lines, wires);
        for (Wire wire : wires.values()) {
            if (wire.getStartingValue() != null) {
                wire.setValue(wire.getStartingValue());
            }
        }
        long sum = 0;
        for (Wire wire : wires.values()) {
            if (wire.getName().startsWith("z") && wire.getValue()) {
                sum += 1L << Integer.parseInt(wire.getName().substring(1));
            }
        }
        return sum;
    }

    public static String getPart2(List<String> lines) {
        wires = parseWires(lines);
        gates = parseGates(lines, wires);
        xWires = wires.values().stream()
                .filter(w -> w.getName().startsWith("x")).sorted().toList();
        yWires = wires.values().stream()
                .filter(w -> w.getName().startsWith("y")).sorted().toList();
        zWires = wires.values().stream()
                .filter(w -> w.getName().startsWith("z")).sorted().toList();

        Set<Wire> correctWires = new HashSet<>();
        Set<String> solution = new TreeSet<>();
        correctWires.addAll(xWires);
        correctWires.addAll(yWires);
        // check and fix bits one by one
        for (int bit = 0; bit < xWires.size(); bit++) {
            if (!checkBit(bit)) {
                System.out.println("error on bit " + bit);
                Set<Wire> candidates = new HashSet<>(zWires.get(bit).getAllWires());
                candidates.removeAll(correctWires);
                Set<Wire> found = new HashSet<>();
                outer:
                for (Wire candidate : candidates) {
                    for (Wire wire : wires.values()) {
                        if (wire != candidate && !correctWires.contains(wire)) {
                            swapWires(candidate, wire);
                            for (Wire wire1 : wires.values()) {
                                wire1.reset();
                            }
                            if (checkAllBits(bit)) {
                                found.add(candidate);
                                found.add(wire);
                                solution.add(candidate.getName());
                                solution.add(wire.getName());
                                break outer;
                            }
                            swapWires(candidate, wire);
                        }
                    }
                }
                if (found.isEmpty()) {
                    throw new IllegalStateException("Did not find solution");
                }
            }
            correctWires.addAll(zWires.get(bit).getAllWires());
        }
        return String.join(",", solution);
    }

    private static Map<String, Wire> parseWires(List<String> lines) {
        Pattern p = Pattern.compile("(\\w{3,3}): ([01])");
        Map<String, Wire> wires = new HashMap<>();
        for (String line : lines) {
            Matcher m = p.matcher(line);
            if (m.matches()) {
                String name = m.group(1);
                String value = m.group(2);
                wires.put(name, new Wire(name, "1".equals(value)));
            }
        }
        return wires;
    }

    private static List<Gate> parseGates(List<String> lines, Map<String, Wire> wires) {
        Pattern p = Pattern.compile("(\\w{3,3}) (AND|OR|XOR) (\\w{3,3}) -> (\\w{3,3})");
        List<Gate> gates = new ArrayList<>();
        for (String line : lines) {
            Matcher m = p.matcher(line);
            if (m.matches()) {
                Wire input1 = wires.computeIfAbsent(m.group(1), n -> new Wire(n, null));
                String type = m.group(2);
                Wire input2 = wires.computeIfAbsent(m.group(3), n -> new Wire(n, null));
                Wire output = wires.computeIfAbsent(m.group(4), n -> new Wire(n, null));
                Gate gate = new Gate(input1, input2, output, type);
                input1.getOutput().add(gate);
                input2.getOutput().add(gate);
                output.setInput(gate);
                gates.add(gate);
            }
        }
        return gates;
    }


    private static boolean checkAllBits(int bit) {
        for (int i = 0; i <= bit; i++) {
            if (!checkBit(i)) {
                return false;
            }
        }
        return true;
    }

    private static void swapWires(Wire candidate, Wire wire) {
        Gate gate1 = gates.stream()
                .filter(gate -> gate.getOutput() == candidate).findFirst().orElseThrow();
        Gate gate2 = gates.stream()
                .filter(gate -> gate.getOutput() == wire).findFirst().orElseThrow();

        gate1.setOutput(wire);
        wire.setInput(gate1);

        gate2.setOutput(candidate);
        candidate.setInput(gate2);
    }

    private static boolean checkBit(int bit) {
        long pow = 1L << bit;
        // set previous bit to either 0 or 1
        for (int prevValue = 0; prevValue <= 1; prevValue++) {
            for (int i = Math.max(0, bit - 1); i < bit; i++) {
                xWires.get(i).setValue(prevValue);
                yWires.get(i).setValue(prevValue);
            }
            for (int x = 0; x <= 1; x++) {
                xWires.get(bit).setValue(x);
                long a = prevValue * (pow - 1) + x * pow;
                for (int y = 0; y <= 1; y++) {
                    yWires.get(bit).setValue(y);
                    long b = prevValue * (pow - 1) + y * pow;
                    boolean result = ((a + b) & pow) != 0;
                    if (!Objects.equals(result, zWires.get(bit).getValue())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}

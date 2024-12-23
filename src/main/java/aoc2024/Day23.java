package aoc2024;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

public class Day23 {

    @Getter
    @EqualsAndHashCode(of = "name")
    @RequiredArgsConstructor
    static class Computer implements Comparable<Computer> {
        final String name;
        final Set<Computer> connections = new HashSet<>();

        @Override
        public int compareTo(Computer o) {
            return name.compareTo(o.name);
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static long getPart1(List<String> lines) {
        Map<String, Computer> computers = parseInput(lines);
        Set<Set<Computer>> triples = getTriples(computers);
        return triples.stream()
                .filter(set -> set.stream().anyMatch(comp -> comp.getName().startsWith("t")))
                .count();
    }

    private static Set<Set<Computer>> getTriples(Map<String, Computer> computers) {
        Set<Set<Computer>> triples = new HashSet<>();
        for (Computer computer : computers.values()) {
            for (Computer neighbor : computer.getConnections()) {
                for (Computer otherNeighbor : neighbor.getConnections()) {
                    if (computer.getConnections().contains(otherNeighbor)) {
                        triples.add(Stream.of(computer, neighbor, otherNeighbor)
                                .collect(toCollection(TreeSet::new)));
                    }
                }
            }
        }
        return triples;
    }

    private static Map<String, Computer> parseInput(List<String> lines) {
        Map<String, Computer> computers = new HashMap<>();
        for (String line : lines) {
            if (line.charAt(2) != '-') {
                continue;
            }
            Computer from = computers.computeIfAbsent(line.substring(0, 2), Computer::new);
            Computer to = computers.computeIfAbsent(line.substring(3), Computer::new);
            from.getConnections().add(to);
            to.getConnections().add(from);
        }
        return computers;
    }


    public static String getPart2(List<String> lines) {
        Map<String, Computer> input = parseInput(lines);
        Set<Set<Computer>> networks = getTriples(input);
        List<Computer> computers = new ArrayList<>(input.values());
        Set<Computer> biggest = new HashSet<>();
        while (!networks.isEmpty()) {
            // System.out.println(networks.size() + " networks of size " + networks.stream().findFirst().get().size());
            biggest = networks.stream().findFirst().orElseThrow();
            Set<Set<Computer>> biggerNetworks = new HashSet<>();
            for (Set<Computer> network : networks) {
                for (Computer computer : computers) {
                    if (network.stream().allMatch(pc -> pc.getConnections().contains(computer))) {
                        Set<Computer> biggerNetwork = new TreeSet<>(network);
                        biggerNetwork.add(computer);
                        biggerNetworks.add(biggerNetwork);
                    }
                }
            }
            networks = biggerNetworks;
        }
        return biggest.stream()
                .map(Computer::getName)
                .collect(Collectors.joining(","));
    }

}

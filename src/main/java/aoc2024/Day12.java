package aoc2024;

import aoc2024.tools.CharMatrix;
import aoc2024.tools.Coord;
import aoc2024.tools.Direction;

import java.util.*;
import java.util.function.Function;

public class Day12 {

    record Plot(char plant, Coord pos) {

        public List<Plot> getNeighbors() {
            return pos.getNeighbors().stream()
                    .map(p -> new Plot(plant, p)).toList();
        }
    }

    record Fence(Coord pos, Direction side) {
        public List<Fence> getNeighbors() {
            return List.of(new Fence(pos.go(side.turnRight()), side), new Fence(pos.go(side.turnLeft()), side));
        }
    }

    public static long getPart1(List<String> lines) {
        final List<SequencedSet<Plot>> regions = getRegions(lines);
        long sum = 0;
        for (Set<Plot> region : regions) {
            long area = region.size();
            long perimeter = 0;
            for (Plot plot : region) {
                for (Plot neighbor : plot.getNeighbors()) {
                    if (!region.contains(neighbor)) {
                        perimeter++;
                    }
                }
            }
            sum += area * perimeter;
        }
        return sum;
    }

    public static long getPart2(List<String> lines) {
        final List<SequencedSet<Plot>> regions = getRegions(lines);
        long sum = 0;
        for (Set<Plot> region : regions) {
            long area = region.size();
            SequencedSet<Fence> fenceParts = new LinkedHashSet<>();
            for (Plot plot : region) {
                for (Plot neighbor : plot.getNeighbors()) {
                    if (!region.contains(neighbor)) {
                        fenceParts.add(new Fence(plot.pos, Direction.valueOf(plot.pos, neighbor.pos)));
                    }
                }
            }
            int fences = partition(fenceParts, Fence::getNeighbors).size();
            sum += area * fences;
        }
        return sum;
    }

    private static List<SequencedSet<Plot>> getRegions(List<String> lines) {
        CharMatrix matrix = CharMatrix.valueOf(lines);
        final SequencedSet<Plot> plots = new LinkedHashSet<>();
        for (int x = 0; x < matrix.getWidth(); x++) {
            for (int y = 0; y < matrix.getHeight(); y++) {
                plots.add(new Plot(matrix.get(x, y), new Coord(x, y)));
            }
        }
        return partition(plots, Plot::getNeighbors);
    }

    static <T> List<SequencedSet<T>> partition(SequencedSet<T> input, Function<T, Collection<T>> getNeighbors) {
        final List<SequencedSet<T>> regions = new ArrayList<>();
        while (!input.isEmpty()) {
            final SequencedSet<T> newRegion = new LinkedHashSet<>();
            List<T> stack = new ArrayList<>();
            stack.add(input.removeLast());
            while (!stack.isEmpty()) {
                T plot = stack.removeLast();
                newRegion.add(plot);
                for (T neighbor : getNeighbors.apply(plot)) {
                    if (input.remove(neighbor)) {
                        stack.add(neighbor);
                    }
                }
            }
            regions.add(newRegion);
        }
        return regions;

    }

}

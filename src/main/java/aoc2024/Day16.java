package aoc2024;

import aoc2024.tools.CharMatrix;
import aoc2024.tools.Coord;
import aoc2024.tools.Direction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day16 {

    private static final int MOVE_COST = 1;
    private static final int TURN_COST = 1000;

    record Orientation(Coord pos, Direction dir) {

    }

    record State(Orientation orientation, int score, List<State> previous) {
        State turnRight() {
            return new State(new Orientation(orientation.pos, orientation.dir.turnRight()),
                    score + TURN_COST, List.of(this));
        }

        State turnLeft() {
            return new State(new Orientation(orientation.pos, orientation.dir.turnLeft()),
                    score + TURN_COST, List.of(this));
        }

        State turnAround() {
            return new State(new Orientation(orientation.pos, orientation.dir.turnAround()),
                    score + TURN_COST * 2, List.of(this));
        }

        State moveForward() {
            return new State(new Orientation(orientation.pos.go(orientation.dir), orientation.dir),
                    score + MOVE_COST, List.of(this));
        }
    }

    public static long getPart1(List<String> lines) {
        CharMatrix matrix = CharMatrix.valueOf(lines);
        Coord goal = matrix.indexOf('E');
        Coord start = matrix.indexOf('S');
        matrix.set(goal, '.');
        matrix.set(start, '.');

        Map<Orientation, State> scores = solve(start, matrix);
        return Arrays.stream(Direction.values())
                .map(dir -> new Orientation(goal, dir))
                .map(scores::get)
                .filter(Objects::nonNull)
                .map(State::score).min(Integer::compareTo).orElse(Integer.MAX_VALUE);
    }

    private static Map<Orientation, State> solve(Coord start, CharMatrix matrix) {
        var todo = new PriorityQueue<>(Comparator.comparing(State::score));
        todo.add(new State(new Orientation(start, Direction.RIGHT), 0, List.of()));
        Map<Orientation, State> scores = new HashMap<>();
        while (!todo.isEmpty()) {
            State state = todo.poll();
            for (State newState : List.of(state, state.turnRight(), state.turnAround(), state.turnLeft())) {
                State existingState = scores.get(newState.orientation);
                if (existingState == null || existingState.score > newState.score) {
                    scores.put(newState.orientation, newState);
                    State forward = newState.moveForward();
                    if (matrix.get(forward.orientation.pos) == '.') {
                        if (scores.get(forward.orientation) == null
                                || scores.get(forward.orientation).score > forward.score) {
                            todo.add(forward);
                        }
                        if (scores.get(forward.orientation) != null
                                && scores.get(forward.orientation).score == forward.score) {
                            scores.put(forward.orientation, new State(forward.orientation, forward.score,
                                    Stream.concat(forward.previous.stream(), scores.get(forward.orientation).previous.stream()).toList()));
                        }
                    }
                }
            }
        }
        return scores;
    }


    public static long getPart2(List<String> lines) {
        CharMatrix matrix = CharMatrix.valueOf(lines);
        Coord goal = matrix.indexOf('E');
        Coord start = matrix.indexOf('S');
        matrix.set(goal, '.');
        matrix.set(start, '.');

        Map<Orientation, State> scores = solve(start, matrix);
        int bestScore = Arrays.stream(Direction.values())
                .map(dir -> new Orientation(goal, dir))
                .map(scores::get)
                .filter(Objects::nonNull)
                .map(State::score).min(Integer::compareTo).orElse(Integer.MAX_VALUE);

        var todo = Arrays.stream(Direction.values())
                .map(dir -> new Orientation(goal, dir))
                .map(scores::get)
                .filter(state -> state.score == bestScore)
                .collect(Collectors.toCollection(ArrayList::new));
        Set<Coord> tiles = new HashSet<>();
        while (!todo.isEmpty()) {
            State state = todo.removeLast();
            tiles.add(state.orientation.pos);
            for (State prev : state.previous) {
                todo.add(scores.get(prev.orientation));
            }
        }
        return tiles.size();
    }

}

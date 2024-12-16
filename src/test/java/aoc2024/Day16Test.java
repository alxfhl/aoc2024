package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Day16Test {

    String example1 = """
            ###############
            #.......#....E#
            #.#.###.#.###.#
            #.....#.#...#.#
            #.###.#####.#.#
            #.#.#.......#.#
            #.#.#####.###.#
            #...........#.#
            ###.#.#####.#.#
            #...#.....#.#.#
            #.#.#.###.#.#.#
            #.....#...#.#.#
            #.###.#.#.#.#.#
            #S..#.....#...#
            ###############
            """;

    String example2 = """
            #################
            #...#...#...#..E#
            #.#.#.#.#.#.#.#.#
            #.#.#.#...#...#.#
            #.#.#.#.###.#.#.#
            #...#.#.#.....#.#
            #.#.#.#.#.#####.#
            #.#...#.#.#.....#
            #.#.#####.#.###.#
            #.#.#.......#...#
            #.#.###.#####.###
            #.#.#...#.....#.#
            #.#.#.#####.###.#
            #.#.#.........#.#
            #.#.#.#########.#
            #S#.............#
            #################
            """;

    @Test
    @Order(0)
    public void example1() {
        assertThat(Day16.getPart1(Input.fromString(example1))).isEqualTo(7036);
    }

    @Test
    @Order(1)
    public void example2() {
        assertThat(Day16.getPart1(Input.fromString(example2))).isEqualTo(11048);
    }

    @Test
    @Order(2)
    public void puzzlePart1() {
        assertThat(Day16.getPart1(Input.forDay(Day16.class))).isEqualTo(105496);
    }

    @Test
    @Order(3)
    public void example1Part2() {
        assertThat(Day16.getPart2(Input.fromString(example1))).isEqualTo(45);
    }

    @Test
    @Order(4)
    public void example2Part2() {
        assertThat(Day16.getPart2(Input.fromString(example2))).isEqualTo(64);
    }

    @Test
    @Order(5)
    public void puzzlePart2() {
        assertThat(Day16.getPart2(Input.forDay(Day16.class))).isEqualTo(524);
    }
}

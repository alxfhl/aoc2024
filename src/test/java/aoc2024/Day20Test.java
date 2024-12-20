package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Day20Test {

    String example = """
            ###############
            #...#...#.....#
            #.#.#.#.#.###.#
            #S#...#.#.#...#
            #######.#.#.###
            #######.#.#...#
            #######.#.###.#
            ###..E#...#...#
            ###.#######.###
            #...###...#...#
            #.#####.#.###.#
            #.#...#.#.#...#
            #.#.#.#.#.#.###
            #...#...#...###
            ###############
            """;

    @Test
    @Order(1)
    public void examplePart1() {
        assertThat(Day20.getPart1(Input.fromString(example), 1)).isEqualTo(44);
    }

    @Test
    @Order(2)
    public void puzzlePart1() {
        assertThat(Day20.getPart1(Input.forDay(Day20.class), 100)).isEqualTo(1197);
    }

    @Test
    @Order(3)
    public void examplePart2() {
        assertThat(Day20.getPart2(Input.fromString(example), 20, 50)).isEqualTo(285);
    }

    @Test
    @Order(4)
    public void puzzlePart2() {
        assertThat(Day20.getPart2(Input.forDay(Day20.class), 20, 100)).isEqualTo(944910);
    }
}

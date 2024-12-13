package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Day12Test {

    String example = """
            RRRRIICCFF
            RRRRIICCCF
            VVRRRCCFFF
            VVRCCCJFFF
            VVVVCJJCFE
            VVIVCCJJEE
            VVIIICJJEE
            MIIIIIJJEE
            MIIISIJEEE
            MMMISSJEEE
            """;

    @Test
    @Order(1)
    public void examplePart1() {
        assertThat(Day12.getPart1(Input.fromString(example))).isEqualTo(1930);
    }

    @Test
    @Order(2)
    public void puzzlePart1() {
        assertThat(Day12.getPart1(Input.forDay(Day12.class))).isEqualTo(1457298);
    }

    @Test
    @Order(3)
    public void examplePart2() {
        assertThat(Day12.getPart2(Input.fromString(example))).isEqualTo(1206);
    }

    @Test
    @Order(4)
    public void puzzlePart2() {
        assertThat(Day12.getPart2(Input.forDay(Day12.class))).isEqualTo(921636);
    }
}

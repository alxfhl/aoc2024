package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Day10Test {

    String example = """
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732
            """;

    @Test
    @Order(1)
    public void examplePart1() {
        assertThat(Day10.getPart1(Input.fromString(example))).isEqualTo(36);
    }

    @Test
    @Order(2)
    public void puzzlePart1() {
        assertThat(Day10.getPart1(Input.forDay(Day10.class))).isEqualTo(472);
    }

    @Test
    @Order(3)
    public void examplePart2() {
        assertThat(Day10.getPart2(Input.fromString(example))).isEqualTo(81);
    }

    @Test
    @Order(4)
    public void puzzlePart2() {
        assertThat(Day10.getPart2(Input.forDay(Day10.class))).isEqualTo(969);
    }
}

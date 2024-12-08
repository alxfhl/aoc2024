package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Day01Test {

    String example = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
            """;

    @Test
    @Order(1)
    public void examplePart1() {
        assertThat(Day01.getPart1(Input.fromString(example))).isEqualTo(11);
    }

    @Test
    @Order(2)
    public void puzzlePart1() {
        assertThat(Day01.getPart1(Input.forDay(Day01.class))).isEqualTo(1388114);
    }

    @Test
    @Order(3)
    public void examplePart2() {
        assertThat(Day01.getPart2(Input.fromString(example))).isEqualTo(31);
    }

    @Test
    @Order(4)
    public void puzzlePart2() {
        assertThat(Day01.getPart2(Input.forDay(Day01.class))).isEqualTo(23529853);
    }
}

package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Day21Test {

    String example = """
            029A
            980A
            179A
            456A
            379A
            """;

    @Test
    @Order(1)
    public void examplePart1() {
        assertThat(Day21.getPart1(Input.fromString(example))).isEqualTo(126384);
    }

    @Test
    @Order(2)
    public void puzzlePart1() {
        assertThat(Day21.getPart1(Input.forDay(Day21.class))).isEqualTo(224326);
    }

    @Test
    @Order(4)
    public void puzzlePart2() {
        assertThat(Day21.getPart2(Input.forDay(Day21.class))).isEqualTo(-1);
    }
}

package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Day22Test {

    String example = """
            1
            10
            100
            2024
            """;
    String example2 = """
            1
            2
            3
            2024
            """;

    @Test
    @Order(1)
    public void examplePart1() {
        assertThat(Day22.getPart1(Input.fromString(example))).isEqualTo(37327623);
    }

    @Test
    @Order(2)
    public void puzzlePart1() {
        assertThat(Day22.getPart1(Input.forDay(Day22.class))).isEqualTo(12979353889L);
    }

    @Test
    @Order(3)
    public void examplePart2() {
        assertThat(Day22.getPart2(Input.fromString(example2))).isEqualTo(23);
    }

    @Test
    @Order(4)
    public void puzzlePart2() {
        assertThat(Day22.getPart2(Input.forDay(Day22.class))).isEqualTo(1449);
    }
}

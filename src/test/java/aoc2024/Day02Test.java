package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Day02Test {

    String example = """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
            """;

    @Test
    @Order(1)
    public void examplePart1() {
        assertThat(Day02.getPart1(Input.fromString(example))).isEqualTo(2);
    }

    @Test
    @Order(2)
    public void puzzlePart1() {
        assertThat(Day02.getPart1(Input.forDay(Day02.class))).isEqualTo(306);
    }

    @Test
    @Order(3)
    public void examplePart2() {
        assertThat(Day02.getPart2(Input.fromString(example))).isEqualTo(4);
    }

    @Test
    @Order(4)
    public void puzzlePart2() {
        assertThat(Day02.getPart2(Input.forDay(Day02.class))).isEqualTo(366);
    }
}

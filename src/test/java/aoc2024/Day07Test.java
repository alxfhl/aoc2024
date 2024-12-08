package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Day07Test {

    String example = """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
            """;

    @Test
    @Order(1)
    public void examplePart1() {
        assertThat(Day07.getPart1(Input.fromString(example))).isEqualTo(3749);
    }

    @Test
    @Order(2)
    public void puzzlePart1() {
        assertThat(Day07.getPart1(Input.forDay(Day07.class))).isEqualTo(3351424677624L);
    }

    @Test
    @Order(3)
    public void examplePart2() {
        assertThat(Day07.getPart2(Input.fromString(example))).isEqualTo(11387);
    }

    @Test
    @Order(4)
    public void puzzlePart2() {
        assertThat(Day07.getPart2(Input.forDay(Day07.class))).isEqualTo(204976636995111L);
    }
}

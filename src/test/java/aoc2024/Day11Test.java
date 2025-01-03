package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Day11Test {

    String example = """
            125 17
            """;

    @Test
    @Order(1)
    public void examplePart1() {
        assertThat(Day11.getPart1(Input.fromString(example))).isEqualTo(55312);
    }

    @Test
    @Order(2)
    public void puzzlePart1() {
        assertThat(Day11.getPart1(Input.forDay(Day11.class))).isEqualTo(218079);
    }

    @Test
    @Order(4)
    public void puzzlePart2() {
        assertThat(Day11.getPart2(Input.forDay(Day11.class))).isEqualTo(259755538429618L);
    }
}

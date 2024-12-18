package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Day18Test {

    String example = """
            5,4
            4,2
            4,5
            3,0
            2,1
            6,3
            2,4
            1,5
            0,6
            3,3
            2,6
            5,1
            1,2
            5,5
            2,5
            6,5
            1,4
            0,4
            6,4
            1,1
            6,1
            1,0
            0,5
            1,6
            2,0
            """;

    @Test
    @Order(1)
    public void examplePart1() {
        assertThat(Day18.getPart1(Input.fromString(example), 12, 7, 7)).isEqualTo(22);
    }

    @Test
    @Order(2)
    public void puzzlePart1() {
        assertThat(Day18.getPart1(Input.forDay(Day18.class), 1024, 71, 71)).isEqualTo(270);
    }

    @Test
    @Order(3)
    public void examplePart2() {
        assertThat(Day18.getPart2(Input.fromString(example), 12, 7, 7)).isEqualTo("6,1");
    }

    @Test
    @Order(4)
    public void puzzlePart2() {
        assertThat(Day18.getPart2(Input.forDay(Day18.class), 1024, 71, 71)).isEqualTo("51,40");
    }
}

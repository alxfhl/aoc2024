package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Day14Test {

    String example = """
            p=0,4 v=3,-3
            p=6,3 v=-1,-3
            p=10,3 v=-1,2
            p=2,0 v=2,-1
            p=0,0 v=1,3
            p=3,0 v=-2,-2
            p=7,6 v=-1,-3
            p=3,0 v=-1,-2
            p=9,3 v=2,3
            p=7,3 v=-1,2
            p=2,4 v=2,-3
            p=9,5 v=-3,-3
            """;

    @Test
    @Order(1)
    public void examplePart1() {
        assertThat(Day14.getPart1(Input.fromString(example), 11, 7, 100)).isEqualTo(12);
    }

    @Test
    @Order(2)
    public void puzzlePart1() {
        assertThat(Day14.getPart1(Input.forDay(Day14.class), 101, 103, 100)).isEqualTo(231019008);
    }

    @Test
    @Order(4)
    public void puzzlePart2() {
        assertThat(Day14.getPart2(Input.forDay(Day14.class), 101, 103)).isEqualTo(8280);
    }
}

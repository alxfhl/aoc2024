package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Day06Test {

    String example = """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
            """;

    @Test
    @Order(1)
    public void examplePart1() {
        assertThat(Day06.getPart1(Input.fromString(example))).isEqualTo(41);
    }

    @Test
    @Order(2)
    public void puzzlePart1() {
        assertThat(Day06.getPart1(Input.forDay(Day06.class))).isEqualTo(5101);
    }

    @Test
    @Order(3)
    public void examplePart2() {
        assertThat(Day06.getPart2(Input.fromString(example))).isEqualTo(6);
    }

    @Test
    @Order(4)
    public void puzzlePart2() {
        assertThat(Day06.getPart2(Input.forDay(Day06.class))).isEqualTo(1951);
    }
}

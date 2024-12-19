package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Day19Test {

    String example = """
            r, wr, b, g, bwu, rb, gb, br
            
            brwrr
            bggr
            gbbr
            rrbgbr
            ubwu
            bwurrg
            brgr
            bbrgwb
            """;

    @Test
    @Order(1)
    public void examplePart1() {
        assertThat(Day19.getPart1(Input.fromString(example))).isEqualTo(6);
    }

    @Test
    @Order(2)
    public void puzzlePart1() {
        assertThat(Day19.getPart1(Input.forDay(Day19.class))).isEqualTo(317);
    }

    @Test
    @Order(3)
    public void examplePart2() {
        assertThat(Day19.getPart2(Input.fromString(example))).isEqualTo(16);
    }

    @Test
    @Order(4)
    public void puzzlePart2() {
        assertThat(Day19.getPart2(Input.forDay(Day19.class))).isEqualTo(883443544805484L);
    }
}

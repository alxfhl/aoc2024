package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day02Test {

    @Test
    public void example1() {
        String example = """
                7 6 4 2 1
                1 2 7 8 9
                9 7 6 2 1
                1 3 2 4 5
                8 6 4 4 1
                1 3 6 7 9
                """;
        assertThat(Day02.getPart1(Input.fromString(example))).isEqualTo(2);
        assertThat(Day02.getPart2(Input.fromString(example))).isEqualTo(4);
    }

    @Test
    public void puzzle() {
        assertThat(Day02.getPart1(Input.forDay(Day02.class))).isEqualTo(306);
        assertThat(Day02.getPart2(Input.forDay(Day02.class))).isEqualTo(366);
    }
}

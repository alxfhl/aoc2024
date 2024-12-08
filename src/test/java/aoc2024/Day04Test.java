package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day04Test {

    @Test
    public void example1() {
        String example = """
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX
                """;
        assertThat(Day04.getPart1(Input.fromString(example))).isEqualTo(18);
        assertThat(Day04.getPart2(Input.fromString(example))).isEqualTo(9);
    }

    @Test
    public void puzzle() {
        assertThat(Day04.getPart1(Input.forDay(Day04.class))).isEqualTo(2547);
        assertThat(Day04.getPart2(Input.forDay(Day04.class))).isEqualTo(1939);
    }
}

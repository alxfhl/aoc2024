package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day01Test {

    @Test
    public void example1() {
        assertThat(Day01.getPart1(Input.fromString("""
                3   4
                4   3
                2   5
                1   3
                3   9
                3   3
                """))).isEqualTo(11);
        assertThat(Day01.getPart2(Input.fromString("""
                3   4
                4   3
                2   5
                1   3
                3   9
                3   3
                """))).isEqualTo(31);
    }

    @Test
    public void puzzle() {
        assertThat(Day01.getPart1(Input.forDay(Day01.class))).isEqualTo(1388114);
        assertThat(Day01.getPart2(Input.forDay(Day01.class))).isEqualTo(23529853);
    }
}

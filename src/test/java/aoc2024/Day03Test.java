package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day03Test {

    @Test
    public void example1() {
        String example = """
                xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
                """;
        assertThat(Day03.getPart1(Input.fromString(example))).isEqualTo(161);
    }

    @Test
    public void example2() {
        String example = """
                xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
                """;
        assertThat(Day03.getPart2(Input.fromString(example))).isEqualTo(48);
    }

    @Test
    public void puzzle() {
        assertThat(Day03.getPart1(Input.forDay(Day03.class))).isEqualTo(170807108);
        assertThat(Day03.getPart2(Input.forDay(Day03.class))).isEqualTo(74838033);
    }
}

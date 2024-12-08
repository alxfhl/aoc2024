package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Day03Test {

    String examplePart1 = """
            xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
            """;
    String examplePart2 = """
            xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
            """;

    @Test
    @Order(1)
    public void example1() {
        assertThat(Day03.getPart1(Input.fromString(examplePart1))).isEqualTo(161);
    }

    @Test
    @Order(2)
    public void puzzlePart1() {
        assertThat(Day03.getPart1(Input.forDay(Day03.class))).isEqualTo(170807108);
    }

    @Test
    @Order(3)
    public void example2() {
        assertThat(Day03.getPart2(Input.fromString(examplePart2))).isEqualTo(48);
    }

    @Test
    @Order(4)
    public void puzzlePart2() {
        assertThat(Day03.getPart2(Input.forDay(Day03.class))).isEqualTo(74838033);
    }
}

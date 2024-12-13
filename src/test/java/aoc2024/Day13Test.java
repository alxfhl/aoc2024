package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Day13Test {

    String example = """
            Button A: X+94, Y+34
            Button B: X+22, Y+67
            Prize: X=8400, Y=5400
            
            Button A: X+26, Y+66
            Button B: X+67, Y+21
            Prize: X=12748, Y=12176
            
            Button A: X+17, Y+86
            Button B: X+84, Y+37
            Prize: X=7870, Y=6450
            
            Button A: X+69, Y+23
            Button B: X+27, Y+71
            Prize: X=18641, Y=10279
            """;

    @Test
    @Order(1)
    public void examplePart1() {
        assertThat(Day13.getPart1(Input.fromString(example))).isEqualTo(480);
    }

    @Test
    @Order(2)
    public void puzzlePart1() {
        assertThat(Day13.getPart1(Input.forDay(Day13.class))).isEqualTo(33481);
    }

    @Test
    @Order(4)
    public void puzzlePart2() {
        assertThat(Day13.getPart2(Input.forDay(Day13.class))).isEqualTo(-1);
    }
}

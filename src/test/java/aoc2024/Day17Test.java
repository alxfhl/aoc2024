package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Day17Test {

    String example = """
            Register A: 729
            Register B: 0
            Register C: 0
            
            Program: 0,1,5,4,3,0
            """;

    String example2 = """
            Register A: 2024
            Register B: 0
            Register C: 0
            
            Program: 0,3,5,4,3,0
            """;

    @Test
    @Order(1)
    public void examplePart1() {
        assertThat(Day17.getPart1(Input.fromString(example))).isEqualTo("4,6,3,5,6,3,5,2,1,0");
    }

    @Test
    @Order(2)
    public void puzzlePart1() {
        assertThat(Day17.getPart1(Input.forDay(Day17.class))).isEqualTo("3,6,7,0,5,7,3,1,4");
    }

    @Test
    @Order(3)
    public void examplePart2() {
        assertThat(Day17.getPart2(Input.fromString(example2))).isEqualTo(117440);
    }

    @Test
    @Order(4)
    public void puzzlePart2() {
        assertThat(Day17.getPart2(Input.forDay(Day17.class))).isEqualTo(164278496489149L);
    }
}

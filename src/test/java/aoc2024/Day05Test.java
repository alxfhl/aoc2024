package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Day05Test {

    String example = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13
            
            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
            """;

    @Test
    @Order(1)
    public void examplePart1() {
        assertThat(Day05.getPart1(Input.fromString(example))).isEqualTo(143);
    }

    @Test
    @Order(2)
    public void puzzlePart1() {
        assertThat(Day05.getPart1(Input.forDay(Day05.class))).isEqualTo(5329);
    }

    @Test
    @Order(3)
    public void examplePart2() {
        assertThat(Day05.getPart2(Input.fromString(example))).isEqualTo(123);
    }

    @Test
    @Order(4)
    public void puzzlePart2() {
        assertThat(Day05.getPart2(Input.forDay(Day05.class))).isEqualTo(5833);
    }
}

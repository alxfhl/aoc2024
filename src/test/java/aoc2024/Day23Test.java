package aoc2024;

import aoc2024.tools.Input;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Day23Test {

    String example = """
            kh-tc
            qp-kh
            de-cg
            ka-co
            yn-aq
            qp-ub
            cg-tb
            vc-aq
            tb-ka
            wh-tc
            yn-cg
            kh-ub
            ta-co
            de-co
            tc-td
            tb-wq
            wh-td
            ta-ka
            td-qp
            aq-cg
            wq-ub
            ub-vc
            de-ta
            wq-aq
            wq-vc
            wh-yn
            ka-de
            kh-ta
            co-tc
            wh-qp
            tb-vc
            td-yn
            """;

    @Test
    @Order(1)
    public void examplePart1() {
        assertThat(Day23.getPart1(Input.fromString(example))).isEqualTo(7);
    }

    @Test
    @Order(2)
    public void puzzlePart1() {
        assertThat(Day23.getPart1(Input.forDay(Day23.class))).isEqualTo(1119);
    }

    @Test
    @Order(3)
    public void examplePart2() {
        assertThat(Day23.getPart2(Input.fromString(example))).isEqualTo("co,de,ka,ta");
    }

    @Test
    @Order(4)
    public void puzzlePart2() {
        assertThat(Day23.getPart2(Input.forDay(Day23.class))).isEqualTo("av,fr,gj,hk,ii,je,jo,lq,ny,qd,uq,wq,xc");
    }
}

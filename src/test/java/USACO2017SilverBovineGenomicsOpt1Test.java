import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class USACO2017SilverBovineGenomicsOpt1Test {

    @Test
    public void providedExampleTest() {
        int numCows = 3;
        int numPosistions = 8;
        String[] spottyCows = {"AATCCCAT", "GATTGCAA", "GGTCGCAA"};
        String[] plainCows={"ACTCCCAG","ACTCGCAT","ACTTCCAT"};
        USACO2017SilverBovineGenomicsOpt1.Input input = new USACO2017SilverBovineGenomicsOpt1.Input(numCows, numPosistions, spottyCows, plainCows);
        assertEquals(22,new USACO2017SilverBovineGenomicsOpt1().findNumPotentialPostions(input));

    }
}

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class USACO2017BronzeBovineGenomicsTest  {

    @Test
    public void providedExampleTest() {
        int numCows = 3;
        int numPosistions = 8;
        String[] spottyCows = {"AATCCCAT", "GATTGCAA", "GGTCGCAA"};
        String[] plainCows={"ACTCCCAG","ACTCGCAT","ACTTCCAT"};
        USACO2017BronzeBovineGenomics.Input input = new USACO2017BronzeBovineGenomics.Input(numCows, numPosistions, spottyCows, plainCows);
        assertEquals(1,new USACO2017BronzeBovineGenomics().findNumPotentialPostions(input));

    }
}

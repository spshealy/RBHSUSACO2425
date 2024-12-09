import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Dec2019Problem1BronzeCowGymnasticsTest2 extends Dec2019Problem1BronzeCowGymnastics2 {

    @Test
    public void basicTest() throws IOException {
        int[][] practiceResults ={{3,0,1,2},{3,0,2,1},{3,1,0,2}};
        assertEquals(4, calcNumConsistentPairs(practiceResults));
    }

    @Test
    public void basicTestFromFile () throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/gymnastics.in");
        int[][] practiceResults = readInputFile(inputStream);
        assertEquals(4,calcNumConsistentPairs(practiceResults));
    }
}

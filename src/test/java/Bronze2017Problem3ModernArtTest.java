import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Bronze2017Problem3ModernArtTest {
    @Test
    public void test1() {
        int[][] painting = {
                {2, 2, 3,0},
                {2, 7, 3,7},
                {2, 7, 7,7},
                {0,0,0,0}
        };
        Assertions.assertEquals(1,new Bronze2017Problem3ModernArt().findCountOfPossilbeFirstColors(painting));

    }
}

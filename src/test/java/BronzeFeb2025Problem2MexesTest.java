import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BronzeFeb2025Problem2MexesTest {

    @Test
    void test1() {
        int[] a = {2,2,2,0};
        int[] minOps = {1,0,3,1,2};
        BronzeFeb2025Problem2Mexes.Input input = new BronzeFeb2025Problem2Mexes.Input();
        input.a = a;
        input.n = 4;

        Assertions.assertArrayEquals(minOps, BronzeFeb2025Problem2Mexes.findMinsOps(input));
    }
}

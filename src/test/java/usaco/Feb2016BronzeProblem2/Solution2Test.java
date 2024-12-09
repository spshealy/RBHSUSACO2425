package usaco.Feb2016BronzeProblem2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Solution2Test {
    @Test
    public void test1() {
        int[] roomSizes = {4, 7, 8, 6, 4};
        Input input = new Input(5, roomSizes);
        Solution2 solution2 = new Solution2();
        int result = solution2.findMinTravelDistance(input);
        assertEquals(48, result);
    }
}
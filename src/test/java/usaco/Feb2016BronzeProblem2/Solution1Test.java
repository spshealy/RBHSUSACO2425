package usaco.Feb2016BronzeProblem2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution1Test {
    @Test
    public void test1() {
        int[] roomSizes = {4, 7, 8, 6, 4};
        Input input = new Input(5, roomSizes);
        Solution1 solution1 = new Solution1();
        int result = solution1.findMinTravelDistance(input);
        assertEquals(48, result);
    }
}
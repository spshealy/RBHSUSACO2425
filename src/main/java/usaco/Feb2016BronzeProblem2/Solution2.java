package usaco.Feb2016BronzeProblem2;

import java.io.File;

public class Solution2 {

    public static void main(String[] args) {
        System.out.println(new Solution2().findMinTravelDistance(new File(args[0])));
    }

    public Solution2() {
    }

    public int findMinTravelDistance(File inputFile) {
        Input input = Input.parseInputFile(inputFile);
        return findMinTravelDistance(input);
    }

    public int findMinTravelDistance(Input input) {
        int numRooms = input.getNumRooms();
        int[] roomSizes = input.getRoomSizes();

        int[] travelDistances = new int[numRooms];
        for (int i = 0; i < numRooms; i++) {
            travelDistances[i] = computeCowTravelDistance(roomSizes, i);
        }

        return findMinInArray(travelDistances);
    }

    private int findMinInArray(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    public int computeCowTravelDistance(final int[] roomSizes, final int startRoom) {
        int distance = 0;
        for (int offset = 0; offset < roomSizes.length; offset++) {
            int roomsLeft = roomSizes.length - offset;
            distance += offset * roomSizes[(startRoom + offset) % roomSizes.length];
        }
        return distance;

    }
}

package usaco.Feb2016BronzeProblem2;

import java.io.File;
import java.io.InputStream;

public class Solution1 {

    public static void main(String[] args) {
        System.out.println(new Solution1().findMinTravelDistance(new File(args[0])));
    }

    public Solution1() {
    }

    public int findMinTravelDistance(File inputFile) {
        Input input = Input.parseInputFile(inputFile);
        return findMinTravelDistance(input);
    }

    public int findMinTravelDistance(Input input) {
        int numRooms = input.getNumRooms();
        int[] roomSizes = input.getRoomSizes();

        int[] travelDistances = new int[numRooms];
        int totalCows = sum(roomSizes);
        for (int i = 0; i < numRooms; i++) {
            travelDistances[i] = computeCowTravelDistance(roomSizes, i, totalCows);
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

    private int sum(int[] arr) {
        int totalCows = 0;
        for (int i = 0; i < arr.length; i++) {
            totalCows += arr[i];
        }
        return totalCows;
    }

    public int computeCowTravelDistance(final int[] roomSizes, final int startRoom, final int totalCows) {
        int numRooms = roomSizes.length;
        int travelDistance = 0;
        int cowsNotInRoom = totalCows;
        for (int i = 0; i < numRooms - 1; i++) {
            int roomIndex = (startRoom + i) % numRooms;
            cowsNotInRoom -= roomSizes[roomIndex];
            travelDistance += cowsNotInRoom;
        }
        return travelDistance;
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

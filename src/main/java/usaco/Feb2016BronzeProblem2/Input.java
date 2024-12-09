package usaco.Feb2016BronzeProblem2;

import java.io.*;

public class Input {
    int numRooms;
    int[] roomSizes;

    public Input(int numRooms, int[] roomSizes) {
        this.numRooms = numRooms;
        this.roomSizes = roomSizes;
    }

    public int getNumRooms() {
        return numRooms;
    }
    public int[] getRoomSizes() {
        return roomSizes;
    }

    static Input parseInputFile(File f) {
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            int numRooms = Integer.parseInt(reader.readLine());
            int[] roomSizes = new int[numRooms];
            for (int i = 0; i < numRooms; i++) {
                roomSizes[i] = Integer.parseInt(reader.readLine());
            }
            return new Input(numRooms, roomSizes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

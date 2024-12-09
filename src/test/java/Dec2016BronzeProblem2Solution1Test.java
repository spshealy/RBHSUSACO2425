

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Dec2016BronzeProblem2Solution1Test {

    @Test
    public void test0() {
        String[][] boards = {{"fox", "box"}, {"dog", "cat"}, {"car", "bus"}};
        int[] expected = {2,
                2,
                2,
                1,
                0,
                1,
                1,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                2,
                0,
                0,
                1,
                1,
                1,
                1,
                0,
                0,
                1,
                0,
                0};

        int[] result = new Dec2016BronzeProblem2Solution1().blocksNeeded(boards);
        assertArrayEquals(expected, result);
    }

    // for each input and ouput file in provided zip file block_bronze_dec16.zip run a test
    @Test
    public void testWithUsacoData() throws IOException {

        URL zipFileURL = getClass().getResource("/blocks_bronze_dec16.zip");
        assert zipFileURL != null;
        ZipFile zipFile = new ZipFile(zipFileURL.getFile());
        Iterator<? extends ZipEntry> entryIt = zipFile.stream().iterator();
        Map<String, ZipEntry> inputsEntries = new HashMap<>();
        Map<String, ZipEntry> outputEntries = new HashMap<>();
        while (entryIt.hasNext()) {
            ZipEntry entry = entryIt.next();
            String fileName = entry.getName();
            String[] fileNameParts = fileName.split("\\.");
            String name = fileNameParts[0];
            String type = fileNameParts[1];
            if (type.equals("in")) {
                inputsEntries.put(name, entry);
            } else if (type.equals("out")) {
                outputEntries.put(name, entry);
            } else {
                throw new RuntimeException("Invalid file type: " + type);
            }
        }


        for (String name : inputsEntries.keySet()) {
            ZipEntry inputEntry = inputsEntries.get(name);
            ZipEntry outputEntry = outputEntries.get(name);
            int[] expected = readTestDataAnswerOutputFile(zipFile.getInputStream(outputEntry));
            int[] testResult = new Dec2016BronzeProblem2Solution1().readInputAndComputeMaxLetterSums(zipFile.getInputStream(inputEntry));
            System.out.println(Arrays.toString(expected));
            System.out.println(Arrays.toString(expected));
            assertArrayEquals(expected, testResult);
            System.out.println("passed");
        }

    }

    private int[] readTestDataAnswerOutputFile(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        ArrayList<Integer> outputs = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            outputs.add(Integer.parseInt(line));
        }
        return outputs.stream().mapToInt(i -> i).toArray();
    }

}

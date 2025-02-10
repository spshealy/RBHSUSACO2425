import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Bronze2018Problem3FamilyTreeTest {

    @Test
    public void test1() {

        String[][] relations = {
                {"MOTHER","AA"},
                {"GGMOTHER","BB"},
                {"MOTHER","SISTER"},
                {"GMOTHER","MOTHER"},
                {"GMOTHER","AUNT"},
                {"AUNT","COUSIN"},
                {"GGMOTHER", "GMOTHER"}

        };
        Bronze2018Problem3FamilyTree.Input input = new Bronze2018Problem3FamilyTree.Input("AA","BB",relations);
        assertEquals("BB is the great-aunt of AA", new Bronze2018Problem3FamilyTree().howAreReleated(input));

    }

    @Test
    public void testWithUsacoData() throws IOException {

        URL zipFileURL = getClass().getResource("/family_bronze_open18.zip");
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
            String expected = readAnswerFile(zipFile.getInputStream(outputEntry));
            Bronze2018Problem3FamilyTree.Input input = Bronze2018Problem3FamilyTree.Input.read(zipFile.getInputStream(inputEntry));
            String result = new Bronze2018Problem3FamilyTree().howAreReleated(input);
            assertEquals(expected, result, "failed for " + name);
            System.out.println("passed");
        }
    }
    @Test
    public void test15() {
        testWithUsacoDataSetN("15");
    }
    public void testWithUsacoDataSetN(String dataSetName)  {

        try {
            URL zipFileURL = getClass().getResource("/family_bronze_open18.zip");
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

            String name = dataSetName;
            ZipEntry inputEntry = inputsEntries.get(name);
            ZipEntry outputEntry = outputEntries.get(name);

            String expected = readAnswerFile(zipFile.getInputStream(outputEntry));
            Bronze2018Problem3FamilyTree.Input input = Bronze2018Problem3FamilyTree.Input.read(zipFile.getInputStream(inputEntry));
            String result = new Bronze2018Problem3FamilyTree().howAreReleated(input);
            assertEquals(expected, result, "failed for " + name);
            System.out.println("passed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private String readAnswerFile(InputStream inputStream) throws IOException {
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String r = bufferedReader.readLine();
        bufferedReader.close();
        return r;

    }
}

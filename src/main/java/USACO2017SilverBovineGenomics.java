import java.io.*;
import java.util.StringTokenizer;

public class USACO2017SilverBovineGenomics {

    public static void main(String[] args) throws IOException {
        new USACO2017SilverBovineGenomics().findNumPotentialPostions(new File("cownomics.in"), new File("cownomics.out"));
    }

    public void findNumPotentialPostions(File inputFile, File outputFile) throws IOException {
        Input input = parseInputFile(new FileInputStream(inputFile));
        int numPositions = findNumPotentialPostions(input);
        writeOutputFile(outputFile, numPositions);
    }
    public int findNumPotentialPostions(Input input) {
        final int numCows = input.getNumCows();
        final int numPositions = input.getNumPositions();
        int potentialPosistion = 0;
        // genrate all sets of three
        for(int i=0;i<numPositions;i++){
            for(int j=i+1;j<numPositions;j++){
                for(int k=j+1;k<numPositions;k++){
                    if (isPotentialPosition(input.getSpottyCows(), input.getPlainCows(), i,j,k)) {
                        potentialPosistion++;
                    }
                }
            }
        }
        return potentialPosistion;
    }

    boolean isPotentialPosition(String[] spottyCows, String[] plainCows, int pos1,int pos2,int pos3) {
        for (int i = 0; i < spottyCows.length; i++) {
            for (int j = 0; j < plainCows.length; j++) {
                if (spottyCows[i].charAt(pos1) == plainCows[j].charAt(pos1) &&
                    spottyCows[i].charAt(pos2) == plainCows[j].charAt(pos2) &&
                    spottyCows[i].charAt(pos3) == plainCows[j].charAt(pos3)) {
                    return false;
                }
            }
        }
        return true;
    }


    Input parseInputFile(InputStream inputStream) throws IOException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numCows = Integer.parseInt(st.nextToken());
        int numPositions = Integer.parseInt(st.nextToken());
        String[] spottyCows = new String[numCows];
        String[] plainCows = new String[numCows];
        for (int i = 0; i < numCows; i++) {
            spottyCows[i] = br.readLine();
        }
        for (int i = 0; i < numCows; i++) {
            plainCows[i] = br.readLine();
        }
        return new Input(numCows, numPositions, spottyCows, plainCows);
    }

    void  writeOutputFile(File outputFile,int numPosistions) throws IOException {
        FileWriter writer = new FileWriter(outputFile);
        writer.write(Integer.toString(numPosistions));
        writer.close();
    }

    public static class Input {
        private final int numCows;
        private final int numPositions;
        private final String[] spottyCows;
        private final String[] plainCows;

        public Input(int numCows, int numPositions, String[] spottyCows, String[] plainCows) {
            this.numCows = numCows;
            this.numPositions = numPositions;
            this.spottyCows = spottyCows;
            this.plainCows = plainCows;
        }

        public int getNumCows() {
            return numCows;
        }

        public int getNumPositions() {
            return numPositions;
        }

        public String[] getSpottyCows() {
            return spottyCows;
        }

        public String[] getPlainCows() {
            return plainCows;
        }
    }

}

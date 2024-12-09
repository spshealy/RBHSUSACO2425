import java.io.*;
import java.util.StringTokenizer;

public class USACO2017SilverBovineGenomicsOpt1 {

    public static void main(String[] args) throws IOException {
        new USACO2017SilverBovineGenomicsOpt1().findNumPotentialPostions(new File("cownomics.in"), new File("cownomics.out"));
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
        int[][] spottyCows = convInputToIntRep(input.getSpottyCows());
        int[][] plainCows = convInputToIntRep(input.getPlainCows());
        // genrate all sets of three
        for(int i=0;i<numPositions;i++){
            for(int j=i+1;j<numPositions;j++){
                for(int k=j+1;k<numPositions;k++){
                    if (isPotentialPosition(spottyCows, plainCows, i,j,k)) {
                        potentialPosistion++;
                    }
                }
            }
        }
        return potentialPosistion;
    }

    boolean isPotentialPosition(int[][] spottyCows, int[][] plainCows, int pos1,int pos2,int pos3) {
        int S[] = new int[64];
        for(int i=0;i<spottyCows.length;i++){
            S[calcId(spottyCows[i],pos1,pos2,pos3)] = 1;
        }
        for(int i=0;i<plainCows.length;i++){
            if(S[calcId(plainCows[i],pos1,pos2,pos3)]==1){
                return false;
            }
        }
        return true;
    }
    int[][] convInputToIntRep(String[] seq){
        int[][] res = new int[seq.length][seq[0].length()];
        for(int i=0;i<seq.length;i++){
            for(int j=0;j<seq[i].length();j++){
               res[i][j] = tranlateCharToInt(seq[i].charAt(j));
            }
        }
        return res;
    }
    int calcId(String[] seq,int i,int j,int k){
        return (tranlateCharToInt(seq[i].charAt(i))*16)
                +(tranlateCharToInt(seq[j].charAt(j))*4)
                +tranlateCharToInt(seq[k].charAt(k));
    }

    int calcId(int cowDNA[],int i,int j,int k){
        return (cowDNA[i]*16 + cowDNA[j]*4 + cowDNA[k]);
    }

    int tranlateCharToInt(char c){
        if(c=='A'){
            return 0;
        }else if(c=='C'){
            return 1;
        }else if(c=='G'){
            return 2;
        }else if(c=='T'){
            return 3;
        }
        else throw new AssertionError("Unknown char");
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

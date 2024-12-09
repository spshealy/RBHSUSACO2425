import java.io.*;
import java.util.StringTokenizer;

public class Dec2019Problem1BronzeCowGymnastics2 {


    public static void main(String[] args) throws IOException {
        Dec2019Problem1BronzeCowGymnastics2 dec2019Problem1BronzeCowGymnastics2 = new Dec2019Problem1BronzeCowGymnastics2();
        dec2019Problem1BronzeCowGymnastics2.calcNumConsistentPairs(new File("gymnastics.in"),new File("gymnastics.out"));
    }

    public void calcNumConsistentPairs(File inputFile,File outputFile) throws IOException {
        int[][] rankings = readInputFile(new FileInputStream(inputFile));
        writeOutputFile(calcNumConsistentPairs(rankings),outputFile);
    }

    public int calcNumConsistentPairs(int[][] rankings) throws IOException {

        final int numPractices = rankings.length;
        final int numCows = rankings[0].length;

        int[][] numberTimesScoredHigherMatrix = new int[numCows][numCows];
        for (int k = 0; k < numPractices; k++) {
            int[] practiceSession = rankings[k];

            for(int i=0;i<numCows;i++){
                int cow1 = practiceSession[i];
                for(int j=i+1;j<numCows;j++){
                    int cow2 = practiceSession[j];
                    numberTimesScoredHigherMatrix[cow1][cow2]++;
                }
            }
        }
        int numConsistentPairs = 0;
        for(int i=0;i<numCows;i++){
            for(int j=0;j<numCows;j++) {
                if (numberTimesScoredHigherMatrix[i][j] == numPractices) {
                    numConsistentPairs++;
                }
            }
        }
        return numConsistentPairs;
    }

    int[][] readInputFile(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numPractices = Integer.parseInt(st.nextToken());
        int numCows = Integer.parseInt(st.nextToken());

        int[][] rankings = new int[numPractices][numCows];
        for (int i = 0; i < numPractices; i++) {
            StringTokenizer practiceLineTokenizer = new StringTokenizer(br.readLine());
            for (int n = 0; n < numCows; n++) {
                // subtract 1 to make the cow numbers 0 based
                rankings[i][n] = Integer.parseInt(practiceLineTokenizer.nextToken()) - 1;
            }
        }
        return rankings;
    }

    void writeOutputFile(int answer ,File f ) throws IOException {
        PrintWriter output= new PrintWriter (new FileOutputStream(f));
        output.println(answer);
        output.close();
    }
    // just for exmaple interleaved i/o and computation
    // not best practive for profressional coding
    public static int calcNumConsistentPairs(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numPractices = Integer.parseInt(st.nextToken());
        int numCows = Integer.parseInt(st.nextToken());

        int[][] numberTimesScoredHigherMatrix = new int[numCows][numCows];
        for (int k = 0; k < numPractices; k++) {
            StringTokenizer practiceLineTokenizer = new StringTokenizer(br.readLine());
            int[] practiceSession = new int[numCows];
            for (int n = 0; n < numCows; n++) {
                practiceSession[n]  = Integer.parseInt(practiceLineTokenizer.nextToken()) - 1;
            }
            for(int i=0;i<numCows;i++){
                int cow1 = practiceSession[i];
                for(int j=i+1;j<numCows;j++){
                    int cow2 = practiceSession[j];
                    numberTimesScoredHigherMatrix[cow1][cow2]++;
                }
            }
        }
        int numConsistentPairs = 0;
        for(int i=0;i<numCows;i++){
            for(int j=0;j<numCows;j++) {
                if (numberTimesScoredHigherMatrix[i][j] == numPractices) {
                    numConsistentPairs++;
                }
            }
        }
        return numConsistentPairs;
    }
}

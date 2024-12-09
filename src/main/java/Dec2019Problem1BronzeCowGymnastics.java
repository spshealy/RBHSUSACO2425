import java.io.*;
import java.util.StringTokenizer;

public class Dec2019Problem1BronzeCowGymnastics {


    public static void main(String[] args) throws IOException {
        Dec2019Problem1BronzeCowGymnastics dec2019Problem1BronzeCowGymnastics = new Dec2019Problem1BronzeCowGymnastics();
        dec2019Problem1BronzeCowGymnastics.calcNumConsistentPairs(new File("gymnastics.in"),new File("gymnastics.out"));
    }

    public void calcNumConsistentPairs(File inputFile,File outputFile) throws IOException {
        int[][] rankings = readInputFile(new FileInputStream(inputFile));
        writeOutputFile(calcNumConsistentPairs(rankings),outputFile);
    }

    int calcNumConsistentPairs(int[][] rankings) {
        int consistentPairs = 0;
        int numPractices = rankings.length;
        int numCows = rankings[0].length;
        for (int cow1 = 0; cow1 < numCows; cow1++) {
            for (int cow2 = cow1+1 ; cow2 < numCows; cow2++) {
                if (isConsistentPair(cow1, cow2, rankings)) {
                    consistentPairs++;
                }
            }
        }
        return consistentPairs;
    }

    boolean isConsistentPair(int cow1, int cow2, int[][] rankings) {
        int numPractices = rankings.length;
        int numCows = rankings[0].length;

        boolean isConsistent = true;
        int firstPracticeResult = whichCowFinishedAhead(cow1, cow2, rankings[0]);
        for (int n = 1; n < numPractices; n++) {
            int practiceResult = whichCowFinishedAhead(cow1, cow2, rankings[n]);
            if (practiceResult != firstPracticeResult) {
                isConsistent = false;
                break;
            }
        }
        return isConsistent;
    }

    int whichCowFinishedAhead(int cow1, int cow2, int[] rankings) {
        for (int i = 0; i < rankings.length; i++) {
            if (rankings[i] == cow1) {
                return 1;
            } else if (rankings[i] == cow2) {
                return 2;
            }
        }
        // cow1 and cow2 are not in the rankings and this should not happen so blow up
        throw new AssertionError("cow1 and cow2 are not in the rankings");
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
}

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Dec2016BronzeProblem2Solution1 {



    public int[] readInputAndComputeMaxLetterSums(InputStream input) throws IOException {
        String[][] boards = readInput(input);
        return blocksNeeded(boards);
    }

    public int[] blocksNeeded(String[][] boards) {
        int[] blocksNeeded = new int[26];
        Arrays.fill(blocksNeeded, 0);
        final int numBoards = boards.length;
        for (int i = 0; i < numBoards; i++) {
            String frontWord = boards[i][0];
            int[] frontWordFrequency = computeWordCharFreq(frontWord);
            String backWord = boards[i][1];
            int[] backWordFrequency = computeWordCharFreq(backWord);

            int[] blocksNeededForBoard = maxCharFreq(frontWordFrequency, backWordFrequency);
            addFreqs(blocksNeeded, blocksNeededForBoard);
        }

        return blocksNeeded;
    }

    private String[][] readInput(InputStream input) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        int numBoards = Integer.parseInt(br.readLine());
        String[][] boards = new String[numBoards][2];
        for (int i = 0; i < numBoards; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            boards[i][0] = st.nextToken();
            boards[i][1] = st.nextToken();
        }
        return boards;
    }

    // 26 letters in the alphabet
    // indexed 0-25 , a-z
    // value is the number of times the letter appears in the word
    private int[] computeWordCharFreq(final String word) {
        int[] letterSums = new int[26];
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            letterSums[c - 'a']++;
        }
        return letterSums;
    }
    //give two alphabet frequency arrays, return the max frequency of each letter
    private int[] maxCharFreq(int[] letterSums1, int[] letterSums2) {
        int[] maxLetterSums = new int[letterSums1.length];
        for (int i = 0; i < letterSums1.length; i++) {
            maxLetterSums[i] = Math.max(letterSums1[i], letterSums2[i]);
        }
        return maxLetterSums;
    }
    // add the frequencies of the letters in alphabetFreq2 to alphabetFreq1
    private void addFreqs(int[] alphabetFreq1, int[] alphabetFreq2) {
        for (int i = 0; i < alphabetFreq1.length; i++) {
            alphabetFreq1[i] += alphabetFreq2[i];
        }
    }

    public static void main(String[] args) throws IOException {
        InputStream input = new FileInputStream(new File("blocks.in"));
        int[] maxLetters = new Dec2016BronzeProblem2Solution1().readInputAndComputeMaxLetterSums(input);
        FileOutputStream os = new FileOutputStream(new File("blocks.out"));
        PrintWriter pw = new PrintWriter(os);
        for (int i = 0; i < maxLetters.length; i++) {
            pw.println(maxLetters[i]);
        }
        input.close();
        pw.close();
    }
}



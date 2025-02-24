import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Scanner;

public class BronzeFeb2025Problem2Mexes2 {
    public static void main(String[] args) {
        Input input = Input.fromInputStream(System.in);
        int[] minOps = findMinsOps(input);
        for (int i = 0; i < minOps.length; i++) {
            System.out.println(minOps[i]);
        }
    }
    static public int[] findMinsOps(Input input) {
        int[] minOps = new int[input.n+1];
        for (int i = 0; i <= input.n; i++) {
            minOps[i] = findMinOpsForMex(i, input.a);
        }
        return minOps;
    }
    static public int findMinOpsForMex(int desiredMex, int[] a) {
       int numOps = 0;
       int[] valueHistogram = valueHistrogram(a);
       if(valueHistogram[desiredMex]>0){
           int numMexMoves = valueHistogram[desiredMex];
           for(int i=0;i<numMexMoves;i++){
               for(int j=0;j<desiredMex;j++){
                   if(valueHistogram[j]==0){
                       numOps++;
                       valueHistogram[j]++;
                       break;
                   }
               }
           }
           // if any moves left then we know we just put them anywhere other the desiredMex
           numOps=numMexMoves;
       }
        // now let fill the remaining holes before desiredMex
        for(int j=0;j<desiredMex;j++){
            if(valueHistogram[j]==0){
                numOps++;
                valueHistogram[j]++;
            }
        }
       return numOps;
    }
    static int[] valueHistrogram(int[] a){
        int[] hist = new int[a.length+1];
        for(int i=0;i<a.length;i++){
            hist[a[i]]++;
        }
        return hist;
    }


    public static class Input {
        public int n;
        public int[] a;

        public static Input fromInputStream(InputStream inputStream) {
            try {
                Input input = new Input();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                input.n = Integer.parseInt(br.readLine());
                input.a = new int[input.n];
                String nextLine = br.readLine();
                Scanner scanner = new Scanner(nextLine);
                for (int i = 0; i < input.n; i++) {
                        input.a[i] = scanner.nextInt();
                }
                return input;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

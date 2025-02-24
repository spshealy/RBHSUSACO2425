import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BronzeFeb2025Problem2Mexes {
    public static int MAX_N = 100000;
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
        int[] sortedA = Arrays.copyOf(a,a.length);
        Arrays.sort(sortedA);
        int numOps=0;
        for(int v=0;v<desiredMex;v++){
            int location = indexOfFirstValue(sortedA,v);
            if(location ==-1){
                numOps++;
                int replaceLocation = findFirstRepeattgValueSecondLocation(sortedA);
                if(replaceLocation==-1)
                    replaceLocation = sortedA.length-1;
                sortedA[replaceLocation] = v;
                Arrays.sort(sortedA);
            }
        }
        numOps+=countValues(sortedA,desiredMex);

        return numOps;
    }

    static int countValues(int[] sortedA, int val){
        int count = 0;
        for(int i=0;i<sortedA.length;i++){
            if(sortedA[i]==val){
                count++;
            }
        }
        return count;
    }
    public static int findMex(int[] a){
       for(int i=0;i<MAX_N;i++){
           if(!contains(a,i)) return i;
       }
       throw new RuntimeException("No mex found within bounds");
    }
    static boolean contains(int[] a,int val){
        for(int i=0;i<a.length;i++){
            if(a[i]==val){
                return true;
            }
        }
        return false;
    }
    static int indexOfFirstValue(int[] a,int val) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == val) {
                return i;
            }
        }
        return -1;
    }
    static int indexOfFirstValueGreaterThan(int[] a,int val) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == val) {
                return i;
            }
        }
        return -1;
    }
    static int findFirstRepeattgValueSecondLocation(int[] a){
            for(int i=1;i<a.length;i++){
                if(a[i]==a[i-1]){
                    return i;
                }
            }
            return -1;
    }
    static List<Integer> findValues(int[] a,int val){
        List<Integer> locations = new ArrayList<>();
        for(int i=0;i<a.length;i++){
            if(a[i]==val){
                locations.add(i);
            }
        }
        return locations;
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

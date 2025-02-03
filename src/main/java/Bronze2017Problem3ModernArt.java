import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Bronze2017Problem3ModernArt {

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(new File("art.in"));
        int count = new Bronze2017Problem3ModernArt().findCountOfPossilbeFirstColors(Input.read(fis).getFinalPainting());
        PrintWriter out = new PrintWriter("art.out");
        out.println(count);
        out.close();
    }

    public int findCountOfPossilbeFirstColors(int[][] finalPainting) {
        Map<Integer, PaintingColorRectangle> rectangles = findColoredRectrangles(finalPainting);
        int count = 0;

        //go through all found .  If a rectangle is on top of another then it is not possible that it was the first
        for (PaintingColorRectangle r : rectangles.values()) {
            boolean isPossible = true;
            for (PaintingColorRectangle other : rectangles.values()) {
                if (r != other && r.isOnTopOf(finalPainting, other)) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) {
                count++;
            }
        }

        return count;

    }


    Map<Integer, PaintingColorRectangle> findColoredRectrangles(int[][] finalPainting) {
        HashMap<Integer, PaintingColorRectangle> rectangles = new HashMap<>();
        int n = finalPainting.length;

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
               int color = finalPainting[y][x];
               if (color == 0) {
                   continue;
               }
               PaintingColorRectangle r = rectangles.get(color);
               if (r == null) {
                   r = new PaintingColorRectangle(color);
                   rectangles.put(color, r);
               }
               r.update(x, y);

            }
        }
        return rectangles;
    }


    public static class PaintingColorRectangle {
        private int color;
        private  int xMin;
        private  int xMax;
        private  int yMin;
        private  int yMax;

        public PaintingColorRectangle(int color) {
            this.color = color;
            xMin = Integer.MAX_VALUE;
            xMax = Integer.MIN_VALUE;
            yMin = Integer.MAX_VALUE;
            yMax = Integer.MIN_VALUE;
        }

        public PaintingColorRectangle(int color,int xMin, int yMin, int xMax, int yMax) {
            this.color = color;
            this.xMin = xMin;
            this.xMax = xMax;
            this.yMin = yMin;
            this.yMax = yMax;
        }

        public int getxMin() {
            return xMin;
        }

        public void setxMin(int xMin) {
            this.xMin = xMin;
        }

        public int getxMax() {
            return xMax;
        }

        public void setxMax(int xMax) {
            this.xMax = xMax;
        }

        public int getyMin() {
            return yMin;
        }

        public void setyMin(int yMin) {
            this.yMin = yMin;
        }

        public int getyMax() {
            return yMax;
        }

        public void setyMax(int yMax) {
            this.yMax = yMax;
        }

        public int getColor() {
            return color;
        }

        public void update(int x, int y) {
            xMin = Math.min(xMin, x);
            xMax = Math.max(xMax, x);
            yMin = Math.min(yMin, y);
            yMax = Math.max(yMax, y);
        }
        boolean isOnTopOf(int[][] painting, PaintingColorRectangle other) {
            for (int y = other.getyMin(); y <= other.getyMax(); y++) {
                for (int x = other.getxMin(); x <= other.getxMax(); x++) {
                    if (painting[y][x] == color) {
                        return true;
                    }
                }
            }
            return false;
        }

    }

    static class Input {
        private final int n;
        private final int[][] finalPainting;

        public Input(int n, int[][] finalPainting) {
            this.n = n;
            this.finalPainting = finalPainting;
        }

        public int getN() {
            return n;
        }
        public int[][] getFinalPainting() {
            return finalPainting;
        }

        public static Input  read(InputStream inputStream) {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            try {
                int n = Integer.parseInt(br.readLine());
                int[][] finalPainting = new int[n][n];
                for (int y = 0; y < n; y++) {
                    String line = br.readLine();
                    for (int x = 0; x < n; x++) {
                        finalPainting[y][x] = Integer.parseInt(line.charAt(x) + "");
                    }
                }
                return new Input(n, finalPainting);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

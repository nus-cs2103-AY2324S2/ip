import java.io.*;
import java.util.*;
public class Duke {
    private static FastInput in = new FastInput(System.in);
    private static PrintWriter pw = new PrintWriter(System.out);
    public static void main(String[] args) {
        System.out.println( "================================ \n" +
                            "Hello I'm Axolotl! \n" +
                            "What can I do for you? \n" +
                            "================================ \n");

        String input = in.next();

        while (!input.equals("bye")) {
            
        }

        System.out.println("================================ \n" +
                "Bye. Hope to see you again soon! \n" +
                "================================ \n");

    }

    static class FastInput {
        BufferedReader br;
        StringTokenizer tok;

        public FastInput(InputStream in) {
            br = new BufferedReader(new InputStreamReader(System.in));
            tok = new StringTokenizer("");
        }

        public String next() {
            while (!tok.hasMoreTokens()) {
                try {
                    tok = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                }
            }
            return tok.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
}

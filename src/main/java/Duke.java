import java.io.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greeting = "Hello I am DylanBot! \nWhat can I do for you?";
        String exit = "Bye! Hope to see you again soon";

        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<String> stored = new ArrayList<>();
        while (!"bye".equals((input = scanner.nextLine()))) {
            if (input.equals("list")) {
                for (int i = 0; i < stored.size(); i++) {
                    System.out.println("\t" + (i + 1) + ". " + stored.get(i));
                }
            } else {
                stored.add(input);
                System.out.println("\t" + input);
            }
        }
        System.out.println(exit);
    }

    public static class FastScanner {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }
        String nextLine() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken("\n");
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}



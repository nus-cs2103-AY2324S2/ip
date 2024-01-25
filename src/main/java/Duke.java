import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReferenceArray;

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
        ArrayList<Task> tasks = new ArrayList<>();
        while (!"bye".equals((input = scanner.nextLine()))) {
            if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task curr = tasks.get(i);
                    System.out.println("\t" + (i + 1) + ". "
                            + curr.printStatus() + " "
                            + curr.desc);
                }
            } else if (input.split(" ")[0].equals("mark")) {
                int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks.get(idx).completed = true;
                System.out.println("Aight marked this task as done:\n\t"
                        + tasks.get(idx).printStatus() + " " + tasks.get(idx).desc);
            } else if (input.split(" ")[0].equals("unmark")) {
                int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks.get(idx).completed = false;
                System.out.println("Sian marked this task as undone:\n\t"
                        + tasks.get(idx).printStatus() + " " + tasks.get(idx).desc);
            } else {
                tasks.add(new Task(input));
                System.out.println("\t" + input);
            }
        }
        System.out.println(exit);
    }

    public static class Task {
        public String desc;
        public boolean completed;

        public Task(String desc) {
            this.desc = desc;
            this.completed = false;
        }

        public String printStatus() {
            return (completed ? "[X]" : "[ ]");
        }
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



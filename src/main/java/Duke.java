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
            String[] splitInput = input.split(" ");
            if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task curr = tasks.get(i);
                    System.out.println("\t" + (i + 1) + ". "
                            + curr.printTask());
                }
            } else if (splitInput[0].equals("mark")) {
                int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks.get(idx).completed = true;
                System.out.println("Aight marked this task as done:\n\t"
                        + tasks.get(idx).printTask());
            } else if (splitInput[0].equals("unmark")) {
                int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks.get(idx).completed = false;
                System.out.println("Sian marked this task as undone:\n\t"
                        + tasks.get(idx).printTask());
            } else if (splitInput[0].equals("todo")){
                Task curr = new Task("T", input);
                tasks.add(curr);
                System.out.println("Roger doger, added this task: \n\t" + curr.printTask());
            } else if (splitInput[0].equals("deadline")) {
                int idx = 1;
                StringBuilder desc = new StringBuilder();
                StringBuilder deadline = new StringBuilder();
                while (splitInput[idx].charAt(0) != '/') {
                    desc.append(splitInput[idx]);
                    desc.append(" ");
                    idx++;
                }
                idx++;
                while (idx < splitInput.length) {
                    deadline.append(splitInput[idx]);
                    deadline.append(" ");
                    idx++;
                }
                Task curr = new Task("D", desc.toString());
                curr.deadline = deadline.toString();
                tasks.add(curr);
                System.out.println("Roger doger, added this task: \n\t" + curr.printTask());
            } else if (splitInput[0].equals("event")) {
                int idx = 1;
                StringBuilder desc = new StringBuilder();
                StringBuilder from = new StringBuilder();
                StringBuilder to = new StringBuilder();
                while (splitInput[idx].charAt(0) != '/') {
                    desc.append(splitInput[idx]);
                    desc.append(" ");
                    idx++;
                }
                idx++;
                while (splitInput[idx].charAt(0) != '/') {
                    from.append(splitInput[idx]);
                    from.append(" ");
                    idx++;
                }
                idx++;
                while (idx < splitInput.length) {
                    to.append(splitInput[idx]);
                    to.append(" ");
                    idx++;
                }
                Task curr = new Task("E", desc.toString());
                curr.from = from.toString();
                curr.to = to.toString();
                tasks.add(curr);
                System.out.println("Roger doger, added this task: \n\t" + curr.printTask());
            }
            else {
                System.out.println("\t" + input);
            }
            System.out.println("Wow! Now you have " + tasks.size() + " tasks in your list");
        }
        System.out.println(exit);
    }

    public static class Task {

        public String type, desc, deadline, from, to;
        public boolean completed;

        public Task(String type, String desc) {
            this.type  = type;
            this.desc = desc;
            this.completed = false;
        }

        public String printTask() {
            return "[" + type + "] "
            + (completed ? "[X]" : "[ ]")
            + " " + desc
            + (type.equals("D") ? "(by: " + deadline + ")": "")
            + (type.equals("E") ? "(from: " + from + " to: " + to + ")": "");
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



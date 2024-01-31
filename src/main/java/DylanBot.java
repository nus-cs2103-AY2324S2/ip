import java.io.*;
import java.util.*;

public class DylanBot {
    static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) throws DylanBotException {
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
        while (!"bye".equals((input = scanner.nextLine()))) {
            try {
                if (input.isEmpty()) {
                    throw new DylanBotException("HEY no input BETTER SAY SOMETHING");
                }
                if (input.equals("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        Task curr = tasks.get(i);
                        System.out.println("\t" + (i + 1) + ". " + curr.toString());
                    }
                } else if (input.startsWith("mark")) {
                    if (input.split(" ").length < 2) {
                        throw new DylanBotException("HEY no index specified for item to mark");
                    }
                    int idx = Integer.parseInt(input.split(" ")[1]);
                    if (idx > tasks.size() || idx < 0) {
                        throw new DylanBotException("HEY index requested is out of bounds");
                    }
                    tasks.get(idx - 1).completed = true;
                    System.out.println("Aight marked this task as done:\n\t"
                            + tasks.get(idx - 1).toString());
                } else if (input.startsWith("unmark")) {
                    if (input.split(" ").length < 2) {
                        throw new DylanBotException("HEY no index specified for item to mark");
                    }
                    int idx = Integer.parseInt(input.split(" ")[1]);
                    if (idx > tasks.size() || idx < 0) {
                        throw new DylanBotException("HEY index requested is out of bounds");
                    }
                    tasks.get(idx - 1).completed = false;
                    System.out.println("Sian marked this task as undone:\n\t"
                            + tasks.get(idx - 1).toString());
                } else if (input.startsWith("todo")) {
                    createTodo(input);
                } else if (input.startsWith("deadline")) {
                    createDeadline(input);
                } else if (input.startsWith("event")) {
                    createEvent(input);
                } else if (input.startsWith("delete")) {
                    deleteTask(input);
                } else {
                    throw new DylanBotException("Hello INVALID INPUT pls make it make sense");
                }
                System.out.println("Wow! Now you have " + tasks.size() + " tasks in your list");
            } catch (DylanBotException e) {
                System.out.println(e);
            }
        }
        System.out.println(exit);
    }

    public static void createTodo(String input) throws DylanBotException {
        String desc = input.substring(5);
        if (desc.isEmpty()) {
            throw new DylanBotException("HEY todo description cannot be empty!");
        }
        Task curr = new TodoTask("T", desc);
        tasks.add(curr);
        System.out.println("Roger doger, added this task: \n\t" + curr.toString());
    }

    public static void createDeadline(String input) throws DylanBotException {
        String[] inputArr = input.split("/by");
        String desc = inputArr[0].substring(9).trim();
        String deadline = inputArr[1].trim();
        if (desc.isEmpty()) {
            throw new DylanBotException("HEY deadline description cannot be empty!");
        }
        if (deadline.isEmpty()) {
            throw new DylanBotException("HEY deadline tasks need deadlines!");
        }
        DeadlineTask curr = new DeadlineTask("D", desc, deadline);
        tasks.add(curr);
        System.out.println("Roger doger, added this task: \n\t" + curr.toString());
    }

    public static void createEvent(String input) throws DylanBotException {
        String[] inputArr = input.split("/from|/to");
        String desc = inputArr[0].substring(6).trim();
        String from = inputArr[1].trim();
        String to = inputArr[2].trim();
        if (desc.isEmpty()) {
            throw new DylanBotException("HEY event description cannot be empty!");
        }
        if (from.isEmpty()) {
            throw new DylanBotException("HEY event tasks need starting dates!");
        }
        if (to.isEmpty()) {
            throw new DylanBotException("HEY event tasks need ending dates!");
        }
        EventTask curr = new EventTask("E", desc, from, to);
        tasks.add(curr);
        System.out.println("Roger doger, added this task: \n\t" + curr.toString());
    }

    public static void deleteTask(String input) throws DylanBotException {
        String[] inputArr = input.split(" ");
        int idx = Integer.parseInt(inputArr[1]);
        if (idx > tasks.size() || idx < 0) {
            throw new DylanBotException("HEY index requested is out of bounds");
        }
        Task toRemove = tasks.get(idx - 1);
        tasks.remove(idx - 1);
        System.out.println("Aight removed this task:\n\t" + toRemove.toString());
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



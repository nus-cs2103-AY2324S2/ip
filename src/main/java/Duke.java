import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        Duke.greet();
        Scanner scanner = new Scanner(System.in);
        String input = "";
        do {
            input = scanner.nextLine();
            read(input);
        } while(!input.equals("bye"));
    }

    public static void horizontalLine() {
        System.out.println("\t────────────────────────────────────────────────────────────");
    }

    public static void greet() {
        Duke.horizontalLine();
        System.out.println("\tHello! I'm Ezra.\n\tWhat can I do for you?");
        Duke.horizontalLine();
    }

    public static void read(String input) {
        if (input.isEmpty()) {
            return;
        }
        Duke.horizontalLine();
        if (input.equals("bye")) {
            System.out.println("\tBye. Hope to see you again soon!");
        } else if (input.equals("list")) {
            listTasks();
        } else if (Pattern.matches("mark\\s\\d+", input)) {
            int taskIndex = Integer.parseInt(input.split("\\s")[1]) - 1;
            mark(taskIndex);
        } else if (Pattern.matches("unmark\\s\\d+", input)) {
            int taskIndex = Integer.parseInt(input.split("\\s")[1]) - 1;
            unmark(taskIndex);
        } else {
            tasks.add(new Task(input));
            System.out.println("\tadded: " + input);
        }
        Duke.horizontalLine();
    }

    public static void listTasks() {
        if (Duke.tasks.size() == 0) {
            System.out.println("\tThere are no tasks in your list.");
            return;
        }
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < Duke.tasks.size(); i++) {
            System.out.printf(
                    "\t%d.%s\n",
                    i + 1,
                    Duke.tasks.get(i).toString()
            );
        }
    }

    public static void mark(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= Duke.tasks.size()) {
            System.out.println("Invalid task number");
            return;
        }
        Duke.tasks.get(taskIndex).isDone = true;
        System.out.println("\tNice! I have marked this task as done:");
        System.out.printf(
                "\t  %s\n",
                Duke.tasks.get(taskIndex).toString()
        );
    }

    public static void unmark(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= Duke.tasks.size()) {
            System.out.println("Invalid task number");
            return;
        }
        Duke.tasks.get(taskIndex).isDone = false;
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.printf(
                "\t  %s\n",
                Duke.tasks.get(taskIndex).toString()
        );
    }
}

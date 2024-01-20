import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
public class Duke {
    private ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        Duke chatbot = new Duke();
        chatbot.greet();
        Scanner scanner = new Scanner(System.in);
        String input = "";
        do {
            input = scanner.nextLine();
            chatbot.read(input);
        } while(!input.equals("bye"));
    }

    public static void horizontalLine() {
        System.out.println("\t────────────────────────────────────────────────────────────");
    }

    public void greet() {
        Duke.horizontalLine();
        System.out.println("\tHello! I'm Ezra.\n\tWhat can I do for you?");
        Duke.horizontalLine();
    }

    public void read(String input) {
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
        } else if (Pattern.matches("todo\\s\\S.*", input)){
            String description = input.split("\\s", 2)[1];
            ToDo todo = new ToDo(description);
            tasks.add(todo);
            System.out.printf("\tGot it. I've added this task:\n\t  %s\n", todo);
        } else if (Pattern.matches("deadline\\s\\S.*/by\\s\\S.*", input)) {
            String[] arr = input.split("\\s/by\\s");
            String by = arr[1];
            String description = arr[0].split("\\s", 2)[1];
            Deadline deadline = new Deadline(description, by);
            tasks.add(deadline);
            System.out.printf("\tGot it. I've added this task:\n\t  %s\n", deadline);
        } else {
            System.out.println("Invalid command");
        }
        Duke.horizontalLine();
    }

    public void listTasks() {
        if (this.tasks.size() == 0) {
            System.out.println("\tThere are no tasks in your list.");
            return;
        }
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.printf(
                    "\t%d.%s\n",
                    i + 1,
                    this.tasks.get(i)
            );
        }
    }

    public void mark(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
            System.out.println("Invalid task number");
            return;
        }
        this.tasks.get(taskIndex).isDone = true;
        System.out.println("\tNice! I have marked this task as done:");
        System.out.printf(
                "\t  %s\n",
                this.tasks.get(taskIndex)
        );
    }

    public void unmark(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
            System.out.println("Invalid task number");
            return;
        }
        this.tasks.get(taskIndex).isDone = false;
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.printf(
                "\t  %s\n",
                this.tasks.get(taskIndex)
        );
    }
}

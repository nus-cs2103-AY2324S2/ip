import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
public class Duke {
    private ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        Duke chatBot = new Duke();
        chatBot.greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            chatBot.read(input);
            if (input.equals("bye")) {
                break;
            }
        };
        scanner.close();
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
        Duke.horizontalLine();
        try {
            if (input.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
            } else if (input.equals("list")) {
                this.listTasks();
            } else if (input.startsWith("mark")) {
                this.mark(this.parseMark(input));
            } else if (input.startsWith("unmark")) {
                this.unmark(this.parseUnmark(input));
            } else if (input.startsWith("todo")) {
                this.updateTasks(this.parseToDo(input));
            } else if (input.startsWith("deadline")) {
                this.updateTasks(this.parseDeadline(input));
            } else if (input.startsWith("event")) {
                this.updateTasks(this.parseEvent(input));
            } else {
                System.out.println("\tInvalid command");
            }
        } catch (WrongFormatException e) {
            System.out.println("\t" + e.getMessage());
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

    public int parseMark(String input) throws WrongFormatException {
        if (Pattern.matches("mark\\s\\d+", input)) {
            return Integer.parseInt(input.split("\\s")[1]) - 1;
        } else {
            throw new WrongFormatException(
                    "Invalid 'mark' command format. Usage: mark <existing task number>"
            );
        }
    }
    public void mark(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
            System.out.println("\tInvalid task number");
            return;
        }
        this.tasks.get(taskIndex).isDone = true;
        System.out.println("\tNice! I have marked this task as done:");
        System.out.printf(
                "\t  %s\n",
                this.tasks.get(taskIndex)
        );
    }

    public int parseUnmark(String input) throws WrongFormatException {
        if (Pattern.matches("unmark\\s\\d+", input)) {
            return Integer.parseInt(input.split("\\s")[1]) - 1;
        } else {
            throw new WrongFormatException(
                    "Invalid 'unmark' command format. Usage: unmark <existing task number>"
            );
        }
    }

    public void unmark(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
            System.out.println("\tInvalid task number");
            return;
        }
        this.tasks.get(taskIndex).isDone = false;
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.printf(
                "\t  %s\n",
                this.tasks.get(taskIndex)
        );
    }

    public void updateTasks(Task task) {
        this.tasks.add(task);
        System.out.printf("\tGot it. I've added this task:\n\t  %s\n", task);
        System.out.printf("\tNow you have %d tasks in the list.\n", this.tasks.size());
    }

    public ToDo parseToDo(String input) throws WrongFormatException {
        if (Pattern.matches("todo\\s\\S.*", input)) {
            String description = input.split("\\s", 2)[1];
            return new ToDo(description);
        } else {
            throw new WrongFormatException(
                    "Invalid 'todo' command format. Usage: todo <description>"
            );
        }
    }

    public Deadline parseDeadline(String input) throws WrongFormatException {
        if (Pattern.matches("deadline\\s\\S.*\\s/by\\s\\S.*", input)) {
            String[] arr = input.split("\\s/by\\s");
            String by = arr[1];
            String description = arr[0].split("\\s", 2)[1];
            return new Deadline(description, by);
        } else {
            throw new WrongFormatException(
                    "Invalid 'deadline' command format. Usage: deadline <description> /by <date>"
            );
        }
    }

    public Event parseEvent(String input) throws WrongFormatException {
        if (Pattern.matches("event\\s\\S.*\\s/from\\s\\S.*\\s/to\\s\\S.*", input)) {
            String[] splitTo = input.split("\\s/to\\s");
            String to = splitTo[1];
            String[] splitFrom = splitTo[0].split("\\s/from\\s");
            String from = splitFrom[1];
            String description = splitFrom[0].split("\\s", 2)[1];
            return new Event(description, from, to);
        } else {
            throw new WrongFormatException(
                    "Invalid 'event' command format. Usage: event <description> /from <date> /to <date>"
            );
        }
    }
}

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final TaskList TASKS = new TaskList();
    private static final Pattern DUE_PATTERN = Pattern.compile("/by (.+)");
    private static final Pattern EVENT_PATTERN = Pattern.compile("/from (.+) /to (.+)");
    private static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    private static final String INDENT = "    ";
    private static final String LIST_COMMAND = "list";
    private static final String EXIT_COMMAND = "bye";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

    public static void main(String[] args) {
        printBanner();
        printGreetings();
        inputLoop();
        printExit();
        Duke.SCANNER.close();
    }

    private static void inputLoop() {
        String input = "";
        while (true) {
            input = SCANNER.nextLine();
            if (input.equals(EXIT_COMMAND)) {
                break;
            }
            try {
                handleInput(input);
            } catch (DukeException e) {
                print(e.getMessage());
            }
        }
    }

    private static void handleInput(String input) throws DukeException {
        if (input.equals(LIST_COMMAND)) {
            String[] messages = new String[TASKS.size() + 1];
            messages[0] = "Here are the tasks in your list:";
            System.arraycopy(TASKS.toString().split("\n"), 0, messages, 1, TASKS.size());
            print(messages);
        } else if (input.startsWith(MARK_COMMAND)) {
            if (input.length() < MARK_COMMAND.length() + 2) {
                throw new DukeException("Please enter an index.");
            }
            String indexStr = input.substring(MARK_COMMAND.length() + 1);
            try {
                int index = Integer.parseInt(indexStr);
                if (!TASKS.validIndex(index)) {
                    throw new DukeException("Please enter a valid index.");
                }
                TASKS.getTask(index).done();
                String[] messages = {
                    "Nice! I've marked this task as done:", TASKS.getTask(index).toString()
                };
                print(messages);
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid number.");
            }
        } else if (input.startsWith(UNMARK_COMMAND)) {
            if (input.length() < UNMARK_COMMAND.length() + 2) {
                throw new DukeException("Please enter an index.");
            }
            String indexStr = input.substring(UNMARK_COMMAND.length() + 1);
            try {
                int index = Integer.parseInt(indexStr);
                if (!TASKS.validIndex(index)) {
                    throw new DukeException("Please enter a valid index.");
                }
                TASKS.getTask(index).undone();
                String[] messages = {
                    "OK, I've marked this task as not done yet:", TASKS.getTask(index).toString()
                };
                print(messages);
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid number.");
            }
        } else if (input.startsWith(TODO_COMMAND)) {
            if (input.length() < TODO_COMMAND.length() + 2) {
                throw new DukeException("Please enter the task description.");
            }
            String description = input.substring(TODO_COMMAND.length() + 1);
            Task task = new Todo(description);
            TASKS.addTask(task);
            printAddedTask(task);
        } else if (input.startsWith(DEADLINE_COMMAND)) {
            if (input.length() < DEADLINE_COMMAND.length() + 2) {
                throw new DukeException("Please enter the task description and /by [Date Time].");
            }
            Matcher matcher = DUE_PATTERN.matcher(input);
            if (matcher.find()) {
                String description =
                        input.substring(DEADLINE_COMMAND.length() + 1, matcher.start() - 1);
                String due = matcher.group(1);
                Task task = new Deadline(description, due);
                TASKS.addTask(task);
                printAddedTask(task);
            } else {
                throw new DukeException(
                        "Please specify the due date of the deadline task using /by [Date Time].");
            }
        } else if (input.startsWith(EVENT_COMMAND)) {
            if (input.length() < EVENT_COMMAND.length() + 2) {
                throw new DukeException("Please enter the task description and /from [Date Time] /to [Date Time].");
            }
            Matcher matcher = EVENT_PATTERN.matcher(input);
            if (matcher.find()) {
                String description =
                        input.substring(EVENT_COMMAND.length() + 1, matcher.start() - 1);
                String from = matcher.group(1);
                String to = matcher.group(2);
                Task task = new Event(description, from, to);
                TASKS.addTask(task);
                printAddedTask(task);
            } else {
                throw new DukeException(
                        "Please specify the start and end date of the event task using /from"
                            + " [Date Time] /to [Date Time].");
            }
        } else {
            print("Sorry I can't help with that :(");
        }
    }

    private static void printAddedTask(Task task) {
        String[] messages = {
            "Got it. I've added this task:",
            task.toString(),
            "Now you have " + TASKS.size() + " tasks in the list."
        };
        print(messages);
    }

    private static void printBanner() {
        System.out.println(" ██████╗ █████╗ ██████╗ ██████╗ ██╗   ██╗");
        System.out.println("██╔════╝██╔══██╗██╔══██╗██╔══██╗╚██╗ ██╔╝");
        System.out.println("██║     ███████║██████╔╝██████╔╝ ╚████╔╝ ");
        System.out.println("██║     ██╔══██║██╔═══╝ ██╔═══╝   ╚██╔╝  ");
        System.out.println("╚██████╗██║  ██║██║     ██║        ██║   ");
        System.out.println(" ╚═════╝╚═╝  ╚═╝╚═╝     ╚═╝        ╚═╝   ");
    }

    private static void print(String message) {
        System.out.println(INDENT + HORIZONTAL_LINE);
        System.out.println(INDENT + " " + message);
        System.out.println(INDENT + HORIZONTAL_LINE + "\n");
    }

    private static void print(String[] messages) {
        System.out.println(INDENT + HORIZONTAL_LINE);
        for (String msg : messages) {
            System.out.println(INDENT + " " + msg);
        }
        System.out.println(INDENT + HORIZONTAL_LINE + "\n");
    }

    private static void printGreetings() {
        String[] messages = {"Hello! I'm Cappy", "What can I do for you?"};
        print(messages);
    }

    private static void printExit() {
        print("Bye. Hope to see you again soon!");
    }
}

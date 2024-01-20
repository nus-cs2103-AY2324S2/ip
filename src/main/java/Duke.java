import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final TaskList TASKS = new TaskList();
    private static final Pattern DUE_PATTERN = Pattern.compile("/by (.*)");
    private static final Pattern EVENT_PATTERN = Pattern.compile("/from (.*) /to (.*)");
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
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
        handleInput();
        printExit();
        Duke.SCANNER.close();
    }

    private static void handleInput() {
        String input = SCANNER.nextLine();
        while (!input.equals(EXIT_COMMAND)) {
            if (input.equals(LIST_COMMAND)) {
                String[] messages = new String[TASKS.size() + 1];
                messages[0] = "Here are the tasks in your list:";
                System.arraycopy(TASKS.toString().split("\n"), 0, messages, 1, TASKS.size());
                print(messages);
            } else if (input.startsWith(MARK_COMMAND)) {
                String indexStr = input.substring(5);
                int index = Integer.parseInt(indexStr);
                TASKS.getTask(index).done();
                String[] messages = {
                    "Nice! I've marked this task as done:",
                    TASKS.getTask(index).toString()
                };
                print(messages);
            } else if (input.startsWith(UNMARK_COMMAND)) {
                String indexStr = input.substring(7);
                int index = Integer.parseInt(indexStr);
                TASKS.getTask(index).undone();
                String[] messages = {
                    "OK, I've marked this task as not done yet:",
                    TASKS.getTask(index).toString()
                };
                print(messages);
            } else if (input.startsWith(TODO_COMMAND)) {
                Task task = new Todo(input);
                TASKS.addTask(task);
                printAddedTask(task);
            } else if (input.startsWith(DEADLINE_COMMAND)) {
                Matcher matcher = DUE_PATTERN.matcher(input);
                if (matcher.find()) {
                    String description = input.substring(DEADLINE_COMMAND.length() + 1, matcher.start() - 1);
                    String due = matcher.group(1);
                    Task task = new Deadline(description, due);
                    TASKS.addTask(task);
                    printAddedTask(task);
                } else {
                    print("Please specify the due date of the deadline task using /by [DateTime].");
                }
            } else if (input.startsWith(EVENT_COMMAND)) {
                Matcher matcher = EVENT_PATTERN.matcher(input);
                if (matcher.find()) {
                    String description = input.substring(EVENT_COMMAND.length() + 1, matcher.start() - 1);
                    String from = matcher.group(1);
                    String to = matcher.group(2);
                    Task task = new Event(description, from, to);
                    TASKS.addTask(task);
                    printAddedTask(task);
                } else {
                    print("Please specify the start and end date of the event task using /from [DateTime] /to [DateTime].");
                }
            } else {
                print("Sorry I can't help with that :(");
            }
            input = SCANNER.nextLine();
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

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
    private static final String MARK_PREFIX = "mark ";
    private static final String UNMARK_PREFIX = "unmark ";
    private static final String TODO_PREFIX = "todo ";
    private static final String DEADLINE_PREFIX = "deadline ";
    private static final String EVENT_PREFIX = "event ";

    public static void main(String[] args) {
        printBanner();
        printGreetings();
        handleInput();
        printExit();
        Duke.SCANNER.close();
    }

    private static void handleInput() {
        String input = Duke.SCANNER.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                Duke.print(Duke.TASKS.toString().split("\n"));
            } else if (input.startsWith(MARK_PREFIX)) {
                String indexStr = input.substring(5);
                int index = Integer.parseInt(indexStr);
                Duke.TASKS.getTask(index).done();
                String[] messages = {
                    "Nice! I've marked this task as done:",
                    Duke.TASKS.getTask(index).toString()
                };
                Duke.print(messages);
            } else if (input.startsWith(UNMARK_PREFIX)) {
                String indexStr = input.substring(7);
                int index = Integer.parseInt(indexStr);
                Duke.TASKS.getTask(index).undone();
                String[] messages = {
                    "OK, I've marked this task as not done yet:",
                    Duke.TASKS.getTask(index).toString()
                };
                Duke.print(messages);
            } else if (input.startsWith(TODO_PREFIX)) {
                Task task = new Todo(input);
                Duke.TASKS.addTask(task);
                printTaskAdded(task);
            } else if (input.startsWith(DEADLINE_PREFIX)) {
                Matcher matcher = Duke.DUE_PATTERN.matcher(input);
                if (matcher.find()) {
                    String description = input.substring(DEADLINE_PREFIX.length(), matcher.start() - 1);
                    String due = matcher.group(1);
                    Task task = new Deadline(description, due);
                    Duke.TASKS.addTask(task);
                    printTaskAdded(task);
                } else {
                    String[] messages = {
                        "Please specify the due date of the deadline task using /by [DateTime]."
                    };
                    Duke.print(messages);
                }
            } else if (input.startsWith(EVENT_PREFIX)) {
                Matcher matcher = Duke.EVENT_PATTERN.matcher(input);
                if (matcher.find()) {
                    String description = input.substring(EVENT_PREFIX.length(), matcher.start() - 1);
                    String from = matcher.group(1);
                    String to = matcher.group(2);
                    Task task = new Event(description, from, to);
                    Duke.TASKS.addTask(task);
                    printTaskAdded(task);
                } else {
                    String[] messages = {
                        "Please specify the start and end date of the event task using /from [DateTime] /to [DateTime]."
                    };
                    Duke.print(messages);
                }
            } else {
                String[] messages = { "Sorry I can't help with that :(" };
                Duke.print(messages);
            }
            input = Duke.SCANNER.nextLine();
        }
    }

    private static void printAddedTask(Task task) {
        String[] messages = {
            "Got it. I've added this task:",
            task.toString(),
            "Now you have " + Duke.TASKS.size() + " tasks in the list."
        };
        Duke.print(messages);
    }

    private static void printBanner() {
        System.out.println(" ██████╗ █████╗ ██████╗ ██████╗ ██╗   ██╗");
        System.out.println("██╔════╝██╔══██╗██╔══██╗██╔══██╗╚██╗ ██╔╝");
        System.out.println("██║     ███████║██████╔╝██████╔╝ ╚████╔╝ ");
        System.out.println("██║     ██╔══██║██╔═══╝ ██╔═══╝   ╚██╔╝  ");
        System.out.println("╚██████╗██║  ██║██║     ██║        ██║   ");
        System.out.println(" ╚═════╝╚═╝  ╚═╝╚═╝     ╚═╝        ╚═╝   ");
    }

    private static void print(String[] messages) {
        System.out.println("    ____________________________________________________________");
        for (String msg : messages) {
            System.out.println("     " + msg);
        }
        System.out.println("    ____________________________________________________________\n");
    }

    private static void printGreetings() {
        String[] messages = {"Hello! I'm Cappy", "What can I do for you?"};
        Duke.print(messages);
    }

    private static void printExit() {
        String[] messages = {"Bye. Hope to see you again soon!"};
        Duke.print(messages);
    }
}

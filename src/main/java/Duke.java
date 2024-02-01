import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import exceptions.tasks.EmptyDescriptionException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * The duke class represents the entry point to the chat-bot.
 */
public class Duke {

    private static final String NAME = "Arctic";
    private static final Character BORDER_CHAR = '_';
    private static final Integer BORDER_LEN = 60;
    private static final ArrayList<Task> TASKS = new ArrayList<>();

    private static String getBorder() {
        return String.valueOf(BORDER_CHAR).repeat(BORDER_LEN);
    }

    private static void userGreeting() {
        System.out.println(getBorder());
        System.out.printf("Hello! I'm %s\n", NAME);
        System.out.println("What can I do for you?");
        System.out.println(getBorder());
    }

    private static void userFarewell() {
        System.out.println(getBorder());
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(getBorder());
    }

    private static String handleList() {
        String[] output = new String[TASKS.size()];
        for (int i = 0; i < TASKS.size(); i++) {
            Task task = TASKS.get(i);
            output[i] = String.format("%d. %s", i + 1, task.toString());
        }
        return String.join("\n", output);
    }

    private static String handleMarkUnMark(String[] args) {
        String command = args[0];
        int taskNum = Integer.parseInt(args[1]);
        Task task = TASKS.get(taskNum - 1);

        if (command.equals("mark")) {
            task.mark();
            return String.format("Nice! I've marked this task as done:\n  %s", task);
        } else {
            task.unmark();
            return String.format("OK, I've marked this task as not done yet:\n  %s", task);
        }
    }

    private static Task addTodo(String[] taskArgs) throws EmptyDescriptionException {
        String description = String.join(" ", taskArgs);

        Todo todo = new Todo(description);
        TASKS.add(todo);

        return todo;
    }

    private static Task addDeadline(String[] taskArgs) throws EmptyDescriptionException {
        String byDelim = "/by";

        String argsStr = String.join(" ", taskArgs);
        int byIndex = argsStr.indexOf(byDelim);

        String by = argsStr.substring(byIndex + byDelim.length()).trim();
        String description = argsStr.substring(0, byIndex).trim();

        Deadline deadline = new Deadline(description, by);
        TASKS.add(deadline);

        return deadline;
    }

    private static Task addEvent(String[] taskArgs) throws EmptyDescriptionException {
        String fromDelim = "/from";
        String toDelim = "/to";

        String argsStr = String.join(" ", taskArgs);
        int fromIndex = argsStr.indexOf(fromDelim);
        int toIndex = argsStr.indexOf(toDelim);

        String from = argsStr.substring(fromIndex + fromDelim.length(), toIndex).trim();
        String to = argsStr.substring(toIndex + toDelim.length()).trim();
        String description = argsStr.substring(0, fromIndex).trim();

        Event event = new Event(description, from, to);
        TASKS.add(event);

        return event;
    }

    private static String handleAddTask(String[] args) {
        String taskType = args[0];
        String[] taskArgs = Arrays.copyOfRange(args, 1, args.length);

        Task task;
        try {
            switch (taskType) {
            case "todo":
                task = addTodo(taskArgs);
                break;
            case "deadline":
                task = addDeadline(taskArgs);
                break;
            case "event":
                task = addEvent(taskArgs);
                break;
            default:
                return "";
            }
        } catch (EmptyDescriptionException ede) {
            return ede.toString();
        }

        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                task, TASKS.size());
    }

    private static String handleDeleteTask(String[] args) {
        int taskNum = Integer.parseInt(args[1]);
        Task task = TASKS.get(taskNum - 1);
        TASKS.remove(taskNum);

        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                task, TASKS.size());
    }

    private static void handleCommands() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            String[] args = userInput.split(" ");
            String command = args[0];

            String commandOutput;
            switch (command) {
            case "list":
                commandOutput = handleList();
                break;
            case "bye":
                return;
            case "mark":
                // Fallthrough
            case "unmark":
                commandOutput = handleMarkUnMark(args);
                break;
            case "todo":
            case "deadline":
            case "event":
                commandOutput = handleAddTask(args);
                break;
            case "delete":
                commandOutput = handleDeleteTask(args);
                break;
            default:
                commandOutput = "OOPS!!! I don't understand that command, try again later.";
                break;
            }

            System.out.println(getBorder());
            System.out.println(commandOutput);
            System.out.println(getBorder());
        }
    }

    public static void main(String[] args) {
        userGreeting();

        handleCommands();

        userFarewell();
    }
}

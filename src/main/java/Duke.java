import exceptions.tasks.EmptyDescriptionException;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String NAME = "Arctic";
    private static final Integer BORDER_LEN = 60;
    private static final ArrayList<Task> TASKS = new ArrayList<>();

    private static String duplicateChar(Character c, Integer num) {
        return String.valueOf(c).repeat(num);
    }

    private static String getBorder() {
        return duplicateChar('_', BORDER_LEN);
    }

    private static void userGreeting () {
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

    private static void printCommandOutput(String output) {
        System.out.println(getBorder());
        System.out.printf(output);
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
            return String.format("Nice! I've marked this task as done:\n  %s", task.toString());
        } else {
            task.unmark();
            return String.format("OK, I've marked this task as not done yet:\n  %s", task.toString());
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

        Event event = new Event(description, from , to);
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

        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.", task.toString(), TASKS.size());
    }

    private static void handleCommands() {
        Scanner scnr = new Scanner(System.in);
        while (scnr.hasNextLine()) {
            String userInput = scnr.nextLine();
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
                case "unmark":
                    commandOutput = handleMarkUnMark(args);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    commandOutput = handleAddTask(args);
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

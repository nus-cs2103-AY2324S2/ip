import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import storage.Storage;
import utilities.DateTimeUtility;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

public class Duke {
    private final static String indentation = " ".repeat(3);
    private final static String subIndentation = indentation + " ";
    private final static String divider = "_".repeat(60);
    private final static String logo =
            " _               _          \n" + "    | |   _   _  ___| | ___   _ \n"
                    + "    | |  | | | |/ __| |/ / | | |      |\\__/,|   (`\\\n"
                    + "    | |__| |_| | (__|   <| |_| |    _.|o o  |_   ) )\n"
                    + "    |_____\\__,_|\\___|_|\\_\\\\__, |  -(((---(((--------\n"
                    + "                          |___/ ";

    public static void main(String[] args) throws IOException {
        printOutput(logo, "Hello! I'm Lucky the cat", "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks;

        if (new File("src/main/java/storage/data.txt").exists()) {
            tasks = Storage.readFromStorage();
        } else {
            tasks = new ArrayList<>();
        }

        boolean isChatting = true;
        Command command;

        while (isChatting) {
            String[] input = sc.nextLine().trim().split(" ", 2);

            command = Command.parseCommand(input[0]);

            switch (command) {
            case VIEW_LIST:
                printList(tasks);
                break;
            case EXIT:
                exit();
                break;
            case SET_MARK:
                try {
                    updateMarkStatus(true, tasks, input);

                } catch (CommandException e) {
                    printOutput(e.getMessage());
                }
                break;
            case SET_UNMARK:
                try {
                    updateMarkStatus(false, tasks, input);
                } catch (CommandException e) {
                    printOutput(e.getMessage());
                }
                break;
            case INSERT_TODO:
                try {
                    insertToDo(input, tasks);

                } catch (CommandException e) {
                    printOutput(e.getMessage());
                }
                break;
            case INSERT_DEADLINE:
                try {
                    insertDeadline(input, tasks);

                } catch (CommandException e) {
                    printOutput(e.getMessage());
                }
                break;
            case INSERT_EVENT:
                try {
                    insertEvent(input, tasks);

                } catch (CommandException e) {
                    printOutput(e.getMessage());
                }
                break;
            case DELETE_TASK:
                try {
                    deleteTask(tasks, input);
                } catch (CommandException e) {
                    printOutput(e.getMessage());
                }
                break;
            default:
                printOutput("I'm sorry, but I have zero idea what you're asking from me...");
                break;
            }
        }
        sc.close();
    }

    public static void printOutput(String... msg) {
        System.out.println(indentation + divider);

        for (String string : msg) {
            System.out.println(subIndentation + string);
        }
        System.out.println(indentation + divider + "\n");
    }

    public static void printList(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task task : tasks) {
            sb.append(i + "." + task.toString() + "\n" + subIndentation);
            i++;
        }
        printOutput("Here are the tasks in your list:", sb.toString());
    }

    public static void exit() {
        printOutput("Goodbye my friend. See you soon!");
        System.exit(0);
    }

    public static void updateMarkStatus(boolean isMark, ArrayList<Task> tasks, String[] input)
            throws CommandException, IOException {

        if (input.length < 2) {
            throw new CommandException(
                    "Please specify which task. (format: mark/unmark <task no.>)");
        }

        if (!isInteger(input[1])) {
            throw new CommandException("Task number not found! (format: mark/unmark <task no.>)");
        }

        int index = Integer.parseInt(input[1]) - 1;

        // check if index is within bounds
        if (index >= tasks.size()) {
            throw new CommandException("Task not found!");
        }

        if (isMark) {
            // check if there's no change in status
            if (tasks.get(index).getStatus()) {
                throw new CommandException(
                        "The task was already marked as done. I'm not changing anything.");
            } else {
                tasks.get(index).setStatus(true);
                printOutput("Nice! I've marked this task as done:", tasks.get(index).toString());
            }
        } else {
            // check if there's no change in status
            if (!tasks.get(index).getStatus()) {
                throw new CommandException(
                        "The task you're unmarking was not marked to begin with... I'm not changing anything.");
            } else {
                tasks.get(index).setStatus(false);
                printOutput("OK, I've marked this task as not done yet: ",
                        tasks.get(index).toString());
            }
        }

        Storage.writeToStorage(tasks);
    }

    public static void insertToDo(String[] input, ArrayList<Task> tasks)
            throws CommandException, IOException {

        if (input.length < 2) {
            throw new CommandException(
                    "Please add the task description. (format: todo <task description>)");
        }

        ToDo todoTask = new ToDo(input[1]);
        tasks.add(todoTask);

        Storage.writeToStorage(todoTask);

        printOutput("Got it. I've added this task:", indentation + todoTask.toString(),
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void insertDeadline(String[] input, ArrayList<Task> tasks)
            throws CommandException, IOException {

        String pattern = "([^/]+)\\s+/by\\s+(\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{4})";
        Pattern regex = Pattern.compile(pattern);

        if (input.length < 2) {
            throw new CommandException(
                    "Please enter the deadline details! (format: deadline <your task> /by <dd/MM/yyyy HHmm>)");
        }

        Matcher matcher = regex.matcher(input[1]);

        if (!matcher.matches()) {
            throw new CommandException(
                    "Wrong format! (format: deadline <your task> /by <dd/MM/yyyy HHmm>)");
        }

        String[] deadlineDetails = input[1].split("/by");

        if (!DateTimeUtility.isValidDateTime(deadlineDetails[1])) {
            throw new CommandException(
                    "Datetime is in the wrong format. (format: deadline <your task> /by <dd/MM/yyyy HHmm>)");
        }

        Deadline deadlineTask = new Deadline(deadlineDetails[0].trim(),
                DateTimeUtility.parseDateTime(deadlineDetails[1].trim()));

        tasks.add(deadlineTask);

        Storage.writeToStorage(deadlineTask);

        printOutput("Got it. I've added this task:", indentation + deadlineTask.toString(),
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void insertEvent(String[] input, ArrayList<Task> tasks)
            throws CommandException, IOException {

        String pattern =
                "([^/]+)\\s+/from\\s+(\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{4})\\s+/to\\s+(\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{4})";

        Pattern regex = Pattern.compile(pattern);

        // check if it doesnt follow the format of event <some string> /from <some
        // string> /to <some string>
        if (input.length < 2) {
            throw new CommandException(
                    "Please enter the event details! (format: event <your task> /from <dd/MM/yyyy HHmm> /to <dd/MM/yyyy HHmm>)");
        }

        Matcher matcher = regex.matcher(input[1]);

        if (!matcher.matches()) {
            throw new CommandException(
                    "Wrong format! (format: event <your task> /from <dd/MM/yyyy HHmm> /to <dd/MM/yyyy HHmm>)");
        }

        String[] eventDetails = input[1].split("/from|/to");

        if (!DateTimeUtility.isValidDateTime(eventDetails[1], eventDetails[2])) {
            throw new CommandException(
                    "Datetime is in the wrong format. (format: event <your task> /from <dd/MM/yyyy HHmm> /to <dd/MM/yyyy HHmm>)");
        }

        Event eventTask = new Event(eventDetails[0].trim(),
                DateTimeUtility.parseDateTime(eventDetails[1].trim()),
                DateTimeUtility.parseDateTime(eventDetails[2].trim()));
        tasks.add(eventTask);

        Storage.writeToStorage(eventTask);

        printOutput("Got it. I've added this task:", indentation + eventTask.toString(),
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void deleteTask(ArrayList<Task> tasks, String[] input)
            throws CommandException, IOException {
        if (input.length < 2) {
            throw new CommandException(
                    "Please specify which task to delete. (format: delete <task no.>)");
        }

        printOutput("Noted. I've removed this task: ",
                tasks.get(Integer.parseInt(input[1]) - 1).toString(),
                "Now you have " + tasks.size() + " tasks in the list.");

        tasks.remove(Integer.parseInt(input[1]) - 1);

        Storage.writeToStorage(tasks);
    }


    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import exceptions.DukeException;
import exceptions.tasks.EmptyDescriptionException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Duke {
    private static final Path TASKS_FILE_PATH = Paths.get(".", "data", "tasks.save");
    private static final String ARG_DELIMITER = "\u241f";

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
        int taskIndex = taskNum - 1;
        Task task = TASKS.get(taskIndex);

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

        String byStr = argsStr.substring(byIndex + byDelim.length()).trim();
        LocalDate by = LocalDate.parse(byStr);

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

        String fromStr = argsStr.substring(fromIndex + fromDelim.length(), toIndex).trim();
        String toStr = argsStr.substring(toIndex + toDelim.length()).trim();

        LocalDate from = LocalDate.parse(fromStr);
        LocalDate to = LocalDate.parse(toStr);
        String description = argsStr.substring(0, fromIndex).trim();

        Event event = new Event(description, from, to);
        TASKS.add(event);

        return event;
    }

    private static String handleAddTask(String[] args) throws DukeException {
        String taskType = args[0];
        String[] taskArgs = Arrays.copyOfRange(args, 1, args.length);

        Task task;
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

        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                task, TASKS.size());
    }

    private static String handleDeleteTask(String[] args) {
        int taskNum = Integer.parseInt(args[1]);
        int taskIndex = taskNum - 1;
        Task task = TASKS.get(taskIndex);
        TASKS.remove(taskIndex);

        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                task, TASKS.size());
    }

    private static void handleCommands() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String commandOutput = "";

            try {
                String userInput = scanner.nextLine();
                String[] args = userInput.split(" ");
                String command = args[0];

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
            } catch (DukeException e) {
                commandOutput = e.toString();
            }

            System.out.println(getBorder());
            System.out.println(commandOutput);
            System.out.println(getBorder());

            saveTasksToFile();
        }
    }

    private static void saveTasksToFile() {
        try {
            ArrayList<String> tasksData = new ArrayList<>();

            Files.writeString(TASKS_FILE_PATH, "");

            for (Task t : TASKS) {
                ArrayList<String> taskArgs = new ArrayList<>();

                if (t instanceof Deadline) {
                    Deadline deadLine = (Deadline) t;

                    taskArgs.add("deadline");
                    taskArgs.add(deadLine.getDescription());
                    taskArgs.add(deadLine.getBy().toString());
                } else if (t instanceof Event) {
                    Event event = (Event) t;

                    taskArgs.add("event");
                    taskArgs.add(event.getDescription());
                    taskArgs.add(event.getFrom().toString());
                    taskArgs.add(event.getTo().toString());
                } else if (t instanceof Todo) {
                    Todo todo = (Todo) t;

                    taskArgs.add("todo");
                    taskArgs.add(todo.getDescription());
                }

                taskArgs.add(t.getIsDone() ? "1" : "0");

                tasksData.add(String.join(ARG_DELIMITER, taskArgs));
            }

            Files.writeString(TASKS_FILE_PATH, String.join("\n", tasksData));
        } catch (IOException ioException) {
            System.out.println("Failed to save tasks to file.");
        }
    }

    private static void createTasksFileIfDontExist() {
        try {
            // Create tasks file if it doesn't exist
            if (!Files.exists(TASKS_FILE_PATH)) {
                Path parentDir = TASKS_FILE_PATH.getParent();

                // Create parent directory if it doesn't exist
                if (!Files.exists(parentDir)) {
                    Files.createDirectories(parentDir);
                }

                // Create tasks file
                Files.createFile(TASKS_FILE_PATH);
            }
        } catch (IOException ioException) {
            System.out.println("Failed to create tasks file.");
        }
    }

    private static void loadTasksFromFile() {
        try {
            String fileContent = Files.readString(TASKS_FILE_PATH);
            String[] fileContentSplit = fileContent.split("\n");

            for (String taskArgsStr : fileContentSplit) {
                String[] taskArgs = taskArgsStr.split(ARG_DELIMITER);
                String taskType = taskArgs[0];

                Task task;
                String description;
                String isDoneStr;
                boolean isDone;

                switch (taskType) {
                case "deadline": {
                    description = taskArgs[1];
                    String byStr = taskArgs[2];
                    LocalDate by = LocalDate.parse(byStr);
                    isDoneStr = taskArgs[3];
                    isDone = isDoneStr.equals("1");

                    task = new Deadline(description, isDone, by);
                    break;
                }
                case "event": {
                    description = taskArgs[1];
                    String fromStr = taskArgs[2];
                    String toStr = taskArgs[3];
                    LocalDate from = LocalDate.parse(fromStr);
                    LocalDate to = LocalDate.parse(toStr);
                    isDoneStr = taskArgs[4];
                    isDone = isDoneStr.equals("1");

                    task = new Event(description, isDone, from, to);
                    break;
                }
                case "todo": {
                    description = taskArgs[1];
                    isDoneStr = taskArgs[2];
                    isDone = isDoneStr.equals("1");

                    task = new Todo(description, isDone);
                    break;
                }
                default:
                    continue;
                }

                TASKS.add(task);
            }

        } catch (DukeException | IOException exception) {
            System.out.println("Failed to load tasks file.");
        }
    }

    public static void main(String[] args) throws java.io.IOException {
        userGreeting();

        createTasksFileIfDontExist();
        loadTasksFromFile();

        handleCommands();

        userFarewell();
    }
}

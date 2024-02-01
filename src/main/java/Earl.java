import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Earl {

    private static final String PADDING = " ".repeat(4);
    private static final List<Task> tasks = new ArrayList<>();

    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter
            .ofPattern("dd/MM/yyyy HHmm");

    private static void printDivider() {
        System.out.println(PADDING + "_".repeat(50));
    }

    private static void makeResponse(String... arr) {
        printDivider();
        for (String s : arr) {
            System.out.println(PADDING + s);
        }
        printDivider();
    }

    private static void loadTasks() throws EarlException {
        File file = new File("./data/earl.txt");
        boolean isFolderMade = file.getParentFile().mkdirs();
        Scanner sc;
        try {
            boolean isFileMade = file.createNewFile();
            if (isFolderMade || isFileMade) {
                throw new EarlException("Storage file missing... "
                        + "creating new file.");
            }
            sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] task = sc.nextLine().split(",");
                switch (task[0]) {
                case "T":
                    tasks.add(new Todo(task[1], task[2]));
                    break;
                case "D":
                    tasks.add(new Deadline(task[1], task[2],
                            LocalDateTime.parse(task[3], DATETIME_FORMAT)));
                    break;
                case "E":
                    tasks.add(new Event(task[1], task[2],
                            LocalDateTime.parse(task[3], DATETIME_FORMAT),
                            LocalDateTime.parse(task[4], DATETIME_FORMAT)));
                    break;
                default:
                    tasks.clear();
                    throw new EarlException("Storage file is corrupted... "
                            + "starting with empty list.");
                }
            }
        } catch (EarlException e) {
            throw e;
        } catch (Exception e) {
            throw new EarlException("Unknown exception occurred "
                    + "when attempting to create or access "
                    + "storage file: " + e.getMessage());
        }
    }

    private static void saveTasks() throws EarlException {
        try (FileWriter fw = new FileWriter("./data/earl.txt")) {
            for (Task task : tasks) {
                fw.write(task.toStorageString() + "\n");
            }
        } catch (IOException e) {
            throw new EarlException("Fatal error while saving to storage.");
        }
    }

    private static void handleCommand(String input) throws EarlException {
        String[] command = input.split("\\s", 2);
        switch (command[0]) {
        case "list":
            if (!tasks.isEmpty()) {
                String[] temp = new String[tasks.size()];
                for (int i = 0; i < tasks.size(); ++i) {
                    temp[i] = i + 1 + "." + tasks.get(i);
                }
                makeResponse(temp);
            } else {
                makeResponse("There is nothing to list.");
            }
            break;
        case "mark": {
            try {
                int idx = Integer.parseInt(command[1]) - 1;
                if (tasks.get(idx).markAsDone()) {
                    makeResponse("Item marked as done.",
                            PADDING + tasks.get(idx));
                } else {
                    makeResponse("Item already marked as done.");
                }
                break;
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new EarlException(
                        "Error, not a valid item number within range.\n"
                                + PADDING + "Example use:\n"
                                + PADDING + PADDING + "mark 3");
            } catch (Exception e) {
                throw new EarlException("Error, unknown use of unmark.\n"
                        + PADDING + "Example use:\n"
                        + PADDING + PADDING + "mark 3");
            }
        }
        case "unmark": {
            try {
                int idx = Integer.parseInt(command[1]) - 1;
                if (tasks.get(idx).markUndone()) {
                    makeResponse("Item marked as not done.",
                            PADDING + tasks.get(idx));
                } else {
                    makeResponse("Item already marked as not done.");
                }
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new EarlException(
                        "Error, not a valid item number within range.\n"
                                + PADDING + "Example use:\n"
                                + PADDING + PADDING + "unmark 3");
            } catch (Exception e) {
                throw new EarlException(
                        "Error, unknown use of unmark.\n"
                                + PADDING + "Example use:\n"
                                + PADDING + PADDING + "unmark 3");
            }
            break;
        }
        case "todo":
            try {
                tasks.add(new Todo(command[1]));
                makeResponse("Added new todo.",
                        PADDING + tasks.get(tasks.size() - 1),
                        "There are " + tasks.size() + " tasks tracked.");
            } catch (IndexOutOfBoundsException e) {
                throw new EarlException("Error, missing task name.\n"
                        + PADDING + "Example use:\n"
                        + PADDING + PADDING + "todo <task_name>");
            } catch (Exception e) {
                throw new EarlException("Error, unknown use of todo.\n"
                        + PADDING + "Example use:\n"
                        + PADDING + PADDING + "todo <task_name>");
            }
            break;
        case "deadline":
            try {
                command = command[1].split("\\s/by\\s");
                tasks.add(new Deadline(command[0], LocalDateTime.parse(
                        command[1], DATETIME_FORMAT)));
                makeResponse("Added new deadline.",
                        PADDING + tasks.get(tasks.size() - 1),
                        "There are " + tasks.size() + " tasks tracked.");
            } catch (IndexOutOfBoundsException e) {
                throw new EarlException(
                        "Error, invalid deadline format.\n"
                                + PADDING + "Example use:\n"
                                + PADDING + PADDING
                                + "deadline <task_name> /by <end>");
            } catch (Exception e) {
                throw new EarlException(
                        "Error, unknown use of deadline.\n"
                                + PADDING + "Example use:\n"
                                + PADDING + PADDING
                                + "deadline <task_name> /by <end>");
            }
            break;
        case "event":
            try {
                command = command[1].split("\\s/(from|to)\\s");
                tasks.add(new Event(command[0],
                        LocalDateTime.parse(command[1], DATETIME_FORMAT),
                        LocalDateTime.parse(command[2], DATETIME_FORMAT)));
                makeResponse("Added new event.",
                        PADDING + tasks.get(tasks.size() - 1),
                        "There are " + tasks.size() + " tasks tracked.");
            } catch (IndexOutOfBoundsException e) {
                throw new EarlException("Error, invalid event format.\n"
                        + PADDING + "Example use:\n"
                        + PADDING + PADDING
                        + "event <task_name> /from <start> /to <end>");
            } catch (Exception e) {
                throw new EarlException("Error, unknown use of event.\n"
                        + PADDING + "Example use:\n"
                        + PADDING + PADDING
                        + "event <task_name> /from <start> /to <end>");
            }
            break;
        case "delete": {
            try {
                int idx = Integer.parseInt(command[1]) - 1;
                makeResponse("Item deleted.",
                        PADDING + tasks.remove(idx));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new EarlException(
                        "Error, not a valid item number within range.\n"
                                + PADDING + "Example use:\n"
                                + PADDING + PADDING + "unmark 3");
            } catch (Exception e) {
                throw new EarlException(
                        "Error, unknown use of delete.\n"
                                + PADDING + "Example use:\n"
                                + PADDING + PADDING + "delete 3");
            }
            break;
        }
        default:
            makeResponse("Error, unknown command.",
                    "Valid commands:",
                    PADDING + "list, mark, unmark, todo, deadline, event");
            break;
        }
    }

    public static void main(String[] args) {
        try {
            loadTasks();
        } catch (EarlException e) {
            makeResponse(e.getMessage());
        }
        Scanner sc = new Scanner(System.in);
        makeResponse("Hello! I'm Earl", "What can I do for you?");

        // main loop
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                handleCommand(input);
            } catch (EarlException e) {
                makeResponse(e.getMessage());
            }
            input = sc.nextLine();
        }

        try {
            saveTasks();
        } catch (EarlException e) {
            makeResponse(e.getMessage());
        }

        makeResponse("Goodbye! See you soon.");
        sc.close();
    }
}
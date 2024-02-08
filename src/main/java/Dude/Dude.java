package Dude;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Dude {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList tasks;
        try {
            tasks = new TaskList(TaskStorage.loadTasksFromFile());
        } catch (IOException e) {
            tasks = new TaskList(new ArrayList<>());
        }
        Ui ui = new Ui();
        ui.showMessage("Loaded " + tasks.size() + " tasks from file.");
        ui.showWelcome();

        boolean running = true;

        while (running) {
            try {
                String input = scanner.nextLine();
                Command cmd = Parser.getCommand(input);
                int index;

                switch (cmd.action){
                    case BYE:
                        ui.showGoodbye();
                        scanner.close();
                        running = false;
                        break;
                    case LIST:
                        ui.showTaskList(tasks.toArrayList());
                        break;
                    case DONE:
                        index = Parser.getDoneIndex(input);

                        if (index < 0 || index >= tasks.size()) {
                            throw new IndexOutOfBoundsException("Task number does not exist.");
                        }
                        tasks.get(index).markAsDone();
                        ui.showDone(tasks.get(index));
                        break;
                    case UNDONE:
                        index = Parser.getDoneIndex(input);

                        if (index < 0 || index >= tasks.size()) {
                            throw new IndexOutOfBoundsException("Task number does not exist.");
                        }
                        tasks.get(index).markAsNotDone();
                        ui.showUndone(tasks.get(index));
                    case DELETE:
                        index = Parser.getDeleteIndex(input);
                        if (index < 0 || index >= tasks.size()) {
                            throw new IndexOutOfBoundsException("Task number does not exist.");
                        }
                        Task removedTask = tasks.remove(index);
                        ui.showDelete(removedTask);
                        break;
                    case TODO:
                        ToDo todo = new ToDo(cmd.info);
                        tasks.add(todo);
                        ui.showAddTask(todo);
                        break;
                    case DEADLINE:
                        if (!cmd.info.contains(" /by ")) {
                            throw new IllegalArgumentException(
                                    "Invalid deadline format. Use '/by' to specify the deadline.");
                        }
                        String[] deadlineParts = cmd.info.split(" /by ", 2);
                        Deadline deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                        tasks.add(deadline);
                        ui.showAddTask(deadline);
                        break;
                    case EVENT:
                        if (!cmd.info.contains(" /from ") || !cmd.info.contains(" /to ")) {
                            throw new IllegalArgumentException(
                                    "Invalid event format. Use '/from' and '/to' to specify the event times.");
                        }
                        String[] eventParts = cmd.info.split(" /from ", 2);
                        String[] eventTimes = eventParts[1].split(" /to ", 2);
                        Event newEvent = new Event(eventParts[0], eventTimes[0], eventTimes[1]);
                        tasks.add(newEvent);
                        ui.showAddTask(newEvent);
                        break;
                }

                TaskStorage.saveTasksToFile(tasks.toArrayList());
            } catch (NumberFormatException e) {
                ui.showError("Please enter a valid task number.");
            } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("An unexpected error occurred: " + e.getMessage());
            }
        }

    }
}

// Base Task class
abstract class Task {
    String description;
    boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;

    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public abstract String toString();
}

// ToDo subclass
class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + (isDone ? "[X] " : "[ ] ") + description;
    }
}

//Deadline subclass
class Deadline extends Task {
    LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = LocalDate.parse(by, formatter);
        // Parse the string to a LocalDate
    }

    @Override
    public String toString() {
        // Define a formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
        // Format the LocalDate to the desired format
        String formattedDate = by.format(formatter);
        return "[D]" + (isDone ? "[X] " : "[ ] ") + description + " (by: " + formattedDate + ")";
    }


}

// Event subclass
class Event extends Task {
    LocalDate start;
    LocalDate end;

    public Event(String description, String start, String end) {
        super(description);
        // Parse the strings to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.start = LocalDate.parse(start, formatter);
        this.end = LocalDate.parse(end, formatter);
    }

    @Override
    public String toString() {
        // Define a formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
        // Format the LocalDate to the desired format
        String formattedStart = start.format(formatter);
        String formattedEnd = end.format(formatter);
        return "[E]" + (isDone ? "[X] " : "[ ] ") + description + " (from: " + formattedStart + " to: " + formattedEnd
                + ")";
    }
}


class TaskStorage {
    public static final String FILE_NAME = "tasks.txt";

    public static void saveTasksToFile(ArrayList<Task> tasks) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME));
        for (Task task : tasks) {
            String taskString = taskToFileString(task);
            if (!taskString.isEmpty()) {
                bufferedWriter.write(taskString);
                bufferedWriter.newLine();
            }
        }

    }

    public static ArrayList<Task> loadTasksFromFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            Task task = taskFromString(line);
            if (task != null) {
                tasks.add(task);
            }
        }

        return tasks;
    }

    private static String taskToFileString(Task task) {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (task instanceof ToDo) {
            sb.append("T|").append(task.isDone ? "1" : "0").append("|").append(task.description);
        } else if (task instanceof Deadline deadline) {
            String byDate = deadline.by.format(formatter);
            sb.append("D|").append(task.isDone ? "1" : "0").append("|").append(task.description).append("|").append(byDate);
        } else if (task instanceof Event event) {
            String startDate = event.start.format(formatter);
            String endDate = event.end.format(formatter);
            sb.append("E|").append(task.isDone ? "1" : "0").append("|").append(task.description).append("|").append(startDate).append("|").append(endDate);
        }
        return sb.toString();
    }

    private static Task taskFromString(String line) {
        String[] parts = line.split("\\|");
        boolean isDone = "1".equals(parts[1]);
        String description = parts[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            switch (parts[0]) {
            case "T":
                ToDo todo = new ToDo(description);
                if (isDone) todo.markAsDone();
                return todo;
            case "D":
                Deadline deadline = new Deadline(description, parts[3]);
                if (isDone) deadline.markAsDone();
                return deadline;
            case "E":
                Event event = new Event(description, parts[3], parts[4]);
                if (isDone) event.markAsDone();
                return event;
            default:
                return null;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
            return null;
        }
    }
}

class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Dude\nWhat can I do for you?");
    }

    public void showAddTask(Event event){
        System.out.println("Added Event: " + event);
    }

    public void showAddTask(Deadline deadline) {
        System.out.println("Added Deadline: " + deadline);
    }

    public void showAddTask(ToDo todo) {
        System.out.println("Added Todo: " + todo);
    }

    public void showDelete(Task task) {
        System.out.println("Deleted: "  + task);
    }

    public void showDone(Task task) {
        System.out.println("Marked as Done: " + task);
    }

    public void showUndone(Task task) {
        System.out.println("Marked as Not Done: " + task);
    }


    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showTaskList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
    public void closeScanner() {
        scanner.close();
    }
}

enum Actions {
    DELETE,
    ADD,
    DONE,
    UNDONE,
    EVENT,
    DEADLINE,
    TODO,
    LIST,
    BYE
}

class Command {
    public Actions action;

    public String info;

    public Command(Actions action, String info) {
        this.action = action;
        this.info = info;
    }

    public Command(Actions action) {
        this.action = action;
    }

}

 class Parser {

    public static Command getCommand(String input) {
        if (Parser.isBye(input)) {
            return new Command(Actions.BYE);
        }
        if (Parser.isList(input)) {
            return new Command(Actions.LIST);
        }
        if (input.startsWith("done ")) {
            return new Command(Actions.DONE);
        }
        if (input.startsWith("undo ")) {
            return new Command(Actions.UNDONE);
        }
        if (input.startsWith("delete ")) {
            return new Command(Actions.DELETE);
        }

        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String taskInfo = parts.length > 1 ? parts[1] : "";

        switch (command.toLowerCase()) {
        case "todo":
            return new Command(Actions.TODO, taskInfo);
         case "deadline":
             return new Command(Actions.DEADLINE, taskInfo);
         case "event":
             return new Command(Actions.EVENT, taskInfo);
         default:
             throw new RuntimeException("Invalid input. Try again!");
        }

    }
    public static boolean isBye(String input) {
        return input.equalsIgnoreCase("bye");
    }


    public static boolean isList(String input) {
        return input.equalsIgnoreCase("list");
    }

    public static int getDoneIndex(String input) {
        return Integer.parseInt(input.substring(5));
    }

    public static int getDeleteIndex(String input) {
        return Integer.parseInt(input.substring(7));
    }

}


class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> startingTasks) {
        this.tasks = startingTasks;
    }
    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public void markAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> toArrayList() {
        return tasks;
    }

}

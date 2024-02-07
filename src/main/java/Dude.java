import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
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
        System.out.println("Loading tasks from file...");
        ArrayList<Task> tasks = TaskStorage.loadTasksFromFile();
        System.out.println("Loaded " + tasks.size() + " tasks from file.");
        System.out.println("Hello! I'm Duddde");
        System.out.println("What can I do for you?");

        while (true) {
            try {
                String input = scanner.nextLine();
                System.out.println(input);

                if (input.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                } else if (input.startsWith("done ")) {
                    int index = Integer.parseInt(input.substring(5));
                    if (index < 0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Task number does not exist.");
                    }
                    tasks.get(index).markAsDone();
                    TaskStorage.saveTasksToFile(tasks);
                    System.out.println("Marked as done: " + tasks.get(index));
                } else if (input.startsWith("undo ")) {
                    int index = Integer.parseInt(input.substring(5));
                    if (index < 0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Task number does not exist.");
                    }
                    tasks.get(index).markAsNotDone();
                    TaskStorage.saveTasksToFile(tasks);
                    System.out.println("Marked as not done: " + tasks.get(index));
                } else if (input.startsWith("delete ")) {
                    int index = Integer.parseInt(input.substring(7));
                    if (index < 0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Task number does not exist.");
                    }
                    Task removedTask = tasks.remove(index);
                    TaskStorage.saveTasksToFile(tasks);
                    System.out.println("Deleted: " + removedTask);

                } else {
                    String[] parts = input.split(" ", 2);
                    String command = parts[0];
                    String taskInfo = parts.length > 1 ? parts[1] : "";

                    switch (command.toLowerCase()) {
                        case "todo":
                            tasks.add(new ToDo(taskInfo));
                            System.out.println("Added ToDo: " + taskInfo);
                            break;
                        case "deadline":
                            if (!taskInfo.contains(" /by ")) {
                                throw new IllegalArgumentException(
                                        "Invalid deadline format. Use '/by' to specify the deadline.");
                            }
                            String[] deadlineParts = taskInfo.split(" /by ", 2);
                            tasks.add(new Deadline(deadlineParts[0], deadlineParts[1]));
                            System.out.println("Added Deadline: " + deadlineParts[0]);
                            break;
                        case "event":
                            if (!taskInfo.contains(" /from ") || !taskInfo.contains(" /to ")) {
                                throw new IllegalArgumentException(
                                        "Invalid event format. Use '/from' and '/to' to specify the event times.");
                            }
                            String[] eventParts = taskInfo.split(" /from ", 2);
                            String[] eventTimes = eventParts[1].split(" /to ", 2);
                            tasks.add(new Event(eventParts[0], eventTimes[0], eventTimes[1]));
                            System.out.println("Added Event: " + eventParts[0]);
                            break;
                        default:
                            System.out.println("Unknown command");
                            break;

                    }
                    TaskStorage.saveTasksToFile(tasks);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid task number.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        scanner.close();
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

// Deadline subclass
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

    public static void saveTasksToFile(ArrayList<Task> tasks) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                String taskString = taskToFileString(task);
                if (!taskString.isEmpty()) {
                    bufferedWriter.write(taskString);
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error while saving tasks to file: " + e.getMessage());
        }
    }

    public static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Task task = taskFromString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    private static String taskToFileString(Task task) {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (task instanceof ToDo) {
            sb.append("T|").append(task.isDone ? "1" : "0").append("|").append(task.description);
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            String byDate = deadline.by.format(formatter);
            sb.append("D|").append(task.isDone ? "1" : "0").append("|").append(task.description).append("|").append(byDate);
        } else if (task instanceof Event) {
            Event event = (Event) task;
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
                    LocalDate by = LocalDate.parse(parts[3], formatter);
                    Deadline deadline = new Deadline(description, parts[3]);
                    if (isDone) deadline.markAsDone();
                    return deadline;
                case "E":
                    LocalDate start = LocalDate.parse(parts[3], formatter);
                    LocalDate end = LocalDate.parse(parts[4], formatter);
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

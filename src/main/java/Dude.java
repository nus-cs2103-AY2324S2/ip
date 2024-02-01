import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Dude {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Loading tasks from file...");
        ArrayList<Task> tasks = TaskStorage.loadTasksFromFile();
        System.out.println("Loaded " + tasks.size() + " tasks from file.");
        System.out.println("Hello! I'm Duddddde");
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
    String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + description + " (by: " + by + ")";
    }
}

// Event subclass
class Event extends Task {
    String start;
    String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + (isDone ? "[X] " : "[ ] ") + description + " (from: " + start + " to: " + end + ")";
    }
}

class TaskStorage {
    public static final String FILE_NAME = "tasks.txt";

    public static void saveTasksToFile(ArrayList<Task> tasks) {
        try (FileWriter fileWriter = new FileWriter(FILE_NAME);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Task task : tasks) {
                bufferedWriter.write(taskToFileString(task));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error while saving tasks to file: " + e.getMessage());
        }
        System.out.println("Saved " + tasks.size() + " tasks from file.");
    }

    public static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                tasks.add(taskFromString(line));
            }
        } catch (IOException e) {
            saveTasksToFile(tasks);
        }
        System.out.println("Loaded " + tasks.size() + " tasks from file.");
        return tasks;
    }

    public static String taskToFileString(Task task) {
        // Convert a task to a string format suitable for saving to the file
        if (task instanceof ToDo) {
            return "T|" + (task.isDone ? "1" : "0") + "|" + task.description;
        } else if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            return "D|" + (task.isDone ? "1" : "0") + "|" + task.description + "|" + deadlineTask.by;
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            return "E|" + (task.isDone ? "1" : "0") + "|" + task.description + "|" + eventTask.start + "|"
                    + eventTask.end;
        } else {
            return ""; // Handle other task types if needed
        }
    }

    public static Task taskFromString(String line) {
        // Convert a string from the file into a Task object
        String[] parts = line.split("\\|");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task;

        if (type.equals("T")) {
            task = new ToDo(description);
        } else if (type.equals("D")) {
            String by = parts[3];
            task = new Deadline(description, by);
        } else if (type.equals("E")) {
            String start = parts[3];
            String end = parts[4];
            task = new Event(description, start, end);
        } else {
            task = null; // Handle other task types if needed
        }

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }
}
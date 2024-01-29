import java.util.ArrayList;
import java.util.Scanner;

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

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (true) {
            try {
                String input = scanner.nextLine();
                // System.out.println(input);

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
                    System.out.println("Marked as done: " + tasks.get(index));
                } else if (input.startsWith("undo ")) {
                    int index = Integer.parseInt(input.substring(5));
                    if (index < 0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Task number does not exist.");
                    }
                    tasks.get(index).markAsNotDone();
                    System.out.println("Marked as not done: " + tasks.get(index));
                } else if (input.startsWith("delete ")) {
                    int index = Integer.parseInt(input.substring(7));
                    if (index < 0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Task number does not exist.");
                    }
                    Task removedTask = tasks.remove(index);
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

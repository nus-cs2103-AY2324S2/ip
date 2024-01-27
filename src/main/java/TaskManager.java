import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void executeCommand(String command) {
        String[] tokens = command.split(" ", 2);

        switch (tokens[0].toLowerCase()) {
            case "list":
                listTasks();
                break;
            case "mark":
                markTask(tokens);
                break;
            case "unmark":
                unmarkTask(tokens);
                break;
            case "bye":
                break;
            default:
                addTask(tokens);
        }
    }

    private void listTasks() {
        System.out.println("____________________________________________________________");
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    private void markTask(String[] tokens) {
        if (tokens.length == 2) {
            int index = Integer.parseInt(tokens[1]);
            if (isValidIndex(index)) {
                tasks.get(index - 1).markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks.get(index - 1));
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("Invalid task index.");
            }
        } else {
            System.out.println("Invalid command. Usage: mark <index>");
        }
    }

    private void unmarkTask(String[] tokens) {
        if (tokens.length == 2) {
            int index = Integer.parseInt(tokens[1]);
            if (isValidIndex(index)) {
                tasks.get(index - 1).markAsUndone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks.get(index - 1));
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("Invalid task index.");
            }
        } else {
            System.out.println("Invalid command. Usage: unmark <index>");
        }
    }

    private void addTask(String[] tokens) {
        try {
            if (tokens.length != 2) {
                throw new IllegalArgumentException("NO! I don't know what is this! Invalid command. Supported taskss: todo, deadline, event");
            }
            String[] taskTokens = tokens;

            String taskType = taskTokens[0].toLowerCase();
            if (taskTokens[1].isEmpty()) {
                throw new IllegalArgumentException("NO! The description of a task cannot be empty.");
            }
            String taskDescription = taskTokens[1].trim();

            switch (taskType) {
                case "todo":
                    if (taskDescription.isEmpty()) {
                        throw new IllegalArgumentException("NO! The description of a todo cannot be empty.");
                    }
                    tasks.add(new Todo(taskDescription));
                    break;
                case "deadline":
                    String[] deadlineTokens = taskDescription.split(" /by ", 2);
                    if (deadlineTokens.length != 2) {
                        throw new IllegalArgumentException("NO! Invalid command. Enter: deadline <description> /by <date/time>");
                    }
                    tasks.add(new Deadline(deadlineTokens[0], deadlineTokens[1]));
                    break;
                case "event":
                    String[] eventTokens = taskDescription.split(" /from ", 2);
                    if (eventTokens.length != 2) {
                        throw new IllegalArgumentException("NO! Invalid command. Enter: event <description> /from <start> /to <end>");
                    }
                    String[] toTokens = eventTokens[1].split(" /to ", 2);
                    if (toTokens.length != 2) {
                        throw new IllegalArgumentException("NO! Invalid command. Enter: event <description> /from <start> /to <end>");
                    }
                    tasks.add(new Event(eventTokens[0], toTokens[0], toTokens[1]));
                    break;
                default:
                    throw new IllegalArgumentException("No! I don't what what is this! Invalid task type. Supported types: todo, deadline, event");
            }

            printTaskAddedMessage(tasks.size());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }

    private void printTaskAddedMessage(int size) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(size - 1));
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}

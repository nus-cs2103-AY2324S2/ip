import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Echo {
    private TaskManager taskManager;

    public Echo() {
        this.taskManager = new TaskManager();
    }

    public static void main(String[] args) {
        Echo echo = new Echo();
        echo.greetUser();
        echo.startConversation();
        endConversation();
    }

    public static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Echo");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void startConversation() {
        Scanner scanner = new Scanner(System.in);
        String userCommand;

        do {
            System.out.print("\n");
            userCommand = scanner.nextLine();
            taskManager.executeCommand(userCommand);
            //echoCommand(userCommand);
        } while (!userCommand.equalsIgnoreCase("bye"));
    }

    public static void endConversation() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void echoCommand(String command) {
        System.out.println("____________________________________________________________");
        System.out.println(command);
        System.out.println("____________________________________________________________");
    }
}

class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}

class TaskManager {
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
            default:
                addTask(command);
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

    private void addTask(String description) {
        if (!description.equals("bye")) {
            tasks.add(new Task(description));
            System.out.println("____________________________________________________________");
            System.out.println("added: " + description);
            System.out.println("____________________________________________________________");
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }
}

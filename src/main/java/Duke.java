import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void messageWithHorizontalLines(String message) {
        System.out.println("____________________________________________________________\n" +
                message + "\n" +
                "____________________________________________________________");
    }

    private static void displayTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            messageWithHorizontalLines("There are no tasks!");
        } else {
            messageWithHorizontalLines("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println(" " + (i + 1) + "." + task);
            }
        }
    }

    private static void markTaskAsDone(ArrayList<Task> tasks, int taskIndex) {
        if (isValidTaskIndex(tasks, taskIndex)) {
            Task task = tasks.get(taskIndex - 1);
            task.markAsDone();
            messageWithHorizontalLines("Nice! I've marked this task as done:\n" + "  " + task);
        } else {
            messageWithHorizontalLines("Invalid task index!");
        }
    }

    private static void markTaskAsUndone(ArrayList<Task> tasks, int taskIndex) {
        if (isValidTaskIndex(tasks, taskIndex)) {
            Task task = tasks.get(taskIndex - 1);
            task.markAsUndone();
            messageWithHorizontalLines("OK, I've marked this task as not done yet:\n" + "  " + task);
        } else {
            messageWithHorizontalLines("Invalid task index!");
        }
    }

    private static boolean isValidTaskIndex(ArrayList<Task> tasks, int taskIndex) {
        return taskIndex >= 1 && taskIndex <= tasks.size();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String botName = "Hammy";
        String welcomeStr = " Hello! I'm " + botName + "\n What can I do for you?";
        String byeStr = "Bye. Hope to see you again soon!";
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println(welcomeStr);
        while (true) {
            String userInput = scanner.nextLine();
            String[] words = userInput.split(" ");
            String command = words[0].toLowerCase();

            switch (command) {
                case "bye":
                    messageWithHorizontalLines(byeStr);
                    return;
                case "list":
                    displayTaskList(tasks);
                    break;
                case "done":
                    if (words.length > 1) {
                        int taskIndex = Integer.parseInt(words[1]);
                        markTaskAsDone(tasks, taskIndex);
                    } else {
                        messageWithHorizontalLines("Please provide the task index to mark as done.");
                    }
                    break;
                case "undone":
                    if (words.length > 1) {
                        int taskIndex = Integer.parseInt(words[1]);
                        markTaskAsUndone(tasks, taskIndex);
                    } else {
                        messageWithHorizontalLines("Please provide the task index to mark as undone.");
                    }
                    break;
                case "":
                case " ":
                    messageWithHorizontalLines("Please enter a valid command or task.");
                    break;
                default:
                    Task newTask = new Task(userInput);
                    tasks.add(newTask);
                    messageWithHorizontalLines("Added: " + newTask);
            }
        }
    }
}

class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}

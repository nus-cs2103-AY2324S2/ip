import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final List<Task> tasks = new ArrayList<>();

    private static boolean isValidTaskIndex(int taskIndex) {
        return taskIndex > 0 && taskIndex <= tasks.size();
    }

    public static void main(String[] args) {
        greetUser();
        startChat();
        exitChatbot();
    }

    public static void greetUser() {
        System.out.println("__________________________________________________________\n");
        System.out.println(" | Hello! I'm FICIN");
        System.out.println(" | What can I do for you?");
        System.out.println("__________________________________________________________\n");
    }

    public static void startChat() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            userInput = scanner.nextLine().trim();
            try {
                Parser.processCommand(userInput);
            } catch (DukeException e) {
                System.out.println("__________________________________________________________\n");
                System.out.println(" | OOPS!!! " + e.getMessage());
            }
            System.out.println("__________________________________________________________\n");
        } while (!userInput.equalsIgnoreCase("bye"));

        scanner.close();
    }

    public static void exitChatbot() {
        System.out.println("__________________________________________________________\n");
        System.out.println(" | Bye. Hope to see you again soon!");
        System.out.println("__________________________________________________________\n\n");
    }

    public static void addTask(Task task) {
        tasks.add(task);
        System.out.println("__________________________________________________________\n");
        System.out.println(" | Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" | Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void listTasks() {
        System.out.println("__________________________________________________________\n");
        System.out.println(" | Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
    }

    public static void markTaskAsDone(int taskIndex) {
        if (isValidTaskIndex(taskIndex)) {
            tasks.get(taskIndex - 1).markAsDone();
            System.out.println("__________________________________________________________\n");
            System.out.println(" | Nice! I've marked this task as done:");
            System.out.println("   " + tasks.get(taskIndex - 1));
        } else {
            System.out.println("__________________________________________________________\n");
            System.out.println(" | Invalid task number. Please try again.");
        }
    }

    public static void unmarkTask(int taskIndex) {
        if (isValidTaskIndex(taskIndex)) {
            tasks.get(taskIndex - 1).unmarkAsDone();
            System.out.println("__________________________________________________________\n");
            System.out.println(" | OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks.get(taskIndex - 1));
        } else {
            System.out.println("__________________________________________________________\n");
            System.out.println(" | Invalid task number. Please try again.");
        }
    }

    public static void deleteTask(int taskIndex) {
        if (isValidTaskIndex(taskIndex)) {
            Task removedTask = tasks.remove(taskIndex - 1);
            System.out.println("____________________________________________________________\n");
            System.out.println(" | Noted. I've removed this task:");
            System.out.println("   " + removedTask);
            System.out.println(" | Now you have " + tasks.size() + " tasks in the list.");
        } else {
            System.out.println("____________________________________________________________\n");
            System.out.println(" | Invalid task number. Please try again.");
        }
    }
}

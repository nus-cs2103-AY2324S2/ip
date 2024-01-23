import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Skyler {
    private static List<Task> tasks = new ArrayList<>();
    private static int taskIdCounter = 1;

    public static void main(String[] args) {
        String chatbotName = "Skyler";
        String line = "------------------------------------------------------------";

        System.out.println("   /\\_/\\");
        System.out.println("  ( o.o ) Hello! I'm " + chatbotName);
        System.out.println("   > ^ < What can I do for you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            System.out.println(line);

            if (userInput.equals("bye")) {
                System.out.println("Skyler: Bye. Hope to see you again soon!");
                System.out.println(line);
                break; // Exit the loop when "bye" is entered
            } else if (userInput.equals("list")) {
                listTasks();
            } else if (userInput.startsWith("mark")) {
                markTask(userInput);
            } else if (userInput.startsWith("unmark")) {
                unmarkTask(userInput);
            } else {
                addTask(userInput);
            }
        }

        scanner.close();
    }

    private static void addTask(String taskDescription) {
        Task newTask = new Task(taskDescription);
        tasks.add(newTask);
        System.out.println("Skyler: added: " + newTask.getDescription());
    }

    private static void listTasks() {
        System.out.println("Skyler: Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(" " + (i + 1) + "." + task.getStatusIcon() + " " + task.getDescription());
        }
        System.out.println("------------------------------------------------------------");
    }

    private static void markTask(String userInput) {
        try {
            int taskId = Integer.parseInt(userInput.split(" ")[1]);
            if (isValidTaskId(taskId)) {
                Task task = tasks.get(taskId - 1);
                task.markAsDone();
                System.out.println("Skyler: Nice! I've marked this task as done:");
                System.out.println("  " + task.getStatusIcon() + " " + task.getDescription());
                System.out.println("------------------------------------------------------------");
            } else {
                System.out.println("Skyler: Invalid task number. Please provide a valid task number.");
                System.out.println("------------------------------------------------------------");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Skyler: Invalid command. Please use 'mark <task number>'.");
            System.out.println("------------------------------------------------------------");
        }
    }

    private static void unmarkTask(String userInput) {
        try {
            int taskId = Integer.parseInt(userInput.split(" ")[1]);
            if (isValidTaskId(taskId)) {
                Task task = tasks.get(taskId - 1);
                task.markAsUndone();
                System.out.println("Skyler: OK, I've marked this task as not done yet:");
                System.out.println("  " + task.getStatusIcon() + " " + task.getDescription());
                System.out.println("------------------------------------------------------------");
            } else {
                System.out.println("Skyler: Invalid task number. Please provide a valid task number.");
                System.out.println("------------------------------------------------------------");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Skyler: Invalid command. Please use 'unmark <task number>'.");
            System.out.println("------------------------------------------------------------");
        }
    }

    private static boolean isValidTaskId(int taskId) {
        return taskId > 0 && taskId <= tasks.size();
    }
}
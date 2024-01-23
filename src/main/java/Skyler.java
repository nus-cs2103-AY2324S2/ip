import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Skyler {
    private static List<Task> tasks = new ArrayList<>();
    private static String line = "------------------------------------------------------------";

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
            } else if (userInput.startsWith("todo")) {
                addTask(new ToDo(userInput.substring(5).trim()));
            } else if (userInput.startsWith("deadline")) {
                addTask(createDeadline(userInput.substring(9).trim()));
            } else if (userInput.startsWith("event")) {
                addTask(createEvent(userInput.substring(6).trim()));
            } else if (userInput.startsWith("mark")) {
                markTask(userInput);
            } else if (userInput.startsWith("unmark")) {
                unmarkTask(userInput);
            } else {
                System.out.println("Skyler: I'm sorry, I don't understand that command.");
                System.out.println(line);
            }
        }

        scanner.close();
    }

    private static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Skyler: Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Skyler: Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(line);
    }

    private static void listTasks() {
        System.out.println("Skyler: Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("------------------------------------------------------------");
    }

    private static Deadline createDeadline(String input) {
        String[] parts = input.split("/by", 2);
        if (parts.length == 2) {
            return new Deadline(parts[0].trim(), parts[1].trim());
        } else {
            System.out.println("Skyler: Invalid deadline format. Please use 'deadline <description> /by <date/time>'.");
            System.out.println(line);
            return null;
        }
    }

    private static Event createEvent(String input) {
        String[] parts = input.split("/from", 2);
        if (parts.length == 2) {
            String[] eventParts = parts[1].split("/to", 2);
            if (eventParts.length == 2) {
                return new Event(parts[0].trim(), eventParts[0].trim(), eventParts[1].trim());
            }
        }
        System.out.println("Skyler: Invalid event format. Please use 'event <description> /from <start> /to <end>'.");
        System.out.println(line);
        return null;
    }

    private static void markTask(String userInput) {
        try {
            int taskId = Integer.parseInt(userInput.split(" ")[1]);
            if (isValidTaskId(taskId)) {
                Task task = tasks.get(taskId - 1);
                task.markAsDone();
                System.out.println("Skyler: Nice! I've marked this task as done:");
                System.out.println("  " + task);
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
                System.out.println("  " + task);
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
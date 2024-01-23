import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Skyler {
    private static List<Task> tasks = new ArrayList<>();

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

            try {
                processUserInput(userInput);
            } catch (SkylerException e) {
                System.out.println("Skyler: Woof, " + e.getMessage());
                System.out.println(line);
            }

            if (userInput.equals("bye")) {
                System.out.println("Skyler: Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
        }

        scanner.close();
    }

    private static void processUserInput(String userInput) throws SkylerException {
        if (userInput.equals("list")) {
            listTasks();
        } else if (userInput.startsWith("todo")) {
            addTask(new ToDo(getTaskDescription(userInput, 4)));
        } else if (userInput.startsWith("deadline")) {
            String description = getTaskDescription(userInput, 9);
            String by = getTaskDetails(userInput, "/by");
            addTask(new Deadline(description, by));
        } else if (userInput.startsWith("event")) {
            String description = getTaskDescription(userInput, 6);
            String from = getTaskDetails(userInput, "/from");
            String to = getTaskDetails(userInput, "/to");
            addTask(new Event(description, from, to));
        } else if (userInput.startsWith("delete")) {
            deleteTask(userInput);
        } else if (userInput.startsWith("mark")) {
            markTask(userInput);
        } else if (userInput.startsWith("unmark")) {
            unmarkTask(userInput);
        } else {
            throw new SkylerException("I'm sorry, I don't understand that command.");
        }
    }

    private static String getTaskDescription(String userInput, int startIndex) throws SkylerException {
        String description = userInput.substring(startIndex).trim();
        if (description.isEmpty()) {
            throw new SkylerException("The description of a task cannot be empty.");
        }
        return description;
    }

    private static String getTaskDetails(String userInput, String keyword) throws SkylerException {
        int index = userInput.indexOf(keyword);
        if (index == -1) {
            throw new SkylerException("Missing " + keyword + " in the command.");
        }
        return userInput.substring(index + keyword.length()).trim();
    }

    private static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Skyler: Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Skyler: Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("------------------------------------------------------------");
    }

    private static void listTasks() {
        System.out.println("Skyler: Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("------------------------------------------------------------");
    }

    private static void deleteTask(String userInput) throws SkylerException {
        try {
            int taskId = Integer.parseInt(userInput.split(" ")[1]);
            if (isValidTaskId(taskId)) {
                Task removedTask = tasks.remove(taskId - 1);
                System.out.println("Skyler: Noted. I've removed this task:");
                System.out.println("  " + removedTask);
                System.out.println("Skyler: Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("------------------------------------------------------------");
            } else {
                throw new SkylerException("Invalid task number. Please provide a valid task number.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new SkylerException("Invalid command. Please use 'delete <task number>'.");
        }
    }

    private static void markTask(String userInput) throws SkylerException {
        try {
            int taskId = Integer.parseInt(userInput.split(" ")[1]);
            if (isValidTaskId(taskId)) {
                Task task = tasks.get(taskId - 1);
                task.markAsDone();
                System.out.println("Skyler: Nice! I've marked this task as done:");
                System.out.println("  " + task);
                System.out.println("------------------------------------------------------------");
            } else {
                throw new SkylerException("Invalid task number. Please provide a valid task number.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new SkylerException("Invalid command. Please use 'mark <task number>'.");
        }
    }

    private static void unmarkTask(String userInput) throws SkylerException {
        try {
            int taskId = Integer.parseInt(userInput.split(" ")[1]);
            if (isValidTaskId(taskId)) {
                Task task = tasks.get(taskId - 1);
                task.markAsUndone();
                System.out.println("Skyler: OK, I've marked this task as not done yet:");
                System.out.println("  " + task);
                System.out.println("------------------------------------------------------------");
            } else {
                throw new SkylerException("Invalid task number. Please provide a valid task number.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new SkylerException("Invalid command. Please use 'unmark <task number>'.");
        }
    }

    private static boolean isValidTaskId(int taskId) {
        return taskId > 0 && taskId <= tasks.size();
    }
}

class SkylerException extends Exception {
    public SkylerException(String message) {
        super(message);
    }
}
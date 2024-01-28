import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Skyler {
    private static final String FILE_PATH = "./data/Skyler.txt";
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasksFromFile();

        String chatbotName = "Skyler";
        String LINE = "------------------------------------------------------------";

        System.out.println("   /\\_/\\");
        System.out.println("  ( o.o ) Hello! I'm " + chatbotName);
        System.out.println("   > ^ < What can I do for you?");
        System.out.println(LINE);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            System.out.println(LINE);

            if (userInput.equals("bye")) {
                System.out.println("Skyler: Bye. Hope to see you again soon!");
                System.out.println(LINE);
                break;
            }

            try {
                processUserInput(userInput);
            } catch (SkylerException e) {
                System.out.println("Skyler: Woof, " + e.getMessage());
                System.out.println(LINE);
            }
        }

        scanner.close();
    }

    private static void processUserInput(String userInput) throws SkylerException {
        if (userInput.equals("list")) {
            listTasks();
        } else if (userInput.startsWith("todo")) {
            addTask(new ToDo(getTaskDescription(userInput, 4), false));
        } else if (userInput.startsWith("deadline")) {
            String[] parts = userInput.split("/by", 2);

            if (parts.length != 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new SkylerException(
                        "Invalid 'deadline' command. Please provide a valid description and deadline.");
            }

            String description = parts[0].substring(9).trim();
            String by = parts[1].trim();

            addTask(new Deadline(description, by, false));
        } else if (userInput.startsWith("event")) {
            String[] parts = userInput.split("/from", 2);

            if (parts.length != 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new SkylerException("Invalid 'event' command. Please provide a valid description and timeframe.");
            }

            String description = parts[0].substring(6).trim();
            String from = parts[1].split("/to")[0].trim();
            String to = parts[1].split("/to")[1].trim();

            addTask(new Event(description, from, to, false));
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

    private static String getTaskDescription(String userInput, int startIndex, String... keywords)
            throws SkylerException {
        String description = userInput.substring(startIndex).trim();
        for (String keyword : keywords) {
            if (description.startsWith(keyword)) {
                description = description.substring(keyword.length()).trim();
                break;
            }
        }
        if (description.isEmpty()) {
            throw new SkylerException("The description of a task cannot be empty.");
        }
        return description;
    }

    private static void loadTasksFromFile() {
        File file = new File(FILE_PATH);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String data = scanner.nextLine();
                Task task = parseTaskFromFile(data);
                tasks.add(task);
            }
            scanner.close();
        } catch (IOException | SkylerException e) {
            System.out.println("An error occurred while loading tasks.");
            e.printStackTrace();
        }
    }

    private static Task parseTaskFromFile(String data) throws SkylerException {
        // Assuming format "[T][ ] read book", "[D][ ] assignment (by: tuesday)", "[E][
        // ] dog sitting (from: sat to: mon)"
        String taskType = data.substring(1, 2); // Extracting task type (T, D, E)
        boolean isDone = data.charAt(4) == 'X'; // Assuming 'x' represents a completed task
        String details = data.substring(7).trim(); // Extracting task details

        switch (taskType) {
            case "T":
                return new ToDo(details, isDone);
            case "D":
                // Assuming format "assignment (by: tuesday)"
                int byIndex = details.indexOf("(by:");
                String descriptionD = details.substring(0, byIndex).trim();
                String by = details.substring(byIndex + 4, details.length() - 1).trim();
                return new Deadline(descriptionD, by, isDone);
            case "E":
                // Assuming format "dog sitting (from: sat to: mon)"
                int fromIndex = details.indexOf("(from:");
                int toIndex = details.indexOf("to:");
                String descriptionE = details.substring(0, fromIndex).trim();
                String from = details.substring(fromIndex + 6, toIndex).trim();
                String to = details.substring(toIndex + 3, details.length() - 1).trim();
                return new Event(descriptionE, from, to, isDone);
            default:
                throw new SkylerException("Unknown task type in the file: " + data);
        }
    }

    private static void saveTasksToFile() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
            e.printStackTrace();
        }
    }

    private static void addTask(Task task) {
        tasks.add(task);
        saveTasksToFile();
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
                saveTasksToFile();
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
                saveTasksToFile();
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
                saveTasksToFile();
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
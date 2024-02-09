import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class SecretaryW {
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    private static final String FILE_PATH = "./data/SecretaryW.txt";

    private static void loadTasksFromFile(ArrayList<Task> taskList) {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNext()) {
                    String taskData = fileScanner.nextLine();
                    Task task = createTaskFromData(taskData);
                    taskList.add(task);
                }
                fileScanner.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    // Save tasks to the file whenever the task list changes
    private static void saveTasksToFile(ArrayList<Task> taskList) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (Task task : taskList) {
                fileWriter.write(task.toFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    // Create a Task object from data read from the file
    private static Task createTaskFromData(String taskData) {
        String[] parts = taskData.split("\\|");
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        if (type.equals("T")) {
            return new Task(TaskType.TODO, description, isDone);
        } else if (type.equals("D")) {
            String by = parts[3].trim();
            return new Task(TaskType.DEADLINE, description, by, isDone);
        } else if (type.equals("E")) {
            String from = parts[3].trim();
            String to = parts[4].trim();
            return new Task(TaskType.EVENT, description, from, to, isDone);
        } else {
            // Handle other types if needed
            return null;
        }
    }
    public static void main(String[] args) {
        // Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // ArrayList to store task items
        ArrayList<Task> taskList = new ArrayList<>();

        // Load tasks from file
        loadTasksFromFile(taskList);

        String greeting = "Whats up, SecretaryW at your service\n" + "How can I help you?\n";
        String farewell = "Bye. Hope to see you again soon!\n";

        // Greetings
        System.out.println(line + greeting + line);

        // Read user input in the loop
        while (true) {
            try {
                String userInput = scanner.nextLine();

                if (userInput.equals("bye")) {
                    break; // Exits loop
                } else if (userInput.equals("list")) {
                    System.out.println(line);
                    if (taskList.isEmpty()) {
                        System.out.println("No tasks available");
                        System.out.println(line);
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskList.size(); i++) {
                            System.out.println(" " + (i + 1) + ". " + taskList.get(i));
                        }
                        System.out.println(line);
                    }
                } else if (userInput.startsWith("mark")) {
                    int index = Integer.parseInt(userInput.substring(5).trim()) - 1;
                    // check for bounds
                    if (index >= 0 && index < taskList.size()) {
                        taskList.get(index).markAsDone();
                        System.out.println(line + " Nice! I've marked this task as done:");
                        System.out.println("  " + taskList.get(index).getStatusIcon() + " " + taskList.get(index).getDescription() + "\n" + line);
                    } else {
                        System.out.println(line + " Index is out of bounds!\n" + line);
                    }
                } else if (userInput.startsWith("unmark")) {
                    int index = Integer.parseInt(userInput.substring(7).trim()) - 1;
                    // check for bounds
                    if (index >= 0 && index < taskList.size()) {
                        taskList.get(index).markAsUndone();
                        System.out.println(line + " OK, I've marked this task as not done yet");
                        System.out.println("  " + taskList.get(index).getStatusIcon() + " " + taskList.get(index).getDescription() + "\n" + line);
                    } else {
                        System.out.println(line + " Index is out of bounds!\n" + line);
                    }
                } else if (userInput.startsWith("delete")){
                    int index = Integer.parseInt(userInput.substring(6).trim()) - 1;
                    // check for bounds
                    if (index >= 0 && index < taskList.size()) {
                        Task removedTask = taskList.remove(index);
                        printTaskDeleted(removedTask, taskList.size());
                    } else {
                        System.out.println(line + " Index is out of bounds!\n" + line);
                    }
                } else if (userInput.startsWith("todo")) {
                    // add to do task
                    String description = userInput.substring(4).trim();
                    checkTodo(description);
                    taskList.add(new Task(TaskType.TODO, description));
                    printTaskAdded(taskList.get(taskList.size() - 1), taskList.size());
                } else if (userInput.startsWith("deadline")) {
                    // Add a deadline task
                    String[] parts = userInput.substring(8).split("/by");
                    checkParts(parts, 2);
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    checkDeadline(by);
                    checkDeadline2(by);
                    taskList.add(new Task(TaskType.DEADLINE, description, by));
                    printTaskAdded(taskList.get(taskList.size() - 1), taskList.size());

                } else if (userInput.startsWith("event")) {
                    // Add an event task
                    String[] parts = userInput.substring(5).split("/from"); // first split
                    checkParts(parts, 2);
                    String description = parts[0].trim();
                    String[] time = parts[1].split("/to"); // second split
                    checkParts(time, 2);
                    String from = time[0].trim();
                    String to = time[1].trim();
                    checkEvent(from, to);
                    checkEvent2(from, to);
                    taskList.add(new Task(TaskType.EVENT, description, from, to));
                    printTaskAdded(taskList.get(taskList.size() - 1), taskList.size());

                } else {
                    // Invalid command
                    throw new WException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (WException e) {
                System.out.println(line + "OOPS!!! " + e.getMessage() + "\n" + line);
            } finally {
                // Save tasks to file whenever tasklist changes
                saveTasksToFile(taskList);
            }
        }
        // Farewell
        System.out.println(line + farewell + line);
        scanner.close();
    }
    private static void printTaskAdded(Task task, int count) {
        System.out.println(line + "Got it. I've added this task:\n" + task);
        System.out.println(" Now you have " + count + " tasks in the list.\n" + line);
    }

    private static void printTaskDeleted(Task task, int count) {
        System.out.println(line + "Noted. I've removed this task:\n" + task);
        System.out.println(" Now you have " + count + " tasks in the list.\n" + line);
    }
    private static String line = "-----------------------------------------------------------------\n";

    static class WException extends Exception {
        public WException(String msg) {
            super(msg);
        }
    }

    private static void checkTodo(String description) throws WException {
        if (description.isEmpty()) {
            throw new WException("The description of a todo cannot be empty");
        }
    }

    private static void checkParts(String[] parts, int length) throws WException {
        if (parts.length != length) {
            throw new WException("Wrong format. Please retype");
        }
    }

    private static void checkDeadline(String by) throws WException {
        if (by.isEmpty()) {
            throw new WException("The deadline cannot be empty");
        }
    }

    private static void checkEvent(String from, String to) throws WException {
        if (from.isEmpty() || to.isEmpty()) {
            throw new WException("The event from and to times cannot be empty");
        }
    }

    private static void checkDeadline2(String by) throws WException {
        try {
            // Attempt to parse the deadline string
            LocalDate.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            throw new WException("Invalid date format provided for deadline task");
        }
    }

    private static void checkEvent2(String from, String to) throws WException {
        try {
            // Attempt to parse the event string
            LocalDate.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy"));
            LocalDate.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            throw new WException("Invalid date format provided for event task");
        }
    }
}
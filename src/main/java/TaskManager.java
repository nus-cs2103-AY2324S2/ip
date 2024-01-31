import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TaskManager {
    private List<Task> tasks;
    private final String FILE_PATH = "." + File.separator + "data" + File.separator + "echo.txt";

    public TaskManager() {
        this.tasks = new ArrayList<>();
        loadTasksFromFile();
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
            case "delete":
                deleteTask(tokens);
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
        saveTasksToFile();
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
        saveTasksToFile();
    }

    public void addTask(String[] tokens) {
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
        saveTasksToFile();
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

    public void deleteTask(String[] tokens) {
        try {
            if (tokens.length != 2) {
                throw new IllegalArgumentException("NO! Invalid command. Enter: delete <task number>");
            }

            int taskNumber = Integer.parseInt(tokens[1]) - 1;  // Adjusting for 0-based index
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new IllegalArgumentException("NO! Task number does not exist. Enter a valid task number to delete.");
            }

            Task removedTask = tasks.remove(taskNumber);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            System.out.println("NO! Invalid task number. Enter a valid task number to delete.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        saveTasksToFile();
    }

    public void addTaskFromFileString(String fileLine) {
        try {
            String[] tokens = fileLine.split(" \\|");
            if (tokens.length <= 1) {
                throw new IllegalArgumentException("Invalid task format in file!");
            }

            String taskType = tokens[0];
            if (tokens[2].isEmpty()) {
                throw new IllegalArgumentException("The description of a task cannot be empty.");
            }
            String taskDescription = tokens[2].trim();

            switch (taskType) {
                case "T":
                    tasks.add(new Todo(taskDescription));
                    break;
                case "D":
                    String[] deadlineTokens = tokens[3].split(" ", 2);
                    if (deadlineTokens.length != 2) {
                        throw new IllegalArgumentException("Invalid deadline format in file.");
                    }
                    tasks.add(new Deadline(taskDescription, deadlineTokens[1]));
                    break;
                case "E":
                    String[] eventTokens = tokens[3].split(" ", 3);
                    if (eventTokens.length != 3) {
                        throw new IllegalArgumentException("Invalid event format in file.");
                    }
                    tasks.add(new Event(taskDescription, eventTokens[1], eventTokens[2]));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid task type in file!");
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    // Method to save tasks to a file
    private void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    // Method to load tasks from a file
    private void loadTasksFromFile() {
        try {
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create parent directories if they don't exist
                file.createNewFile(); // Create the file if it doesn't exist

                System.out.println("No tasks file found. Created a new tasks file.");
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;

                while ((line = reader.readLine()) != null) {
                    try {
                        addTaskFromFileString(line);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error loading a task. Skipping invalid task line: " + line);
                    }
                }

                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());

            // Handle the situation where the file is corrupted
            System.out.println("Deleting the corrupted file and creating a new tasks file.");
            File corruptedFile = new File(FILE_PATH);
            corruptedFile.delete(); // Delete the corrupted file
            loadTasksFromFile(); // Recursively call the method to create a new file
        }
    }

    public void deleteAllTasks() {
        tasks.removeAll(tasks);
        resetFile();
    }

    public void resetFile() {
        try {
            File file = new File(FILE_PATH);

            // Create a new file writer with append mode set to false (clears existing content)
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                // Clear the tasks list
                tasks.clear();
                System.out.println("Tasks list cleared.");

                System.out.println("File content cleared successfully.");
            }

        } catch (IOException e) {
            System.out.println("Error resetting file: " + e.getMessage());
        }
    }
}

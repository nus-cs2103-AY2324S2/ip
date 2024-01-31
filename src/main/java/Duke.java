
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "./data/duke.txt";


    public static void main(String[] args) {
        // Greeting
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duck");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Main chat loop
        while (true) {
            try {
                loadTasksFromFile();
                ensureDataFileExists();
                // Read user input
                String userInput = scanner.nextLine();

                // Process user input
                if (userInput.equalsIgnoreCase("bye")) {
                    // Farewell
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break; // Exit the loop
                } else if (userInput.equalsIgnoreCase("list")) {
                    // Display the list of tasks
                    System.out.println("____________________________________________________________");
                    if (tasks.isEmpty()) {
                        System.out.println(" There are no tasks in your list.");
                    } else {
                        System.out.println(" Here" + (tasks.size() == 1 ? " is the " : " are the ") + tasks.size() +
                                (tasks.size() == 1 ? " task " : " tasks ") + "in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(" " + (i + 1) + "." + tasks.get(i));
                        }
                    }
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("mark")) {
                    // Mark a task as done
                    int taskIndex = parseTaskIndex(userInput);
                    markTaskAsDone(taskIndex);
                } else if (userInput.startsWith("unmark")) {
                    // Mark a task as not done
                    int taskIndex = parseTaskIndex(userInput);
                    unmarkTaskAsDone(taskIndex);
                } else if (userInput.trim().isEmpty()) {
                    throw new DukeException("Please enter an action and a task");
                } else if (userInput.startsWith("delete")) {
                    // Delete a task
                    int taskIndex = parseTaskIndex(userInput);
                    deleteTask(taskIndex);
                } else {
                    // Add the task to the array
                    tasks.add(createTask(userInput));
                    // Display confirmation
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
                    System.out.println("____________________________________________________________");
                }
                saveTasksToFile();
            } catch (DukeException e) {
                // Handle custom Duke exceptions
                System.out.println("____________________________________________________________");
                System.out.println(" " + e.getMessage());
                System.out.println("____________________________________________________________");
            }  catch (DukeDataCorruptedException e) {
                System.out.println("Error: The data file is corrupted. Please check the file format.");
            } catch (FileNotFoundException e) {
                System.out.println("Data file not found. Creating a new one.");
                // Handle the case when the data file is not found, and create a new one
                tasks = new ArrayList<>();
            } catch (IOException e) {
                // Handle IOException (e.g., file-related issues)
                System.out.println("____________________________________________________________");
                System.out.println("Error saving or loading tasks. Please check the file.");
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                // Handle NumberFormatException (e.g., when parsing integers)
                System.out.println("____________________________________________________________");
                System.out.println("Please enter a valid task index.");
                System.out.println("____________________________________________________________");
            }
        }

        // Close the scanner
        scanner.close();
    }

    private static void saveTasksToFile() throws IOException {
        Path filePath = Paths.get(FILE_PATH);

        // Create the parent directory if it doesn't exist
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }

        // Write tasks to the file
        Files.write(filePath, TaskListEncoder.encode(tasks));
    }

    private static void loadTasksFromFile() throws IOException, DukeDataCorruptedException {
        Path filePath = Paths.get(FILE_PATH);

        // Check if the file exists before attempting to load
        if (Files.exists(filePath)) {
            try {
                // Read tasks from the file
                List<String> lines = Files.readAllLines(filePath);
                tasks = TaskListDecoder.decode(lines);
            } catch (FileNotFoundException e) {
                // If the file is not found, throw a more specific exception
                throw new FileNotFoundException("Data file not found");
            } catch (IOException e) {
                throw new IOException("Error reading tasks from the file.", e);
            } catch (DukeDataCorruptedException e) {
                // Rethrow other exceptions, including DukeDataCorruptedException
                throw e;
            }
        } else {
            tasks = new ArrayList<>(); // Initialize with an empty list if the file doesn't exist yet
        }
    }

    private static void ensureDataFileExists() throws IOException {
        Path filePath = Paths.get(FILE_PATH);

        // Check if the file exists or create it
        if (!Files.exists(filePath)) {
            // Check if the parent directory exists, create it if not
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }

            // Create the data file
            Files.createFile(filePath);
        }
    }

    // Delete a task
    private static void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task removedTask = tasks.remove(index);
            System.out.println("____________________________________________________________");
            System.out.println(" Noted. I've removed this task:");
            System.out.println("   " + removedTask);
            System.out.println(" Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid task index. Please enter a valid task index.");
            System.out.println("____________________________________________________________");
        }
    }
    // Parse task index from user input
    private static int parseTaskIndex(String userInput) throws DukeException {
        try {
            // Assuming the input is in the format "mark 2" or "unmark 2"
            String indexString = userInput.split(" ", 2)[1].trim();
            return Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid task index :(");
        }
    }

    // Mark a task as done
    private static void markTaskAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setDone(true);
            System.out.println("____________________________________________________________");
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks.get(index));
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid task index. Please enter a valid task index.");
            System.out.println("____________________________________________________________");
        }
    }

    // Mark a task as not done
    private static void unmarkTaskAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setDone(false);
            System.out.println("____________________________________________________________");
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks.get(index));
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid task index. Please enter a valid task index.");
            System.out.println("____________________________________________________________");
        }
    }

    // Create a task based on user input
    private static Task createTask(String userInput) throws DukeException {
        String[] inputParts = userInput.split(" ", 2);

        if(!(inputParts[0].equals("todo")) && !(inputParts[0].equals("deadline")) && !(inputParts[0].equals("event"))) {
            throw new DukeException("Don't talk nonsense");
        } else if (inputParts.length < 2) {
            throw new DukeException("What do you want to do");
        }

        String taskType = inputParts[0].toLowerCase();

        switch (taskType) {
            case "todo":
                return new Todo(inputParts[1]);
            case "deadline":
                return createDeadlineTask(inputParts[1]);
            case "event":
                return createEventTask(inputParts[1]);
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    // Create a deadline task based on user input
    private static Task createDeadlineTask(String input) throws DukeException{
        String[] parts = input.split("/by", 2);
        if (parts.length < 2) {
            throw new DukeException("Please provide a deadline in the format '/by <deadline>'");
        }
        return new Deadline(parts[0].trim(), parts[1].trim());
    }

    // Create an event task based on user input
    private static Task createEventTask(String input) throws DukeException {
        String[] parts = input.split("/from", 2);
        if (parts.length < 2) {
            throw new DukeException("Please provide an event in the format '/from <start time> /to <end time>'");
        }
        String[] dateParts = parts[1].trim().split("/to", 2);
        return new Event(parts[0].trim(), dateParts[0].trim(), dateParts[1].trim());
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + "[" + (isDone ? "X" : " ") + "] " + description;
    }

    public String getStatusIcon() {
        return " "; // Default to empty space
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return "T"; // Todo tasks have "T" as their status icon
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getStatusIcon() {
        return "D"; // Deadline tasks have "D" as their status icon
    }
    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}

class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getStatusIcon() {
        return "E"; // Event tasks have "E" as their status icon
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
class TaskListEncoder {
    public static List<String> encode(List<Task> tasks) {
        List<String> encodedTasks = new ArrayList<>();
        for (Task task : tasks) {
            encodedTasks.add(encodeTask(task));
        }
        return encodedTasks;
    }

    private static String encodeTask(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            return "D | " + (task.isDone() ? "1" : "0") + " | " + deadlineTask.getDescription() + " | " + deadlineTask.getBy();
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            return "E | " + (task.isDone() ? "1" : "0") + " | " + eventTask.getDescription() + " | " + eventTask.getFrom() + " to " + eventTask.getTo();
        } else {
            // Handle other task types if needed
            return "";
        }
    }
}

class TaskListDecoder {
    public static List<Task> decode(List<String> lines) throws DukeDataCorruptedException {
        List<Task> decodedTasks = new ArrayList<>();
        try {
            for (String line : lines) {
                decodedTasks.add(decodeTask(line));
            }
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            throw new DukeDataCorruptedException("Data file is corrupted: " + e.getMessage());
        }
        return decodedTasks;
    }

    private static Task decodeTask(String line) {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        //boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
            case "T":
                return new Todo(description);
            case "D":
                String by = parts[3];
                return new Deadline(description, by);
            case "E":
                String from = parts[3].split(" to ")[0];
                String to = parts[3].split(" to ")[1];
                return new Event(description, from, to);
            default:
                // Handle other task types if needed
                return null;
        }
    }
}
// Custom DukeException class
class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
class DukeDataCorruptedException extends Exception {
    public DukeDataCorruptedException(String message) {
        super(message);
    }
}


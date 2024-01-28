import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}

enum TaskType {
    TODO,
    DEADLINE,
    EVENT
}

class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getStatusIcon() {
        return "[" + taskType + "]" + (isDone ? "[X] " : "[ ] ");
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.TODO);
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (by: " + by + ")";
    }
}

class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (from: " + from + " to: " + to + ")";
    }
}

public class Duke {
    private static final String DIRECTORY_PATH = "./data/duke.txt";
    private static final String FILE_PATH = DIRECTORY_PATH + File.separator + "duke.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasksFromFile();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm SCZL");
        System.out.println("What can I do for you?");

        while (true) {
            String userInput = scanner.nextLine();

            System.out.println("____________________________________________________________\n");

            try {
                if (userInput.equalsIgnoreCase("bye")) {
                    System.out.println(" Bye. Hope to see you again soon!");
                    break;
                } else if (userInput.equalsIgnoreCase("list")) {
                    listTasks();
                } else {
                    processTaskInput(userInput);
                }
            } catch (DukeException e) {
                System.out.println(" " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void processTaskInput(String userInput) throws DukeException {
        if (userInput.startsWith("todo")) {
            addTodoTask(userInput.substring(5).trim());
        } else if (userInput.startsWith("deadline")) {
            addDeadlineTask(userInput.substring(9).trim());
        } else if (userInput.startsWith("event")) {
            addEventTask(userInput.substring(6).trim());
        } else if (userInput.startsWith("mark")) {
            markTask(userInput);
        } else if (userInput.startsWith("unmark")) {
            unmarkTask(userInput);
        } else if (userInput.startsWith("delete")) {
            deleteTask(userInput);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void addTodoTask(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }

        tasks.add(new Todo(description));
        printTaskAddedMessage(tasks.get(tasks.size() - 1));
        saveTasksToFile();
    }

    private static void addDeadlineTask(String input) throws DukeException {
        int byIndex = input.indexOf("/by");
        if (byIndex != -1) {
            String description = input.substring(0, byIndex).trim();
            String by = input.substring(byIndex + 3).trim();

            if (description.isEmpty() || by.isEmpty()) {
                throw new DukeException("OOPS!!! The description and /by cannot be empty for a deadline.");
            }

            tasks.add(new Deadline(description, by));
            printTaskAddedMessage(tasks.get(tasks.size() - 1));
        } else {
            throw new DukeException("OOPS!!! Invalid deadline command format.");
        }
        saveTasksToFile();
    }

    private static void addEventTask(String input) throws DukeException {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        if (fromIndex != -1 && toIndex != -1) {
            String description = input.substring(0, fromIndex).trim();
            String from = input.substring(fromIndex + 5, toIndex).trim();
            String to = input.substring(toIndex + 3).trim();

            if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new DukeException("OOPS!!! The description, /from, and /to cannot be empty for an event.");
            }

            tasks.add(new Event(description, from, to));
            printTaskAddedMessage(tasks.get(tasks.size() - 1));
        } else {
            throw new DukeException("OOPS!!! Invalid event command format.");
        }
        saveTasksToFile();
    }

    private static void markTask(String userInput) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(userInput.substring(5).trim()) - 1;
            if (isValidTaskIndex(taskIndex)) {
                tasks.get(taskIndex).markAsDone();
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + tasks.get(taskIndex).getStatusIcon() + tasks.get(taskIndex).getDescription());
            } else {
                throw new DukeException("OOPS!!! Invalid task number.");
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Invalid command format.");
        }
        saveTasksToFile();
    }

    private static void unmarkTask(String userInput) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(userInput.substring(7).trim()) - 1;
            if (isValidTaskIndex(taskIndex)) {
                tasks.get(taskIndex).markAsNotDone();
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks.get(taskIndex).getStatusIcon() + tasks.get(taskIndex).getDescription());
            } else {
                throw new DukeException("OOPS!!! Invalid task number.");
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Invalid command format.");
        }
        saveTasksToFile();
    }
    private static void loadTasksFromFile() {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return; // File will be created when saving tasks for the first time.
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");
                try {
                    // Expected format: Type | Done | Description | Additional Info
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];
                    Task task = null;

                    switch (type) {
                        case "T":
                            task = new Todo(description);
                            break;
                        case "D":
                            if (parts.length < 4) throw new DukeException("Invalid deadline format in file.");
                            task = new Deadline(description, parts[3]);
                            break;
                        case "E":
                            if (parts.length < 5) throw new DukeException("Invalid event format in file.");
                            task = new Event(description, parts[3], parts[4]);
                            break;
                    }

                    if (task != null) {
                        if (isDone) {
                            task.markAsDone();
                        }
                        tasks.add(task);
                    }
                } catch (DukeException e) {
                    System.out.println("Skipping invalid task: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    private static void saveTasksToFile() {
        try (PrintWriter writer = new PrintWriter(FILE_PATH)) {
            for (Task task : tasks) {
                String type = task instanceof Todo ? "T" :
                        task instanceof Deadline ? "D" :
                                task instanceof Event ? "E" : "";
                String status = task.isDone ? "1" : "0";
                String line = type + " | " + status + " | " + task.getDescription();
                if (task instanceof Deadline) {
                    line += " | " + ((Deadline) task).by;
                } else if (task instanceof Event) {
                    line += " | " + ((Event) task).from + " | " + ((Event) task).to;
                }
                writer.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save tasks: " + e.getMessage());
        }
    }



    private static void deleteTask(String userInput) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(userInput.substring(7).trim()) - 1;
            if (isValidTaskIndex(taskIndex)) {
                Task removedTask = tasks.remove(taskIndex);
                System.out.println(" Noted. I've removed this task:");
                System.out.println("   " + removedTask.getStatusIcon() + removedTask.getDescription());
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            } else {
                throw new DukeException("OOPS!!! Invalid task number.");
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Invalid command format.");
        }
        saveTasksToFile(); // 
    }

    private static void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");

        if (tasks.isEmpty()) {
            System.out.println(" No tasks yet.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i).getStatusIcon() + tasks.get(i).getDescription());
            }
        }
    }

    private static void printTaskAddedMessage(Task task) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task.getStatusIcon() + task.getDescription());
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    private static boolean isValidTaskIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}


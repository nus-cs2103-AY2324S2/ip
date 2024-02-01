import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    public enum CommandType {
        BYE, LIST, DELETE, MARK, UNMARK, TODO, EVENT, DEADLINE
    }

    private static final String DATA_FILE_PATH = "./data/duke.txt";
    private static final Path directoryPath = Paths.get("./data");
    private static final Path filePath = Paths.get(DATA_FILE_PATH);

    public static void main(String[] args) throws DukeException, IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String confirmation = "Got it. I've added this task:";
        String userInput;

        //Greetings
        String name = "Tommy";
        printDivider();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        printDivider();


        tasks = loadTasks();

        while (scanner.hasNextLine()) {
            try {
                userInput = scanner.nextLine();
                String[] components = parseCommand(userInput);
                String desc = "";
                String command;

                // Validate Command and Description and initialise them
                validateNonEmptyCommand(components);
                command = components[0].toUpperCase();
                validateValidCommand(command);
                CommandType formattedCommand = CommandType.valueOf(command);

                if (!formattedCommand.equals(CommandType.LIST) && !formattedCommand.equals(CommandType.BYE)) {
                    validateNonEmptyDesc(components);
                    desc = components[1];
                }

                switch (formattedCommand) {
                case UNMARK:

                    // Validate and initialise index
                    int indexToUnmark = Integer.parseInt(desc);
                    validateIndex(indexToUnmark, tasks.size());

                    // Unmark the task
                    Task taskToUnmark = tasks.get(indexToUnmark - 1);
                    taskToUnmark.isDone = false;

                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + taskToUnmark.toString());

                    printDivider();
                    break;

                case MARK:
                    // Validate and initialise index
                    int indexToMark = Integer.parseInt(desc);
                    validateIndex(indexToMark, tasks.size());

                    // Unmark the task
                    Task taskToMark = tasks.get(indexToMark - 1);
                    taskToMark.isDone = true;

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + taskToMark.toString());

                    printDivider();
                    break;

                case LIST:
                    System.out.println("Here are the tasks in your list:");
                    int length = tasks.size();
                    for (int i = 0; i < length; i++) {
                        Task task = tasks.get(i);
                        System.out.println(i + 1 + "." + task.toString());
                    }

                    printDivider();
                    break;

                case TODO:

                    // instantiate To-do
                    Todo todo = new Todo(desc);
                    tasks.add(todo);

                    System.out.println(confirmation);
                    System.out.println("  " + todo.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    printDivider();
                    break;

                case DEADLINE:
                    // Format : deadlineDetails /by some date
                    String deadlineDetails;
                    String byDate;

                    // extract and validate deadlineDetails and byDate
                    String[] fragments = desc.split(" /by ", 2);
                    validateFormat(fragments, 2);
                    deadlineDetails = fragments[0];
                    byDate = fragments[1];

                    // instantiate deadline
                    desc = deadlineDetails + " (by: " + byDate + ")";
                    Deadline deadline = new Deadline(desc);
                    tasks.add(deadline);

                    System.out.println(confirmation);
                    System.out.println("  " + deadline.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    printDivider();
                    break;

                case EVENT:
                    // Format details /from some date /to some date
                    String eventDetails;
                    String fromDate;
                    String toDate;

                    // extract and validate eventDetails, fromDate, and toDate
                    fragments = desc.split(" /from | /to ", 3);
                    validateFormat(fragments, 3);
                    eventDetails = fragments[0];
                    fromDate = fragments[1];
                    toDate = fragments[2];

                    // instantiate event
                    desc = eventDetails + " (from: " + fromDate + " to: " + toDate + ")";
                    Event event = new Event(desc);
                    tasks.add(event);

                    System.out.println(confirmation);
                    System.out.println("  " + event.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    printDivider();
                    break;

                case DELETE:

                    // Validate and initialise index
                    int indexToDelete = Integer.parseInt(desc);
                    validateIndex(indexToDelete, tasks.size());

                    // Delete the task
                    Task taskToDelete = tasks.get(indexToDelete - 1);
                    String descOfTaskToDelete = taskToDelete.toString();
                    tasks.remove(taskToDelete);

                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + descOfTaskToDelete);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    printDivider();
                    break;

                case BYE:
                    System.out.println("Bye. Hope to see you again soon!");
                    printDivider();
                    System.exit(0);
                    break;
                default:
                    throw new DukeException("Invalid command");
                }

                saveTasks(tasks);

            } catch (DukeException e) {
                System.out.println("Oops: " + e.getMessage());
                printDivider();

            }
        }
        scanner.close();
    }


    private static String[] parseCommand(String userInput) {
        return userInput.split(" ", 2);
    }
    private static void validateNonEmptyDesc(String[] components) throws DukeException {
        if (components.length < 2 || components[1].isBlank()) {
            throw new DukeException("The description cannot be empty :(");
        }
    }
    private static void validateNonEmptyCommand(String[] components) throws DukeException {
        if (components.length < 1 || components[0].isBlank()) {
            throw new DukeException("Invalid command");
        }
    }
    private static void validateValidCommand(String command) throws DukeException {
        try {
            CommandType convertCommand = CommandType.valueOf(command);
        } catch (IllegalArgumentException e) {
            throw new DukeException("Invalid command");
        }
    }

    private static void validateFormat(String[] fragments, int expectedNumberOfFragments) throws DukeException {
        if (fragments.length != expectedNumberOfFragments) {
            throw new DukeException("Something is wrong with the format!");
        }
        for (int i = 0; i < expectedNumberOfFragments; i++) {
            if (fragments[i].isBlank()) {
                throw new DukeException("Something is wrong with the format!");
            }
        }
    }
    private static void validateIndex(int index, int length) throws DukeException {
        if (index > length || index <= 0) {
            throw new DukeException("Invalid index");
        }
    }

    private static void printDivider() {
        System.out.println("____________________________");
    }

    private static void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private static ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (!Files.exists(filePath)) {
            if (!Files.exists(directoryPath));
                Files.createDirectories(directoryPath);
            System.out.println("dont have");
            Files.createFile(filePath);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE_PATH))) {
            String data;
            while ((data = reader.readLine()) != null) {
                Task task = Task.parseFromFileString(data);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing file. Starting with an empty task list.");
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }
}




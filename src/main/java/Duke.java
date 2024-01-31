import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "./data/duke.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printWelcomeMessage();

        try {
            loadTasks();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        while (true) {
            try {
                String userInput = scanner.nextLine().trim();
                if (userInput.equalsIgnoreCase("bye")) {
                    break;
                }
                handleInput(userInput);
            } catch (DukeException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }

        printGoodbyeMessage();
        scanner.close();
    }

    private static void handleInput(String userInput) throws DukeException {
        String[] parts = userInput.split(" ", 2);
        String command = parts[0];

        switch (command.toLowerCase()) {
            case "list":
                printTaskList();
                break;
            case "delete":
                deleteTask(parts[1]);
                break;
            case "mark":
            case "unmark":
                markOrUnmarkTask(command, parts[1]);
                break;
            case "todo":
            case "deadline":
            case "event":
                createNewTask(parts);
                break;
            default:
                throw new DukeException("Whoops! I didn't catch that. Could you try a different command?");
        }
        saveTasks();
    }


    private static void saveTasks() {
        try {
            File directory = new File(FILE_PATH).getParentFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    private static void loadTasks() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return; // No tasks to load
        }
        List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            switch (parts[0]) {
                case "T":
                    tasks.add(new Todo(parts[2], parts[1].equals("1")));
                    break;
                case "D":
                    tasks.add(new Deadline(parts[2], parts[3], parts[1].equals("1")));
                    break;
                case "E":
                    tasks.add(new Event(parts[2], parts[3], parts[4], parts[1].equals("1")));
                    break;
            }
        }
    }

    private static void markOrUnmarkTask(String command, String taskNumberStr) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(taskNumberStr) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new DukeException("Oopsie! I can't seem to find that task. Could it be a magical invisible task?.");
            }
            Task task = tasks.get(taskNumber);
            if (command.equalsIgnoreCase("mark")) {
                task.markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:\n  " + task);
            } else {
                task.unmarkAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:\n  " + task);
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Whoops! Looks like that's not a valid number for a task. Numbers only, please!");
        }
    }

    private static void createNewTask(String[] parts) throws DukeException {
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new DukeException("Looks like you missed the description! " + parts[0] + " cannot be empty.");
        }

        Task newTask = null;
        switch (parts[0].toLowerCase()) {
            case "todo":
                newTask = new Todo(parts[1], false);
                break;
            case "deadline":
                String[] deadlineParts = parts[1].split(" /by ", 2);
                if (deadlineParts.length < 2) {
                    throw new DukeException("Deadline format incorrect. Correct format: deadline description /by time");
                }
                newTask = new Deadline(deadlineParts[0], deadlineParts[1], false);
                break;
            case "event":
                String[] eventParts = parts[1].split(" /from ", 2);
                if (eventParts.length < 2 || !eventParts[1].contains(" /to ")) {
                    throw new DukeException("Event format incorrect. Correct format: event description /from start time /to end time");
                }
                String[] timeParts = eventParts[1].split(" /to ", 2);
                newTask = new Event(eventParts[0], timeParts[0], timeParts[1], false);
                break;
        }

        if (newTask != null) {
            tasks.add(newTask);
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:\n  " + newTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } else {
            throw new DukeException("Task type '" + parts[0] + "' is not recognized.");
        }
    }


    private static void deleteTask(String taskNumberStr) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(taskNumberStr) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new DukeException("Uh-oh, can't find that task. Are you sure it's the right number?");
            }
            Task removedTask = tasks.remove(taskNumber);
            System.out.println("____________________________________________________________");
            System.out.println("Noted. I've removed this task:\n  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            throw new DukeException("Whoops! Looks like that's not a valid number for a task. Numbers only, please!");
        }
    }

    private static void printTaskList() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    private static void printWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm EchoPilot.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}


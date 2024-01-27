package yapper;
import yapper.tasks.*;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Yapper {
    private static final String FILE_PATH = "./src/main/java/data/yapper.Yapper.txt";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private static List<Task> tasks;
    private Scanner userScanner;
    private static Ui ui;
    private Storage storage;

    public Yapper(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (YapperException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        userScanner = new Scanner(System.in);
    }

    public void run() {
        ui.showWelcomeMessage();
        ui.showInstructions();

        while (true) {
            ui.showUserPrompt();
            String userInput = userScanner.nextLine();
            try {
                processUserInput(userInput);
                storage.saveTasks(tasks);
            } catch (YapperException e) {
                ui.showError(e.getMessage());
            }
        }
    }


    /**
     * Processes the various user inputs.
     *
     * @param userInput User input in string format.
     * @throws YapperException If any of user inputs is invalid.
     */
    private static void processUserInput(String userInput) throws YapperException {

        if (userInput.equalsIgnoreCase("list")) {
            ui.showTaskList(tasks);
        } else if (userInput.startsWith("mark")) {
            try {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks.get(taskNumber).markAsDone();
                ui.showMarkedDoneMessage(tasks.get(taskNumber));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new YapperException("Please provide a valid task number to mark as done.");
            }
        } else if (userInput.startsWith("unmark")) {
            try {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks.get(taskNumber).markAsNotDone();
                ui.showMarkedNotDoneMessage(tasks.get(taskNumber));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new YapperException("Please provide a valid task number to mark as not done.");
            }
        } else if (userInput.startsWith("todo")) {
            if (userInput.length() <= 5) {
                throw new YapperException("The description of a todo cannot be empty!");
            }
            Todo newTask = new Todo(userInput.substring(5), false);
            tasks.add(newTask);
            ui.showAddedTaskMessage(newTask, tasks.size());
        } else if (userInput.startsWith("deadline")) {
            try {
                String[] parts = userInput.substring(9).split("/by");
                Deadline newTask = new Deadline(parts[0].trim(), false, LocalDateTime.parse(parts[1].trim(),
                        DATE_TIME_FORMATTER));
                tasks.add(newTask);
                ui.showAddedTaskMessage(newTask, tasks.size());
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new YapperException("Please provide a valid deadline task format.");
            }
        } else if (userInput.startsWith("event")) {
            try {
                String[] parts = userInput.substring(6).split("/from|/to");
                Event newTask = new Event(parts[0].trim(), false, LocalDateTime.parse(parts[1].trim(),
                        DATE_TIME_FORMATTER),
                        LocalDateTime.parse(parts[2].trim(), DATE_TIME_FORMATTER));
                tasks.add(newTask);
                ui.showAddedTaskMessage(newTask, tasks.size());
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new YapperException("Please provide a valid event task format.");
            }
        } else if (userInput.startsWith("delete")) {
            try {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                Task removedTask = tasks.remove(taskNumber);
                ui.showRemovedTaskMessage(removedTask, tasks.size());
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new YapperException("Please provide a valid task number to delete.");
            }
        } else if (userInput.equalsIgnoreCase("bye")) {
            if (userInput.equalsIgnoreCase("bye")) {
                ui.showGoodbyeMessage();
                System.exit(0);
            }
        } else {
            throw new YapperException("Sorry but I don't know what you're yapping about :(");
        }
    }

    public static void main(String[] args) {
        new Yapper(FILE_PATH).run();
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks List of tasks based on user inputs.
     * @return List of tasks.
     */
    private static void printTaskList(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }
}
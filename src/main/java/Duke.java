import java.util.ArrayList;
<<<<<<< HEAD
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
=======
import java.util.Scanner;

/**
 * HERE IS THE SEQUENCE OF EVENTS:
 * In duke you can access TaskList which is the storage of the class
 * You can also access Storage to store TaskList
 * You can also access Ui to manage the user interactions
 *
 * first make a new ui
 * make a new storage to the filepath
 * then try to make a tasklist and take in the storage that is arleady inside
 */
>>>>>>> more-OOP

/**
 * Duke - Main class that handles user interactions and task management.
 */
public class Duke {
<<<<<<< HEAD
    private static ArrayList<Task> storage = new ArrayList<>();
    //private static final String FILEPATH = "./data/duke.txt";
    public static void main(String[] args) {
=======
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui(null, null);
        tasks = new TaskList();
        storage = new Storage(filePath, tasks);

        try {
            ArrayList<Task> loadedTasks = storage.loadFile();
            tasks.getTasks().addAll(loadedTasks);
        } catch (DukeException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.sayHi();
        storage.loadFile();
>>>>>>> more-OOP
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            ui.handleInput(input);
        }


<<<<<<< HEAD
            if ("mark".equalsIgnoreCase(split[0])) {
                task.markAsDone();
                //saveTasks();
                System.out.println("I've marked this task as done:\n  " + task);
            }

            else if ("unmark".equalsIgnoreCase(split[0])) {
                task.unmarkTask();
                //saveTasks();
                System.out.println("I've unmarked this task! It is now not done yet:\n  " + task);
            }

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Invalid task number. Please refer to your to-do list again.");
        }
    }

    /**
     * Handles the creation and addition of Todo tasks to the task list.
     * @param input User input specifying the Todo description.
     */
    private static void handleTodos(String input) {
        String description = input.substring(5).trim();
        Todo todo = new Todo(description);
        storage.add(todo);
        //saveTasks();
        System.out.println("Ok! I've added this todo: " + todo);
        System.out.println("Now you have " + storage.size() + " tasks in your list.");
    }

    /**
     * Handles the creation and addition of Deadline tasks to the task list.
     * @param input User input specifying the Deadline description and due date.
     */
    private static void handleDeadlines(String input) {
        String[] splitParts = input.substring(9).split("/by", 2);

        if (splitParts.length > 1) {
            String description = splitParts[0].trim();
            String date = splitParts[1].trim();
            if (isValidDate(date)) {
                LocalDate d1 = LocalDate.parse(date, DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));
                Deadlines deadline = new Deadlines(description, d1);
                storage.add(deadline);
                //saveTasks();
                System.out.println("Ok! I've added this deadline: " + deadline);
            } else {
                Deadlines deadline = new Deadlines(description, date);
                storage.add(deadline);
                //saveTasks();
                System.out.println("Ok! I've added this deadline: " + deadline);
            }
            System.out.println("Now you have " + storage.size() + " tasks in your list.");
        }
        else {
            System.out.println("Invalid input format for deadline. Please provide a valid date/time.");
        }
    }

    /**
     * Handles the creation and addition of Event tasks to the task list.
     * @param input User input specifying the Event description and date range.
     */
    private static void handleEvents(String input) {
        String[] splitParts = input.substring(6).split("/from", 2);
        String[] splitTo = splitParts[1].split("/to", 2);

        if (splitParts.length > 1) {
            String description = splitParts[0].trim();
            String fromDate = splitTo[0].trim();
            String toDate = splitTo[1].trim();
            if (isValidDate(fromDate)) {
                LocalDate d1 = LocalDate.parse(fromDate);
                if (isValidDate(toDate)) {
                    LocalDate d2 = LocalDate.parse(toDate);
                    Event event = new Event(description, d1, d2);
                    storage.add(event);
                    //saveTasks();
                    System.out.println("Ok! I've added this event: " + event);
                }
            } else {
                Event event = new Event(description, fromDate, toDate);
                storage.add(event);
                //saveTasks();
                System.out.println("Ok! I've added this event: " + event);
            }
            System.out.println("Now you have " + storage.size() + " tasks in your list.");

        } else {
            System.out.println("Invalid input format for event. Please provide a valid date/time.");
        }
    }

    private static void handleRemove(String input) {
        String[] splitParts = input.split(" ");
        if (splitParts.length < 2) {
            System.out.println("Please specify which task number you want to remove!");
            return;
        }

        try {
            int index = Integer.parseInt(splitParts[1]) - 1;
            Task removedTask = storage.remove(index);
            //saveTasks();
            System.out.println("Ok! I have removed this task from your list:\n  " + removedTask);
            System.out.println("Now you have " + storage.size() + " tasks in your list.");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Invalid task number. Please refer to your to-do list again.");
        }
    }

    /**
     * Validates the input format for creating Deadlines.
     * @param input User input specifying the Deadline description and due date.
     * @return True if the input format is valid, false otherwise.
     */
    private static boolean validateDeadlineInput(String input) {
        String[] splitParts = input.substring(9).split("/by", 2);
        return splitParts.length > 1;
    }

    /**
     * Validates the input format for creating Todo tasks.
     * @param input User input specifying the Todo description.
     * @return True if the input format is valid, false otherwise.
     */
    private static boolean validateTodoInput(String input) {
        return input.length() > 5;
    }

    /**
     * Validates the input format for creating Events.
     * @param input User input specifying the Event description and date range.
     * @return True if the input format is valid, false otherwise.
     */
    private static boolean validateEventInput(String input) {
        String[] splitParts = input.substring(6).split("/from", 2);
        return splitParts.length > 1;
    }

    // HANDLE DATES

    private static boolean isValidDate(String input) {
        try {
            LocalDate.parse(input, DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));
            return true;
        } catch (DateTimeParseException e1) {
            try {
                LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return true;
            } catch (DateTimeParseException e2) {
                return false;
            }

        }
    }
=======
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

>>>>>>> more-OOP
}

package floofy;

import floofy.task.Deadline;
import floofy.task.Event;
import floofy.task.Task;
import floofy.task.ToDo;

import java.util.Scanner;
import java.time.LocalDate;

/**
 * Represents the main class of the Floofy chat-bot application.
 */
public class Floofy {
    /** The storage object to handle the loading and saving of tasks. */
    private Storage storage;

    /** The parser object to handle the parsing of user input. */
    private Parser parser;

    /** The task list object to handle the list of tasks. */
    private TaskList tasks;

    /** The user interface object to handle the interaction with the user. */
    private Ui ui;

    /** The file path of the file to store the tasks. */
    private static final String FILE_PATH = "./data/duke.txt";

    /**
     * Constructs a new object of the Floofy class.
     *
     * @param filePath The file path of the file to store the tasks.
     */
    public Floofy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList();
            storage.loadTasks(tasks);
        } catch (FloofyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Floofy chat-bot application.
     */
    public void run() {
        ui.showWelcomeMsg();
        Scanner scanner = new Scanner(System.in);
        loop:
        while (true) {
            try {
                String userInput = scanner.nextLine();
                String[] input = parser.parse(userInput);
                switch (input[0]) {
                case "mark":
                    int idx = Integer.parseInt(input[1]) - 1;
                    tasks.markTaskAsDone(idx);
                    ui.showMarkedTask(this.tasks.getTask(idx));
                    storage.saveTasks(tasks);
                    continue;
                case "unmark":
                    int unmarkIdx = Integer.parseInt(input[1]) - 1;
                    tasks.markTaskAsUndone(unmarkIdx);
                    ui.showUnmarkedTask(this.tasks.getTask(unmarkIdx));
                    storage.saveTasks(tasks);
                    continue;
                case "find":
                    TaskList matchingTasks = tasks.findMatchingTasks(input[1]);
                    ui.showMatchingTasks(matchingTasks);
                    continue;
                case "todo":
                    String todoTask = input[1];
                    ToDo newTodo = new ToDo(todoTask);
                    tasks.addTask(newTodo);
                    ui.showAddedTask(newTodo, tasks.getSize());
                    storage.saveTasks(tasks);
                    continue;
                case "deadline":
                    String deadlineTask = input[1];
                    LocalDate deadlineBy = LocalDate.parse(input[2]);
                    Deadline newDeadline = new Deadline(deadlineTask, deadlineBy);
                    tasks.addTask(newDeadline);
                    ui.showAddedTask(newDeadline, tasks.getSize());
                    storage.saveTasks(tasks);
                    continue;
                case "event":
                    String eventTask = input[1];
                    LocalDate eventDateFrom = LocalDate.parse(input[2]);
                    LocalDate eventDateTo = LocalDate.parse(input[3]);
                    Event newEvent = new Event(eventTask, eventDateFrom, eventDateTo);
                    tasks.addTask(newEvent);
                    ui.showAddedTask(newEvent, tasks.getSize());
                    storage.saveTasks(tasks);
                    continue;
                case "delete":
                    int deleteIdx = Integer.parseInt(input[1]) - 1;
                    Task deletedTask = tasks.getTask(deleteIdx);
                    tasks.deleteTask(deleteIdx);
                    ui.showDeletedTask(deletedTask, tasks.getSize());
                    storage.saveTasks(tasks);
                    continue;
                case "list":
                    ui.showTaskList(tasks);
                    continue;
                case "bye":
                    ui.showGoodbyeMsg();
                    scanner.close();
                    break loop;
                case "invalid":
                    throw new FloofyException("To add a task, please start with any of these commands: 'todo', 'deadline' or 'event'!");
                }
            } catch (FloofyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * The main method of the Floofy chat-bot application.
     */
    public static void main(String[] args) {
        new Floofy(FILE_PATH).run();
    }
}

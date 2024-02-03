package drake;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import drake.task.Deadline;
import drake.task.Event;
import drake.task.Task;
import drake.task.TaskList;
import drake.task.Todo;

enum Command {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID, FIND;
    public static Command fromString(String commandString) {
        switch (commandString.toLowerCase()) {
        case "bye":
            return BYE;
        case "list":
            return LIST;
        case "mark":
            return MARK;
        case "unmark":
            return UNMARK;
        case "todo":
            return TODO;
        case "deadline":
            return DEADLINE;
        case "event":
            return EVENT;
        case "delete":
            return DELETE;
        case "find":
            return FIND;
        default:
            return INVALID;
        }
    }
}

/**
 * This is the main class for the Drake application, which is a task manager program.
 * It initializes the application, loads existing tasks, and enters a command loop that
 * allows users to interact with their task list through various commands.
 */
public class Drake {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private boolean isRunning;

    /**
     * Constructs a new instance of the Drake application.
     *
     * @param filePath The path to the file where tasks are stored and retrieved from.
     */
    public Drake(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
        isRunning = true;
    }

    /**
     * Starts the main command loop of the application. This method reads user input,
     * processes commands, and interacts with the task list until the bye command is issued.
     *
     * @throws Exception if an unexpected error occurs during command processing.
     */
    public void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        ui.showWelcome();
        while (isRunning) {
            String input = scanner.nextLine().trim();
            String[] words = input.split(" ", 2);
            String commandWord = words[0];
            Command command = Command.fromString(commandWord);
            try {
                switch (command) {
                case BYE:
                    isRunning = false;
                    storage.saveTasks(tasks.getTasks());
                    ui.showGoodbye();
                    break;
                case LIST:
                    ui.showTaskList(tasks);
                    break;
                case MARK:
                    int markIndex = Parser.parseTaskIndex(input);
                    tasks.markTask(markIndex);
                    ui.showMarkTask(tasks.getTask(markIndex));
                    break;
                case UNMARK:
                    int unmarkIndex = Parser.parseTaskIndex(input);
                    tasks.unmarkTask(unmarkIndex);
                    ui.showUnmarkTask(tasks.getTask(unmarkIndex));
                    break;
                case TODO:
                    String todoDescription = Parser.parseDescription(input);
                    Todo newTodo = new Todo(todoDescription);
                    tasks.addTask(newTodo);
                    ui.showAddTask(newTodo, tasks.size());
                    break;
                case DEADLINE:
                    try {
                        Object[] deadlineDetails = Parser.parseDeadline(input);
                        Deadline newDeadline = new Deadline((String) deadlineDetails[0],
                                (LocalDateTime) deadlineDetails[1]);
                        tasks.addTask(newDeadline);
                        ui.showAddTask(newDeadline, tasks.size());
                    } catch (DateTimeParseException e) {
                        ui.showError("Oops, format error! Type in a date in the form yy-mm-dd and try again!");
                    }
                    break;
                case EVENT:
                    String[] eventDetails = Parser.parseEvent(input);
                    Event newEvent = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
                    tasks.addTask(newEvent);
                    ui.showAddTask(newEvent, tasks.size());
                    break;
                case DELETE:
                    int deleteIndex = Parser.parseTaskIndex(input);
                    Task deletedTask = tasks.deleteTask(deleteIndex);
                    ui.showDeleteTask(deletedTask, tasks.size());
                    break;
                case FIND:
                    String keyword = Parser.parseKeyword(input);
                    ArrayList<Task> matchingTasks = tasks.findTasksByKeyword(keyword);
                    ui.showMatchingTasks(matchingTasks);
                    break;
                default:
                    throw new NotValidCommand("That's not a valid command!");
                }
            } catch (NotValidCommand | TodoLeftBlank e) {
                ui.showError(e.getMessage());
            }
        }
        scanner.close();
    }

    public static void main(String[] args) throws Exception {
        new Drake("./list.dat").run();
    }
}

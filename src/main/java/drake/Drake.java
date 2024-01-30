package drake;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import drake.task.Deadline;
import drake.task.Event;
import drake.task.Task;
import drake.task.TaskList;
import drake.task.Todo;

enum Command {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID;
    
    // Method to get the appropriate enum value from a string input
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
        default:
            return INVALID;
        }
    }
}

public class Drake {
    private final Ui UI;
    private final Storage STORAGE;
    private final TaskList TASKS;
    private boolean isRunning; 

    public Drake(String FILE_PATH) {
        UI = new Ui();
        STORAGE = new Storage(FILE_PATH);
        TASKS = new TaskList(STORAGE.loadTasks());
        isRunning = true;
    }

    public void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        UI.showWelcome();
    
        while (isRunning) {
            String input = scanner.nextLine().trim();
            String[] words = input.split(" ", 2);
            String commandWord = words[0];
            Command command = Command.fromString(commandWord);

            try {
                switch (command) {
                case BYE:
                    isRunning = false;
                    STORAGE.saveTasks(TASKS.getTasks());
                    UI.showGoodbye();
                    break;
                case LIST:
                    UI.showTaskList(TASKS);
                    break;
                case MARK:
                    int markIndex = Parser.parseTaskIndex(input);
                    TASKS.markTask(markIndex);
                    UI.showMarkTask(TASKS.getTask(markIndex));
                    break;
                case UNMARK:
                    int unmarkIndex = Parser.parseTaskIndex(input);
                    TASKS.unmarkTask(unmarkIndex);
                    UI.showUnmarkTask(TASKS.getTask(unmarkIndex));
                    break;
                case TODO:
                    String todoDescription = Parser.parseDescription(input);
                    Todo newTodo = new Todo(todoDescription);
                    TASKS.addTask(newTodo);
                    UI.showAddTask(newTodo, TASKS.size());
                    break;
                case DEADLINE:
                    try {
                        Object[] deadlineDetails = Parser.parseDeadline (input);
                        Deadline newDeadline = new Deadline((String) deadlineDetails[0],
                                (LocalDateTime) deadlineDetails[1]);
                        TASKS.addTask(newDeadline);
                        UI.showAddTask(newDeadline, TASKS.size());
                    } catch (DateTimeParseException e) {
                        UI.showError("Oops, format error! Type in a date in the form yy-mm-dd and try again!");
                    }
                    break;
                case EVENT:
                    String[] eventDetails = Parser.parseEvent(input);
                    Event newEvent = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
                    TASKS.addTask(newEvent);
                    UI.showAddTask(newEvent, TASKS.size());
                    break;
                case DELETE:
                    int deleteIndex = Parser.parseTaskIndex(input);
                    Task deletedTask = TASKS.deleteTask(deleteIndex);
                    UI.showDeleteTask(deletedTask, TASKS.size());
                    break;
                case INVALID:
                    throw new NotValidCommand("That's not a valid command!");
                }
            } catch (NotValidCommand | TodoLeftBlank e) {
                UI.showError(e.getMessage());
            }
        }
        scanner.close();
    }

    public static void main(String[] args) throws Exception {
        new Drake("./list.dat").run();
    }
}
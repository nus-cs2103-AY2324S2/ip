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
    private final TaskList TASK_LIST;
    private boolean isRunning; 

    public Drake(String FILE_PATH) {
        UI = new Ui();
        STORAGE = new Storage(FILE_PATH);
        TASK_LIST = new TaskList(STORAGE.loadTasks());
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
                        STORAGE.saveTasks(TASK_LIST.getTasks());
                        UI.showGoodbye();
                        break;
                    case LIST:
                        UI.showTaskList(TASK_LIST);
                        break;
                    case MARK:
                        int markIndex = Parser.parseTaskIndex(input);
                        TASK_LIST.markTask(markIndex);
                        UI.showMarkTask(TASK_LIST.getTask(markIndex));
                        break;
                    case UNMARK:
                        int unmarkIndex = Parser.parseTaskIndex(input);
                        TASK_LIST.unmarkTask(unmarkIndex);
                        UI.showUnmarkTask(TASK_LIST.getTask(unmarkIndex));
                        break;
                    case TODO:
                        String todoDescription = Parser.parseDescription(input);
                        Todo newTodo = new Todo(todoDescription);
                        TASK_LIST.addTask(newTodo);
                        UI.showAddTask(newTodo, TASK_LIST.size());
                        break;
                    case DEADLINE:
                        try {
                            Object[] deadlineDetails = Parser.parseDeadline (input);
                            Deadline newDeadline = new Deadline((String) deadlineDetails[0],
                                    (LocalDateTime) deadlineDetails[1]);
                            TASK_LIST.addTask(newDeadline);
                            UI.showAddTask(newDeadline, TASK_LIST.size());
                        } catch (DateTimeParseException e) {
                            UI.showError("Oops, format error! Type in a date in the form yy-mm-dd and try again!");
                        }
                        break;
                    case EVENT:
                        String[] eventDetails = Parser.parseEvent(input);
                        Event newEvent = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
                        TASK_LIST.addTask(newEvent);
                        UI.showAddTask(newEvent, TASK_LIST.size());
                        break;
                    case DELETE:
                        int deleteIndex = Parser.parseTaskIndex(input);
                        Task deletedTask = TASK_LIST.deleteTask(deleteIndex);
                        UI.showDeleteTask(deletedTask, TASK_LIST.size());
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
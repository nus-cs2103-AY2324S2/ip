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
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private boolean isRunning; 

    public Drake(String FILE_PATH) {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        taskList = new TaskList(storage.loadTasks());
        isRunning = true;
    }

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
                        storage.saveTasks(taskList.getTasks());
                        ui.showGoodbye();
                        break;
                    case LIST:
                        ui.showTaskList(taskList);
                        break;
                    case MARK:
                        int markIndex = Parser.parseTaskIndex(input);
                        taskList.markTask(markIndex);
                        ui.showMarkTask(taskList.getTask(markIndex));
                        break;
                    case UNMARK:
                        int unmarkIndex = Parser.parseTaskIndex(input);
                        taskList.unmarkTask(unmarkIndex);
                        ui.showUnmarkTask(taskList.getTask(unmarkIndex));
                        break;
                    case TODO:
                        String todoDescription = Parser.parseDescription(input);
                        Todo newTodo = new Todo(todoDescription);
                        taskList.addTask(newTodo);
                        ui.showAddTask(newTodo, taskList.size());
                        break;
                    case DEADLINE:
                        try {
                            Object[] deadlineDetails = Parser.parseDeadline (input);
                            Deadline newDeadline = new Deadline((String) deadlineDetails[0], (LocalDateTime) deadlineDetails[1]);
                            taskList.addTask(newDeadline);
                            ui.showAddTask(newDeadline, taskList.size());
                        } catch (DateTimeParseException e) {
                            ui.showError("Oops, format error! Type in a date in the form yy-mm-dd and try again!");
                        }
                        break;
                    case EVENT:
                        String[] eventDetails = Parser.parseEvent(input);
                        Event newEvent = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
                        taskList.addTask(newEvent);
                        ui.showAddTask(newEvent, taskList.size());
                        break;
                    case DELETE:
                        int deleteIndex = Parser.parseTaskIndex(input);
                        Task deletedTask = taskList.deleteTask(deleteIndex);
                        ui.showDeleteTask(deletedTask, taskList.size());
                        break;
                    case INVALID:
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
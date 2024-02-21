package drake;

import java.time.LocalDateTime;
import java.util.ArrayList;

import drake.contact.Contact;
import drake.task.Deadline;
import drake.task.Event;
import drake.task.Task;
import drake.task.TaskList;
import drake.task.Todo;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.util.Duration;

enum Command {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID, FIND, ADD_CONTACT, SHOW_CONTACTS;
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
        case "add-contact":
            return ADD_CONTACT;
        case "list-contacts":
            return SHOW_CONTACTS;
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
    private final ArrayList<Contact> contacts;

    /**
     * Constructs a new instance of the Drake application.
     */
    public Drake() {
        ui = new Ui();
        storage = new Storage("./list.dat");
        tasks = new TaskList(storage.loadTasks());
        contacts = new ArrayList<>();
    }

    /**
     * Gets the response from Drake.
     *
     * @param input the user's input.
     * @return the response from Drake.
     */
    protected String getResponse(String input) {
        String[] words = input.split(" ", 2);
        String commandWord = words[0];
        Command command = Command.fromString(commandWord);
        try {
            switch (command) {
            case BYE:
                return handleBye();
            case LIST:
                return handleList();
            case MARK:
                return handleMark(input);
            case UNMARK:
                return handleUnmark(input);
            case TODO:
                return handleTodo(input);
            case DEADLINE:
                return handleDeadline(input);
            case EVENT:
                return handleEvent(input);
            case DELETE:
                return handleDelete(input);
            case FIND:
                return handleFind(input);
            case ADD_CONTACT:
                return handleContactAdd(input);
            case SHOW_CONTACTS:
                return showContacts();
            default:
                throw new NotValidCommandException("That's not a valid command!");
            }
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }
    private String handleBye() {
        storage.saveTasks(tasks.getTasks());
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
        return ui.showGoodbye();
    }

    private String handleContactAdd(String input) {
        String[] result = Parser.parseContactAdd(input);
        String contactName = result[0];
        String contactDescription = result[1];
        Contact newContact = new Contact(contactName, contactDescription);
        contacts.add(newContact);
        return ui.showContactAdd(newContact, contacts.size());
    }

    private String showContacts() {
        return ui.showContacts(contacts);
    }

    private String handleList() {
        return ui.showTaskList(tasks);
    }

    private String handleMark(String input) throws Exception {
        int markIndex = Parser.parseTaskIndex(input);
        if (markIndex < 0 && markIndex >= tasks.size()) {
            throw new Exception("Invalid task index for marking");
        }
        tasks.markTask(markIndex);
        storage.saveTasks(tasks.getTasks());
        return ui.showMarkTask(tasks.getTask(markIndex));
    }

    private String handleUnmark(String input) throws Exception {
        int unmarkIndex = Parser.parseTaskIndex(input);
        if (unmarkIndex < 0 && unmarkIndex >= tasks.size()) {
            throw new Exception("Invalid task index for un-marking");
        }
        tasks.unmarkTask(unmarkIndex);
        storage.saveTasks(tasks.getTasks());
        return ui.showUnmarkTask(tasks.getTask(unmarkIndex));
    }

    private String handleTodo(String input) {
        String todoDescription = Parser.parseDescription(input);
        Todo newTodo = new Todo(todoDescription);
        tasks.addTask(newTodo);
        storage.saveTasks(tasks.getTasks());
        return ui.showAddTask(newTodo, tasks.size());
    }

    private String handleDeadline(String input) {
        try {
            Object[] deadlineDetails = Parser.parseDeadline(input);
            Deadline newDeadline = new Deadline((String) deadlineDetails[0],
                    (LocalDateTime) deadlineDetails[1]);
            tasks.addTask(newDeadline);
            storage.saveTasks(tasks.getTasks());
            return ui.showAddTask(newDeadline, tasks.size());
        } catch (IllegalArgumentException e) {
            return ui.showError(e.getMessage());
        } catch (Exception e) {
            return ui.showError("Oops, format error! Type in a date in the form yyyy-mm-dd and try again!");
        }
    }

    private String handleEvent(String input) {
        try {
            Object[] eventDetails = Parser.parseEvent(input);
            Event newEvent = new Event((String) eventDetails[0], (LocalDateTime) eventDetails[1],
                    (LocalDateTime) eventDetails[2]);
            tasks.addTask(newEvent);
            storage.saveTasks(tasks.getTasks());
            return ui.showAddTask(newEvent, tasks.size());
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }

    private String handleDelete(String input) throws Exception {
        int deleteIndex = Parser.parseTaskIndex(input);
        if (deleteIndex < 0 && deleteIndex >= tasks.size()) {
            throw new Exception("Invalid task index for deleting");
        }
        Task deletedTask = tasks.deleteTask(deleteIndex);
        storage.saveTasks(tasks.getTasks());
        return ui.showDeleteTask(deletedTask, tasks.size());
    }

    private String handleFind(String input) {
        String keyword = Parser.parseKeyword(input);
        ArrayList<Task> matchingTasks = tasks.findTasksByKeyword(keyword);
        return ui.showMatchingTasks(matchingTasks);
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

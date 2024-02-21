package mona;
import java.util.Scanner;

/**
 * This class represents the main class used for gradle builds for my version of the Duke Chatbot.
 */
public class Mona {
    private Storage storage;
    private TaskList tasks;
    public Ui ui;
    private Scanner sc;
    private Parser parser;
    enum Command {
        BYE,
        LIST,
        EVENT,
        TODO,
        DEADLINE,
        MARK,
        UNMARK,
        DELETE,
        FIND,
        UPDATE
    }

    /**
     * Constructor for Mona, the Duke Chatbot
     */
    public Mona() {
        this.ui = new Ui();
        this.storage = new Storage("data/duke.txt");
        this.tasks = new TaskList(storage);
        this.sc = new Scanner(System.in);
        this.parser = new Parser();
    }

    public String getResponse(String input) {
        String[] inputArray = input.split(" ");
        Command currCommand = null;
        try {
            currCommand = parser.getCurrentCommand(inputArray);
        } catch (MonaException e) {
            return ui.showError(e.getMessage());
        }
        switch (currCommand) {
        case BYE:
            return ui.sayBye();
        case LIST:
            return tasks.displayList();
        case TODO:
            return handleTodo(input);
        case DEADLINE:
            return handleDeadline(input);
        case EVENT:
            return handleEvent(input);
        case MARK:
            return handleMark(inputArray);
        case UNMARK:
            return handleUnmark(inputArray);
        case DELETE:
            return handleDelete(inputArray);
        case FIND:
            return tasks.showRelevantTasks(inputArray[1]);
        case UPDATE:
            return handleUpdate(input.split(" /new "), inputArray);
        default:
            assert false : currCommand;
        }
        assert false : "Execution should not reach this point!";
        return "";
    }

    public String handleTodo(String input) {
        String[] details = null;
        try {
            details = parser.getDetails(Command.TODO, input);
        } catch (MonaException e) {
            return ui.showError(e.getMessage());
        }
        Task newTask = new Todo(details[0]);
        tasks.addTask(newTask);
        return ui.showListAfterQuantityChange(newTask, tasks.getNumberOfTasks(), true);
    }

    public String handleDeadline(String input) {
        String[] details = null;
        try {
            details = parser.getDetails(Command.DEADLINE, input);
        } catch (MonaException e) {
            return ui.showError(e.getMessage());
        }
        Task newTask = new Deadline(details[0], details[1]);
        tasks.addTask(newTask);
        return ui.showListAfterQuantityChange(newTask, tasks.getNumberOfTasks(), true);
    }

    public String handleEvent(String input) {
        String[] details = null;
        try {
            details = parser.getDetails(Command.EVENT, input);
        } catch (MonaException e) {
            return ui.showError(e.getMessage());
        }
        Task newTask = new Event(details[0], details[1], details[2]);
        tasks.addTask(newTask);
        return ui.showListAfterQuantityChange(newTask, tasks.getNumberOfTasks(), true);
    }

    public String handleMark(String[] inputArray) {
        Task markedTask = tasks.markTask(Integer.parseInt(inputArray[1]) - 1);
        return ui.showListAfterProgressChange(markedTask);
    }

    public String handleUnmark(String[] inputArray) {
        Task unmarkedTask = tasks.unmarkTask(Integer.parseInt(inputArray[1]) - 1);
        return ui.showListAfterProgressChange(unmarkedTask);
    }

    public String handleDelete(String[] inputArray) {
        Task removedTask = tasks.deleteTask(Integer.parseInt(inputArray[1]) - 1);
        return ui.showListAfterQuantityChange(removedTask, tasks.getNumberOfTasks(), false);
    }

    public String handleUpdate(String[] details, String[] inputArray) {
        int taskIndex = Integer.parseInt(inputArray[1]) - 1;
        Task updatedTask = tasks.updateTask(taskIndex, details[1]);
        return ui.showListAfterUpdate(updatedTask);
    }
}

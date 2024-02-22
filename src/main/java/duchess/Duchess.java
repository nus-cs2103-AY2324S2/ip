package duchess;

import java.util.ArrayList;

import duchess.parser.Parser;
import duchess.storage.Storage;
import duchess.task.Task;
import duchess.ui.Ui;
import javafx.util.Pair;

/**
 * Duchess class represents the main class of the Duchess program.
 * It initializes the necessary components and runs the program.
 */
public class Duchess {
    private static final String FILE_PATH = "./data/duchess.txt";
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private boolean isRunning;

    /**
     * Constructs a Duchess object.
     * Initializes storage, task list, and user interface components.
     *
     * @throws DuchessException if an error occurs during initialization
     */
    public Duchess() throws DuchessException {
        this.storage = new Storage(FILE_PATH);
        this.isRunning = true;
        this.ui = new Ui();
        ArrayList<Task> tasksStored = this.storage.loadData();
        if (!tasksStored.isEmpty()) {
            this.taskList = new TaskList(this.storage.loadData());
        } else {
            this.taskList = new TaskList();
        }
    }


    /**
     * Starts running the Duchess program.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        try {
            new Duchess().run();
        } catch (DuchessException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Runs the Duchess program.
     * Displays opening greeting, interacts with user, and handles exceptions.
     */
    public void run() {
        try {
            while (this.isRunning) {
                String input = this.ui.nextLine();
                System.out.println(this.getResponse(input));
            }
        } catch (DuchessException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Exits the Duchess program.
     * Closes the scanner used for user input, saves the task list data, and sets the program state to not running.
     */
    public void exit() {
        assert this.isRunning;
        this.ui.closeScanner();
        this.storage.saveData(this.taskList);
        this.isRunning = false;
    }

    public String getOpeningGreeting() {
        return this.ui.openingGreeting();
    }


    protected String getResponse(String input) throws DuchessException {
        try {
            switch (Parser.parseCommand(input)) {
            case BYE:
                this.exit();
                return this.ui.showClosingGreeting();
            case LIST:
                return this.ui.showList(this.taskList);
            case TODO:
                Task toDoTask = this.taskList.addToDo(Parser.parseArgs(input));
                return this.ui.showAdd(toDoTask, this.taskList.getTaskCount(), "TODO");
            case EVENT:
                Task eventTask = this.taskList.addEvent(Parser.parseArgs(input));
                return this.ui.showAdd(eventTask, this.taskList.getTaskCount(), "EVENT");
            case DEADLINE:
                Task deadlineTask = this.taskList.addDeadline(Parser.parseArgs(input));
                return this.ui.showAdd(deadlineTask, this.taskList.getTaskCount(), "DEADLINE");
            case DELETE:
                int taskIndexToDelete = Integer.parseInt(Parser.parseArgs(input)) - 1;
                Task deletedTask = this.taskList.deleteTask(taskIndexToDelete);
                return this.ui.showDelete(deletedTask, this.taskList.getTaskCount());
            case FIND:
                String keywords = Parser.parseArgs(input);
                ArrayList<Pair<Integer, Task>> matchingTasks = this.taskList.findTasksByKeyword(keywords);
                return this.ui.showSubList(matchingTasks, "matching");
            case MARK:
                int taskIndexToMark = Integer.parseInt(Parser.parseArgs(input)) - 1;
                Task markedTask = this.taskList.markTaskAsDone(taskIndexToMark);
                return this.ui.showMarked(markedTask);
            case UNMARK:
                int taskIndexToUnmark = Integer.parseInt(Parser.parseArgs(input)) - 1;
                Task unmarkedTask = this.taskList.unmarkTaskAsDone(taskIndexToUnmark);
                return this.ui.showUnmarked(unmarkedTask);
            case DUPLICATES:
                ArrayList<Pair<Integer, Task>> duplicateTasks = this.taskList.listDuplicateTasks();
                return this.ui.showSubList(duplicateTasks, "duplicate");
            default:
                throw new DuchessException("Oh dear, I can't make out what that is.");
            }
        } catch (DuchessException e) {
            return e.getMessage();
        }
    }

    /**
     * Restarts the Duchess program by resetting the program state and user interface.
     * This method sets the program to running state and creates a new instance of the user interface.
     * After calling this method, the program will be ready to accept user input and execute commands.
     */
    public void restartDuchess() {
        this.isRunning = true;
        this.ui = new Ui();
    }
}

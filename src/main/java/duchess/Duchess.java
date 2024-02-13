package duchess;

import java.io.IOException;
import java.util.ArrayList;

import duchess.storage.Storage;
import duchess.task.Task;
import duchess.ui.Ui;
import duchess.parser.Parser;
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
        this.taskList = new TaskList();
        this.isRunning = true;
        ArrayList<Task> tasksStored = this.storage.loadData();
        if (!tasksStored.isEmpty()) {
            this.taskList = new TaskList(this.storage.loadData());
        }
        this.ui = new Ui();
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
                ArrayList<Pair<Integer, Task>> matchingTasks = taskList.findTasksByKeyword(keywords);
                return this.ui.showFind(matchingTasks);
            case MARK:
                int taskIndexToMark = Integer.parseInt(Parser.parseArgs(input)) - 1;
                Task markedTask = this.taskList.markTaskAsDone(taskIndexToMark);
                return this.ui.showMarked(markedTask);
            case UNMARK:
                int taskIndexToUnmark = Integer.parseInt(Parser.parseArgs(input)) - 1;
                Task unmarkedTask = this.taskList.unmarkTaskAsDone(taskIndexToUnmark);
                return this.ui.showUnmarked(unmarkedTask);
            default:
                throw new DuchessException("Oh dear, I can't make out what that is.");
            }
        } catch (DuchessException e) {
            return e.getMessage();
        }
    }

    /**
     * Main method to start the Duchess program.
     *
     * @param args command-line arguments (not used)
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
            ui.printOpeningGreeting();
            while (this.isRunning) {
                String input = this.ui.nextLine();
                System.out.println(this.getResponse(input));
            }
        } catch (DuchessException e) {
            System.out.println(e.getMessage());
        }
    }

    public void exit() {
        assert this.isRunning;
        this.ui.closeScanner();
        this.storage.saveData(this.taskList);
        this.isRunning = false;
    }
}

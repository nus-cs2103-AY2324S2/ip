package duchess;

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
public class Duchess{
    private static final String FILE_PATH = "./data/duchess.txt";
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a Duchess object.
     * Initializes storage, task list, and user interface components.
     *
     * @throws DuchessException if an error occurs during initialization
     */
    public Duchess() throws DuchessException {
        storage = new Storage(FILE_PATH);
        taskList = new TaskList();
        ArrayList<Task> tasksStored = storage.loadData();
        if (!tasksStored.isEmpty()) {
            taskList = new TaskList(storage.loadData());
        }
        ui = new Ui();
    }

    protected String getResponse(String input) throws DuchessException {
        try {
            switch (Parser.parseCommand(input)) {
            case BYE:
                return this.ui.showClosingGreeting();
            case LIST:
                return this.ui.showList(this.taskList);
            case TODO:
                Task toDoTask = this.taskList.addToDo(Parser.parseArgs(input));
                this.storage.saveData(this.taskList);
                return this.ui.showAdd(toDoTask, this.taskList.getTaskCount(), "TODO");
            case EVENT:
                Task eventTask = this.taskList.addEvent(Parser.parseArgs(input));
                this.storage.saveData(this.taskList);
                return this.ui.showAdd(eventTask, this.taskList.getTaskCount(), "EVENT");
            case DEADLINE:
                Task deadlineTask = this.taskList.addDeadline(Parser.parseArgs(input));
                this.storage.saveData(this.taskList);
                return this.ui.showAdd(deadlineTask, this.taskList.getTaskCount(), "DEADLINE");
            case DELETE:
                int taskIndexToDelete = Integer.parseInt(Parser.parseArgs(input)) - 1;
                Task deletedTask = this.taskList.deleteTask(taskIndexToDelete);
                this.storage.saveData(this.taskList);
                return this.ui.showDelete(deletedTask, this.taskList.getTaskCount());
            case FIND:
                String keywords = Parser.parseArgs(input);
                ArrayList<Pair<Integer, Task>> matchingTasks = taskList.findTasksByKeyword(keywords);
                return this.ui.showFind(matchingTasks);
            case MARK:
                int taskIndexToMark = Integer.parseInt(Parser.parseArgs(input)) - 1;
                Task markedTask = this.taskList.markTaskAsDone(taskIndexToMark);
                this.storage.saveData(this.taskList);
                return this.ui.showMarked(markedTask);
            case UNMARK:
                int taskIndexToUnmark = Integer.parseInt(Parser.parseArgs(input)) - 1;
                Task unmarkedTask = this.taskList.unmarkTaskAsDone(taskIndexToUnmark);
                this.storage.saveData(this.taskList);
                return this.ui.showUnmarked(unmarkedTask);
            default:
                //sth
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
        ui.printOpeningGreeting();
        try {
            ui.printEcho(taskList, storage);
        } catch (DuchessException e) {
            System.out.println(e.getMessage());
        } finally {
            //Close scanner
            ui.closeScanner();
        }
    }


}

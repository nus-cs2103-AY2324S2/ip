package lrbg.codriver;

import lrbg.codriver.command.Command;
import lrbg.codriver.data.exception.CoDriverException;
import lrbg.codriver.data.TaskList;
import lrbg.codriver.parser.Parser;
import lrbg.codriver.storage.Storage;
import lrbg.codriver.ui.Ui;

/**
 * The CoDriver program is a task manager that helps users keep track of their tasks.
 */
public class CoDriver{
    /** The greeting message. */
    public static final String GREETING_MESSAGE = "Hello! I'm CoDriver, your everyday AI companion!\n" +
            "What can I do for you?\n";
    /** The goodbye message. */
    public static final String GOODBYE_MESSAGE = "Goodbye! Have a great day!\n";
    /** The storage object that handles the loading and saving of tasks. */
    private Storage storage;
    /** The task list that stores the tasks. */
    private TaskList tasks;
    /** The user interface that interacts with the user. */
    private Ui ui;
    /** The boolean to check if the program should exit, for the GUI. */
    public boolean isExit = false;

    /**
     * Constructs a CoDriver object.
     *
     * @param filePath The file path to the file where the tasks are stored.
     */
    public CoDriver(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println(ui.showFileLoadingError());
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the CoDriver program.
     */
    public void run() {
        System.out.println(GREETING_MESSAGE);
        while (true) {
            String commandLine = ui.readCommand();
            System.out.println(ui.showLine());
            try {
                Command c = Parser.parse(commandLine);
                System.out.println(c.execute(tasks, ui, storage));
                if (c.isExit()) {
                    break;
                }
            } catch (CoDriverException e) {
                System.out.println(ui.showError(e));
            } catch (NumberFormatException e) {
                System.out.println(ui.showNumberFormatError());
            } finally {
                System.out.println(ui.showLine());
            }
        }
        ui.close();
        storage.save(this.tasks.toFileSaveString());
    }

    /**
     * Gets the response from the CoDriver program for the GUI.
     *
     * @param input The input from the user.
     * @return The response from the CoDriver program.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                this.isExit = true;
            }
            return c.execute(tasks, ui, storage);
        } catch (CoDriverException e) {
            return ui.showError(e);
        } catch (NumberFormatException e) {
            return ui.showNumberFormatError();
        }
    }

    public static void main(String[] args) {
        new CoDriver("./data/codriver.txt").run();
    }
}

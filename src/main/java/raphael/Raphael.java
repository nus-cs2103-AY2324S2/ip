package raphael;

import javafx.application.Platform;
import raphael.command.Command;
import raphael.exception.RaphaelException;
import raphael.parser.Parser;
import raphael.storage.Storage;
import raphael.task.TaskList;
import raphael.ui.Ui;

/**
 * The main class of Raphael.
 */
public class Raphael {
    public static final String LOGO = "\n"
            + "  _____                _                   _\n"
            + " |  __ \\              | |                 | |\n"
            + " | |__) | __ _  _ __  | |__    __ _   ___ | |\n"
            + " |  _  / / _` || '_ \\ | '_ \\  / _` | / _ \\| |\n"
            + " | | \\ \\| (_| || |_) || | | || (_| ||  __/| |\n"
            + " |_|  \\_\\\\__,_|| .__/ |_| |_| \\__,_| \\___||_|\n"
            + "               | |\n"
            + "               |_|\n";
    public static final String BOT_NAME = "raphael";
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * The mode of Raphael (normal or testing)
     */
    public enum Mode {
        NORMAL("./data/tasks.txt"),
        TEST("./data/test_tasks.txt");
        private String filePath;
        Mode(String filePath) {
            this.filePath = filePath;
        }
    };

    /**
     * The constructor of Raphael.
     * @param raphaelMode the mode of Raphael to be initialized.
     */
    public Raphael(Mode raphaelMode) {
        this.ui = new Ui();
        this.storage = new Storage(raphaelMode.filePath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (RaphaelException e) {
            System.out.println(e.getMessage());
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Default constructor for Raphael.
     */
    public Raphael() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(Mode.NORMAL.filePath);
    }
    /**
     * Get response from Raphael
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            if (c.isExit()) {
                Platform.exit();
            }
            return ui.getOutput();
        } catch (RaphaelException e) {
            return e.getMessage();
        }
    }

}

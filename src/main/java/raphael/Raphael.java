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
     * The constructor of Raphael.
     * @param filePath the file path of the task file to be read.
     */
    public Raphael(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (RaphaelException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }
    public Raphael() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage("./data/tasks.txt");
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

package raphael;
import raphael.parser.Parser;
import raphael.storage.Storage;
import raphael.task.TaskList;
import raphael.ui.Ui;
import raphael.command.Command;
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
    public Raphael(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (raphael.exception.RaphaelException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Activates Raphael.
     */
    private void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (raphael.exception.RaphaelException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Serves as the entry point of the whole program.
     *
     * @param args the command line argument upon running the binary file.
     */
    public static void main(String[] args) {
        new Raphael("./data/tasks.txt").run();
    }
}

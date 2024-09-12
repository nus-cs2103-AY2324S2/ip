package ganAnWo;

import java.io.FileNotFoundException;

import ganAnWo.command.Command;
import ganAnWo.exception.CommandInvalidException;
import javafx.scene.image.Image;


/**
 * Main code to run program.
 *
 *
 */
public class GanAnWo {

    private static final String CURRENT_WORKING_DIRECTORY = System.getProperty("user.dir");
    private static final String PATH = "/list.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/Kuromi.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/bot.jpg"));

    private boolean isExit;

    /**
     * Constructor for Duke
     *
     *
     * @param filePath X path of the saved file.
     */
    public GanAnWo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Constructor for Duke
     *
     *
     */
    public GanAnWo() {
        ui = new Ui();
        storage = new Storage(CURRENT_WORKING_DIRECTORY + PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showMessage(e.getMessage());
            tasks = new TaskList();
        }
        isExit = false;
        assert ui != null;
        assert storage != null;
        assert tasks != null;
        assert isExit == false;
        assert duke != null;
        assert user != null;
    }

    /**
     * Runs the duke program.
     *
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (CommandInvalidException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    /**
     * Generates response according to the given command.
     *
     *
     * @param input the given command.
     * @return the response according to the command.
     */
    public String getResponse(String input) {
        String output;
        try {
            String fullCommand = input;
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            output = c.getOut();
            isExit = c.isExit();
        } catch (CommandInvalidException e) {
            ui.showMessage(e.getMessage());
            output = e.getMessage();
        }
        return "GanAnWo answer: \n" + output;
    }

    /**
     * A getter for isExit.
     *
     * @return boolean represent isExit.
     */
    public boolean getIsExit() {
        return isExit;
    }

    public static void main(String[] args) {
        new GanAnWo(CURRENT_WORKING_DIRECTORY + PATH).run();
    }
}

package duke;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Duke class represents a task manager bot.
 */
public class Duke extends Application {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Gui gui;

    /**
     * Constructs a new Duke object.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            this.taskList = new TaskList(storage);
        } catch (DukeException e) {
            ui.printMessage(e.getMessage());
        }
    }

    public Duke() {
        this.ui = new Ui();
        try {
            this.storage = new Storage("./data/duke.txt");
            this.taskList = new TaskList(storage);
        } catch (DukeException e) {
            ui.printMessage(e.getMessage());
        }
    }

    /**
     * The main method that runs the bot.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Duke bot = new Duke("./data/duke.txt");
        bot.ui.showWelcome();

        String input = bot.ui.readCommand();
        Handler handler = new Handler(bot.taskList);

        while (!input.equals("bye")) {
            try {
                handler.handle(input);
            } catch (DukeException e) {
                bot.ui.printMessage(e.getMessage());
            }
            input = bot.ui.readCommand();
        }

        bot.ui.showGoodbye();
    }

    @Override
    public void start(Stage stage) {
        gui = new Gui();
        gui.showGui(stage);
    }
}

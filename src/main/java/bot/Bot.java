package bot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * The Duke class represents a task manager bot.
 */
public class Bot extends Application {
    protected TaskList taskList; 
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a new Duke object.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Bot(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            this.taskList = new TaskList(storage);
        } catch (DukeException e) {
            ui.printMessage(e.getMessage());
        }
    }

    public Bot() {
        this.ui = new Ui();
        try {
            this.storage = new Storage("./data/duke.txt");
            this.taskList = new TaskList(storage);
        } catch (DukeException e) {
            ui.printMessage(e.getMessage());
        }
    }

    /**
     * Runs the bot.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Bot bot = new Bot("./data/duke.txt");
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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Bot.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Bot");
            fxmlLoader.<MainWindow>getController().setDuke(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        return "Duke heard: " + input;
    }
}

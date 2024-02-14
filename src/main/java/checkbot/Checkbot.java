package checkbot;

import checkbot.command.Command;
import checkbot.exception.CheckbotException;
import checkbot.task.TodoList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Represents the main class of the Checkbot program.
 */
public class Checkbot extends Application {
    public static final String TASK_FILE_DIR = "./tasks.txt";

    private static final String CHECKBOT_AVATAR_DIR = "/images/checkbot.png";
    private static final String USER_AVATAR_DIR = "/images/DaUser.png";
    private final TodoList todoList;
    private final Storage storage;
    private final Parser parser;
    private final Ui ui;

    /**
     * Constructor for Checkbot.
     *
     * @param filePath The file path to the file where the tasks are stored.
     */
    public Checkbot(String filePath) {
        this.storage = new Storage(filePath);
        this.todoList = this.storage.loadTasks();
        this.parser = new Parser();
        this.ui = new Ui(
                new Image(this.getClass().getResourceAsStream(CHECKBOT_AVATAR_DIR)),
                new Image(this.getClass().getResourceAsStream(USER_AVATAR_DIR)),
                this::parseUserInput);
    }

    /**
     * Constructor for Checkbot that uses the default filepath for tasks.txt
     */
    public Checkbot() {
        this(TASK_FILE_DIR);
    }

    private void parseUserInput(String input) {
        try {
            Command c = parser.parse(input.strip());
            c.executeCommand(todoList, storage, ui);
            if (c.isBye()) {
                Platform.exit();
            }
        } catch (CheckbotException e) {
            ui.showError(e);
        }
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Checkbot");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.setScene(ui.initializeScene());
        stage.show();

        ui.showWelcome();
    }
}

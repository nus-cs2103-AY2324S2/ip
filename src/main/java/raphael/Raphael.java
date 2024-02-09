package raphael;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import raphael.command.Command;
import raphael.parser.Parser;
import raphael.storage.Storage;
import raphael.task.TaskList;
import raphael.ui.Ui;

/**
 * The main class of Raphael.
 */
public class Raphael extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
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
        } catch (raphael.exception.RaphaelException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }
    public Raphael() {
        this.ui = new Ui();
        this.storage = new Storage("./data/tasks.txt");
        this.scrollPane = new ScrollPane();
        this.dialogContainer = new VBox();
        this.userInput = new TextField();
        this.sendButton = new Button("send");
    }
    private void setupGUI(Stage stage) {
        AnchorPane mainLayout = new AnchorPane();
        this.scene = new Scene(mainLayout);

        stage.setTitle("Raphael");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        this.scrollPane.setPrefSize(385, 535);
        this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        this.scrollPane.setVvalue(1.0);
        this.scrollPane.setFitToWidth(true);

        this.dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        this.userInput.setPrefWidth(325.0);
        this.sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(this.scrollPane, 1.0);

        AnchorPane.setBottomAnchor(this.sendButton, 1.0);
        AnchorPane.setRightAnchor(this.sendButton, 1.0);

        AnchorPane.setLeftAnchor(this.userInput, 1.0);
        AnchorPane.setBottomAnchor(this.userInput, 1.0);

        this.scrollPane.setContent(dialogContainer);

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
    }

    /**
     * Activates Raphael.
     */
    @Override
    public void start(Stage stage) {
//        this.ui.showWelcome();


        stage.setScene(this.scene);
        stage.show();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine();
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (raphael.exception.RaphaelException e) {
//                ui.showError(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
//        }
    }
}

package duke;


import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;




/**
 * The main class for the Duke application. Duke is a simple task management
 * application that allows users to add, delete, mark as done, and list tasks.
 * Tasks are stored in a file specified by the user upon starting the application.
 * Duke provides a graphical user interface (GUI) for users to interact with.
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructs a Duke object with the specified file path.
     *
     * @param filePath The file path where tasks will be stored.
     */
    public Duke(String filePath) {
        if (filePath != null) {
            ui = new Ui();
            storage = new Storage(filePath);

            try {
                tasks = new TaskList(storage.load());
            } catch (DukeException e) {
                ui.showLoadingError();
                tasks = new TaskList();
            }
        }
    }

    /**
     * Constructs a Duke object with the specified file path.
     */
    public Duke() {
        this("./data/duke.txt");
    }

    /**
     * Processes the user input and returns Duke's response.
     *
     * @param input The user input string.
     * @return The response from Duke.
     */
    public String getResponse(String input) {
        try {
            ui.clearResponse();
            Parser.parse(input, tasks, ui);
            return ui.getResponse();
        } catch (DukeException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "An unexpected error occurred: " + e.getMessage();
        }
    }

    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeResponse = getResponse(userText);

        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, new ImageView(user).getImage()),
            DialogBox.getDukeDialog(dukeResponse, new ImageView(duke).getImage())
        );
        userInput.clear();
    }



    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        String welcomeMessage = ui.showWelcome();
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(welcomeMessage, new ImageView(duke).getImage())
        );
        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }



    /**
     * Main method to start the Duke application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }
}

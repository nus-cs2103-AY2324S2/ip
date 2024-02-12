package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Duke extends Application {
    private Storage storage;
    private ArrayList<Task> tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Image userImage = new Image(getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke() {
        // Initialize storage and other components
        storage = new Storage("./data/duke.txt");
        tasks = Storage.loadTasks();
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        ui = new Ui();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        AnchorPane mainLayout = new AnchorPane();
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        userInput = new TextField();
        sendButton = new Button("Send");

        dialogContainer.prefWidthProperty().bind(scrollPane.widthProperty());
        scrollPane.setContent(dialogContainer);
        scrollPane.setFitToWidth(true);

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        Scene scene = new Scene(mainLayout, 400, 600);

        stage.setScene(scene);
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.show();

        sendButton.setOnAction(event -> handleUserInput());
        userInput.setOnAction(event -> handleUserInput());

        displayWelcomeMessage();
    }

    private void displayWelcomeMessage() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(ui.message(), dukeImage));
    }

    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        String response = String.valueOf(getResponse(input));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();
    }

    boolean getResponse(String input) {
        Parser parser = new Parser(input, tasks);
        boolean isSuccess = parser.parseCommand();
        if (isSuccess) {
            Storage.saveTasks(tasks);
            ui.finalMessage();
        } else {
            ui.errorMessage();
        }
        return isSuccess;
    }
}

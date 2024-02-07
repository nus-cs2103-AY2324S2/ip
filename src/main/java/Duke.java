import duke.command.Command;
import duke.command.CommandType;
import duke.helpers.Parser;
import duke.helpers.Storage;
import duke.helpers.Ui;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.geometry.Insets;
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
 * Duke class
 */
public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/corgi.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/golden_retriever.jpg"));

    /**
     * Constructor of Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(CommandType.FILEPATH.toString());
        this.storage.getStorageFromHardDisk(this.tasks);
    }


    /**
     * Starts communication with chatbot
     */
    public void startChat() {
        ui.welcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.readCommand();

            Command command = Parser.parse(fullCommand);
            command.execute(tasks, ui, storage);
            isExit = command.isExit();
        }
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, new ImageView(user)),
//                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
//        );
//        userText.setPadding(new Insets(10, 10, 10, 10));
//        dukeText.setPadding(new Insets(10, 10, 10, 10));
//        dialogContainer.setPadding(new Insets(10));
//        userInput.clear();
//    }

//    @Override
//    public void start(Stage stage) {
//        // Setting up required components
//        Button sendButton = new Button();
//
//
//        // The container for the content of the chat to scroll.
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
//        // Formatting the window to look as expected.
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        //You will need to import `javafx.scene.layout.Region` for this.
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//        // Add functionality to handle user input
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//
//        // scroll down to the end every time dialogContainer's height changes
//        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));
//
//        stage.setScene(scene);
//        stage.show();
//    }

    public static void main(String[] args) {
        new Duke().startChat();
    }
}

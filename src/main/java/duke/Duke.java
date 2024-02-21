package duke;

import duke.control.DialogBox;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Scanner;

/**
 * Represents an instance of the chatbot Elias, which is
 * this project's rendition of project Duke. Elias is able
 * to take in commands to add, modify or delete items in a
 * ItemList. On exit, Items in the ItemList are serialized
 * and stored in the duke.txt file. The duke.txt file is created
 * when no such file exists, and is created in the ./data directory
 * unless no such directory exists, where it is added to the root.
 */
public class Duke extends Application {
    public static Duke instance;
    private final Storage storage;
    private final ItemList itemList;
    public final UI ui;
    private Parser parser;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/elias.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Creates a new instance of Duke as Elias.
     */

    public Duke() {
        this.storage = new Storage("./data/duke.txt");
        this.itemList = storage.readFromFile();
        this.ui = new UI();
        this.parser = new Parser(itemList);
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
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

        //You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(this.ui.getLogo(), duke));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(this.ui.getGreet(), duke));

        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    public void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );

        if (userInput.getText().equals("bye")) {
            Platform.exit();
        }

        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        // to abstract out UI stuff using fxml
        String out = "";
        if (!input.equals("bye")) {
            try {
                out = this.parser.parse(input);
                return out;
            } catch (CustomExceptions.UnrecognizedCommandException e) {
                return "Sorry I do not recognize this command: " + input;
            } catch (CustomExceptions e) {
                return e.getMessage();
            }
        } else {
            this.storage.writeToFile(this.itemList);
            return this.ui.getBye();
        }
    }

}

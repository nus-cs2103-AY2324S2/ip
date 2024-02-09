package duke.rahbot;

import java.io.IOException;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Output;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.PauseTransition;
import javafx.util.Duration;


public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/me.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/brothers.jpeg"));
    
    public Duke() {
        // ...
    }

    @Override
    public void start(Stage stage) {
        // Step 1. Setting up required components

        // The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        // Step 2. Formatting the window to look as expected
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

        // Here, after setting up the UI but before the stage is shown
        showWelcomeMessage();

        // Add functionality to handle user input.
        addInputHandlers();

        stage.setScene(scene);
        stage.show();
    }

    private void addInputHandlers() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    
    private void showWelcomeMessage() {
        Output output = new Output(new Parser(new Storage()), new Storage());
        String welcomeMsg = output.welcome();
        HBox dialogHBox = createDialogHBox(welcomeMsg, duke); // Use the duke Image object
        dialogContainer.getChildren().add(dialogHBox); // Add HBox to the dialog container
    }
    // Inside your Duke class

    private HBox createDialogHBox(String text, Image image) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100); // Adjust based on your UI needs
        imageView.setFitWidth(100); // Adjust based on your UI needs
        imageView.setPreserveRatio(true);

        HBox hbox = new HBox(10); // 10 is the spacing between elements in the HBox
        hbox.setAlignment(Pos.CENTER_LEFT); // Aligns children within the HBox
        hbox.getChildren().addAll(imageView, textToAdd);

        return hbox;
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
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        Storage storage = new Storage();
        Parser parser = new Parser(storage);
        Output output = new Output(parser, storage);
        
        if (input.equalsIgnoreCase("bye")) {
            String byeMessage = output.execute(input); // Assume this returns the "Bye" message
            Platform.runLater(() -> {
                PauseTransition delay = new PauseTransition(Duration.seconds(1.5)); // Adjust the delay as needed
                delay.setOnFinished(e -> Platform.exit());
                delay.play();
            });
            return byeMessage;
        } else {
            return output.execute(input);
        }
    }

}
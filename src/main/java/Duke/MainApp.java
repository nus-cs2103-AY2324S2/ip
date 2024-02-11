package Duke;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class MainApp extends Application{

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Duke duke;
    @Override
    public void init() throws Exception {
        duke = new Duke();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        Button sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        // Formatting
        stage.setTitle("Shirmin");
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

        //user input.
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    private final Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/lulu.jpg")));
    private final Image min = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Shirmin.jpg")));

    /** Functions Status
     * 9/9 complete  hell yea        - retest whenever big changes are made
     * LIST           WORKING
     * MARK           WORKING
     * UNMARK         WORKING
     * TODO           WORKING
     * DEADLINE       WORKING
     * EVENT          WORKING
     * DELETE         WORKING
     * FIND           WORKING
     * BYE            WORKING
     */
    private void handleUserInput() {
        String input = userInput.getText();

        if (!input.isEmpty()) {
            if (input.trim().equalsIgnoreCase("bye")) {
                Label goodbyeText = new Label("Goodbye! See you again!");
                ImageView dukeImageView = new ImageView(min); // 'min' is the image
                dukeImageView.setFitHeight(50);
                dukeImageView.setFitWidth(50);
                DialogBox goodbyeBox = new DialogBox(goodbyeText, dukeImageView);
                dialogContainer.getChildren().add(goodbyeBox);

                Platform.runLater(() -> {
                    try {
                        // Wait a bit for the user to see the message
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                    Platform.exit();
                });
            } else {
                handleOtherInputs(input);
            }
            userInput.clear(); // Clear the input field after processing
        }
    }

    /**
     * Handles non-bye inputs, encapsulating the previous logic for handling user inputs.
     * @param input The user input to handle.
     */
    private void handleOtherInputs(String input) {
        // Display user input with user image
        Label userText = new Label(input);
        ImageView userImageView = new ImageView(user); // Assuming 'user' is the Image for the user
        userImageView.setFitHeight(50); // Adjust size as needed
        userImageView.setFitWidth(50);
        DialogBox userInputBox = new DialogBox(userText, userImageView);

        // Get Duke's response and display it with Duke's image
        String response = duke.runCommand(input);
        Label dukeText = new Label(response);
        ImageView dukeImageView = new ImageView(min);
        dukeImageView.setFitHeight(50);
        dukeImageView.setFitWidth(50);
        DialogBox dukeResponseBox = new DialogBox(dukeText, dukeImageView);

        dialogContainer.getChildren().addAll(userInputBox, dukeResponseBox);
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}



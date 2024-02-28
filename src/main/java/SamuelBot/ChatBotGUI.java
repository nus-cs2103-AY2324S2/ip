package SamuelBot;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ChatBotGUI extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Samuelbot samuelbot;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/Mario.png"));
    private Image Samuel = new Image(this.getClass().getResourceAsStream("/images/Luigi.png"));

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Setting up required components

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

        stage.setTitle("SamuelBot");
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

        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());

        // Initialize Samuelbot instance
        samuelbot = new Samuelbot();

        // Display welcome message
        dialogContainer.getChildren().add(DialogBox.getDukeDialog("Hello from SamuelBot! What can I do for you?", Samuel));

        // Set the action for closing the stage
        stage.setOnCloseRequest(event -> {
            // Handle the close event
            // For example, you can exit the application
            Platform.exit();
            System.exit(0);
        });
    }


    /**
     * Handles user input and displays SamuelBot's response in the dialog container.
     */
    /**
     * Handles user input and displays SamuelBot's response in the dialog container.
     */
    private void handleUserInput() {
        String userInputText = userInput.getText();
        String samuelResponse = samuelbot.getResponse(userInputText);

        // Create DialogBox for user input
        DialogBox userDialog = DialogBox.getUserDialog(userInputText, user);

        // Create DialogBox for Samuel's response
        DialogBox samuelDialog = DialogBox.getDukeDialog(samuelResponse, Samuel);

        // Add both DialogBoxes to the dialog container
        dialogContainer.getChildren().addAll(userDialog, samuelDialog);

        // Clear the user input field
        userInput.clear();

        // Check if the user input is "bye"
        if (userInputText.equalsIgnoreCase("bye")) {
            // Save tasks before closing the application
            samuelbot.saveTasksToFile();

            new Thread(() -> {
                try {
                    Thread.sleep(3000); // Wait for 3 seconds
                    Platform.exit(); // Close the JavaFX application
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


}

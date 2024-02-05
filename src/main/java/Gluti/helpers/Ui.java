package Gluti.helpers;

import Gluti.utils.GlutiException;

import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the control hub for user input and filestorage for user
 */
public class Ui extends Application {
    private Parser parser;
    private boolean isExit ;
    private Scanner sc;
    private VBox  outputArea;
    /**
     * Initializes a Ui instance and sets the status to "working"
     * @param fStorage the filestorage object that is going to be used in the program
     */
    public Ui(FileStorage fStorage){
        this.parser = new Parser(fStorage, this::updateOutputArea);
        isExit  = false;
        sc = new Scanner(System.in);
    }

    /**
     * Runs the program loop and calls the Parser to parse user inputs
     * @throws GlutiException Exceptions caught during parsing
     */
    public void run(Stage primaryStage) throws GlutiException {
//        parser.parse(sc.nextLine());
//        while(!isExit) {
//            parser.parse(sc.nextLine());
//            isExit = parser.isLooping();
//        }
        start(primaryStage);
    }

    @Override
    public void start(Stage primaryStage) throws GlutiException {
        primaryStage.setTitle("Gluti Chatbot");
        ImageView userImageView = new ImageView(new Image(getClass().getClassLoader().getResource("data/usericon.jpg").toExternalForm()));
        userImageView.setFitWidth(50);
        userImageView.setFitHeight(50);
        TextArea inputField = new TextArea();
        outputArea = new VBox ();
        outputArea.setPadding(new Insets(10));

        // Button to trigger an action
        Button actionButton = new Button("Enter");
        actionButton.setOnAction(event -> {
            String inputText = inputField.getText();
            inputField.clear();
            try {
                processInput(inputText);
            } catch (Exception e) {
                try {
                    throw new GlutiException("Error!");
                } catch (GlutiException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        VBox mainBox = new VBox(10,
                outputArea,
                createInputBox(inputField, actionButton)
        );
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {
            primaryStage.close();
        });

        //rootLayout.getChildren().addAll(new Label("Chatbox:"), outputArea, new Label("Input:"), inputField, actionButton, exitButton);

        //HBox mainBox = new HBox(20, inputBox,imageBox, resultBox);
        //mainBox.setPadding(new Insets(20));
        // Create a scene and set it in the stage
        Scene scene = new Scene(mainBox, 600, 400);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();

        String logo = "Hello! I'm Gluti\n" +
                "What can I do for you?";

        //outputArea.appendText(logo + "\n");

        //while (!isExit) {
            // For simplicity, this uses a blocking call to Scanner. You might need to handle input asynchronously.
//            parser.parse(sc.nextLine());
//            isExit = parser.isLooping();
        //}
    }
    private HBox createInputBox(TextArea userInputTextArea, Button sendButton) {
        HBox inputBox = new HBox(10, userInputTextArea, sendButton);
        HBox.setHgrow(userInputTextArea, Priority.ALWAYS);
        return inputBox;
    }

    private void processInput(String input) throws GlutiException {
        // Perform any processing here
        if (!parser.isLooping()) {
            try {
                String output = parser.parse(input);

                // Display the output in the TextArea
                //outputArea.appendText(output + "\n");
                updateOutputArea(output);
            } catch (GlutiException e) {
                // Handle exceptions, you might want to display them in the UI
                //outputArea.appendText("Error: " + e.getMessage() + "\n");
                updateOutputArea("Error: " + e.getMessage());
            }
        } else {
            //outputArea.appendText("You already said bye :(");
        }
    }
    private void updateOutputArea(String message) {
        // Update the TextArea with the received message
        //outputArea.appendText(message + "\n");
        ImageView botImageView = new ImageView(new Image(getClass().getClassLoader().getResource("data/Gluticon.png").toExternalForm()));
        botImageView.setFitHeight(50);
        botImageView.setFitWidth(50);
        addMessage(message,botImageView);
    }
    private void addMessage(String message, ImageView imageView) {
        // Append to the existing message box if the sender is the same
        HBox messageBox = new HBox(10, imageView, new TextArea(message));
        outputArea.getChildren().add(messageBox);
    }
}

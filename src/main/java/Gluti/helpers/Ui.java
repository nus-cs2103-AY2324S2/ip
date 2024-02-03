package Gluti.helpers;

import Gluti.utils.GlutiException;

import java.util.Scanner;
import java.util.function.Consumer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the control hub for user input and filestorage for user
 */
public class Ui extends Application {
    private Parser parser;
    private boolean isExit ;
    private Scanner sc;
    private TextArea outputArea;
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
        primaryStage.setTitle("Gluti");

        TextField inputField = new TextField();
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);

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
        VBox rootLayout = new VBox(10); // Vertical layout with spacing
        rootLayout.getChildren().addAll(new Label("Chatbox:"), outputArea, new Label("Input:"), inputField, actionButton);

        // Create a scene and set it in the stage
        Scene scene = new Scene(rootLayout, 820, 400);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();

        String logo = "Hello! I'm Gluti\n" +
                "What can I do for you?";

        outputArea.appendText(logo + "\n");

        //while (!isExit) {
            // For simplicity, this uses a blocking call to Scanner. You might need to handle input asynchronously.
//            parser.parse(sc.nextLine());
//            isExit = parser.isLooping();
        //}
    }
    private void processInput(String input) throws GlutiException {
        // Perform any processing here
        if (!parser.isLooping()) {
            try {
                String output = parser.parse(input);

                // Display the output in the TextArea
                outputArea.appendText(output + "\n");
            } catch (GlutiException e) {
                // Handle exceptions, you might want to display them in the UI
                outputArea.appendText("Error: " + e.getMessage() + "\n");
            }
        } else {
            outputArea.appendText("You already said bye :(");
        }
    }
    private void updateOutputArea(String message) {
        // Update the TextArea with the received message
        outputArea.appendText(message + "\n");
    }
}

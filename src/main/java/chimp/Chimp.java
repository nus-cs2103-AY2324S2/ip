package chimp;

import java.security.InvalidParameterException;
import java.util.Scanner;
import chimp.command.Command;
import chimp.controls.DialogBox;
import chimp.core.*;
import chimp.exception.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Chimp extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(getClass().getResourceAsStream("/images/DaDuke.png"));

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Chimp() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage();
    }

    @Override
    public void start(Stage stage) {
        AnchorPane mainLayout = getMainLayout(stage);

        formatWindow(stage, mainLayout);

        addFunctionality();

    }

    private void addFunctionality() {
        // Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private void formatWindow(Stage stage, AnchorPane mainLayout) {
        // Step 2. Formatting the window to look as expected
        stage.setTitle("Chimp");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private AnchorPane getMainLayout(Stage stage) {
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

        stage.setScene(scene);
        stage.show();
        return mainLayout;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing
     * Chimp's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        Label userText = new Label(input);
        
        String response = generateResponse();
        assert response != null : "response should not be null";

        Label dukeText = new Label(response);
        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(user)),
                new DialogBox(dukeText, new ImageView(duke)));
        userInput.clear();
    }

    private String generateResponse(){
        String input = userInput.getText();
        try {
            Command c = Parser.parse(input);
            String response = c.execute(this.tasks, this.ui, this.storage);
            // TODO: This does not belong here
            if (c.isExit()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
            return response;
        } catch (InvalidCommandException | CommandParseException | CommandExecuteException e) {
            return ui.say("hoo");
        } catch (IndexOutOfBoundsException e) {
            return ui.say("hoo");
        }
    }

    /**
     * The main method is the entry point of the Chimp program.
     * It initializes the user interface, task list, and storage objects.
     * It then enters a loop to continuously read user input, parse it into a
     * command,
     * execute the command, and save the output to a file.
     * If an invalid command or exception occurs, an error message is displayed.
     * The loop continues until the user enters an exit command.
     * Finally, the scanner is closed.
     *
     * @param args the command line arguments
     * @throws InvalidParameterException if an invalid parameter is passed
     */
    public static void main(String[] args) throws InvalidParameterException {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        Storage storage = new Storage();
        ui.say("greet");

        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String inp = sc.nextLine();
            try {
                Command c = Parser.parse(inp);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                Storage.saveOutputToFile(tasks.toString());
            } catch (InvalidCommandException
                    | CommandParseException
                    | CommandExecuteException e) {
                ui.say("hoo");
            } catch (IndexOutOfBoundsException e) {
                ui.say("hoo");
            }
        }
        sc.close();
    }
}

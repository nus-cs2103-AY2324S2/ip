package maltese;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import maltese.action.Action;
import maltese.action.TaskList;
import maltese.exception.MalteseException;





/**
 * maltese is a task management application that allows users to manage their tasks.
 */
public class Maltese extends Application {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/maltese1.png"));
    private Image owner = new Image(this.getClass().getResourceAsStream("/images/maltese2.png"));

    public Maltese() {
    }

    /**
     * Constructs a maltese object with the specified file path.
     *
     * @param filePath The file path for storing tasks.
     */
    public Maltese(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = storage.loadFromFile();
        } catch (IOException e) {
            System.out.println("Sorry " + e.getMessage());
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Runs the maltese application, prompting the user for commands and executing them until the exit command is given.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            String command = ui.getUserInput();
            try {
                Action response = Parser.parseCommand(command, taskList, storage);
                storage.writeToFile(taskList);
                isExit = response.isExit();
            } catch (MalteseException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }


    String getResponse(String input) {
        boolean isExit = false;
        try {
            String command = input;
            Action response = Parser.parseCommand(command, taskList, storage);
            if (response != null) {
                storage.writeToFile(taskList);
                return response.getResponse();
            } else {
                // Handle the case where response is null
                return "Error: Unexpected null response";
            }
        } catch (MalteseException | IOException e) {
            return String.format("%s\n", e.getMessage());
        }
    }



    /**
     * The entry point for the maltese application.
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Maltese("./data/tasks.txt").run();
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
        stage.setTitle("maltese");
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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and other containing maltese's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String malteseText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getMalteseDialog(malteseText, owner)
        );
        userInput.clear();
    }



}








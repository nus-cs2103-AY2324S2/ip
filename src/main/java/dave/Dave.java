package dave;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import dave.commands.Command;
import dave.exceptions.ChatbotException;

import dave.DialogBox;

public class Dave extends Application {
    /** The output file. */
    private Storage storage;
    /** The task list to record tasks. */
    private TaskList taskList;
    /** The UI to print feedback to user. */
    private Ui daveUi;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image dave = new Image(this.getClass().getResourceAsStream("/images/dave.png"));

    public Dave() {

    }

    /**
     * Creates new Dave object.
     * This is the chatbot Dave.
     * 
     * @param filepath File path to where tasks are stored for output.
     */
    public Dave(String filepath) {
        daveUi = new Ui();
        storage = new Storage(filepath);
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException exc) {
            taskList = new TaskList();
        }
    }

    /**
     * Runs the chatbot Dave.
     * Operations do not stop until the user has given the exit command, "bye".
     */
    public void run() {
        daveUi.sayHello();
        boolean isExit = false;
        if (taskList.getNumberOfTasks() != 0) {
            daveUi.showLoadingSuccess(taskList.getNumberOfTasks());
        } else {
            daveUi.showLoadingError();
        }
        while (!isExit) {
            try {
                String fullCommand = daveUi.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, daveUi, storage);
                isExit = c.isExit();
            } catch (ChatbotException exc) {
                daveUi.showError(exc.getMessage());
            }
        }
    }

    /**
     * The main program.
     * @param args
     */
    public static void main(String[] args) {
        new Dave("data/tasks.txt").run();
    }

    @Override
    public void start(Stage stage) {
        // components
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

        // formatting window of app

        stage.setTitle("Dave");
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
        // make scroll pane scroll automatically
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // functionality to handle user input
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
    
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label daveText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDaveDialog(daveText, new ImageView(dave))
        );
        userInput.clear();
    }

    // replace this (generate response to user input)
    private String getResponse(String input) {
        return "Dave heard: " + input;
    }

}
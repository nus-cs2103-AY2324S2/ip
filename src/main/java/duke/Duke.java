package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import duke.command.Command;
import duke.utility.DukeException;
import duke.utility.Parser;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;
import duke.task.Task;

/**
 * Class that represents the Duke Chatbot.
 */
public class Duke extends Application {
    /** TaskList Object to be used to store Tasks. */
    private TaskList taskList;
    /** Ui Object for User Interactions. */
    private Ui userInterface;
    /** Storage Object to store and load Tasklist states. */
    private Storage fileStorage;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/PenguUser.png"));
    private Image pengu = new Image(this.getClass().getResourceAsStream("/images/PenguBot.png"));

    /**
     * Constucts a Duke Object that will be loaded with existing TaskList state.
     *
     * @param FilePath FilePath of file to be used to load TaskList stored.
     * @throws DukeException
     * @throws IOException
     */
    public Duke(String FilePath) throws DukeException, IOException {
        this.userInterface = new Ui();
        this.fileStorage = new Storage(FilePath);
    }

    public Duke() throws DukeException, IOException {
        this.userInterface = new Ui();
        this.fileStorage = new Storage("./data/tasks.txt");
        userInterface.showWelcome();
        if (this.fileStorage.isOccupied) {
            ArrayList<Task> loadedList = fileStorage.loadStorage();
            taskList = new TaskList(loadedList);
        } else {
            taskList = new TaskList();
        }
    }

    /**
     * Runs the Duke Chatbot.
     * 
     * @throws DukeException
     * @throws IOException
     */
    public void run() throws DukeException, IOException {

    }
    @Override
    public void start(Stage stage) {
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

        stage.setTitle("Pengu");
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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event -> {
            handleUserInput();
        }));

        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));

    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, pengu)
        );
        userInput.clear();
    }

    protected String getResponse(String input) {
        try {
            Command c = Parser.parseInstructions(input);
            return c.execute(taskList, userInterface, fileStorage);
        } catch (DukeException e) {
            return userInterface.showError(e.getMessage());
        }
    }
}

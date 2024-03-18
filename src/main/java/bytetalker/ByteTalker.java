package bytetalker;

import bytetalker.task.TaskList;
import bytetalker.ui.Ui;
import bytetalker.storage.Storage;
import bytetalker.parser.Parser;

import graphics.DialogBox;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The ByteTalker program implements a chatbot where it processes user input.
 *
 * @author Junseo Kim
 * @version 0.1
 * @since 2024-01-28
 */
public class ByteTalker extends Application {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    boolean isExit = false;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image byteTalker = new Image(this.getClass().getResourceAsStream("/images/chatbot.png"));

    public ByteTalker() {
        ui = new Ui();
        storage = new Storage("./data/ByteTalker.txt");
        storage.setupDirectoryAndFile();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundErrorMsg();
            isExit = true;
        }
    }

    /**
     * Determines the response based on the user input.
     * User input is processed and the chatbot gives a reponse as a message to indicate successful or unsuccessful
     * execution.
     *
     * @param input User input as a string.
     * @return Message indicating successful or unsuccessful execution of the program.
     */
    private String getResponse(String input) {
        String[] splitMessages = Parser.parse(input);
        if (input.equals("bye")) {
            Platform.exit();
            return this.ui.showBye();
        } else if (input.equals("list")) {
            return this.ui.returnList(this.tasks.getTasks());
        } else if (splitMessages[0].equals("mark")) {
            return this.tasks.markTask(splitMessages, storage, ui);
        } else if (splitMessages[0].equals("unmark")) {
            return this.tasks.unmarkTask(splitMessages, storage, ui);
        } else if (splitMessages[0].equals("delete")) {
            return this.tasks.deleteTask(splitMessages, storage, ui);
        } else if (splitMessages[0].equals("find")) {
            return this.tasks.findTask(splitMessages, ui);
        } else if (splitMessages[0].equals("update")) {
            return this.tasks.editTask(splitMessages, storage, ui);
        } else {
            return this.tasks.addTask(splitMessages, storage, ui);
        }
    }

    /**
     * Creates a Gui for the chatbot.
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
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

        setWindow(stage, mainLayout);

        //functionality
        handleActions();

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.getChildren().add(DialogBox.getByteTalkerDialog(new Label(this.ui.showWelcome()),
                new ImageView(byteTalker)));
    }

    private void setAcnhorPane() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private void handleActions() {
        sendButton.setOnMouseClicked((event -> {
            handleUserInput();
        }));

        userInput.setOnAction((event -> {
            handleUserInput();
        }));
    }

    private void setStage(Stage stage) {
        stage.setTitle("ByteTalker");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }

    private void setScrollPane() {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    private void setPref() {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(340.0);

        sendButton.setPrefWidth(55.0);
    }

    private void setWindow(Stage stage, AnchorPane mainLayout) {
        stage.setScene(scene);
        stage.show();

        setStage(stage);

        mainLayout.setPrefSize(400.0, 600.0);

        setScrollPane();

        setPref();

        setAcnhorPane();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears teh user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label botText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getByteTalkerDialog(botText, new ImageView(byteTalker))
        );
        userInput.clear();
    }
}

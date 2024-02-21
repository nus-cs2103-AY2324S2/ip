package bytetalker;

import bytetalker.task.TaskList;
import bytetalker.ui.Ui;
import bytetalker.storage.Storage;
import bytetalker.parser.Parser;

import graphics.DialogBox;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
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
        storage = new Storage("./data/bytetalker.ByteTalker.txt");
        storage.setupDirectoryAndFile();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundErrorMsg();
            isExit = true;
        }
    }

    public ByteTalker(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        storage.setupDirectoryAndFile();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundErrorMsg();
            isExit = true;
        }
    }

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
            return this.tasks.deleteTask(Integer.parseInt(splitMessages[1]), storage, ui);
        } else if (splitMessages[0].equals("find")) {
            return this.tasks.findTask(splitMessages, ui);
        } else {
            return this.tasks.determineTask(splitMessages, storage, ui);
        }
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

        stage.setTitle("ByteTalker");
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

        userInput.setPrefWidth(340.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //functionality
        sendButton.setOnMouseClicked((event -> {
            handleUserInput();
        }));

        userInput.setOnAction((event -> {
            handleUserInput();
        }));

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.getChildren().add(DialogBox.getByteTalkerDialog(new Label(this.ui.showWelcome()),
                new ImageView(byteTalker)));
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
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

    /**
     * This is the main method which starts the chatbot by running run method.
     * @param args Unused.
     */
    /*
    public static void main(String[] args) {
        new ByteTalker("./data/bytetalker.ByteTalker.txt").run();
    }
     */
}

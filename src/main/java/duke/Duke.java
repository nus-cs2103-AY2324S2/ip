package duke;

import duke.exceptions.InvalidTaskException;
import duke.frontend.DialogBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * This class represents a running instance of Duke, the task list!
 */
public class Duke extends Application {

    private Storage manager;

    private Parser parser;

    private TaskList history;

    private UI ui;

    private ScrollPane scrollPane;

    private VBox dialogContainer;

    private TextField userInput;

    private Button sendButton;

    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("../images/user.png"));

    private Image duke = new Image(this.getClass().getResourceAsStream("../images/shuheng.png"));

    boolean isExit = false;

    /**
     * Constructor for duke application.
     */
    public Duke() {
        this.manager = new Storage("data");
        this.manager.createLog();
        this.parser = new Parser();
        this.history = new TaskList(this.manager);
        this.ui = new UI(manager, parser, history);
    }
    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add.
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }
    /**
     * Creates two dialog boxes for user and shuheng replies.
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
            new DialogBox(userText, new ImageView(user)),
            new DialogBox(dukeText, new ImageView(duke))
        );
        try{
            isExit =
                this.parser.getCommand(userInput.getText().split(" ")).equals(UI.Command.BYE);
        } catch (InvalidTaskException e){
            System.out.println(e);
        }
        userInput.clear();
    }

    /**
     * Gets response from shuheng!
     *
     * @param input The user's input into the chatbot.
     */
    private String getResponse(String input) {
        return this.ui.run(input);
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        userInput = new TextField();
        sendButton = new Button("Send");
        sendButton.setOnMouseClicked((e) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            handleUserInput();
            if (isExit) {
                System.exit(0);
            }
        });
        userInput.setOnAction((e) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            handleUserInput();
            if (isExit) {
                System.exit(0);
            }
        });
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);
        stage.setTitle("shuheng");
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
        stage.setScene(scene);
        stage.show();
    }
}

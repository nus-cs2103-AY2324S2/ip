package kervyn;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kervyn.FXControls.DialogBox;
import kervyn.Tasks.TaskList;


/**
 * The Ui class is responsible for handling user interactions.
 * It captures user input and displays output from the chatbot.
 */
public class Ui {

    private final String CHATBOTNAME = "Kervyn";

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image kervyn = new Image(this.getClass().getResourceAsStream("/images/bot.png"));
    private TaskList taskList;

    /**
     * Constructs a Ui instance.
     */
    public Ui() {}

    /**
     * Starts the chatbot interaction loop. It takes user input, processes it using the Parser,
     * and continues until the user inputs 'bye'.
     *
     * @param taskList The TaskList object containing the list of tasks.
     * @param storage  The Storage object used for reading and writing tasks.
     */
    public void startChatBot(TaskList taskList, Storage storage, Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();

        dialogContainer.getChildren().addAll(
                DialogBox.getKervynDialog("\tHello! I'm " + this.CHATBOTNAME + "\n\tWhat can I do for you?", kervyn)
        );

        this.taskList = taskList;

        Parser parser = new Parser(storage);
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        stage.setTitle("Kervyn");
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
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);


        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Handle user input via the send button
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(parser);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(parser);
        });
    }

    private void handleUserInput(Parser parser) {
        String userText = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user)
        );
        parser.deduceCommand(userInput.getText(), this.taskList, kervyn, dialogContainer);
        userInput.clear();
    }
}

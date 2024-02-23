package pookie;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.util.Duration;
import pookie.tasks.TaskList;

/**
 * Represents a chat bot to keep track of user's pookie tasks.
 */
public class Pookie extends Application {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private Scene scene;

    private static TaskList list = new TaskList();
    private Parser parser = new Parser();

    private Image user = new Image(this.getClass().getResourceAsStream("/images/IMG_1297.JPG"));
    private Image pookie = new Image(this.getClass().getResourceAsStream("/images/IMG_1296.JPG"));

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
        stage.setTitle("Pookie");
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

        //Show intro
        Label intro = new Label(parser.showIntro());
        dialogContainer.getChildren().add(DialogBox.getPookieDialog(intro, new ImageView(pookie)));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });



    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Pookie's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String ans = getResponse(input);
        Label userText = new Label(input);
        Label pookieText = new Label(ans);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getPookieDialog(pookieText, new ImageView(pookie))
        );
        if (input.equals("bye")) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                System.exit(0);
            }));
            timeline.play();
        }
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        try {
            return parser.processLine(input, list);
        } catch (PookieException e) {
            return parser.showLoadingError(e.getMessage());
        }
    }

    /**
     * Constructor for the chat bot.
     */
    public Pookie() {
        try {
            Storage.loadFileContents(list);
        } catch (PookieException e) {
            parser.showLoadingError(e.getMessage());
        }
    }

    /**
     * Main method to run the chat bot.
     * @param args The arguments to be passed into the main method.
     */
    public static void main(String[] args) {
    }

}



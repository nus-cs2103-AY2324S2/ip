package wis.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import wis.ChatBox;

//@@author: Jeffry Lum
//All code in this file is reused from https://se-education.org/guides/tutorials/javaFx.html
// with minor modifications

public class WisApp extends Application {
    private ScrollPane scrollPane;  // contained in scene
    private VBox dialogContainer;  // contained in scrollPane
    private TextField userInput;

    private Button sendButton;
    private Scene scene;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image wisImage = new Image(this.getClass().getResourceAsStream("/images/Wis.png"));

    Bridge bridge = new Bridge(this);

    public WisApp() {

    }

    /**
     * {@inheritDoc}
     * Load the main scene, launch the app and listen for user input.
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage stage) {
        AnchorPane mainLayout = new AnchorPane();

        createUiElements(stage, mainLayout);
        setUiParameters(stage, mainLayout);

        bridge.link(dialogContainer, wisImage);

        // Handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private void createUiElements(Stage stage, AnchorPane mainLayout) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }

    private void setUiParameters(Stage stage, AnchorPane mainLayout) {
        stage.setTitle("Wis");
        stage.setResizable(true);
        stage.setMinHeight(600.0);
        stage.setMinWidth(800.0);

        mainLayout.setPrefSize(800.0, 600.0);

        scrollPane.setPrefSize(785, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(625.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    @Override
    public void stop() {
        bridge.quit();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        TextArea userText = new TextArea(userInput.getText());
        String response = bridge.getResponse(userInput);
        if (response == "") {
            return;
        }

        TextArea wisText = new TextArea(response);

        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(userImage)),
                new DialogBox(wisText, new ImageView(wisImage))
        );
        userInput.clear();
    }

    public void displayWelcomeMessage(ChatBox chatbox) {
        TextArea wisText = new TextArea(chatbox.getWelcomeMessage());

        dialogContainer.getChildren().addAll(
                new DialogBox(wisText, new ImageView(wisImage))
        );
    }
}

package gpt;

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
 * The main entry-point for the Duke application.
 */
public class Main extends Application {

    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaGpt.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Gpt chadGpt = new Gpt();
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Main() {
        // ...
    }

    @Override
    public void start(Stage stage) {
        initializeUiComponents();
        AnchorPane mainLayout = configureLayout();
        scene = new Scene(mainLayout);
        configureStage(stage);
        configureScrollPane();
        configureDialogContainer();
        displayWelcomeMessage();
        setEventHandlers();

    }

    private void initializeUiComponents() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
    }

    private void configureStage(Stage stage) {
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }

    private AnchorPane configureLayout() {
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(400.0, 600.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        return mainLayout;
    }

    private void configureScrollPane() {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    }

    private void configureDialogContainer() {
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void setEventHandlers() {
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(chadGpt.getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();


    }

    private void displayWelcomeMessage() {
        Ui ui = new Ui();
        String welcomeMessage = "Hello! I'm your chatbot. How can I assist you today?";
        Label dukeText = new Label(ui.getWelcomeMsg());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeText, new ImageView(duke)));
    }



}

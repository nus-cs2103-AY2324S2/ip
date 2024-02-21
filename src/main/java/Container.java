import java.util.ArrayList;
import java.util.List;

import filestorage.Storage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import parser.Parser;
import tasks.Task;

/**
 * Container for the content of the chat.
 */
public class Container {
    private List<List<Task>> tasksList;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private AnchorPane mainLayout;
    private Image taytay = new Image(this.getClass().getResourceAsStream("/images/Taylor.png"));
    private Image tayswift = new Image(this.getClass().getResourceAsStream("/images/TS.png"));

    /**
     * Initialises the container class.
     */
    public Container() {
        this.tasksList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            tasksList.add(new ArrayList<>());
        }
        this.scrollPane = new ScrollPane();
        this.dialogContainer = new VBox();
        this.userInput = new TextField();
        this.sendButton = new Button("Send");
    }

    /**
     * Set-up container for the content of the chat to scroll.
     *
     * @param stage
     */
    public void setUp(Stage stage) {
        scrollPane.setContent(dialogContainer);

        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Formats the window to look as expected.
     *
     * @param stage
     */
    public void formatWindow(Stage stage) {
        stage.setTitle("Taylor");
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
    }

    /**
     * Adds functionality to handle user input.
     *
     * @param stage
     */
    public void handleInput(Stage stage) {
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        try {
            tasksList = Storage.inputFromFile();
            initialiseTasksList();
        } catch (Exception e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getTaylorDialog(e.toString(), tayswift)
            );
        }
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(stage);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(stage);
        });
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
     * Creates two dialog boxes, one echoing user input and
     * the other containing Duke's reply and then appends them to
     * the dialog container.
     * Clears the user input after processing.
     */
    private void handleUserInput(Stage stage) {
        String userText = userInput.getText();
        String tayText = getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, taytay),
                DialogBox.getTaylorDialog(tayText, tayswift)
        );
        userInput.clear();

        if ("Bye".equalsIgnoreCase(userText.trim())) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> stage.close()));
            timeline.setCycleCount(1);
            timeline.play();
        }
    }

    /**
     * Generates a response to user input.
     */
    public String getResponse(String input) {
        return Parser.executeCommand(input, tasksList);
    }

    private void initialiseTasksList() {
        // Load pre-existing task from Hard Disk
        dialogContainer.getChildren().addAll(
                DialogBox.getTaylorDialog(getResponse("list"), tayswift)
        );
    }
}


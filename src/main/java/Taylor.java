import java.util.ArrayList;
import java.util.List;

import filestorage.Storage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
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
 * Main class to execute Taylor ChatBot
 */
public class Taylor extends Application {
    private List<List<Task>> tasksList = new ArrayList<>();
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image taytay = new Image(this.getClass().getResourceAsStream("/images/Taylor.png"));
    private Image tayswift = new Image(this.getClass().getResourceAsStream("/images/TS.png"));
    public Taylor() {
        //...
    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        for (int i = 0; i < 3; i++) {
            tasksList.add(new ArrayList<>());
        }

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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
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
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    public Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
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
     * Generate a response to user input
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

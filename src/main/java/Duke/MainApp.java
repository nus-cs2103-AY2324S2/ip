package Duke;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class MainApp extends Application{

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Duke duke;
    final int imageSize = 50; // square image so only one field
    final int closeWaitTime = 1000;
    @Override
    public void init() throws Exception {
        duke = new Duke();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        Button sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();


        stage.setTitle("Shirmin");
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

        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    private final Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/lulu.jpg")));
    private final Image min = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Shirmin.jpg")));

    /** Functions Status
     * 9/9 complete  hell yea        - retest whenever big changes are made
     * LIST           WORKING
     * MARK           WORKING
     * UNMARK         WORKING
     * TODO           WORKING
     * DEADLINE       WORKING
     * EVENT          WORKING
     * DELETE         WORKING
     * FIND           WORKING
     * BYE            WORKING
     */
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.trim().equalsIgnoreCase("bye")) {
            sayGoodbye();
            return;
        }
        handleOtherInputs(input);
        userInput.clear();
    }
    private void sayGoodbye() {
        Label goodbyeText = new Label("Goodbye! See you again!");
        ImageView dukeImageView = new ImageView(min);
        dukeImageView.setFitHeight(imageSize);
        dukeImageView.setFitWidth(imageSize);
        DialogBox goodbyeBox = new DialogBox(goodbyeText, dukeImageView);
        dialogContainer.getChildren().add(goodbyeBox);

        Platform.runLater(() -> {
            try {
                Thread.sleep(closeWaitTime);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            Platform.exit();
        });
    }

    /**
     * Handles non-bye inputs, encapsulating the previous logic for handling user inputs.
     * @param input The user input to handle.
     */

    private void handleOtherInputs(String input) {
        Label userText = new Label(input);
        ImageView userImageView = new ImageView(user);
        userImageView.setFitHeight(imageSize);
        userImageView.setFitWidth(imageSize);
        DialogBox userInputBox = DialogBox.getUserDialog(userText, userImageView);


        String response = duke.runCommand(input);
        Label dukeText = new Label(response);
        ImageView dukeImageView = new ImageView(min);
        dukeImageView.setFitHeight(imageSize);
        dukeImageView.setFitWidth(imageSize);
        DialogBox dukeResponseBox = DialogBox.getDukeDialog(dukeText, dukeImageView);

        dialogContainer.getChildren().addAll(userInputBox, dukeResponseBox);
    }


}



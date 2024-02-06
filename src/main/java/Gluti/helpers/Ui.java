package Gluti.helpers;

import Gluti.Gui.DialogBox;
import Gluti.utils.GlutiException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Represents the control hub for user input and filestorage for user
 */
public class Ui extends Application {
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image gluti = new Image(this.getClass().getResourceAsStream("/data/Gluticon.png"));
    private Image user  = new Image(this.getClass().getResourceAsStream("/data/usericon.jpg"));
    /**
     * Initializes a Ui instance and sets the status to "working"
     * @param fStorage the filestorage object that is going to be used in the program
     */
    public Ui(FileStorage fStorage){
        this.parser = new Parser(fStorage, this::updateOutputArea);
    }

    /**
     * Runs the program loop and calls the Parser to parse user inputs
     * @throws GlutiException Exceptions caught during parsing
     */
    public void run(Stage primaryStage) throws GlutiException {
        start(primaryStage);
    }

    @Override
    public void start(Stage stage) throws GlutiException {
        String logo = "Hello! I'm Gluti\n" +
                "What can I do for you?";
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
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        //You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (GlutiException e) {
                throw new RuntimeException(e);
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (GlutiException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
    private void handleUserInput() throws GlutiException {
        Label userText = new Label(userInput.getText());
        DialogBox ub = new DialogBox(userText, new ImageView(user));
        ub.setStyle("-fx-background-color: " + "#b54e72" + "; "
                + "-fx-background-radius: 6; "
                + "-fx-padding: 8px; "
                + "-fx-effect: dropshadow(one-pass-box, rgba(0, 0, 0, 0.4), 10, 0, 0, 2);");
        dialogContainer.getChildren().addAll(
            ub
        );
        getResponse(userInput.getText());
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) throws GlutiException {
        return parser.parse(input);
    }

    private void updateOutputArea(String message) {
        Label dukeText = new Label(message);
        DialogBox db = new DialogBox(dukeText, new ImageView(gluti));
        db.setStyle("-fx-background-color: " + "#a3bfaa" + "; "
                + "-fx-background-radius: 6; "
                + "-fx-padding: 8px; "
                + "-fx-effect: dropshadow(one-pass-box, rgba(0, 0, 0, 0.4), 10, 0, 0, 2);");
        dialogContainer.getChildren().addAll(
                db
        );
    }
}

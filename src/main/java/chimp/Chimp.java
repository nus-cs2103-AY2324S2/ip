package chimp;

import chimp.command.Command;
import chimp.controls.DialogBox;
import chimp.core.*;
import chimp.exception.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Chimp extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(getClass().getResourceAsStream("/images/DaDuke.png"));

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Chimp() {
        this.ui = new Ui();
        this.storage = new Storage();
        Storage.createFileIfNotExist();
        this.tasks = Storage.readOutputFromFile();
    }

    @Override
    public void start(Stage stage) {
        AnchorPane mainLayout = getMainLayout(stage);
        formatWindow(stage, mainLayout);
        addFunctionality();
    }

    private void addFunctionality() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private void formatWindow(Stage stage, AnchorPane mainLayout) {
        stage.setTitle("Chimp");
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

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private AnchorPane getMainLayout(Stage stage) {
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
        return mainLayout;
    }

    private void handleUserInput() {
        String input = userInput.getText();
        Label userText = new Label(input);

        String response = generateResponse(input);
        assert response != null : "response should not be null";

        Label dukeText = new Label(response);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke)));
        userInput.clear();
    }

    private String generateResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(this.tasks, this.ui, this.storage);
            if (c.isExit()) {
                exitChimp();
            }
            Storage.saveOutputToFile(this.tasks);
            return response;
        } catch (InvalidCommandException
                | CommandParseException 
                | CommandExecuteException
                | IndexOutOfBoundsException e) {
            return ui.say("hoo");
        }
    }

    private void exitChimp() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
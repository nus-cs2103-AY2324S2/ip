package BadApple.main;

import BadApple.task.Parser;
import BadApple.task.Storage;
import BadApple.task.TaskList;

import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import BadApple.uiElements.DialogBox;
import BadApple.uiElements.Ui;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.application.Application;

import javafx.scene.image.Image;

public class BadPingGuo extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    public static final String FILENAME = "src/main/data/whiteSpace.txt";
    private Image user = new Image(this.getClass().getResourceAsStream("/images/Sunny.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/MARI.png"));

    public static boolean makeFile() {
        try {
            File f1 = new File("src/main/data");
            File f = new File("src/main/data/whiteSpace.txt");
            return f1.mkdir() && f.createNewFile();
        } catch (IOException e) {
            System.out.println("Humphrey has denied your entrance to white space! \n " +
                    "perhaps the write permissions aren't working?");
            return false;
        }
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        dialogContainer.setSpacing(25);
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(800.0);
        stage.setMinWidth(600.0);

        mainLayout.setPrefSize(600.0, 8000.0);

        scrollPane.setPrefSize(590, 740);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        //You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(455.0);
        userInput.setPrefHeight(30.0);

        sendButton.setPrefWidth(100.0);
        sendButton.setPrefHeight(30);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // initialisation step 1: welcome
        dialogContainer.getChildren().add(getDialogLabel(Ui.showWelcome()));

        // initialisation step 2: load save
        try {
            Storage.loadSave(new File(FILENAME));
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(
                    (TaskList.listTasks(new BufferedReader(new FileReader(FILENAME)))),
                    (duke)));
        } catch (IOException e) {
            dialogContainer.getChildren().add(getDialogLabel("FAILED"));
        }

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

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    public Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        // Label userText = new Label(userInput.getText());
        // userText.setFont(Font.font("Comic Sans MS", 24));
        // Label dukeText = new Label(getResponse(userInput.getText()));
        // dukeText.setFont(Font.font("Comic Sans MS", 24));
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, (user)),
                DialogBox.getDukeDialog(dukeText, (duke))
        );

        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input){
        try {
            return Parser.ProcessQuery(input);
        } catch (IOException e) {
            return "FAILED";
        }
    }

}

package ui;

import javafx.application.Application;
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
import parser.ParseExecutionable;
import parser.Parser;
import task.ActionTask;
import task.TaskStorage;
import util.DataReader;
import util.DataWriter;
import util.TextUi;

/**
 * The main user interface for the "ChatBot", promptly named BobBot.
 *
 * This class is a front to manage user input, displaying the corresponding output,
 * and managed the conditional statements for the prompting.
**/
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("../images/DaUser.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("../images/DaDuke.png"));

    @Override
    public void start(Stage stage) {
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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * This method help to manage the users input, by directing
     * them to the respective class, getting the respective images
     * and clearing the input once it has been sent.
     */
    private void handleUserInput() {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getDukeDialog(getResponse(userInput.getText()), duke)
        );
        userInput.clear();
    }

    /**
     * This method will handle user input by parsering the input
     * and creating an Action object for it to be executed.
     * When executed, it will return the output of the action.
     *
     * @param input the user input.
     */
    public String getResponse(String input) {
        Parser parse = new Parser();
        TextUi textUi = new TextUi();
        DataReader dataReader = new DataReader();
        TaskStorage taskStorage = dataReader.readDataFile(textUi);
        ParseExecutionable actionable = parse.parseInput(input);
        String botResponse = actionable.execute(taskStorage);
        if (actionable instanceof ActionTask) {
            ActionTask actionTask = (ActionTask) actionable;
            if (actionTask.isItExitAction()) {
                DataWriter dataWriter = new DataWriter();
                dataWriter.saveData(taskStorage);
                Platform.exit();
                return botResponse;
            }
        }
        return botResponse;
    }
}

package Duke.UI;

import Duke.Activities.ActivityList;
import Duke.Commands.Command;
import Duke.Converstion.Dialog;
import Duke.Exception.CommandException;
import Duke.phrase.phrase;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.util.concurrent.Phaser;

/**
 * A GUI for Duke using FXML.
 */
public class MainWindow extends Application {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Scene scene;
    private static boolean isTerminated = false;

    private Image user = new Image(this.getClass().getResourceAsStream("/image/user.png"));
    private Image client = new Image(this.getClass().getResourceAsStream("/image/client.png"));

    private final ActivityList activityList = new ActivityList("data/duke.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Label msg = new Label(Dialog.greetUser());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(msg, new ImageView(client))
        );
    }


    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws CommandException {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(client))
        );
        if (userInput.getText().equals("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                Platform.exit();
                System.exit(0);
            });
            pause.play();
        }
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) throws CommandException {
        try {
            Command command = phrase.phraseCommand(input);
            command.execute(activityList);
            return command.toString();
        } catch (CommandException e) {
            return e.getMessage();
        }
    }

    public static boolean isTerminated() {
        return isTerminated;
    }
}

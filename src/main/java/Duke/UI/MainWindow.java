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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;

/**
 * The {@code MainWindow} class represents the main window of the Duke application using JavaFX and FXML.
 * It handles user input, displays a conversation dialog, and executes commands based on user input.
 */
public class MainWindow extends Application {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    private static boolean isTerminated = false;

    private final Image client = new Image(this.getClass().getResourceAsStream("/image/user.png"));
    private final Image user = new Image(this.getClass().getResourceAsStream("/image/client.png"));

    private final ActivityList activityList = new ActivityList("data/duke.txt");

    /**
     * The main entry point for JavaFX application. It loads the FXML layout, sets up the scene, and shows the stage.
     *
     * @param stage The primary stage for this application.
     */
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

    /**
     * Initializes the main window after the FXML layout has been loaded.
     * It binds the vertical value property of the scroll pane and displays an initial greeting message from Duke.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String msg = Dialog.greetUser();
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(msg, client)
        );
    }

    /**
     * Handles user input by creating dialog boxes for the user and Duke's response.
     * Clears the user input after processing and terminates the application if the user enters "bye".
     *
     * @throws CommandException If an error occurs while processing the user command.
     */
    @FXML
    private void handleUserInput() throws CommandException {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                DialogBox.getDukeDialog(response, client)
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
     * Retrieves Duke's response for a given user input by executing the corresponding command.
     *
     * @param input The user input to process.
     * @return Duke's response to the user input.
     * @throws CommandException If an error occurs while processing the user command.
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

    /**
     * Gets the termination status of the application.
     *
     * @return {@code true} if the application is terminated, {@code false} otherwise.
     */
    public static boolean isTerminated() {
        return isTerminated;
    }
}

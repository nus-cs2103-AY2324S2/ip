package duke;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

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

/**
 * Class that represents the main instance of the app.
 */
public class Duke extends Application {
    Ui ui = new Ui();
    State state = Storage.load();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
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

    }
    String getResponse(String input) {
        if (input.equals("bye")) {
            return "bye!";
        }
        try {
            Command c = Parser.parse(input, state);
            String output = c.execute(state, ui);
            Storage.save(state);
            return output;
        } catch (DukeException e) {
            return "Uh Oh! " + e.getMessage();
        } catch (DateTimeParseException e) {
            return "Uh Oh! Format your date as yyyy-mm-dd!";
        }
    }
}

package duke.display;

import java.io.IOException;

import duke.command.DukeException;
import duke.command.Parser;
import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Handles Anxi taskbot operations.
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Duke constructor.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");

        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        AnchorPane mainLayout = initializeContainer(stage);

        //Step 3. Add functionality to handle user input.
        userInput();

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates two dialog boxes, one echoing user input, the other containing Duke's reply.
     * Clears the user input after processing.
     */
    private void handleUserInput() throws DukeException, IOException {
        String input = userInput.getText();
        String response = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                DialogBox.getDukeDialog(response, duke)
        );
        userInput.clear();
    }

    /**
     * Gets taskbot response to user input.
     * @param input             User input string.
     * @return response         Result of parsing the user input.
     */
    public String getResponse(String input) {
        Parser parser = new Parser();
        return parser.parseInput(input, storage, taskList, ui);
    }

    /**
     * Sets up required components for taskbot GUI.
     * @param stage         Instance of stage object.
     * @return mainLayout   AnchorPane object.
     */
    private AnchorPane initializeContainer(Stage stage) {
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

        return mainLayout;
    }

    /**
     * Adds functionality to handle user input.
     */
    private void userInput() {
        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                System.out.println();
            } catch (DukeException de) {
                System.out.println();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                System.out.println();
            } catch (DukeException d) {
                System.out.println();
            }
        });
    }
}

package talkingcat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.util.List;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private TalkingCat talkingcat;

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/minnie.jpeg"));
    private Image talkingcatImage = new Image(this.getClass().getResourceAsStream("/images/spinminnie.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void settalkingcat(TalkingCat d, TaskList tasks, Ui ui, Storage storage) {
        this.talkingcat = d;
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    public TaskList getTasks() {
        return tasks;
    }

    public Ui getUi() {
        return ui;
    }

    public Storage getStorage() {
        return storage;
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing talkingcat's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userInputText = userInput.getText();
        String response;

        try {
            Command command = Parser.parse(userInputText);
            response = command.execute(tasks, ui, storage);
        } catch (TalkingCatException e) {
            response = e.getMessage();
        }

        // Display the user input and the response in the GUI
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInputText, userImage),
                DialogBox.gettalkingcatDialog(response, talkingcatImage)
        );

        userInput.clear();
    }
}

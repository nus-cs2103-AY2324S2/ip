package Gluti.Gui;

import Gluti.Gluti;
import Gluti.helpers.Ui;
import Gluti.utils.GlutiException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Gluti gluti;

    @FXML
    public void initialize(Gluti gluti, Ui ui) {
        this.gluti = gluti;
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getGlutiDialog(ui.typeHi()));
    }
    public void setGluti(Gluti g) {
        gluti = g;
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws GlutiException {
        String input = userInput.getText();
        assert input != null : "Input should not be empty!";
        String response = gluti.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getGlutiDialog(response)
        );
        userInput.clear();
        if (input.equals("bye")) {
            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.schedule(Platform::exit, 3, TimeUnit.SECONDS);
            scheduler.shutdown();
        }
    }
}
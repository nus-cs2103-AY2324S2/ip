package chatbot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

  @FXML
  private ScrollPane scrollPane;

  @FXML
  private VBox dialogContainer;

  @FXML
  private TextField userInput;

  @FXML
  private Button sendButton;

  private Duke duke;

  private Image userImage = new Image(
    this.getClass().getResourceAsStream("/images/DaUser.png")
  );
  private Image dukeImage = new Image(
    this.getClass().getResourceAsStream("/images/DaDuke.png")
  );

  @FXML
  public void initialize() {
    scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    String dukeGreeting = "Hi! I'm Tfamily bot. How can I assist you today?";
    dialogContainer
      .getChildren()
      .add(DialogBox.getDukeDialog(dukeGreeting, dukeImage));
    assert scrollPane != null : "FXML failed to load ScrollPane";
    assert dialogContainer != null : "FXML failed to load VBox";
    assert userInput != null : "FXML failed to load TextField";
    assert sendButton != null : "FXML failed to load Button";
  }

  public void setDuke(Duke d) {
    duke = d;
  }

  /**
   * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
   * the dialog container. Clears the user input after processing.
   */
  @FXML
  private void handleUserInput() {
    assert userInput != null : "User input TextField cannot be null";
    assert duke != null : "Duke instance has not been initialized";
    String input = userInput.getText();
    assert input != null &&
    !input.isEmpty() : "User input cannot be null or empty";
    String response = duke.getResponse(input);
    dialogContainer
      .getChildren()
      .addAll(
        DialogBox.getUserDialog(input, userImage),
        DialogBox.getDukeDialog(response, dukeImage)
      );
    userInput.clear();
  }
}

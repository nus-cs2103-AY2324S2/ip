package morty.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import morty.Morty;

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

  private Morty morty;

  private Image userImage = new Image(this.getClass().getResourceAsStream("/images/rick.png"));
  private Image mortyImage = new Image(this.getClass().getResourceAsStream("/images/morty.png"));

  /**
   * Initializes the main window.
   */
  @FXML
  public void initialize() {
    scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
  }

  /**
   * Sets Morty to the Morty object.
   */
  public void setMorty(Morty m) {
    morty = m;
  }

  /**
   * Creates two dialog boxes, one echoing user input and the other containing
   * Morty's reply and then appends them to
   * the dialog container. Clears the user input after processing.
   */
  @FXML
  private void handleUserInput() {
    String input = userInput.getText();
    String response = morty.getResponse(input);
    dialogContainer.getChildren().addAll(
        DialogBox.getUserDialog(input, userImage),
        DialogBox.getMortyDialog(response, mortyImage));
    userInput.clear();
  }
}

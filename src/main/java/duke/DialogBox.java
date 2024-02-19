package duke;

import java.io.IOException;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Custom control representing a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
  @FXML
  private Label dialog;
  @FXML
  private ImageView displayPicture;

  private DialogBox(String text, Image img) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
      fxmlLoader.setController(this);
      fxmlLoader.setRoot(this);
      fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

    dialog.setText(text);
    displayPicture.setImage(img);
  }

  /**
   * Flips the dialog box such that the ImageView is on the left and text on the right.
   */
  private void flip() {
    ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
    Collections.reverse(tmp);
    getChildren().setAll(tmp);
    setAlignment(Pos.TOP_LEFT);
  }

  /**
   * Creates and returns a DialogBox representing the user's dialog.
   *
   * @param text The text to be displayed in the dialog box.
   * @param img  The image to be displayed in the dialog box.
   * @return A DialogBox representing the user's dialog.
   */
  public static duke.DialogBox getUserDialog(String text, Image img) {
    return new duke.DialogBox(text, img);
  }

  /**
   * Creates and returns a DialogBox representing Duke's dialog.
   * This method flips the dialog box to display Duke's dialog on the left.
   *
   * @param text The text to be displayed in Duke's dialog box.
   * @param img  The image to be displayed in Duke's dialog box.
   * @return A DialogBox representing Duke's dialog.
   */
  public static duke.DialogBox getDukeDialog(String text, Image img) {
    var db = new duke.DialogBox(text, img);
    db.flip();
    return db;
  }
}

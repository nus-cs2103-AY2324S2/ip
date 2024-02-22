package Duke.UI;

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

import java.io.IOException;

/**
 * The {@code DialogBox} class represents a graphical user interface element for displaying dialog messages.
 * It extends the {@link HBox} class and includes a label for text and an image view for displaying a display picture.
 */
public class DialogBox extends HBox {

    /**
     * The label for displaying text content in the dialog box.
     */
    @FXML
    private Label dialog;

    /**
     * The image view for displaying a display picture in the dialog box.
     */
    @FXML
    private ImageView displayPicture;

    private static final String USER_DIALOG_STYLE = "user-dialog";
    private static final String DUKE_DIALOG_STYLE = "duke-dialog";

    /**
     * Constructs a new {@code DialogBox} with the specified label and image view.
     *
     * @param text The label for displaying text content.
     * @param img The image view for displaying a display picture.
     */
    private DialogBox(String text, Image img, boolean isUserDialog) {
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
        dialog.textProperty().addListener((observable, oldValue, newValue) -> adjustHeight());

        // Set style class based on the type of dialog (user or Duke)
        String backgroundColor = isUserDialog ? "-fx-background-color: lightyellow; -fx-border-color: grey;"
                : "-fx-background-color: lightblue; -fx-border-color: grey;";
        this.setStyle(backgroundColor);
    }

    private void adjustHeight() {
        // Calculate the preferred height based on the number of lines
        double lineHeight = dialog.getFont().getSize();
        int numLines = dialog.getText().split("\n").length;
        double preferredHeight = numLines * lineHeight;

        // Set the preferred height and update the layout
        setPrefHeight(preferredHeight);
        layout();
    }


    /**
     * Flips the alignment of the dialog box, changing it to be aligned to the top left.
     * This is used to represent messages from the user.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates a new {@code DialogBox} representing a user dialog.
     *
     * @param text The label for displaying text content.
     * @param img The image view for displaying a display picture.
     * @return A new {@code DialogBox} representing a user dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true);
    }

    /**
     * Creates a new {@code DialogBox} representing a Duke dialog.
     * Flips the alignment to indicate messages from Duke.
     *
     * @param text The label for displaying text content.
     * @param img The image view for displaying a display picture.
     * @return A new {@code DialogBox} representing a Duke dialog.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, false);
        db.flip();
        return db;
    }
}

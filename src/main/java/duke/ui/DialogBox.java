package duke.ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;

    @FXML
    private Label infoLabel;

    @FXML
    private ImageView displayPicture;

    DialogBox(String username, String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String timestamp = now.format(formatter);

        this.infoLabel.setText(username + " [" + timestamp + "]");
        dialog.setText(text);
        dialog.setMinWidth(Region.USE_COMPUTED_SIZE);
        dialog.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        dialog.setWrapText(true);

        displayPicture.setImage(img);
    }

    // Method to set the background style for the label
    protected void setLabelStyle(String backgroundStyle, Color textColor) {
        dialog.setStyle(backgroundStyle);
        dialog.setTextFill(textColor);
    }
}

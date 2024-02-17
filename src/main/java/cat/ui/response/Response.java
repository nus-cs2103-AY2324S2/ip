package cat.ui.response;

import java.io.IOException;

import cat.ui.MainWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Output abstraction for results.
 */
public abstract class Response extends VBox {
    @FXML
    private Label blurb;

    @FXML
    private Label output;

    @FXML
    private Pane outputPane;

    public Response(String blurb, String output) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/Result.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.blurb.setText(blurb);
        this.output.setText(output);
    }

    protected void setOutputColor(String colorHex) {
        this.outputPane.setBackground(new Background(
                new BackgroundFill(Color.valueOf(colorHex), CornerRadii.EMPTY, Insets.EMPTY)));
    }
}

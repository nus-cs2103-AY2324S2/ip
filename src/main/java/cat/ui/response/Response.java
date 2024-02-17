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
    private Label info;

    protected static class Colors {
        public static final String BLACK = "424242";
        public static final String RED = "E57373";
        public static final String GREEN = "C5E1A5";
        public static final String YELLOW = "FFEE58";
        public static final String BLUE = "64B5F6";
        public static final String MAGENTA = "E1BEE7";
        public static final String CYAN = "80DEEA";
        public static final String WHITE = "9E9E9E";

        public static final String BRIGHT_BLACK = "757575";
        public static final String BRIGHT_RED = "EF9A9A";
        public static final String BRIGHT_GREEN = "F4FF81";
        public static final String BRIGHT_YELLOW = "FFF59D";
        public static final String BRIGHT_BLUE = "42A5F5";
        public static final String BRIGHT_MAGENTA = "9575CD";
        public static final String BRIGHT_CYAN = "26C6DA";
        public static final String BRIGHT_WHITE = "E0E0E0";
    }

    /**
     * Constructs a response.
     * @param blurb The blurb to emote the cat with.
     * @param info The actual information.
     */
    public Response(String blurb, String info) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/Result.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.blurb.setText(blurb);
        this.info.setText(info);
    }

    protected void setOutputColor(String colorHex) {
        this.info.setBackground(new Background(
                new BackgroundFill(Color.valueOf(colorHex), CornerRadii.EMPTY, Insets.EMPTY)));
    }
}

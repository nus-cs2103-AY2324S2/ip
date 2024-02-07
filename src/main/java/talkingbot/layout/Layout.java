package talkingbot.layout;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;

/**
 * Layout class for the whole GUI.
 */
public class Layout {
    private ScrollPane scrollPane;
    private TextField textInput;
    private Button sendButton;
    private VBox vBox;
    private AnchorPane anchorPane;

    /**
     * Constructor for the Layout class.
     */
    public Layout() {
        this.scrollPane = new ScrollPane();
        this.textInput = new TextField();
        this.sendButton = new Button("Send");
        this.vBox = new VBox();
        this.anchorPane = new AnchorPane();
        this.scrollPane.setContent(this.vBox);
        this.anchorPane.getChildren().addAll(this.scrollPane, this.textInput, this.sendButton);
    }

    /**
     * Returns the Scene that will be displayed as the GUI.
     * @return The Scene to be presented.
     */
    public Scene getScene() {
        return new Scene(this.anchorPane);
    }
}

package talkingbot.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import talkingbot.logic.TalkingBot;

/**
 * Window class for the GUI of the application.
 */
public class Window extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vBox;
    @FXML
    private TextField textInput;
    @FXML
    private Button sendButton;
    private TalkingBot talkingBot;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/gui/You.jpg"));
    private final Image botImage = new Image(this.getClass().getResourceAsStream("/gui/TalkingBot.jpg"));
    private final String userName = "You";
    private final String botName = "TalkingBot";

    /**
     * Initializes the scrollPane and binds it to the vBox.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.vBox.heightProperty());
    }

    /**
     * Sets the logic handler within the window.
     * @param talkingBot The logic handler of the application.
     */
    public void setBot(TalkingBot talkingBot) {
        this.talkingBot = talkingBot;
    }

    /**
     * Sets the listeners available in the current layout.
     */
    public void setActions() {
        this.sendButton.setOnMouseClicked((event) -> {
            this.vBox.getChildren().add(getDialogLabel(this.textInput.getText()));
            this.textInput.clear();
        });

        this.textInput.setOnAction((event) -> {
            this.vBox.getChildren().add(getDialogLabel(this.textInput.getText()));
            this.textInput.clear();
        });

        this.vBox.heightProperty().addListener((obs) -> this.scrollPane.setVvalue(1.0));
    }

    private Label getDialogLabel(String text) {
        Label label = new Label(text);
        label.setWrapText(true);
        return label;
    }

    @FXML
    private void handleUserInput() {
        String text = this.textInput.getText();
        String botResponse = this.talkingBot.process(this.textInput.getText());
        this.vBox.getChildren().addAll(
                Message.getUserMessage(this.userImage, text, this.userName),
                Message.getBotMessage(this.botImage, botResponse, this.botName)
        );
        this.textInput.clear();
    }
}

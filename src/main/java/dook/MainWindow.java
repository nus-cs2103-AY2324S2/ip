package dook;


import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static final ArrayList<String> DANCE_STRING = new ArrayList<>(List.of(
            "　　　  　^＿^　　　♪\n"
                    + "　　　 （´・ω・｀∩\n"
                    + "　　 　　o　　　,ﾉ\n"
                    + "　　　　Ｏ＿　.ﾉ\n"
                    + "♪　　　 　 (ノ",
            "　　　 　^＿^　♪\n"
                    + "　　　 ∩・ω・｀）\n"
                    + "　　　 |　　 cﾉ\n"
                    + "　　 　｜　　 _⊃　　♪\n"
                    + "　　　 し -,"));

    private static final String WELCOME_STRING = "Hello from Dook! :D meow";

    private static final String PROMPT_STRING = "What can I do for you?? uwu";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Dook dook;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Meow2.png"));
    private Image dookImage = new Image(this.getClass().getResourceAsStream("/images/Meow1.png"));

    private String logo = " ____              _  \n"
            + "|  _ \\  ___   ___ | | __      ╱|\n"
            + "| | | |/ _ \\ / _ \\| |/ /    (˚. 。7  \n"
            + "| |_| | |_| | |_| |   <      |、˜〵 \n"
            + "|____/ \\___/ \\___/|_|\\_\\     \u3058\u3057_,)ノ\n";

    private Timeline danceAnimation;

    private KeyFrame getKeyFrame(double time, boolean isDook, String content) {
        if (isDook) {
            return new KeyFrame(Duration.seconds(time),
                    event -> dialogContainer
                            .getChildren()
                            .addAll(DialogBox.getDukeDialog(content, dookImage)));
        } else {
            return new KeyFrame(Duration.seconds(time),
                    event -> dialogContainer
                            .getChildren()
                            .addAll(DialogBox.getUserDialog(content, userImage)));
        }
    }
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(logo, dookImage));
        Timeline timeline = new Timeline();
        timeline.setCycleCount(0);
        timeline.getKeyFrames().addAll(
                getKeyFrame(1.5, true, WELCOME_STRING),
                getKeyFrame(3.0, true, PROMPT_STRING));
        timeline.play();
        danceAnimation = new Timeline();
        danceAnimation.setCycleCount(0);
        for (int i = 0; i < 16; i++) {
            danceAnimation.getKeyFrames().addAll(
                    getKeyFrame(((float) i + 0.5) / 2.0, true, DANCE_STRING.get(0)),
                    getKeyFrame(((float) i + 1) / 2.0, true, DANCE_STRING.get(1)));
        }
    }

    public void setDook(Dook d) {
        dook = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        userInput.clear();
        Timeline timeline = new Timeline();
        timeline.setCycleCount(0);
        String response = dook.getResponse(input);
        if (input.equals("bye")) {
            timeline.getKeyFrames().addAll(
                    getKeyFrame(0, false, input),
                    getKeyFrame(1.5, true, response),
                    new KeyFrame(Duration.seconds(3),
                            event -> Platform.exit())
            );
        } else if (input.equals("dance")) {
            danceAnimation.play();
            timeline.getKeyFrames().addAll(
                    getKeyFrame(0, false, input),
                    getKeyFrame(8, true, response));
        } else {
            timeline.setCycleCount(0);
            timeline.getKeyFrames().addAll(
                    getKeyFrame(0, false, input),
                    getKeyFrame(1.5, true, response));
        }
        timeline.play();
    }
}

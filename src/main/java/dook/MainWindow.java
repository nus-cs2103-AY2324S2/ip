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
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(logo, dookImage));
        Timeline timeline = new Timeline();
        timeline.setCycleCount(0);
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(1.5),
                        event -> dialogContainer
                                .getChildren()
                                .addAll(DialogBox
                                        .getDukeDialog("Hello from Dook! :D meow", dookImage))),
                new KeyFrame(Duration.seconds(3.0),
                        event -> dialogContainer
                                .getChildren()
                                .addAll(DialogBox
                                        .getDukeDialog("What can I do for you?? uwu", dookImage)))
        );
        timeline.play();
        danceAnimation = new Timeline();
        danceAnimation.setCycleCount(0);
        for (int i = 0; i < 16; i++) {
            danceAnimation.getKeyFrames().addAll(
                    new KeyFrame(Duration.seconds(((float) i + 0.5) / 2.0),
                            event -> dialogContainer
                                    .getChildren()
                                    .addAll(DialogBox
                                            .getDukeDialog(DANCE_STRING.get(0), dookImage))),
                    new KeyFrame(Duration.seconds(((float) i + 1) / 2.0),
                            event -> dialogContainer
                                    .getChildren()
                                    .addAll(DialogBox
                                            .getDukeDialog(DANCE_STRING.get(1), dookImage))));
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
        // TODO: create DookResponse types (animation, text or quit)
        if (input.equals("bye")) {
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.seconds(0),
                            event -> dialogContainer
                                    .getChildren()
                                    .addAll(DialogBox
                                            .getUserDialog(input, userImage))),
                    new KeyFrame(Duration.seconds(1.5),
                            event -> dialogContainer
                                    .getChildren()
                                    .addAll(DialogBox
                                            .getDukeDialog(response, dookImage))),
                    new KeyFrame(Duration.seconds(3),
                            event -> Platform.exit())
            );
        } else if (input.equals("dance")) {
            danceAnimation.play();
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.seconds(0),
                            event -> dialogContainer
                                    .getChildren()
                                    .addAll(DialogBox
                                            .getUserDialog(input, userImage))),
                    new KeyFrame(Duration.seconds(8),
                            event -> dialogContainer
                                    .getChildren()
                                    .addAll(DialogBox
                                            .getDukeDialog(response, dookImage))));
        } else {
            timeline.setCycleCount(0);
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.seconds(0),
                            event -> dialogContainer
                                    .getChildren()
                                    .addAll(DialogBox
                                            .getUserDialog(input, userImage))),
                    new KeyFrame(Duration.seconds(1.5),
                            event -> dialogContainer
                                    .getChildren()
                                    .addAll(DialogBox
                                            .getDukeDialog(response, dookImage)))
            );
        }
        timeline.play();
    }
}

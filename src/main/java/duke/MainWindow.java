package duke;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke bingus;
    private final Image BINGUS_IMAGE = new Image(this.getClass().getResourceAsStream("/images/bingus.jpg"));
    private final Image USER_IMAGE = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));

    final static String BINGUS_LOGO = " B   i   n   g   u   s ";
    final static  String NAME = "Bingus";

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }
    @FXML
    private void handleUserInput() throws DukeException{
        String input = this.userInput.getText();
        String response = bingus.getResponse(input);
        //String response = "this is a test.";
        if(response.equals("Bye. Bingus hopes to see you again soon!\n")){
            /*this.dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, this.USER_IMAGE),
                    DialogBox.getDukeDialog(response, this.BINGUS_IMAGE)
            );*/
           // this.dialogContainer.getChildren().addAll(
                   // DialogBox.getDukeDialog("See you soon, take care!", this.BINGUS_IMAGE)
          //  );

            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> {
                Platform.exit();
            });
            delay.play();
        }
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, this.USER_IMAGE),
                DialogBox.getDukeDialog(response, this.BINGUS_IMAGE)
        );
        this.userInput.clear();
    }

    public void openingMessages() {
        String result = "Hello from\n" + BINGUS_LOGO + "\n" + "Hello! I'm " + NAME + "\n" + "What can I do for you?\n";
        this.dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(result, this.BINGUS_IMAGE));
    }

    public void setDuke(Duke bingus) {
        this.bingus = bingus;
    }



}

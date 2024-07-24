package bob.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import bob.Bob;
import bob.Parser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The <code>MainWindow</code> control. Provides the layout for the other controls.
 */
public class MainWindow extends VBox {
    private static final String IMAGE_BOB = "DaDuke.png";
    private static final String IMAGE_USER = "DaUser.png";

    private static final String PATH_IMAGES = "/images/";
    private static final String PATH_STYLESHEETS = "/stylesheets/";

    private static final String STYLESHEET = "styles.css";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Stage stage;

    private Bob bob;

    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream(PATH_IMAGES + IMAGE_USER)));
    private final Image bobImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream(PATH_IMAGES + IMAGE_BOB)));

    /**
     * Returns a <code>MainWindow</code> control that provides the layout for the other controls.
     *
     * @param stage The stage that is set to show the control
     */
    public MainWindow(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/views/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();

            Scene scene = new Scene(this);
            URL url = Objects.requireNonNull(MainWindow.class.getResource(PATH_STYLESHEETS + STYLESHEET));
            scene.getStylesheets().add(url.toExternalForm());

            stage.setTitle("Bob");
            stage.setResizable(false);
            stage.setScene(scene);
            this.stage = stage;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBob(Bob b) {
        bob = b;
        dialogContainer.getChildren().add(DialogBox.getBobDialog(bob.getInitialMessage(), bobImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bob.getResponse(input);

        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox bobDialog = DialogBox.getBobDialog(response, bobImage);
        dialogContainer.getChildren().addAll(userDialog, bobDialog);
        userInput.clear();

        if (input.equals(Parser.COMMAND_EXIT)) {
            stage.close();
        }
    }

    public void show() {
        stage.show();
    }
}

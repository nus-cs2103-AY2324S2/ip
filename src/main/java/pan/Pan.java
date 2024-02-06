package pan;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Pan - Represents the Main Class that would override the Default Application class
 * 
 * @author Jerome Goh
 */
public class Pan extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Parser parser;

    public Pan() {
    }

    @Override
    public void start(Stage stage) {
        // The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        // Set up event handler for the sendButton
        sendButton.setOnAction(event -> {
            String input = userInput.getText(); // Get user input from the TextField
            // Pass input to Parser for processing
            parser.parseInput(input);
            userInput.clear(); // Clear the TextField after sending the input
        });

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(userInput, 0.0);
        AnchorPane.setLeftAnchor(userInput, 0.0);
        AnchorPane.setRightAnchor(userInput, 60.0);
        AnchorPane.setBottomAnchor(sendButton, 0.0);
        AnchorPane.setRightAnchor(sendButton, 0.0);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        parser = new Parser(new Ui(), new TaskList(new Storage()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}

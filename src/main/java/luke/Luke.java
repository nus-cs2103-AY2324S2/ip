package luke;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Luke extends Application {

    //Used this https://se-education.org/guides/tutorials/javaFxPart2.html as the main template!
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/roblox.jpg"));
    private Image luke = new Image(this.getClass().getResourceAsStream("/images/ltg.jpg"));

    public static void main(String[] args) {
        launch();
    }

    //Used this https://se-education.org/guides/tutorials/javaFxPart2.html as the main template!
    @Override
    public void start(Stage stage) {
        //a bit messy. could be fixed with XFML.
        Storage storage = Storage.loadHistory();
        File historyFile = Storage.getHistoryFile();
        UI ui = new UI();

        Parser parser = new Parser(storage, historyFile);

        initFxFields();
        setFxFields(stage);
        lukeSpeaks(UI.greet());
        setEventHandlers(parser, ui, stage);
        setListeners();
    }

    /**
     * Initializes JavaFX fields for Luke GUI.
     */
    private void initFxFields() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
    }

    /**
     * Sets JavaFX field values for Luke GUI.
     *
     * @param stage JavaFX stage
     */
    private void setFxFields(Stage stage) {
        stage.setScene(scene);
        stage.getIcons().addAll(luke);
        stage.setX(300);
        stage.setY(100);
        stage.show();

        stage.setTitle("Luke");
        double minHeight = 600;
        double minWidth = 600;
        stage.setMinHeight(minHeight);
        stage.setMinWidth(minWidth);
        stage.setResizable(false);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPrefSize(0.97 * minWidth, 0.89 * minHeight);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(520);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Sets event handlers to handle GUI inputs.
     *
     * @param parser Command parser (input string -> result string)
     * @param ui UI to generate UI messages
     * @param stage JavaFX stage
     */
    private void setEventHandlers(Parser parser, UI ui, Stage stage) {
        //makes it such that we handle user input upon clicking.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(parser, ui, stage);
        });

        //makes it such that we handle user input upon pressing enter.
        userInput.setOnAction((event) -> {
            handleUserInput(parser, ui, stage);
        });
    }

    /**
     * Sets JavaFX listeners.
     * In this case, makes sure the page scrolls down to the bottom if input
     * flows off the screen.
     */
    private void setListeners() {
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Handles user input and generates the appropriate response.
     * Shows the appropriate response in the GUI.
     *
     * @param parser The parser used to parse string inputs from user.
     * @param ui The UI which generates messages in the GUI.
     * @param stage The JavaFX stage.
     */
    //Used this https://se-education.org/guides/tutorials/javaFxPart2.html as the main template!
    private void handleUserInput(Parser parser, UI ui, Stage stage) {
        assert parser != null;
        String QUIT_STRING = "QUIT";
        String formattedInput = userInput.getText();
        String lukeReply;
        Label lukeText;
        Label userText = new Label(userInput.getText());
        if (parser.isLastCommand) {
            stage.close();
        }
        try {
            lukeReply = parser.parseCommand(formattedInput);
        } catch (TasklistException e) {
            lukeReply = e.getMessage();
        } catch (ParseCommandException e) {
            lukeReply = e.getMessage();
        }
        if (lukeReply.equals(QUIT_STRING)) {
            lukeText = new Label(ui.bye());
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(user)),
                    DialogBox.getLukeDialog(lukeText, new ImageView(luke))
            );
            userInput.clear();
            parser.isLastCommand = true;
        } else {
            lukeText = new Label(lukeReply);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(user)),
                    DialogBox.getLukeDialog(lukeText, new ImageView(luke))
            );
            userInput.clear();
        }
    }

    /**
     * Makes Luke say something (which is then shown in the GUI).
     *
     * @param input What you want Luke to say.
     */
    private void lukeSpeaks(String input) {
        Label lukeText = new Label(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getLukeDialog(lukeText, new ImageView(luke))
        );
    }

}



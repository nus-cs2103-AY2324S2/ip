package kitchensink;

import java.io.IOException;

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

import kitchensink.exception.InvalidDateTimeException;
import kitchensink.exception.InvalidSyntaxException;
import kitchensink.exception.TaskNotFoundException;
import kitchensink.exception.UnknownCommandException;

/**
 * The entry point of the app.
 */
public class KitchenSink /* extends Application */ {
    private Ui ui = new Ui();
    private Parser parser = new Parser();
    private String fileName = "./data/duke.txt";
    private Storage storage = new Storage(fileName);
    private List taskList = new List(storage.loadTasks());
//    private ScrollPane scrollPane;
//    private VBox dialogContainer;
//    private TextField inputBox;
//    private Button sendButton;
//    private Scene scene;
//    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
//    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public KitchenSink() throws IOException {

    }

//    public void run() throws TaskNotFoundException, UnknownCommandException, InvalidSyntaxException, IOException,
//            InvalidDateTimeException {
//        ui.welcome();
//        boolean isExit = false;
//        while (!isExit) {
//            String input = ui.readInput();
//            isExit = parser.parse(input, taskList, ui, storage);
//        }
//    }

//    public static void main(String[] args) throws IOException, TaskNotFoundException, UnknownCommandException,
//            InvalidSyntaxException, InvalidDateTimeException {
//        new KitchenSink().run();
//    }
//
//    @Override
//    public void start(Stage stage) {
//        //Step 1. Setting up required components
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        inputBox = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, inputBox, sendButton);
//
//        scene = new Scene(mainLayout);
//
//        stage.setScene(scene);
//        stage.show();
//
//        //Step 2. Formatting the window to look as expected
//        stage.setTitle("Luke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        //You will need to import `javafx.scene.layout.Region` for this.
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        inputBox.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(inputBox, 1.0);
//        AnchorPane.setBottomAnchor(inputBox, 1.0);
//
//        //Step 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(inputBox.getText()));
//            inputBox.clear();
//        });
//
//        inputBox.setOnAction((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(inputBox.getText()));
//            inputBox.clear();
//        });
//
//        //Scroll down to the end every time dialogContainer's height changes.
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//
        //Part 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        inputBox.setOnAction((event) -> {
//            handleUserInput();
//        });
//    }

//    /**
//     * Iteration 1:
//     * Creates a label with the specified text and adds it to the dialog container.
//     * @param text String containing text to add
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        // You will need to import `javafx.scene.control.Label`.
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws TaskNotFoundException, UnknownCommandException, InvalidSyntaxException, IOException, InvalidDateTimeException {
        return parser.parse(input, taskList, ui, storage);
    }
}

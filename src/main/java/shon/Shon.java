package shon;

import java.time.format.DateTimeParseException;

import component.DialogBox;
import javafx.application.Application;
import javafx.application.Platform;
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
import shon.command.Command;
import shon.exception.CommandException;
import shon.exception.ParameterException;

/**
 * Represents a chatbot that maintains a Todo List.
 */
public class Shon extends Application {
    private Ui ui;
    private TaskList list;
    private Storage storage;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Creates a <code>Shon</code> chatbot.
     */
    public Shon() {
        this.ui = new Ui();
        this.storage = new Storage("./data/Shon.txt");
        this.list = storage.loadList();
    }


    public static void main(String[] args) {
//        new Shon().run();
    }

//    /**
//     * Runs the chatbot and takes in commands from the standard input.
//     * Terminates when the <code>bye</code> command is received.
//     */
//    private void run() {
//        this.ui.greet();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String input = this.ui.readInput();
//                Command command = Parser.parse(input);
//                command.execute(this.list, this.ui);
//                this.storage.updateData(this.list.formatData());
//                isExit = command.isExit();
//            } catch (ParameterException | CommandException e) {
//                this.ui.print(e.getMessage());
//            } catch (DateTimeParseException e) {
//                this.ui.print(e.getParsedString() + " is not a valid date/time",
//                        "Please enter the date/time in \"dd/mm/yyyy hhmm\" format with valid values.");
//            }
//        }
//    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        // Set greeting
        Label dukeGreeting = new Label("Hello! I'm Shon. What can I do for you?");
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(dukeGreeting, new ImageView(duke)));
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        if (userInput.getText().equals("bye")) {
            Platform.exit();
        }
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String output = command.execute(this.list);
            this.storage.updateData(this.list.formatData());
            return output;
        } catch (ParameterException | CommandException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return e.getParsedString() + " is not a valid date/time. "
                    + "Please enter the date/time in \"dd/mm/yyyy hhmm\" format with valid values.";
        }
    }
}

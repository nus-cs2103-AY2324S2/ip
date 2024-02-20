package duke;

import java.util.Objects;

import duke.commands.CommandList;
import duke.commands.DukeCommandNotFoundException;
import duke.tasks.TaskList;
import duke.ui.DialogBox;
import duke.ui.Ui;
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


public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.jpg")));
    private Image duke = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/louie.jpg")));

    private final Ui ui = new Ui();
    private final CommandList commands = new CommandList();
    private TaskList tasks = new TaskList();
    private final Storage st = new Storage("data.txt");

    public Duke() {
    }

    public Storage getStorage() {
        return st;
    }


    public TaskList getTasks() {
        return this.tasks;
    }

    /**
     * Prints message to the chatbot GUI.
     * @param msg the message to be printed.
     */
    public void print(String msg) {
        dialogContainer.getChildren()
                .add(DialogBox.createDukeDialog
                        (new Label(msg), new ImageView(duke)));

    }

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

        //More code to be added here later
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

        //You will need to import `javafx.scene.layout.Region` for this.
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

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        try {
            this.tasks = this.st.loadTasks();
        } catch (DukeException e) {
            this.print(String.format
                    ("Error loading task data: %s"
                            + "\n\nPlease delete 'data.txt' and try again. Bye bye...", e.getMessage()));
            System.exit(1);
        }
    }


    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        dialogContainer.getChildren().add(DialogBox.createUserDialog(userText, new ImageView(user)));
        Parser parser = new Parser(userInput.getText());
        userInput.clear();

        try {
            this.commands.get(parser.next()).run(parser, this);
        } catch (DukeCommandNotFoundException e) {
            this.print("no matching command...");
        } catch (DukeException e) {
            this.print(String.format("OH NYO!!!!!!!!!!! %s", e.getMessage()));
        }
    }
}

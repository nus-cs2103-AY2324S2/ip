package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage("duke.txt");
        tasks = new TaskList(new ArrayList<Task>());
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
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

        //Step 2. Formatting the window to look as expected
        //Setting up the stage
        stage.setTitle("Duke"); // Sets the title of the window to "Duke"
        stage.setResizable(false); // Makes the window non-resizable
        stage.setMinHeight(600.0); // Sets the minimum height of the window to 600 pixels
        stage.setMinWidth(400.0); // Sets the minimum width of the window to 400 pixels
        //Setting up the layout
        mainLayout.setPrefSize(400.0, 600.0); // The mainLayout is set up with a preferred size of 400x600 pixels.
        //Setting up the scroll pane
        scrollPane.setPrefSize(385, 535); // Sets the preferred scrollPane size to 385 width x 535 height
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal scrollbar is never shown
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Vertical scrollbar is always shown
        scrollPane.setVvalue(1.0); // Used to set the vertical scroll position of the ScrollPane, 1.0 is at the bottom
        scrollPane.setFitToWidth(true); // Automatically adjust the width of the content to match the scrollbar
        // The VBox height is set to adjust based on its content
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        // Set preferred width of userInput and sendButton
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
        // Anchor children to the pane, the value Double represents the distance in pixels from the pane
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        // Alternative ways instead of AnchorPane:
        // 1.BorderPane
        //  (+) straightforward to use and great for layouts that need to expand in one direction
        //  (-) less flexible than AnchorPane as it only allows one node per region
        // 2.GridPane
        //  (+) very flexible
        //  (-) more complex to set up and manage
        // 3.VBox
        //  (+) simple to use and great for stacking components
        //  (-) less flexible since it doesn't allow precise positioning of components

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    public void run() {
        this.ui.open();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parseToCommand(fullCommand);
                c.execute(tasks, ui);
                ui.showLine();
                isExit = c.isExit();
            } catch (DukeException ex) {
                System.out.println(ex.toString());
                ui.showLine();
            }
        }
        ui.close();
        storage.save(tasks);
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}

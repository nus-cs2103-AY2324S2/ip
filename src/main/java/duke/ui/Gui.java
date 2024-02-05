package duke.ui;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.TaskList;
import duke.ui.components.DialogBox;

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

/**
 * The UI GUI class handles the displaying of UI elements in the application
 * using a graphical user interface
 *
 * @author Ryan NgWH
 */
public class Gui extends Ui {
    /**
     * Tasklist for the application
     */
    private static TaskList taskList;

    /**
     * The DukeGui class handles displaying of UI elements in the Duke appliation
     * using a GUI implemented via JavaFX
     *
     * @author Ryan NgWH
     */
    public static class DukeGui extends Application {
        // Images for user and duke
        private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
        private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.jpg"));

        // GUI Elements
        ScrollPane scrollPane;
        VBox dialogContainer;
        TextField userInput;
        Button sendButton;
        AnchorPane mainLayout;
        Scene scene;

        /**
         * Default constructor for a Duke GUI
         */
        public DukeGui() {
            // Create container for the chatbox
            this.scrollPane = new ScrollPane();
            this.dialogContainer = new VBox();
            this.scrollPane.setContent(dialogContainer);

            // Create elements for user input
            this.userInput = new TextField();
            this.sendButton = new Button("Send");

            // Create GUI pane
            this.mainLayout = new AnchorPane();
            this.mainLayout.getChildren().addAll(this.scrollPane, this.userInput, this.sendButton);

            // Set the scene
            this.scene = new Scene(mainLayout);
        }

        @Override
        public void start(Stage stage) {
            stage.setScene(this.scene);

            // Format the GUI
            stage.setTitle("Duke");
            stage.setResizable(false);
            stage.setMinHeight(600.0);
            stage.setMinWidth(400.0);

            this.mainLayout.setPrefSize(400.0, 600.0);

            this.scrollPane.setPrefSize(385, 535);
            this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

            this.scrollPane.setVvalue(1.0);
            this.scrollPane.setFitToWidth(true);

            this.dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

            this.userInput.setPrefWidth(325.0);

            this.sendButton.setPrefWidth(55.0);

            AnchorPane.setTopAnchor(this.scrollPane, 1.0);

            AnchorPane.setBottomAnchor(this.sendButton, 1.0);
            AnchorPane.setRightAnchor(this.sendButton, 1.0);

            AnchorPane.setLeftAnchor(this.userInput, 1.0);
            AnchorPane.setBottomAnchor(this.userInput, 1.0);

            // User input functionalities
            this.sendButton.setOnMouseClicked((event) -> {
                try {
                    this.handleUserInput();
                } catch (DukeException e) {
                    dialogContainer.getChildren().add(new Label(String.format("ERROR: %s", e.getMessage())));
                } finally {
                    userInput.clear();
                }
            });

            this.userInput.setOnAction((event) -> {
                try {
                    this.handleUserInput();
                } catch (DukeException e) {
                    dialogContainer.getChildren().add(new Label(String.format("ERROR: %s", e.getMessage())));
                } finally {
                    userInput.clear();
                }
            });

            // Scroll to end every time dialogContainer's height changes
            this.dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

            stage.show();
        }

        /**
         * Create a label with the response from the application to the user input
         *
         * @param input User input to be parsed
         *
         * @return String with the response from the application
         */
        private String getResponse(String input) throws DukeException {
            // Parse user input
            Command command = Parser.parse(input);

            // Execute command
            return command.execute(Gui.taskList);
        }

        /**
         * Parse the user input and display a response on the GUI
         */
        private void handleUserInput() throws DukeException {
            Label userText = new Label(this.userInput.getText());
            Label dukeText = new Label(this.getResponse(this.userInput.getText()));
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(this.user)),
                    DialogBox.getDukeDialog(dukeText, new ImageView(this.duke)));
            this.userInput.clear();
        }
    }

    /**
     * Displays the UI of the application
     *
     * @param taskList Tasklist to use for the application
     * @param args     Arguments for the application
     */
    @Override
    public void startUI(TaskList taskList, String[] args) {
        Gui.taskList = taskList;
        Application.launch(DukeGui.class, args);
    }
}

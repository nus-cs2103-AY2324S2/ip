package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The main class for the Duke application named HughJazz.
 * This class initializes the application and manages the main interaction loop, processing user inputs
 * and executing corresponding actions.
 */
public class HughJazz extends Application{

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaChatbot.png"));

    public HughJazz() {}

    /**
     * UI component responsible for interactions with the user.
     */
    private static Ui ui = new Ui();

    /**
     * Storage component responsible for loading and saving tasks to a file.
     */
    private static Storage storage = new Storage("." + File.separator + "data" + File.separator + "duke.txt");

    /**
     * TaskList component that holds and manages all tasks in the application.
     */
    private static TaskList taskList = new TaskList();

    /**
     * The entry point of the application. It initializes necessary components,
     * loads existing tasks from storage, and enters a loop to accept and process user commands.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        String userInput;

        ui.showGreeting();

        try {
            ArrayList<Task> loadedTasks = storage.load();
            taskList.loadTasks(loadedTasks);
        } catch (FileNotFoundException e) {
            ui.showError("No existing txt file found.");
        }

        while (true) {
            userInput = ui.readCommand();
            if ("bye".equalsIgnoreCase(userInput)) {
                break;
            } else {
                try {
                    Parser.parse(userInput, taskList, storage);
                } catch (DateTimeParseException e) {
                    System.out.println("Please input date and time in the correct format dd/MM/yyyy HHmm");
                    System.out.println("Please try again");

                } catch (ChatbotException e) {
                    ui.showError(e.getMessage());
                }
            }
        }

        ui.showGoodbye();
    }

    @Override
    public void start(Stage stage) {
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

        // Setting the height and width of the window
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        // Setting the height and width of the scroll pane
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        //You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        // Set scroll pane to be top of the page
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        // Set button to be bottom right of the page
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        // Set user input to be bottom left of the page
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    public void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, new ImageView(user)),
//                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }


}

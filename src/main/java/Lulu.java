import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import command.Command;
import exceptions.InvalidCommandException;
import exceptions.InvalidDateException;
import exceptions.InvalidSlashParameterException;
import exceptions.LuluException;
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
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;

/**
 * The main class for Lulu, a simple chatbot application.
 * Lulu interacts with the user through a command-line interface (CLI)
 * to perform tasks such as adding, listing, and deleting tasks.
 */
public class Lulu extends Application {

    /** The storage component for managing data persistence. */
    private Storage storage;

    /** The task list for storing and managing tasks. */
    private TaskList tasks;

    /** The parser for interpreting user input and generating commands. */
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructs a new instance of Lulu with default configurations.
     * Initializes the storage, task list, and parser.
     */
    public Lulu() {
        storage = new Storage("src/main/resources/data/lulu.txt");
        tasks = new TaskList(storage.retrieveLines());
        parser = new Parser();
    }

    /**
     * Listens for user input and responds accordingly until the user exits.
     * Handles various user commands and exceptions during interaction.
     */
    public void respond() {
        while (true) {
            String input = UI.nextLine();

            try {
                if (input.toLowerCase().equals("bye")) {
                    break;
                } else if (input.toLowerCase().equals("list")) {
                    UI.printTasks(this.tasks);
                } else {
                    Command command = parser.parse(input);
                    command.execute(this.tasks, this.storage);
                }
            } catch (InvalidCommandException e) {
                UI.print("Sorry, I don't think I quite understood what you meant...");
            } catch (InvalidDateException e) {
                UI.print("Please ensure that you are inputting valid start and end dates.");
            } catch (InvalidSlashParameterException e) {
                UI.print("Please ensure that you are inputting valid date parameters.");
            } catch (LuluException e) {
                UI.print(e.getMessage());
            }
        }
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
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
        stage.setMinWidth(600.0);

        mainLayout.setPrefSize(600.0, 600.0);

        scrollPane.setPrefSize(585, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        //You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(525.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        displayStartMessage();

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Handles the user's input in the chat application. Displays the user's input, Duke's response, and updates the UI
     * accordingly. If the user enters "bye," schedules a program exit after 2 seconds.
     */
    public void handleUserInput() {
        // Get the user's input text and convert it to lowercase
        String userInputText = userInput.getText().toLowerCase();

        // Create labels for user's input and Duke's response
        Label userText = new Label(userInputText);
        Label dukeText = new Label(getResponse(userInputText));

        // Add user and Duke dialog boxes to the dialog container
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );

        // Clear the user input field
        userInput.clear();

        // Check if the user entered "bye" and schedule program exit after 3 seconds
        if (userInputText.equals("bye")) {
            exit();
        }
    }

    /**
     * Displays the initial greeting message from Duke when the chat application starts.
     * The message includes a welcome greeting and an inquiry about how Duke can assist the user.
     */
    public void displayStartMessage() {
        Label greetingMessage = new Label("Hello! I'm Lulu.\nWhat can I do for you?");
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greetingMessage, new ImageView(duke)));
    }

    /**
     * Schedules the program to exit after a 3-second delay. This method is typically called when the user inputs "bye."
     * Uses a ScheduledExecutorService to execute the Platform.exit() method after the specified delay.
     */
    public void exit() {
        // Schedule program exit after 2 seconds
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> Platform.exit(), 2, TimeUnit.SECONDS);
        scheduler.shutdown();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            if (input.toLowerCase().equals("bye")) {
                return "Bye. Hope to see you again soon!";
            } else if (input.toLowerCase().equals("list")) {
                return UI.printTasks(this.tasks);
            } else {
                Command command = parser.parse(input);
                return command.execute(this.tasks, this.storage);
            }
        } catch (InvalidCommandException e) {
            return "Sorry, I don't think I quite understood what you meant...";
        } catch (InvalidDateException e) {
            return "Please ensure that you are inputting valid start and end dates.";
        } catch (InvalidSlashParameterException e) {
            return "Please ensure that you are inputting valid date parameters.";
        } catch (LuluException e) {
            return e.getMessage();
        }
    }

    /**
     * The main method to run the Lulu chatbot application.
     * Creates an instance of Lulu, starts the application, and handles exceptions.
     * Exits the application with a goodbye message.
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        Lulu chatbot = new Lulu();
        UI.start();
        try {
            chatbot.respond();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        UI.exit();
    }
}

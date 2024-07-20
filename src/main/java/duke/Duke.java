package duke;
//import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import GUI.DialogBox;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



/**
 * Represents the main class for the Duke chatbot application.
 * Duke handles user input, manages tasks, and interacts with the user through a command-line interface.
 */
public class Duke extends Application {
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Leo.png"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Scene scene;
    private boolean hasUserInteracted = false;

    /**
     * The file path for storing tasks.
     */
    private String FILE_PATH = System.getProperty("user.dir") + "/src/main/java/data/duke.txt";
    /**
     * The storage component responsible for reading and writing tasks to a file.
     */
    private Storage storage;
    /**
     * The task list containing the user's tasks.
     */
    private TaskList tasks;
    /**
     * The user interface for interacting with the user.
     */
    private Ui ui;
    public Duke() {
    }

    /**
     * Constructs a Duke object with the specified file path.
     *
     * @param filePath The file path for storing tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(filePath);
    }

    /**
     * Runs the Duke chatbot application, processing user input and executing commands until the user exits.
     */
    public void run() {
        String line = "------------------------------";
        Scanner obj = new Scanner(System.in);
        System.out.println("\n Hello! I'm Leo\n"
                + " What can I do for you?");
        System.out.println(line);

        while (obj.hasNextLine()) {
            String res = obj.nextLine();
            if (res.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                return;
            }
            Parser parser = new Parser(res);
            parser.execute(tasks, ui);
        }
    }
    @Override
    public void start(Stage stage) {
        initialize();
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

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

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
    /**
     * Handles the user's input by displaying it in the dialog container and showing Duke's response.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        String uText = userInput.getText();
        String dText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(uText, user),
                DialogBox.getDukeDialog(dText, duke)
        );
        userInput.clear();
        if (uText.equalsIgnoreCase("bye")) {
            Platform.exit();
        }
    }
    /**
     * Gets the response generated by the chatbot for the given user input.
     *
     * @param input The user input.
     * @return The chatbot's response.
     */
    public String getResponse(String input) {
        return processUserInput(input);
    }
    /**
     * Processes the user input, executes corresponding actions, and returns the chatbot's response.
     *
     * @param input The user input.
     * @return The chatbot's response.
     */
    private String processUserInput(String input) {
        if (input.equalsIgnoreCase("bye")) {
            Platform.runLater(() -> {
                Stage stage = (Stage) userInput.getScene().getWindow();
                stage.close();
                //Platform.exit();
            });
            return "Bye. Hope to see you again soon!";
        }
        Parser parser = new Parser(input);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOut = System.out;
        System.setOut(printStream);

        try {
            parser.execute(tasks, ui);
        } finally {
            System.setOut(originalOut);
        }
        String response = outputStream.toString();

        response = response.trim();
        return response;
    }
    /**
     * Initializes the chatbot by displaying an initial greeting in the dialog container.
     */
    private void initialize() {
        String dukeGreeting = getResponse("");
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeGreeting, duke));
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}

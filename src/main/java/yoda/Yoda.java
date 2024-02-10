package yoda;

import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import yoda.constants.FilePaths;
import yoda.gui.DialogBox;
import yoda.yodaUI.YodaUI;
import yoda.parser.Parser;
import yoda.storage.Storage;
import yoda.task.TaskList;
import java.util.Scanner;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Yoda is a chatbot that helps users to manage their tasks.
 * It can add, delete, and list tasks, as well as mark tasks as done.
 * Yoda can also find tasks by searching for keywords.
 */
public class Yoda extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private final YodaUI YODA_UI;
    private Parser parser;
    private String filePath = FilePaths.RELATIVE_OUTPUT_TXT_FILE_PATH;
    private Image yoda = new Image(this.getClass().getResourceAsStream("/images/DaYoda.png"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaDarthVader.png"));

    public Yoda() {

        Storage storage = new Storage(filePath);
        TaskList tasks;
        try {
            // Load tasks from the storage
            tasks = storage.loadTasks();
        } catch (IOException e) {
            // If there's an error loading tasks, start with an empty task list
            System.out.println("Error loading tasks: " + e.getMessage());
            tasks = new TaskList(null);
        }
        // Initialize YodaUI with the loaded tasks
        this.YODA_UI = new YodaUI("Yoda", tasks, storage);
    }

    public void run() {
        YODA_UI.printGreeting();
        Scanner scanner = new Scanner(System.in);
        Parser commandParser = new Parser(YODA_UI);
        boolean isRunning = true;
        while (isRunning) {
            String input = scanner.nextLine();
            try {
                commandParser.parseAndExecute(input);
            } catch (Exception e) {
                YODA_UI.printMessage("Error occurred: " + e.getMessage());
                isRunning = false;
            }

        }
    }

    public static void main(String[] args) {
        // Initialize Yoda with the file path for storing tasks
        launch(args);
        new Yoda().run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Yoda here! Hello"); // Adjust text to reflect application context
        Scene scene = new Scene(helloWorld, 400, 200); // Adjust size as needed
        stage.setTitle("Yoda Chatbot"); // Set window title
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
        stage.setTitle("Yoda");
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
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
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

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getYodaDialog(dukeText, new ImageView(yoda))
        );
        userInput.clear();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return input + " Yoda heard: ";
    }


}


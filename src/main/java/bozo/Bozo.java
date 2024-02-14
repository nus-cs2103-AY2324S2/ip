package bozo;

import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
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
 * The Bozo class is the main class for the Bozo chatbot.
 * It contains the main method and the run method.
 */
@SuppressWarnings("checkstyle:Regexp")
public class Bozo extends Application {
    private static TaskList list;
    private static Storage storage;
    private static Ui ui;
    private static Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image bozo = new Image(this.getClass().getResourceAsStream("/images/DaBozo.png"));

    /**
     * Constructor for the Bozo class.
     * @param filePath The file path to save the list to.
     */
    public Bozo(String filePath) {
        storage = new Storage(filePath);
        list = new TaskList(storage.loadFile());
        ui = new Ui();
        parser = new Parser();
    }

    /**
     * Empty Constructor for the Bozo class.
     */
    public Bozo() {
        storage = new Storage("./data/bozo.txt");
        list = new TaskList(storage.loadFile());
        ui = new Ui();
        parser = new Parser();
    }

    /**
     * Runs the Bozo chatbot.
     */
    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        String input;

        do {
            input = sc.nextLine();
            try {
                parser.parseCommand(input, list);
            } catch (BozoException e) {
                ui.showError(e.getMessage());
            }
        } while (!input.equals("bye"));

        ui.showGoodbye();
        storage.saveList(list);
    }

    public static void main(String[] args) {
        new Bozo("./data/bozo.txt").run();
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
        stage.setTitle("Bozo");
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

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // Show welcome message
        DialogBox bozoDialog = DialogBox.getBozoDialog(new Label("Hello! I'm Bozo\nWhat can I do for you?"),
                            new ImageView(bozo));
        bozoDialog.setAlignment(Pos.CENTER_LEFT);
        dialogContainer.getChildren().add(bozoDialog);
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
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
        Label bozoText = new Label(getResponse(userInput.getText()));

        // Add spacing between dialog boxes
        Region userSpacer = new Region();
        Region bozoSpacer = new Region();

        userSpacer.setPrefHeight(5);
        bozoSpacer.setPrefHeight(5);

        // Adjust alignment for user's dialog box
        DialogBox userDialog = DialogBox.getUserDialog(userText, new ImageView(user));
        userDialog.setAlignment(Pos.CENTER_RIGHT); // Align user's dialog box to the right
        DialogBox bozoDialog = DialogBox.getBozoDialog(bozoText, new ImageView(bozo));
        bozoDialog.setAlignment(Pos.CENTER_LEFT); // Align Bozo's dialog box to the left

        dialogContainer.getChildren().addAll(
                userDialog,
                userSpacer, // Add a Region node for spacing
                bozoDialog,
                bozoSpacer // Add a Region node for spacing
        );
        userInput.clear();
    }

    /**
     * Gets a response from the chatbot based on the user input.
     */
    private String getResponse(String input) {
        try {
            String output = parser.parseCommand(input, list);
            if (output.equals("Bye. Hope to see you again soon!")) {
                storage.saveList(list);
                Platform.exit();
            }
            return output;
        } catch (BozoException e) {
            // If there's an exception, return the error message
            return e.getMessage();
        }
    }
}

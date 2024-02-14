package morty;

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
import morty.command.Command;
import morty.ui.DialogBox;

/**
 * Morty is a chatbot that helps users to keep track of their tasks.
 */
public class Morty extends Application {

    private Storage storage;
    private TaskList tasks;
    private Response ui;

    private VBox dialogContainer;
    private TextField userInput;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/rick.png"));
    private Image morty = new Image(this.getClass().getResourceAsStream("/images/morty.png"));

    /**
     * Constructs a Morty object.
     */
    public Morty() {
    }

    /**
     * Initializes Morty with the file path to the tasks file.
     *
     * @param filePath File path to the tasks file.
     */
    private void initMorty(String filePath) {
        ui = new Response();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (MortyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Morty application.
     *
     * @param args Command line arguments.
     */
    @Override
    public void start(Stage stage) {
        // Initialize Morty with the file path to the tasks file
        initMorty("data/tasks.txt");

        stage.setTitle("Morty");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.setPrefSize(400.0, 600.0);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer = new VBox();
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        userInput.setPrefWidth(325.0);

        Button sendButton = new Button("Send");
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Handles the user input and displays the user input and Morty's response in
     * the dialog container.
     */
    private void handleUserInput() {
        if (!userInput.getText().isEmpty()) {
            Label userText = new Label(userInput.getText());
            Label mortyText = new Label(getResponse(userInput.getText()));
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(user)),
                    DialogBox.getMortyDialog(mortyText, new ImageView(morty)));
            userInput.clear();
        }
    }

    /**
     * Gets the response from Morty based on the user input.
     *
     * @param input User input.
     * @return Morty's response.
     */
    private String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Starts the Morty application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

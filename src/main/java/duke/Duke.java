package duke;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private static Ui ui = new Ui();
    public Storage storage = new Storage("./data/logfile.txt");
    private static final File FILE_PATH = new File("./data/logfile.txt");
    private List<Task> l = storage.readFromFile();

    public TaskList taskList;
    public static Parser parser;

    /**
     * Constructs the Duke application with necessary initializations.
     *
     * @throws IOException If an input or output exception occurred.
     */
    public Duke() throws IOException {
        this.taskList = new TaskList(storage, ui);
        this.parser = new Parser(ui, storage, taskList);
        l = storage.readFromFile();

        try {
            this.ui = new Ui();
            this.storage = new Storage("./data/logfile.txt");
            this.taskList = new TaskList(storage, ui);
            this.parser = new Parser(ui, storage, taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert storage != null : "Storage should not be null";
        assert taskList != null : "TaskList should not be null";
        assert parser != null : "Parser should not be null";
    }

    /**
     * The main method is the entry point to the Duke application.
     * It starts the application and runs the main loop.
     *
     * @param args Unused.
     * @throws IOException If an input or output exception occurred.
     */
    public static void main(String[] args) throws IOException {
        ui.showWelcome();
        Duke lucifer = new Duke();
        String user_word;
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Duke");
            MainWindow controller = fxmlLoader.<MainWindow>getController();
            controller.setDuke(this);
            controller.displayWelcomeMessage();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);


        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        String userText = String.valueOf(new Label(userInput.getText()));
        String dukeText = String.valueOf(new Label(getResponse(userInput.getText())));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user).getImage()),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke).getImage())
        );
        userInput.clear();
    }

    /**
     * Processes the given input using the application's logic and returns the response.
     * This method encapsulates the error handling for the command processing, ensuring that
     * any exceptions thrown during the command processing are caught and a user-friendly message
     * is returned.
     *
     * @param input The user input to be processed.
     * @return The response message after processing the input. If an exception occurs, returns an error message.
     * @throws AssertionError if the input is null, indicating a contract violation as input should always be provided.
     */
    public String getResponse(String input) {
        try {
            assert input != null : "Input should not be null";
            return parser.processCommand(input);
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }


}


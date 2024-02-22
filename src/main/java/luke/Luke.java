package luke;

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
import luke.exception.FileException;
import luke.exception.LukeException;
import luke.parser.Parser;
import luke.task.TaskList;
import luke.ui.Ui;

/**
 * Represents the main class of the program.
 */
public class Luke extends Application {
    private static final String FILE_PATH = "./src/main/data/luke.txt";
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private Image luke = new Image(this.getClass().getResourceAsStream("/images/luke.jpeg"));

    /**
     * Constructor for Luke that initializes the Ui, Storage and TaskList.
     *
     * @param filePath The file path of the file to be read from.
     */
    public Luke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readTask());

        } catch (FileException e) {
            ui.getErrorMessage(e.getMessage());
            tasks = new TaskList();
        }

    }

    /**
     * Constructor for Luke that initializes the Ui, Storage and TaskList for the GUI
     */
    public Luke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.readTask());

        } catch (FileException e) {
            ui.getErrorMessage(e.getMessage());
            tasks = new TaskList();
        }

    }

    /**
     * Runs the program.
     */
    public void run() {
        // Greetings
        System.out.println(ui.welcome());

        boolean isExit = false;
        Parser parser = new Parser("");
        String response = "";

        // Conditions
        while (!isExit) {
            String inputString = ui.readCommand();
            parser.setInput(inputString);
            response = parser.parse(tasks, ui, storage);
            isExit = parser.isExit();

        }

        // Bye and exits
        System.out.println(response);
    }

    /**
     * Main method to run the program.
     *
     * @param args The arguments passed in.
     * @throws LukeException If an error occurs.
     */
    public static void main(String[] args) throws LukeException {
        new Luke(FILE_PATH).run();

    }

    @Override
    public void start(Stage stage) {
        AnchorPane mainLayout = formatStage(stage);
        formatDialogBox(stage, mainLayout);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));


    }

    private AnchorPane formatStage(Stage stage) {
        //Step 1. Setting up required components
        // Referred to https://se-education.org/guides/tutorials/javaFxPart2.html and used the code
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

        return mainLayout;

    }


    private void formatDialogBox(Stage stage, AnchorPane mainLayout) {
        //Step 2. Formatting the window to look as expected
        stage.setTitle("Luke");
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

        Label lukeText = new Label(ui.welcome());
        dialogContainer.getChildren().addAll(
                DialogBox.getLukeDialog(lukeText, new ImageView(luke))
        );
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label lukeText = new Label(getResponse());

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getLukeDialog(lukeText, new ImageView(luke))
        );

        if (userInput.getText().equals("bye")) {
            System.exit(0);
        }
        userInput.clear();
    }

    private String getResponse() {
        String input = userInput.getText();
        Parser parser = new Parser(input);
        return parser.parse(tasks, ui, storage);

    }

}

package yapchit;

import yapchit.yapchitexceptions.YapchitException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import yapchit.yapchitui.DialogBox;


/**
 * Yapchit is a bot that allows users to create and manage their tasks. The Yapchit class
 * is the entry point into the program and encapsulates a number of other classes that
 * enable the functionality of Yapchit.
 *
 * @author Archit Goswami
 * @version 1.0
 * @since 2024-02-01
 */
public class Yapchit extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image yapchit = new Image(this.getClass().getResourceAsStream("/images/DaYapchit.png"));

    @Override
    public void start(Stage stage) {

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

        stage.setTitle("Yapchit");
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

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

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label yapchitText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getYapchitDialog(yapchitText, new ImageView(yapchit))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Yapchit heard: " + input;
    }


    public Yapchit(){

    }


    /**
     * List of operations that the Yapchit functionality can handle. Operations
     * are keywords that the user can enter as Yapchit input.
     * Yapchit additionally stores tasks across restarts.
     */
    enum Operations {
        LIST,
        MARK,
        UNMARK,
        DEADLINE,
        EVENT,
        TODO,
        DELETE,
        FIND;
    }

    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    private Handler handler;
    private boolean isBye;
    private String filePath;

    /**
     * Constructor of a Yapchit object. Initiates instance of components of Yapchit and loads
     * tasks from existing file (if any).
     *
     * @param filePath The file path to the storage file used to keep track of tasks.
     */
    public Yapchit(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.isBye = false;
        this.parser = new Parser();
        this.handler = new Handler();
        this.filePath = filePath;

        try{
            this.tasks = storage.importFromFile(filePath, ui, handler, parser);
        } catch (YapchitException e) {
            ui.printTasklistLoadError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Initiates core functionality of the bot by running a main loop that harnesses relevant components
     * to accept, handle inputs and output a response.
     *
     * Main loop ends when the 'bye' command is entered by the user.
     */
    public void run() {
        ui.printIntro();
        String input = ui.scanInput();

        runHelper(input);

        storage.updateFile(filePath, this.tasks);
        ui.printOutro();
    }

    private void runHelper(String input) {
        while (!handler.checkIsBye(input)) {
            try{
                Yapchit.Operations k = parser.parseInputOperation(input);
                handler.handleOperation(input, k, tasks, ui, parser);
            } catch (YapchitException e) {
                Ui.print(e.getMessage());
            } finally {
                input = ui.scanInput();
            }
        }
    }

    /**
     * The entry point of the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Yapchit bot = new Yapchit("./src/main/data/dataStore.txt");
        bot.run();
    }

}

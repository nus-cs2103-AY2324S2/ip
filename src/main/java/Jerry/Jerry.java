package Jerry;

import Jerry.command.ByeCommand;
import Jerry.command.Command;

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
 * Main class for the Jerry Chatbot application.
 * <p>
 * This class is responsible for initializing the application, including
 * setting up the user interface, the parser for interpreting user commands,
 * and the storage system for managing tasks. It contains the main loop that
 * reads and executes commands until the application is terminated.
 */
public class Jerry {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private String filePath;

    /**
     * Constructs a new instance of Jerry with default settings.
     * Initializes the UI, parser, and storage components, and sets the file path
     * used for storing tasks.
     */
    public Jerry() {
        filePath = "./data/jerry.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(Storage.loadTasks(filePath));
        parser = new Parser(ui, tasks);
    }

//    @Override
//    public void start(Stage stage) {
//        //Step 1. Setting up required components
//
//        //The container for the content of the chat to scroll.
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
//
//        stage.setScene(scene);
//        stage.show();
//
//        //Step 2. Formatting the window to look as expected
//        stage.setTitle("Jerry");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        //Step 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Command command = parser.parse(input);
        String response = command.execute();

        Storage.saveTasks(tasks.getTasks(), "./data/jerry.txt");
        return "JERRY\n " + response;
    }


    /**
     * The entry point of the application.
     *
     * @param args Command line arguments passed to the application (not used).
     */
    public static void main(String[] args) {
        new Jerry().run();
    }

    /**
     * Starts the application's main loop.
     * <p>
     * In the loop, the application reads commands from the user, parses them,
     * executes them, and then waits for the next command. This process repeats
     * until a termination command is received.
     */
    public void run() {

        ui.showWelcome();
        tasks = new TaskList(Storage.loadTasks(filePath));
        parser = new Parser(ui, tasks);
        boolean isExit = false;


        while (!isExit) {
            String input = ui.readCommand();
            Command command = parser.parse(input);
            command.execute();

            Storage.saveTasks(tasks.getTasks(), "./data/jerry.txt");

            if (command instanceof ByeCommand) {
                isExit = true;
            }

        }
    }
}





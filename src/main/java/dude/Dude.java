package dude;

import java.util.NoSuchElementException;
import java.util.Scanner;

import javafx.application.Application;

import dude.commands.Command;
import dude.commands.CommandTypes;
import dude.commands.Parser;
import dude.exceptions.DudeException;
import dude.tasks.TaskList;
import dude.utils.Storage;
import dude.utils.Ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * The main class of the Duke application.
 * <p>
 * This class is responsible for the main logic of the application.
 * <p>
 * The main loop of the application is responsible for reading user input,
 * parsing it into a command, executing the command and saving the task list to disk.
 **/
public class Dude extends Application {
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    private boolean isRunning = true;

    /**
     * Constructor for the Dude class.
     * <p>
     * The constructor initializes the storage and UI components of the application.
     * It also loads the task list from the storage.
     *
     * @param filePath The file path to the storage file.
     */
    public Dude(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();

        TaskList temp = null;
        try {
            temp = this.storage.loadTasks();
        } catch (Exception e) { //Thrown when file gets corrupted
            ui.showMessage("An error occurred while loading the tasks. Deleting the storage and starting with "
                    + "an empty task list.");
            this.storage.deleteStorage();
            temp = new TaskList();
        }

        this.taskList = temp;
    }

    /**
     * This method runs the main loop of the application.
     * <p>
     * This method is responsible for reading user input, parsing it into a command,
     * executing the command and saving the task list to disk.
     */
    public void run() {

        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        while (this.isRunning) {
            String input = extractInput(sc);
            Command command = Parser.parse(input, taskList);

            String response = executeCommand(command);
            ui.showMessage(response);

            saveToDisk();

            if (command.getCommandType() == CommandTypes.BYE) {
                this.isRunning = false;
            }
        }
    }

    private static String extractInput(Scanner sc) {
        String input = "";
        try {
            input = sc.nextLine();
        } catch (NoSuchElementException e) {
            //this will not be handled. App will only exit at bye command.
            input = "";
        }
        return input;
    }

    private static String executeCommand(Command command) {
        try {
            return command.execute();
        } catch (DudeException | IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    private void saveToDisk() {
        try {
            this.storage.saveTasks(taskList);
        } catch (Exception e) {
            this.ui.showMessage("An error occurred while saving the tasks.");
        }
    }

    public Dude() {
        this("data/tasks.txt");
    }

    @Override
    public void start(Stage stage) {


        Label label = new Label("Hello Boii");

        ScrollPane scrollPane = new ScrollPane();
        VBox container = new VBox();

        scrollPane.setContent(container);

        Button sendButton = new Button("Send");
        TextField textField = new TextField();

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(label, scrollPane, textField, sendButton);


        Scene scene = new Scene(mainLayout);

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(440);

        scrollPane.setPrefSize(420, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        textField.setPrefWidth(300);
        sendButton.setPrefWidth(100);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setLeftAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 10.0);
        AnchorPane.setRightAnchor(sendButton, 5.0);

        AnchorPane.setBottomAnchor(textField, 10.0);
        AnchorPane.setLeftAnchor(textField, 5.0);

        container.setPrefHeight(Region.USE_COMPUTED_SIZE);

        sendButton.setOnMouseClicked(e -> {
            String input = textField.getText();
            container.getChildren().add(getUserMessageView(input));
            textField.clear();
        });

        textField.setOnAction(e -> {
            String input = textField.getText();
            container.getChildren().add(getUserMessageView(input));
            textField.clear();
        });

        mainLayout.setPrefSize(420, 600.0);
        mainLayout.heightProperty().addListener((observable) -> {
            scrollPane.setVvalue(1.0);
            System.out.println("Height: " + mainLayout.getHeight());
        });

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }


    private static Label getUserMessageView(String message) {
        System.out.println("User: " + message);
        Label label = new Label(message);
        label.setWrapText(true);
        label.setMinHeight(Region.USE_PREF_SIZE);
        label.setPrefWidth(Region.USE_COMPUTED_SIZE);
        label.getStyleClass().add("dialog-label");
        return label;
    }
}

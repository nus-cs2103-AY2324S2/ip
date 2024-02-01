package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import duke.command.Bye;
import duke.command.Command;
import duke.exception.DukeException;

/**
 * Main Class for our Chat bot
 */
public class Duke extends Application {
    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/husserl.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/satre.jpg"));

    public Duke() {
        // ...
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getDukeDialog(getResponse(userInput.getText()), duke)
        );
        userInput.clear();
    }

    String getResponse(String input) {
        return "Duke heard: " + input;
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
        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        //More code to be added here later
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
    /**
     * Constructor for chatbot.
     * @param filePath file to load.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            String[] pathStep = filePath.split("/");
            String progressivePath = "";
            for (int i = 0; i < pathStep.length - 1; i++) {
                String dir = pathStep[i];
                progressivePath = String.format("%s%s/", progressivePath, dir);
            }
            File directory = new File(progressivePath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File makeupFile = new File(filePath);
            try {
                makeupFile.createNewFile();
            } catch (IOException ex) {
                System.out.println("Logically it won't happen, but who knows?");
                System.exit(-1);
            }
            tasks = new TaskList(new ArrayList<>());
        }
        parser = new Parser(tasks);
    }

    /**
     * Run the chat bot.
     */
    public void run() {
        ui.showWelcome();
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                Command cmd = parser.parse(fullCommand);
                if (cmd instanceof Bye) {
                    ui.sayGoodbye();
                }
                ui.printReply(cmd.reply());
            } catch (DukeException e) {
                System.out.printf("    %s\n", e.getMessage());
            }
            try {
                storage.writeToFile(tasks.getTaskList());
            } catch (IOException e) {
                System.out.println("Why delete the file when program running?");
                System.exit(-1);
            }

        }
    }

    /**
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new Duke("data/tasklist.txt").run();
    }


}

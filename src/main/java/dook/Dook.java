package dook;

import command.Command;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import task.TaskList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Dook extends Application {

    private static final ArrayList<String> FILE_PATH = new ArrayList<>(List.of("data", "dook.txt"));
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/Meow1.png"));
    private Image dook = new Image(this.getClass().getResourceAsStream("/images/Meow2.png"));


    public Dook() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(FILE_PATH);
    }

    /**
     * Main body of the code. Repeatedly takes in a single line command
     * and acts on the state of Dook accordingly.
     */
    public void run() {
        try {
            this.storage.checkFile();
        } catch (Exception e) {
            System.out.println("meow :( an error happened when opening your files, please restart to prevent data loss");
            return;
        }
        this.ui.introduce();
        try {
            this.tasks = this.storage.loadTaskListFromFile();
        } catch (IOException e) {
            this.ui.println("error while loading file, specific error: " + e);
        } catch (DookException e) {
            this.ui.println(e.getMessage());
        }
        boolean willExitLoop = false;
        while (!willExitLoop) {
            String input = ui.getInput();
            this.ui.printSeparator();
            try {
                Command c = Parser.parse(input);
                c.execute(this.tasks, this.ui, this.storage);
                willExitLoop = c.isExit();
            } catch (Exception e) {
                this.ui.printException(e);
            } finally {
                this.ui.printSeparator();
            }
        }
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

        //More code to be added here later
        stage.setTitle("Dook");
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
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

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
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dook))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        return "Dook heard: " + input;
    }
}
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


public class Dook {

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

    public String getResponse(String input) {
        try {
            this.tasks = this.storage.loadTaskListFromFile();
            Command c = Parser.parse(input);
            String response = c.execute(this.tasks, this.ui, this.storage);
            return response;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
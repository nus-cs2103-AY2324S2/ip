package seedu.chatteroo;

import seedu.chatteroo.commands.Command;
import seedu.chatteroo.parser.Parser;
import seedu.chatteroo.storage.Storage;
import seedu.chatteroo.tasks.TaskList;
import seedu.chatteroo.ui.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Scanner;

import java.io.IOException;

/**
 * The main class of the Chatteroo ChatBot program.
 */
public class Chatteroo {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;


//    private Image user = new Image(this.getClass().getResourceAsStream("/images/buffkangaroo.jpg"));
//    private Image duke = new Image(this.getClass().getResourceAsStream("/images/cutekangaroo.jpg"));

    /**
     * Constructor for the Chatteroo class.
     * @throws IOException If an I/O error occurs.
     */
    public Chatteroo() throws IOException {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.loadTasks());
    }


    /**
     * Runs the Chatteroo program and handles user input.
     * @throws IOException If an I/O error occurs.
     */
    public String run(String input) throws IOException {
        String response = "";

        try {
            Command c = Parser.parseInput(input);
            response = c.execute(tasks, ui, storage);
        } catch (Exception e) {
                System.out.println(e.getMessage());
        }
        storage.saveTasks(tasks);
        return response;
    }

}





package duke.main;
import duke.command.Command;
import duke.exception.DukeException;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Represents the main class for the chat application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private static final String filePath = "data/duke.txt";
    private boolean exited = false;
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        if (!exited) {
            try {
                ui.repeat();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                exited = c.isExit();
                return ui.getAnswer();
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
        else {
            return "";
        }

    }


    /**
     * Constructs a Duke instance with the given file path to store tasks.
     */
    Duke() {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.loadList());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }
}


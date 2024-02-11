package duke;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/duke.txt");
        tasks = new TaskList(storage.load());
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        String output;
        try {
            Command c = Parser.parseToCommand(input);
            output = c.execute(tasks, ui);
        } catch (DukeException ex) {
            output = ex.getMessage();
        }
        return output;
    }

    public void run() {
        this.ui.open();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parseToCommand(fullCommand);
                c.execute(tasks, ui);
                ui.showLine();
                isExit = c.isExit();
            } catch (DukeException ex) {
                System.out.println(ex.toString());
                ui.showLine();
            }
        }
        ui.close();
        storage.save(tasks);
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}

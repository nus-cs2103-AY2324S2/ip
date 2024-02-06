package dook;

import command.Command;
import task.TaskList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class Dook extends Application {

    private static final ArrayList<String> FILE_PATH = new ArrayList<>(List.of("data", "dook.txt"));
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Storage storage;

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
                Command c = this.parser.parse(input);
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
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
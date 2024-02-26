package damon;

import damon.command.Command;
import damon.exceptions.DamonExceptions;
import damon.storage.Storage;
import damon.tasklist.TaskList;
import damon.ui.Ui;
import damon.parser.Parser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * Creates a chatbot called Damon. A Damon object responds to user's input
 * by adding, deleting, marking, and un-marking Tasks in TaskList object.
 * Damon also store Tasks in TaskList in storage file of its filepath using Storage object.
 */
public class Damon extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Damon() {
        // ...
    }

    /**
     * Constructs a new Damon object with String filePath parameter.
     *
     * @param filePath Path of storage file of this Damon object.
     */
    public Damon(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DamonExceptions e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs chatbot Damon.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                //ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DamonExceptions e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        //Solution below inspired by https://stackoverflow.com/questions/2715118/how-to-change-the-size-of-the-font-of-a-jlabel-to-take-the-maximum-size
        helloWorld.setFont(new Font("Arial", 50));
        Scene helloScene = new Scene(helloWorld); // Setting the scene to be our Label

        Label goodByeWorld = new Label("Goodbye World!"); // Creating a new Label control
        //Solution below inspired by https://stackoverflow.com/questions/2715118/how-to-change-the-size-of-the-font-of-a-jlabel-to-take-the-maximum-size
        goodByeWorld.setFont(new Font("Arial", 50));
        Scene goodByeScene = new Scene(goodByeWorld); // Setting the scene to be our Label

        stage.setScene(goodByeScene); // Setting the stage to show our screen
        stage.show();
        stage.close();
        stage.setScene(helloScene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public static void main(String[] args) {
        new Damon("..\\Damon.txt").run();
    }
}


package dune;

import dune.task.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.util.Scanner;

/**
 * The main class for the program.
 */
public class Dune extends Application {


    private TaskList tasks;

    private Storage storage;

    private Parser p;

    private Ui ui;

    /**
     * Constructor for dune.Dune.
     */
    public Dune() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList();
        this.storage.loadTasks(this.tasks);
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Runs the program.
     */
    public void run() {
        ui.printWelcome();
        p = new Parser();
        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;
        while (isContinue) {
            isContinue = p.parse(scanner, tasks, ui, this.storage);
        }
    }

    public static void main(String[] args) {
        new Dune().run();
    }

}

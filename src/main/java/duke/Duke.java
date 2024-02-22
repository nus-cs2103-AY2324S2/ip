package duke;

import java.util.ArrayList;
import java.util.Scanner;
import duke.Command.Command;
import duke.Task.Task;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;



/**
 * The Duke class represents a task management application.
 * It allows users to add, delete, and mark tasks as done.
 */
public class Duke extends Application{
    private TaskList taskList;

    /**
     * Constructs a Duke object.
     * Loads tasks from file and initializes the task list.
     */
    public Duke() {
        ArrayList<Task> tasks = Storage.loadTasksFromFile();
        taskList = new TaskList(tasks);
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Runs the Duke application.
     * Prompts the user for commands and executes them until the user enters "bye".
     * Saves tasks to file before exiting.
     */
    void run() {
        Ui.printLogo();
        Ui.printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String command = Ui.getUserCommand(scanner);

        while (!command.equals("bye")) {
            try {
                Command cmd = Parser.parseCommand(command);
                Ui.printMessage(cmd.execute(taskList, command));
            } catch (DukeException e) {
                Ui.printMessage("OOPS!!! " + e.getMessage());
            }
            command = Ui.getUserCommand(scanner);
        }

        Storage.saveTasksToFile(taskList.getTasks());
        Ui.printGoodbyeMessage();
    }

    /**
     * The entry point of the Duke application.
     * Creates a Duke object and runs the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
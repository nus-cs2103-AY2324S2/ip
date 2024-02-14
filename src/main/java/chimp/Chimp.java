package chimp;

import java.security.InvalidParameterException;
import java.util.Scanner;
import chimp.command.Command;
import chimp.core.*;
import chimp.exception.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Chimp extends Application{

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }


    /**
     * The main method is the entry point of the Chimp program.
     * It initializes the user interface, task list, and storage objects.
     * It then enters a loop to continuously read user input, parse it into a command,
     * execute the command, and save the output to a file.
     * If an invalid command or exception occurs, an error message is displayed.
     * The loop continues until the user enters an exit command.
     * Finally, the scanner is closed.
     *
     * @param args the command line arguments
     * @throws InvalidParameterException if an invalid parameter is passed
     */
    public static void main(String[] args) throws InvalidParameterException {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        Storage storage = new Storage();
        ui.say("greet");

        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String inp = sc.nextLine();
            try {
                Command c = Parser.parse(inp);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                Storage.saveOutputToFile(tasks.toString());
            } catch (InvalidCommandException
                    | CommandParseException
                    | CommandExecuteException e) {
                ui.say("hoo");
            } catch (IndexOutOfBoundsException e) {
                ui.say("hoo");
            }
        }
        sc.close();
    }
}

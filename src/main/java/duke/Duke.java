package duke;


import java.io.IOException;
import java.util.Scanner;
import duke.ui.Output;
import duke.storage.Storage;
import duke.parser.Parser;


/**
 * Entry point of the Duke task management application.
 * <p>
 * This class initializes the application's core components, including storage, parser, and output handling.
 * It manages the main application loop, processing user inputs through the command line interface and
 * executing corresponding actions until the user decides to exit. The application supports tasks such as
 * adding, listing, marking, and deleting tasks, with changes persistently stored in a file.
 * </p>
 */
public class Duke {
    /**
     * Starts the application and manages the main user interaction loop.
     * <p>
     * This method sets up the necessary components for the application to run: storage for task persistence,
     * a parser for interpreting user commands, and an output handler for generating user-facing messages.
     * It reads user input from the command line, processes it, and provides feedback until the "bye" command
     * is issued. Changes to tasks are saved to a file, ensuring persistence across application sessions.
     * </p>
     *
     * @param args Command line arguments passed to the application (not used).
     */
    public static void main(String[] args) {
        Storage storage = new Storage();
        Parser parser = new Parser(storage);
        Output output = new Output(parser, storage);
        System.out.println(output.welcome());
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(output.execute(input));
                break;
            } else {
                System.out.println(output.execute(input));
            }
            //writing into the file
            try {
                storage.writeToFile(storage.load());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        sc.close();
    }   
}

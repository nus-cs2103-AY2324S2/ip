package duke.rahbot;

import javafx.application.Application;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Output;
import java.util.Scanner;
import java.io.IOException;
/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        /*
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
        }*/
        Application.launch(Duke.class, args);
    }
}
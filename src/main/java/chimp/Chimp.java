package chimp;

import java.security.InvalidParameterException;
import java.util.Scanner;
import chimp.command.Command;
import chimp.core.*;
import chimp.exception.*;

public class Chimp {
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
        TaskList list = new TaskList();
        Storage storage = new Storage();
        ui.say("greet");

        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String inp = sc.nextLine();
            try {
                Command c = Parser.parse(inp);
                c.execute(list, ui, storage);
                isExit = c.isExit();
                Storage.saveOutputToFile(list.toString());
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

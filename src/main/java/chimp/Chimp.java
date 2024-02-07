package chimp;

import java.security.InvalidParameterException;
import java.util.Scanner;
import chimp.command.Command;
import chimp.core.*;
import chimp.exception.*;

public class Chimp {
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

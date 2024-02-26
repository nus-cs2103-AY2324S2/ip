package louie.commands;

import louie.Louie;
import louie.LouieException;
import louie.Parser;
import javafx.application.Platform;

public class ByeCommand extends Command {
    @Override
    public void run(Parser parser, Louie louie) throws LouieException {
        parser.assertEnd();
        louie.getUi().print("Ok, going to sleep...");
        Platform.exit();
    }
}

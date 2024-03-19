package louie.commands;

import louie.Louie;
import louie.LouieOptionParsingException;
import louie.Parser;

public class ListCommand extends Command {

    public static final String SUCCESS_MSG = "Here's what you've done today...\n%s";

    @Override
    public void run(Parser parser, Louie louie) throws LouieOptionParsingException {
        parser.assertEnd();
        louie.getUi().print(String.format(SUCCESS_MSG, louie.getTasks().toDisplayString()));
    }
}

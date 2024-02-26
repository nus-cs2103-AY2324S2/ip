package louie.commands;

import louie.Louie;
import louie.LouieException;
import louie.Parser;

public class FindCommand extends Command {

    public static final String SUCCESS_MSG = "I found the following tasks with names that match '%s':\n%s";

    @Override
    public void run(Parser parser, Louie louie) throws LouieException {

        String toFind = parser.rest();
        String response = String.format
                (SUCCESS_MSG, toFind, louie.getTasks().filterSubString(toFind));
        louie.getUi().print(response);
    }
}

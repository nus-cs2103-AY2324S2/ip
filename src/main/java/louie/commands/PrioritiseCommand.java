package louie.commands;

import louie.Louie;
import louie.LouieException;
import louie.LouieOptionParsingException;
import louie.Parser;
import louie.tasks.Priority;
import louie.tasks.Task;

public class PrioritiseCommand extends Command {
    private static final String SUCCESS_MSG = "Ok, I've set this task as SUPA DUPA IMPORTANT!!!!!\n%s";
    private static final String INT_PARSE_EXCEPTION_MSG = "I expected a number but %s was given instead";

    private int tryParseInt(Parser p) throws LouieOptionParsingException {
        String indexStr = p.next();
        try {
            return Integer.parseInt(indexStr);
        } catch (NumberFormatException e) {
            throw new LouieOptionParsingException(String.format(INT_PARSE_EXCEPTION_MSG, indexStr));
        }
    }

    @Override
    public void run(Parser parser, Louie louie) throws LouieException {
        int index = tryParseInt(parser);
        Task t = louie.getTasks().get(index - 1);
        t.setPriority(Priority.HIGH);
        louie.getUi().print(String.format(SUCCESS_MSG, t.describe()));
        louie.getStorage().writeTasks(louie.getTasks());
    }
}

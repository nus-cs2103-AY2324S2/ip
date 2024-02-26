package louie.commands;

import louie.Louie;
import louie.LouieException;
import louie.LouieOptionParsingException;
import louie.Parser;
import louie.tasks.Task;

public class MarkCommand extends Command {
    private static final String INT_PARSE_EXCEPTION_MSG = "I expected a number but %s was given instead";
    private static final String INVALID_INDEX_MSG = "You tried to access an invalid task index: %d";
    private static final String SUCCESS_MSG = "CONGRATULATION!!!!!! you completed this task:\n%s";

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
        Task t;

        parser.assertEnd();

        try {
            t = louie.getTasks().get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new LouieException(String.format(INVALID_INDEX_MSG, index));
        }

        t.mark();
        louie.getUi().print(String.format(SUCCESS_MSG, t.describe()));
        louie.getStorage().writeTasks(louie.getTasks());
    }
}

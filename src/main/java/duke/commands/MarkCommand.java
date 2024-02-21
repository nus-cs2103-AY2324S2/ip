package duke.commands;

import duke.Duke;
import duke.DukeException;
import duke.DukeOptionParsingException;
import duke.Parser;
import duke.tasks.Task;

public class MarkCommand extends Command {
    private static final String INT_PARSE_EXCEPTION_MSG = "I expected a number but %s was given instead";
    private static final String INVALID_INDEX_MSG = "You tried to access an invalid task index: %d";
    private static final String SUCCESS_MSG = "CONGRATULATION!!!!!! you completed this task:\n%s";

    private int tryParseInt(Parser p) throws DukeOptionParsingException {
        String indexStr = p.next();
        try {
            return Integer.parseInt(indexStr);
        } catch (NumberFormatException e) {
            throw new DukeOptionParsingException(String.format(INT_PARSE_EXCEPTION_MSG, indexStr));
        }
    }

    @Override
    public void run(Parser parser, Duke duke) throws DukeException {

        int index = tryParseInt(parser);
        Task t;

        parser.assertEnd();

        try {
            t = duke.getTasks().get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format(INVALID_INDEX_MSG, index));
        }

        t.mark();
        duke.getUi().print(String.format(SUCCESS_MSG, t.describe()));
        duke.getStorage().writeTasks(duke.getTasks());
    }
}

package duke.commands;

import duke.Duke;
import duke.DukeException;
import duke.DukeOptionParsingException;
import duke.Parser;
import duke.tasks.Priority;
import duke.tasks.Task;

public class DeprioritiseCommand extends Command {
    private static final String SUCCESS_MSG = "Ok, I've set this task as not so important...\n%s";
    private static final String INT_PARSE_EXCEPTION_MSG = "I expected a number but %s was given instead";

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
        Task t = duke.getTasks().get(index - 1);
        t.setPriority(Priority.LOW);
        duke.getUi().print(String.format(SUCCESS_MSG, t.describe()));
        duke.getStorage().writeTasks(duke.getTasks());
    }
}

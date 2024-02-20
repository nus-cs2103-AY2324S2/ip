package duke.commands;

import duke.Duke;
import duke.DukeException;
import duke.DukeOptionParsingException;
import duke.Parser;
import duke.tasks.Task;

public class MarkCommand extends Command {
    private int tryParseInt(Parser p) throws DukeOptionParsingException {
        String indexStr = p.next();
        try {
            return Integer.parseInt(indexStr);
        } catch (NumberFormatException e) {
            throw new DukeOptionParsingException
                    (String.format("I expected a number but %s was given instead", indexStr));
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
            throw new DukeException(String.format("You tried to access an invalid task index: %d", index));
        }

        t.mark();
        duke.print("CONGRATULATION!!!!!! you completed this task:\n" + t.describe());
        duke.getStorage().writeTasks(duke.getTasks());
    }
}

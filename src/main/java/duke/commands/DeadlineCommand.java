package duke.commands;

import duke.Duke;
import duke.DukeException;
import duke.DukeOptionParsingException;
import duke.Parser;
import duke.tasks.Deadline;
import duke.tasks.Task;

import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    @Override
    public void run(Parser parser, Duke duke) throws DukeException {
        String by;
        String name;
        Task t;

        final String NO_NAME = "you didn't specify specify a name for your deadline";
        final String NO_BY = "you failed to specify an end date using '/by'";

        name = parser.nextUntilOption();

        parser.assertNext("/by");
        by = parser.nextUntilOption();

        parser.assertEnd();

        if (name.isEmpty()) {
            throw new DukeOptionParsingException(NO_NAME);
        }

        if (by.isEmpty()) {
            throw new DukeOptionParsingException(NO_BY);
        }

        try {
            t = new Deadline(name, by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Couldn't parse the end date " + by);
        }

        duke.getUi().print(String.format("Ok, I've added a new deadline...\n  %s", t.describe()));
        duke.getTasks().add(t);
        duke.getStorage().writeTasks(duke.getTasks());
    }
}

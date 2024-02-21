package duke.commands;

import duke.Duke;
import duke.DukeException;
import duke.DukeOptionParsingException;
import duke.Parser;
import duke.tasks.Deadline;
import duke.tasks.Task;

import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private static final String NO_NAME_MSG = "you didn't specify specify a name for your deadline";
    private static final String NO_BY_MSG = "you failed to specify an end date using '/by'";
    private static final String DATE_PARSE_EXCEPTION_MSG = "Couldn't parse the end date %s";
    private static final String ADDED_DEADLINE_MSG = "Ok, I've added a new deadline...\n  %s";

    @Override
    public void run(Parser parser, Duke duke) throws DukeException {
        String by;
        String name;
        Task t;


        name = parser.nextUntilOption();

        parser.assertNext("/by");
        by = parser.nextUntilOption();

        parser.assertEnd();

        if (name.isEmpty()) {
            throw new DukeOptionParsingException(NO_NAME_MSG);
        }

        if (by.isEmpty()) {
            throw new DukeOptionParsingException(NO_BY_MSG);
        }

        try {
            t = new Deadline(name, by);
        } catch (DateTimeParseException e) {
            throw new DukeException(String.format(DATE_PARSE_EXCEPTION_MSG, by));
        }

        duke.getUi().print(String.format(ADDED_DEADLINE_MSG, t.describe()));
        duke.getTasks().add(t);
        duke.getStorage().writeTasks(duke.getTasks());
    }
}

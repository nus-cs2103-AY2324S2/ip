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
        StringBuilder by = new StringBuilder();
        StringBuilder name = new StringBuilder();
        Task t;

        final String NO_NAME = "you didn't specify specify a name for your deadline";
        final String NO_BY = "you failed to specify an end date using '/by'";

        while (!parser.peek().startsWith("/")) {
            if (!name.isEmpty()) {
                name.append(" ");
            }
            name.append(parser.next());
        }

        if (name.isEmpty()) {
            throw new DukeOptionParsingException(NO_NAME);
        }

        {
            String str = parser.next();
            if (!str.equals("/by")) {
                throw new DukeOptionParsingException
                        (String.format("I encountered an unexpected option '%s'", str));
            }
        }

        while (parser.hasNext()) {
            String next = parser.next();
            if (next.startsWith("/")) {
                throw new DukeOptionParsingException
                        (String.format("I encountered an unexpected option '%s'", next));
            }
            if (!by.isEmpty()) {
                by.append(" ");
            }
            by.append(next);
        }

        if (by.isEmpty()) {
            throw new DukeOptionParsingException(NO_BY);
        }

        try {
            t = new Deadline(name.toString(), by.toString());
        } catch (DateTimeParseException e) {
            throw new DukeException("Couldn't parse the end date " + by);
        }

        duke.getUi().print(String.format("Ok, I've added a new deadline...\n  %s", t.describe()));
        duke.getTasks().add(t);
        duke.getStorage().writeTasks(duke.getTasks());
    }
}

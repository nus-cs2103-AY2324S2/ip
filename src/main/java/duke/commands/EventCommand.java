package duke.commands;

import duke.Duke;
import duke.DukeException;
import duke.DukeOptionParsingException;
import duke.Parser;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;

import java.time.format.DateTimeParseException;


public class EventCommand extends Command {

    private static final String NO_NAME = "you didn't specify a name for your event";
    private static final String NO_FROM = "you failed to specify an start date using '/from'";
    private static final String NO_TO = "you failed to specify an end date using '/to'";

    @Override
    public void run(Parser parser, Duke duke) throws DukeException {
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();
        StringBuilder name = new StringBuilder();
        Event t;

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
            if (!str.equals("/from")) {
                throw new DukeOptionParsingException
                        (String.format("I encountered an unexpected option '%s'", str));
            }
        }


        while (!parser.peek().startsWith("/")) {
            if (!from.isEmpty()) {
                from.append(" ");
            }
            from.append(parser.next());
        }

        if (from.isEmpty()) {
            throw new DukeOptionParsingException(NO_FROM);
        }

        {
            String str = parser.next();
            if (!str.equals("/to")) {
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
            if (!to.isEmpty()) {
                to.append(" ");
            }
            to.append(next);
        }

        if (to.isEmpty()) {
            throw new DukeOptionParsingException(NO_TO);
        }

        try {
            t = new Event(name.toString(), from.toString(), to.toString());
        } catch (DateTimeParseException e) {
            throw new DukeException(String.format
                    ("Couldn't parse the start/end date %s/%s", from, to));
        }

        duke.print(String.format("Ok, I've added a new event...\n  %s", t.describe()));
        duke.getTasks().add(t);
        duke.getStorage().writeTasks(duke.getTasks());
    }
}

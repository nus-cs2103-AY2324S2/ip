package duke.commands;

import java.time.format.DateTimeParseException;

import duke.Duke;
import duke.DukeException;
import duke.DukeOptionParsingException;
import duke.Parser;
import duke.tasks.Event;

public class EventCommand extends Command {

    private static final String NO_NAME = "you didn't specify a name for your event";
    private static final String NO_FROM = "you failed to specify an start date using '/from'";
    private static final String NO_TO = "you failed to specify an end date using '/to'";

    @Override
    public void run(Parser parser, Duke duke) throws DukeException {
        String from;
        String to;
        String name;
        Event t;

        name = parser.nextUntilOption();

        parser.assertNext("/from");
        from = parser.nextUntilOption();

        parser.assertNext("/to");
        to = parser.nextUntilOption();

        parser.assertEnd();

        if (name.isEmpty()) {
            throw new DukeOptionParsingException(NO_NAME);
        }

        if (from.isEmpty()) {
            throw new DukeOptionParsingException(NO_FROM);
        }

        if (to.isEmpty()) {
            throw new DukeOptionParsingException(NO_TO);
        }

        try {
            t = new Event(name, from, to);
        } catch (DateTimeParseException e) {
            throw new DukeException(String.format
                    ("Couldn't parse the start/end date %s/%s", from, to));
        }

        duke.getUi().print(String.format("Ok, I've added a new event...\n  %s", t.describe()));
        duke.getTasks().add(t);
        duke.getStorage().writeTasks(duke.getTasks());
    }
}

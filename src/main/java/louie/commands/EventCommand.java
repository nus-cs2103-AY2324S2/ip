package louie.commands;

import java.time.format.DateTimeParseException;

import louie.Louie;
import louie.LouieException;
import louie.LouieOptionParsingException;
import louie.Parser;
import louie.tasks.Event;

public class EventCommand extends Command {

    private static final String NO_NAME = "you didn't specify a name for your event";
    private static final String NO_FROM = "you failed to specify an start date using '/from'";
    private static final String NO_TO = "you failed to specify an end date using '/to'";
    private static final String DATE_PARSE_EXC_MSG = "Couldn't parse the start/end date %s/%s";
    private static final String SUCCESS_MSG = "Ok, I've added a new event...\n  %s";

    @Override
    public void run(Parser parser, Louie louie) throws LouieException {
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
            throw new LouieOptionParsingException(NO_NAME);
        }

        if (from.isEmpty()) {
            throw new LouieOptionParsingException(NO_FROM);
        }

        if (to.isEmpty()) {
            throw new LouieOptionParsingException(NO_TO);
        }

        try {
            t = new Event(name, from, to);
        } catch (DateTimeParseException e) {
            throw new LouieException(String.format(DATE_PARSE_EXC_MSG, from, to));
        }

        louie.getUi().print(String.format(SUCCESS_MSG, t.describe()));
        louie.getTasks().add(t);
        louie.getStorage().writeTasks(louie.getTasks());
    }
}

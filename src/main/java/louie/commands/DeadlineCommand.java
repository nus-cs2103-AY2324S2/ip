package louie.commands;

import louie.Louie;
import louie.LouieException;
import louie.LouieOptionParsingException;
import louie.Parser;
import louie.tasks.Deadline;
import louie.tasks.Task;

import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private static final String NO_NAME_MSG = "you didn't specify specify a name for your deadline";
    private static final String NO_BY_MSG = "you failed to specify an end date using '/by'";
    private static final String DATE_PARSE_EXCEPTION_MSG = "Couldn't parse the end date %s";
    private static final String ADDED_DEADLINE_MSG = "Ok, I've added a new deadline...\n  %s";

    @Override
    public void run(Parser parser, Louie louie) throws LouieException {
        String by;
        String name;
        Task t;


        name = parser.nextUntilOption();

        parser.assertNext("/by");
        by = parser.nextUntilOption();

        parser.assertEnd();

        if (name.isEmpty()) {
            throw new LouieOptionParsingException(NO_NAME_MSG);
        }

        if (by.isEmpty()) {
            throw new LouieOptionParsingException(NO_BY_MSG);
        }

        try {
            t = new Deadline(name, by);
        } catch (DateTimeParseException e) {
            throw new LouieException(String.format(DATE_PARSE_EXCEPTION_MSG, by));
        }

        louie.getUi().print(String.format(ADDED_DEADLINE_MSG, t.describe()));
        louie.getTasks().add(t);
        louie.getStorage().writeTasks(louie.getTasks());
    }
}

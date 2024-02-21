package duke.commands;

import duke.Duke;
import duke.DukeOptionParsingException;
import duke.Parser;

public class ListCommand extends Command {

    public static final String SUCCESS_MSG = "Here's what you've done today...\n%s";

    @Override
    public void run(Parser parser, Duke duke) throws DukeOptionParsingException {
        parser.assertEnd();
        duke.getUi().print(String.format(SUCCESS_MSG, duke.getTasks().toDisplayString()));
    }
}

package duke.commands;

import duke.Duke;
import duke.DukeOptionParsingException;
import duke.Parser;

public class ListCommand extends Command {
    @Override
    public void run(Parser parser, Duke duke) throws DukeOptionParsingException {
        parser.assertEnd();
        duke.print("Here's what you've done today...\n" + duke.getTasks().toDisplayString());
    }
}

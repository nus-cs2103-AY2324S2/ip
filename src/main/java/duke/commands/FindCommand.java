package duke.commands;

import duke.Duke;
import duke.DukeException;
import duke.Parser;

public class FindCommand extends Command {

    public static final String SUCCESS_MSG = "I found the following tasks with names that match '%s':\n%s";

    @Override
    public void run(Parser parser, Duke duke) throws DukeException {

        String toFind = parser.rest();
        String response = String.format
                (SUCCESS_MSG, toFind, duke.getTasks().filterSubString(toFind));
        duke.getUi().print(response);
    }
}

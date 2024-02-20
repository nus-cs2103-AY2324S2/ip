package duke.commands;

import duke.Duke;
import duke.DukeException;
import duke.Parser;

public class FindCommand extends Command {
    @Override
    public void run(Parser parser, Duke duke) throws DukeException {

        String toFind = parser.rest();
        String response = String.format("I found the following tasks with names that match '%s':\n%s",
                toFind, duke.getTasks().filterSubString(toFind));
        duke.getUi().print(response);
    }
}

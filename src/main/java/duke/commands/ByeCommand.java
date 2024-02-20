package duke.commands;

import duke.Duke;
import duke.DukeException;
import duke.Parser;
import javafx.application.Platform;

public class ByeCommand extends Command {
    @Override
    public void run(Parser parser, Duke duke) throws DukeException {
        parser.assertEnd();
        duke.getUi().print("Ok, going to sleep...");
        Platform.exit();
    }
}

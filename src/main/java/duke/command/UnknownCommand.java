package duke.command;

import duke.exception.*;
import duke.task.*;
import duke.ui.*;
import java.util.List;

public class UnknownCommand extends Command<List<Task>> {
    public UnknownCommand() {
        super("", List.of());
    }

    public List<Task> execute(List<Task> tasks) {
        return null;
    }
}

package duke.command;

import duke.exception.*;
import duke.task.*;
import duke.ui.*;
import java.util.List;

public class ByeCommand extends Command<List<Task>> {
    protected String command;
    protected List<String> arguments;

    public ByeCommand() {
        super("bye", List.of());
    }

    public List<Task> execute(List<Task> tasks) {
        return tasks;
    }
}

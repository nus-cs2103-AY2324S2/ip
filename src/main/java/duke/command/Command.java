package duke.command;

import java.util.List;
import duke.exception.*;
import duke.task.*;
import duke.ui.*;


public abstract class Command<T> {
    protected String command;
    protected List<String> arguments;

    public Command(String command, List<String> arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public abstract T execute(List<Task> tasks);

    public String getCommand() {
        return this.command;
    }
}

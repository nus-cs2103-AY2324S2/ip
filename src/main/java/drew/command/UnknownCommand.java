package drew.command;

import drew.storage.Storage;
import drew.storage.TaskList;
import drew.task.Deadline;
import drew.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class UnknownCommand extends Command {
    public static final Command UNKNOWN = new UnknownCommand("");

    private UnknownCommand(String input) {
        super(input);
    }

    public static Command getUnkownCommand() {
        return UNKNOWN;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws IllegalArgumentException {
        return "Unknown command";
    }
}

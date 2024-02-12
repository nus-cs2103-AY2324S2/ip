package drew.command;

import drew.storage.TaskList;
import drew.task.Deadline;
import drew.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class ByeCommand extends Command {
    public static final Command BYE = new ByeCommand("");

    private ByeCommand(String input) {
        super(input);
    }

    public static Command getByeCommand() {
        return BYE;
    }
    @Override
    public String execute(TaskList tasks) throws IllegalArgumentException {
        return "Bye";
    }

    public static boolean isByeCommand(int inputLength, String input) {
        return inputLength == 3 && input.substring(0, 3).equalsIgnoreCase("bye");
    }

}

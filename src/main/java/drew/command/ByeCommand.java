package drew.command;

import drew.storage.Storage;
import drew.storage.TaskList;

public class ByeCommand extends Command {
    public static final Command BYE = new ByeCommand("");

    private ByeCommand(String input) {
        super(input);
    }

    public static Command getByeCommand() {
        return BYE;
    }
    @Override
    public String execute(TaskList tasks, Storage storage) throws IllegalArgumentException {
        storage.save(tasks);
        return "Bye. Hope to see you again soon!";
    }

    public static boolean isByeCommand(int inputLength, String input) {
        return inputLength == 3 && input.substring(0, 3).equalsIgnoreCase("bye");
    }

}

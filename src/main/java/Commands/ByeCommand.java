package Commands;

import java.io.IOException;

public class ByeCommand extends Command{
    public static final String COMMAND_WORD = "bye";

    @Override
    public String execute() {
        return "Bye! Hope to see you again.";
    }

    public static boolean isExit(Command command) {
        return command instanceof ByeCommand; // instanceof returns false if it is null
    }
}

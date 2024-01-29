package Commands;

import java.io.IOException;

public class ClearCommand extends Command{
    public static final String COMMAND_WORD = "clear";

    @Override
    public String execute() throws IOException {
        this.taskList.clear();
        return "Cleared your cache!";
    }
}

package ellie.command;

import ellie.TaskList;

/**
 * Represents a command to display a list of supported commands.
 */
public class HelpCommand extends Command {

    private static final String NEW_LINE = "\n";

    /**
     * Constructs a HelpCommand object.
     */
    public HelpCommand() {
        super.isExit = false;
    }

    /**
     * {@inheritDoc}
     * Displays a list of supported commands.
     *
     * @param tasklist The TaskList to be operated on by the command.
     */
    public String runAndReturnResponse(TaskList tasklist) {
        String response = NEW_LINE;
        response += "Here's a list of supported commands so far: " + NEW_LINE
                + " <help>" + NEW_LINE
                + " <list>" + NEW_LINE
                + " <mark/unmark [int]>" + NEW_LINE
                + " <todo [task]>" + NEW_LINE
                + " <deadline [task] /by [date]>" + NEW_LINE
                + " <event [task] /from [date] /to [date]>" + NEW_LINE
                + " <bye/exit>" + NEW_LINE;
        return response;
    }
}


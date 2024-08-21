package lemona.command;

import lemona.oop.TaskList;

/**
 * An UnmarkCommand to handle unmark command.
 */
public class UnmarkCommand extends Command{
    private String[] input;

    /**
     * Constructs UnmarkCommand object to handle unmark command.
     */
    public UnmarkCommand(String[] input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks) {
        String str = tasks.unmark(Integer.parseInt(input[1]));
        return str;
    }
}

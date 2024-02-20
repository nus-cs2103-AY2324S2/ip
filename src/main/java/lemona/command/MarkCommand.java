package lemona.command;

import lemona.oop.TaskList;

/**
 * A MarkCommand to handle mark command.
 */
public class MarkCommand extends Command{
    private String[] input;

    /**
     * Constructs MarkCommand object to handle mark command.
     */
    public MarkCommand(String[] input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks) {
        String str = tasks.mark(Integer.parseInt(input[1]));
        return str;
    }
}

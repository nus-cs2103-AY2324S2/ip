package lemona.command;

import lemona.oop.TaskList;

/**
 * A DeleteCommand to handle Delete command.
 */
public class DeleteCommand extends Command{
    private String[] input;

    /**
     * Constructs DeleteCommand object to handle Delete command.
     */
    public DeleteCommand(String[] input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks) {
        String str = tasks.delete(Integer.parseInt(input[1]));
        return str;
    }
}

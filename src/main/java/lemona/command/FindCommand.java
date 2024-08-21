package lemona.command;

import lemona.oop.TaskList;

/**
 * A FindCommand to handle find command.
 */
public class FindCommand extends Command{
    private String[] input;

    /**
     * Constructs FindCommand object to handle find command.
     */
    public FindCommand(String[] input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks) {
        String str = tasks.find(input[1]);
        return str;
    }
}

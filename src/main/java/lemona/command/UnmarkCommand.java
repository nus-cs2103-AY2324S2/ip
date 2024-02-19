package lemona.command;

import lemona.oop.TaskList;

public class UnmarkCommand extends Command{
    private String[] input;
    public UnmarkCommand(String[] input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks) {
        String str = tasks.unmark(Integer.parseInt(input[1]));
        return str;
    }
}

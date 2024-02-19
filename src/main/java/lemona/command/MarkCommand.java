package lemona.command;

import lemona.oop.TaskList;

public class MarkCommand extends Command{
    private String[] input;
    public MarkCommand(String[] input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks) {
        String str = tasks.mark(Integer.parseInt(input[1]));
        return str;
    }
}

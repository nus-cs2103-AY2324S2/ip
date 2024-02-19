package lemona.command;

import lemona.oop.TaskList;

public class DeleteCommand extends Command{
    private String[] input;
    public DeleteCommand(String[] input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks) {
        String str = tasks.delete(Integer.parseInt(input[1]));
        return str;
    }
}

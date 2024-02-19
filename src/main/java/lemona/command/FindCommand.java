package lemona.command;

import lemona.oop.TaskList;

public class FindCommand extends Command{
    private String[] input;
    public FindCommand(String[] input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks) {
        String str = tasks.find(input[1]);
        return str;
    }
}

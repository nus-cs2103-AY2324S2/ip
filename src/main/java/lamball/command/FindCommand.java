package lamball.command;

import lamball.TaskList;

public class FindCommand extends Command {
    private String args;

    public FindCommand(String args, TaskList tasks) {
        super(tasks);
        this.args = args;
    }

    @Override
    public boolean run() {
        taskList.find(args);
        return true;
    }

}

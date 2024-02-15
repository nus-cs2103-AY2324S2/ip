package commands;

import tasks.TaskList;
import tasks.taskType.Task;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String[] fullCommand, TaskList tasks) {
        super(fullCommand, tasks);
    }

    @Override
    public String execute() {
        try {
            Task currTask = this.tasks.getTask(Integer.parseInt(this.fullCommand[1]) - 1);
            currTask.setUndone();
            return "Nice! I've marked this task as not done yet: \n"
                    + "    " + currTask;
        } catch (IndexOutOfBoundsException err) {
            String single = this.tasks.size() <= 1 ? "task" : "tasks";
            int num = this.tasks.size();
            return "You only have " + num + " "
                    + single
                    + " currently. Type \"list\" to view all your current "
                    + single;
        } catch (NumberFormatException err) {
            return "Please enter a valid input";
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UnmarkCommand;
    }
}
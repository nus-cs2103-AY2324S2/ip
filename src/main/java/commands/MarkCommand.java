package commands;

import tasks.TaskList;
import tasks.taskType.Task;

public class MarkCommand extends Command {

    public MarkCommand(String[] fullCommand, TaskList tasks) {
        super(fullCommand, tasks);
    }

    public void markAll(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.getTask(i).setDone();
        }
    }

    @Override
    public String execute() {
        try {
            Task currTask = this.tasks.getTask(Integer.parseInt(this.fullCommand[1]) - 1);
            currTask.setDone();
            return "Nice! I've marked this task as done: \n"
                    + "    " + currTask;
        } catch (IndexOutOfBoundsException err) {
            String single = this.tasks.size() <= 1 ? "task" : "tasks";
            int num = this.tasks.size();
            return "You only have " + num + " "
                    + single
                    + " currently. Type \"list\" to view all your current "
                    + single;
        } catch (NumberFormatException err) {
            if (this.fullCommand[1].equalsIgnoreCase("all")) {
                markAll(this.tasks);
            }
            return "Set all current tasks as completed";
        }
    }
}

package commands;

import tasks.TaskList;

public class ListCommand extends Command {

    public TaskList tasks;

    public ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }
    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list: \n");
        for (int i = 1; i <= this.tasks.size(); i++) {
            sb.append(i).append(". ");
            sb.append(this.tasks.getTask(i - 1)).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
}

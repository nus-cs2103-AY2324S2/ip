package commands;

import tasks.TaskList;
import tasks.taskType.Task;

public class Command {
    String[] fullCommand;
    public TaskList tasks;

    public Command(String[] fullCommand, TaskList tasks) {
        this.fullCommand = fullCommand;
        this.tasks = tasks;
    }

    public Command() {}

    public String execute() {
        return "hello I am command's main class";
    };

    public String displayTask(Task task, TaskList tasks) {
        String singular = tasks.size() == 1 ? "task" : "tasks";
        int num = tasks.size();
        return "Got it. I've added this task: \n"
                + "    " + task
                + "\nNow you have " + num + " " + singular + " in the list.";
    }
}

package task;

public class TaskList {
    public Task[] tasks;

    public TaskList(Task[] tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new Task[100];
    }

    public Task[] getTaskList() {
        return tasks.clone();
    }

    public void updateTaskList(TaskList tasks) {
        this.tasks = tasks.getTaskList();
    }
}

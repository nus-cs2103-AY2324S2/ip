package task;

public class TaskList {
    public Task[] taskList;

    public TaskList(Task[] tasks) {
        this.taskList = tasks;
    }

    public TaskList() {
        this.taskList = new Task[100];
    }

    public Task[] getTaskList() {
        return taskList.clone();
    }

    public void updateTaskList(TaskList tasks) {
        this.taskList = tasks.getTaskList();
    }
}

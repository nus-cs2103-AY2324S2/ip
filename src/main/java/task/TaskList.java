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

    public Task[] find(String match) {
        Task[] output = new Task[100];
        int current = 0;
        for (Task task : taskList) {
            if (task == null) {
                break;
            }
            if (task.checkMatch(match)){
                output[current] = task;
                current++;
            }
        }

        return output;
    }
}

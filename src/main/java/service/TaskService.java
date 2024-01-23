package service;

import model.Task;

public class TaskService {
    private Task[] taskList = new Task[100]; // According to specifications, there cannot be more than 100 tasks
    private int taskListPointer = 0;

    public void addTask(String taskName) {
        Task newTask = new Task(taskName);
        this.taskList[this.taskListPointer++] = newTask;

        System.out.println(String.format("Added: %s", taskName));
    }

    public void markTaskCompleted(int taskId) {
        this.taskList[taskId].setTaskCompleted();

        // TODO: Fix Printing Format
        System.out.println("Ok! I've marked this task as completed!");
    }

    public void markTaskUncompleted(int taskId) {
        this.taskList[taskId].setTaskUncompleted();

        // TODO: Fix Printing Format
        System.out.println("Hmmm, were you teasing me? I've marked the task as uncompleted");
    }

    public void getAllTasks() {
        for (int i = 0; i < this.taskList.length; i++) {
            Task curTask = this.taskList[i];

            if (curTask == null) {
                break;
            }

            System.out.println(String.format("%d. %s", i + 1, this.taskList[i]));
        }
    }
}

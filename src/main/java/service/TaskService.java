package service;

import model.Task;

public class TaskService {
    private Task[] taskList = new Task[100]; // According to specifications, there cannot be more than 100 tasks
    private int taskListPointer = 0;

    public String addTask(String taskName) {
        Task newTask = new Task(taskName);
        this.taskList[this.taskListPointer++] = newTask;

        return String.format("Added: %s", taskName);
    }

    public String markTaskCompleted(int taskId) {
        this.taskList[taskId].markTaskCompleted();

        return "Ok! I've marked Task " + (taskId + 1) + " as completed!";
    }

    public String markTaskUncompleted(int taskId) {
        // TODO: Exception handling for if task does not exist
        this.taskList[taskId].markTaskNotCompleted();

        return "Hmmm, were you teasing me?\n" +
                "Well, I've marked Task " + (taskId + 1) +  " as uncompleted,\n" +
                "But don't do this again, you hear me?";
    }

    public String getAllTasks() {
        StringBuilder returnVal = new StringBuilder();

        for (int i = 0; i < this.taskListPointer; i++) {
            returnVal.append(String.format("%d. %s", i + 1, this.taskList[i])).append("\n");
        }

        return returnVal.toString();
    }
}

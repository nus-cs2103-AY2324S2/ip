package service;

import model.Deadline;
import model.Event;
import model.Task;
import model.Todo;

public class TaskService {
    private Task[] taskList = new Task[100]; // According to specifications, there cannot be more than 100 tasks
    private int taskListPointer = 0;

    public String addTodo(String taskName) {
        Todo newTodo = new Todo(taskName);
        this.taskList[this.taskListPointer++] = newTodo;

        return String.format("Added new Todo: %s", taskName);
    }

    public String addDeadline(String taskName, String byDateTime) {
        Deadline newDeadline = new Deadline(taskName, byDateTime);
        this.taskList[this.taskListPointer++] = newDeadline;

        return String.format("Added new Deadline: %s with Due Date: %s", taskName, byDateTime);
    }

    public String addEvent(String taskName, String fromDateTime, String toDateTime) {
        Event newEvent = new Event(taskName, fromDateTime, toDateTime);
        this.taskList[this.taskListPointer++] = newEvent;

        return String.format("Added new Event: %s occurring from %s to %s", taskName, fromDateTime, toDateTime);
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
            returnVal.append(String.format("%d. %s", i + 1, this.taskList[i]));

            // Only add new line if its not the last task
            if (i < this.taskListPointer - 1) {
                returnVal.append("\n");
            }
        }

        return returnVal.toString();
    }
}

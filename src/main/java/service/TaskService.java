package service;

import model.Deadline;
import model.Event;
import model.Task;
import model.Todo;

import java.util.ArrayList;

public class TaskService {
    private ArrayList<Task> tasks = new ArrayList<>();

    public String addTodo(String taskName) {
        Todo newTodo = new Todo(taskName);
        this.tasks.add(newTodo);

        return String.format("Added new Todo: %s", taskName);
    }

    public String addDeadline(String taskName, String byDateTime) {
        Deadline newDeadline = new Deadline(taskName, byDateTime);
        this.tasks.add(newDeadline);

        return String.format("Added new Deadline: %s with Due Date: %s", taskName, byDateTime);
    }

    public String addEvent(String taskName, String fromDateTime, String toDateTime) {
        Event newEvent = new Event(taskName, fromDateTime, toDateTime);
        this.tasks.add(newEvent);

        return String.format("Added new Event: %s occurring from %s to %s", taskName, fromDateTime, toDateTime);
    }

    public String deleteTask(int taskId) {
        this.tasks.remove(taskId);

        return String.format("Are you giving up? Or is this task no longer needed?\nHmmm.. I've deleted Task %s for you for now.\nBut, I'll be watching you.", taskId);
    }

    public String markTaskCompleted(int taskId) {
//        this.tasks[taskId].markTaskCompleted();
        this.tasks.get(taskId).markTaskCompleted();

        return "Ok! I've marked Task " + (taskId + 1) + " as completed!";
    }

    public String markTaskUncompleted(int taskId) {
        // TODO: Exception handling for if task does not exist
        this.tasks.get(taskId).markTaskNotCompleted();
//        this.tasks[taskId].markTaskNotCompleted();

        return "Hmmm, were you teasing me?\n" +
                "Well, I've marked Task " + (taskId + 1) +  " as uncompleted,\n" +
                "But don't do this again, you hear me?";
    }

    public String getAllTasks() {
        StringBuilder returnVal = new StringBuilder();

        for (int i = 0; i < this.tasks.size(); i++) {
            returnVal.append(String.format("%d. %s", i + 1, this.tasks.get(i)));

            // Only add new line if its not the last task
            if (i < this.tasks.size() - 1) {
                returnVal.append("\n");
            }
        }

        return returnVal.toString();
    }
}

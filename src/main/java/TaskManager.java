import Task.Task;
import Task.ToDoTask;
import Task.DeadlineTask;
import Task.EventTask;

import java.util.ArrayList;

public class TaskManager {

    ArrayList<Task> userTasks = new ArrayList<>();

    public void addUserTask(String taskName) {
        Task newTask = new Task(taskName);
        this.userTasks.add(newTask);
    }

    public void addToDoTask(String taskName) {
        ToDoTask newTask = new ToDoTask(taskName);
        this.userTasks.add(newTask);
    }

    public void addDeadlineTask(String taskName, String deadline) {
        DeadlineTask newTask = new DeadlineTask(taskName, deadline);
        this.userTasks.add(newTask);
    }

    public void addEventTask(String taskName, String startDateTime, String endDateTime) {
        EventTask newTask = new EventTask(taskName, startDateTime, endDateTime);
        this.userTasks.add(newTask);
    }

    public Task getTask(int index){
        return this.userTasks.get(index);
    }

    public int getTotalTaskCount(){
        return this.userTasks.size();
    }
    public boolean markTaskCompleted(int index) {
        try {
            this.userTasks.get(index).markCompleted();
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No task with index " + (index + 1) + ". Enter list to view tasks.");
            return false;
        }
    }

    public boolean markTaskIncomplete(int index) {
        try {
            this.userTasks.get(index).markIncomplete();
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No task with index " + (index + 1) + ". Enter list to view tasks.");
            return false;
        }
    }

    public ArrayList<Task> getUserTasks(){
        return userTasks;
    }
}

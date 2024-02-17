package reacher;

import java.util.ArrayList;
import reacher.task.Task;
public class TaskList {
    ArrayList<Task> tasks = new ArrayList<>();
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public ArrayList<Task> getTasks(){
        return tasks;
    }

    public int noOfTasks(){
        return tasks.size();
    }
    public void delete(int index){
        tasks.remove(index);
    }
    public void addTask(Task task){
        tasks.add(task);
    }
    public Task getTask(int index){
        return tasks.get(index);
    }
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isMatching(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}

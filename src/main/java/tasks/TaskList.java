import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int size() {
        return this.tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTaskAtIndex(int index) {
        this.tasks.remove(index);
    }

    public TaskList getTasksToday() {
        DateTime today = new DateTime(LocalDate.now());
        return getTasksOnDate(today);
    }

    public TaskList getTasksOnDate(String date) {
        DateTime dt = new DateTime(date);
        return getTasksOnDate(dt);
    }

    public TaskList getTasksOnDate(DateTime date) {
        ArrayList<Task> tasksToDisplayList = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.hasDate) {
                if(task.isWithinDate(date)) {
                    tasksToDisplayList.add(task);
                }
            }
        }
        return new TaskList(tasksToDisplayList);
    }





}

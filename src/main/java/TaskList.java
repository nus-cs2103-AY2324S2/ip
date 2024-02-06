import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTaskAtIndex(int index) {
        this.tasks.remove(index);
    }

    public ArrayList<Task> getTasksToday() {
        DateTime today = new DateTime(LocalDate.now());
        return getTasksOnDate(today);
    }

    public ArrayList<Task> getTasksOnDate(String date) {
        DateTime dt = new DateTime(date);
        return getTasksOnDate(dt);
    }

    public ArrayList<Task> getTasksOnDate(DateTime date) {
        ArrayList<Task> tasksToDisplayList = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.hasDate) {
                if(task.isWithinDate(date)) {
                    tasksToDisplayList.add(task);
                }
            }
        }
        return tasksToDisplayList;
    }





}

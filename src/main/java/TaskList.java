import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private ArrayList<Task> taskList;
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTasks() {
        return this.taskList;
    }
    public void yapTasks() {
        if (taskList.size() == 0) {
            System.out.println("Nothin' to yap...");
        }
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i+1)+". "+taskList.get(i));
        }
    }

    public void markTaskAsDone(int index) {
        Task task = taskList.get(index - 1);
        task.markDone(false);
    }

    public void unmarkTaskAsDone(int index) {
        Task task = taskList.get(index - 1);
        task.unmarkDone();
    }

    public void addTasktoTaskList(Task task) {
        if (task == null) {
            return;
        }
        taskList.add(task);
    }

    public Task removeTaskfromTaskList(int index) {
        Task task = taskList.remove(index - 1);
        return task;
    }

}

import java.util.ArrayList;
public class TaskList {
    public ArrayList<Task> tasksList;

    public TaskList() {
        this.tasksList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }

    public ArrayList<Task> getList() {
        return this.tasksList;
    }
    public void addToTaskList(Task task) {
        this.tasksList.add(task);
    }

    public void deleteFromTaskList(int taskNum) {
        this.tasksList.remove(taskNum - 1);
    }

    public void list() {
        for (Task task: this.tasksList) {
            System.out.println(task);
        }
    }

    public void mark(int num) {
        Task currTask = this.tasksList.get(num - 1);
        currTask.check();
        this.tasksList.set(num - 1, currTask);
    }

    public void unMark(int num) {
        Task currTask = this.tasksList.get(num - 1);
        currTask.uncheck();
        this.tasksList.set(num - 1, currTask);
    }

    public Task get(int num) {
        return this.tasksList.get(num - 1);
    }
}
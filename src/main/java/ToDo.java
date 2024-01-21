import java.util.ArrayList;

public class ToDo {

    protected ArrayList<Task> tasks;

    protected static int tasksCount = 0;

    public ToDo() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        tasksCount ++;
    }

    public void listTask() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasksCount; i++) {
            System.out.println((i + 1)+ ". " + tasks.get(i).getDescription());
        }
    }

    public Task getTask(int num) {
        return tasks.get(num);
    }

    public void markTask(int num, boolean status) {
        getTask(num).mark(status);
    }

}

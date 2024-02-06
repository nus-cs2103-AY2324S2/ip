import java.util.ArrayList;
public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public static int getListSize() {
        return tasks.size();
    }
    private static void listTasks() {
        // display task
        System.out.println("Here are the missions you added:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            int displayedIndex = i + 1;
            System.out.println(displayedIndex + ". " + task.getDisplayedString());
            //System.out.printf("%d. %s %s\n", displayedIndex, task.getStatusIcon(), task.getDescription());
        }
        System.out.println("Total: " + tasks.size() + " mission(s)");
    }
    public void addTask(Task task) {
        tasks.add(task);
    }
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }
}

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;
    protected int taskCount;

    public TaskList() {
        this.taskList = new ArrayList<>();
        this.taskCount = 0;
    }

    public void addTask(Task task) {
        taskList.add(task);
        taskCount++;
    }

    public void deleteTask(Task task) {
        taskList.remove(task);
        taskCount--;
    }

    public Task getTaskByNumber(int taskNum) {
        return taskList.get(taskNum - 1);
    }

    public int getTaskCount() {
        return this.taskCount;
    }

    public void printList() {;
        if (taskCount == 0) {
            System.out.println("There are currently no task added.");
        } else {
            System.out.println("Here are the task(s) you have:");
            for (int i = 0; i < taskList.size(); i++) {
                int taskNum = i + 1;
                String taskString = "→ " + taskNum + ". " + taskList.get(i).toString();
                System.out.println(taskString);
            }
        }
    }
}

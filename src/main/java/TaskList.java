import java.util.ArrayList;
import java.util.List;
//test
public class TaskList {
    private final List<Task> taskArray;
    private int taskCount;

    public TaskList() {
        this.taskArray = new ArrayList<>();
    }

//    public void add(String taskDescription) {
//        taskArray.add(new Task(taskDescription));
//    }

    public int getTaskCount() {
        return taskCount;
    }

    public Task deleteTask(int taskNumber) throws JayneException {
        if (taskNumber < 1 || taskNumber > taskArray.size()) {
            throw new JayneException("Task number " + taskNumber + " does not exist.");
        }
        this.taskCount = taskCount - 1;
        return taskArray.remove(taskNumber - 1);
    }

    public void addTask(Task task) {
        taskArray.add(task);
        this.taskCount = taskCount + 1;
    }

    public Task getTask(int index) {
        if (index >= 0 && index <= taskArray.size()) {
            return taskArray.get(index - 1);
        }
        System.out.println("Index invalid");
        return null;
    }

    public void markTaskAsDone(int taskNumber) {
        if (taskNumber >= 1 && taskNumber <= taskArray.size()) {
            taskArray.get(taskNumber - 1).markAsDone();
        }
    }

    public void markTaskAsNotDone(int taskNumber) {
        if (taskNumber >= 1 && taskNumber <= taskArray.size()) {
            taskArray.get(taskNumber - 1).markAsNotDone();
        }
    }

    public void display() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskArray.size(); i++) {
            System.out.println((i + 1) + ". " + taskArray.get(i).toString());
        }
    }
}

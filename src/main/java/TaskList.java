import java.util.ArrayList;
import java.util.List;
//test
public class TaskList {
    private final List<Task> taskArray;

    public TaskList() {
        this.taskArray = new ArrayList<>();
    }

    public void add(String taskDescription) {
        taskArray.add(new Task(taskDescription));
    }

    public Task getTask(int indx) {
        if (indx >= 0 && indx <= taskArray.size()) {
            return taskArray.get(indx - 1);
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

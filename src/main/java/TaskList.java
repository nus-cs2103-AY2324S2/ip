import java.time.LocalDateTime;
import java.util.*;

public class TaskList {

    private static List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public static List<Task> getTaskList() {
        return taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public int size() {
        return taskList.size();
    }

    public String showList() {
        int i = 1;
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            sb.append(i++).append(". ").append(task).append("\n");
        }
        return sb.toString();
    }


    public Task getTaskAtIndex(int index) {
        return taskList.get(index);
    }

    public void markTask(int index) {
        taskList.get(index).mark();
    }

    public void unmarkTask(int index) {
        taskList.get(index).unmark();
    }

    // Additional method to add Deadline task
    public void addDeadlineTask(String desc, String start) {
        Task deadlineTask = new Task(desc, false, start);
        taskList.add(deadlineTask);
    }

    // Additional method to add Event task
    public void addEventTask(String desc, String start, String end) {
        Task eventTask = new Task(desc, false, start, end);
        taskList.add(eventTask);
    }
}

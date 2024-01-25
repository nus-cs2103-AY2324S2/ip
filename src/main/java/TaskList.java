import java.util.*;

public class TaskList {

    private static List<Task> taskLists;

    public TaskList() {
        taskLists = new ArrayList<>();
    }

    public static List<Task> getTaskLists() {
        return taskLists;
    }

    public void add(Task task) {
        taskLists.add(task);
    }

    public String showList() {
        int i = 1;
        StringBuilder sb = new StringBuilder();
        for (Task t: taskLists) {
            sb.append( i++ + ". " + t + "\n");
        }
        return sb.toString();
    }

    public Task showTaskAtIndex(int i) {
        return taskLists.get(i);
    }

    public void setMark(int i) {
        taskLists.get(i).mark();
    }

    public void setUnmark(int i) {
        taskLists.get(i).unmark();
    }



}

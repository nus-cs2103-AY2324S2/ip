import java.util.ArrayList;
public class Task {
    private String task, type;
    private boolean isDone;
    public Task(String task, String type, boolean isDone) {
        this.task = task;
        this.type = type;
        this.isDone = isDone;
    }
    public void setDone() {
        this.isDone = true;
    }
    public void setUndone() {
        this.isDone = false;
    }
    public boolean getDone() {
        return this.isDone;
    }
    public String getType() {
        return this.type;
    }
    public String getTask() {
        return this.task;
    }
    public static void printList(ArrayList<Task> list) {
        for (int i = 1; i <= list.size(); i++) {
            Task currtask = list.get(i-1);
            String s = i + ". " + "[" + currtask.type + "][" + (currtask.isDone ? "X" : "") + "]" + " " + currtask.task;
            System.out.println("    " + s);
        }
    }
    public String printTask(String type, boolean isDone, String task) {
        return "[" + type + "][" + (isDone ? "X" : "") + "]" + " " + task;
    }

    public void deleteTask(int index, ArrayList<Task> list) {
        Task task = list.get(index-1);
        list.remove(task);
    }
}

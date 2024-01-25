import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;
    private static ArrayList<Task> taskList = new ArrayList<>();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public static void setDone(int index) {
        System.out.println("Cha Ching! Task Completed.");
        Task curr = taskList.get(index - 1);
        curr.isDone = true;
        System.out.println(curr);
    }

    public static void setNotDone(int index) {
        System.out.println("$$$ Task Incomplete :(");
        Task curr = taskList.get(index - 1);
        curr.isDone = false;
        System.out.println(curr);
    }

    public static void addTask(String description) {
        taskList.add(new Task(description));
        System.out.println("added: " + description);
    }

    public static void listTask() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i+1 + ". " + taskList.get(i));
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

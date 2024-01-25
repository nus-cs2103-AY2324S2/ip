import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public static void setDone(int index) {
        System.out.println("Cha Ching! Task Completed.");
        Task curr = Duke.taskList.get(index - 1);
        curr.isDone = true;
        System.out.println(curr);
    }

    public static void setNotDone(int index) {
        System.out.println("$$$ Task Incomplete :(");
        Task curr = Duke.taskList.get(index - 1);
        curr.isDone = false;
        System.out.println(curr);
    }

    public static void addTask(String description) {
        Task curr = new Task(description);
        Duke.taskList.add(curr);
        System.out.println("added: " + curr);
    }

    public static void removeTask(String description) {
        String[] tokens = description.split(" ");
        if (tokens.length == 1)
            throw new ArrayIndexOutOfBoundsException("Please enter the index of the task to be deleted.");
        int index = Integer.parseInt(tokens[1]);
        Task curr = Duke.taskList.remove(index-1);
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + curr);
        getNumberOfTasks();
    }

    public static void listTask() {
        for (int i = 0; i < Duke.taskList.size(); i++) {
            System.out.println(i+1 + ". " + Duke.taskList.get(i));
        }
    }

    public static void getNumberOfTasks() {
        System.out.println("Now you have " + Duke.taskList.size() + " task(s) in the list.");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

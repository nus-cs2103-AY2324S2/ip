import java.util.ArrayList;

public class Task {
    private static final ArrayList<Task> taskList = new ArrayList<>();
    private final String name;
    private boolean done;

    private Task(String name) {
        this.name = name;
        this.done = false;
    }
    
    public static void add(String name) {
        taskList.add(new Task(name));
    }
    
    public static String taskString() {
        // here's your """""effectively final""""" value bro
        var numBox = new Object() {
            int num = 1;
        };
        return taskList.stream()
                .reduce(
                        new StringBuilder(),
                        (curr, acc) -> {
                            curr.append(numBox.num).append(".").append(acc.describe());
                            if (numBox.num < taskList.size()) {
                                curr.append("\n");
                            }
                            numBox.num++;
                            return curr;
                        }, 
                        StringBuilder::append)
                .toString();
    }
    
    public static void mark(int i) {
        Task.taskList.get(i).done = true;
    }
    public static void unmark(int i) {
        Task.taskList.get(i).done = false;
    }

    public String describe() {
        return '[' + (this.done ? 'X': ' ') + ']' + this.name;
    }    
}

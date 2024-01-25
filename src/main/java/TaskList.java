import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            str.append(String.format("%d. %s", i + 1, tasks.get(i).getName()));
            if (i != tasks.size() - 1) {
                str.append("\n");
            }
        }

        return str.toString();
    }
}

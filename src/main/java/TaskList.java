import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task getTask(int idx) {
        return this.tasks.get(idx);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            String title = String.format("%d. %s", i+1, this.tasks.get(i).getTitle());
            // do not add new line when for last task in list
            if (i == this.tasks.size() - 1) {
                stringBuilder.append(title); 
            } else {
                stringBuilder.append(title + "\n");
            }
        }
        return stringBuilder.toString();
    }
}

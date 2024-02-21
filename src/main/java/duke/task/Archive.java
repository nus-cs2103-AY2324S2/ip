package duke.task;

import java.util.ArrayList;

public class Archive {
    private ArrayList<Task> archivedTasks;

    public Archive() {
        this.archivedTasks = new ArrayList<>();
    }

    public Archive(ArrayList<Task> archivedTasks) {
        this.archivedTasks = archivedTasks;
    }

    public void addArchivedTask(Task task) {
        archivedTasks.add(task);
    }

    public ArrayList<Task> getArchivedTasks() {
        return archivedTasks;
    }
}

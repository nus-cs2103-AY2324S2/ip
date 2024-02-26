package duke;

import java.util.ArrayList;

/**
 * A class representing a list of Tasks.
 */
public class TaskList {
    private ArrayList<Task> list;


    public TaskList() {
        this.list = new ArrayList<>();
    }

    public Task get(int i) {
        return this.list.get(i);
    }

    public void remove(int i) {
        this.list.remove(i);
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public int size() {
        return this.list.size();
    }
    public void clear() {
        this.list.clear();
    }

    @Override
    public String toString() {
        int n = this.list.size();
        String str = "";
        for (int i = 0; i < n; i++) {
            str += String.format("%d. %s\n", i + 1, this.list.get(i));
        }
        str += String.format("You have %d tasks.", n);
        return str;
    }

    /**
     * Used to get the representation of a task to be stored in data.
     * @return String representing a task to be stored in data.
     */
    public String getInputs() {
        int n = this.list.size();
        String str = "";
        for (int i = 0; i < n; i++) {
            str += this.list.get(i).getInput();
            str += "\n";
        }
        return str;
    }
}

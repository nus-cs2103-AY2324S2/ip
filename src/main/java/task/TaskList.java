package task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void remove(int i) {
        this.tasks.remove(i);
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public int size() {
        return this.tasks.size();
    }

    public String getStatus() {
        if (this.tasks.size() == 0) {
            return "Now you have no tasks on the list :(((";
        } else if (this.tasks.size() == 1) {
            return "You have 1 task on the list!";
        } else {
            return String.format("You have %d tasks on the list!%n", this.tasks.size());
        }
    }

    public String fileRepresentation() {
        ArrayList<String> a = new ArrayList<>();
        for (Task t : this.tasks) {
            a.add(t.fileRepresentation());
        }
        return String.join("\n", a);
    }

    @Override
    public String toString() {
        ArrayList<String> a = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            a.add(String.format("%d. %s",
                    i + 1,
                    this.tasks.get(i)));
        }
        return String.join("\n", a);
    }
}

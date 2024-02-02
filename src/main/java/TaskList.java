import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    public void addTask(Task t) {
        this.tasks.add(t);
        Storage.updateSaved(tasks);
    }

    public void deleteTask(int i) {
        this.tasks.remove(i);
        Storage.updateSaved(tasks);
    }

    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void markTask(int i) {
        this.tasks.get(i).mark();
        Storage.updateSaved(tasks);
    }

    public void unmarkTask(int i) {
        this.tasks.get(i).unmark();
        Storage.updateSaved(tasks);
    }

    public void printTasks() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            System.out.println("\t" + num + ". " + tasks.get(i));
        }
    }

    public void printListCounter() {
        System.out.println("\tNow you have " + this.tasks.size() + " tasks in the list.");
    }
}

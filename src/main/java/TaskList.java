import java.util.ArrayList;

public class TaskList extends Storage<Task> {
    public TaskList() {
        super();
    }
    public void checkTask(int idx) {
        if (idx < 0 || idx >= this.storage.size()) {
            System.out.println("Invalid task index!");
        } else {
            this.storage.get(idx).check();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this.storage.get(idx).toString());
        }
    }
    public void uncheckTask(int idx) {
        if (idx < 0 || idx >= this.storage.size()) {
            System.out.println("Invalid task index!");
        } else {
            this.storage.get(idx).uncheck();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(this.storage.get(idx).toString());
        }
    }
    @Override
    public void storeItem(Task task) {
        this.storage.add(task);
        final String output = String.format("added: %s", task.getDescription());
        System.out.println(output);
    }
    @Override
    public void listItem() {
        if (this.storage.isEmpty()) {
            System.out.println("YAY! You have no tasks ongoing ^_^");
        }
        System.out.println("Here are the tasks in your list:\n");
        super.listItem();
    }
}

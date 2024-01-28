import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    public void addTask(Task task) {
        add(task);
        System.out.println("_________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + task.toString());
        String numOfTask;
        if (this.size() < 2) {
            numOfTask = this.size() + " task";
        } else {
            numOfTask = this.size() + " tasks";
        }
        System.out.println("Now you have " + numOfTask + " in the list.");
        System.out.println("_________________________________________");
    }

    public Task getTask(int index) {
        return get(index);
    }
    public void displayTasks() {
        if (isEmpty()) {
            System.out.println("_________________________________________");
            System.out.println("No pending tasks, congrats!");
            System.out.println("_________________________________________");
        } else {
            System.out.println("_________________________________________");
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < size(); i++) {
                System.out.println((i + 1) + ". " + getTask(i));
            }
            System.out.println("_________________________________________");
        }
    }

}

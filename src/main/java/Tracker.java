import java.util.ArrayList;

public class Tracker {

    private final ArrayList<Task> taskList;

    public Tracker(){
        taskList = new ArrayList<>();
    }

    public void addTask(Task element) {
        taskList.add(element);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + element.listTaskString());
        System.out.println("Now you have " + this.taskQuantity() + " tasks in the list.\n");
    }

    public void listTasks() {
        if (taskList.size() == 0) {
            System.out.println("No items in list!\n");
            return;
        }

        int index = 0;
        System.out.println("Here are your tasks!");
        for (Task element: taskList) {
            index++;
            System.out.println("    " + index + "." + element.listTaskString());
        }
        System.out.print("\n");
    }

    public int taskQuantity() {
        return taskList.size();
    }

    public void markTaskIndex(int index) {

        if (index > taskList.size()) {
            System.out.println("Sorry! There doesn't seem to be enough tasks for there to be a task " + index + "!\n");
            return;
        }

        Task task = taskList.get(index - 1);
        task.markTask();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.listTaskString() +"\n");

    }

    public void unmarkTaskIndex(int index) {

        if (index > taskList.size()) {
            System.out.println("Sorry! There doesn't seem to be enough tasks for there to be a task " + index + "!\n");
            return;
        }

        Task task = taskList.get(index - 1);
        task.unmarkTask();

        System.out.println("Nice! I've marked this task as not done yet:");
        System.out.println("  " + task.listTaskString() + "\n");

    }


}

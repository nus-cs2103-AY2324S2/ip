package ellie;
import ellie.task.Task;
import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> taskArrayList;
    private final Storage storage;

    public TaskList(Storage storage) {
        taskArrayList = new ArrayList<>();
        this.storage = storage;
        storage.load(taskArrayList);


    }

    public void addTask(Task element) {
        taskArrayList.add(element);
        saveTasks();

        System.out.println("Got it. I've added this task:");
        System.out.println("    " + element.listTaskString());
        System.out.println("Now you have " + this.taskQuantity() + " tasks in the list.\n");
    }

    public void listTasks() {
        if (taskArrayList.isEmpty()) {
            System.out.println("No items in list!\n");
            return;
        }

        int index = 0;
        System.out.println("Here are your tasks!");
        for (Task element : taskArrayList) {
            index++;
            System.out.println("    " + index + "." + element.listTaskString());
        }
        System.out.print("\n");
    }

    public int taskQuantity() {
        return taskArrayList.size();
    }

    public void markTaskIndex(int index) {
        if (index > taskArrayList.size()) {
            System.out.println("Sorry! There doesn't seem to be enough tasks for there to be a task " + index + "!\n");
            return;
        }

        Task task = taskArrayList.get(index - 1);
        task.markTask();
        saveTasks();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.listTaskString() + "\n");

    }

    public void unmarkTaskIndex(int index) {
        if (index > taskArrayList.size()) {
            System.out.println("Sorry! There doesn't seem to be enough tasks for there to be a task " + index + "!\n");
            return;
        }

        Task task = taskArrayList.get(index - 1);
        task.unmarkTask();
        saveTasks();

        System.out.println("Nice! I've marked this task as not done yet:");
        System.out.println("  " + task.listTaskString() + "\n");

    }

    public void deleteTaskIndex(int index) {
        if (index > taskArrayList.size()) {
            System.out.println("Sorry! There doesn't seem to be enough tasks for there to be a task " + index + "!\n");
            return;
        }

        Task removedTask = taskArrayList.remove(index - 1);
        saveTasks();
        System.out.println("Got it! I've removed this task from your list:");
        System.out.println("  " + removedTask.listTaskString() + "\n");


    }

    private void saveTasks() {
        storage.save(taskArrayList);
    }


}

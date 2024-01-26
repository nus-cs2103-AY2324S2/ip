import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addToList(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void removeFromList(int index) {
        tasks.remove(index);
    }

    public String saveMechanism() {
        String result = "";

        for (Task task : tasks) {
            result += task.storagePrinter() + System.lineSeparator();
        }

        return result;
    }

    public int taskNum() {
        return tasks.size();
    }

    public boolean isNull() {
        return tasks.isEmpty();
    }

    public void printList() {
        try {
            if (tasks.isEmpty()) {
                throw new TobiasException("    Your list is empty at the moment !");
            } else {
                Ui.printDivider();

                System.out.println("    Here are the tasks in your list:");
                for (Task task : tasks) {
                    int index = tasks.indexOf(task);
                    task.taskPrinter(index);
                }

                Ui.printDivider();
            }
        } catch (TobiasException e) {
            e.printMessage();
        }
    }
}

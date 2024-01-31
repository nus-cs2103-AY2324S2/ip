import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> items;

    public TaskList() {
        this.items = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> items) {
        this.items = items;
    }

    public void addTask(Task task) {
        items.add(task);
        System.out.println("Added this task");
        System.out.println(items.get(items.size() - 1));
        System.out.printf("Now you have %d tasks in the list :(\n", items.size());
        System.out.println();
    }

    public void markTask(int index) throws HenryException {
        if (index < 0 || index >= items.size()) {
            throw new HenryException("The index is out of bounds!");
        }
        items.get(index).markAsDone();
        System.out.println("This task is marked as done XD");
        System.out.println(items.get(index));
        System.out.println();
    }

    public void unmarkTask(int index) throws HenryException {
        if (index < 0 || index >= items.size()) {
            throw new HenryException("The index is out of bounds!");
        }
        items.get(index).unmarkAsDone();
        System.out.println("This task is marked as undone :(");
        System.out.println(items.get(index));
        System.out.println();
    }

    public void deleteTask(int index) throws HenryException {
        if (index < 0 || index >= items.size()) {
            throw new HenryException("The index is out of bounds!");
        }
        System.out.println("This task is deleted :)");
        System.out.println(items.get(index));
        System.out.println();
        items.remove(index);
    }

    public void printList() {
        System.out.println("Here is a list of tasks:");
        for (int i = 0; i < items.size(); i = i + 1) {
            System.out.printf("%d. %s\n", i + 1, items.get(i));
        }
        System.out.println();
    }

    public ArrayList<String> getFileStrings() {
        ArrayList<String> ret = new ArrayList<>();
        for (Task item : items) {
            ret.add(item.toFileString());
        }
        return ret;
    }
}

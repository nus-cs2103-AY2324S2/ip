import java.util.ArrayList;
public class TaskList  {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void printList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i+1 + ". " + tasks.get(i));
        }
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) throws PannaException {
        try {
            return this.tasks.get(i);
        }
        catch (Exception e) {
            throw new PannaException("Index out of bounds!");
        }
    }

    public void delete(int label) throws PannaException {
        try {
            tasks.remove(label);
        }
        catch (Exception e) {
            throw new PannaException("Invalid label! The number of tasks now is " + this.size() +
                    "\nPlease try with a more appropriate value! ");
        }

    }
}

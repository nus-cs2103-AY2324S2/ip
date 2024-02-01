import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> internalList;
    private File dataFile;

    TaskList() {
        this.internalList = new ArrayList<>();
    }

    TaskList(List<Task> list) {
        this.internalList = list;
    }

    public String generateName() {
        String s = "";
        for (int i = 0; i < internalList.size(); i++) {
            Task t = internalList.get(i);
            s += String.format("%d. %s", i + 1, t.getName()) + System.lineSeparator();
        }
        return s;
    }

    public Task getTask(int index) {
        return internalList.get(index - 1);
    }

    public void add(Task t) {
        this.internalList.add(t);
    }

    public void delete(int index) {
        internalList.remove(index - 1);
    }

    public Task pop(int index) {
        Task t = this.getTask(index);
        this.delete(index);
        return t;
    }

    public void saveData() {

    }
}

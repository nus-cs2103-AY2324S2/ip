import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> internalList;
    private File dataFile;

    TaskList(File dataFile) {
        this(new ArrayList<>(), dataFile);
    }

    TaskList(List<Task> list, File dataFile) {
        this.internalList = list;
        this.dataFile = dataFile;
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
        this.saveData();
    }

    public void delete(int index) {
        internalList.remove(index - 1);
        this.saveData();
    }

    public Task pop(int index) {
        Task t = this.getTask(index);
        this.delete(index);
        return t;
    }

    public void saveData() {
        ArrayList<String> data = new ArrayList<>();
        for (Task t : this.internalList) {
            data.add(t.exportData());
        }
        String dataString = String.join(System.lineSeparator(), data);
        try (FileWriter writer = new FileWriter(dataFile);) {
            writer.write(dataString);
        } catch (IOException e) {
            System.out.println("Data could not be saved.");
        }
    }
}

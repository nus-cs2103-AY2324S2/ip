// import ArrayList
import java.util.ArrayList;

public class Storage {
    private ArrayList<String> storage = new ArrayList<String>();

    public void addTask(String task) {
        storage.add(task);
    }

    public void listTasks() {
        for (int i = 0; i < storage.size(); i++) {
            System.out.println((i + 1) + ". " + storage.get(i));
        }
    }
};

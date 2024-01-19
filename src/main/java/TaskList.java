import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<String> taskList = new ArrayList<>();

    @Override
    public String toString() {
        String parsed = "";
        int count = 1;

        for (String currentItem : this.taskList) {
            parsed += count + "." + currentItem + "\n";
            count++;
        }
        return parsed;
    }

    public void add(String task) {
        this.taskList.add(task);
        System.out.println("added: " + task);
    }
}

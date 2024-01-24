import java.util.List;

public class TaskList {
    public List<Task> internalList;

    TaskList() {
        this.internalList = List.of();
    }

    TaskList(List<Task> list) {
        this.internalList = list;
    }

    public String generateName() {
        String s = "";
        for (Task t : internalList) {
            s += t.getName() + System.lineSeparator();
        }
        return s;
    }
}

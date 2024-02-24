import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    ArrayList<list_Entry> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public void add(list_Entry listEntry) {
        list.add(listEntry);
    }

    public void add(String taskName) {
        list.add(new list_Entry_Todo(taskName, false));
    }


    public void add(String taskName, LocalDate endDate) {
        list.add(new list_Entry_Deadline(taskName, false, endDate));
    }

    public void add(String taskName, LocalDate startDate, LocalDate endDate) {
        list.add(new list_Entry_Event(taskName, false,startDate, endDate));
    }

    public void delete(int index) {
        list.remove(index);
    }

    public void mark(int index) {
        list.get(index).markEntry();
    }
    public void unmark(int index) {
        list.get(index).unmarkEntry();
    }

    public boolean isValidIndex(int index) {
        return ((index < this.size()) && (index >= 0));
    }

    public int size() {
        return list.size();
    }

    public list_Entry get(int index) {
        return list.get(index);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        this.list.clear();
    }

}

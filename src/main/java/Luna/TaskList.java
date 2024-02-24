package Luna;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    ArrayList<ListEntry> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public void add(ListEntry listEntry) {
        list.add(listEntry);
    }

    public void add(String taskName) {
        list.add(new ListEntryTodo(taskName, false));
    }


    public void add(String taskName, LocalDate endDate) {
        list.add(new ListEntryDeadline(taskName, false, endDate));
    }

    public void add(String taskName, LocalDate startDate, LocalDate endDate) {
        list.add(new ListEntryEvent(taskName, false,startDate, endDate));
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

    public ListEntry get(int index) {
        return list.get(index);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        this.list.clear();
    }

}

import java.util.ArrayList;
import java.util.List;

public class Tasklist {
    private List<Task> todolist = new ArrayList<>();

    public void addItem(Task item) {
        todolist.add(item);
    }

    public Task removeItem(int index) {
        return todolist.remove(index);
    }

    public boolean isEmpty() {
        return todolist.isEmpty();
    }

    public String printTodolist() {
        StringBuilder s = new StringBuilder();
        int i = 1;
        if (isEmpty()) {
            s = new StringBuilder("Todolist is empty!");
            return s.toString();
        }
        for (Task t : todolist) {
            s.append(i).append(". ").append(t.toString()).append("\n\t");
            i++;
        }
        return s.toString().trim();
    }

    public void markTaskAsDone(int taskNumber, boolean isDone) {
        todolist.get(taskNumber).markAsDone(isDone);
    }

    public List<Task> getTodolist() {
        return todolist;
    }

    public String getTaskString(int taskNumber) {
        return todolist.get(taskNumber).toString();
    }
}

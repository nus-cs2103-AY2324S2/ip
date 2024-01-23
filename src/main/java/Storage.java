import java.util.List;
import java.util.ArrayList;

public class Storage {
    private List<Task> taskList;

    public Storage() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task t) {
        this.taskList.add(t);
    }

    public Task markAsDone(int index) {
        // Note input index is 1-indexed
        this.taskList.get(index-1).mark();
        return this.taskList.get(index-1);
    }

    public Task unmark(int index) {
        // Note input index is 1-indexed
        this.taskList.get(index-1).unmark();
        return this.taskList.get(index-1);
    }

    public int getSize() {
        return this.taskList.size();
    }

    public String displayList() {
        String d = "";
        for (int i = 1; i <= this.taskList.size(); ++i) {
            d += (i + ". " + taskList.get(i-1).toString() + '\n');
        }
        return d;
    }

}

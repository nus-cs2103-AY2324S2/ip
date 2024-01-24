import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> list;
    public Storage() { //constructor
        this.list = new ArrayList<>();
    }

    public void addItem(Task task) { //to append items to list
        this.list.add(task);
        UI.printResponse(task.getDescription());
    }

    public void mark(int num) {
        Task tomark = this.list.get(num - 1);
        tomark.markDone();
    }

    public void unmark(int num) {
        Task tounmark = this.list.get(num - 1);
        tounmark.unmark();
    }

    public void printList() {
        for (int i = 0; i < this.list.size(); i++) {
            if (i <= this.list.size() - 1) { //not last element
                this.list.get(i).printTaskDesc(i + 1, false);
            } else {
                this.list.get(i).printTaskDesc(i + 1, true);
            }
        }
    }
}

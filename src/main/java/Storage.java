import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> list;
    public Storage() { //constructor
        this.list = new ArrayList<>();
    }

    public void addItem(String item) { //to append items to list
        Task newTask = new Task(item);
        this.list.add(newTask);
        UI.printResponse(item);
    }

    public void mark(int num) {
        Task tomark = this.list.get(num - 1);
        tomark.markDone();
    }

    public void unmark(int num) {
        Task tounmark = this.list.get(num);
        tounmark.unmark();
    }

    public void printList() {
        for (int i = 0; i < this.list.size(); i++) {
            if (i != this.list.size() - 1) { //not last element
                UI.printResponse(i + 1, this.list.get(i), false);
            } else {
                UI.printResponse(i + 1, this.list.get(i), true);
            }
        }
    }
}

import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> list;
    public Storage() { //constructor
        this.list = new ArrayList<>();
    }

    public void addItem(Task task) { //to append items to list
        this.list.add(task);
        System.out.print("      ________________________________________________________\n");
        System.out.print("      Got it. I've added this task:\n ");
        task.printFullDesc();
        System.out.printf("      Now you have %d %s in the list.\n", this.list.size(), (this.list.size() == 1 ? "task" : "tasks"));
        System.out.print("      ________________________________________________________\n");
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
            if (i < this.list.size() - 1 && this.list.size() != 1) { //not last element
                this.list.get(i).printTaskDesc(i + 1, false);
            } else {
                this.list.get(i).printTaskDesc(i + 1, true);
            }
        }
    }
}

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

    public void delete(int num) {
        System.out.print("      ________________________________________________________\n");
        System.out.print("      Got it. I've removed this task:\n ");
        this.list.get(num - 1).printFullDesc();
        this.list.remove(num - 1);
        System.out.printf("      Now you have %d %s in the list.\n", this.list.size(), (this.list.size() == 1 ? "task" : "tasks"));
        System.out.print("      ________________________________________________________\n");
    }

    public void mark(int num) {
        Task toMark = this.list.get(num - 1);
        toMark.markDone();
    }

    public void unmark(int num) {
        Task toUnmark = this.list.get(num - 1);
        toUnmark.unmark();
    }

    public void printList() {
        if (this.list.size() == 0) {
            System.out.print("      ________________________________________________________\n");
            System.out.print("      Currently you have 0 tasks in the list!\n");
            System.out.print("      ________________________________________________________\n");
        } else {
            for (int i = 0; i < this.list.size(); i++) {
                if (i < this.list.size() - 1 && this.list.size() != 1) { //not last element
                    this.list.get(i).printTaskDesc(i + 1, false);
                } else {
                    this.list.get(i).printTaskDesc(i + 1, true);
                }
            }
        }
    }
}

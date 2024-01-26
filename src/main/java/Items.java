import java.util.ArrayList;

public class Items {
    private int item_count = 0;
    private ArrayList<Task> item_list = new ArrayList<Task>();

    public Items() {}

    public void add(Task item) {
        this.item_list.add(item);
        item_count += 1;
        // ack msg
        System.out.println(new Msg("Got it. I've added this task:", false));
        System.out.println(item);
        System.out.println(new Msg(String.format("Now you have %d tasks in the list.", this.item_count)));
    }

    public void mark(int i) {
        this.item_list.get(i - 1).markAsDone();
        System.out.println(Std_msgs.MARK);
        System.out.println(new Msg(this.item_list.get(i - 1).toString()));
    }

    public void unmark(int i) {
        this.item_list.get(i - 1).unMarkAsDone();
        System.out.println(Std_msgs.UNMARK);
        System.out.println(new Msg(this.item_list.get(i - 1).toString()));
    }

    public void delete(int i) {
        // exception handling when item_list is empty or invalid index is required
        Task temp = this.item_list.get(i - 1);
        this.item_list.remove(i - 1);
        this.item_count -= 1;
        System.out.println(new Msg("Got it. I've deleted this task:", false));
        System.out.println(temp);
        System.out.println(new Msg(String.format("Now you have %d tasks in the list.", this.item_count)));
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder("");
        for (int i = 0;i < item_count; i++) {
            text.append(String.format("%d. %s \n", i + 1, this.item_list.get(i)));
        }
        return text.toString();
    }
}

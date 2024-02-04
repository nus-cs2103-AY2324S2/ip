package items;
import java.util.ArrayList;
import msg.StdMsgs;
import task.Task;
import msg.Msg;

/**
 * The items class is a representation of a list of tasks that is able to add, delete, mark and unmark tasks on that
 * list and return an appropriate response
 */
public class Items {
    /** tracks number of tasks created*/
    private int item_count = 0;
    /** ArrayList<task> to store tasks */
    private ArrayList<Task> item_list = new ArrayList<Task>();

    public Items() {}

    /**
     * Adds an item to item_list and gives appropriate response msg
     *
     * @param item
     */
    public void add(Task item) {
        this.item_list.add(item);
        item_count += 1;
        // ack msg
        System.out.println(new Msg("Got it. I've added this task:", false));
        System.out.println(item);
        System.out.println(new Msg(String.format("Now you have %d tasks in the list.", this.item_count)));
    }

    /**
     * Marks task at index i of item_list as done
     *
     * @param i
     */
    public void mark(int i) {
        this.item_list.get(i - 1).markAsDone();
        System.out.println(StdMsgs.MARK);
        System.out.println(new Msg(this.item_list.get(i - 1).toString()));
    }

    /**
     * Unmarks task at index i of item_list
     *
     * @param i
     */
    public void unmark(int i) {
        this.item_list.get(i - 1).unMarkAsDone();
        System.out.println(StdMsgs.UNMARK);
        System.out.println(new Msg(this.item_list.get(i - 1).toString()));
    }

    /**
     * Deletes task at index i of item_list
     *
     * @param i
     */
    public void delete(int i) {
        // exception handling when item_list is empty or invalid index is required
        Task temp = this.item_list.get(i - 1);
        this.item_list.remove(i - 1);
        this.item_count -= 1;
        System.out.println(new Msg("Got it. I've deleted this task:", false));
        System.out.println(temp);
        System.out.println(new Msg(String.format("Now you have %d tasks in the list.", this.item_count)));
    }

    /**
     * Prints the list of tasks
     *
     */
    @Override
    public String toString() {
        StringBuilder text = new StringBuilder("");
        for (int i = 0;i < item_count; i++) {
            text.append(String.format("%d. %s \n", i + 1, this.item_list.get(i)));
        }
        return text.toString();
    }
}

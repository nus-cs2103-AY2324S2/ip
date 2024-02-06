package items;
import java.io.IOException;
import java.util.ArrayList;

import handler.DataHandler;
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
    public void add(Task item) throws IOException {
        this.item_list.add(item);
        item_count += 1;
        // ack msg
        new Msg(
                "Got it. I've added this task:\n" +
                        item +
                        "\n" +
                        String.format("Now you have %d tasks in the list.", this.item_count)
        ).print();
        DataHandler.overWriteItems(this.toDataFormat());
    }

    /**
     * Marks task at index i of item_list as done
     *
     * @param i
     */
    public void mark(int i) throws IOException {
        this.item_list.get(i - 1).markAsDone();
        StdMsgs.MARK.print();
        new Msg(this.item_list.get(i - 1).toString()).print();
        DataHandler.overWriteItems(this.toDataFormat());
    }

    /**
     * Unmarks task at index i of item_list
     *
     * @param i
     */
    public void unmark(int i) throws IOException {
        this.item_list.get(i - 1).unMarkAsDone();
        StdMsgs.UNMARK.print();
        new Msg(this.item_list.get(i - 1).toString()).print();
        DataHandler.overWriteItems(this.toDataFormat());
    }

    /**
     * Deletes task at index i of item_list
     *
     * @param i
     */
    public void delete(int i) throws IOException {
        // exception handling when item_list is empty or invalid index is required
        Task temp = this.item_list.get(i - 1);
        this.item_list.remove(i - 1);
        this.item_count -= 1;
        new Msg(
                "Got it. I've deleted this task:\n" +
                        temp +
                        "\n" +
                        String.format("Now you have %d tasks in the list.", this.item_count)
        ).print();
        DataHandler.overWriteItems(this.toDataFormat());
    }
    public Msg toMsg() {
        return new Msg(this.toString());
    }
    /**
     * Prints the list of tasks
     *
     */
    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        if (item_count == 0) {
            return "Sorry Sir/Mdm, it looks as though you have yet to add any tasks";
        }
        for (int i = 0;i < item_count; i++) {
            text.append(String.format("%d. %s \n", i + 1, this.item_list.get(i)));
        }
        return text.toString();
    }

    public String toDataFormat() {
        StringBuilder text = new StringBuilder();
        if (item_count == 0) {
            return "Sorry Sir/Mdm, it looks as though you have yet to add any tasks";
        }
        for (int i = 0; i < item_count; i++) {
            text.append(String.format("%s\n", this.item_list.get(i).toDataFormat()));
        }
        return text.toString();
    }
}

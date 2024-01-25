public class Items {
    private int item_count = 0;
    private Task[] item_list = new Task[100];

    public Items() {}

    public void add(Task item) {
        this.item_list[item_count] = item;
        item_count += 1;
        // ack msg
        System.out.println(new Msg("Got it. I've added this task:", false));
        System.out.println(item);
        System.out.println(new Msg(String.format("Now you have %d tasks in the list.", this.item_count)));
    }

    public void mark(int i) {
        this.item_list[i - 1].markAsDone();
        System.out.println(Std_msgs.MARK);
        System.out.println(new Msg(this.item_list[i - 1].toString()));
    }

    public void unmark(int i) {
        this.item_list[i - 1].unMarkAsDone();
        System.out.println(Std_msgs.UNMARK);
        System.out.println(new Msg(this.item_list[i - 1].toString()));
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder("");
        for (int i = 0;i < item_count; i++) {
            text.append(String.format("%d. %s \n", i + 1, item_list[i]));
        }
        return text.toString();
    }
}

public class Items {
    private int item_count = 0;
    private String[] item_list = new String[100];

    public Items() {}

    public void add(String item) {
        this.item_list[item_count] = item;
        item_count += 1;
        System.out.println(new Msg("added: " + item));
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

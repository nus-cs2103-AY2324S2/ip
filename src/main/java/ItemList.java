import java.util.ArrayList;
import java.util.List;

public class ItemList {
    private static final int MAX_ITEMS = 100;
    private List<String> items;

    public ItemList() {
        this.items = new ArrayList<>();
    }

    public void addItem(String item) {
        if (this.items.size() < MAX_ITEMS) {
            this.items.add(item);
            System.out.println("Added: " + item);
        } else {
            System.out.println("The item list is full.");
        }
    }
    public void listItems() {
        if (this.items.size() == 0) {
            System.out.println("The item list is empty.");
        } else {
            for (int i = 0; i < this.items.size(); i++) {
                System.out.println((i + 1) + ". " + this.items.get(i));
            }
        }
    }
}

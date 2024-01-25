import java.util.ArrayList;

public class Storage <T> {
    private final ArrayList<T> storage;

    public Storage() {
        this.storage = new ArrayList<T>();
    }

    public void storeItem(T item) {
        this.storage.add(item);
        final String output = String.format("added: %s", item.toString());
        System.out.println(output);
    }

    public void listItem() {
        for(int i = 0; i < this.storage.size(); ++i) {
            final String output = String.format("%d. %s", i + 1,
                    this.storage.get(i).toString());
            System.out.println(output);
        }
    }
}

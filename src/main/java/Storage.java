import java.util.ArrayList;

public class Storage <T> {
    private final ArrayList<T> storage;

    public Storage() {
        this.storage = new ArrayList<T>();
    }

    public void storeItem(T item) {
        this.storage.add(item);
    }
    public T getItem(int idx) {
        try {
            return this.storage.get(idx);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    public int getSize() {
        return this.storage.size();
    }

    public void listItem() {
        for(int i = 0; i < this.storage.size(); ++i) {
            final String output = String.format("%d. %s", i + 1,
                    this.storage.get(i).toString());
            System.out.println(output);
        }
    }
}

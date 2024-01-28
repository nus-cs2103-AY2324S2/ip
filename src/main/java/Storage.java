import java.util.ArrayList;

public class Storage <T extends FileFormattable> implements FileFormattable {
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
    public int deleteItem(int idx) {
        if (idx < 0 || idx >= this.storage.size()) {
            return -1;
        } else {
            this.storage.remove(idx);
            return 0;
        }
    }
    public int getSize() {
        return this.storage.size();
    }
    @Override
    public String toString() {
        String res = "";
        for(int i = 0; i < this.storage.size(); ++i) {
            res = String.format("%s\n%d. %s", res, i + 1, this.storage.get(i).toString());
        }
        return res;
    }

    @Override
    public String toFileFormat() {
        String res = "";
        for (T item : this.storage) {
            res = String.format("%s\n%s", res, item.toFileFormat());
        }
        return res;
    }
}

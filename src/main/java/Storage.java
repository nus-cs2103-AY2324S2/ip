import java.util.ArrayList;
public class Storage {
    public ArrayList<Task> storage;
    public Storage() {
        this.storage = new ArrayList<Task>();
    }
    public void add(Task t) {
        this.storage.add(t);
    }
    public int size() {
        return this.storage.size();
    }
    public Task get(int i) {
        return this.storage.get(i);
    }
    @Override
    public String toString() {
        String output = "";
        for (int i=0; i<this.storage.size(); i++) {
            output += String.format("%d. ", i+1);
            output += this.storage.get(i).toString();
        }
        return output;
    }

}

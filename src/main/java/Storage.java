import java.util.ArrayList;
import java.util.List;

public class Storage {

    // Data structure to store text entered by user
    private List<String> items = new ArrayList<>();

    public void add(String task) {
        items.add(task);
    }
    public List<String> getItems() {
        return items;
    }
}

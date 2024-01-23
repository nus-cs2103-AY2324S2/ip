import java.util.ArrayList;
public class TodoList {
    private final ArrayList<String> items;

    public TodoList() {
        items = new ArrayList<>();
    }

    public void addItem(String item) {
        items.add(item);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < items.size(); ++i) {
            if (i > 0) output.append("\t");
            output.append(i + 1).append(".  ").append(items.get(i));
            if (i < items.size() - 1) output.append("\n");
        }
        return output.toString();
    }
}

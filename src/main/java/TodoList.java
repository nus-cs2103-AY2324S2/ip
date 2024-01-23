import java.util.ArrayList;
public class TodoList {
    private final ArrayList<TodoItem> items;

    public TodoList() {
        this.items = new ArrayList<>();
    }

    public void addItem(TodoItem item) {
        this.items.add(item);
    }

    public void markItemDone(int index) {
        TodoItem currItem = this.items.get(index);
        currItem.markItem();

    }

    public void unmarkItemDone(int index) {
        TodoItem currItem = this.items.get(index);
        currItem.unmarkItem();
    }

    public String getItemToString(int index) {
        return this.items.get(index).toString();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < items.size(); ++i) {
            if (i > 0) output.append("\t");
            output.append(i + 1).append(".  ").append(items.get(i).toString());
            if (i < items.size() - 1) output.append("\n");
        }
        return output.toString();
    }
}

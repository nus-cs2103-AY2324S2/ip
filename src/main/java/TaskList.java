import java.util.ArrayList;
public class TaskList {
    private final ArrayList<ToDo> items;

    public TaskList() {
        this.items = new ArrayList<>();
    }

    public void addItem(ToDo item) {
        this.items.add(item);
    }

    public void markItemDone(int index) {
        ToDo currItem = this.items.get(index);
        currItem.markItem();

    }

    public void unmarkItemDone(int index) {
        ToDo currItem = this.items.get(index);
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

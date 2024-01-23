import java.util.ArrayList;
public class TaskList {
    private final ArrayList<Task> items;

    public TaskList() {
        this.items = new ArrayList<>();
    }

    public int getListSize() {
        return this.items.size();
    }

    public String getItemToString(int index) {
        return this.items.get(index).toString();
    }

    public void addItem(Task item) {
        this.items.add(item);
    }

    public void markItemDone(int index) {
        Task currItem = this.items.get(index);
        currItem.markItem();

    }

    public void unmarkItemDone(int index) {
        Task currItem = this.items.get(index);
        currItem.unmarkItem();
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

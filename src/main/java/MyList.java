import java.util.*;
public class MyList {
    private List<Task> items;

    public MyList() {
        this.items = new ArrayList<>();
    }

    public String addItem(String item) {
        Task t = new Task(item);
        this.items.add(t);
        return "added: " + item;
    }

    public String getItems() {
        StringBuilder stringBuilder = new StringBuilder();
        int index = 1;

        for (Task task : this.items) {
            String itemString = String.format("%d. [%s] %s\n", index, task.getStatusIcon(), task.getDescription());
            stringBuilder.append(itemString);
            index++;
        }

        return stringBuilder.toString();
    }

    public String markTask(int index) {
        if (index > this.items.size()) {
            return "Number is longer than list";
        } else {
            return this.items.get(index - 1).markAsDone();
        }
    }
}

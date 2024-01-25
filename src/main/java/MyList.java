import java.util.*;
public class MyList {
    private List<Task> items;

    public MyList() {
        this.items = new ArrayList<>();
    }

    public String addItem(Task t) {
        this.items.add(t);
        return "Got it. I've added this task:\n" + t.toString() + "\nNow you have " + this.items.size() + " tasks in the list.";
    }

    public String getItems() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");
        int index = 1;

        for (Task task : this.items) {
            String itemString = String.format("%d. %s\n", index, task.toString());
            stringBuilder.append(itemString);
            index++;
        }

        return stringBuilder.toString();
    }

    public String markTask(int index) {
        if (index < 1 || index > this.items.size()) {
            return "Number is outside length of list";
        } else {
            return "Nice! I've marked this task as done:\n" + this.items.get(index - 1).markAsDone();
        }
    }

    public String delete(int index) {
        if (index < 1 || index > this.items.size()) {
            return "Number is outside length of list";
        } else {
            Task t = this.items.remove(index - 1);
            return "Noted. I've removed this task:\n" + t.toString() + "\nNow you have " + this.items.size() + " tasks in the list.";
        }
    }
}

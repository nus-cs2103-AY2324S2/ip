package duke;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> list = new ArrayList<>();

    public String toDisplayString() {
        // here's your """""effectively final""""" value bro
        var numBox = new Object() {
            int num = 1;
        };
        return this.list.stream()
                .reduce
                        (new StringBuilder(),
                        (curr, acc) -> {
                            curr.append(numBox.num).append(".").append(acc.describe());
                            if (numBox.num < list.size()) {
                                curr.append("\n");
                            }
                            numBox.num++;
                            return curr;
                        },
                        StringBuilder::append)
                .toString();
    }
    
    public String toStorageString() {
        StringBuilder sb = new StringBuilder();

        for (Task t : this.list) {
            sb.append(t.toStorageString()).append('\n');
        }
        return sb.toString();
    }

    public Task get(int i) throws IndexOutOfBoundsException {
        return this.list.get(i);
    }

    public void add(Task t) {
        this.list.add(t);
    }

    public int size() {
        return this.list.size();
    }

    public void remove(int i) throws IndexOutOfBoundsException {
        this.list.remove(i);
    }
}

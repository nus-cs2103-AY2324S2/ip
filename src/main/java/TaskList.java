import java.util.ArrayList;

public class TaskList {
    
    ArrayList<String> list;

    public TaskList() {
        this.list = new ArrayList<String>();
    }

    public String addTask(String task) {
        this.list.add(task);
        return String.format("added: %s", task);

    }

    public void getList(PrintList printList) {
        for (int i = 0; i < this.list.size(); i++) {
            printList.add(String.format("%d. %s",
                i + 1,
                this.list.get(i)));
        }
    }
}

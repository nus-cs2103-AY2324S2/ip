import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> taskList;
    public Storage() {
        this.taskList = new ArrayList<>();
    }

    public void add(String command) {
        this.taskList.add(new Task(command));
        MessagePrinter.commandPrint(command);
    }

    public void printList() {
        MessagePrinter.commandListPrint(this.taskList);
    }

    public void changeStatusOfItem(String action, int which) {
        this.taskList.get(which).changeStatus(action);
    }
}

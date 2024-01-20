import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> taskList;
    public Storage() {
        this.taskList = new ArrayList<>();
    }

    public void add(Task newTask) {
        this.taskList.add(newTask);
        MessagePrinter.commandPrint(newTask, this.taskList.size());
    }

    public void printList() {
        MessagePrinter.commandListPrint(this.taskList);
    }

    public void changeStatusOfItem(String action, int which) {
        this.taskList.get(which).changeStatus(action);
    }

    @Override
    public String toString() {
        return "Now you have " + taskList.size() + "tasks in the list.";
    }
}

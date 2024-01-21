import java.util.ArrayList;

public class TaskList {
    
    ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public String addTask(String task) {
        Task newTask = new Task(task);
        this.list.add(newTask);
        return String.format("added: %s", task);

    }

    public void mark(int taskNumber, PrintList printList) {
        printList.add("Great! I will mark this as done:");
        Task task = this.list.get(taskNumber - 1);
        task.markAsDone();
        printList.add(task.toString());
    }

    public void unmark(int taskNumber, PrintList printList) {
        printList.add("Alright! this task is now unmarked:");
        Task task = this.list.get(taskNumber - 1);
        task.markAsNotDone();
        printList.add(task.toString());
    }

    public void getList(PrintList printList) {
        printList.add("Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            printList.add(String.format("%d. %s",
                i + 1,
                this.list.get(i)));
        }
    }
}

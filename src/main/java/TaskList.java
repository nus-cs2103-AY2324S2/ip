import java.util.ArrayList;

public class TaskList {
    
    ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public String addTask(String task, String fullDescription) {
        Task newTask;
        if (task.toLowerCase().equals("todo")) {
            newTask = new ToDo(fullDescription);
        } else if (task.toLowerCase().equals("deadline")) {
            int indexOfBy = fullDescription.indexOf("/by");
            String description = fullDescription.substring(0, indexOfBy - 1);
            String by = fullDescription.substring(indexOfBy + 4);
            newTask = new Deadline(description, by);
            
        } else {
            int indexOfBy = fullDescription.indexOf("/from");
            int indexOfTo = fullDescription.indexOf("/to");
            String description = fullDescription.substring(0, indexOfBy - 1);
            String from = fullDescription.substring(indexOfBy + 6, indexOfTo - 1);
            String to = fullDescription.substring(indexOfTo + 4);
            newTask = new Event(description, from, to);
        }
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

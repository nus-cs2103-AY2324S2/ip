package duke;

import duke.task.Task;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() { //constructor
        this.tasks = new ArrayList<>();
    }

    public void addItem(Task task, boolean isNew) { //to append items to tasks
        this.tasks.add(task);
        if (isNew) {
            System.out.print("      ________________________________________________________\n");
            System.out.print("      Got it. I've added this task:\n ");
            task.printFullDesc();
            System.out.printf("      Now you have %d %s in the tasks.\n", this.tasks.size(), (this.tasks.size() == 1 ? "task" : "tasks"));
            System.out.print("      ________________________________________________________\n");
            Storage.add(task);
        }
    }

    public void delete(int num) {
        Task taskToDelete = this.tasks.get(num - 1);
        System.out.print("      ________________________________________________________\n");
        System.out.print("      Got it. I've removed this task:\n ");
        taskToDelete.printFullDesc();
        this.tasks.remove(num - 1);
        System.out.printf("      Now you have %d %s in the tasks.\n", this.tasks.size(), (this.tasks.size() == 1 ? "task" : "tasks"));
        System.out.print("      ________________________________________________________\n");
        Storage.delete(num - 1);
    }

    public void mark(int num) {
        Task toMark = this.tasks.get(num - 1);
        toMark.markDone(true);
        String toReplace = toMark.toStore();
        try {
            Storage.changeMarking(num - 1, toReplace);
        } catch (IOException e) {
            System.out.println("Oops something went wrong.\n" + e.getMessage());
        }
    }

    public void unmark(int num) {
        Task toUnmark = this.tasks.get(num - 1);
        String toDelete = toUnmark.toStore();
        toUnmark.unmark();
        String toReplace = toUnmark.toStore();
        try {
            Storage.changeMarking(num - 1, toReplace);
        } catch (IOException e) {
            System.out.println("Oops something went wrong.\n" + e.getMessage());
        }
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void printList() {
        if (this.tasks.size() == 0) {
            System.out.print("      ________________________________________________________\n");
            System.out.print("      Currently you have 0 tasks in the tasks!\n");
            System.out.print("      ________________________________________________________\n");
        } else {
            for (int i = 0; i < this.tasks.size(); i++) {
                if (i < this.tasks.size() - 1 && this.tasks.size() != 1) { //not last element
                    this.tasks.get(i).printTaskDesc(i + 1, false);
                } else {
                    this.tasks.get(i).printTaskDesc(i + 1, true);
                }
            }
        }
    }
}

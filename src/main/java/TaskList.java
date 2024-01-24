import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> mylist;

    public TaskList() {
        this.mylist = new ArrayList<>();
    }
    public int size() {
        return mylist.size();
    }

    public Task get(int index) {
        if (index >= 0 && index < mylist.size()) {
            return mylist.get(index);
        }
        return null;
    }

    public void addTask(Task task) {
        mylist.add(task);
        System.out.println("added: " + task.getDescription());
    }

    public void markTask(int index) {
        if (validateIndex(index)) {
            Task taskToMark = mylist.get(index);
            taskToMark.mark();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskToMark);
        }
    }

    public void unmarkTask(int index) {
        if (validateIndex(index)) {
            Task taskToUnmark = mylist.get(index);
            taskToUnmark.unmark();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(taskToUnmark);
        }
    }

    public void listTasks() {
        System.out.print("");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < mylist.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + mylist.get(i));
        }
    }


    private boolean validateIndex(int index) {
        if (index >= 0 && index < mylist.size()) {
            return true;
        } else {
            System.out.println("Invalid task index. Please provide a valid index.");
            return false;
        }
    }

    public void goodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void hello() {
        System.out.println(" Hello I'm NoisyChatter");
        System.out.println(" What can I do for you?");
    }
}

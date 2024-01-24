import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void printList() {
        Reply replyToUser = new PrintList(this.tasks);
        replyToUser.displayMessage();
    }

    public void markTask(int i) {
        Task currTask = this.tasks.get(i - 1);
        currTask.markAsDone();
        Reply replyToUser = new Reply("Ah-ah-ah! ONE! I've marked this task as done:\n"
                + "        " + currTask.toString());
        replyToUser.displayMessage();
    }

    public void unmarkTask(int i) {
        Task currTask = this.tasks.get(i - 1);
        currTask.unmark();
        Reply replyToUser = new Reply("MINUS ONE! I've marked this task as not done yet:\n"
                + "        " + currTask.toString());
        replyToUser.displayMessage();
    }

    public int length() {
        return this.tasks.size();
    }
}

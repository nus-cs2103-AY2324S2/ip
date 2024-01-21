import java.util.ArrayList;

public class ListOfMessages {

    private class Task {
        String msg;
        boolean done;
        Task(String msg) {
            this.msg = msg;
            this.done = false;
        }
        public void mark() {
            this.done = true;
        }
        public void unmark() {
            this.done = false;
        }
        public String toString() {
            String is_done = this.done ? "X" : " ";
            return String.format("[%s] %s", is_done, this.msg);
        }
    }
    ArrayList<Task> listOfMessages;

    public ListOfMessages() {
        this.listOfMessages = new ArrayList<>();
    }
    public String add(String msg) {
        this.listOfMessages.add(new Task(msg));
        return "added: " + msg + "\n";
    }
    public String mark(int idx) {
        Task task = this.listOfMessages.get(idx);
        task.mark();
        return "Nice! I've marked this task as done:\n" + task + "\n";
    }
    public String unmark(int idx) {
        Task task = this.listOfMessages.get(idx);
        task.unmark();
        return "OK, I've marked this task as not done yet:\n" + task + "\n";
    }
    public String list() {
        StringBuilder res = new StringBuilder();
        int idx = 1;
        for (Task msg : this.listOfMessages) {
            res.append(String.format("%d. %s\n", idx, msg));
            idx++;
        }
        return "Here are the tasks in your list:\n" + res + "\n";
    }
}

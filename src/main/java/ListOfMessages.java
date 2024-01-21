import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.ArrayList;

public class ListOfMessages {


    private final ArrayList<Task> listOfMessages;

    public ListOfMessages() {
        this.listOfMessages = new ArrayList<>();
    }

    private String prelude() {
        return "Got it. I've added this task:\n";
    }
    private String trailer() {
        return String.format("Now you have %d tasks in the list.\n", this.listOfMessages.size());
    }
    private String standardize(Task msg) {
        return this.prelude() + msg + "\n" + this.trailer();
    }
    public String todo(String msg) {
        Task task = new ToDo(msg);
        this.listOfMessages.add(task);
        return this.standardize(task);
    }
    public String event(String msg) {
        Task task = new Event(msg);
        this.listOfMessages.add(task);
        return this.standardize(task);
    }
    public String deadline(String msg) {
        Task task = new Deadline(msg);
        this.listOfMessages.add(task);
        return this.standardize(task);
    }
    public String mark(String msg) {
        int idx = Integer.parseInt(msg.split(" ")[1]);
        Task task = this.listOfMessages.get(idx-1);
        task.mark();
        return "Nice! I've marked this task as done:\n" + task + "\n";
    }
    public String unmark(String msg) {
        int idx = Integer.parseInt(msg.split(" ")[1]);
        Task task = this.listOfMessages.get(idx-1);
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

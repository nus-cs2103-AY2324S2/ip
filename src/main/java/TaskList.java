import java.util.ArrayList;
public class TaskList {
    ArrayList<Task> tasksList;
    String line = "____________________________________________________________";

    public TaskList() {
        this.tasksList = new ArrayList<Task>();
    }

    public void add(Task t) {
        this.tasksList.add(t);
    }

    public String add(ToDo t) {
        this.tasksList.add(t);
        String output = "Got it. I've added this task:\n";
        output += t.toString() + "\n";
        output += "Now you have " + tasksList.size() + " tasks in the list.";
        return output;
    }

    public String add(Deadline t) {
        this.tasksList.add(t);
        String output = "Got it. I've added this task:\n";
        output += t.toString() + "\n";
        output += "Now you have " + tasksList.size() + " tasks in the list.";
        return output;
    }

    public String add(Event t) {
        this.tasksList.add(t);
        String output = "Got it. I've added this task:\n";
        output += t.toString() + "\n";
        output += "Now you have " + tasksList.size() + " tasks in the list.";
        return output;
    }

    public String mark(int index) {
        String output = "\n" + this.line;
        int length = this.tasksList.size();
        if (index > length || index <= 0) {

            return "";
        } else {
            index -= 1;
            this.tasksList.get(index).mark();
            output += "\nNice! I've marked this task as done:\n";
            output += this.tasksList.get(index).toString();
            output += ("\n" + this.line);
            return output;
        }
    }

    public String unmark(int index) {

        String output = "\n" + this.line;
        int length = this.tasksList.size();
        if (index > length || index <= 0) {
            return "";
        } else {
            index -= 1;
            this.tasksList.get(index).unmark();
            output += "\nOK, I've marked this task as not done yet:\n";
            output += this.tasksList.get(index).toString();
            output += ("\n" + this.line);
            return output;
        }
    }

    @Override
    public String toString() {
        int taskCount = 0;
        String output = "____________________________________________________________\n";
        output += "Here are the tasks in your list:\n";
        for (Task t : this.tasksList) {
            taskCount++;
            output += (taskCount + " " + t.toString() + "\n");
        }
        output += "____________________________________________________________\n";
        return output;
    }

    void printLines() {
        System.out.println("____________________________________________________________");
    }
}

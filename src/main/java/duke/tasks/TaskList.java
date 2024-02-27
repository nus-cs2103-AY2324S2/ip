package duke.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Class that keeps track of a list of tasks.
 */
public class TaskList {
    ArrayList<Task> tasksList;
    String line = "____________________________________________________________";

    public TaskList() {
        this.tasksList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }

    /**
     * Adds task to list
     * @param t task to be added
     */
    public void add(Task t) {
        this.tasksList.add(t);
    }

    /**
     * Adds ToDo task to list
     * @param t ToDo task to be added
     * @return String response to be outputted to the user.
     */
    public String add(ToDo t) {
        this.tasksList.add(t);
        String output = "Got it. I've added this task:\n";
        output += t.toString() + "\n";
        output += "Now you have " + tasksList.size() + " tasks in the list.";
        return output;
    }
    /**
     * Adds DeadLine task to list
     * @param t DeadLine task to be added
     * @return String response to be outputted to the user.
     */
    public String add(Deadline t) {
        this.tasksList.add(t);
        String output = "Got it. I've added this task:\n";
        output += t.toString() + "\n";
        output += "Now you have " + tasksList.size() + " tasks in the list.";
        return output;
    }
    /**
     * Adds Event task to list
     * @param t Event task to be added
     * @return String response to be outputted to the user.
     */
    public String add(Event t) {
        this.tasksList.add(t);
        String output = "Got it. I've added this task:\n";
        output += t.toString() + "\n";
        output += "Now you have " + tasksList.size() + " tasks in the list.";
        return output;
    }

    /**
     * Marks task as completed based on index in task list
     * @param index task to be marked
     * @return String to be outputted to user.
     */
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

    public String delete(int index) {
        String output = "\n" + this.line;
        int length = this.tasksList.size();
        if (index > length || index <= 0) {

            return "";
        } else {
            index -= 1;
            Task t = this.tasksList.get(index);
            this.tasksList.remove(index);
            output += "\nNoted. I've removed this task:\n";
            output += t.toString();
            output += ("\nNow you have " + (length - 1) + " tasks in the list.");
            output += ("\n" + this.line);
            return output;
        }
    }
    /**
     * Unmarks task as completed based on index in task list
     * @param index task to be marked
     * @return String to be outputted to user.
     */
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

    public ArrayList<Task> getTasksList() {
        return this.tasksList;
    }
    void printLines() {
        System.out.println("____________________________________________________________");
    }
}

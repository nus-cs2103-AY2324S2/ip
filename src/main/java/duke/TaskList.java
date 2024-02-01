package duke;
import task.*;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = taskList;
    }

    public void bye(Ui ui) {
        Ui.showGoodbyeMessage();
    }

    // print the entire list
    public void showList() {
        System.out.println(Ui.INDENT_SEPERATOR);
        if (taskList.isEmpty()) {
            System.out.println("There are currently no tasks in your list");
        } else {
            System.out.println(Ui.INDENT + "Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.get(i);
                System.out.println(Ui.INDENT + (i + 1) + "." + t.toString());
            }
        }
        System.out.println(Ui.INDENT_SEPERATOR);
    }

    // mark an item in list
    public void mark(int position) {
        // check for error
        if (position + 1 > taskList.size()) {
            System.out.println(Ui.INDENT_SEPERATOR);
            System.out.println(Ui.INDENT + "Task.Task does not exist");
        } else if (position < 0) {
            System.out.println(Ui.INDENT_SEPERATOR);
            System.out.println(Ui.INDENT + "Task.Task does not exist");

        } else {
            System.out.println(Ui.INDENT_SEPERATOR);
            Task t = taskList.get(position);
            if (t.getStatusIcon().equals(" ")) {
                System.out.println(Ui.INDENT + "Nice! I've marked this task as done:");
            }
            t.markAsDone();
            System.out.println(Ui.INDENT + "  " + t.toString());
        }
        System.out.println(Ui.INDENT_SEPERATOR);
    }

    // unmark an item in list
    public void unmark(int position) {
        // check for error
        if (position + 1 > taskList.size()) {
            System.out.println(Ui.INDENT_SEPERATOR);
            System.out.println(Ui.INDENT + "Task.Task does not exist");
        } else if (position < 0) {
            System.out.println(Ui.INDENT_SEPERATOR);
            System.out.println(Ui.INDENT + "Task.Task does not exist");

        } else {
            System.out.println(Ui.INDENT_SEPERATOR);
            Task t = taskList.get(position);
            if (t.getStatusIcon().equals("X")) {
                System.out.println(Ui.INDENT + "OK, I've marked this task as not done yet:");
            }
            t.markAsUndone();
            System.out.println(Ui.INDENT + "  " + t.toString());
        }
        System.out.println(Ui.INDENT_SEPERATOR);
    }
    public void todo(Todo t)  {
        taskList.add(t);
        System.out.println(Ui.INDENT_SEPERATOR);
        System.out.println(Ui.INDENT + "Got it. I've added this task:");
        System.out.println(Ui.INDENT + "  " + t.toString());
        System.out.println(Ui.INDENT + "Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(Ui.INDENT_SEPERATOR);
    }

    public void deadline(Deadline t) {
        System.out.println(Ui.INDENT_SEPERATOR);
        taskList.add(t);
        System.out.println(Ui.INDENT + "Got it. I've added this task:");
        System.out.println(Ui.INDENT + "  " + t.toString());
        System.out.println(Ui.INDENT + "Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(Ui.INDENT_SEPERATOR);
    }
    public void event(Event t) {
        System.out.println(Ui.INDENT_SEPERATOR);
        taskList.add(t);
        System.out.println(Ui.INDENT + "Got it. I've added this task:");
        System.out.println(Ui.INDENT + "  " + t.toString());
        System.out.println(Ui.INDENT + "Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(Ui.INDENT_SEPERATOR);
    }

    public void delete(int position) {
        Task t = taskList.get(position);
        // check for error
        if (position + 1 > taskList.size()) {
            System.out.println(Ui.INDENT + "Task.Task does not exist");
        } else if (position < 0) {
            System.out.println(Ui.INDENT + "Task.Task does not exist");
        } else {
            taskList.remove(t);
            System.out.println(Ui.INDENT_SEPERATOR);
            System.out.println(Ui.INDENT + "Noted. I've removed this task:");
            System.out.println(Ui.INDENT + "  " + t.toString());
            System.out.println(Ui.INDENT + "Now you have " + taskList.size() + " tasks in the list.");
        }
        System.out.println(Ui.INDENT_SEPERATOR);
    }

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }
}

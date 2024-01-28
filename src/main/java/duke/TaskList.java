package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> taskList;
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void List() {
        String text;
        System.out.println("\t____________________________________________________________");
        for (int i = 0; i < this.taskList.size(); i++) {
            text = "\t" + (i+1) + "." + this.taskList.get(i).toString();
            System.out.println(text);
        }
        System.out.println("\t____________________________________________________________\n");
    }

    public void mark(int num) {
        Task curr = this.taskList.get(num - 1);
        curr.mark();

        String text = "\t____________________________________________________________\n"
                + "\tNice! I've marked this task as done:\n"
                + "\t" + curr + "\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }

    public void unmark(int num) {
        Task curr = this.taskList.get(num - 1);
        curr.unmark();

        String text = "\t____________________________________________________________\n"
                + "\tOK, I've marked this task as not done yet:\n"
                + "\t" + curr + "\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }

    public void add(Task add) {
        this.taskList.add(add);
        String word = " task";
        if (this.taskList.size() != 1) {word += "s";}

        String text = "\t____________________________________________________________\n"
                + "\tGot it. I've added this task:\n"
                + "\t  " + add.toString() + "\n"
                + "\tNow you have " + this.taskList.size() + word + " in the list.\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }

    public void delete(int num) {
        Task curr = this.taskList.get(num - 1);
        this.taskList.remove(curr);

        String text = "\t____________________________________________________________\n"
                + "\tOK, I've deleted this task:\n"
                + "\t  " + curr.toString() + "\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }
}

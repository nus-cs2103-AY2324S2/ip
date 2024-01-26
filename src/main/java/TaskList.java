import java.util.ArrayList;

public class TaskList extends Storage<Task> {
    public TaskList() {
        super();
    }
    public void checkTask(int idx) {
        Task toCheck = super.getItem(idx);
        if (toCheck != null) {
            toCheck.check();
            final String output = String.format("Nice! I've marked this task as done:\n"+
                    "%s", super.getItem(idx).toString());
            System.out.println(output);
        } else {
            System.out.println("Invalid task index!");
        }
    }
    public void uncheckTask(int idx) {
        Task toUncheck = super.getItem(idx);
        if (toUncheck != null) {
            toUncheck.uncheck();
            final String output = String.format("OK, I've marked this task as not done yet:\n"+
                    "%s", super.getItem(idx).toString());
            System.out.println(output);
        } else {
            System.out.println("Invalid task index!");
        }
    }
    @Override
    public void storeItem(Task task) {
        super.storeItem(task);
        final String output = String.format("Got it. I've added this task:\n"
                + "    %s\n"
                + "Now you have %d tasks in the list.", task.toString(), super.getSize());
        System.out.println(output);
    }
    @Override
    public void listItem() {
        if (super.getSize() == 0) {
            System.out.println("YAY! You have no tasks ongoing ^_^");
        } else {
            System.out.println("Here are the tasks in your list:\n");
            super.listItem();
        }
    }
}

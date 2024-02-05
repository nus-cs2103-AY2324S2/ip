import java.awt.*;

public class TaskList {

    private final Task[] list;
    private int count;

    public TaskList(){
        list = new Task[100];
        count = 0;
    }

    public String add_task(String msg) throws TaskListFullException {

        if (this.count >= 100) {
            throw new TaskListFullException("Sorry, the task list is full.");
        }

        list[count] = new Task(msg);
        count++;

        return "\t-----------------------------------\n" +
                "\tadded: " + msg + "\n" +
                "\t-----------------------------------";
    }

    public String mark_as_done(int index) throws IndexOutOfBoundsException{
        if (index > count || index < 1) {
            throw new IndexOutOfBoundsException("Sorry, the index is out of range.");
        }
        list[index - 1].markAsDone();
        return "\t-----------------------------------\n" +
                "\tNice! I've marked this task as done:\n" +
                "\t  " + list[index - 1].toString() + "\n" +
                "\t-----------------------------------";
    }

    public String mark_as_undone(int index) throws IndexOutOfBoundsException {
        if (index > count || index < 1) {
            throw new IndexOutOfBoundsException("Sorry, the index is out of range.");
        }
        list[index - 1].markAsUndone();
        return "\t-----------------------------------\n" +
                "\tNice! I've marked this task as undone:\n" +
                "\t  " + list[index - 1].toString() + "\n" +
                "\t-----------------------------------";
    }


    @Override
    public String toString() {
        String result = "\t-----------------------------------\n";
        for (int i = 0; i < count; i++) {
            result += "\t" + (i + 1) + ". " + list[i].toString() + "\n";
        }
        result += "\t-----------------------------------";
        return result;
    }

}

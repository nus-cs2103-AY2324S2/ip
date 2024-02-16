package Tasks;

import Tasks.Task;

import java.util.ArrayList;
import Exceptions.TaskListFullException;

public class TaskList {

    private final ArrayList<Task> list;

    public TaskList(){
        list = new ArrayList<>();
    }

    public String add_task(Task task) throws TaskListFullException {

        if ( list.size() >= 100) {
            throw new TaskListFullException("Sorry, the task list is full.");
        }

        list.add(task);

        return "\t-----------------------------------\n" +
                "\tGot it. I've added this task:\n" +
                "\t\t" + task.toString() + "\n" +
                "\tNow you have " + list.size() + " tasks in the list.\n" +
                "\t-----------------------------------";
    }

    public String remove_task(int index) throws IndexOutOfBoundsException {
        if (index > list.size() || index < 1) {
            throw new IndexOutOfBoundsException("Sorry, the provided id is invalid.");
        }
        Task removed = list.remove(index - 1);
        return "\t-----------------------------------\n" +
                "\tNoted. I've removed this task:\n" +
                "\t  " + removed.toString() + "\n" +
                "\tNow you have " + list.size() + " tasks in the list.\n" +
                "\t-----------------------------------";
    }

    public String mark_as_done(int index) throws IndexOutOfBoundsException{
        if (index > list.size() || index < 1) {
            throw new IndexOutOfBoundsException("Sorry, the provided id is invalid.");
        }
        list.get(index - 1).markAsDone();
        return "\t-----------------------------------\n" +
                "\tNice! I've marked this task as done:\n" +
                "\t  " + index + ". " + list.get(index - 1).toString() + "\n" +
                "\t-----------------------------------";
    }

    public String mark_as_undone(int index) throws IndexOutOfBoundsException {
        if (index > list.size() || index < 1) {
            throw new IndexOutOfBoundsException("Sorry, the provided id is invalid.");
        }
        list.get(index - 1).markAsUndone();
        return "\t-----------------------------------\n" +
                "\tNice! I've marked this task as undone:\n" +
                "\t  " + index + ". " + list.get(index - 1).toString() + "\n" +
                "\t-----------------------------------";
    }


    @Override
    public String toString() {
        String result = "\t-----------------------------------\n";
        for (int i = 0; i < list.size(); i++) {
            result += "\t" + (i + 1) + ". " + list.get(i).toString() + "\n";
        }
        result += "\t-----------------------------------";
        return result;
    }

}

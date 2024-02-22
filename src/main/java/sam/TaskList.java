package sam;

import sam.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> items;

    public TaskList() {
        this.items = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> items) {
        this.items = items;
    }

    public void displayList() {
        System.out.println("____________________________________________________________");
        if (items.isEmpty()) {
            System.out.println("No tasks in the list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < items.size(); i++) {
                Task task = items.get(i);
                System.out.println(((i + 1) + "." + task));
            }
        }
        System.out.println("____________________________________________________________");
    }

    public void markTask(int index) throws SamException{
        if (index < 0 || index >= items.size()) {
            throw new SamException("Please check how many tasks are there in your list.");
        }
        items.get(index).markAsDone();
        System.out.println("Nice, I've marked this task as done for you:");
        System.out.println((items.get(index)));
    }

    public void unmarkTask(int index) throws SamException {
        if (index < 0 || index >= items.size()) {
            throw new SamException("Please check how many tasks are there in your list.");
        }
        items.get(index).markAsUndone();
        System.out.println("Nice, I've marked this task as undone for you:");
        System.out.println((items.get(index)));
    }

    public void addTask(Task newTask) {
        items.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + items.size() + " tasks in the list.");
    }
    public void deleteTask(int index) throws SamException {
        if (index < 0 || index >= items.size()) {
            throw new SamException("Invalid task number. Please check how many tasks your have in the list.");
        }

        Task removedTask = items.remove(index);
        System.out.println("---------------------------");
        System.out.println("I've removed this task:");
        System.out.println(" " + removedTask);
        System.out.println("Now you have " + items.size() + " task(s) left in the list. ");
        System.out.println("---------------------------");
    }

    public ArrayList<String> getFileStrings() {
        ArrayList<String> ret = new ArrayList<>();
        for (Task item : items) {
            ret.add(item.toFileFormat());
        }
        return ret;
    }
}

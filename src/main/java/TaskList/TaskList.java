package TaskList;

import java.util.ArrayList;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;


public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public int getListSize() {
        return this.list.size();
    }

    public ArrayList<Task> getList() {
        return (ArrayList<Task>) this.list.clone();
    }

    public String getListUpdate() {
        if (this.getListSize() == 0) {
            return "You have no tasks or upcoming deadlines! Take a breather and relax.";
        }
        if (this.getListSize() <= 5) {
            return "Time for more work and less playing!";
        }
        return "HUMAN! stop WATCHING YOUTUBE and start DOING WORK!!!";
    }

    public void displayList() {
        int index = 1;
        for (Task task : this.list) {
            System.out.println(String.format("%d. [%s] [%s] %s", index, task.getTypeIcon(), task.getStatusIcon(), task.getDescription()));
            index++;
        }
    }

    public void markTask(int index) {
        try {
            this.list.get(index - 1).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No task at that index! you fool");
        }
    }

    public void unmarkTask(int index) {
        try {
            this.list.get(index - 1).maskAsUndone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No task at that index! you fool");
        }
    }

    public void deleteTask(int index) {
        try {
            Task t = this.list.remove(index - 1);
            System.out.println("Noted. The following task is removed, you careless human being!");
            System.out.println(String.format("[%s] [%s] %s\n", t.getTypeIcon(), t.getStatusIcon(), t.getDescription()));
            System.out.println(String.format("Now you only have %d tasks left. %s", this.getListSize(), this.getListUpdate()));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No task at that index! you fool");
        }
    }

    public void addToList(Task t) {
        this.list.add(t);
        System.out.println(String.format("added: %s", t.getDescription()));
    }
}

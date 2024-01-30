package TheAdvisor;

import java.util.ArrayList;
import java.io.Serializable;

public class TaskList implements Serializable {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void addToList(Task task) {
        this.taskList.add(task);
        System.out.println("     Got it. I've added this task:\n" +
                "       " + task.toString() + "\n" +
                "     Now you have " + taskList.size() +
                " tasks in the list.");
    }

    public void deleteFromList(int index) throws TheAdvisorException {
        try {
            checkIndex(index);
            Task task = this.taskList.get(index);
            this.taskList.remove(index);
            System.out.println("     Noted. I've removed this task:\n" + "       " +
                    task.toString() + "\n" + "     Now you have " + taskList.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new TheAdvisorException("We use 1-based indexing for deletion, marking and unmarking! Do try again :)");
        }
    }

    public void markTask(int index) throws TheAdvisorException {
        try {
            checkIndex(index);
            Task task = this.taskList.get(index);
            checkMarked(task);
            task.markDone();
            System.out.println("     Nice! I've marked this task as done:\n" + "       " +
                    task.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new TheAdvisorException("We use 1-based indexing for deletion, marking and unmarking! Do try again :)");
        }
    }

    public void unmarkTask(int index) throws TheAdvisorException {
        try {
            checkIndex(index);
            Task task = this.taskList.get(index);
            checkUnmarked(task);
            task.unmark();
            System.out.println("          OK, I've marked this task as not done yet:\n" + "       " +
                    task.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new TheAdvisorException("We use 1-based indexing for deletion, marking and unmarking! Do try again :)");
        }
    }

    public int getSize() {
        return this.taskList.size();
    }

    public String getTask(int index) {
        return taskList.get(index).toString();
    }

    private void checkMarked(Task task) throws TheAdvisorException {
        if (task.isDone) {
            throw new TheAdvisorException("The task is already marked! Carry on.");
        }
    }

    private void checkUnmarked(Task task) throws TheAdvisorException {
        if (!task.isDone) {
            throw new TheAdvisorException("The task is already unmarked! Carry on.");
        }
    }

    private void checkIndex(int index) throws TheAdvisorException {
        int size = this.taskList.size();
        if (index < 0) {
            throw new TheAdvisorException("We use 1-indexing for marking. Please try again.");
        } else if (index > size) {
            throw new TheAdvisorException("Out of bounds. We use 1-indexing for marking. Please try again.");
        } else if (size == 0) {
            throw new TheAdvisorException("The list is empty! Start adding in things :)");
        }
    }
}

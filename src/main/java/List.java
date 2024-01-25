import java.util.*;
public class List {
    private ArrayList<Task> tasks;

    public List(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        String task_s = this.tasks.size() == 1 ? " task " : " tasks ";
        System.out.println("__________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have " + this.tasks.size() + task_s + "in the list.\n"
                + "__________________________________________________________\n");
    }

    public void displayTasks() {
        System.out.println("__________________________________________________________\n"
                + "Here are the tasks in your list:"
        );
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println("__________________________________________________________\n");
    }

    public void deleteTask(int taskNum) {
        String task_s = this.tasks.size() - 1 == 1 ? " task " : " tasks ";
        System.out.println("__________________________________________________________\n"
                + "Noted. I've removed this task:\n"
                + tasks.get(taskNum).toString()
                + "\nNow you have " + (this.tasks.size() - 1) + task_s + "in the list.\n"
                + "__________________________________________________________\n");
        this.tasks.remove(taskNum);
    }

    public boolean validTaskNum(int taskNum) {
        return taskNum > 0 && taskNum <= tasks.size();
    }

    public void markTask(int taskNum) {
        this.tasks.get(taskNum).mark();
    }

    public void unmarkTask(int taskNum) {
        this.tasks.get(taskNum).unmark();
    }

    public int getListSize() {
        return this.tasks.size();
    }

    public int getNumberOfTasks() {
        return this.tasks.size();
    }
}

import java.util.ArrayList;
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tl) {
        this.tasks = tl;
    }

    public void add(Task t) {
        this.tasks.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println(t.getStatus());
        System.out.println(String.format("You now have %d tasks in the list", tasks.size()));
    }

    public void delete(int i) {
            System.out.println("Noted! this task gon like pentagon:");
            System.out.println(tasks.get(i - 1).getStatus());
            tasks.remove(Integer.valueOf(i) - 1);
            System.out.println(String.format("There are %d tasks left in the list", tasks.size()));
    }

    public void markAsDone(int i) {
        tasks.get(i - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(i - 1).getStatus());
    }

    public void unmark(int i) {
        tasks.get(i - 1).unmark();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(tasks.get(i - 1).getStatus());
    }
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public boolean isValid(int i) {
        if (i > tasks.size() || i < 1) {
            return false;
        }

        return true;
    }
}

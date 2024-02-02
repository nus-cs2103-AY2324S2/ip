import java.util.ArrayList;
public class TaskList extends ArrayList<Task> {
    void addToList(String s) {
        this.add(new Task(s));
        System.out.println("added: " + s);
    }

    void addToList(Task t) {
        this.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        countTasks();
    }

    void displayList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.size(); i++) {
            System.out.println(i+1 + ":" + this.get(i));
        }
    }

    void markComplete(int i) {
        System.out.println("Nice! I've marked this task as done:");
        this.get(i-1).markComplete();
        System.out.println(this.get(i-1));
    }

    void unmarkComplete(int i) {
        System.out.println("OK, I've marked this task as not done yet:");
        this.get(i-1).unmarkComplete();
        System.out.println(this.get(i-1));
    }

    void deleteTask(int i) {
        Task t = this.remove(i-1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        countTasks();
    }

    void countTasks() {
        System.out.println("Now you have " + this.size() + " tasks in the list.");
    }
}

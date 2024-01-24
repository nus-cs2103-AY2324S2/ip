import java.sql.SQLOutput;
import java.util.ArrayList;

public class Ping {
    final String name = "Ping";


    protected ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task i) {
        tasks.add(i);
        System.out.println("added: " + i.description);
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No ability added yet");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                int idx = i + 1;
                //System.out.printf("%d. " + tasks.get(i).description + "%n", idx);
                    if (tasks.get(i).isDone) {
                        System.out.printf("%d." + tasks.get((i)).toString() + "%n", idx);
                    } else {
                        System.out.printf("%d." + tasks.get(i).toString() + "%n", idx);

                }
            }
        }
    }

    public void markJobs(Task i) {
        System.out.println("Nice! I've marked this task as done:");
        i.isDone = true;
        System.out.println(i.toString());
    }

    public void unMarkJobs(Task i) {
        System.out.println("OK, I've marked this task as not done yet:");
        i.isDone = false;
        System.out.println(i.toString());
    }
    public void goodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void todoJobs(Todo i) {
        System.out.println("Got it. I've added this task:");
        tasks.add(i);
        System.out.println(i.toString());
        int numOfWork = tasks.size();
        System.out.printf("Now you have %d tasks in the list." + "%n", numOfWork);
    }

    public void dlJobs(Deadline i) {
        System.out.println("Got it. I've added this task:");
        tasks.add(i);
        System.out.println(i.toString());
        int numOfWork = tasks.size();
        System.out.printf("Now you have %d tasks in the list." + "%n", numOfWork);
    }

    public void evJobs(Event i) {
        System.out.println("Got it. I've added this task:");
        tasks.add(i);
        System.out.println(i.toString());
        int numOfWork = tasks.size();
        System.out.printf("Now you have %d tasks in the list." + "%n", numOfWork);
    }

    public void delete(int i) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(i).toString());
        tasks.remove(i);
        int numOfWork = tasks.size();
        System.out.printf("Now you have %d tasks in the list." + "%n", numOfWork);
    }
}

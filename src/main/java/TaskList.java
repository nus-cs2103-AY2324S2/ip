import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    static ArrayList<Task> taskList;

    public static void create() {
        taskList = new ArrayList<>();
    }

    public static void list() {
        if (taskList.size() == 0) {
            System.out.println("You have no tasks! Hooray!!!!!!!!!!");
        }
        else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                int number = i + 1;
                System.out.println(number + ". " + taskList.get(i).toString());
            }
        }
    }

    public static int listSize() {
        return taskList.size();
    }

    public static void addTask(Task task) {
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));   
    }


    public static void markTask(int idx) {
        Task task = taskList.get(idx-1);
        task.done();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public static void unmarkTask(int idx) {
        Task task = taskList.get(idx-1);
        task.undone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    public static void deleteTask(int idx) {
        Task task = taskList.get(idx-1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
        taskList.remove(idx-1);
    }

    public static Task getTask(int idx) {
        return taskList.get(idx-1);
    }
}

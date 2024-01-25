import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BadGPT {
    private static final String name = "BadGPT";
    private static List<Task> tasks = new ArrayList<>(100);

    public static void main(String[] args) {
        line();
        System.out.println("Hello! I'm " + name + ".\n" + "What can I do for you?");
        line();

        while (true) {
            Scanner sc = new Scanner(System.in);
            String cmd = sc.next();

            // Cases
            // "bye": Exit the loop and the program.
            // "list": List out all currently stored tasks.
            // "mark": Mark the task corresponding to the number entered after as complete.
            // "unmark": Unmark the task corresponding to the number entered after.
            // Else, store the string entered as a new Task object.
            switch (cmd) {
                case "bye": {
                    bye();
                    break;
                }
                case "list": {
                    list();
                    break;
                }
                case "mark": {
                    int taskNum = sc.nextInt() - 1;
                    mark(taskNum);
                    break;
                }
                case "unmark": {
                    int taskNum = sc.nextInt() - 1;
                    unmark(taskNum);
                    break;
                }
                default: {
                    Task task = new Task(cmd + sc.nextLine());
                    store(task);
                    break;
                }
            }
        }
    }

    public static void line() {
        System.out.println("____________________________________________________________");
    }

    public static void store(Task task) {
        tasks.add(task);
        line();
        System.out.println("Added: " + task);
        line();
    }

    public static void list() {
        line();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        line();
    }

    public static void mark(int taskNum) {
        tasks.get(taskNum).complete();
        line();
        System.out.println("Nice! I've marked this task as done:\n" + tasks.get(taskNum));
        line();
    }

    public static void unmark(int taskNum) {
        tasks.get(taskNum).uncomplete();
        line();
        System.out.println("wyd bro\n" + tasks.get(taskNum));
        line();
    }

    public static void bye() {
        line();
        System.out.println("Smell ya later");
        line();
        System.exit(0);
    }
}

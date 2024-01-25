import java.util.*;

public class Tyler {
    private static Task[] taskList = new Task[100];
    private static int curr = 0;

    public static void main(String[] args) {
        System.out.println("    Hello from Tyler");
        System.out.println("    What can I do for you?");
        System.out.println("    list, todo, deadline, event, mark, unmark, bye");
        System.out.println("    --------------------------------------------------");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals("bye")) {
            System.out.println("    --------------------------------------------------");
            if (input.equals("list")) {
                Tyler.list();
            } else if (input.equals("mark")) {
                int taskNumber = Integer.parseInt(sc.nextLine());
                taskList[taskNumber - 1].mark();
            } else if (input.equals("unmark")) {
                int taskNumber = Integer.parseInt(sc.nextLine());
                taskList[taskNumber - 1].unmark();
            } else if (input.equals("todo")) {
                String task = sc.nextLine();
                Task newTask = new Todo(task);
                taskList[curr] = newTask;
                System.out.println("    Got it! I've added this task:");
                System.out.println("      " + newTask.toString());
                curr++;
                System.out.println("    Now you have " + curr + " tasks in the list");
            } else if (input.equals("deadline")) {
                String task = sc.nextLine();
                String end = sc.nextLine();
                Task newTask = new Deadline(task, end);
                taskList[curr] = newTask;
                System.out.println("    Got it! I've added this task:");
                System.out.println("      " + newTask.toString());
                curr++;
                System.out.println("    Now you have " + curr + " tasks in the list");
            } else if (input.equals("event")) {
                String task = sc.nextLine();
                String start = sc.nextLine();
                String end = sc.nextLine();
                Task newTask = new Event(task, start, end);
                taskList[curr] = newTask;
                System.out.println("    Got it! I've added this task:");
                System.out.println("      " + newTask.toString());
                curr++;
                System.out.println("    Now you have " + curr + " tasks in the list");
            }
            System.out.println("    --------------------------------------------------");
            input = sc.nextLine();
        }
        System.out.println("    Bye. Hope to see you again");
    }

    public static void list() {
        for(int i = 1; i < 100; i++) {
            if (taskList[i - 1] == null) {
                break;
            }
            String task = taskList[i - 1].toString();
            System.out.println("    " + i + ". " + task);
        }
    }
}

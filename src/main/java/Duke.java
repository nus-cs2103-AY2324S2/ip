import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Your Only Friend\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        Scanner whatToDo = new Scanner(System.in);
        Task task = new Task(whatToDo.nextLine().trim().toLowerCase());

        ArrayList<Task> listOfTasks = new ArrayList<>();

        while (!task.description.equals("bye")) {
            if (task.description.equals("list")) {
                System.out.println("____________________________________________________________\n");
                for (Integer i = 1; i <= listOfTasks.size(); i++) {
                    System.out.println(i.toString() + ".[" + listOfTasks.get(i-1).getStatusIcon() + "] "
                    + listOfTasks.get(i-1).description);
                }
                System.out.println("____________________________________________________________\n");
            } else if (task.isMark()) {
//                String[] items = task.description.split(" ");
//                int indToMark = Integer.parseInt(items[1]);
                listOfTasks.get(task.indexToMarkOrUnmark).markComplete();
                System.out.println("____________________________________________________________\n");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + listOfTasks.get(task.indexToMarkOrUnmark).getStatusIcon() + "] "
                        + listOfTasks.get(task.indexToMarkOrUnmark).description);
                System.out.println("____________________________________________________________\n");
            } else if (task.isUnmark()) {
//                String[] items = task.description.split(" ");
//                int indToMark = Integer.parseInt(items[1]);
                listOfTasks.get(task.indexToMarkOrUnmark).markIncomplete();
                System.out.println("____________________________________________________________\n");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + listOfTasks.get(task.indexToMarkOrUnmark).getStatusIcon() + "] "
                        + listOfTasks.get(task.indexToMarkOrUnmark).description);
                System.out.println("____________________________________________________________\n");
            } else {
                listOfTasks.add(task);
                System.out.println("____________________________________________________________\n");
                System.out.println("added: " + task.description);
                System.out.println("\n____________________________________________________________\n");
            }

            task = new Task(whatToDo.nextLine().trim().toLowerCase());
        }

        System.out.println("____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n -Your Only Friend\n"
                + "____________________________________________________________\n");

    }
}
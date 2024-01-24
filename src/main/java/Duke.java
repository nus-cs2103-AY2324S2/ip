import java.util.*;
import Task.Task;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String divider = "        ------------------------------------------------------------";

        System.out.println(divider);
        System.out.println("        Hello! I'm Chronos.");
        System.out.println("        What can I do for you?");
        System.out.println(divider);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        int noOfTasks = 0;

        while (true) {
            String command = sc.nextLine();
            String[] token = command.split(" ");
            Task task = new Task(command);

            if (token[0].equals("bye")) {
                System.out.println(divider);
                System.out.println("        Bye. Hope to see you again soon!");
                System.out.println(divider);
                break;
            } else if (token[0].equals("list")) {
                System.out.println(divider);
                System.out.println("        Here are the tasks in your list:");
                for (int i = 1; i < noOfTasks + 1; i++) {
                    Task currentTask = tasks.get(i - 1);
                    System.out.println("        " + i + ". [" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
                }
                System.out.println(divider);
            } else if (token[0].equals("mark")) {
                int selectedTaskNumber = Integer.parseInt(token[1]);
                Task selectedTask = tasks.get(selectedTaskNumber - 1);
                selectedTask.setMarked();
                tasks.set(selectedTaskNumber - 1, selectedTask);
                System.out.println(divider);
                System.out.println("        Nice! I've marked this task as done:");
                System.out.println("           [" + selectedTask.getStatusIcon() + "] " + selectedTask.getDescription());
                System.out.println(divider);
            } else if (token[0].equals("unmark")) {
                int selectedTaskNumber = Integer.parseInt(token[1]);
                Task selectedTask = tasks.get(selectedTaskNumber - 1);
                selectedTask.setUnmarked();
                tasks.set(selectedTaskNumber - 1, selectedTask);
                System.out.println(divider);
                System.out.println("        OK, I've marked this task as not done yet:");
                System.out.println("           [" + selectedTask.getStatusIcon() + "] " + selectedTask.getDescription());
                System.out.println(divider);
            } else {
                tasks.add(task);
                noOfTasks++;
                System.out.println(divider);
                System.out.println("        added: " + task.getDescription());
                System.out.println(divider);
            }
        }
    }
}
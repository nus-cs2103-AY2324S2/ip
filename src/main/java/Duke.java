import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printLine();
        System.out.println("Hello! I'm Bozo");
        System.out.println("What can I do for you?");
        printLine();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            if (input.equals("bye")) {
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            } else if (input.equals("list")){
                printLine();
                System.out.println("Here are the tasks in your list:");
                int counter = 1;
                for (Task task : list) {
                    System.out.println(counter + ". " + "[" + task.getStatusIcon() + "] " + task.description);
                    counter++;
                }
                printLine();
                input = sc.nextLine();
            } else if (input.startsWith("mark")) {
                printLine();
                String taskStr = input.substring(input.indexOf(" ") + 1);
                int taskNum = Integer.parseInt(taskStr);
                if (taskNum > 0 && taskNum < list.size() + 1) {
                    Task currentTask = list.get(taskNum - 1);
                    currentTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + "[" + currentTask.getStatusIcon() + "] " + currentTask.description);
                    printLine();
                    input = sc.nextLine();
                } else {
                    System.out.println("Error: Task does not exist!");
                    printLine();
                    input = sc.nextLine();
                }
            } else if (input.startsWith("unmark")) {
                printLine();
                String taskStr = input.substring(input.indexOf(" ") + 1);
                int taskNum = Integer.parseInt(taskStr);
                if (taskNum > 0 && taskNum < list.size() + 1) {
                    Task currentTask = list.get(taskNum - 1);
                    currentTask.markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + "[" + currentTask.getStatusIcon() + "] " + currentTask.description);
                    printLine();
                    input = sc.nextLine();
                } else {
                    System.out.println("Error: Task does not exist!");
                    printLine();
                    input = sc.nextLine();
                }
            } else {
                Task t = new Task(input);
                list.add(t);
                printLine();
                System.out.println("added: " + input);
                printLine();
                input = sc.nextLine();
            }
        }
    }

    public static void printLine() {
        System.out.println("_________________________________________________");
    }
}

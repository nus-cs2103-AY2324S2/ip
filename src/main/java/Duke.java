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
                    System.out.println(counter + ". " + task.toString());
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
                    System.out.println("  " + currentTask);
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
                    System.out.println("  " + currentTask);
                    printLine();
                    input = sc.nextLine();
                } else {
                    System.out.println("Error: Task does not exist!");
                    printLine();
                    input = sc.nextLine();
                }
            } else {
                printLine();
                if (input.startsWith("todo")) {
                    Todo td = new Todo(input.substring(input.indexOf(" ") + 1));
                    list.add(td);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + td);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } else if (input.startsWith("deadline")) {
                    int indexOfSlash = input.indexOf("/by");
                    Deadline d = new Deadline(input.substring(input.indexOf(" ")+ 1, indexOfSlash - 1),
                                                input.substring(indexOfSlash + 4));
                    list.add(d);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + d);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } else if (input.startsWith("event")) {
                    int indexOfFrom = input.indexOf("/from");
                    int indexOfTo = input.indexOf("/to");
                    Event e = new Event(input.substring(input.indexOf(" ")+ 1, indexOfFrom - 1),
                                          "from: " + input.substring(indexOfFrom + 6, indexOfTo - 1) + " ",
                                            "to: " + input.substring(indexOfTo + 4));
                    list.add(e);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + e);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");

                }
                printLine();
                input = sc.nextLine();
            }
        }
    }

    public static void printLine() {
        System.out.println("_________________________________________________");
    }
}

import java.util.*;
public class Duke {
    public static void main(String[] args) {
/*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
 */

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Doye\n" + "What can I do for you?");
        System.out.println("____________________________________________________________");

        List<Task> array = new ArrayList<>();

        while (true) {
            Scanner sc = new Scanner(System.in);
            String order = sc.nextLine();

            if (order.equals("end")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (order.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list: \n");
                for (int i = 0; i < array.size(); i++) {
                    Task addTask = array.get(i);
                    System.out.println((i + 1) + "." + "[" + addTask.getStatusIcon() + "] " + addTask.description);
                }
                System.out.println("____________________________________________________________");
            } else if (order.contains("unmark")) {
                String[] tokens = order.split(" ");
                //String command = tokens[0];
                int number = Integer.parseInt(tokens[1]);
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet: ");
                Task taskNotMark = array.get(number - 1);
                taskNotMark.markAsUnDone();
                System.out.println("[" + taskNotMark.getStatusIcon() + "] " + taskNotMark.description);
                System.out.println("____________________________________________________________");
            } else if (order.contains("mark")) {
                String[] tokens = order.split(" ");
                //String command = tokens[0];
                int number = Integer.parseInt(tokens[1]);
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done: ");
                Task taskMark = array.get(number - 1);
                taskMark.markAsDone();
                System.out.println("[" + taskMark.getStatusIcon() + "] " + taskMark.description);
                System.out.println("____________________________________________________________");
            } else {
                Task t = new Task(order);
                array.add(t);
                System.out.println("____________________________________________________________");
                System.out.println("added: " + t.description);
                System.out.println("____________________________________________________________");
            }
        }
    }
}





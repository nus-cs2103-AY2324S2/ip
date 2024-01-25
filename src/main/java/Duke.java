import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println("Hello! I'm tars.");
        System.out.println("What can I do for you?");

        while (true) {
            String comd = scanner.nextLine();

            if (comd.equals("bye")) {
                break;
            }
            if (comd.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + ". " + list.get(i));
                }
            }

            else if (comd.startsWith("todo ")) {
                list.add(new Todo(comd.substring(5)));
                System.out.println("Got it. I've added this task: ");
                System.out.println(list.get(list.size() - 1));
                System.out.println("Now you have " + list.size() + " tasks in the list.");

            }

            else if (comd.startsWith("deadline ")) {
                String[] data = comd.split("/");
                String task = data[0].substring(9);
                list.add(new Deadline(task, data[1].substring(3)));
                System.out.println("Got it. I've added this task: ");
                System.out.println(list.get(list.size() - 1));
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }

            else if (comd.startsWith("event ")) {
                String[] data = comd.split("/");
                String task = data[0].substring(6);
                list.add(new Event(task, data[1].substring(5), data[2].substring(3)));
                System.out.println("Got it. I've added this task: ");
                System.out.println(list.get(list.size() - 1));
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }


            else if(comd.startsWith("mark ")) {
                String[] res = comd.split(" ");
                String in = res[1];
                int index = Integer.parseInt(in);
                System.out.println(index);
                list.get(index - 1).mark();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(list.get(index - 1).printWithStatus());

            }

            else if (comd.startsWith("unmark ")) {
                String[] result = comd.split(" ");
                String in = result[1];
                int index = Integer.parseInt(in);
                list.get(index - 1).unmark();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println(list.get(index - 1).printWithStatus());
            }

            else {
                list.add(new Task(comd));
                System.out.println("added: "+ list.get(list.size() - 1));

            }


        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

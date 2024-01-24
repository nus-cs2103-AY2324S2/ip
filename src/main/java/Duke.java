import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        String logo = " ____ \n"
                    + "|  _ \\   ___   ___ \n"
                    + "| |_| | / _ \\ / _ \\\n"
                    + "| |_| | | __/ | __/\n"
                    + "|____/  \\___| \\___|\n";

        String msg = "------------------------------------------------ \n"
                + "Hello! I'm Bee! \n"
                + "What can I do for you? \n"
                + "------------------------------------------------";

        System.out.println(logo + "\n" + msg);

        String indent = "    ";

        boolean output = true;
        String input;
        ArrayList<Task> list = new ArrayList<>();

        while (output) {
            System.out.println(" ");
            input = sc.next();

                // end the program
            if (input.equals("bye")) {
                System.out.println(indent + "------------------------------------------------");
                System.out.println(indent + "Bye. Hope to see you again soon!");
                System.out.println(indent + "------------------------------------------------");
                output = false;

                // print the entire task list
            } else if (input.equals("list")) {
                System.out.println(indent + "------------------------------------------------");
                System.out.println(indent + "Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    Task t = list.get(i);
                    System.out.println(indent + (i + 1) + "." + t.toString());
                }
                System.out.println(indent + "------------------------------------------------");

                // mark an item in list
            } else if (input.equals("mark")) {
                System.out.println(indent + "------------------------------------------------");
                int position = sc.nextInt() - 1;
                Task t = list.get(position);
                if (t.getStatusIcon().equals(" ")) {
                    System.out.println(indent + "Nice! I've marked this task as done:");
                }
                t.markAsDone();
                System.out.println(indent + "  " +  t.toString());
                System.out.println(indent + "------------------------------------------------");

                // unmark an item in list
            } else if (input.equals("unmark")) {
                System.out.println(indent + "------------------------------------------------");
                int position = sc.nextInt() - 1;
                Task t = list.get(position);
                if (t.getStatusIcon().equals("X")) {
                    System.out.println(indent + "OK, I've marked this task as not done yet:");
                }
                t.markAsUndone();
                System.out.println(indent + "  " + t.toString());
                System.out.println(indent + "------------------------------------------------");

            } else if (input.equals("todo")) {
                System.out.println(indent + "------------------------------------------------");
                System.out.println(indent + "Got it. I've added this task:");
                String des = sc.nextLine().trim();
                Todo t = new Todo(des);
                list.add(t);
                System.out.println(indent + "  " + t.toString());
                System.out.println(indent + "Now you have " + list.size() + " tasks in the list.");
                System.out.println(indent + "------------------------------------------------");

            } else if (input.equals("deadline")) {
                System.out.println(indent + "------------------------------------------------");
                System.out.println(indent + "Got it. I've added this task:");
                String temp = sc.nextLine();
                String[] arrOfStr = temp.split("/by");
                String des = arrOfStr[0].trim();
                String by = arrOfStr[1].trim();
                Deadline t = new Deadline(des, by);
                list.add(t);
                System.out.println(indent + "  " + t.toString());
                System.out.println(indent + "Now you have " + list.size() + " tasks in the list.");
                System.out.println(indent + "------------------------------------------------");

            } else if (input.equals("event")) {
                System.out.println(indent + "------------------------------------------------");
                System.out.println(indent + "Got it. I've added this task:");
                String temp = sc.nextLine();
                String[] arrOfStr = temp.split("/");
                String des = arrOfStr[0].trim();
                String start = arrOfStr[1].substring(5).trim();
                String end = arrOfStr[2].substring(3).trim();
                Event t = new Event(des, start, end);
                list.add(t);
                System.out.println(indent + "  " + t.toString());
                System.out.println(indent + "Now you have " + list.size() + " tasks in the list.");
                System.out.println(indent + "------------------------------------------------");

                // echo the words
            } else {
                System.out.println(indent + "------------------------------------------------");
                String des = input + sc.nextLine();
                System.out.println(indent + des);
                System.out.println(indent + "------------------------------------------------");
            }
        }

        sc.close();
    }
}

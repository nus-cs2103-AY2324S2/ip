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

            if (input.equals("bye")) {
                System.out.println(indent + "------------------------------------------------");
                System.out.println(indent + "Bye. Hope to see you again soon!");
                System.out.println(indent + "------------------------------------------------");
                output = false;
            } else if (input.equals("list")) {
                System.out.println(indent + "------------------------------------------------");
                System.out.println(indent + "Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    Task t = list.get(i);
                    System.out.println(indent + (i + 1) + "." + t.toString());
                }
                System.out.println(indent + "------------------------------------------------");
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
            } else {
                System.out.println(indent + "------------------------------------------------");
                String des = input + sc.nextLine();
                System.out.println(indent + "added: " + des);
                System.out.println(indent + "------------------------------------------------");
                list.add(new Task(des));
            }
        }

        sc.close();
    }
}

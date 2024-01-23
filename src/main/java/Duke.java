import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    public static void main(String[] args) {
        String sayHi = "Helloo! I'm LilyBot ;)\nWhat's up'?\n";
        String divider = "_____________________________________________";
        String markDone = "Good job! I've marked this task as done: ";
        String markNotDone = "Okie, Rest well. Marked this task as not done yet: ";
        ArrayList<Task> ls = new ArrayList<>();
        int counter = 0;

        //Scanner for getting user input
        Scanner myCom = new Scanner(System.in);
        System.out.println(sayHi + divider);
        String command = myCom.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println("Here're the tasks in ur list:");
                for (Task i : ls) {
                    counter++;
                    System.out.println(counter + ". " +
                            "[" + i.getStatusIcon() + "] " + i.getDescription());
                }
                System.out.println(divider);
                command = myCom.nextLine();
            } else if (command.startsWith("unmark")) {
                int taskNum = Integer.valueOf(command.replaceAll("[^0-9]", ""));
                Task tk = ls.get(taskNum - 1);

                tk.unmark();
                System.out.println(markNotDone+"\n"+
                        "  [" + tk.getStatusIcon() + "] " + tk.getDescription());
                System.out.println(divider);
                command = myCom.nextLine();
            } else if (command.startsWith("mark")) {
                int taskNum = Integer.valueOf(command.replaceAll("[^0-9]", ""));
                Task tk = ls.get(taskNum - 1);

                tk.mark();
                System.out.println(markDone+"\n"+
                        "  [" + tk.getStatusIcon() + "] " + tk.getDescription());
                System.out.println(divider);
                command = myCom.nextLine();
            } else {
                Task t = new Task(command);
                ls.add(t);
                System.out.println("  added: "  + command + "\n" + divider);
                command = myCom.nextLine();
            }
        }


        if (command.equals("bye")) {
            String sayBye = "Bye Bye. See u later!\n";
            System.out.println(sayBye + divider);
        }

    }
}

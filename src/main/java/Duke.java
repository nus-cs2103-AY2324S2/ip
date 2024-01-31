import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Your Only Friend\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        Scanner whatToDo = new Scanner(System.in);
        String whatToDoCall = whatToDo.nextLine().trim().toLowerCase();

        ArrayList listOfTasks = new ArrayList();

        while (!whatToDoCall.equals("bye")) {
            if (whatToDoCall.equals("list")) {
                System.out.println("____________________________________________________________\n");
                for (Integer i = 1; i <= listOfTasks.size(); i++) {
                    System.out.println(i.toString() + ". " + listOfTasks.get(i-1));
                }
                System.out.println("____________________________________________________________\n");
            } else {
                listOfTasks.add(whatToDoCall);
                System.out.println("____________________________________________________________\n");
                System.out.println("added: " + whatToDoCall);
                System.out.println("\n____________________________________________________________\n");
            }

            whatToDoCall = whatToDo.nextLine().trim().toLowerCase();
        }

        System.out.println("____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n -Your Only Friend\n"
                + "____________________________________________________________\n");

    }
}
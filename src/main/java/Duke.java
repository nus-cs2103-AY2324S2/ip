import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Your Only Friend\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        Scanner whatToDo = new Scanner(System.in);
        String whatToDoCall = whatToDo.nextLine().trim().toLowerCase();
        String[] command = whatToDoCall.split(" ", 2);

        ArrayList<Task> listofTasks = new ArrayList<>();

        while (!whatToDoCall.equals("bye")) {
            if ((whatToDoCall.equals("list") | whatToDoCall.equals("List") | whatToDoCall.equals("LIST"))) {
                System.out.println("____________________________________________________________\n");
                System.out.println("Here are the tasks in your list:\n");
                for (Integer i = 1; i <= listofTasks.size(); i++) {
                    System.out.println(i.toString() + "." + listofTasks.get(i-1).toString());
                }
                System.out.println("____________________________________________________________\n");
            } else if (command[0].equals("mark")) {
                int indexToMark = Integer.parseInt(command[1]) - 1;

                listofTasks.get(indexToMark).markComplete();
                System.out.println("____________________________________________________________\n");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(listofTasks.get(indexToMark).toString());
                System.out.println("____________________________________________________________\n");
            } else if (command[0].equals("unmark")) {
                int indexToUnmark = Integer.parseInt(command[1]) - 1;
                listofTasks.get(indexToUnmark).markIncomplete();
                System.out.println("____________________________________________________________\n");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(listofTasks.get(indexToUnmark).toString());
                System.out.println("____________________________________________________________\n");
            } else {
                Task task = null;
                if (command[0].equals("todo")) {
                    task = new ToDo(command[1]);
                } else if (command[0].equals("deadline")) {
                    task = new Deadline(command[1]);
                } else if (command[0].equals("event")) {
                    task = new Event(command[1]);
                } else {
                    System.out.println("Invalid Input!");
                    break;
                }

                listofTasks.add(task);
                System.out.println("____________________________________________________________\n"
                        + "Got it. I've added this task:\n"
                        + task.toString() + "\n"
                        + "Now you have " + listofTasks.size() + (listofTasks.size() <= 1 ?  " task in the list." : " tasks in the list.")
                        + "\n____________________________________________________________\n");
            }

            whatToDoCall = whatToDo.nextLine().trim().toLowerCase();
            command = whatToDoCall.split(" ", 2);
        }

        System.out.println("____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n -Your Only Friend\n"
                + "____________________________________________________________\n");




    }
}

import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    public static void main(String[] args) {
        String sayHi = "Helloo! I'm LilyBot ;)\nWhat's up'?\n";
        String divider = "_____________________________________________";
        String markDone = "Good job! I've marked this task as done:";
        String markNotDone = "Okie, Rest well. Marked this task as not done yet: ";
        String added = "Got it. I've added this task:";
        String dkEmpty = "Oops! Sorry, I don't know what that means. Description is empty";
        ArrayList<Task> ls = new ArrayList<>();


        //Scanner for getting user input
        Scanner myCom = new Scanner(System.in);
        System.out.println(sayHi + divider);
        String command = myCom.nextLine();


        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println("Here're the tasks in ur list:");
                int counter = 0;
                for (Task i : ls) {
                    counter++;
                    System.out.println(counter + ". " + i.toString());
                }
                System.out.println(divider);
                command = myCom.nextLine();
            } else if (command.startsWith("unmark")) {
                int taskNum = Integer.valueOf(command.replaceAll("[^0-9]", ""));
                Task tk = ls.get(taskNum - 1);

                tk.unmark();
                System.out.println(markNotDone+"\n"+ "  " + tk.toString());
                System.out.println(divider);
                command = myCom.nextLine();
            } else if (command.startsWith("mark")) {
                int taskNum = Integer.valueOf(command.replaceAll("[^0-9]", ""));
                Task tk = ls.get(taskNum - 1);

                tk.mark();
                System.out.println(markDone+"\n"+ "  " + tk.toString());
                System.out.println(divider);
                command = myCom.nextLine();
            } else {
                String[] cmd = command.split(" ", 2);
                if (cmd[0].equals("todo")) {
                    try {
                        Task t = new ToDo(cmd[1]);
                        ls.add(t);
                        System.out.println("  " + added + "\n" +
                                "    " + t.toString() + "\n" +
                                "  Now u have " + ls.size() +
                                " tasks in the list." + "\n" + divider);
                        command = myCom.nextLine();
                    } catch (Exception exc) {
                        System.out.println(dkEmpty + "\n" + divider);
                        command = myCom.nextLine();
                    }
                }
                 else if (cmd[0].equals("deadline")) {
                     try {
                         String[] date = cmd[1].split("/by", 2);
                         try {
                             Task t = new Deadline(date[0], date[1]);
                             ls.add(t);
                             System.out.println("  " + added + "\n" +
                                     "    " + t.toString() + "\n" +
                                     "  Now u have " + ls.size() +
                                     " tasks in the list."+ "\n" + divider);
                             command = myCom.nextLine();
                         } catch (Exception exc) {
                             System.out.println("Plz enter a date for the deadline using '/by'\n" + divider);
                             command = myCom.nextLine();
                         }
                     } catch (Exception exc) {
                         System.out.println(dkEmpty + "\n" + divider);
                         command = myCom.nextLine();
                     }
                } else if (cmd[0].equals("event")) {
                     try {
                         String[] date = cmd[1].split("/", 3);
                         try {
                             Task t = new Event(date[0], date[1], date[2]);
                             ls.add(t);
                             System.out.println("  " + added + "\n" +
                                     "    " + t.toString() + "\n" +
                                     "  Now u have " + ls.size() +
                                     " tasks in the list."+ "\n" + divider);
                             command = myCom.nextLine();
                         } catch (Exception exc) {
                             System.out.println("Plz enter a date for the event using '/from' and '/to'\n" + divider);
                             command = myCom.nextLine();
                         }
                     } catch (Exception exc) {
                         System.out.println(dkEmpty + "\n" + divider);
                         command = myCom.nextLine();
                     }
                } else {
                    System.out.println("Oops! I don't understand the instruction." + "\n" + divider);
                    command = myCom.nextLine();
                }

            }
        }

        if (command.equals("bye")) {
            String sayBye = "Bye Bye. See u later!\n";
            System.out.println(sayBye + divider);
        }

    }
}

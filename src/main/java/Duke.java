import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws NoCmdException {
        String greet = "Hello! I'm DOUMMI\n" +
                "What can I do for you?";

        System.out.println(greet);


//        Task[] task = new Task[100];
        ArrayList<Task> task = new ArrayList<Task>();

        Scanner cmd = new Scanner(System.in);

        while (!cmd.hasNext("bye")) {
            if (cmd.hasNext("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < task.size(); i++) {
                    if (task.get(i) == null) {
                        break;
                    }
                    int n = i + 1;
                    System.out.println(n + ". " + task.get(i).toString());
                }
                String userCmd = cmd.nextLine();
            } else if (cmd.hasNext("mark")) {
                String userCmd = cmd.nextLine();
                String [] uCmd = userCmd.split(" ");
                int index = Integer.parseInt(uCmd[1]) - 1;
                task.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" +
                        "\t" + task.get(index).toString());
            } else if (cmd.hasNext("unmark")) {
                String userCmd = cmd.nextLine();
                String [] uCmd = userCmd.split(" ");
                int index = Integer.parseInt(uCmd[1]) - 1;
                task.get(index).unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" +
                        "\t" + task.get(index).toString());
            } else if (cmd.hasNext("todo")) {
                String userCmd = cmd.nextLine();
                try {
                    processToDos(userCmd, task);
                } catch (ToDosException e){
                    System.out.println("Sir, " + e.getMessage());
                }
            } else if (cmd.hasNext("event")) {
                String userCmd = cmd.nextLine();
                try {
                    processEvents(userCmd, task);
                } catch (EventException e){
                    System.out.println("Sir, " + e.getMessage());
                }
            } else if (cmd.hasNext("deadline")) {
                String userCmd = cmd.nextLine();
                try {
                    processDeadline(userCmd, task);
                } catch (DeadlineException e){
                    System.out.println("Sir, " + e.getMessage());
                }
            } else if (cmd.hasNext("delete")) {
                String userCmd = cmd.nextLine();
                String [] uCmd = userCmd.split(" ");
                int index = Integer.parseInt(uCmd[1]) - 1;
                Task removed = task.remove(index);
                String length = "" + task.size();
                System.out.println("Noted. I've removed this task:");
                System.out.println(removed.toString());
                System.out.println("Now you have " + length + " tasks in the list.");

            } else {
                throw new NoCmdException("Please tell me what you want me to do.");
            }
        }

        String bye = "Hope to see you again soon!";

        System.out.println(bye);
    }

    public static void processToDos(String cmd, ArrayList<Task> task) throws ToDosException{
        String [] divided = cmd.split(" ", 2);
        if (divided.length < 2) {
            throw new ToDosException("What todos do you need to record?");
        }
        String D = divided[1];
        Task new_task = new ToDos(D);
        task.add(new_task);
        String length = "" + task.size();
        System.out.println("Got it. I've added this task:\n" +
                "\t" + new_task.toString());
        System.out.println("Now you have " + length + " tasks in the list.");
    }

    public static void processDeadline(String cmd, ArrayList<Task> task) throws DeadlineException{
        String [] divided = cmd.split(" ",2);
        if (divided.length < 2) {
            throw new DeadlineException("What deadline do you need to record?");
        }
        String D = divided[1];
        divided = D.split("/by", 2);
        if (divided.length < 2) {
            throw new DeadlineException("When do you have to get it done");
        }
        D = divided[0];
        String by = divided[1];
        Task new_task =  new Deadline(D, by);
        task.add(new_task);
        String length = "" + task.size();
        System.out.println("Got it. I've added this task:\n" +
                "\t" + new_task.toString());
        System.out.println("Now you have " + length + " tasks in the list.");
    }

    public static void processEvents(String cmd, ArrayList<Task> task) throws EventException{
        String [] divided = cmd.split(" ", 2);
        if (divided.length < 2) {
            throw new EventException("What event do you need to record?");
        }
        String D = divided[1];
        divided = D.split("/from", 2);
        if (divided.length < 2) {
            throw new EventException("There is no event timeline!");
        }
        D = divided[0];
        String fromTo = divided[1];
        divided = fromTo.split("/to", 2);
        if (divided.length < 2) {
            throw new EventException("There is no event timeline!");
        }
        String from = divided[0];
        String to = divided[1];
        Task new_task = new Events(D, from, to);
        task.add(new_task);
        String length = "" + task.size();
        System.out.println("Got it. I've added this task:\n" +
                "\t" + new_task.toString());
        System.out.println("Now you have " + length + " tasks in the list.");
    }
}

import java.io.*;
import java.util.*;
public class Duke {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("================================ \n" +
                "Hello I'm Axolotl! \n" +
                "What can I do for you? \n" +
                "================================ \n");

        String input = sc.nextLine();
        ArrayList<Task> list = new ArrayList<>();

        while (!input.equals("bye")) {
            int cmdSplit = input.indexOf(" ");
            String command = input;
            String task = null;
            if (cmdSplit != -1) {
                command = input.substring(0, cmdSplit);
                task = input.substring(input.indexOf(" ") + 1);
            }
//            String command = input.substring(0, cmdSplit);
//            String task = (input.length() - 1) == cmdSplit ? null : input.substring(input.indexOf(" ") + 1);
            if (command.equals("list")) {
                System.out.println( "-------------------------------- \n" +
                                    "Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    Task iTask = list.get(i);
                    System.out.println((i+1) + ". " + iTask.toString());
                }
                System.out.println( "-------------------------------- \n");
            }
            else if (command.equals("mark")) {
                int taskNo = Integer.parseInt(task) - 1;
                Task dTask = list.get(taskNo);
                dTask.done();

                System.out.println("-------------------------------- \n" +
                        "Nice! I've marked task " + taskNo + " as done: \n" +
                        dTask.toString() + "\n" +
                        "-------------------------------- \n");
            }
            else if (command.equals("unmark")) {
                int taskNo = Integer.parseInt(task) - 1;
                Task uTask = list.get(taskNo);
                uTask.undone();

                System.out.println("-------------------------------- \n" +
                        "Sure, I've marked task " + taskNo + " as not done yet: \n" +
                        uTask.toString() + "\n" +
                        "-------------------------------- \n");
            }
            else if (command.equals("todo") | command.equals("deadline") | command.equals("event")) {
                Task t = null;
                switch(command) {
                    case "todo":
                        t = new ToDo(task);
                        break;
                    case "deadline":
                        String deadline[] = task.split(" /by ");
                        t = new Deadline(deadline[0], deadline[1]);
                        break;
                    case "event":
                        String event = task.substring(0, task.indexOf(" /from "));
                        String from = task.substring(task.indexOf("/from ") + 6, task.indexOf(" /to "));;
                        String to = task.substring((task.indexOf("/to ") + 4));
                        t = new Event(event, from, to);
                        break;

                }
                list.add(t);
                System.out.println( "-------------------------------- \n" +
                        "Sure, I've added this task: \n" +
                        t.toString() + "\n" +
                        "Now you have " + list.size() + " task(s) in the list. \n" +
                        "-------------------------------- \n");
            }

            input = sc.nextLine();
        }

        System.out.println("================================ \n" +
                "Bye. Hope to see you again soon! \n" +
                "================================ \n");
    }
}

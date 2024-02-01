import java.util.*;

public class Duke {
    public static void main(String[] args) throws DukeException{
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Your Only Friend\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        Scanner whatToDo = new Scanner(System.in);
        String whatToDoCall = whatToDo.nextLine().trim().toLowerCase();
        String[] command = whatToDoCall.split(" ", 2);

        ArrayList<Task> listOfTasks = new ArrayList<>();
        try {
            while (!whatToDoCall.equals("bye")) {
                if ((whatToDoCall.equals("list") | whatToDoCall.equals("List") | whatToDoCall.equals("LIST"))) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println("Here are the tasks in your list:\n");
                    for (Integer i = 1; i <= listOfTasks.size(); i++) {
                        System.out.println(i.toString() + "." + listOfTasks.get(i - 1).toString());
                    }
                    System.out.println("____________________________________________________________\n");
                } else if (command[0].equals("mark")) {
                    int indexToMark = Integer.parseInt(command[1]) - 1;

                    listOfTasks.get(indexToMark).markComplete();
                    System.out.println("____________________________________________________________\n");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(listOfTasks.get(indexToMark).toString());
                    System.out.println("____________________________________________________________\n");
                } else if (command[0].equals("unmark")) {
                    int indexToUnmark = Integer.parseInt(command[1]) - 1;
                    listOfTasks.get(indexToUnmark).markIncomplete();
                    System.out.println("____________________________________________________________\n");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(listOfTasks.get(indexToUnmark).toString());
                    System.out.println("____________________________________________________________\n");
                } else {
                    if (!command[0].equals("todo") && !command[0].equals("deadline") && !command[0].equals("event")) {
                        throw new DukeException("____________________________________________________________\n" +
                                "OOPS! Turns out Your Only Friend does not know what that is :(\n" +
                                "____________________________________________________________\n");
                    }
                    Task task = null;
                    if (command.length <= 1) {
                        throw new DukeException("____________________________________________________________\n" +
                                "OOPS! Your Only Friend cannot take in an empty " + command[0] +
                                "\nMake sure " + command[0] + "has a description!\n" +
                                "____________________________________________________________\n");
                    }
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

                    listOfTasks.add(task);
                    System.out.println("____________________________________________________________\n"
                            + "Got it. I've added this task:\n"
                            + task.toString() + "\n"
                            + "Now you have " + listOfTasks.size() + (listOfTasks.size() <= 1 ? " task in the list." : " tasks in the list.")
                            + "\n____________________________________________________________\n");
                }

                whatToDoCall = whatToDo.nextLine().trim().toLowerCase();
                command = whatToDoCall.split(" ", 2);
            }
        } catch (DukeException exception) {
            System.out.println(exception.getMessage());
        }

        System.out.println("____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n - Your Only Friend\n"
                + "____________________________________________________________\n");




    }
}

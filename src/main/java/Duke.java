import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("--------------------------");
            System.out.println("Welcome!! I'm Belle <3.");
            System.out.println("What can I do for you?");
            System.out.println("--------------------------");

            boolean exit = false;
            String input = "";
            ArrayList<Task> list = new ArrayList<>();
            Task curr;

            while (!exit) {
                input = sc.nextLine();
                String[] inputlist = input.split(" ");
                if (input.equals("bye")) {
                    exit = true;
                    break;
                }
                if (input.equals("list")) {
                    System.out.println("--------------------------");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(String.valueOf(i+1) + "." + list.get(i).toString());
                    }
                    System.out.println("--------------------------");
                    continue;
                } else if (inputlist[0].equals("mark")) {
                    String index = inputlist[1];
                    Task doingtask = list.get(Integer.valueOf(index)-1);
                    doingtask.dotask();
                    System.out.println("--------------------------");
                    System.out.println("Nice! I have marked this task as done:");
                    System.out.println("[X] "+ doingtask.getName());
                    System.out.println("--------------------------");
                    continue;
                } else if (inputlist[0].equals("unmark")) {
                    String index = inputlist[1];
                    Task doingtask = list.get(Integer.valueOf(index)-1);
                    doingtask.undotask();
                    System.out.println("--------------------------");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[] "+ doingtask.getName());
                    System.out.println("--------------------------");
                    continue;
                } else if (inputlist[0].equals("todo")) {
                    if (input.equals("todo")) {
                        throw new NoTaskNameException("");
                    }
                    curr = new TodoTask(input.substring(5), false);
                    System.out.println("--------------------------");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(curr.toString());
                    System.out.println("Now you have " + Task.getTotalTask()+ " tasks in the list.");
                    System.out.println("--------------------------");
                    list.add(curr);
                } else if (inputlist[0].equals("deadline")) {
                    if (input.equals("deadline")) {
                        throw new NoTaskNameException("");
                    }
                    String[] deadlinelist = input.substring(9).split(" /by ");
                    curr = new DeadlineTask(deadlinelist[0], false, deadlinelist[1]);
                    System.out.println("--------------------------");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(curr.toString());
                    System.out.println("Now you have " + Task.getTotalTask()+ " tasks in the list.");
                    System.out.println("--------------------------");
                    list.add(curr);
                } else if (inputlist[0].equals("event")) {
                    if (input.equals("event")) {
                        throw new NoTaskNameException("");
                    }
                    String[] eventlist = input.substring(6).split(" /from ");
                    String[] startend = eventlist[1].split(" /to ");
                    curr = new EventTask(eventlist[0], false, startend[0], startend[1]);
                    System.out.println("--------------------------");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(curr.toString());
                    System.out.println("Now you have " + Task.getTotalTask()+ " tasks in the list.");
                    System.out.println("--------------------------");
                    list.add(curr);
                } else if (inputlist[0].equals("delete")) {
                    String index = inputlist[1];
                    Task deletetask = list.get(Integer.valueOf(index)-1);
                    list.remove(Integer.parseInt(index) - 1);
                    Task.subtractTotal();
                    System.out.println("--------------------------");
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(deletetask.toString());
                    System.out.println("Now you have "+ Task.getTotalTask() + " tasks in the list.");
                    System.out.println("--------------------------");
                    System.out.println(list.size());
                    continue;
                } else {
                    System.out.println("--------------------------");
                    throw new InvalidCommandException("");
                }

            }

            System.out.println("--------------------------");
            System.out.println("Till next time!! Goodbye.");
            System.out.println("--------------------------");
        } catch (InvalidCommandException e) {
            System.out.println("Belle does not accept this command");
        } catch (NoTaskNameException e) {
            System.out.println("Task name is missing");
        }

    }
}

//can add more exceptions
// mark doesnt have relevant number

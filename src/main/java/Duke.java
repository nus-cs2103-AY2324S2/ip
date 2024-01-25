import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<>();
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            boolean exit = false;
            String input = "";
            greet();

            while (!exit) {
                input = sc.nextLine();
                String[] inputlist = input.split(" ");

                if (input.equals("bye")) {
                    exit = true;
                    break;
                } else if (input.equals("list")) {
                    System.out.println("--------------------------");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(String.valueOf(i+1) + "." + list.get(i).toString());
                    }
                    System.out.println("--------------------------");
                    continue;
                } else if (inputlist[0].equals("mark")) {
                    mark(inputlist[1]);
                    continue;
                } else if (inputlist[0].equals("unmark")) {
                    unmark(inputlist[1]);
                    continue;
                } else if (inputlist[0].equals("todo") || inputlist[0].equals("deadline") || inputlist[0].equals("event")) {
                    if (inputlist[0].equals("todo")) {
                        addTaskList("todo", input);
                    } else if (inputlist[0].equals("deadline")) {
                        addTaskList("deadline", input);
                    } else {
                        addTaskList("event", input);
                    }
                    continue;
                } else if (inputlist[0].equals("delete")) {
                    delete(inputlist[1]);
                    continue;
                } else {
                    System.out.println("--------------------------");
                    throw new BelleException("This is not a valid command that I can recognise :(");
                }
            }
            System.out.println("--------------------------");
            System.out.println("Till next time!! Goodbye.");
            System.out.println("--------------------------");
            sc.close();
        } catch (BelleException e) {
            System.out.println(e.toString());
        }
    }

    public static void addTaskList(String type, String msg) throws BelleException {
        Task curr;
        if (type.equals("todo")) {
            try {
                curr = new TodoTask(msg.substring(5), false);
            } catch (StringIndexOutOfBoundsException e) {
                throw new BelleException("You did not specify a title for this todo task");
            }
        } else if (type.equals("deadline")) {
            try {
                String[] deadlinelist = msg.substring(9).split(" /by ");
                curr = new DeadlineTask(deadlinelist[0], false, deadlinelist[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new BelleException("You did not specify all the required information for deadline task.");
            } catch (StringIndexOutOfBoundsException e) {
                throw new BelleException("You did not specify all the required information for deadline task.");
            }
        } else {
            try {
                String[] eventlist = msg.substring(6).split(" /from ");
                String[] startend = eventlist[1].split(" /to ");
                curr = new EventTask(eventlist[0], false, startend[0], startend[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new BelleException("You did not specify all the required information for deadline task.");
            } catch (StringIndexOutOfBoundsException e) {
                throw new BelleException("You did not specify all the required information for deadline task.");
            }
        }
        System.out.println("--------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println(curr.toString());
        System.out.println("Now you have " + Task.getTotalTask()+ " tasks in the list.");
        System.out.println("--------------------------");
        list.add(curr);
    }

    public static void greet() {
        System.out.println("--------------------------");
        System.out.println("Welcome!! I'm Belle <3.");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------");
    }

    public static void mark(String index) throws BelleException {
        try {
            Task doingtask = list.get(Integer.valueOf(index)-1);
            doingtask.dotask();
            System.out.println("--------------------------");
            System.out.println("Nice! I have marked this task as done:");
            System.out.println(doingtask.toString());
            System.out.println("--------------------------");
        } catch (IndexOutOfBoundsException e){
            throw new BelleException("This is not a valid number in my task list :(");
        }
    }

    public static void unmark(String index) throws BelleException {
        try {
            Task doingtask = list.get(Integer.valueOf(index)-1);
            doingtask.undotask();
            System.out.println("--------------------------");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(doingtask.toString());
            System.out.println("--------------------------");
        } catch (IndexOutOfBoundsException e){
            throw new BelleException("This is not a valid number in my task list :(");
        }
    }

    public static void delete(String index) throws BelleException{
        try {
            Task deletetask = list.get(Integer.valueOf(index)-1);
            list.remove(Integer.parseInt(index) - 1);
            Task.subtractTotal();
            System.out.println("--------------------------");
            System.out.println("Noted. I've removed this task:");
            System.out.println(deletetask.toString());
            System.out.println("Now you have "+ Task.getTotalTask() + " tasks in the list.");
            System.out.println("--------------------------");
            System.out.println(list.size());
        } catch (IndexOutOfBoundsException e){
            throw new BelleException("This is not a valid number in my task list :(");
        }
    }
}

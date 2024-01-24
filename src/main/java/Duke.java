import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String WELCOME = "   Hi, I'm Gronk!\n"
            + "   What are we up to today?";
    public static String GOODBYE = "   System closing. Goodbye!";
    public static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void lines() { // prints out a line
        System.out.println("  ----------------------------------------");
    }

    public static void printMessage(String message) { // the system sends a message
        lines();
        System.out.println(message);
        lines();
    }

    public static void reciteList() {
        try {
            if (tasks.size() == 0) {
                throw new EmptyListException();
            }
            String m = "";
            for (int j = 0; j < tasks.size(); j++) {
                m += "   " + Integer.toString(j + 1) + ". " + tasks.get(j).toString() + "\n";
            }
            printMessage(m.substring(0, m.length() - 1));
        } catch (EmptyListException e) {
            printMessage(e.toString());
        }
    }

    public static void parseMessage(String m) {
        try {
            if (m.equals("todo") || m.equals("deadline") || m.equals("event")) {
                throw new EmptyDescException();
            } else if (m.startsWith("todo")) {
                String t = m.substring(5);
                if (t.equals("")) {
                    throw new EmptyDescException();
                }
                printMessage("   added task: " + t);
                tasks.add(new Todo(t, 0));
            } else if (m.startsWith("deadline")) {
                String[] t = m.substring(9).split(" /by");
                if (t[1].equals("")) {
                    throw new EmptyDescException();
                }
                printMessage("   added deadline: " + t[0]);
                tasks.add(new Deadline(t[0], 0, t[1]));
            } else if (m.startsWith("event")) {
                String[] t1 = m.substring(6).split(" /from");
                if (t1[1].equals("")) {
                    throw new EmptyDescException();
                }
                String[] t2 = t1[1].split(" /to");
                if (t2[0].equals("") || t2[1].equals("")) {
                    throw new EmptyDescException();
                }
                printMessage("   added event: " + t1[0]);
                tasks.add(new Event(t1[0], 0, t2[0], t2[1]));
            } else {
                throw new DukeException();
            }
        } catch (DukeException e) {
            printMessage(e.toString());
        } catch (EmptyDescException e) {
            printMessage(e.toString());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String message = "";
        printMessage(WELCOME);

        while (true) {
            message = sc.nextLine();
            if (message.equals("bye")) {
                break;
            } else if (message.equals("list")) {
                reciteList();
            } else if (message.startsWith("mark")) {
                String[] t = message.split(" ");
                int ind = Integer.parseInt(t[1]) - 1;
                printMessage(tasks.get(ind).statusMessage());
                tasks.get(ind).flip();
            } else if (message.startsWith("delete")) {
                String[] t = message.split(" ");
                int ind = Integer.parseInt(t[1]) - 1;
                printMessage("   Item: " + tasks.get(ind).getDesc() + " removed from list.");
                tasks.remove(ind);
            } else {
                parseMessage(message);
            }
        }

        printMessage(GOODBYE);
    }
}

import java.util.Scanner;

public class Duke {
    public static String WELCOME = "   Hi, I'm Gronk!\n"
            + "   What are we up to today?";
    public static String GOODBYE = "   System closing. Goodbye!";
    public static Task[] tasks = new Task[100];
    public static int i = 0;

    public static void lines() { // prints out a line
        System.out.println("  ----------------------------------------");
    }

    public static void printMessage(String message) { // the system sends a message
        lines();
        System.out.println(message);
        lines();
    }

    public static void reciteList() {
        if (i == 0) {
            printMessage("   List empty! Nothing added yet!");
        } else if (i >= 100) {
            printMessage("   oh no! too many tasks!");
        } else {
            String m = "";
            for (int j = 0; j < i; j++) {
                m += "   " + Integer.toString(j + 1) + ". " + tasks[j].toString() + "\n";
            }
            printMessage(m.substring(0, m.length() - 1));
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
                tasks[i] = new Todo(t, 0);
                i += 1;
            } else if (m.startsWith("deadline")) {
                String[] t = m.substring(9).split(" /by");
                if (t[1].equals("")) {
                    throw new EmptyDescException();
                }
                printMessage("   added deadline: " + t[0]);
                tasks[i] = new Deadline(t[0], 0, t[1]);
                i += 1;
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
                tasks[i] = new Event(t1[0], 0, t2[0], t2[1]);
                i += 1;
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
                printMessage(tasks[ind].statusMessage());
                tasks[ind].flip();
            } else {
                parseMessage(message);
            }
        }

        printMessage(GOODBYE);
    }
}

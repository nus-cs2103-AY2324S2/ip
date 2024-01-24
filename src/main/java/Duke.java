import java.util.Scanner;

public class Duke {
    public static String WELCOME = "   Hi, I'm Gronk! \uD83D\uDDFF \n"
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
            printMessage("   nothing added yet!");
        } else {
            String lst = "";
            for (int j = 0; j < i; j++) {
                lst += "   " + Integer.toString(j + 1) + ". " + tasks[j].toString() + "\n";
            }
            printMessage(lst.substring(0, lst.length() - 2));
        }
    }

    public static void parseMessage(String m) {

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
                if (tasks[ind].getStatus() == 0) {
                    tasks[ind].update(1);
                    System.out.println("   Well done! Task: " + tasks[ind].getDesc() + " completed.");
                } else if (tasks[ind].getStatus() == 1) {
                    tasks[ind].update(0);
                    System.out.println("   Task updated. Task: " + tasks[ind].getDesc() + " is incomplete.");
                }
            } else if (message.startsWith("todo")) {
                String t = message.substring(5);
                printMessage("   added task: " + t);
                tasks[i] = new Todo(t, 0);
                i += 1;
            } else if (message.startsWith("deadline")) {
                String[] t = message.substring(9).split("/by");
                printMessage("   added deadline: " + t[0]);
                tasks[i] = new Deadline(t[0], 0, t[1]);
                i += 1;
            } else if (message.startsWith("event")) {
                String[] t1 = message.substring(6).split("/from");
                String[] t2 = t1[1].split("/to");
                printMessage("   added event: " + t1[0]);
                tasks[i] = new Event(t1[0], 0, t2[0], t2[1]);
                i += 1;
            }
        }

        printMessage(GOODBYE);
    }
}

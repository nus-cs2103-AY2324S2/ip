import java.util.Scanner;

public class Duke {
    public static String WELCOME = "   Hi, I'm Gronk! \uD83D\uDDFF \n"
            + "   What are we up to today?";

    public static String GOODBYE = "   System closing. Goodbye!";

    public static void lines() {
        System.out.println("  ----------------------------------------");
    }

    public static void printMessage(String message) {
        System.out.println(message);
        lines();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String message = "";
        lines();
        printMessage(WELCOME);
        Task[] tasks = new Task[100];
        int i = 0;
        while (true) {
            message = sc.nextLine();
            lines();
            if (message.equals("bye")) {
                break;
            } else if (message.equals("list")) {
                if (i == 0) {
                    System.out.println("   nothing added yet!");
                } else {
                    for (int j = 0; j < i; j++) {
                        System.out.println("   "
                                + Integer.toString(j + 1)
                                + ". " + tasks[j].toString());
                    }
                }
                lines();
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
            } else {
                printMessage("   added: " + message);
                tasks[i] = new Task(message, 0);
                i += 1;
            }
        }
        printMessage(GOODBYE);
    }
}

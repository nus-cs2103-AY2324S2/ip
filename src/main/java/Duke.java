import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String spacer = "    ____________________________________________________________\n";
    private static ArrayList<Task> toDoList = new ArrayList<>();

    private static void startupMessage() {
        String name = "CBBW";
        System.out.println(spacer + "    Hello! I'm " + name 
                           + "\n    What can I do for you?\n" + spacer);
    }

    private static void goodbyeMessage() {
        System.out.println(spacer + "    Bye. Hope to see you again soon!\n" + spacer);
    }

    private static void botPrint(String s) {
        s = s.replace("\n", "\n    ");
        System.out.println(spacer + "    " + s + "\n" + spacer);
    }

    private static void printList() {
        System.out.println(spacer);
        for (int i = 1; i <= toDoList.size(); i++) {
            System.out.println("    " + i + "." + toDoList.get(i - 1) + "\n");
        }
        System.out.println(spacer);
    }


    private static void markTask(int i) {
        if (i > 0 && i <= toDoList.size()) {
            Task t = toDoList.get(i - 1);
            t.doTask();
            botPrint("Nice! I've marked this task as done:\n  " + t);
        } else {
            botPrint("Invalid Index for current list");
        }
    }

    private static void unmarkTask(int i) {
        if (i > 0 && i <= toDoList.size()) {
            Task t = toDoList.get(i - 1);
            t.undoTask();
            botPrint("OK, I've marked this task as not done yet:\n  " + t);
        } else {
            botPrint("Invalid Index for current list");
        }
    }

    public static void main(String[] args) {
        startupMessage();
        Scanner s = new Scanner(System.in);
        while (true) {
            String echo = s.nextLine();
            String[] echoSplit = echo.split(" ");

            if (echo.equals("bye")) {
                s.close();
                break;
            } else if (echo.equals("list")) {
                printList();
                continue;
            } else if (echoSplit[0].equals("mark")) {
                markTask(Integer.parseInt(echoSplit[1]));
                continue;
            } else if (echoSplit[0].equals("unmark")) {
                unmarkTask(Integer.parseInt(echoSplit[1]));
                continue;
            }

            toDoList.add(new Task(echo));
            botPrint("added: " + echo);
        }
        goodbyeMessage();
    }
}

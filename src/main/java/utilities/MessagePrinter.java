package utilities;

import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

//Delegate printing to this class
public class MessagePrinter {
    public String message;
    public MessagePrinter(String message) {
        this.message = message;
    }

    public void printStored() {
        System.out.println(this.message);
    }

    //Help check the nature of the message to have more personalised replies
    public static void personalisedReply(String message) {
        MessagePrinter.printLine();
        HashMap<String, String> responseMap = new HashMap<>();
        responseMap.put("byee", "You meant bye?");
        responseMap.put("byeee", "You meant bye?");
        responseMap.put("hi", "Hi again!");
        if (responseMap.containsKey(message)) {
            System.out.println(responseMap.get(message));
        } else {
            System.out.println(message);
        }
        MessagePrinter.printLine();
    }
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void greeting(String chatBotName) {
        MessagePrinter.printLine();
        System.out.println("Hello! I'm " + chatBotName + "\nWhat can I do for you?");
        MessagePrinter.printLine();
    }

    public static void bye() {
        MessagePrinter.printLine();
        System.out.println("Goodbye. Hope to see you again!");
        MessagePrinter.printLine();
    }

    public static void commandPrint(String command) {
        MessagePrinter.printLine();
        System.out.println(command);
        MessagePrinter.printLine();
    }

    public static void commandPrint(Task task, int size) {
        MessagePrinter.printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        MessagePrinter.printLine();
    }
    public static void commandListPrint(ArrayList<Task> list) {
        MessagePrinter.printLine();
        for (int i = 0; i < list.size(); i += 1) {
            System.out.print(i+1 + ". ");
            System.out.println(list.get(i));
        }
        MessagePrinter.printLine();
    }

    public static void markActionPrint(String action, Task task) {
        MessagePrinter.printLine();
        if (action.equals("mark")) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(task);
        MessagePrinter.printLine();
    }

    public static void errorPrinter(Exception e) {
        MessagePrinter.printLine();
        System.out.println(e.getMessage());
        MessagePrinter.printLine();
    }

    public static void removePrinter(Task task, int size) {
        size-=1;
        MessagePrinter.printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        MessagePrinter.printLine();
    }

    public static void printFoundTasks(ArrayList<Task> listOfFoundTasks) {
        MessagePrinter.printLine();
        if (listOfFoundTasks.isEmpty()) {
            System.out.println("No such tasks in the list :(, try again!");
        } else {
            System.out.println("Found! Here they are!");
            for (int i = 0; i < listOfFoundTasks.size(); i += 1) {
                System.out.println(i+1 + "." + listOfFoundTasks.get(i));
            }
        }
        MessagePrinter.printLine();
    }

}

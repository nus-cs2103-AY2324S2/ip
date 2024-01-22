
import java.util.Scanner;
public class Lamball {
    String indent = "    ____________________________________________________________\n";
    String welcomeMessage = "    ____________________________________________________________\n" +
            "     Hello! I'm Lamball!\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________\n";
    String goodbyeMessage = "    ____________________________________________________________\n" +
            "     See you again!\n" +
            "    ____________________________________________________________\n";

    String[] tasks;
    int counter;
    public Lamball() {
        tasks = new String[100];
        counter = 0;
    }

    public void greetingMessage() {
        System.out.println(this.welcomeMessage);
    }

    public void printList() {
        System.out.println(indent);
        for(int i = 0; i < counter; i++) {
            System.out.println("    " + (i + 1) + ". " + tasks[i] + "\n");
        }
        System.out.println(indent);
    }

    public boolean parse(String msg) {
        String[] parts = msg.split(" ", 2);
        switch(parts[0]) {
            case "bye":
                System.out.println(goodbyeMessage);
                return false;
            case "list":
                printList();
                return true;
            default:
                System.out.println(indent + "    added:" + msg + "\n" + indent);
                tasks[counter] = msg;
                counter++;
                return true;
        }
    }
}

import java.util.Scanner;
public class Lamball {
    String indent = "    ____________________________________________________________\n";
    String welcomeMessage = "    ____________________________________________________________\n" +
            "     Hello! I'm Lamball, your helpful sheep!\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________\n";
    String goodbyeMessage = "    ____________________________________________________________\n" +
            "     See you again!\n" +
            "    ____________________________________________________________\n";

    Task[] tasks;
    int counter;
    public Lamball() {
        tasks = new Task[100];
        counter = 0;
    }

    public void greetingMessage() {
        System.out.println(this.welcomeMessage);
    }

    public void printList() {
        System.out.println(indent);
        for(int i = 0; i < counter; i++) {
            System.out.println("    " + (i + 1) + ". " + tasks[i].toString() + "");
        }
        System.out.println(indent);
    }

    public boolean parse(String msg) {
        String[] parts = msg.split(" ", 2);
        Task temp;
        int idx;
        switch(parts[0]) {
            case "mark":
                System.out.println(indent + "    I have maaarked the task as done:\n");
                idx = Integer.valueOf(parts[1]) - 1;
                temp = tasks[idx];
                temp.mark();
                System.out.println("        "+ temp.toString() + "\n" + indent);
                return true;
            case "unmark":
                System.out.println(indent + "    I have maaarked the task as undone:\n" + indent);
                idx = Integer.valueOf(parts[1]) - 1;
                temp = tasks[idx];
                temp.unMark();
                System.out.println("        " + temp.toString() + "\n" + indent);
                return true;
            case "bye":
                System.out.println(goodbyeMessage);
                return false;
            case "list":
                printList();
                return true;
            default:
                System.out.println(indent + "    added:" + msg + "\n" + indent);
                tasks[counter] = new Task(msg);
                counter++;
                return true;
        }
    }
}
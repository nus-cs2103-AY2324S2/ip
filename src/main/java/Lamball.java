
import java.util.Scanner;
import java.util.ArrayList;
public class Lamball {
    String indent = "    ____________________________________________________________\n";
    String welcomeMessage = "    ____________________________________________________________\n" +
            "     Hello! I'm Lamball, your helpful sheep!\n" +
            "     Whaaat can I do for you?\n" +
            "    ____________________________________________________________\n";
    String goodbyeMessage = "    ____________________________________________________________\n" +
            "     See you again!\n" +
            "    ____________________________________________________________\n";

    ArrayList<Task> tasks;
    public Lamball() {
        tasks = new ArrayList<>();
    }

    public void greetingMessage() {
        System.out.println(this.welcomeMessage);
    }

    public void printList() {
        System.out.println(indent);
        System.out.println("    Here aaaaare the taaaasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i).toString() + "");
        }
        System.out.println(indent);
    }

    public boolean parse(String msg) {
        String[] parts = msg.split(" ", 2);
        int idx;
        switch(parts[0]) {
            case "mark": {
                System.out.println(indent + "    I have maaarked the task as done:\n");
                idx = Integer.valueOf(parts[1]) - 1;
                Task temp = tasks.get(idx);
                temp.mark();
                System.out.println("        " + temp.toString() + "\n" + indent);
                return true;
            }
            case "unmark": {
                System.out.println(indent + "    I have maaarked the task as undone:\n" + indent);
                idx = Integer.valueOf(parts[1]) - 1;
                Task temp = tasks.get(idx);
                temp.unMark();
                System.out.println("        " + temp.toString() + "\n" + indent);
                return true;
            }
            case "bye":
                System.out.println(goodbyeMessage);
                return false;
            case "list":
                printList();
                return true;
            case "todo": {
                Task temp = new ToDo(parts[1]);
                tasks.add(temp);
                System.out.println(indent + "    Added ToDo:\n        " + temp.toString() + "\n    Now you have " + tasks.size() + " tasks in the list.\n" + indent);
                return true;
            }
            case "event": {
                String[] furtherSplit = parts[1].split("/", 3);
                Task temp = new Event(furtherSplit[0], furtherSplit[1].replaceFirst("from ", ""), furtherSplit[2].replaceFirst("to ", ""));
                tasks.add(temp);
                System.out.println(indent + "    Added Event:\n        " + temp.toString() + "\n    Now you have " + tasks.size() + " tasks in the list.\n" + indent);
                return true;
            }
            case "deadline": {
                String[] furtherSplit = parts[1].split("/", 2);
                Task temp = new Deadline(furtherSplit[0], furtherSplit[1].replaceFirst("by ", ""));
                tasks.add(temp);
                System.out.println(indent + "    Added Deadline:\n        " + temp.toString() + "\n    Now you have " + tasks.size() + " tasks in the list.\n" + indent);
                return true;
            }
            default:
                return true;
        }
    }
}
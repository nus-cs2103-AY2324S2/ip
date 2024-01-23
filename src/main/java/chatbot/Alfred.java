package chatbot;

import java.util.ArrayList;
import java.util.Scanner;

public class Alfred {
    protected static final String line = "________________________________________________________________________________________";
    protected static ArrayList<Task> list = new ArrayList<Task>();

    public void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm Alfred");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public void bye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public void echo(String input) {
        System.out.println(line);
        System.out.println(input);
        System.out.println(line);
    }

    public void addList(String input) {
        if (input.startsWith("todo")) {
            input = input.substring(5);
            list.add(new ToDo(input));
        } else if (input.startsWith("deadline")) {
            input = input.substring(9);
            String description = input.split(" /by ", 2)[0];
            String by = input.split(" /by ", 2)[1];
            list.add(new Deadline(description, by));
        } else if (input.startsWith("event")) {
            input = input.substring(6);
            String [] eventDetails = input.split(" /", 3);
            String description = eventDetails[0];
            String startTime = eventDetails[1].substring(5, 12);
            String endTime = eventDetails[2].substring(3);
            list.add(new Event(description, startTime, endTime));
        }
        String singularTask = list.size() == 1 ? "task" : "tasks";
        this.echo(String.format("Got it. I've added this task:\n  %s\nNow you have %d %s in the list.", list.get(list.size()-1).toString(), list.size(), singularTask));
    }

    public void printList() {
        System.out.println(line);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).toString());
        }
        System.out.println(line);
    }

    public void markList(int index) {
        list.get(index).toggleDone();
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + list.get(index));
        System.out.println(line);
    }

    public void unmarkList(int index) {
        list.get(index).toggleDone();
        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + list.get(index));
        System.out.println(line);
    }

    public static void main(String[] args) {
        Alfred alfred = new Alfred();
        alfred.greet();
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            switch (input) {
                case "bye":
                    alfred.bye();
                    return;
                case "list":
                    alfred.printList();
                    break;
                default:
                    if (input.startsWith("unmark")) {
                        int extractedIdx = Integer.parseInt(input.substring(7));
                        alfred.unmarkList(extractedIdx - 1);
                    } else if (input.startsWith("mark")) {
                        int extractedIdx = Integer.parseInt(input.substring(5));
                        alfred.markList(extractedIdx - 1);
                    } else {
                        alfred.addList(input);
                    }
                    break;
            }
        }
    }
}
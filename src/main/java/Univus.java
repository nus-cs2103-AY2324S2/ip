import jdk.jfr.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Univus {
    private static Scanner scanner;
    private List<Task> store;
    public Univus() {
        this.scanner = new Scanner(System.in);
        this.store = new ArrayList<>();
    }
    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Univus");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }
    public void bye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
    public void echo(String message) {
        if (message.equals("bye")){
            bye();
            scanner.close();
        } else if (message.equals("list")) {
            System.out.println("____________________________________________________________");
            int index = 1;
            for (Task msg : store) {
                System.out.println(index + "." + msg.toString());;
                index++;
            }
            System.out.println("____________________________________________________________");
        } else if (message.matches("mark\\s\\d+")) {
            int index = Integer.parseInt(message.split(" ")[1]);
            Task task = store.get(index - 1);
            task.mark();
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("\t" + task.toString());
            System.out.println("____________________________________________________________");
        } else if (message.matches("unmark\\s\\d+")) {
            int index = Integer.parseInt(message.split(" ")[1]);
            Task task = store.get(index - 1);
            task.unMark();
            System.out.println("____________________________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("\t" + task.toString());
            System.out.println("____________________________________________________________");
        } else if (message.startsWith("todo") && message.length() > 4) {
            ToDos todo = new ToDos(message);
            store.add(todo);
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + todo.toString());
            System.out.println("Now you have " + store.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } else if (message.startsWith("deadline") && message.length() > 8) {
            String dueDate = message.split("/")[1];
            String description = message.split("/")[0];
            Deadlines deadline = new Deadlines(description, dueDate);
            store.add(deadline);
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + deadline.toString());
            System.out.println("Now you have " + store.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } else if (message.startsWith("event") && message.length() > 5) {
            String description = message.split("/")[0];
            String start = message.split("/")[1];
            String end = message.split("/")[2];
            Events event = new Events(description, start, end);
            store.add(event);
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + event.toString());
            System.out.println("Now you have " + store.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } else {
            Task task = new Task(message);
            store.add(task);
            System.out.println("____________________________________________________________");
            System.out.println("added: " + message);
            System.out.println("____________________________________________________________");
        }
    }
    public static void main(String[] args) {
        Univus univus = new Univus();
        univus.greet();
        while (true) {
            String message = scanner.nextLine();
            univus.echo(message);
            if (message.equals("bye")) {
                break;
            }
        }
    }
}

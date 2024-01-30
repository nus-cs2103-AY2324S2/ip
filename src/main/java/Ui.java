import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public String printLine() {
        System.out.println("____________________________________________________________");
    }

    public String welcome() {
        printLine();
        System.out.println("Hello! I'm Doye\n" + "What can I do for you?");
        printLine();
    }

    public String bye() {
        save(array);
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public String list() {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < array.size(); i++) {
            Task addTask = array.get(i);
            System.out.println((i + 1) + "." + addTask.toString());
        }
        printLine();
    }

    public String unmark() {
        String[] tokens = order.split(" ");
        int number = Integer.parseInt(tokens[1]);
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        Task t = array.get(number - 1);
        t.markAsUnDone();
        System.out.println(t.toString());
        printLine();
    }


    public String mark() {
        String[] tokens = order.split(" ");
        int number = Integer.parseInt(tokens[1]);
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        Task t = array.get(number - 1);
        t.markAsDone();
        System.out.println(t.toString());
        printLine();
    } 

    public Stirng todo() {
        try {
            String task = order.substring(4).trim();
            if (task.isEmpty()) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            Task t = new Todo(task);
            array.add(t);
            save(array);
            printLine();
            System.out.println("Got it. I've added this task:");
            System.out.println(t.toString());
            System.out.println("Now you have " + array.size() + " tasks in the list.");
            printLine();
        }  catch (DukeException e) {
            printLine();
            System.out.println(e.getMessage());
            printLine();
        }
    }

    public String todo() {
        int byIndex = order.indexOf("/by");
        String task = order.substring(9, byIndex - 1);
        String due = order.substring(byIndex + 4);
        Task t = new Deadline(task, due);
        array.add(t);
        save(array);
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(t.toString());
        System.out.println("Now you have " + array.size() + " tasks in the list.");
        printLine();
    }

    public String event() {
        int fromIndex = order.indexOf("/from");
        int toIndex = order.indexOf("/to");
        String task = order.substring(6, fromIndex - 1);
        String from = order.substring(fromIndex + 6, toIndex);
        String to = order.substring(toIndex + 4);
        Task t = new Event(task, from, to);
        array.add(t);
        save(array);
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(t.toString());
        System.out.println("Now you have " + array.size() + " tasks in the list.");
        printLine();
    }

    public String delete() {
        String[] tokens = order.split(" ");
        int number = Integer.parseInt(tokens[1]);
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        Task t = array.get(number - 1);
        System.out.println(t.toString());
        array.remove(number - 1);
        save(array);
        System.out.println("Now you have " + array.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public String else() {
        System.out.println("____________________________________________________________");
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println("____________________________________________________________");
    }
}
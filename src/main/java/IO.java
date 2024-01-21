import java.util.ArrayList;
import java.util.Scanner;

class IO {
    ArrayList<Task> tasks = new ArrayList<>();
    private Scanner sc;
    private String hLine = "________________________________________________";
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private String greetMsg = "Hello! I'm Hatsune Miku!\n"
            + " What can I do for you?";
    private String exitMsg = "Bye. Hope to see you again soon!";

    public IO() {
        sc = new Scanner(System.in);
    }

    public void greet() {
        System.out.println("Hello from\n" + logo);
        System.out.println(hLine);
        System.out.println(greetMsg);
        System.out.println(hLine);
    }

    public void command() {
        String input = sc.nextLine();
        if (input.equals("bye")) {
            echo(exitMsg);
            System.exit(0);
        } else if (input.equals("list")) {
            list();
        } else if (input.contains("mark")) {
            mark(input);
        } else {
            addTask(input);
        }
        command();
    }

    private void addTask(String input) {
        Task t = null;
        if (input.startsWith("todo")) {
            String s = input.substring(5);
            t = new Todo(s);
        } else if (input.startsWith("deadline")) {
            String s = input.substring(9);
            String[] token = s.split(" /");
            t = new Deadline(token[0], token[1].substring(3));
        } else if (input.startsWith("event")) {
            String s = input.substring(6);
            String[] token = s.split(" /");
            t = new Event(token[0], token[1].substring(5), token[2].substring(3));
        }
        if (t != null) {
            tasks.add(t);
            echo("Got it. I've added this task:\n  "
                    + t + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.");
        }
    }

    private void list() {
        int count = 0;
        System.out.println("Here are the tasks in your list:");
        for (Task t : tasks) {
            count++;
            System.out.println(count + ". " + t);
        }
        System.out.println(hLine);
    }

    private void mark(String input) {
        String[] token = input.split(" ");
        Task t = tasks.get(Integer.parseInt(token[1]) - 1);
        if (token[0].equals("unmark")) {
            t.markUndone();
            echo("OK, I've marked this task as not done yet:\n"
                    + t);
        } else if (token[0].equals("mark")) {
            t.markAsDone();
            echo("Nice! I've marked this task as done:\n"
                    + t);
        }
    }

    private void echo(String msg) {
        System.out.println(msg);
        System.out.println(hLine);
    }
}
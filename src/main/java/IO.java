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
            tasks.add(new Task(input));
            echo("added: " + input);
        }
        command();
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
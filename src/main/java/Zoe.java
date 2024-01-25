import java.util.*;
public class Zoe {
    protected ArrayList<Task> tasks;
    protected ArrayList<String> functions = new ArrayList<String>();
    public Zoe() {
        this.tasks = new ArrayList<Task>();
        functions.addAll(List.of("list", "mark", "unmark", "todo", "deadline", "event"));
    }
    public void saysHi() {
        System.out.println("Hello! I'm Zoe");
        System.out.println("What can I do for you?");
    }
    public void process(String command) {
        int idx = command.indexOf(' ');
        if (idx > -1) {
            String[] str = command.split(" ", 2);
            carryOutLongCommand(str[0], str[1]);
        } else {
            carryOutShortCommand(command);
        }
    }

    public void carryOutShortCommand(String command) {
        if (command.equals("list")) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(String.format
                        ("%d.%s", i + 1, tasks.get(i).getStatus()));
            }
        } else {
            if (command.equals("todo")) {
                System.out.println("Sorry! todo requires a description like todo XYZ");
            } else {
                System.out.println("Sorry! I can't carry this out. I can only perform these currently: " + functions);
            }
        }
    }

    public void carryOutLongCommand(String command, String commandDescription) {
        if (command.equals("todo")) {
            ToDo td = new ToDo(commandDescription);
            tasks.add(td);
            System.out.println("Got it. I've added this task:");
            System.out.println(td.getStatus());
            System.out.println(String.format("You now have %d tasks in the list", tasks.size()));
        } else if (command.equals("deadline")) {
            Deadline dl = new Deadline(commandDescription);
            tasks.add(dl);
            System.out.println("Got it. I've added this task:");
            System.out.println(dl.getStatus());
            System.out.println(String.format("You now have %d tasks in the list", tasks.size()));
        } else if (command.equals("event")) {
            Event e = new Event(commandDescription);
            tasks.add(e);
            System.out.println("Got it. I've added this task:");
            System.out.println(e.getStatus());
            System.out.println(String.format("You now have %d tasks in the list", tasks.size()));
        } else if (command.equals("mark")) {
            int i = Integer.parseInt(commandDescription);
            if (i > tasks.size() || i < 1) {
                System.out.println("This task does not exist! " +
                        "Input a positive number among tasks in list starting from 1");
            } else {
                tasks.get(i - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(i - 1).getStatus());
            }
        } else if (command.equals("unmark")) {
            int i = Integer.parseInt(commandDescription);
            if (i > tasks.size() || i < 1) {
                System.out.println("This task does not exist! " +
                        "Input a positive number among tasks in list starting from 1");
            } else {
                tasks.get(i - 1).unmark();
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println(tasks.get(i - 1).getStatus());
            }
        } else if (command.equals("delete")) {
            int i = Integer.parseInt(commandDescription);
            if (i > tasks.size() || i < 1) {
                System.out.println("This task does not exist! " +
                        "Input a positive number among tasks in list starting from 1");
            } else {
                System.out.println("Noted! this task gon like pentagon:");
                System.out.println(tasks.get(i - 1).getStatus());
                tasks.remove(Integer.valueOf(i) - 1);
                System.out.println(String.format("There are %d tasks left in the list", tasks.size()));
            }
        } else {
            System.out.println("You have keyed in an invalid command, " +
                    "Zoe can only perform todo, event, deadline, mark, unmark and delete");
        }
    }
}
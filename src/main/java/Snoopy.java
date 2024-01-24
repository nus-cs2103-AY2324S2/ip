import java.util.*;

public class Snoopy{
    public static void formalities(String context) {
        if (context.equals("greet")) {
            String newLogo = "\n" +
                    "$$$$$$$\\  $$\\                     $$$$$$$\\                                \n" +
                    "$$  __$$\\ \\__|                    $$  __$$\\                               \n" +
                    "$$ |  $$ |$$\\ $$$$$$$\\   $$$$$$\\  $$ |  $$ | $$$$$$\\  $$$$$$$\\   $$$$$$\\  \n" +
                    "$$$$$$$\\ |$$ |$$  __$$\\ $$  __$$\\ $$$$$$$\\ |$$  __$$\\ $$  __$$\\ $$  __$$\\ \n" +
                    "$$  __$$\\ $$ |$$ |  $$ |$$ /  $$ |$$  __$$\\ $$ /  $$ |$$ |  $$ |$$ /  $$ |\n" +
                    "$$ |  $$ |$$ |$$ |  $$ |$$ |  $$ |$$ |  $$ |$$ |  $$ |$$ |  $$ |$$ |  $$ |\n" +
                    "$$$$$$$  |$$ |$$ |  $$ |\\$$$$$$$ |$$$$$$$  |\\$$$$$$  |$$ |  $$ |\\$$$$$$$ |\n" +
                    "\\_______/ \\__|\\__|  \\__| \\____$$ |\\_______/  \\______/ \\__|  \\__| \\____$$ |\n" +
                    "                        $$\\   $$ |                              $$\\   $$ |\n" +
                    "                        \\$$$$$$  |                              \\$$$$$$  |\n" +
                    "                         \\______/                                \\______/ \n";
            System.out.println(newLogo);
            System.out.println("____________________________________________________________");
            System.out.println(" Wassup dawg, I'm Snoopy");
            System.out.println(" What can I do for you?");
            System.out.println("____________________________________________________________");
        } else if (context.equals("farewell")) {
            System.out.println("____________________________________________________________");
            System.out.println(" Bye. Don't come back. jk!");
            System.out.println("____________________________________________________________");
        }
    }


    public static void main(String[] args) {
        formalities("greet");

        ArrayList<Task> todos = new ArrayList<Task>();

        while (true) {
            Scanner reader = new Scanner(System.in).useDelimiter("\\n"); //Solution adapted by https://stackoverflow.com/questions/4058912/scanner-doesnt-read-whole-sentence-difference-between-next-and-nextline-o
            String s = reader.next();
            String arr[] = s.split(" ", 2); // String in Array format. Useful: https://www.geeksforgeeks.org/split-string-java-examples/
            String maybeCommand = arr[0];

            if (s.toLowerCase().equals("bye")) {
                formalities("farewell");
                break;
            } else if (s.toLowerCase().equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < todos.size(); i++) {
                    Task currTask = todos.get(i);
                    System.out.println((i + 1) + ". " + currTask.toString());
                }
                System.out.println("____________________________________________________________");
            } else if (maybeCommand.equals("mark")) {
                Integer index = Integer.valueOf(arr[1]) - 1;
                System.out.print(" Nice! I've marked this task as done:\n");
                Task currTask = todos.get(index);
                currTask.markAsDone();
                System.out.println(" " + currTask.toString());
                System.out.println("____________________________________________________________");
            } else if (maybeCommand.equals("unmark")) {
                Integer index = Integer.valueOf(arr[1]) - 1;
                System.out.println("____________________________________________________________");
                System.out.print(" OK, I've marked this task as not done yet:\n");
                Task currTask = todos.get(index);
                currTask.markAsUndone();
                System.out.println(" " + currTask.toString());
                System.out.println("____________________________________________________________");
            } else if (maybeCommand.equals("todo") || maybeCommand.equals("deadline") || maybeCommand.equals("event")) {
                switch (maybeCommand) {
                    case "todo":
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. Added this task:");
                        Todo todo = new Todo(arr[1]);
                        todos.add(todo);
                        System.out.println(todo.toString());
                        System.out.println("Now you have " + todos.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                        break;
                    case "deadline":
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. Added this task:");
                        String arguments[] = arr[1].split(" /by ");
                        String description = arguments[0];
                        String by = arguments[1];
                        Deadline deadline = new Deadline(description, by);
                        todos.add(deadline);
                        System.out.println(deadline.toString());
                        System.out.println("Now you have " + todos.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                        break;
                    case "event":
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. Added this task:");
                        // extraction of parameters
                        String getDesc[] = arr[1].split(" /from ");
                        String desc = getDesc[0];
                        String getDates[] = getDesc[1].split(" /to ");
                        String from = getDates[0];
                        String to = getDates[1];

                        //creating of event
                        Event event = new Event(desc, from, to);
                        todos.add(event);

                        System.out.println(event.toString());
                        System.out.println("Now you have " + todos.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                        break;
                }
            } else {
                todos.add(new Task(s));
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + s);
                System.out.println("____________________________________________________________");
            }

        }

    }
}

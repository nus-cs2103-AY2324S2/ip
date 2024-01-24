import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Harvard {
    public static void main(String[] args) throws HarvardException {
        String initial = "____________________________________________________________\n"
                + "Hello! I'm Harvard\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(initial);

        List<Task> tasks = new ArrayList<Task>();



        while (true) {
            Scanner scanner = new Scanner(System.in);
            String echoInput = scanner.nextLine();

            if (echoInput.equals("bye")) {
                break;
            }

            String command = echoInput.split(" ")[0];

            try {
                if (!command.equals("list") && !command.equals("todo") && !command.equals("deadline") &&
                        !command.equals("event") && !command.equals("mark") && !command.equals("unmark") &&
                            !command.equals("delete")) {
                    throw new HarvardException("Bro... Idk what that is man.");
                }
            } catch (HarvardException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            }

            if (command.equals("delete")) {
                int index = Integer.parseInt(echoInput.split(" ")[1]);
                Task targetTask = tasks.get(index - 1);

                System.out.println("____________________________________________________________");
                System.out.println("Noted. I've removed this task:");
                System.out.println(targetTask.toString());
                tasks.remove(index - 1);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________\n");
                continue;
            }


            if (command.equals("todo")) {
                try {
                    if (echoInput.split(" ").length == 1) {
                        throw new HarvardException("Wow that's awkward... Please enter a description for todo!");
                    }
                    Todo todoTask = new Todo(echoInput.substring(echoInput.indexOf(' ') + 1));
                    tasks.add(todoTask);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(todoTask.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________\n");
                } catch (HarvardException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("____________________________________________________________");
                }

            }

            if (command.equals("deadline")) {
                String[] commandItems = echoInput.split(" /by ");
                String desc = commandItems[0].substring(commandItems[0].indexOf(' ')+1);
                String by = commandItems[1];
                Deadline deadlineTask = new Deadline(desc, by);
                tasks.add(deadlineTask);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(deadlineTask.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________\n");
            }

            if (command.equals("event")) {
                String[] commandItems = echoInput.split(" /from ");
                String desc = commandItems[0].substring(commandItems[0].indexOf(' ')+1);
                String[] commandItems2 = commandItems[1].split(" /to ");
                String from = commandItems2[0];
                String to = commandItems2[1];


                Event eventTask = new Event(desc, from, to);
                tasks.add(eventTask);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(eventTask.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________\n");
            }

            if (command.equals("mark") || command.equals("unmark")) {
                int index = Integer.parseInt(echoInput.split(" ")[1]);
                Task targetTask = tasks.get(index - 1);

                System.out.println("____________________________________________________________");
                if (command.equals("mark")) {
                    targetTask.mark();
                    System.out.println("Nice! I've marked this task as done:");
                } else {
                    targetTask.unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                }

                System.out.println(targetTask.toString());
                System.out.println("____________________________________________________________\n");
                continue;
            }

            if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + "." + tasks.get(i).toString());
                }
                System.out.println("____________________________________________________________\n");
            }

        }

        String exit = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        System.out.println(exit);
    }

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        @Override
        public String toString() {
            return this.getStatusIcon() + " " + this.getDescription();
        }

        public String getStatusIcon() {
            return (isDone ? "[X]" : "[ ]"); // mark done task with X
        }

        public void mark() {
            this.isDone = true;
        }

        public void unmark() {
            this.isDone = false;
        }

        public String getDescription() {
            return this.description;
        }
    }

    public static class Todo extends Task {

        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public static class Event extends Task {

        protected String from;
        protected String to;

        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
        }
    }

    public static class HarvardException extends Throwable {
        public HarvardException(String errorMessage) {
            super(errorMessage);
        }
    }
}

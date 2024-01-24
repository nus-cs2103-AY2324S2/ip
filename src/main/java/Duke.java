import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> al = new ArrayList<>();

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Emis!\n \tWhat can I do for you?");
        while (sc.hasNextLine()) {
            String user_input = sc.nextLine();
            Integer spaceIndex = user_input.indexOf(" ");

            if (spaceIndex == -1) {
                if (user_input.equals("bye")) {
                    exit();

                } else if (user_input.equals("list")) {
                    print_list(al);

                }
            } else {
                
                String action = user_input.substring(0, user_input.indexOf(" "));
                try {
                    Integer taskNo = Integer.parseInt(user_input.substring(spaceIndex + 1));
                    if (taskNo <= 0 || taskNo > al.size()) {
                        System.out.println("Task does not exist");

                    } else {
                        // to mark as done or undone
                        Task t = al.get(taskNo - 1);
                
                        if (action.equals("mark")) {
                            t.markAsDone();
                            al.set(taskNo - 1, t);

                        } else if (action.equals("unmark")) {
                            t.markAsUndone();
                            al.set(taskNo - 1, t);
                        } 
                    }
                } catch (NumberFormatException nfe) {
                    // todo, deadline, event
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\tGot it. I've added this task:");
                    String d = user_input.substring(spaceIndex + 1);
                    if (action.equals("todo")) {
                        ToDo td = new ToDo(d);
                        al.add(td);
                        System.out.println("\t\t" + td.toString());

                    } else if (action.equals("deadline")) {
                        int slashIndex = d.indexOf("/by");
                        String des = d.substring(0, slashIndex);
                        String by = d.substring(slashIndex + 3);
                        Deadline dl = new Deadline(des, by);
                        al.add(dl);
                        System.out.println("\t\t" + dl.toString());

                    } else if (action.equals("event")) {
                        int slashIndex_from = d.indexOf("/from");
                        int slashIndex_to = d.indexOf("/to");
                        String des = d.substring(0, slashIndex_from);
                        String from = d.substring(slashIndex_from + 5, slashIndex_to);
                        String to = d.substring(slashIndex_to + 3);
                        Event eve = new Event(des, from, to);
                        al.add(eve);
                        System.out.println("\t\t" + eve.toString());

                    }
                    System.out.println("\tNow you have " + al.size() + " tasks in the list.");
                    System.out.println("\t____________________________________________________________");
                }
            }
        }
    }

    public static void exit() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
        System.exit(0);
    }

    public static void print_list(ArrayList<Task> al) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < al.size(); i++) {
            System.out.println("\t\t" + (i + 1) + "." + al.get(i).toString());
        }
        System.out.println("\t____________________________________________________________");
    }

    public static class Task {
        protected String description;
        protected boolean isDone;
    
        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }
    
        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }
    
        public void markAsDone() {
            this.isDone = true;
            System.out.println("\t____________________________________________________________");
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t\t[" + this.getStatusIcon() + "] " + this.description);
            System.out.println("\t____________________________________________________________");
        }
    
        public void markAsUndone() {
            this.isDone = false;
            System.out.println("\t____________________________________________________________");
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t\t[" + this.getStatusIcon() + "] " + this.description);
            System.out.println("\t____________________________________________________________");
        }
    
        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
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
            return"[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public static class ToDo extends Task {
        public ToDo(String description) {
            super(description);
        }
    
        @Override
        public String toString() {
            return"[T]" + super.toString();
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
            return"[E]" + super.toString() + "(from: " + this.from + " to: " + this.to + ")";
        }
    }
}

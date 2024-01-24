import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> al = new ArrayList<>();

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Emis!\n \tWhat can I do for you?");
        System.out.println("\tEmis is happy to help with printing a list of tasks with the command 'list'.");
        System.out.println("\tEmis is happy to add todos with the command 'todo (insert task here)'.");
        System.out.println("\tEmis is happy to add deadlines with the command 'deadline /by (insert deadline here)'.");
        System.out.println("\tEmis is happy to add events with the command 'event (insert event name) /from (insert start time) /to (insert end time)'.");
        System.out.println("\tEmis can mark tasks as done with the command 'mark (task no)'.");
        System.out.println("\tEmis can mark tasks as undone with the command 'unmark (task no)'.");
        System.out.println("\tTo stop talking to Emis, please say 'bye'.");

        while (sc.hasNextLine()) {
            String user_input = sc.nextLine();
            Integer spaceIndex = user_input.indexOf(" ");

            if (spaceIndex == -1) {
                if (user_input.equals("bye")) {
                    exit();

                } else if (user_input.equals("list")) {
                    print_list(al);
                } else {
                    System.out.println("Emis does not know what that means :(");
                }
            } else {
                
                String action = user_input.substring(0, user_input.indexOf(" "));
                try {
                    Integer taskNo = Integer.parseInt(user_input.substring(spaceIndex + 1));
                    if (taskNo <= 0 || taskNo > al.size()) {
                        System.out.println("Task does not exist! Please try again.");

                    } else {
                        // to mark as done or undone
                        Task t = al.get(taskNo - 1);
                
                        if (action.equals("mark")) {
                            t.markAsDone();
                            al.set(taskNo - 1, t);

                        } else if (action.equals("unmark")) {
                            t.markAsUndone();
                            al.set(taskNo - 1, t);
                        } else if (action.equals("delete")) {
                            deleteTask(al, taskNo - 1);
                        }
                    }
                } catch (NumberFormatException nfe) {
                    String d = user_input.substring(spaceIndex + 1);
                    // check if invalid, eg empty after blank
                    if (d == null || d.isEmpty()) {
                        System.out.println("Emis is judging you for not stating what you want");
                    } else {
                        // todo, deadline, event
                        System.out.println("\t____________________________________________________________");
                        System.out.println("\tGot it. I've added this task:");
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

    public static void deleteTask(ArrayList<Task> al, int taskNo) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + al.get(taskNo).toString());
        al.remove(taskNo);
        System.out.println("\tNow you have " + al.size() + " tasks in the list.");
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

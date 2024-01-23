import java.util.HashMap;
import java.util.Scanner;
public class Duke {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, Task> lsts = new HashMap<>();
        int counter = 0;

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Emis!\n \tWhat can I do for you?");
        while (sc.hasNextLine()) {
            String user_input = sc.nextLine();
            Integer spaceIndex = user_input.indexOf(" ");

            if (spaceIndex == -1) {
                if (user_input.equals("bye")) {
                    exit();
                } else if (user_input.equals("list")) {
                    print_list(lsts);
                } else {
                    counter += 1;
                    addTasks(user_input, counter, lsts);
                }
            } else {
                String action = user_input.substring(0, user_input.indexOf(" "));
                try {
                    Integer taskNo = Integer.parseInt(user_input.substring(spaceIndex + 1));
                    if (taskNo <= 0 || taskNo > counter) {
                        System.out.println("Task does not exist");
                    } else {
                        Task t = lsts.get(taskNo);
                
                        if (action.equals("mark")) {
                            t.markAsDone();
                            lsts.replace(taskNo, t);
                        } else if (action.equals("unmark")) {
                            t.markAsUndone();
                            lsts.replace(taskNo, t);
                        } 
                    }
                } catch (NumberFormatException nfe) {
                    counter += 1;
                    addTasks(user_input, counter, lsts);
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

    public static void print_list(HashMap<Integer, Task> hs) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHere are the tasks in your list:");
        for (Integer i : hs.keySet()) {
            System.out.println("\t" + hs.get(i).toString());
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void addTasks(String input, int counter, HashMap<Integer, Task> hs) {
        Task t = new Task(input, counter);
        hs.put(counter, t);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tadded: " + input);
        System.out.println("\t____________________________________________________________");
    }

    public static class Task {
        protected String description;
        protected int taskNumber;
        protected boolean isDone;

        public Task(String description, int taskNumber) {
            this.description = description;
            this.taskNumber = taskNumber;
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
            return taskNumber + ". [" + this.getStatusIcon() + "] " + this.description;
        }
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markDone() {
            isDone = true;
        }

        public void mark_not_done() {
            isDone = false;
        }

        public String toString() {
            return "[" + getStatusIcon() + "] " + description;
        }
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Lucifer\n" + "What can I do for you?");
        System.out.println("____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        String user_word = sc.nextLine();
        List<Task> l = new ArrayList<Task>();
        while (!user_word.equals("bye")) {
            if (user_word.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int x = 0; x < l.size(); x++) {
                    System.out.println(x + 1 + ". " + l.get(x));
                }
            }

            //This is for mark
            if (user_word.contains("mark")) {
                System.out.println("____________________________________________________________");
                int element_index = Integer.parseInt(user_word.split(" ")[1]) - 1;
                l.get(element_index).markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(l.get(element_index));
            }

            //This is for unmark
            if (user_word.contains("unmark")) {
                System.out.println("____________________________________________________________");
                int element_index = Integer.parseInt(user_word.split(" ")[1]) - 1;
                l.get(element_index).mark_not_done();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(l.get(element_index));
            }

            //This is for list
            if (!user_word.equals("list") && !user_word.contains("mark") && !user_word.equals("unmark")) {
                System.out.println("____________________________________________________________");
                System.out.println("added: " + user_word);
            }


            if (!user_word.contains("mark") && !user_word.equals("list") && !user_word.equals("unmark")) {
                Task newTask = new Task(user_word);
                l.add(newTask);
            }


            //System.out.println(user_word);
            System.out.println("____________________________________________________________");
            user_word = sc.nextLine();


        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}

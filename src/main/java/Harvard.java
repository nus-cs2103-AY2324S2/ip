import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Harvard {
    public static void main(String[] args) {
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

            if (command.equals("mark") || command.equals("unmark")) {
                int index = Integer.parseInt(echoInput.split(" ")[1]);
                Task targetTask = tasks.get(index - 1);

                System.out.println("____________________________________________________________\n");
                if (command.equals("mark")) {
                    targetTask.mark();
                    System.out.println("Nice! I've marked this task as done:\n");
                } else {
                    targetTask.unmark();
                    System.out.println("OK, I've marked this task as not done yet:\n");
                }

                System.out.println(targetTask.getStatusIcon() + " " + targetTask.getDescription() + "\n");
                System.out.println("____________________________________________________________\n");
                continue;
            }

            switch (echoInput) {
                case "list":
                    System.out.println("____________________________________________________________\n");
                    System.out.println("Here are the tasks in your list:\n");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + ". " + tasks.get(i).getStatusIcon() + " " + tasks.get(i).getDescription());
                    }
                    System.out.println("____________________________________________________________\n");
                    continue;

                default:
                    Task taskItem = new Task(echoInput);
                    tasks.add(taskItem);
                    System.out.println("____________________________________________________________\n");
                    System.out.println("added: " + taskItem.getDescription());
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
}

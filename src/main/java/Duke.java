import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        class Task {
            private String description;
            private boolean completed;

            public Task(String description, boolean completed) {
                this.description = description;
                this.completed = completed;
            }

            public String getDescription() {
                return this.description;
            }

            public boolean isCompleted() {
                return this.completed;
            }

            public void markComplete() {
                this.completed = true;
            }

            public void markUncomplete() {
                this.completed = false;
            }
        }

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>(); // list to store tasks

        System.out.println("Hello! I'm Scribbles.");
        System.out.println("What can I do for you?\n");

        String input = sc.nextLine(); // takes in input from user

        while (!(input.equals("bye"))) {

            if (input.equals("list")) { // user inputs "list"
                int numOfTasks = list.size(); // finds the number of tasks in list currently
                int taskNumber = 1;

                System.out.println("Here are the tasks in your list:");

                while (numOfTasks != 0) {
                    if (!(list.get(taskNumber - 1).isCompleted())) {
                        System.out.println(taskNumber + ".[ ] " + list.get(taskNumber - 1).getDescription());
                        taskNumber++;
                        numOfTasks--;
                    } else {
                        System.out.println(taskNumber + ".[X] " + list.get(taskNumber - 1).getDescription());
                        taskNumber++;
                        numOfTasks--;
                    }
                }
                input = sc.nextLine();

            } else if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.substring(5)); // get the task number to mark
                int numOfTasks = list.size(); // finds the number of tasks in list currently
                if (taskNumber <= numOfTasks) {
                    list.get(taskNumber - 1).markComplete();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  [X] " + list.get(taskNumber - 1).getDescription());
                }
                input = sc.nextLine();

            } else if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.substring(7)); // get the task number to unmark
                int numOfTasks = list.size(); // finds the number of tasks in list currently
                if (taskNumber <= numOfTasks) {
                    list.get(taskNumber - 1).markUncomplete();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  [ ] " + list.get(taskNumber - 1).getDescription());
                }
                input = sc.nextLine();

            } else {
                list.add(new Task(input, false)); // add input into list
                System.out.println("added: " + input);
                input = sc.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!"); // exits when user inputs bye
    }
}

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {

        class Task {
            protected String description; // task description
            protected boolean completed; // true is task is completed

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

            public String toString() {
                if (this.completed) {
                    return "[X] " + this.description;
                }
                return "[ ] " + this.description;
            }
        }

        class ToDos extends Task {
            public ToDos(String description, boolean completed) {
                super(description, completed);
            }

            @Override
            public String toString() {
                return "[T]" + super.toString();
            }
        }

        class Deadlines extends Task {
            protected String by; // date/time to be done before

            public Deadlines(String description, boolean completed, String by) {
                super(description, completed);
                this.by = by;
            }

            public String getBy() {
                return this.by;
            }

            public void setBy(String time) {
                this.by = time;
            }

            @Override
            public String toString() {
                return "[D]" + super.toString() + " (by: " + this.by + ")";
            }
        }

        class Events extends Task {
            protected String start; // start date/time
            protected String end; // end date/time

            public Events(String description, boolean completed, String start, String end) {
                super(description, completed);
                this.start = start;
                this.end = end;
            }

            public String getStart() {
                return  this.start;
            }

            public String getEnd() {
                return this.end;
            }

            public void setStart(String time) {
                this.start = time;
            }

            public void setEnd (String time) {
                this.end = time;
            }

            @Override
            public String toString() {
                return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
            }
        }

        Scanner sc = new Scanner(System.in); // scanner for user input
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
                    System.out.println(taskNumber + ". " + list.get(taskNumber - 1).toString());
                    taskNumber++;
                    numOfTasks--;
                }
                input = sc.nextLine();

            } else if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.substring(5)); // get the task number to mark
                int numOfTasks = list.size(); // finds the number of tasks in list currently
                if (taskNumber <= numOfTasks) {
                    list.get(taskNumber - 1).markComplete();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list.get(taskNumber - 1).toString());
                } else {
                    System.out.println("Invalid input: task number does not exist in your list.");
                }
                input = sc.nextLine();

            } else if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.substring(7)); // get the task number to unmark
                int numOfTasks = list.size(); // finds the number of tasks in list currently
                if (taskNumber <= numOfTasks) {
                    list.get(taskNumber - 1).markUncomplete();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(list.get(taskNumber - 1).toString());
                } else {
                    System.out.println("Invalid input: task number does not exist in your list.");
                }
                input = sc.nextLine();

            } else if (input.startsWith("todo ")) {
                String descrpt = input.substring(5);
                list.add(new ToDos(descrpt, false));
                System.out.println("added: " + descrpt);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                input = sc.nextLine();

            } else if (input.startsWith("deadline ")) {
                String descrpt = input.substring(9);
                String taskDescrpt = descrpt.split(" /by")[0];
                String taskDeadline = descrpt.split(" /by ")[1];
                list.add(new Deadlines(taskDescrpt, false, taskDeadline));
                System.out.println("added: " + input);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                input = sc.nextLine();

            } else if (input.startsWith("event ")) {
                String descrpt = input.substring(6);
                String taskDescrpt = descrpt.split(" /from ")[0];
                String taskStart = descrpt.split(" /from ")[1].split(" /to ")[0];
                String taskEnd = descrpt.split(" /to ")[1];
                list.add(new Events(taskDescrpt, false, taskStart, taskEnd));
                System.out.println("added: " + input);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                input = sc.nextLine();

            } else {
                System.out.println("Invalid input: try starting inputs with 'todo', 'deadline', or 'event'.");
                input = sc.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!"); // exits when user inputs bye
    }
}

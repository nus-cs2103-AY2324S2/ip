import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("      Hello! I'm AndrewOng2066");
        System.out.println("      What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String userInput = sc.nextLine().trim();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("      Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("      " + (i + 1) + ".["+ tasks.get(i).getStatusIcon() +"] " + tasks.get(i).getDescription());
                }
                System.out.println("    ____________________________________________________________\n");
            } else if (userInput.substring(0,5).trim().equals("mark")) {
                String[] splitInput = userInput.split(" ");
                int choiceMark = Integer.parseInt(splitInput[1]);
                if (choiceMark <= tasks.size()) {
                    tasks.get(choiceMark - 1).markAsDone();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("      Nice! I've marked this task as done: ");
                    System.out.println("        " + "[X] " + tasks.get(choiceMark - 1).getDescription());
                    System.out.println("    ____________________________________________________________\n");
                } else {
                    System.out.println("Invalid choice");
                }
            } else if (userInput.substring(0,7).trim().equals("unmark")) {
                String[] splitInput = userInput.split(" ");
                int choiceMark = Integer.parseInt(splitInput[1]);
                if (choiceMark <= tasks.size()) {
                    tasks.get(choiceMark - 1).markAsUndone();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("      OK, I've marked this task as not done yet: ");
                    System.out.println("        " + "[ ] " + tasks.get(choiceMark - 1).getDescription());
                    System.out.println("    ____________________________________________________________\n");
                } else {
                    System.out.println("Invalid choice");
                }
            } else {
                System.out.println("    ____________________________________________________________");
                System.out.println("      added: " + userInput);
                tasks.add(new Task(userInput));
                System.out.println("    ____________________________________________________________\n");
            }
            userInput = sc.nextLine().trim();
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("      Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}

class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }
}

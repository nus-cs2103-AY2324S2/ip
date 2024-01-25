import java.util.ArrayList;
import java.util.Scanner;

public class Lemona {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        //greeting
        System.out.println("\t______________________________________________________" +
                "\n\t" + " Hello! I'm Lemona" + "\n\t" + " What can I do for you?" +
                "\n\t______________________________________________________");

        while (true) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            if (input.equals("bye")) {                        // exits when user types in "bye"
                System.out.println("\t______________________________________________________");
                System.out.println("\t" + " Bye. Hope to see you again soon!");
                System.out.println("\t______________________________________________________");
                break;
            } else if (input.equals("list")) {                // lists up when user types in "list"
                System.out.println("\t______________________________________________________");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t " + (i + 1) + ".[" + list.get(i).getStatusIcon() + "] " +
                            list.get(i).getDescription());
                }
                System.out.println("\t______________________________________________________");
            } else if (parts[0].equals("mark")) {             // marks the @th task when user types in "mark @"
                int index = Integer.parseInt(parts[1]);
                list.get(index - 1).markAsDone();
                System.out.println("\t______________________________________________________");
                System.out.println("\t" + " Nice! I've marked this task as done:" + "\n\t\t" +
                        "[" + list.get(index - 1).getStatusIcon() + "]" + " " + list.get(index - 1).getDescription());
                System.out.println("\t______________________________________________________");
            } else if (parts[0].equals("unmark")) {           // unmarks the @th task when user types in "unmark @"
                int index = Integer.parseInt(parts[1]);
                list.get(index - 1).unmarkAsDone();
                System.out.println("\t______________________________________________________");
                System.out.println("\t" + " OK, I've marked this task as not done yet:" + "\n\t\t" +
                        "[" + list.get(index - 1).getStatusIcon() + "]" + " " + list.get(index - 1).getDescription());
                System.out.println("\t______________________________________________________");
            } else {                                          // adds the task into the list when user types in new task
                System.out.println("\t______________________________________________________");
                Task t = new Task(input);
                list.add(t);
                System.out.println("\t " + "added: " + input);
                System.out.println("\t______________________________________________________");
            }
        }
        scanner.close();
    }
}

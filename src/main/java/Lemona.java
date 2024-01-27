import java.util.ArrayList;
import java.util.Scanner;

public class Lemona {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        //greeting
        System.out.println("\t______________________________________________________" +
                "\n\t Hello! I'm Lemona" +
                "\n\t What can I do for you?" +
                "\n\t______________________________________________________");

        while (true) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            int size = parts.length;

            // EXIT when user types in "bye"
            if (input.equals("bye")) {
                System.out.println("\t______________________________________________________");
                System.out.println("\t Bye. Don't forget to take a LEMONA!");
                System.out.println("\t______________________________________________________");
                break;

            // LIST up when user types in "list"
            } else if (input.equals("list")) {
                System.out.println("\t______________________________________________________");
                System.out.println("\t Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t " + (i + 1) + "." + list.get(i).print());
                }
                System.out.println("\t______________________________________________________");

            // MARK the @th task when user types in "mark @"
            } else if (parts[0].equals("mark")) {
                int index = Integer.parseInt(parts[1]);
                if (list.size() < index || parts.length == 1) {
                    System.out.println("\t______________________________________________________");
                    System.out.println("\t I think you haven't had enough vitamin A." +
                            "\n\t You do not have that many tasks!" +
                            "\n\t I suggest you take some LEMONA.");
                    System.out.println("\t______________________________________________________");
                    break;
                }
                list.get(index - 1).markAsDone();
                System.out.println("\t______________________________________________________");
                System.out.println("\t Nice! I've marked this task as done:" + "\n\t\t" +
                        list.get(index - 1).print());
                System.out.println("\t______________________________________________________");

            // UNMARK the @th task when user types in "unmark @"
            } else if (parts[0].equals("unmark")) {
                int index = Integer.parseInt(parts[1]);
                if (list.size() < index || parts.length == 1) {
                    System.out.println("\t______________________________________________________");
                    System.out.println("\t I think you haven't had enough vitamin E." +
                            "\n\t You do not have that many tasks!" +
                            "\n\t I suggest you take some LEMONA.");
                    System.out.println("\t______________________________________________________");
                    break;
                }
                list.get(index - 1).unmarkAsDone();
                System.out.println("\t______________________________________________________");
                System.out.println("\t OK, I've marked this task as not done yet:" + "\n\t\t" +
                        list.get(index - 2).print());
                System.out.println("\t______________________________________________________");

            //DELETE the task from the list
            } else if (parts[0].equals("delete")) {
                int index = Integer.parseInt(parts[1]);
                if (list.size() < index || parts.length == 1) {
                    System.out.println("\t______________________________________________________");
                    System.out.println("\t I think you haven't had enough vitamin K." +
                            "\n\t You do not have a task to delete!" +
                            "\n\t I suggest you take some LEMONA.");
                    System.out.println("\t______________________________________________________");
                    break;
                }
                System.out.println("\t______________________________________________________");
                System.out.println("\t OK, I've removed this task:" + "\n\t\t" +
                        list.get(index - 1).print());
                list.remove(index - 1);
                System.out.println("\t Now you have " + list.size() + " tasks in the list.");
                System.out.println("\t______________________________________________________");

            // ADD the task into the list when user types in new task
            } else if (parts[0].equals("todo") || parts[0].equals("deadline") || parts[0].equals("event")) {
                 if (parts.length == 1) {
                    System.out.println("\t______________________________________________________");
                    System.out.println("\t I think you haven't had enough vitamin B." +
                            "\n\t The descriptions of your tasks are missing!" +
                            "\n\t I suggest you take some LEMONA.");
                    System.out.println("\t______________________________________________________");
                    break;
                } else {
                     System.out.println("\t______________________________________________________");
                     System.out.println("\t Got it. I've added this task:");

                     if (parts[0].equals("todo")) {
                         Task task = new Todo(parts[1]);
                         list.add(task);
                         System.out.print("\t   " + task.print());

                     } else if (parts[0].equals("deadline")) {
                         String[] content = parts[1].split("/by ");
                         Task task = new Deadline(content[0], content[1]);
                         list.add(task);
                         System.out.print("\t   " + task.print());

                     } else if (parts[0].equals("event")) {
                         String[] content = parts[1].split("/from ");
                         String[] dates = content[1].split("/to ");
                         Task task = new Event(content[0], dates[0], dates[1]);
                         list.add(task);
                         System.out.print("\t  " + task.print());

                     }
                     System.out.println("\n\t Now you have " + list.size() + " tasks in the list.");
                     System.out.println("\t______________________________________________________");
                 }
            } else {
                System.out.println("\t______________________________________________________");
                System.out.println("\t I think you haven't had enough vitamin C." +
                        "\n\t I can't understand what you are saying at all!" +
                        "\n\t I suggest you take some LEMONA.");
                System.out.println("\t______________________________________________________");
            }
        }
        scanner.close();
    }
}

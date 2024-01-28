import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        String logo = " KASSIM ";
        System.out.println("YOO I AM " + logo);
        System.out.println("What can I do for you?");

        ArrayList<Task> myList = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.print(" ");

        while (true) {
            String input = sc.nextLine();

            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String restOfInputs = parts.length > 1 ? parts[1] : "";

            if (command.equals("mark")) {
                System.out.println("");
                int num = Integer.parseInt(parts[1]) - 1;

                if (num >= 0 && num < myList.size()) {
                    Task task = myList.get(num);
                    task.markAsDone();

                    System.out.println("------------------------------------------------------------");
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(task);
                    System.out.println("------------------------------------------------------------");

                } else {
                    System.out.println("Invalid task number.");
                }

            } else if (command.equals("unmark")) {
                System.out.println("");
                int num = Integer.parseInt(parts[1]) - 1;

                if (num >= 0 && num < myList.size()) {
                    Task task = myList.get(num);
                    task.markAsNotDone();

                    System.out.println("------------------------------------------------------------");
                    System.out.println("Ok, I've marked this task as not done yet: ");
                    System.out.println(task);
                    System.out.println("------------------------------------------------------------");

                } else {
                    System.out.println("Invalid task number.");
                }

            } else if (command.equals("list")) {
                System.out.println("------------------------------------------------------------");
                System.out.println("Here are the tasks in your list: ");

                for (Task task : myList) {
                    System.out.println((myList.indexOf(task) + 1) + "." + task);
                }
                System.out.println("------------------------------------------------------------");

            } else if (command.equals("bye")) {
                break;

            } else if (command.equals("delete")) {
                int removed_item = Integer.parseInt(parts[1]) - 1;

                System.out.println("------------------------------------------------------------");
                System.out.println("Noted. I've removed this task: ");
                System.out.println(myList.get(removed_item));

                myList.remove(removed_item);

                System.out.println("Now you have " + myList.size() + " tasks in the list.");
                System.out.println("------------------------------------------------------------");

            } else {
                
                try {
                    // Todo is called
                    if (command.equals("todo")) {
                        String item = restOfInputs;
                        try {
                            if (item.isEmpty()) {
                                
                                throw new DukeException("Todo cannot be empty, please indicate what you plan to do.");

                            } else {

                                Todo newTodo = new Todo(item);
                                myList.add(newTodo);

                                System.out.println("------------------------------------------------------------");
                                System.out.println("Got it. I've added this task: ");
                                System.out.println(newTodo);
                                System.out.println("Now you have " + myList.size() + " tasks in the list.");
                                System.out.println("------------------------------------------------------------");
                            }
                        } catch (DukeException e) {
                            System.out.println("------------------------------------------------------------");
                            System.out.println(e.getMessage());
                            System.out.println("------------------------------------------------------------");
                        }
                    }

                    // Deadline is called
                    else if (command.equals("deadline")) {
                        String[] item_time = restOfInputs.split("/by");
                        String item = item_time[0];
                        String time = item_time[1];

                        Deadline newDeadline = new Deadline(item, time);
                        myList.add(newDeadline);
                        System.out.println("------------------------------------------------------------");
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(newDeadline);
                        System.out.println("Now you have " + myList.size() + " tasks in the list.");
                        System.out.println("------------------------------------------------------------");
                    }

                    // Event is called
                    else if (command.equals("event")) {

                        String[] item_time = restOfInputs.split("/from");
                        String item = item_time[0];
                        String time = item_time[1];

                        String[] from_to = time.split("/to");
                        String from = from_to[0];
                        String to = from_to[1];

                        Event newEvent = new Event(item, from, to);
                        myList.add(newEvent);

                        System.out.println("------------------------------------------------------------");
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(newEvent);
                        System.out.println("Now you have " + myList.size() + " tasks in the list.");
                        System.out.println("------------------------------------------------------------");
                    }

                    else {
                        throw new DukeException("SORRY! but are you sure you enter the correct command? please check!");
                    }

                } catch (DukeException e) {

                    System.out.println("------------------------------------------------------------");
                    System.out.println(e.getMessage());
                    System.out.println("------------------------------------------------------------");
                }
            }

            System.out.print(" ");
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("Bye! Hope to see you again!!");
    }
}


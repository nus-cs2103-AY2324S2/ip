import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);

        String logo = " ____ \n"
                + "|  _ \\   ___   ___ \n"
                + "| |_| | / _ \\ / _ \\\n"
                + "| |_| | | __/ | __/\n"
                + "|____/  \\___| \\___|\n";

        String msg = "------------------------------------------------ \n"
                + "Hello! I'm Bee! \n"
                + "What can I do for you? \n"
                + "------------------------------------------------";

        System.out.println(logo + "\n" + msg);

        boolean output = true;
        String input;

        while (output) {
            System.out.println(" ");
            input = sc.nextLine();

            // end the program
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                output = false;

                // print the entire task list
            } else if (input.equals("list")) {
                System.out.println(indent + "------------------------------------------------");
                System.out.println(indent + "Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    Task t = list.get(i);
                    System.out.println(indent + (i + 1) + "." + t.toString());
                }
                System.out.println(indent + "------------------------------------------------");

                // mark an item in list
            } else if (input.equals("mark")) {
                System.out.println(indent + "------------------------------------------------");
                int position = sc.nextInt() - 1;

                // check for error
                if (position + 1 > list.size()) {
                    System.out.println(indent + "Task does not exist");
                } else if (position < 0) {
                    System.out.println(indent + "Task does not exist");

                } else {
                    Task t = list.get(position);
                    if (t.getStatusIcon().equals(" ")) {
                        System.out.println(indent + "Nice! I've marked this task as done:");
                    }
                    t.markAsDone();
                    System.out.println(indent + "  " + t.toString());
                }
                System.out.println(indent + "------------------------------------------------");

                // unmark an item in list
            } else if (input.equals("unmark")) {
                System.out.println(indent + "------------------------------------------------");
                int position = sc.nextInt() - 1;

                // check for error
                if (position + 1 > list.size()) {
                    System.out.println(indent + "Task does not exist");
                } else if (position < 0) {
                    System.out.println(indent + "Task does not exist");

                } else {
                    Task t = list.get(position);
                    if (t.getStatusIcon().equals("X")) {
                        System.out.println(indent + "OK, I've marked this task as not done yet:");
                    }
                    t.markAsUndone();
                    System.out.println(indent + "  " + t.toString());
                }
                System.out.println(indent + "------------------------------------------------");

            } else if (input.equals("todo")) {
                System.out.println(indent + "------------------------------------------------");
                // check for error
                String des = input + sc.nextLine();
                des = des.trim();
                if (des.equals("todo")) {
                    System.out.println(indent + "OOPS!!! The description of a todo cannot be empty.");
                } else {
                    des = des.substring(4).trim();
                    Todo t = new Todo(des);
                    list.add(t);
                    System.out.println(indent + "Got it. I've added this task:");
                    System.out.println(indent + "  " + t.toString());
                    System.out.println(indent + "Now you have " + list.size() + " tasks in the list.");
                }
                System.out.println(indent + "------------------------------------------------");

            } else if (input.equals("deadline")) {
                System.out.println(indent + "------------------------------------------------");

                // check for error
                String temp = input + sc.nextLine();
                temp = temp.trim();
                if (temp.equals("deadline")) {
                    System.out.println(indent + "OOPS!!! The description of a deadline cannot be empty.");
                } else {
                    temp = temp.substring(8);
                    String[] arrOfStr = temp.split("/by");
                    String des = arrOfStr[0].trim();
                    if (arrOfStr.length < 2) {
                        System.out.println(indent + "Please provide the end time for the deadline.");

                    } else {
                        String by = arrOfStr[1].trim();
                        Deadline t = new Deadline(des, by);
                        list.add(t);
                        System.out.println(indent + "Got it. I've added this task:");
                        System.out.println(indent + "  " + t.toString());
                        System.out.println(indent + "Now you have " + list.size() + " tasks in the list.");
                    }
                }
                System.out.println(indent + "------------------------------------------------");

            } else if (input.equals("event")) {
                System.out.println(indent + "------------------------------------------------");
                // check for error
                String temp = input + sc.nextLine();
                temp = temp.trim();
                if (temp.equals("event")) {
                    System.out.println(indent + "OOPS!!! The description of a event cannot be empty.");
                } else {
                    temp = temp.substring(5);
                    String[] arrOfStr = temp.split("/");
                    if (arrOfStr.length < 2) {
                        System.out.println(indent + "Please provide the start time of the event.");
                    } else if (arrOfStr.length < 3) {
                        System.out.println(indent + "Please provide the end time of the event.");

                    } else {
                        String des = arrOfStr[0].trim();
                        String start = arrOfStr[1].substring(4).trim();
                        String end = arrOfStr[2].substring(2).trim();
                        Event t = new Event(des, start, end);
                        list.add(t);
                        System.out.println(indent + "Got it. I've added this task:");
                        System.out.println(indent + "  " + t.toString());
                        System.out.println(indent + "Now you have " + list.size() + " tasks in the list.");
                    }
                }
                System.out.println(indent + "------------------------------------------------");

                // delete a task
            } else if (input.equals("delete")) {
                int position = sc.nextInt() - 1;
                System.out.println(indent + "------------------------------------------------");

                // check for error
                if (position + 1 > list.size()) {
                    System.out.println(indent + "Task does not exist");
                } else if (position < 0) {
                    System.out.println(indent + "Task does not exist");

                } else {
                    Task t = list.get(position);
                    list.remove(t);
                    System.out.println(indent + "Noted. I've removed this task:");
                    System.out.println(indent + "  " + t.toString());
                    System.out.println(indent + "Now you have " + list.size() + " tasks in the list.");
                }
                System.out.println(indent + "------------------------------------------------");

            } else {
                System.out.println(indent + "------------------------------------------------");
                System.out.println(indent + "Sorry this is an invalid input :(");
                System.out.println(indent + "------------------------------------------------");
            }
        }
    }
}

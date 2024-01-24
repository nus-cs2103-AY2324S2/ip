import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {

        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        String lineBreak = "\t\t------------------------------------------";
        String options = "\t\t1. To create a 'To Do': todo <description>" +
                "\n\t\t2. To create a 'Deadline': deadline <description> /by <by>" +
                "\n\t\t3. To create a 'Event': event <description> /from <from> /to <to>" +
                "\n\t\t4. To mark task as done: mark <item number in list>" +
                "\n\t\t5. To mark task as undone: unmark <item number in list>" +
                "\n\t\t6. To exit: bye";

        System.out.println(lineBreak);
        System.out.println("\t\tHello! I'm Duchess");
        System.out.println("\t\t--------------How to Use Me--------------");
        System.out.println(options);
        System.out.println(lineBreak);

        Scanner scan = new Scanner( System.in );
        String input = scan.nextLine();
        ArrayList<Task> list = new ArrayList<>();

        while (!input.equalsIgnoreCase("bye")) {
            try {
                if (input.equalsIgnoreCase("list")) {
                    System.out.println(lineBreak);
                    System.out.println("\t\tHere are the tasks in your list:");
                    for (int i = 1; i <= list.size(); i++) {
                        Task t = list.get(i - 1);
                        System.out.println("\t\t" + i + "." + t);
                    }
                    System.out.println(lineBreak);
                }


                else if (input.toUpperCase().contains("TODO")) {
                    System.out.println(lineBreak);
                    String[] shortened_input = input.split("todo ");
                    try {
                        Task t = new ToDo(shortened_input[1]);
                        list.add(t);
                        System.out.println("\t\tGot it. I've added this task: \n\t\t  " + t);
                        System.out.println("\t\tNow you have " + list.size() + " tasks in the list.");
                        System.out.println(lineBreak);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("\t\tOOPS!!! The description of a todo cannot be empty.");
                        System.out.println("\t\tTo create a 'To Do': todo <description>");
                        System.out.println(lineBreak);
                    }
                }

                else if (input.toUpperCase().contains("DEADLINE")) {
                    System.out.println(lineBreak);
                    String[] shortened_input = input.split("deadline ");
                    boolean descriptionAvailable = false;
                    try {
                        String[] inputArray = shortened_input[1].split(" /by ");
                        descriptionAvailable = true;
                        Task t = new Deadline(inputArray[0], inputArray[1]);
                        list.add(t);
                        System.out.println("\t\tGot it. I've added this task: \n\t\t  " + t);
                        System.out.println("\t\tNow you have " + list.size() + " tasks in the list.");
                        System.out.println(lineBreak);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        if (!descriptionAvailable) {
                            System.out.println("\t\tOOPS!!! The description of a deadline cannot be empty.");
                        } else {
                            System.out.println("\t\tOOPS!!! The 'by' of a deadline cannot be empty.");
                        }
                        System.out.println("\t\tTo create a 'Deadline': deadline <description> /by <by>");
                        System.out.println(lineBreak);
                    }
                }


                else if (input.toUpperCase().contains("EVENT")) {
                    System.out.println(lineBreak);
                    String[] shortened_input = input.split("event ");
                    boolean descriptionAvailable = false;
                    boolean fromAvailable = false;
                    try {
                        String[] inputArray = shortened_input[1].split(" /from ");
                        descriptionAvailable = true;
                        String[] from_to = inputArray[1].split(" /to ");
                        String from = from_to[0];
                        fromAvailable = true;
                        String to = from_to[1];
                        Task t = new Event(inputArray[0], from_to[0], from_to[1]);
                        list.add(t);
                        System.out.println("\t\tGot it. I've added this task: \n\t\t  " + t);
                        System.out.println("\t\tNow you have " + list.size() + " tasks in the list.");
                        System.out.println(lineBreak);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        if (!descriptionAvailable) {
                            System.out.println("\t\tOOPS!!! The description of an event cannot be empty.");
                        } else if (!fromAvailable) {
                            System.out.println("\t\tOOPS!!! The 'from' of an event cannot be empty.");
                        } else {
                            System.out.println("\t\tOOPS!!! The 'to' of an event cannot be empty.");
                        }
                        System.out.println("\t\tTo create a 'Event': event <description> /from <from> /to <to>");
                        System.out.println(lineBreak);
                    }
                }


                else if (input.toUpperCase().contains("UNMARK")) {
                    try {
                        System.out.println(input.charAt(7));
                        int item_index = Character.getNumericValue(input.charAt(7));
                        Task t = list.get(item_index - 1);
                        t.markAsUndone();
                        System.out.println(lineBreak);
                        System.out.println("\t\tOK, I've marked this task as not done yet:");
                        System.out.println("\t\t  " + t);
                        System.out.println(lineBreak);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println(lineBreak);
                        System.out.println("\t\tOOPS!!! Please specify a valid task number.");
                        System.out.println(lineBreak);
                        input = "list";
                        continue;
                    } catch (IndexOutOfBoundsException f) {
                        System.out.println(lineBreak);
                        System.out.println("\t\tOOPS!!! Please specify a valid task number.");
                        System.out.println(lineBreak);
                        input = "list";
                        continue;
                    }
                }


                else if (input.toUpperCase().contains("MARK")) {
                    try {
                        int item_index = Character.getNumericValue(input.charAt(5));
                        Task t = list.get(item_index - 1);
                        t.markAsDone();
                        System.out.println(lineBreak);
                        System.out.println("\t\tNice! I've marked this task as done:");
                        System.out.println("\t\t  " + t);
                        System.out.println(lineBreak);
                    }
                    catch (StringIndexOutOfBoundsException e) {
                        System.out.println(lineBreak);
                        System.out.println("\t\tOOPS!!! Please specify a valid task number.");
                        System.out.println(lineBreak);
                        input = "list";
                        continue;
                    }
                    catch (IndexOutOfBoundsException f) {
                        System.out.println(lineBreak);
                        System.out.println("\t\tOOPS!!! Please specify a valid task number.");
                        System.out.println(lineBreak);
                        input = "list";
                        continue;
                    }
                }

                else {
                    throw new DukeException();
                }
            }

            catch (DukeException e) {
                System.out.println(lineBreak);
                System.out.println("\t\tOOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("\t\t--------------How to Use Me--------------");
                System.out.println(options);
                System.out.println(lineBreak);
            }
            input = scan.nextLine();
        }

        System.out.println(lineBreak);
        System.out.println("\t\tBye. Hope to see you again soon!");
        System.out.println(lineBreak);

    }
}
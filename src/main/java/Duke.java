import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
     static ArrayList<String> commands = new ArrayList<String>();

    public static void main(String[] args) {
        commands.add("help");
        commands.add("list");
        commands.add("todo");
        commands.add("deadline");
        commands.add("event");
        commands.add("mark");
        commands.add("unmark");
        commands.add("bye");


        Scanner scan = new Scanner(System.in);

        System.out.println("Hello! My name is Mitsuki, nice to meet you!\n" + "What can I do for you today?\n");


        ArrayList<Task> list = new ArrayList<Task>();
        String command = "nil";

        while (!command.equals(null)) {
            command = scan.next();
            try {
                DukeException.validate(command);
            } catch(DukeException ex) {
                System.out.println("Sorry, I am unable to do that at the current moment.");
                System.out.println("Please type 'help' for a list of commands you can give me! :)");
            }

            switch (command) {
                case "help":
                    System.out.println("Here is a list of commands you can give me:");
                    for (int i = 0; i < commands.size(); i++) {
                        int j = i + 1;
                        System.out.println(j + ". " + commands.get(i));
                    }
                    break;
                case "deadline":
                    String fullString = scan.nextLine();
                    try {
                        EmptyTaskException.validate(fullString);
                    } catch(EmptyTaskException ex) {
                        System.out.println("Please give a description for your deadline item. Try again!");
                        break;
                    }

                    String[] tokens = fullString.split("/");
                    try {
                        MissingDeadlineException.validate(tokens);
                    } catch(MissingDeadlineException ex) {
                    System.out.println("Please give a deadline for your deadline item. Try again! XD");
                    System.out.println("E.g. Type 'deadline return book /by Sunday 2pm' to add the task 'return book' " +
                            "\nwith a deadline of 'Sunday 2pm' to your list.");
                    break;
                    }

                    String description = tokens[0];
                    String trimmed = description.trim();
                    String by = tokens[1];
                    Task task = new Deadline(trimmed, by);
                    list.add(task);
                    System.out.println("OK, I have added the task '" + trimmed + "' to your list! :)");
                    System.out.println("Now you have " + list.size() + " task(s) in the list.");
                    break;
                case "todo":
                    String description1 = scan.nextLine();
                    try {
                        EmptyTaskException.validate(description1);
                    } catch(EmptyTaskException ex) {
                        System.out.println("Please give a description for your todo item. Try again!");
                        break;
                    }

                    String trimmed1 = description1.trim();
                    Task task1 = new Todo(trimmed1);
                    list.add(task1);
                    System.out.println("OK, I have added the task '" + trimmed1 + "' to your list! :)");
                    System.out.println("Now you have " + list.size() + " task(s) in the list.");
                    break;
                case "event":
                    String fullString1 = scan.nextLine();
                    try {
                        EmptyTaskException.validate(fullString1);
                    } catch(EmptyTaskException ex) {
                        System.out.println("Please give a description for your event item. Try again!");
                        break;
                    }

                    String[] tokens1 = fullString1.split("/");
                    try {
                        MissingEventTimingException.validate(tokens1);
                    } catch(MissingEventTimingException ex) {
                        System.out.println("Please give a time period for your event item. Try again! XD");
                        System.out.println("E.g. Type 'event meeting /From Monday 10am /to 12pm' to add the task 'meeting' \n" +
                                "with a time period 'From Monday 10am to 12pm' to your list.");
                        break;
                    }

                    String description2 = tokens1[0];
                    String trimmed2 = description2.trim();
                    String from = tokens1[1];
                    String to = tokens1[2];
                    String toTrimmed = to.trim();
                    Task task2 = new Event(trimmed2, from, toTrimmed);
                    list.add(task2);
                    System.out.println("OK, I have added the task '" + trimmed2 + "' to your list! :)");
                    System.out.println("Now you have " + list.size() + " task(s) in the list.");
                    break;
                case "list":
                    System.out.println("Here are the items in your list:");
                    int i = 0;
                    int j = 1;
                    while (i < list.size()) {
                        System.out.println(j + ". " + list.get(i).toString());
                        i++;
                        j++;
                    }
                    break;
                case "mark":
                    int index = scan.nextInt();
                    Task theTask = list.get(index - 1);

                    try {
                        AlreadyDoneException.validate(theTask);
                    } catch(AlreadyDoneException ex) {
                        System.out.println("You have already completed this task! :D");
                        System.out.println(theTask);
                        break;
                    }

                    theTask.markAsDone();
                    System.out.println("Ok, I have marked this task as done. :D\n" + theTask);
                    break;
                case "unmark":
                    int index1 = scan.nextInt();
                    Task aTask = list.get(index1 - 1);

                    try {
                        WasNotDoneException.validate(aTask);
                    } catch(WasNotDoneException ex) {
                        System.out.println("You had not completed this task! :O");
                        System.out.println(aTask);
                        break;
                    }

                    aTask.markAsNotDone();
                    System.out.println("Ok, I have marked this task as not done yet. :O\n" + aTask);
                    break;
                case "bye":
                    System.out.println("Bye! Hope to see you again soon!\n");
                    scan.close();
                    System.exit(0);
                default:
                    break;
            }
        }



    }
}

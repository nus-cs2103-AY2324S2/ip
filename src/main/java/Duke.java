import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) throws DukeException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String confirmation = "Got it. I've added this task:";
        String userInput;

        //Greetings
        String name = "Tommy";
        String divider = "____________________________";
        System.out.println(divider);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(divider);

        while (scanner.hasNextLine()) {
            try {
                userInput = scanner.nextLine();

                if (userInput.contains("unmark")) {
                    // Unmark the tasks

                    int indexToUnmark = Integer.parseInt(userInput.substring(7));
                    validateIndex(indexToUnmark, tasks.size());

                    Task taskToUnmark = tasks.get(indexToUnmark - 1);
                    taskToUnmark.isDone = false;

                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + taskToUnmark.toString());

                    System.out.println(divider);

                }
                else if (userInput.contains("mark")) {

                    int indexToMark = Integer.parseInt(userInput.substring(5));
                    validateIndex(indexToMark, tasks.size());

                    Task taskToMark = tasks.get(indexToMark - 1);
                    taskToMark.isDone = true;

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + taskToMark.toString());

                    System.out.println(divider);

                }
                else if (userInput.equals("list")) {
                    // listing

                    System.out.println("Here are the tasks in your list:");
                    int length = tasks.size();
                    for (int i = 0; i < length; i++) {
                        Task task = tasks.get(i);
                        System.out.println( i + 1 + "." + task.toString());
                    }

                    System.out.println(divider);

                }
                else if (userInput.contains("todo")) {
                    // to-do

                    // validating data
                    String desc = userInput.substring(4);
                    validateDesc(desc);

                    Todo todo = new Todo(desc);
                    tasks.add(todo);
                    System.out.println(confirmation);
                    System.out.println("  " + todo.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    System.out.println(divider);

                }
                else if (userInput.contains("deadline")) {
                    // Deadline

                    //validate non-empty desc
                    String stringCheck = userInput.substring(8);
                    validateDesc(stringCheck);

                    //extract date
                    int indexOfBy = userInput.indexOf("/by");
                    int indexOfDate = indexOfBy + 4;
                    validateFormat(indexOfBy);
                    validateDate(indexOfDate, userInput.length());

                    String deadlineDetails = userInput.substring(8, indexOfBy - 1);
                    validateDesc(deadlineDetails);

                    String dateOfDeadline = userInput.substring(indexOfDate);

                    String desc =  deadlineDetails + " (by: " + dateOfDeadline + ")";


                    Deadline deadline = new Deadline(desc, dateOfDeadline);
                    tasks.add(deadline);
                    System.out.println(confirmation);
                    System.out.println("  " + deadline.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    System.out.println(divider);
                }
                else if (userInput.contains("event")) {
                    // Event

                    // validate non-empty string
                    String stringCheck = userInput.substring(5);
                    validateDesc(stringCheck);

                    //extract dates
                    int indexOfFrom = userInput.indexOf("/from");
                    int indexOfTo = userInput.indexOf("/to");

                    validateFormat(indexOfFrom);
                    validateFormat(indexOfTo);

                    int startOfFromDate = indexOfFrom + 6;
                    int endOfFromDate = indexOfTo - 1;

                    int startOfToDate = indexOfTo + 4;
                    validateDate(startOfFromDate, endOfFromDate);
                    validateDate(startOfToDate, userInput.length());


                    String from = userInput.substring(startOfFromDate, endOfFromDate);
                    String to = userInput.substring(startOfToDate);

                    String eventDetails = userInput.substring(5, indexOfFrom - 1);
                    validateDesc(eventDetails);

                    String desc = eventDetails + " (from: " + from + " to: " + to + ")";

                    Event event = new Event(desc, from, to);
                    tasks.add(event);
                    System.out.println(confirmation);
                    System.out.println("  " + event.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    System.out.println(divider);
                }
                else if (userInput.contains("delete")) {


                    int indexToDelete = Integer.parseInt(userInput.substring(7));
                    validateIndex(indexToDelete, tasks.size());

                    Task taskToDelete = tasks.get(indexToDelete - 1);
                    String desc = taskToDelete.toString();
                    tasks.remove(taskToDelete);

                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + desc);

                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    System.out.println(divider);

                }
                else if (userInput.equals("bye")) {
                    break;
                } else {
                    throw new DukeException("Invalid command");
                }

            } catch (DukeException e) {
                System.out.println("Oops: " + e.getMessage());
                System.out.println(divider);
            }
        }

        //Farewell
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(divider);

        scanner.close();
    }


    public static void validateDesc(String desc) throws DukeException {
        if (desc.isBlank()) {
            throw new DukeException("The description cannot be empty :(");
        }
    }

    public static void validateDate(int date, int length) throws DukeException {
        if (date > length - 1) {
            throw new DukeException("The date cannot be empty :(");
        }
    }

    public static void validateFormat(int index) throws DukeException {
        if (index == -1) {
            throw new DukeException("Something is wrong with the format!");
        }
    }
    public static void validateIndex(int index, int length) throws DukeException {
        if (index > length || index <= 0) {
            throw new DukeException("Invalid index");
        }
    }
}




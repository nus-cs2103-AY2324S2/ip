import java.util.ArrayList;
import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String name = "Lunaris";
        String indentation = "  ";
        String indentedLine = "  _________________________________________________________";
        // Just for convenience of copy paste.
        // System.out.println(indentedLine);

        // Print out greeting message
        System.out.println(indentedLine);
        System.out.println(indentation + "Hey! I'm " + name + "\n"
            + indentation + "Is there anything I can do for you?");
        System.out.println(indentedLine);

        ArrayList<Task> inputList = new ArrayList<>();

        /*
        Main body of addList task.
        Handle case where input is "bye" or "list".
        Add input to arrayList otherwise.
        */
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String[] array = input.split(" ", 2);
            String category = array[0];
            int taskId = 0;
            try {
                if (input.contains("mark") || input.contains("unmark") || input.contains("delete")) {
                    taskId = Integer.parseInt(array[1]) - 1; // subtract 1 for actual index.
                }
                // Farewell message.
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(indentedLine);
                    System.out.println(indentation +
                            "Leaving so soon? Alright, have a great day ahead!");
                    System.out.println(indentedLine);
                    break;
                }
                // Display the current list of tasks.
                else if (input.equalsIgnoreCase("list")) {
                    System.out.println(indentedLine);
                    System.out.println(indentation + "Here are the tasks in your list:");
                    for (int i = 0; i < inputList.size(); i++) {
                        Task currTask = inputList.get(i);
                        System.out.println(indentation + (i + 1) + "." + currTask.toString());
                    }
                    System.out.println(indentedLine);
                }
                // For Unmarking selected task exception.
                else if (input.equalsIgnoreCase("unmark")) {
                    System.out.println(indentedLine);
                    throw new DukeException("Sorry, but which task do you want me to unmark?");
                }
                // For Unmarking selected task.
                else if (category.startsWith("unmark")) {
                    inputList.get(taskId).markNotDone();
                    System.out.println(indentedLine);
                    System.out.println(indentation + "Ok, I've marked this task as not done yet:");
                    System.out.println(inputList.get(taskId).toString());
                    System.out.println(indentedLine);
                }
                // For Marking selected task exception.
                else if (input.equalsIgnoreCase("mark")) {
                    System.out.println(indentedLine);
                    throw new DukeException("Sorry, but which task do you want me to mark?");
                }
                // For Marking selected task.
                else if (category.startsWith("mark")) {
                    inputList.get(taskId).markDone();
                    System.out.println(indentedLine);
                    System.out.println(indentation + "Nice! I've marked this task as done:");
                    System.out.println(inputList.get(taskId).toString());
                    System.out.println(indentedLine);
                }
                // For deleting selected task exception.
                else if (input.equalsIgnoreCase("delete")) {
                    System.out.println(indentedLine);
                    throw new DukeException("Sorry, but which task do you want me to delete?");
                }
                // For deleting selected task.
                else if (category.startsWith("delete")) {
                    inputList.remove(taskId - 1);
                    System.out.println(indentedLine);
                    System.out.println(indentation + "Noted. I've removed this task:");
                    System.out.println(indentation + inputList.get(taskId - 1).toString());
                    System.out.println(indentation + "Now you have " + inputList.size() + " tasks in the list.");
                    System.out.println(indentedLine);
                }
                // For todos exception.
                else if (input.equalsIgnoreCase("todo")) {
                    System.out.println(indentedLine);
                    throw new DukeException("Sorry, please give me a description of the todo as well! >.<\n" +
                            indentation + "Format should be todo (description)!");
                }
                // For todos.
                else if (category.startsWith("todo")) {
                    System.out.println(indentedLine);
                    System.out.println(indentation + "Got it. Added this task:");
                    ToDo toDo = new ToDo(array[1]);
                    inputList.add(toDo);
                    System.out.println(indentation + toDo);
                    System.out.println(indentation + "Now you have " + inputList.size() + " tasks in the list.");
                    System.out.println(indentedLine);
                }
                // For Deadlines exception.
                else if (input.equalsIgnoreCase(("deadline"))) {
                    System.out.println(indentedLine);
                    throw new DukeException("Sorry, please give me a description of the deadline as well! >.<\n" +
                            indentation + "Format should be deadline (description) /by (date)!");
                }
                // For Deadlines.
                else if (input.startsWith("deadline")) {
                    System.out.println(indentedLine);
                    System.out.println(indentation + "Got it. Added this task:");
                    String[] arguments = array[1].split(" /by ");
                    Deadline deadline = new Deadline(arguments[0], arguments[1]);
                    inputList.add(deadline);
                    System.out.println(indentation + deadline);
                    System.out.println(indentation + "Now you have " + inputList.size() + " tasks in the list.");
                    System.out.println(indentedLine);
                }
                // For Events exception.
                else if (input.equalsIgnoreCase("event")) {
                    System.out.println(indentedLine);
                    throw new DukeException("Sorry, please give me a description of the event as well! >.<\n" +
                            indentation + "Format should be event (description) /from (time) /to (time)!");
                }
                // For Events.
                else if (input.startsWith("event")) {
                    System.out.println(indentedLine);
                    System.out.println(indentation + "Got it. Added this task:");
                    String[] description = array[1].split(" /from ");
                    String[] duration = description[1].split(" /to ");
                    Event event = new Event(description[0], duration[0], duration[1]);
                    inputList.add(event);
                    System.out.println(indentation + event);
                    System.out.println(indentation + "Now you have " + inputList.size() + " tasks in the list.");
                    System.out.println(indentedLine);
                }
                // For unregistered commands.
                else {
                    System.out.println(indentedLine);
                    throw new DukeException("Sorry, I cannot understand what this is!");
                }
            }
            catch (DukeException e) {
                System.out.println(indentation + e.getMessage());
                System.out.println(indentedLine);
            }
        }
    }
}

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm AcademicWeapon");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        String action;
        ArrayList<Task> lst = new ArrayList<>();
        do {
        String input = br.readLine();
        String[] inputParts = input.split(" ", 2);
        action = inputParts[0];
        String parameters;
        if (inputParts.length == 2) {
            parameters = inputParts[1];
        } else {
            parameters = " ";
        }

        try {
            DukeExceptions.validateInput(action, parameters);
        } catch (DukeExceptions e) {
            System.out.println("____________________________________________________________");
            System.out.println(e.getMessage());
            System.out.println("____________________________________________________________");
            continue;
        }

            switch (action) {
                case "list":
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < lst.size(); i++) {
                        Task currentTask = lst.get(i);
                        System.out.println((i + 1) + "." + currentTask.toString());
                    }
                    System.out.println("____________________________________________________________");
                    break;
                case "mark":
                    int indexToMark = Integer.parseInt(inputParts[1]);
                    Task markTask = lst.get(indexToMark - 1);
                    markTask.markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(markTask.toString());
                    System.out.println("____________________________________________________________");
                    break;
                case "unmark":
                    int indexToUnmark = Integer.parseInt(inputParts[1]);
                    Task unmarkTask = lst.get(indexToUnmark - 1);
                    unmarkTask.markAsNotDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(unmarkTask.toString());
                    System.out.println("____________________________________________________________");
                    break;
                case "todo":
                    Task addToDoTask = new Todo(inputParts[1]);
                    lst.add(addToDoTask);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(addToDoTask.toString());
                    System.out.println("Now you have " + lst.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                case "deadline":
                    String[] splitAgain = inputParts[1].split("/by");
                    Task addDeadlineTask = new Deadline(splitAgain[0], splitAgain[1]);
                    lst.add(addDeadlineTask);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(addDeadlineTask.toString());
                    System.out.println("Now you have " + lst.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                case "event":
                    String[] splitOnce = inputParts[1].split("/from ");
                    String message = splitOnce[0];
                    String[] splitTwice = splitOnce[1].split("/to ");
                    Task addEventTask = new Event(message, splitTwice[0], splitTwice[1]);
                    lst.add(addEventTask);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(addEventTask.toString());
                    System.out.println("Now you have " + lst.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                case "bye":
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                default:
                    System.out.println("____________________________________________________________");
                    System.out.println("added: " + input);
                    Task toAddTask = new Task(input);
                    lst.add(toAddTask);
                    System.out.println("____________________________________________________________");
            }


        } while(!action.equals("bye"));

        br.close();
    }
}

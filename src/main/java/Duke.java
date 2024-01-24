import javax.swing.plaf.TableHeaderUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    //Initialise variables for the programs
    private static final String bar = "____________________________________________________________";
    private static final String first = " Hello! I'm Pluiexo";
    private static final String second = " What can I do for you?";
    private static final String third = " Bye. Hope to see you again soon!";
    private static final String[] greet = new String[]{bar, first, second, bar};
    private static final String[] bye = new String[]{bar, third, bar};

    //Strings for listing Response
    private static final String listingResponse = "Here are the tasks in your list:";

    //Strings for marking and unmarking
    private static final String markResponse = "Nice! I've marked this task as done:";
    private static final String unmarkResponse = "OK, I've marked this task as not done yet:";

    //String and variables for task
    private static final String addTask = "Got it. I've added this task:";


    private static String manHandleUser(String msg) {
        //Used when your user does not know how to use the application
        switch (msg) {
            case "description":
                return " OOPS!!! The description of this command cannot be empty.";
            case "from":
                return " OOPS!!! the from date .";
            case "by":
                return " OOPS!!! the end date cannot be empty.";
            case "dateError":
                return "OOPS!!! Missing or invalid format of the date command";
            case "number":
                return "OOPS!!! This is missing your index number";
            case "invalid":
                return "OOPS!!! I'm sorry, but I don't know what that means Maybe learn how to spell :(";
            default:
                return "invalid application commence self-destruct";
        }
    }


    public static void main(String[] args) throws IOException {
        TaskManager manager = new TaskManager();
        //Greet first
        String indent = "   ";
        for (String l : greet) {
            System.out.println(indent + l);
        }
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = false;

        while (true) {

            String next = input.readLine();
            if (next.equals("bye")) {
                //Goodbye
                for (String l : bye) {
                    System.out.println(indent + l);
                }
                break;
            }
            //This is and arraylist for managing the output items
            ArrayList<String> output = new ArrayList<>();
            output.add(bar);
            try {
                if (next.equals("list")) {
                    output.add(listingResponse);
                    output.addAll(manager.ListItems());
                } else {
                    //Switch statements does not support contains methods :(
                    //unmark has to come first because unmark contains mark
                    //BufferedReader is supports reading line only unlike Scanner class
                    Task item;
                    if (next.contains("unmark")) {
                        item = manager.mangeTask(Actions.UNMARK, next);
                        output.add(unmarkResponse);
                        output.add(indent + item);
                    } else if (next.contains("mark")) {
                        item = manager.mangeTask(Actions.MARK, next);
                        output.add(markResponse);
                        output.add(indent + item);

                    } else if (next.contains("todo")) {
                        item = manager.addTask(Tasks.TODO, next);
                        output.add(addTask); // This is the first line
                        output.add(indent + item);
                        output.add(manager.numOfTask());

                    } else if (next.contains("deadline")) {
                        item = manager.addTask(Tasks.DEADLINE, next);
                        output.add(addTask);
                        output.add(indent + item);
                        output.add(manager.numOfTask());

                    } else if (next.contains("event")) {
                        item = manager.addTask(Tasks.EVENT, next);
                        output.add(addTask);
                        output.add(indent + item);
                        output.add(manager.numOfTask());

                    } else {
                        //At this point this doest not really makes sense but will still be here
                        //For error handling here
                        throw new DukeException("invalid");
                    }
                }
            } catch (DukeException e) {
                output.add(indent + manHandleUser(e.getMessage()));
            } catch (NumberFormatException e) {
                output.add(indent + " OPPPS!!!!That is not a number!!!!!!!!!!");
            }
            output.add(bar);
            for (String l : output) {
                System.out.println(indent + l);
            }
            System.out.println();
        }

    }
}

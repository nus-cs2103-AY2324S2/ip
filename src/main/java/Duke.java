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

    //For data storage
    private static final ArrayList<Task> items = new ArrayList<>();

    //String and variables for task
    private static final String addTask = "Got it. I've added this task:";

    //For Managing input
    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private static String next;

    public static void main(String[] args) throws IOException {
        TaskManager manager = new TaskManager();
        //Greet first
        String indent = "   ";
        for (String l : greet) {
            System.out.println(indent + l);
        }

        while (!(next = input.readLine()).equals("bye")) {
            //This is and arraylist for managing the output items
            ArrayList<String> output = new ArrayList<>();
            output.add(bar);
            if (next.equals("list")) {
                output.add(listingResponse);
                output.addAll(manager.ListItems());
            } else {
                //Switch statements does not support contains methods :(
                //unmark has to come first because unmark contains mark
                //BufferedReader is supports reading line only unlike Scanner class
                if (next.contains("unmark")) {
                    output.add(unmarkResponse);
                    output.add(indent + manager.mangeTask(Actions.UNMARK, next));
                } else if (next.contains("mark")) {
                    output.add(markResponse);
                    output.add(indent + manager.mangeTask(Actions.MARK, next));

                } else if (next.contains("todo")) {
                    output.add(addTask); // This is the first line
                    output.add(indent + manager.addTask(Tasks.TODO, next));
                    output.add(manager.numOfTask());

                } else if (next.contains("deadline")) {
                    output.add(addTask);
                    output.add(indent + manager.addTask(Tasks.DEADLINE, next));
                    output.add(manager.numOfTask());

                } else if (next.contains("event")) {
                    output.add(addTask);
                    output.add(indent + manager.addTask(Tasks.EVENT, next));
                    output.add(manager.numOfTask());

                } else {
                    //At this point this doest not really makes sense but will still be here
                    //For error handling here
                }
            }
            output.add(bar);
            for (String l : output) {
                System.out.println(indent + l);
            }
            System.out.println();
        }
        //Goodbye
        for (String l : bye) {
            System.out.println(indent + l);
        }

    }
}

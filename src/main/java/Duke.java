import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {
        //Initialiser for the programss
        String bar = "____________________________________________________________";
        String indent = "   ";
        String first = " Hello! I'm Pluiexo";
        String second = " What can I do for you?";
        String third = " Bye. Hope to see you again soon!";
        String[] greet = new String[]{bar, first, second, bar};
        String[] bye = new String[]{bar, third, bar};

        //Strings for listing Respoinse
        String listingResponse = "Here are the tasks in your list:";

        //Strings for marking and unmarking
        String markResponse = "Nice! I've marked this task as done:";
        String unmarkResponse = "OK, I've marked this task as not done yet:";

        //For data storage
        ArrayList<Task> items = new ArrayList<>();

        //For Managing input
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String next;

        //Greet first
        for (String l : greet) {
            System.out.println(indent + l);
        }

        while (!(next = input.readLine()).equals("bye")) {
            //This is and arraylist for managing the output items
            ArrayList<String> output = new ArrayList<>();
            output.add(bar);
            if (next.equals("list")) {
                int i = 1;
                output.add(listingResponse);
                for (Task item : items) {
                    output.add(" " + i + "." + item);
                    i++;
                }
            } else {
                //Switch statements does not support contains methods :(
                //unmark has to come first because unmark contains mark
                //BufferedReader is supports reading line only unlike Scanner class
                if (next.contains("unmark")) {
                    int id = Integer.parseInt(next.split(" ")[1]) - 1; //Index 0 based
                    Task currentItem = items.get(id);
                    currentItem.unmark();
                    output.add(unmarkResponse);
                    output.add(indent + currentItem);
                } else if (next.contains("mark")) {
                    int id = Integer.parseInt(next.split(" ")[1]) - 1; //Index 0 based
                    Task currentItem = items.get(id);
                    currentItem.markAsDone();
                    output.add(markResponse);
                    output.add(indent + currentItem);
                } else {
                    output.add(" added: " + next);
                    items.add(new Task(next));
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

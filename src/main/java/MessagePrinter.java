/* Handles printing messages  */

import java.util.ArrayList;

public class MessagePrinter {
    // Prints the List message with the given list of tasks
    public static void PrintStorage(ArrayList<Task> _tasks) {
        System.out.println("| Here's everything that I'd stored!");
        int i = 1;
        for(Task t : _tasks) {
            String s = "| " + i + ".[";
            if (t.IsMarked()) {
                s += "X";
            } else {
                s += " ";
            }
            s += "] "  + t.Name();

            System.out.println(s);
            i++;
        }
    }

    // Prints the storing message
    public static void PrintStoring() {
        String msg = "| Sure I'll store it right away!";
        System.out.println(msg);
    }

    // Prints the invalid message
    public static void PrintInvalid() {
        String msg = "| I'm not sure what that command is...";
        System.out.println(msg);
    }

    // Prints the echo of a given string
    public static void Echo(String _input) {
        String msg = "| Echoing input \n| " + _input;
        System.out.println(msg);
    }

    // Prints the greeting message when the user enters
    public static void PrintGreeting() {
        String msg = "| Welcome! I'm JAV\n"
                   + "| How may I sprinkle a bit of happiness into your day today?";
        System.out.println(msg);
    }

    // Prints the farewell message when the user exits
    public static void PrintExit() {
        String msg = "| Farewell!\n"
                   + "| May your days be filled with laughter and warmth!";
        System.out.println(msg);
    }

    // Prints the marking message
    public static void PrintMarkingTask() {
        String msg = "| Fantastic news!\n"
                   + "| You've just upgraded that task from a to-do to a ta-daa!";
        System.out.println(msg);
    }

    // Prints the unmarking message
    public static void PrintUnmarkingTask() {
        String msg = "| Task status reversed!\n"
                   + "| Sometimes even completed tasks could use an encore.";
        System.out.println(msg);
    }

    // Prints the unmarking message
    public static void PrintInvalidParameters() {
        String msg = "| It appears the parameters might be doing a little dance of confusion!\n"
                   + "| Could you please check the parameters and give them another whirl?";
        System.out.println(msg);
    }
}
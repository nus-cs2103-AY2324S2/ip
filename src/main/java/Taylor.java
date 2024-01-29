import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.TaylorException;
import executes.*;
import filehandler.FileInput;
import tasks.Task;

/**
 * Main class to execute Taylor ChatBot
 */
public class Taylor {
    enum Activity {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, SEARCH, FIND, INVALID
    }

    public static void main(String[] args) {
        List<Task> listing = new ArrayList<>();

        try {
            listing = FileInput.execInput(listing);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Hello! I'm Taylor");
        System.out.println("What can I do for you?");
        Scanner type = new Scanner(System.in);


        label:
        while (true) {
            String input = type.nextLine();

            if (input.isBlank()) {
                System.out.println("Input is empty, please enter something.");

            } else {
                String[] act = input.split(" ", 2);
                String action = act[0];

                Activity activity = getActivity(action);
                switch (activity) {
                    case BYE:
                        break label;

                    case LIST:
                        ListTask.exec(listing);

                        break;
                    case MARK:
                    case UNMARK:
                        try {
                            MarkTask.exec(input, listing);
                        } catch (TaylorException err) {
                            System.out.println("Error: " + err.getMessage());
                        }

                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        try {
                            InsertTask.exec(input, listing);
                        } catch (TaylorException err) {
                            System.out.println("Error: " + err.getMessage());
                        }
                        break;
                    case DELETE:
                        try {
                            DeleteTask.exec(input, listing);
                        } catch (TaylorException err) {
                            System.out.println("Error: " + err.getMessage());
                        }
                        break;
                    case SEARCH:
                        try {
                            SearchTask.exec(act[1], listing);
                        } catch (TaylorException err) {
                            System.out.println("Error: " + err.getMessage());
                        }
                        break;
                    case FIND:
                        try {
                            FindTask.exec(act[1], listing);
                        } catch (TaylorException err) {
                            System.out.println("Error: " + err.getMessage());
                        }
                        break;
                    default:
                        System.out.println("Invalid input. ChatBot can only handle "
                                + "'todo', 'deadline', 'event', 'bye', 'list' tasks");
                        break;
                }
            }

            try {
                FileInput.execOutput(listing);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        type.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static Activity getActivity(String action) {
        try {
            return Activity.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException err) {
            return Activity.INVALID;
        }
    }
}

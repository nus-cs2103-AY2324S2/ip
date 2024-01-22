import Actions.Action;

import Exceptions.DukeException;
import Executes.DeleteTask;
import Executes.InsertTask;
import Executes.ListTask;
import Executes.MarkTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Taylor {
    enum Activity {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Taylor");
        System.out.println("What can I do for you?");
        Scanner type = new Scanner(System.in);

        List<Action> listing = new ArrayList<>();

        label:
        while(true) {
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
                        } catch (DukeException err) {
                            System.out.println("Error: " + err.getMessage());
                        }

                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        try {
                            InsertTask.exec(input, listing);
                        } catch (DukeException err) {
                            System.out.println("Error: " + err.getMessage());
                        }
                        break;
                    case DELETE:
                        try {
                            DeleteTask.exec(input, listing);
                        } catch (DukeException err) {
                            System.out.println("Error: " + err.getMessage());
                        }
                        break;
                    case INVALID:
                        System.out.println("Invalid input. ChatBot can only handle " +
                                "'todo', 'deadline', 'event', 'bye', 'list' tasks");
                        break;
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        type.close();
    }

    private static Activity getActivity(String action) {
        try {
            return Activity.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException err) {
            return Activity.INVALID;
        }
    }
}

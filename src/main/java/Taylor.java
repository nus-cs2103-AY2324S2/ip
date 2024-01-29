import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.TaylorException;
import executes.DeleteTask;
import executes.FindTask;
import executes.InsertTask;
import executes.ListTask;
import executes.MarkTask;
import executes.SearchTask;
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
        List<Task> tasks = new ArrayList<>();
        // Load pre-existing task from Hard Disk
        try {
            tasks = FileInput.execInput(tasks);
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
                String[] userInputSplit = input.split(" ", 2);
                String actionCalled = userInputSplit[0];

                Activity activity = getActivity(actionCalled);
                // Switch between different calls
                switch (activity) {
                    case BYE:
                        break label;

                    case LIST:
                        ListTask.execListTask(tasks);

                        break;
                    case MARK:
                    case UNMARK:
                        try {
                            MarkTask.execMarkTask(input, tasks);
                        } catch (TaylorException err) {
                            System.out.println("Error: " + err.getMessage());
                        }

                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        try {
                            InsertTask.execInsertTask(input, tasks);
                        } catch (TaylorException err) {
                            System.out.println("Error: " + err.getMessage());
                        }
                        break;
                    case DELETE:
                        try {
                            DeleteTask.execDeleteTask(input, tasks);
                        } catch (TaylorException err) {
                            System.out.println("Error: " + err.getMessage());
                        }
                        break;
                    case SEARCH:
                        try {
                            SearchTask.execSearchTask(userInputSplit[1], tasks);
                        } catch (TaylorException err) {
                            System.out.println("Error: " + err.getMessage());
                        }
                        break;
                    case FIND:
                        try {
                            FindTask.exec(userInputSplit[1], tasks);
                        } catch (TaylorException err) {
                            System.out.println("Error: " + err.getMessage());
                        }
                        break;
                    default:
                        System.out.println("Invalid input. ChatBot can only handle "
                                + "'todo', 'deadline', 'event', 'bye', 'list' tasks");
                        break;
                }
                // Save Task into File in Hard Disk
                try {
                    FileInput.execOutput(tasks);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

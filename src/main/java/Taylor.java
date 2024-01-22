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

                switch (action) {
                    case "bye":
                        break label;

                    case "list":
                        ListTask.exec(listing);

                        break;
                    case "mark":
                    case "unmark":
                        try {
                            MarkTask.exec(input, listing);
                        } catch (DukeException err) {
                            System.out.println("Error: " + err.getMessage());
                        }

                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        try {
                            InsertTask.exec(input, listing);
                        } catch (DukeException err) {
                            System.out.println("Error: " + err.getMessage());
                        }
                        break;
                    case "delete":
                        try {
                            DeleteTask.exec(input, listing);
                        } catch (DukeException err) {
                            System.out.println("Error: " + err.getMessage());
                        }
                        break;
                    default:
                        System.out.println("Invalid input. ChatBot can only handle " +
                                "'todo', 'deadline', 'event', 'bye', 'list' tasks");
                        break;
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        type.close();
    }
}

import Actions.Action;

import Exceptions.DukeException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Taylor {
    enum Activity {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, SEARCH, INVALID
    }

    public static void main(String[] args) {
        List<Action> listing = new ArrayList<>();

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("taylor.txt"));
            listing = (List<Action>) ois.readObject();
            int pos = 1;
            for (Action act : listing) {
                System.out.println(pos++ + ". " + act);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.out.println("Please create file in " + System.getProperty("user.dir"));
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No past data");
        } catch (ClassCastException e) {
            System.out.println("Content are corrupted!");
        }

        System.out.println("Hello! I'm Taylor");
        System.out.println("What can I do for you?");
        Scanner type = new Scanner(System.in);


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
                    case SEARCH:
                        try {
                            SearchTask.exec(act[1], listing);
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

            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("taylor.txt"));
                oos.writeObject(listing);
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

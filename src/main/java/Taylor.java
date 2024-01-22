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
                        System.out.println("Here are the tasks in your list:");
                        int pos = 1;
                        if (listing.isEmpty()) {
                            System.out.println("List is empty.");
                        } else {
                            for (Action acting : listing) {
                                System.out.println(pos++ + ". " + acting);
                            }
                        }

                        break;
                    case "mark":
                    case "unmark":
                        try {
                            markTask(input, listing);
                        } catch (DukeException err) {
                            System.out.println("Error: " + err.getMessage());
                        }

                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        try {
                            insertTask(input, listing);
                        } catch (DukeException err) {
                            System.out.println("Error: " + err.getMessage());
                        }
                        break;
                    case "delete":
                        try{

                        } catch (DukeException err) {
                            System.out.println("Error: " err.getMessage());
                        }
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

    public static void markTask(String input, List<Action> actionList) throws DukeException {
        String[] markWhat = input.split(" ");
        String what = markWhat[0];

        try {
            int num = Integer.parseInt(markWhat[1]) - 1;

            if (num < 0 || num >= actionList.size()) {
                throw new DukeException("Invalid task number");
            }

            if (what.equals("mark")) {
                actionList.get(num).markIt();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(actionList.get(num));
            } else if (what.equals("unmark")) {
                actionList.get(num).unMark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(actionList.get(num));
            } else {
                throw new DukeException("Invalid command -  Only use mark/unmark");
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new DukeException("Please insert task number!");
        }
    }

    public static void insertTask(String input, List<Action> actionList) throws DukeException{
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isBlank()) {
            throw new DukeException("The description of the task is empty.");
        }
        String content = parts[1];

        switch (parts[0]) {
            case "todo":
                todoTask(content, actionList);
                break;
            case "deadline":
                deadlineTask(content, actionList);
                break;
            case "event":
                eventTask(content, actionList);
                break;
        }
    }

    public static void todoTask(String content, List<Action> actionList) {
        System.out.println("Got it. I've added this task:");
        Todo task = new Todo(content);
        actionList.add(task);
        System.out.println(task);
        System.out.println("Now you have " + actionList.size() + " tasks in the list.");
    }

    public static void deadlineTask(String content, List<Action> actionList) throws DukeException {
        try {
            String[] splitter = content.split("/by");

            if (splitter.length < 2 || splitter[0].trim().isBlank() || splitter[1].trim().isBlank()) {
                throw new DukeException("Invalid format. Please type in the following format: " +
                        "deadline <action> /by <time>");
            }

            System.out.println("Got it. I've added this task:");
            Deadline dl = new Deadline(splitter[0], splitter[1]);
            actionList.add(dl);
            System.out.println(dl);
            System.out.println("Now you have " + actionList.size() + " tasks in the list.");

        } catch (ArrayIndexOutOfBoundsException err) {
            throw new DukeException("Invalid format. Please type in the following format: deadline <action> /by <time>");
        }
    }

    public static void eventTask(String content, List<Action> actionList) throws DukeException{
        try {
            String[] splitter = content.split("/from");

            if (splitter.length < 2 || splitter[0].trim().isBlank() || splitter[1].trim().isBlank()) {
                throw new DukeException("Invalid format. Please type in the following format: " +
                        "event <action> /from <time> /to <time>");
            }

            try {
                String[] splitter1 = splitter[1].split("/to");

                if (splitter1.length < 2 || splitter1[0].trim().isBlank() || splitter1[1].trim().isBlank()) {
                    throw new DukeException("Invalid format. Please type in the following format: " +
                            "event <action> /from <time> /to <time>");
                }
                Event eve = new Event(splitter[0], splitter1[0], splitter1[1]);
                actionList.add(eve);

                System.out.println(eve);
                System.out.println("Now you have " + actionList.size() + " tasks in the list.");
            } catch (ArrayIndexOutOfBoundsException err) {
                throw new DukeException("Invalid format. Please type in the following format: " +
                        "event <action> /from <time> /to <time>");
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new DukeException("Invalid format. Please type in the following format: " +
                    "event <action> /from <time> /to <time>");
        }
    }

    public static void deleteTask(String input, List<Action> actionList) thr{
        String[] markWhat = input.split(" ");
        String what = markWhat[0];

        try {
            int num = Integer.parseInt(markWhat[1]) - 1;

            if (num < 0 || num >= actionList.size()) {
                throw new DukeException("Invalid task number");
            }
        } catch (Array)
    }
}

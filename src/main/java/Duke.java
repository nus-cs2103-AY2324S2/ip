import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a chat bot to keep track of user's tasks.
 */
public class Duke {

    private static ArrayList<Task> list = new ArrayList<>();
    private Ui ui;

    public static void main(String[] args) {
        new Duke().run();
    }

    public Duke() {
        ui = new Ui();
        try {
            Storage.loadFileContents();
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showIntro();

        String original = sc.nextLine();

        while (!original.equals("bye")) {
            try {
                processLine(original);
            } catch (DukeException e) {
                ui.showLoadingError(e.getMessage());
            }
            original = sc.nextLine();
        }

        try {
            Storage.writeToFile(list);
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
        }
        ui.showOutro();
        sc.close();
    }

    /**
     * Returns message string for "delete" action.
     *
     * @param i index of task to delete.
     * @return string to output for "delete" action.
     */
    public static String deleteMessage(int i) {
        String output = "____________________________________________________________\n"
                + " Noted. I've removed this task:\n"
                + "   " + list.get(i - 1)
                + "\n Now you have " + (list.size() - 1) + " tasks in the list.\n"
                + "____________________________________________________________\n";
        list.remove(i - 1);
        return output;
    }

    /**
     * Returns message string for "add" action.
     *
     * @param task new task to add to list.
     * @return string to output for "add" action.
     */
    public static String addMessage(Task task) {
        list.add(task);
        return "____________________________________________________________\n"
                + " Got it. I've added this task:\n"
                + "   " + task
                + "\n Now you have " + (list.size()) + " tasks in the list.\n"
                + "____________________________________________________________\n";
    }

    public void processLine(String original) throws DukeException {
            String[] inputParts = original.split("\\s+");

            //handle mark || unmark
            if (inputParts.length == 2 && (inputParts[0].equals("mark") || inputParts[0].equals("unmark"))) {
                int inputInt = Integer.parseInt(inputParts[1]);
                System.out.println(list.get(inputInt - 1).toggle());
            } else if (original.equals("list")) {
                //handle "list"
                ui.showList(list);
            } else if (inputParts[0].equals("todo")) {
                //handle "todoo"
                String description = original.replace("todo", "");
                if (description.isEmpty()) {
                    throw new DukeException("oi todo what. todo WHATTTTTT!!!!!!!!");
                }
                Task task = new ToDo(description);
                System.out.print(Duke.addMessage(task));
            } else if (inputParts[0].equals("deadline")) {
                //handle "deadline"
                String[] parts = original.replace("deadline", "").split(" /");
                Task task = new Deadline(parts[0], parts[1].replace("by ", ""));
                System.out.print(Duke.addMessage(task));
            } else if (inputParts[0].equals("event")) {
                //handle event
                String[] parts = original.replace("event", "").split(" /");
                Task task = new Event(parts[0], parts[1].replace("from ", ""), parts[2].replace("to ", ""));
                System.out.print(Duke.addMessage(task));
            } else if (inputParts[0].equals("delete")) {
                //handle delete
                int inputInt = Integer.parseInt(inputParts[1]);
                System.out.println(Duke.deleteMessage(inputInt));
            } else {
                throw new DukeException("harh what u talking sia walao");
            }

    }

    public static void loadLine(String original) throws DukeException {
        String[] inputParts = original.split("\\s+");

        if (inputParts[0].equals("todo")) {
            //handle "todoo"
            String description = original.replace("todo", "");
            if (description.isEmpty()) {
                throw new DukeException("oi todo what. todo WHATTTTTT!!!!!!!!");
            }
            Task task = new ToDo(description);
            Duke.addMessage(task);
        } else if (inputParts[0].equals("deadline")) {
            //handle "deadline"
            String[] parts = original.replace("deadline", "").split(" /");
            Task task = new Deadline(parts[0], parts[1].replace("by ", ""));
            Duke.addMessage(task);
        } else if (inputParts[0].equals("event")) {
            //handle event
            String[] parts = original.replace("event", "").split(" /");
            Task task = new Event(parts[0], parts[1].replace("from ", ""), parts[2].replace("to ", ""));
            Duke.addMessage(task);
        } else if (inputParts[0].equals("delete")) {
            //handle delete
            int inputInt = Integer.parseInt(inputParts[1]);
            Duke.deleteMessage(inputInt);
        } else {
            throw new DukeException("harh what u talking sia walao");
        }

    }

}



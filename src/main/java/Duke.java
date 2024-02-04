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
        Parser parser = new Parser();
        ui.showIntro();

        String original = sc.nextLine();

        while (!original.equals("bye")) {
            try {
                parser.processLine(original, list, ui);
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


}



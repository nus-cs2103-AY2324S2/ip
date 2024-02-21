package bit;


import java.util.Scanner;

/**
 * This class parses through inputs of users and
 * translate them to string instructions for chatbot
 */
public class Parser {

    private Scanner scanner = new Scanner(System.in);

    private int index = 0;

    private String word = "";
    private Ui ui = new Ui();

    public Parser() {

    }

    /**
     * Returns index number stored in this object
     * This is usually index of Task you wish to access
     * @return index number stored
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns user's command in string format
     * If there is an error, it will be funneled towards add method, which will handle them
     * @param input
     * @return command
     */
    public String parse(String input) {
        if (input.equals("bye")) {
            return "bye";
        } else if (input.equals("list")) {
            return "list";
        } else if (input.contains("mark ")) {
            return parseForMark(input);
        } else if (input.startsWith("delete")) {
            return parseForDelete(input);
        } else if (input.startsWith("find ")) {
            return parseForFind(input);
        } else {
            return "add";
        }
    }

    /**
     * Get string stored in this object. This is used with find command.
     * @return string stored in object.
     */
    public String getWord() {
        return word;
    }

    private String parseForMark(String input) {
        String[] parts = input.split(" ");
        try {
            int i = Integer.parseInt(parts[1]);
            if (parts[0].equals("mark")) {
                index = i;
                return "mark";
            } else if (parts[0].equals("unmark")) {
                index = i;
                return "unmark";
            } else {
                return "add";
            }

        } catch (NumberFormatException e) {
            return "add";
        }
    }

    private String parseForDelete(String input) {
        try {
            String[] strings = input.split(" ", 2);
            int i = Integer.parseInt(strings[1]);
            index = i;
            return "delete";
        } catch (NumberFormatException x) {
            ui.handleErrorMessage("Not a number");
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.handleErrorMessage("forget");
        }
        return "add";
    }
    private String parseForFind(String input) {
        if (input.trim().equals("find")) {
            ui.handleErrorMessage("forget");
        } else {
            String[] parts = input.split(" ", 2);
            word = parts[1];
            return "find";
        }
        return "add";
    }
}

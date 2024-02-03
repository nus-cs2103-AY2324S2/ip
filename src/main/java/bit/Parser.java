package bit;

import bit.Ui;

import  java.util.Scanner;
public class Parser {

    private Scanner scanner = new Scanner(System.in);

    private int index = 0;

    private String word = "";
    private Ui UI = new Ui();

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

        } else if (input.startsWith("delete")) {
            try {
                String[] strings = input.split(" ", 2);
                int i = Integer.parseInt(strings[1]);
                index = i;
                return "delete";
            } catch (NumberFormatException x) {
                UI.handleErrorMessage("Not a number");
            } catch (ArrayIndexOutOfBoundsException e) {
                UI.handleErrorMessage("forget");
            }
        } else if (input.startsWith("find ")) {
            if (input.trim().equals("find")) {
                UI.handleErrorMessage("forget");
            } else {
                String[] parts = input.split(" ", 2);
                word = parts[1];
                return "find";
            }
        } else {
            return "add";
        }
        return "";
    }

    /**
     * Get string stored in this object. This is used with find command.
     * @return string stored in object.
     */
    public String getWord() {
        return word;
    }
}

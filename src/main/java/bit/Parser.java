package bit;

import bit.Ui;

import  java.util.Scanner;
public class Parser {

    private Scanner scanner = new Scanner(System.in);

    private int index = 0;

    private Ui UI = new Ui();

    public Parser() {

    }

    public int getIndex() {
        return index;
    }

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
        } else {
            return "add";
        }
        return "";


    }
}

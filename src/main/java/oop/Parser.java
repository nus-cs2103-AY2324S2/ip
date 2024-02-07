package oop;

import java.util.Arrays;
import exceptions.MissingIndexException;
import exceptions.MissingDescriptionException;

/**
 * Represents a parser utility for parsing user input.
 * Parser parses the user input into meaningful parts for further processing.
 */
public class Parser {
    private static final String line = "\t______________________________________________________";

    /**
     * Trims the input string and splits it into parts.
     *
     * @param input The input string to be trimmed and split.
     * @return An array containing the trimmed and non-empty parts of the input string.
     */
    public static String[] trim(String input) {
        String[] untrimmedParts = input.split(" ", 2);
        String[] parts = Arrays.stream(untrimmedParts)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
        return parts;
    }

    /**
     * Parses the input string into meaningful parts.
     *
     * @param input The input string to be parsed.
     * @return An array containing the parsed parts of the input string.
     */
    public static String[] parse(String input) {
        String[] parts = trim(input);
        int size = parts.length;
        try {
            if (parts[0].equals("mark") || parts[0].equals("unmark") || parts[0].equals("delete") && size == 1) {
                if (size == 1) {
                    throw new MissingIndexException();
                }
            }

            switch (parts[0]) {
            case ("deadline"):
                if (size == 1 || parts[1].split("/by ").length == 1) {
                    throw new MissingDescriptionException();
                }

                String[] content = parts[1].split("/by ");
                String[] temp = parts;
                parts = new String[3];
                parts[0] = temp[0];
                parts[1] = content[0];
                parts[2] = content[1];
                break;
            case ("event"):
                if (size == 1 || parts[1].split("/from ").length == 1) {
                    throw new MissingDescriptionException();
                }

                content = parts[1].split("/from ");

                if (size == 1 || content[1].split("/to ").length == 1) {
                    throw new MissingDescriptionException();
                }

                String[] dates = content[1].split(" /to ");
                temp = parts;
                parts = new String[4];
                parts[0] = temp[0];
                parts[1] = content[0];
                parts[2] = dates[0];
                parts[3] = dates[1];
                break;
            case ("todo"):
                if (size == 1) {
                    throw new MissingDescriptionException();
                }
                break;
            default:
                if (!parts[0].equals("list") && !parts[0].equals("bye") && !parts[0].equals("mark")
                        && !parts[0].equals("unmark") && !parts[0].equals("delete")) {
                    System.out.println(line);
                    System.out.println("\t I think you haven't had enough vitamin C." +
                            "\n\t I can't understand what you want me to do!" +
                            "\n\t I suggest you take some LEMONA.");
                    System.out.println(line);
                }
            }
        } catch (MissingDescriptionException e) {
            System.out.println(line);
            System.out.println(e.toString(parts[0]));
            System.out.println(line);
        } catch (MissingIndexException e) {
            System.out.println(line);
            System.out.println(e.toString(parts[0]));
            System.out.println(line);
        }
        return parts;
    }
}

package util;

import java.util.Scanner;

import exception.NarutoException;

/**
 * The Parser class is responsible for parsing user input and extracting relevant information.
 */
public class Parser {
    private static Scanner sc = new Scanner(System.in);

    private Parser() {
        // Private constructor to prevent instantiation of the class
    }

    /**
     * Parses the input string into an integer index.
     *
     * @param input     The input string to be parsed.
     * @param taskList  The TaskList object containing the tasks.
     * @return          The parsed integer index.
     * @throws NarutoException  If the input string is not a valid index or is out of range.
     */
    static int parseIdx(String input, TaskList taskList) throws NarutoException {
        int idx;
        try {
            idx = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw NarutoException.createInvalidIndexException();
        }
        if (idx > taskList.getSize() || idx <= 0) {
            throw NarutoException.createInvalidIndexException();
        }
        return idx;
    }

    /**
     * Parses the input string into a description.
     *
     * @param input     The input string to be parsed.
     * @return          The parsed description.
     * @throws NarutoException  If the input string is empty.
     */
    static String parseDescription(String input) throws NarutoException {
        String description = input.trim();

        if (description.isEmpty()) {
            throw NarutoException.createEmptyCommandException();
        }
        return description;
    }

    /**
     * Parses the input string into a deadline description and date/time.
     *
     * @param input     The input string to be parsed.
     * @return          An array containing the parsed description and deadline.
     * @throws NarutoException  If the input string is empty, does not contain "/by", or has an invalid deadline.
     */
    static String[] parseDeadline(String input) throws NarutoException {
        if (input.isEmpty()) {
            throw NarutoException.createEmptyDeadlineException();
        }
        if (!input.contains("/by")) {
            throw NarutoException.createInvalidDeadlineException();
        }
        String [] tokens = input.split("/by");
        String description = tokens[0].trim();
        String by = tokens[1].trim();
        if (!DateTimeUtil.isValid(by)) {
            throw NarutoException.createInvalidDeadlineException();
        }
        return new String[] { description, by };
    }

    /**
     * Parses the input string into an event description, start date/time, and end date/time.
     *
     * @param input     The input string to be parsed.
     * @return          An array containing the parsed description, start date/time, and end date/time.
     * @throws NarutoException  If the input string is empty, does not contain "/from" and "/to", or
     *                  has an invalid event.
     */
    static String[] parseEvent(String input) throws NarutoException {
        if (input.isEmpty()) {
            throw NarutoException.createEmptyEventException();
        }
        if (!(input.contains("/from") && input.contains("/to"))) {
            throw NarutoException.createInvalidEventException();
        }
        String[] tokens = input.split("/from");
        String description = tokens[0].trim();
        tokens = tokens[1].split("/to");
        String from = tokens[0].trim();
        String to = tokens[1].trim();
        if (!DateTimeUtil.isValid(from) || !DateTimeUtil.isValid(to)) {
            throw NarutoException.createInvalidDeadlineException();
        }
        return new String[] { description, from , to };
    }
}

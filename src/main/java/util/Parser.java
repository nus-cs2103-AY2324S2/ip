package util;

import exception.*;

import java.util.*;

public class Parser {
    private static Scanner sc = new Scanner(System.in);
    private Parser() {

    }

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

    static String parseDescription(String input) throws NarutoException {
        // Read the actual description
        String description = input.trim();

        if (description.isEmpty()) {
            throw NarutoException.createEmptyTodoException();
        }
        return description;
    }

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
